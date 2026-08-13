package com.scouter.netherdepthsupgrade.world.feature;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.slf4j.Logger;


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
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Find lava in the selected column.
        BlockPos.MutableBlockPos searchPos = new BlockPos.MutableBlockPos(origin.getX(), 31, origin.getZ());

        while (searchPos.getY() > level.getMinBuildHeight()
                && !level.getFluidState(searchPos).is(FluidTags.LAVA)) {
            searchPos.move(Direction.DOWN);
        }

        if (!level.getFluidState(searchPos).is(FluidTags.LAVA)) {
            return false;
        }

        // Find the floor underneath the lava.
        while (searchPos.getY() > level.getMinBuildHeight()
                && level.getFluidState(searchPos).is(FluidTags.LAVA)) {
            searchPos.move(Direction.DOWN);
        }

        BlockPos basePos = searchPos.immutable();

        int startingRadius = random.nextInt(3, 7);
        int maximumHeight = random.nextInt(5, 15);
        int generatedHeight = 0;

        for (int y = 0; y < maximumHeight; y++) {
            int radius = startingRadius - ((y + 1) / 2);

            if (radius <= 0) {
                break;
            }

            int radiusSq = radius * radius;

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + z * z > radiusSq) {
                        continue;
                    }

                    BlockPos targetPos = basePos.offset(x, y, z);
                    level.setBlock(targetPos, getVentBlock(random), 2);
                }
            }

            generatedHeight = y + 1;
        }

        // Create the lava shaft in the centre.
        BlockState lava = Fluids.LAVA.defaultFluidState().createLegacyBlock();

        for (int y = 0; y < generatedHeight; y++) {
            level.setBlock(basePos.offset(0, y, 0), lava, 2);
        }

        return generatedHeight > 0;
    }

    private static BlockState getVentBlock(RandomSource random) {
        int roll = random.nextInt(100);

        if (roll < 81) {
            return Blocks.NETHERRACK.defaultBlockState();
        }

        if (roll < 91) {
            return Blocks.NETHER_QUARTZ_ORE.defaultBlockState();
        }

        if (roll < 99) {
            return Blocks.NETHER_GOLD_ORE.defaultBlockState();
        }

        return Blocks.ANCIENT_DEBRIS.defaultBlockState();
    }
}
