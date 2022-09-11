package com.scouter.netherdepthsupgrade.world.feature;

import com.mojang.serialization.Codec;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;


public class VentFeature extends Feature<NoFeatureConfig> {
    public static final Logger LOGGER = LogManager.getLogger(MODID);


    public VentFeature(Codec<NoFeatureConfig> p_66219_) {
        super(p_66219_);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     *
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        List<Integer> list = new ArrayList<>();
        list.clear();
        for(int l = -29; l < 1;l++){
            list.add(l);
        }
        ISeedReader worldgenlevel = reader;
        BlockPos blockpos = pos;
        int z = 30 + list.get(rand.nextInt(30));
        BlockPos blockpos1 = new BlockPos(blockpos.getX(), z, blockpos.getZ());
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA)) {
            while(worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA) || worldgenlevel.getBlockState(blockpos1).is(NDUBlocks.WARPED_SEAGRASS.get()) || worldgenlevel.getBlockState(blockpos1).is(NDUBlocks.WARPED_KELP.get()) || worldgenlevel.getBlockState(blockpos1).is(NDUBlocks.WARPED_KELP.get())){
                blockpos1 = blockpos1.below();

            }
            BlockState block = Blocks.NETHERRACK.defaultBlockState();
            BlockState block2 = Blocks.ANCIENT_DEBRIS.defaultBlockState();
            BlockState block3 = Blocks.NETHER_GOLD_ORE.defaultBlockState();
            BlockState block4 = Blocks.NETHER_QUARTZ_ORE.defaultBlockState();
            BlockState block5 = Blocks.LAVA.defaultBlockState();
            BlockState block6 = Blocks.AIR.defaultBlockState();
            int rRand = rand.nextInt( 7);
            int heightMax = rand.nextInt(15) +5;
            for (int y = 0; y < heightMax; y++) {
                for (int l = 0; l < (rRand); l++) {
                    for (int p = 0; p < (rRand); p++) {
                        for (double a = 0; a < (Math.PI * 2); a = a + 0.1) {
                            double x = (l) * Math.cos(a);
                            double k = (p) * Math.sin(a);
                            int randomNum = rand.nextInt(100);
                            BlockPos blockPos = new BlockPos(blockpos1.offset(Math.round(x), y, Math.round(k)));
                            if (randomNum < 80) {
                                worldgenlevel.setBlock(blockPos, block, 3);

                            } else if (randomNum > 80 && randomNum < 90) {
                                worldgenlevel.setBlock(blockPos, block4, 3);
                            } else if (randomNum > 90 && randomNum < 98) {
                                worldgenlevel.setBlock(blockPos, block3, 3);
                            } else if (randomNum > 98 && randomNum < 100) {
                                worldgenlevel.setBlock(blockPos, block2, 3);
                            }
                        }
                    }
                }
                if (y % 2 == 0) {
                    rRand--;
                }
            }
            for (int y = 0; y < heightMax; y++) {
                BlockPos blockPos = new BlockPos(blockpos1.offset(0, y, 0));
                if (checkSurrounding(worldgenlevel, blockPos)) {
                    worldgenlevel.setBlock(blockPos, block5, 3);
                }if(blockPos.getY() > 29 && !checkSurrounding(worldgenlevel, blockPos)){
                    worldgenlevel.setBlock(blockPos, block6, 3);
                }
            }


        }


        return true;
    }

    public boolean checkSurrounding(ISeedReader level, BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState blockstate1 = level.getBlockState(pos.relative(direction));
            BlockState fluidstate = level.getBlockState(pos.relative(direction));
            if (fluidstate.is(Blocks.LAVA) || !blockstate1.is(Blocks.AIR)) {
                return true;
            }
        }
        return false;
    }
}
