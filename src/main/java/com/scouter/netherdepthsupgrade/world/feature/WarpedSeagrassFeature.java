package com.scouter.netherdepthsupgrade.world.feature;

import com.mojang.serialization.Codec;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.blocks.TallLavaSeagrassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class WarpedSeagrassFeature extends Feature<ProbabilityConfig> {
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public WarpedSeagrassFeature(Codec<ProbabilityConfig> p_66768_) {
        super(p_66768_);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ProbabilityConfig config) {
        List<Integer> list = new ArrayList<>();
        list.clear();
        for(int l = -29; l < 11;l++){
            list.add(l);
        }
        boolean flag = false;
        ISeedReader worldgenlevel = reader;
        Random random = reader.getRandom();
        BlockPos blockpos = pos;
        ProbabilityConfig probabilityfeatureconfiguration = config;
        int i = random.nextInt(8) - random.nextInt(8);
        int j = random.nextInt(8) - random.nextInt(8);
        int k = 30 + list.get(rand.nextInt(40));
        BlockPos blockpos1 = new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j);
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA)) {
            boolean flag1 = random.nextDouble() < (double)probabilityfeatureconfiguration.probability;
            BlockState blockstate = flag1 ? NDUBlocks.TALL_WARPED_SEAGRASS.get().defaultBlockState() : NDUBlocks.WARPED_SEAGRASS.get().defaultBlockState();
            if (blockstate.canSurvive(worldgenlevel, blockpos1)) {
                if (flag1) {
                    BlockState blockstate1 = blockstate.setValue(TallLavaSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
                    BlockPos blockpos2 = blockpos1.above();
                    if (worldgenlevel.getBlockState(blockpos2).is(Blocks.LAVA)) {
                        worldgenlevel.setBlock(blockpos1, blockstate, 2);
                        worldgenlevel.setBlock(blockpos2, blockstate1, 2);
                    }
                } else {
                    worldgenlevel.setBlock(blockpos1, blockstate, 2);
                }

                flag = true;
            }
        }

        return flag;
    }
}