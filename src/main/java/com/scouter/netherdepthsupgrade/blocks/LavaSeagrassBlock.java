package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class LavaSeagrassBlock extends BushBlock implements IGrowable, ILiquidContainer, net.minecraftforge.common.IForgeShearable {
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public LavaSeagrassBlock(Properties p_154496_) {
        super(p_154496_);
    }

    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
        return pState.isFaceSturdy(pLevel, pPos, Direction.UP) && !pState.is(Blocks.MAGMA_BLOCK);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return fluidstate.is(FluidTags.LAVA) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(pContext) : null;
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, IWorld pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        BlockState blockstate = super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        if (!blockstate.isAir()) {
            pLevel.getLiquidTicks().scheduleTick(pCurrentPos, Fluids.LAVA, Fluids.LAVA.getTickDelay(pLevel));
        }
        return blockstate;
    }

    /**
     * @return whether bonemeal can be used on this block
     */
    public boolean isValidBonemealTarget(IBlockReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return true;
    }

    public boolean isBonemealSuccess(World pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        return true;
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.LAVA.getSource(false);
    }


    public void performBonemeal(ServerWorld pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        BlockState blockstate = NDUBlocks.TALL_WARPED_SEAGRASS.get().defaultBlockState();
        BlockState blockstate1 = blockstate.setValue(TallLavaSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
        BlockPos blockpos = pPos.above();
        if (pLevel.getBlockState(blockpos).is(Blocks.LAVA)) {
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.setBlock(blockpos, blockstate1, 2);
        }

    }

    public boolean canPlaceLiquid(IBlockReader pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    public boolean placeLiquid(IWorld pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }
}
