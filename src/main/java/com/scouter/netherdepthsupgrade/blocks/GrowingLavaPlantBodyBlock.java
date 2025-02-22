package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

public abstract class GrowingLavaPlantBodyBlock extends GrowingLavaPlantBlock implements BonemealableBlock {
    protected GrowingLavaPlantBodyBlock(Properties p_53886_, Direction p_53887_, VoxelShape p_53888_, boolean p_53889_) {
        super(p_53886_, p_53887_, p_53888_, p_53889_);
    }

    protected BlockState updateHeadAfterConvertedFromBody(BlockState p_153326_, BlockState p_153327_) {
        return p_153327_;
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */

    protected BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource) {
        if (direction == this.growthDirection.getOpposite() && !blockState.canSurvive(levelReader, blockPos)) {
            scheduledTickAccess.scheduleTick(blockPos, this, 1);
        }

        GrowingLavaPlantHeadBlock growingPlantHeadBlock = this.getHeadBlock();
        if (direction == this.growthDirection && !blockState2.is(this) && !blockState2.is(growingPlantHeadBlock)) {
            return this.updateHeadAfterConvertedFromBody(blockState, growingPlantHeadBlock.getStateForPlacement(randomSource));
        } else {
            if (this.scheduleFluidTicks) {
                scheduledTickAccess.scheduleTick(blockPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(levelReader));
            }

            return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
        }
    }


    protected ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return new ItemStack(this.getHeadBlock());
    }



    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        Optional<BlockPos> optional = this.getHeadPos(levelReader, blockPos, blockState.getBlock());
        return optional.isPresent() && this.getHeadBlock().canGrowInto(levelReader.getBlockState(optional.get().relative(this.growthDirection)));
    }

    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        Optional<BlockPos> optional = this.getHeadPos(pLevel, pPos, pState.getBlock());
        if (optional.isPresent()) {
            BlockState blockstate = pLevel.getBlockState(optional.get());
            ((GrowingLavaPlantHeadBlock)blockstate.getBlock()).performBonemeal(pLevel, pRandom, optional.get(), blockstate);
        }

    }

    private Optional<BlockPos> getHeadPos(BlockGetter p_153323_, BlockPos p_153324_, Block p_153325_) {
        return BlockUtil.getTopConnectedBlock(p_153323_, p_153324_, p_153325_, this.growthDirection, this.getHeadBlock());
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        boolean flag = super.canBeReplaced(pState, pUseContext);
        return flag && pUseContext.getItemInHand().is(this.getHeadBlock().asItem()) ? false : flag;
    }

    protected Block getBodyBlock() {
        return this;
    }
}
