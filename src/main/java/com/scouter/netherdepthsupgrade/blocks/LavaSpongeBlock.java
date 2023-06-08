package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class LavaSpongeBlock extends Block {
    public static final int MAX_DEPTH = 6;
    public static final int MAX_COUNT = 64;
    private static final Direction[] ALL_DIRECTIONS = Direction.values();
    public LavaSpongeBlock(Properties p_56796_) {
        super(p_56796_);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pOldState.is(pState.getBlock())) {
            this.tryAbsorbLava(pLevel, pPos);
        }
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        this.tryAbsorbLava(pLevel, pPos);
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
    }

    protected void tryAbsorbLava(Level pLevel, BlockPos pPos) {
        if (this.removeLavaBreadthFirstSearch(pLevel, pPos)) {
            pLevel.setBlock(pPos, NDUBlocks.WET_LAVA_SPONGE.get().defaultBlockState(), 3);
            pLevel.levelEvent(2001, pPos, Block.getId(Blocks.LAVA.defaultBlockState()));
        }

    }

    private boolean removeLavaBreadthFirstSearch(Level pLevel, BlockPos pPos) {
        BlockState spongeState = pLevel.getBlockState(pPos);
        return BlockPos.breadthFirstTraversal(pPos, 6, 65, (p_277519_, p_277492_) -> {
            for(Direction direction : ALL_DIRECTIONS) {
                p_277492_.accept(p_277519_.relative(direction));
            }

        }, (p_279054_) -> {
            if (p_279054_.equals(pPos)) {
                return true;
            } else {
                BlockState blockstate = pLevel.getBlockState(p_279054_);
                FluidState fluidstate = pLevel.getFluidState(p_279054_);
                    Block block = blockstate.getBlock();
                    if (block instanceof BucketPickup) {
                        BucketPickup bucketpickup = (BucketPickup)block;
                        if (!bucketpickup.pickupBlock(pLevel, p_279054_, blockstate).isEmpty()) {
                            return true;
                        }
                    }

                    if (blockstate.getBlock() instanceof LiquidBlock) {
                        pLevel.setBlock(p_279054_, Blocks.AIR.defaultBlockState(), 3);
                    } else {
                        if (!blockstate.is(NDUBlocks.WARPED_SEAGRASS.get()) && !blockstate.is(NDUBlocks.TALL_WARPED_SEAGRASS.get()) && !blockstate.is(NDUBlocks.WARPED_KELP.get()) && !blockstate.is(NDUBlocks.WARPED_KELP_PLANT.get())) {
                            return false;
                        }

                        BlockEntity blockentity = blockstate.hasBlockEntity() ? pLevel.getBlockEntity(p_279054_) : null;
                        dropResources(blockstate, pLevel, p_279054_, blockentity);
                        pLevel.setBlock(p_279054_, Blocks.AIR.defaultBlockState(), 3);
                    }

                    return true;
                }

        }) > 1;
    }
}
