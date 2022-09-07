package com.scouter.netherdepthsupgrade.world.feature;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.scouter.netherdepthsupgrade.blocks.LavaKelpBlock;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.slf4j.Logger;

import java.util.Random;


public class VentFeature extends Feature<NoneFeatureConfiguration> {
    private static final Logger LOGGER = LogUtils.getLogger();

    public VentFeature(Codec<NoneFeatureConfiguration> p_66219_) {
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
        RandomSource random = p_159956_.random();
        int z = 30 + random.nextInt(-29, 0);
        BlockPos blockpos1 = new BlockPos(blockpos.getX(), z, blockpos.getZ());
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.LAVA)) {
            while(worldgenlevel.getFluidState(blockpos1).is(Fluids.LAVA)){
                blockpos1 = blockpos1.below();
            }
            BlockState block = Blocks.NETHERRACK.defaultBlockState();
            BlockState block2 = Blocks.ANCIENT_DEBRIS.defaultBlockState();
            BlockState block3 = Blocks.NETHER_GOLD_ORE.defaultBlockState();
            BlockState block4 = Blocks.NETHER_QUARTZ_ORE.defaultBlockState();
            FluidState block5 = Fluids.LAVA.defaultFluidState();
            BlockState block6 = Blocks.AIR.defaultBlockState();
            int rRand = random.nextInt(3, 7);
            int heightMax = random.nextInt(5, 15);
            for (int y = 0; y < heightMax; y++) {
                for (int l = 0; l < (rRand); l++) {
                    for (int p = 0; p < (rRand); p++) {
                        for (double a = 0; a < (Math.PI * 2); a = a + 0.05) {
                            double x = (l) * Math.cos(a);
                            double k = (p) * Math.sin(a);
                            int randomNum = random.nextInt(0, 100);
                            if (randomNum < 80) {
                                worldgenlevel.setBlock(blockpos1.offset(Math.round(x), y, Math.round(k)), block, 3);

                            } else if (randomNum > 80 && randomNum < 90) {
                                worldgenlevel.setBlock(blockpos1.offset(Math.round(x), y, Math.round(k)), block4, 3);
                            } else if (randomNum > 90 && randomNum < 98) {
                                worldgenlevel.setBlock(blockpos1.offset(Math.round(x), y, Math.round(k)), block3, 3);
                            } else if (randomNum > 98 && randomNum < 100) {
                                worldgenlevel.setBlock(blockpos1.offset(Math.round(x), y, Math.round(k)), block2, 3);
                            }
                        }
                    }
                }
                if (y % 2 == 0) {
                    rRand--;
                }
            }
            for (int y = 0; y < heightMax; y++) {
                if (checkSurrounding(worldgenlevel, blockpos1.offset(0, y, 0))) {
                    worldgenlevel.setBlock(blockpos1.offset(0, y, 0), block5.createLegacyBlock(), 3);
                }if(blockpos1.offset(0,y,0).getY() > 29 && !checkSurrounding(worldgenlevel, blockpos1.offset(0, y, 0))){
                    worldgenlevel.setBlock(blockpos1.offset(0, y, 0), block6, 3);
                }
            }


        }


        return true;
    }

    public boolean checkSurrounding(WorldGenLevel level, BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState blockstate1 = level.getBlockState(pos.relative(direction));
            FluidState fluidstate = level.getFluidState(pos.relative(direction));
            if (fluidstate.is(FluidTags.LAVA) || !blockstate1.is(Blocks.AIR)) {
                return true;
            }
        }
        return false;
    }
}
