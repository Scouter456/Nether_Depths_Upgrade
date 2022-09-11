package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;
import java.util.Random;

public abstract class GrowingLavaPlantBodyBlock extends GrowingLavaPlantBlock implements IGrowable {
    protected GrowingLavaPlantBodyBlock(AbstractBlock.Properties p_i241179_1_, Direction p_i241179_2_, VoxelShape p_i241179_3_, boolean p_i241179_4_) {
        super(p_i241179_1_, p_i241179_2_, p_i241179_3_, p_i241179_4_);
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
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, IWorld pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pFacing == this.growthDirection.getOpposite() && !pState.canSurvive(pLevel, pCurrentPos)) {
            pLevel.getBlockTicks().scheduleTick(pCurrentPos, this, 1);
        }

        GrowingLavaPlantHeadBlock abstracttopplantblock = this.getHeadBlock();
        if (pFacing == this.growthDirection) {
            Block block = pFacingState.getBlock();
            if (block != this && block != abstracttopplantblock) {
                return abstracttopplantblock.getStateForPlacement(pLevel);
            }
        }

        if (this.scheduleFluidTicks) {
            pLevel.getLiquidTicks().scheduleTick(pCurrentPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public ItemStack getCloneItemStack(IBlockReader pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(this.getHeadBlock());
    }

    /**
     * @return whether bonemeal can be used on this block
     */
    public boolean isValidBonemealTarget(IBlockReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        Optional<BlockPos> optional = this.getHeadPos(pLevel, pPos, pState);
        return optional.isPresent() && this.getHeadBlock().canGrowInto(pLevel.getBlockState(optional.get().relative(this.growthDirection)));
    }

    public boolean isBonemealSuccess(World pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        return true;
    }

    public void performBonemeal(ServerWorld pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        Optional<BlockPos> optional = this.getHeadPos(pLevel, pPos, pState);
        if (optional.isPresent()) {
            BlockState blockstate = pLevel.getBlockState(optional.get());
            ((GrowingLavaPlantHeadBlock)blockstate.getBlock()).performBonemeal(pLevel, pRand, optional.get(), blockstate);
        }

    }

    private Optional<BlockPos> getHeadPos(IBlockReader p_235501_1_, BlockPos p_235501_2_, BlockState p_235501_3_) {
        BlockPos blockpos = p_235501_2_;

        Block block;
        do {
            blockpos = blockpos.relative(this.growthDirection);
            block = p_235501_1_.getBlockState(blockpos).getBlock();
        } while(block == p_235501_3_.getBlock());

        return block == this.getHeadBlock() ? Optional.of(blockpos) : Optional.empty();
    }

    public boolean canBeReplaced(BlockState pState, BlockItemUseContext pUseContext) {
        boolean flag = super.canBeReplaced(pState, pUseContext);
        return flag && pUseContext.getItemInHand().getItem() == this.getHeadBlock().asItem() ? false : flag;
    }

    protected Block getBodyBlock() {
        return this;
    }
}
