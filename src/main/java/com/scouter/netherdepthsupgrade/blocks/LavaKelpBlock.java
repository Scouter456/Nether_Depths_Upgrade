package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class LavaKelpBlock extends GrowingLavaPlantHeadBlock implements LiquidBlockContainer {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
    private static final double GROW_PER_TICK_PROBABILITY = 0.14D;

    public LavaKelpBlock(Properties p_54300_) {
        super(p_54300_, Direction.UP, SHAPE, true, 0.14D);
    }

    protected boolean canGrowInto(BlockState pState) {
        return pState.is(Blocks.LAVA);
    }

    protected Block getBodyBlock() {
        return NDUBlocks.WARPED_KELP_PLANT;
    }

    protected boolean canAttachTo(BlockState pState) {
        return !pState.is(Blocks.MAGMA_BLOCK);
    }

    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }

    /**
     * Used to determine how much to grow the plant when using bonemeal.
     */
    protected int getBlocksToGrowWhenBonemealed(RandomSource pRandom) {
        return 1;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return fluidstate.is(FluidTags.LAVA) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(pContext) : null;
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.LAVA.getSource(false);
    }
}
