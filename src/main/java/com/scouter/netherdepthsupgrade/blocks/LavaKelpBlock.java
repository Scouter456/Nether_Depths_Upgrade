package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class LavaKelpBlock extends GrowingLavaPlantHeadBlock implements ILiquidContainer {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
    private static final double GROW_PER_TICK_PROBABILITY = 0.14D;

    public LavaKelpBlock(Properties p_54300_) {
        super(p_54300_, Direction.UP, SHAPE, true, 0.14D);
    }

    protected boolean canGrowInto(BlockState pState) {
        return pState.is(Blocks.LAVA);
    }

    protected Block getBodyBlock() {
        return NDUBlocks.WARPED_KELP_PLANT.get();
    }

    protected boolean canAttachTo(BlockState pState) {
        return !pState.is(Blocks.MAGMA_BLOCK);
    }

    public boolean canPlaceLiquid(IBlockReader pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    public boolean placeLiquid(IWorld pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }

    /**
     * Used to determine how much to grow the plant when using bonemeal.
     */
    protected int getBlocksToGrowWhenBonemealed(Random pRandom) {
        return 1;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return fluidstate.is(FluidTags.LAVA) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(pContext) : null;
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.LAVA.getSource(false);
    }
}
