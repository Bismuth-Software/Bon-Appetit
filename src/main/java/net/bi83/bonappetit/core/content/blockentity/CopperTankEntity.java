package net.bi83.bonappetit.core.content.blockentity;

import net.bi83.bonappetit.core.BABlockEntities;
import net.bi83.bonappetit.core.content.block.CopperTankBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.CapabilityRegistry;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CopperTankEntity extends BlockEntity {
    public static final int CAPACITY = 8000;
    private int conversionProgress = 0;

    private ItemStack augmentSlot = ItemStack.EMPTY;

    public boolean insertAugment(ItemStack stack) {
        if (hasAugment() || stack.isEmpty() || !isValidAugment(stack)) return false;

        augmentSlot = stack.split(1); // take one item
        setChanged();
        return true;
    }

    public boolean hasAugment() {
        return !augmentSlot.isEmpty();
    }

    public ItemStack getAugment() {
        return augmentSlot;
    }

    public void clearAugment() {
        augmentSlot = ItemStack.EMPTY;
        setChanged();
    }

    private boolean isValidAugment(ItemStack stack) {
        // Define valid augment items here
        return stack.is(Items.REDSTONE) || stack.is(Items.GLOWSTONE_DUST);
    }

    private final FluidTank tank = new FluidTank(CAPACITY) {
        @Override
        public int fill(FluidStack stack, FluidAction action) {
            BlockState state = level.getBlockState(worldPosition);
            if (!state.getValue(CopperTankBlock.OPEN)) return 0;
            return super.fill(stack, action);
        }

        @Override
        public FluidStack drain(FluidStack stack, FluidAction action) {
            BlockState state = level.getBlockState(worldPosition);
            if (!state.getValue(CopperTankBlock.OPEN)) return FluidStack.EMPTY;
            return super.drain(stack, action);
        }

        @Override
        protected void onContentsChanged() {
            setChanged();
            if (level != null && !level.isClientSide) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        }
    };

    public FluidTank getTank() {
        return tank;
    }
    public IFluidHandler getFluidHandler() {
        return tank;
    }

    public void tick() {
        if (level == null || level.isClientSide) return;

        FluidStack stack = tank.getFluid();
        if (stack.isEmpty() || stack.getFluid() != NeoForgeMod.MILK.get()) {
            conversionProgress = 0; // reset if not milk
            return;
        }

        BlockPos below = worldPosition.below();
        BlockState belowState = level.getBlockState(below);

        // Only convert if there's ice below
        if (belowState.is(Blocks.ICE) || belowState.is(Blocks.BLUE_ICE) || belowState.is(Blocks.PACKED_ICE)) {

            // Compute dynamic conversion time
            int fluidAmount = stack.getAmount(); // in mB
            int baseTime = 300; // for first 1000 mB
            int extra = fluidAmount - 1000; // extra mB beyond first bucket
            if (extra < 0) extra = 0;

            // extra time = extra * 0.3 ticks → round to integer
            int extraTicks = Math.round(extra * 0.15f);
            int conversionTime = baseTime + extraTicks;

            conversionProgress++;

            if (conversionProgress >= conversionTime) {
                int amount = stack.getAmount();
                tank.drain(amount, IFluidHandler.FluidAction.EXECUTE);

                // default result
                Fluid resultFluid = Fluids.WATER;

                // change fluid based on augment
                if (hasAugment()) {
                    ItemStack augment = getAugment();
                    if (augment.is(Items.REDSTONE)) {
                        resultFluid = Fluids.LAVA; // example: redstone makes it lava
                    } else if (augment.is(Items.GLOWSTONE_DUST)) {
                        resultFluid = Fluids.EMPTY; // if you ever have a milk fluid, or just leave WATER
                    }
                    clearAugment();
                }

                tank.fill(new FluidStack(resultFluid, amount), IFluidHandler.FluidAction.EXECUTE);

                level.playSound(null, worldPosition, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 0.8F, 1.0F);

                // snowflake particles
                RandomSource rand = level.getRandom();
                if (level instanceof ServerLevel server) {
                    for (int i = 0; i < 6; i++) {
                        double d0 = (rand.nextDouble() - 0.5) * 0.02;
                        double d1 = 0.075D;
                        double d2 = (rand.nextDouble() - 0.5) * 0.02;
                        double x = worldPosition.getX() + 0.3 + rand.nextDouble() * 0.4;
                        double y = worldPosition.getY() + 1;
                        double z = worldPosition.getZ() + 0.3 + rand.nextDouble() * 0.4;
                        server.sendParticles(ParticleTypes.SNOWFLAKE, x, y, z, 0, d0, d1, d2, 0.5);
                    }
                }

                conversionProgress = 0;
            }

        } else {
            conversionProgress = 0; // reset if no ice below
        }
    }

    public CopperTankEntity(BlockPos pos, BlockState blockState) {
        super(BABlockEntities.COPPER_TANK.get(), pos, blockState);
    }

    @Nullable @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}