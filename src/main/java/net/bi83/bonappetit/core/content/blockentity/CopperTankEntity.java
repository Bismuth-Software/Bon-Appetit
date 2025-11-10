package net.bi83.bonappetit.core.content.blockentity;

import net.bi83.bonappetit.core.BABlockEntities;
import net.bi83.bonappetit.core.content.block.CopperTankBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.capabilities.CapabilityRegistry;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CopperTankEntity extends BlockEntity {
    public static final int CAPACITY = 8000;

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