package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class LavaKelpPlantBlock extends GrowingLavaPlantBodyBlock implements ILiquidContainer {
    public LavaKelpPlantBlock(Properties p_54323_) {
        super(p_54323_, Direction.UP, VoxelShapes.block(), true);
    }

    protected GrowingLavaPlantHeadBlock getHeadBlock() {
        return (GrowingLavaPlantHeadBlock) NDUBlocks.WARPED_KELP.get();
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.LAVA.getSource(false);
    }

    public boolean canPlaceLiquid(IBlockReader pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    public boolean placeLiquid(IWorld pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }

}
