package com.scouter.netherdepthsupgrade.world.feature;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.blocks.WarpedKelpBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.slf4j.Logger;

public class CrimsonKelpFeature extends Feature<NoneFeatureConfiguration> {
    private static final Logger LOGGER = LogUtils.getLogger();

    public CrimsonKelpFeature(Codec<NoneFeatureConfiguration> p_66219_) {
        super(p_66219_);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159956_) {

        int i = 0;
        WorldGenLevel worldgenlevel = p_159956_.level();
        BlockPos blockpos = p_159956_.origin();
        RandomSource random = p_159956_.random();
        //LOGGER.info("placing" + worldgenlevel);
        int j = 30 + random.nextInt(-29,10);
        //LOGGER.info("j" + j);
        BlockPos blockpos1 = new BlockPos(blockpos.getX(), j, blockpos.getZ());
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA)) {
            BlockState blockstate = NDUBlocks.CRIMSON_KELP.get().defaultBlockState();
            BlockState blockstate1 = NDUBlocks.CRIMSON_KELP_PLANT.get().defaultBlockState();
            int k = 1 + random.nextInt(10);

            for(int l = 0; l <= k; ++l) {
                if (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA) && worldgenlevel.getBlockState(blockpos1.above()).is(Blocks.LAVA) && blockstate1.canSurvive(worldgenlevel, blockpos1)) {
                    if (l == k) {
                        worldgenlevel.setBlock(blockpos1, blockstate.setValue(WarpedKelpBlock.AGE, Integer.valueOf(random.nextInt(4) + 20)), 2);
                        ++i;
                    } else {
                        worldgenlevel.setBlock(blockpos1, blockstate1, 2);
                    }
                } else if (l > 0) {
                    BlockPos blockpos2 = blockpos1.below();
                    if (blockstate.canSurvive(worldgenlevel, blockpos2) && !worldgenlevel.getBlockState(blockpos2.below()).is(NDUBlocks.CRIMSON_KELP.get())) {
                        worldgenlevel.setBlock(blockpos2, blockstate.setValue(WarpedKelpBlock.AGE, Integer.valueOf(random.nextInt(4) + 20)), 2);
                        ++i;
                    }
                    break;
                }

                blockpos1 = blockpos1.above();
            }
        }

        return i > 0;
    }
}
