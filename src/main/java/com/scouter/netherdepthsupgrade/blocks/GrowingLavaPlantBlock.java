package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class GrowingLavaPlantBlock extends Block {
    protected final Direction growthDirection;
    protected final boolean scheduleFluidTicks;
    protected final VoxelShape shape;

    public GrowingLavaPlantBlock(Properties pProperties, Direction pGrowthDirection, VoxelShape pShape, boolean pScheduleFluidTicks) {
        super(pProperties);
        this.growthDirection = pGrowthDirection;
        this.shape = pShape;
        this.scheduleFluidTicks = pScheduleFluidTicks;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos().relative(this.growthDirection));
        return !blockstate.is(this.getHeadBlock()) && !blockstate.is(this.getBodyBlock()) ? this.getStateForPlacement(pContext.getLevel()) : this.getBodyBlock().defaultBlockState();
    }

    public BlockState getStateForPlacement(IWorld pLevel) {
        return this.defaultBlockState();
    }

    public boolean canSurvive(BlockState pState, IWorldReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = pLevel.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachToBlock(block)) {
            return false;
        } else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.isFaceSturdy(pLevel, blockpos, this.growthDirection);
        }
    }

    public void tick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRand) {
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }

    }

    protected boolean canAttachToBlock(Block p_230333_1_) {
        return true;
    }

    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        return this.shape;
    }

    protected abstract GrowingLavaPlantHeadBlock getHeadBlock();

    protected abstract Block getBodyBlock();
}
