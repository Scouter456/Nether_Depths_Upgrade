package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IForgeShearable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class TallLavaSeagrassBlock extends DoublePlantBlock implements ILiquidContainer, IForgeShearable {
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final EnumProperty<DoubleBlockHalf> HALF = DoublePlantBlock.HALF;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public TallLavaSeagrassBlock(Properties p_154745_) {
        super(p_154745_);
    }

    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
        return pState.isFaceSturdy(pLevel, pPos, Direction.UP) && !pState.is(Blocks.MAGMA_BLOCK);
    }

    public ItemStack getCloneItemStack(IBlockReader pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(NDUBlocks.WARPED_SEAGRASS.get());
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        BlockState blockstate = super.getStateForPlacement(pContext);
        if (blockstate != null) {
            FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos().above());
            if (fluidstate.is(FluidTags.LAVA) && fluidstate.getAmount() == 8) {
                return blockstate;
            }
        }

        return null;
    }

    public boolean canSurvive(BlockState pState, IWorldReader pLevel, BlockPos pPos) {
        if (pState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockstate = pLevel.getBlockState(pPos.below());
            return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
        } else {
            FluidState fluidstate = pLevel.getFluidState(pPos);
            return super.canSurvive(pState, pLevel, pPos) && fluidstate.is(FluidTags.LAVA) && fluidstate.getAmount() == 8;
        }
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
