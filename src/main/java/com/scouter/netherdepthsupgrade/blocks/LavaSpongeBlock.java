package com.scouter.netherdepthsupgrade.blocks;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;

import java.util.Queue;

public class LavaSpongeBlock extends Block {
    public static final int MAX_DEPTH = 6;
    public static final int MAX_COUNT = 64;

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
        if (this.removeWaterBreadthFirstSearch(pLevel, pPos)) {
            pLevel.setBlock(pPos, NDUBlocks.WET_LAVA_SPONGE.get().defaultBlockState(), 2);
            pLevel.levelEvent(2001, pPos, Block.getId(Blocks.LAVA.defaultBlockState()));
        }

    }

    private boolean removeWaterBreadthFirstSearch(Level pLevel, BlockPos pPos) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(pPos, 0));
        int i = 0;

        while(!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos blockpos = tuple.getA();
            int j = tuple.getB();

            for(Direction direction : Direction.values()) {
                BlockPos blockpos1 = blockpos.relative(direction);
                BlockState blockstate = pLevel.getBlockState(blockpos1);
                FluidState fluidstate = pLevel.getFluidState(blockpos1);
                Material material = blockstate.getMaterial();
                if (fluidstate.is(FluidTags.LAVA)) {
                    if (blockstate.getBlock() instanceof BucketPickup && !((BucketPickup)blockstate.getBlock()).pickupBlock(pLevel, blockpos1, blockstate).isEmpty()) {
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (blockstate.getBlock() instanceof LiquidBlock) {
                        pLevel.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    }// else if (material == Material.WATER_PLANT || material == Material.REPLACEABLE_WATER_PLANT) {
                      //  BlockEntity blockentity = blockstate.hasBlockEntity() ? pLevel.getBlockEntity(blockpos1) : null;
                       // dropResources(blockstate, pLevel, blockpos1, blockentity);
                        //pLevel.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                        //++i;
                        //if (j < 6) {
                        //    queue.add(new Tuple<>(blockpos1, j + 1));
                        //}
                    //}
                }
            }

            if (i > 64) {
                break;
            }
        }

        return i > 0;
    }
}
