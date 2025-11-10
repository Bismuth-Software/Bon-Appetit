package net.bi83.bonappetit.core.content.block;

import com.mojang.serialization.MapCodec;
import net.bi83.bonappetit.core.content.blockentity.CopperTankEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

public class CopperTankBlock extends BaseEntityBlock implements EntityBlock {
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final MapCodec<CopperTankBlock> CODEC = simpleCodec(CopperTankBlock::new);

    public CopperTankBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OPEN);
    }
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean player = context.getPlayer() != null && context.getPlayer().isShiftKeyDown();
        return this.defaultBlockState().setValue(OPEN, false);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }


    public @NotNull BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        /*if (!level.isClientSide && player.isCreative()) {
            preventCreativeDropFromBottomPart(level, pos, state, player);
        }*/
        super.playerWillDestroy(level, pos, state, player);
        return state;
    }

    /*protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf half = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);
        BlockPos alt = half == DoubleBlockHalf.UPPER ? pos.below() : pos.above();
        BlockState altState = level.getBlockState(alt);
        if (altState.is(state.getBlock()) && altState.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) != half) {
            BlockState ans = altState.getFluidState().createLegacyBlock(); Blocks.AIR.defaultBlockState();
            level.setBlock(alt, ans, 35);
            level.levelEvent(player, 2001, alt, Block.getId(altState));
        }
    }*/

    @NotNull
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!(level.getBlockEntity(pos) instanceof CopperTankEntity tub)) return InteractionResult.PASS;

        boolean open = state.getValue(OPEN);
        ItemStack inHand = player.getItemInHand(hand);
        ItemStack result;

        if (player.isShiftKeyDown() || (!inHand.isEmpty() && inHand.is(ItemTags.PICKAXES))) {
            if (!level.isClientSide) {setOpen(state, level, pos, player);}
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        if (!open) {
            return InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

    //block entity
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public static void setOpen(BlockState state, Level level, BlockPos pos, Player player) {
        boolean open = state.getValue(OPEN);

        level.playSound(null, pos, SoundEvents.COPPER_DOOR_OPEN, SoundSource.BLOCKS, 0.8F, 0.8F);
        level.setBlock(pos, state.setValue(OPEN, !open), 3);
        level.gameEvent(player, !open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    @NotNull @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CopperTankEntity(blockPos, blockState);
    }

    public static void contentApply(Level level, BlockPos pos) {
        RandomSource rand = level.getRandom();
        BlockEntity entity = level.getBlockEntity(pos);

        if (entity instanceof CopperTankEntity tub) {
            FluidStack stack = tub.getTank().getFluid();
            if (stack.getFluid().isSame(Fluids.LAVA)) {
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.BLOCKS, 0.8F, 0.8F);
            } else {
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 0.8F, 0.8F);
            }
        }

        if (!level.isClientSide && level instanceof ServerLevel server)
            for(int i = 0; i < 6; ++i) {
                double d0 = (rand.nextDouble() - 0.5) * 0.02;
                double d1 = 0.075D;
                double d2 = (rand.nextDouble() - 0.5) * 0.02;

                double x = pos.getX() + 0.3 + rand.nextDouble() * 0.4;
                double y = pos.getY() + 1;
                double z = pos.getZ() + 0.3 + rand.nextDouble() * 0.4;
                server.sendParticles(ParticleTypes.SNOWFLAKE, x, y, z, 0, d0, d1, d2, 0.5);
            }
    }
}