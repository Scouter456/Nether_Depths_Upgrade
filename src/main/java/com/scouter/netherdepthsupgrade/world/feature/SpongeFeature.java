package com.scouter.netherdepthsupgrade.world.feature;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
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
import net.minecraft.world.level.material.Fluids;
import org.slf4j.Logger;


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
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Search downward from the Nether lava-sea level.
        BlockPos.MutableBlockPos searchPos = new BlockPos.MutableBlockPos(origin.getX(), 31, origin.getZ());

        while (searchPos.getY() > level.getMinBuildHeight()
                && !level.getFluidState(searchPos).is(FluidTags.LAVA)) {
            searchPos.move(Direction.DOWN);
        }

        // This column contains no lava.
        if (!level.getFluidState(searchPos).is(FluidTags.LAVA)) {
            return false;
        }

        // Move through the lava until reaching the floor underneath it.
        while (searchPos.getY() > level.getMinBuildHeight()
                && level.getFluidState(searchPos).is(FluidTags.LAVA)) {
            searchPos.move(Direction.DOWN);
        }

        BlockPos floorPos = searchPos.immutable();
        BlockState spongeState = NDUBlocks.WET_LAVA_SPONGE.get().defaultBlockState();

        double radius = random.nextInt(3, 6) + 0.5D;
        double radiusSq = radius * radius;
        int ceilRadius = (int) Math.ceil(radius);
        int placedBlocks = 0;

        for (int x = -ceilRadius; x <= ceilRadius; x++) {
            for (int y = -ceilRadius; y <= ceilRadius; y++) {
                for (int z = -ceilRadius; z <= ceilRadius; z++) {
                    int distanceSq = x * x + y * y + z * z;

                    if (distanceSq > radiusSq) {
                        continue;
                    }

                    BlockPos targetPos = floorPos.offset(x, y, z);

                    if (level.getFluidState(targetPos).is(FluidTags.LAVA)) {
                        if (level.setBlock(targetPos, spongeState, 2)) {
                            placedBlocks++;
                        }
                    }
                }
            }
        }

        return placedBlocks > 0;
    }
}
