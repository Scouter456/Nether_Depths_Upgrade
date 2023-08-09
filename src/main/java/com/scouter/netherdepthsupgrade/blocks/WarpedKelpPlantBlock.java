package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;

public class WarpedKelpPlantBlock extends GrowingLavaPlantBodyBlock implements LiquidBlockContainer {
    public WarpedKelpPlantBlock(Properties p_54323_) {
        super(p_54323_, Direction.UP, Shapes.block(), true);
    }

    protected GrowingLavaPlantHeadBlock getHeadBlock() {
        return (GrowingLavaPlantHeadBlock) NDUBlocks.WARPED_KELP;
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.LAVA.getSource(false);
    }

    protected boolean canAttachTo(BlockState pState) {
        return this.getHeadBlock().canAttachTo(pState);
    }

    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }
}
