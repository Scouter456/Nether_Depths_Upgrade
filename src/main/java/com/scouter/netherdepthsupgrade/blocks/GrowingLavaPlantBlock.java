package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

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
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos().relative(this.growthDirection));
        return !blockstate.is(this.getHeadBlock()) && !blockstate.is(this.getBodyBlock()) ? this.getStateForPlacement(pContext.getLevel()) : this.getBodyBlock().defaultBlockState();
    }

    public BlockState getStateForPlacement(LevelAccessor pLevel) {
        return this.defaultBlockState();
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (!this.canAttachTo(blockstate)) {
            return false;
        } else {
            return blockstate.is(this.getHeadBlock()) || blockstate.is(this.getBodyBlock()) || blockstate.isFaceSturdy(pLevel, blockpos, this.growthDirection);
        }
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRand) {
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }

    }

    protected boolean canAttachTo(BlockState pState) {
        return true;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return this.shape;
    }

    protected abstract GrowingLavaPlantHeadBlock getHeadBlock();

    protected abstract Block getBodyBlock();
}
