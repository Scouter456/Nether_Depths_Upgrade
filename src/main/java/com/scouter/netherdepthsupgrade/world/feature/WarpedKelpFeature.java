package com.scouter.netherdepthsupgrade.world.feature;

import com.mojang.serialization.Codec;
import com.scouter.netherdepthsupgrade.blocks.LavaKelpBlock;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class WarpedKelpFeature extends Feature<NoFeatureConfig> {
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public WarpedKelpFeature(Codec<NoFeatureConfig> p_66219_) {
        super(p_66219_);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        List<Integer> list = new ArrayList<>();
        list.clear();
        for(int l = -29; l < 11;l++){
            list.add(l);
        }
        int i = 0;
        ISeedReader worldgenlevel = reader;
        BlockPos blockpos = pos;
        Random random = reader.getRandom();
        //LOGGER.info("placing" + worldgenlevel);
        int j = 30 + list.get(rand.nextInt(40));
        //LOGGER.info("j" + j);
        BlockPos blockpos1 = new BlockPos(blockpos.getX(), j, blockpos.getZ());
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA)) {
            BlockState blockstate = NDUBlocks.WARPED_KELP.get().defaultBlockState();
            BlockState blockstate1 = NDUBlocks.WARPED_KELP_PLANT.get().defaultBlockState();
            int k = 1 + random.nextInt(10);

            for(int l = 0; l <= k; ++l) {
                if (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA) && worldgenlevel.getBlockState(blockpos1.above()).is(Blocks.LAVA) && blockstate1.canSurvive(worldgenlevel, blockpos1)) {
                    if (l == k) {
                        worldgenlevel.setBlock(blockpos1, blockstate.setValue(LavaKelpBlock.AGE, Integer.valueOf(random.nextInt(4) + 20)), 2);
                        ++i;
                    } else {
                        worldgenlevel.setBlock(blockpos1, blockstate1, 2);
                    }
                } else if (l > 0) {
                    BlockPos blockpos2 = blockpos1.below();
                    if (blockstate.canSurvive(worldgenlevel, blockpos2) && !worldgenlevel.getBlockState(blockpos2.below()).is(NDUBlocks.WARPED_KELP.get())) {
                        worldgenlevel.setBlock(blockpos2, blockstate.setValue(LavaKelpBlock.AGE, Integer.valueOf(random.nextInt(4) + 20)), 2);
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
