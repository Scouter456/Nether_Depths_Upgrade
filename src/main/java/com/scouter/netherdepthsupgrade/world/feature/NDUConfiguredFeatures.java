package com.scouter.netherdepthsupgrade.world.feature;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;

import java.util.Random;

public class NDUConfiguredFeatures {
    private static final String WARPED_SEAGRASS_SIMPLE_FEATURE_NAME = "warped_seagrass_simple_feature";
    private static final String WARPED_KELP_FEATURE_NAME = "warped_kelp_feature";
    private static final String WARPED_SEAGRASS_SHORT_FEATURE_NAME = "warped_seagrass_short_feature";
    private static final String WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME = "warped_seagrass_slightly_less_short_feature";
    private static final String WARPED_SEAGRASS_MID_FEATURE_NAME = "warped_seagrass_mid_feature";
    private static final String WARPED_SEAGRASS_TALL_FEATURE_NAME = "warped_seagrass_tall_feature";
    private static final String VENT_FEATURE_NAME = "vent_feature";
    private static final String LAVA_SPONGE_FEATURE_NAME = "lava_sponge_feature";
    private static Random rand = new Random();

    public static final ConfiguredFeature<?, ?> CONFIGURED_WARPED_SEAGRASS_SIMPLE = register(WARPED_SEAGRASS_SIMPLE_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS.get()
            .configured(new ProbabilityConfig(0.8F))
            .count(40)
            .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));

    public static final ConfiguredFeature<?, ?> CONFIGURED_WARPED_KELP = register(WARPED_KELP_FEATURE_NAME, NDUFeatures.WARPED_KELP.get()
            .configured(IFeatureConfig.NONE)
            .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP)
            .squared().decorated(Placement.COUNT_NOISE_BIASED
                    .configured(new TopSolidWithNoiseConfig(80, 80.0D, 0.0D))));

    public static final ConfiguredFeature<?, ?> CONFIGURED_WARPED_SEAGRASS_SHORT = register(WARPED_SEAGRASS_SHORT_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS.get()
            .configured(new ProbabilityConfig(0.3F))
            .count(32)
            .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));

    public static final ConfiguredFeature<?, ?> CONFIGURED_WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT = register(WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS.get()
            .configured(new ProbabilityConfig(0.4F))
            .count(48)
            .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
    public static final ConfiguredFeature<?, ?> CONFIGURED_WARPED_SEAGRASS_MID= register(WARPED_SEAGRASS_MID_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS.get()
            .configured(new ProbabilityConfig(0.6F))
            .count(64)
            .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
    public static final ConfiguredFeature<?, ?> CONFIGURED_WARPED_SEAGRASS_TALL = register(WARPED_SEAGRASS_TALL_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS.get()
            .configured(new ProbabilityConfig(0.8F))
            .count(48)
            .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
    public static final ConfiguredFeature<?, ?> CONFIGURED_VENT = register(VENT_FEATURE_NAME, NDUFeatures.VENT.get()
            .configured(IFeatureConfig.NONE)
            .range(128)
            .squared()
            .chance(5));
    public static final ConfiguredFeature<?, ?> CONFIGURED_LAVA_SPONGE= register(LAVA_SPONGE_FEATURE_NAME, NDUFeatures.LAVA_SPONGE.get()
            .configured(IFeatureConfig.NONE)
            .range(128)
            .chance(30));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String pId, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, pId, pConfiguredFeature);
    }
}
