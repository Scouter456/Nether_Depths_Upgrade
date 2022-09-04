package com.scouter.netherdepthsupgrade.world.feature;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import org.slf4j.Logger;

import java.util.Random;


public class SpongeFeature extends Feature<NoneFeatureConfiguration> {
    private static final Logger LOGGER = LogUtils.getLogger();

    public SpongeFeature(Codec<NoneFeatureConfiguration> p_66219_) {
        super(p_66219_);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     *
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159956_) {
        WorldGenLevel worldgenlevel = p_159956_.level();
        BlockPos blockpos = p_159956_.origin();
        Random random = p_159956_.random();
        int t = 30 + random.nextInt(-29, 0);
        BlockPos blockpos1 = new BlockPos(blockpos.getX(), t, blockpos.getZ());
        BlockState block = NDUBlocks.WET_LAVA_SPONGE.get().defaultBlockState();

        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA)) {
            while (worldgenlevel.getFluidState(blockpos1).is(Fluids.LAVA)) {
                blockpos1 = blockpos1.below();
            }
            int radius = random.nextInt(3, 6);
            radius += 0.5D;
            double radiusSq = radius * radius;
            int ceilRadius = (int) Math.ceil(radius);
            for (int x = 0; x <= ceilRadius; x++) {
                for (int y = 0; y <= ceilRadius; y++) {
                    for (int z = 0; z <= ceilRadius; z++) {
                        double dSq = lengthSq(x, y, z);

                        if (dSq > radiusSq) {
                            continue;
                        }
                        if (worldgenlevel.getFluidState((blockpos1.offset(x, y, z))).is(Fluids.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(x, y, z), block, 3);
                        }
                        if (worldgenlevel.getFluidState((blockpos1.offset(-x, y, z))).is(Fluids.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(-x, y, z), block, 3);
                        }
                        if (worldgenlevel.getFluidState((blockpos1.offset(x, -y, z))).is(Fluids.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(x, -y, z), block, 3);
                        }
                        if (worldgenlevel.getFluidState((blockpos1.offset(x, y, -z))).is(Fluids.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(x, y, -z), block, 3);
                        }
                        if (worldgenlevel.getFluidState((blockpos1.offset(-x, -y, z))).is(Fluids.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(-x, -y, z), block, 3);
                        }
                        if (worldgenlevel.getFluidState((blockpos1.offset(x, -y, -z))).is(Fluids.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(x, -y, -z), block, 3);
                        }
                        if (worldgenlevel.getFluidState((blockpos1.offset(-x, y, -z))).is(Fluids.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(-x, y, -z), block, 3);
                        }
                        if (worldgenlevel.getFluidState((blockpos1.offset(-x, -y, -z))).is(Fluids.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(-x, -y, -z), block, 3);
                        }







                    }
                }
            }
        }
        return true;
    }
    public static double lengthSq(int x, int y, int z){
        return Math.pow(x,2) +  Math.pow(y,2) + Math.pow(z,2);
    }
}
