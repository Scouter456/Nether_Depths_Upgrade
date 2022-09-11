package com.scouter.netherdepthsupgrade.world.feature;

import com.mojang.serialization.Codec;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
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


public class SpongeFeature extends Feature<NoFeatureConfig> {
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public SpongeFeature(Codec<NoFeatureConfig> p_66219_) {
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
        for(int l = -29; l < 1;l++){
            list.add(l);
        }
        ISeedReader worldgenlevel = reader;
        BlockPos blockpos = pos;
        Random random = reader.getRandom();
        int t = 30 + list.get(rand.nextInt(30));
        BlockPos blockpos1 = new BlockPos(blockpos.getX(), t, blockpos.getZ());
        BlockState block = NDUBlocks.WET_LAVA_SPONGE.get().defaultBlockState();

        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA)) {
            while (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA)) {
                blockpos1 = blockpos1.below();
            }
            int radius = random.nextInt( 6) + 3;
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
                        if (worldgenlevel.getBlockState((blockpos1.offset(x, y, z))).is(Blocks.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(x, y, z), block, 3);
                        }
                        if (worldgenlevel.getBlockState((blockpos1.offset(-x, y, z))).is(Blocks.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(-x, y, z), block, 3);
                        }
                        if (worldgenlevel.getBlockState((blockpos1.offset(x, -y, z))).is(Blocks.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(x, -y, z), block, 3);
                        }
                        if (worldgenlevel.getBlockState((blockpos1.offset(x, y, -z))).is(Blocks.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(x, y, -z), block, 3);
                        }
                        if (worldgenlevel.getBlockState((blockpos1.offset(-x, -y, z))).is(Blocks.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(-x, -y, z), block, 3);
                        }
                        if (worldgenlevel.getBlockState((blockpos1.offset(x, -y, -z))).is(Blocks.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(x, -y, -z), block, 3);
                        }
                        if (worldgenlevel.getBlockState((blockpos1.offset(-x, y, -z))).is(Blocks.LAVA)) {
                            worldgenlevel.setBlock(blockpos1.offset(-x, y, -z), block, 3);
                        }
                        if (worldgenlevel.getBlockState((blockpos1.offset(-x, -y, -z))).is(Blocks.LAVA)) {
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
