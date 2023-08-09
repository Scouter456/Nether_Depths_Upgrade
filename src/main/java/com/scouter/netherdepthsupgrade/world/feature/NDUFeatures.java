package com.scouter.netherdepthsupgrade.world.feature;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.world.level.levelgen.feature.Feature;

import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDUFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, NetherDepthsUpgrade.MODID);

    private static final String WARPED_KELP_FEATURE_NAME = "warped_kelp_feature";
    private static final String WARPED_SEAGRASS_FEATURE_NAME = "warped_seagrass_feature";
    private static final String CRIMSON_KELP_FEATURE_NAME = "crimson_kelp_feature";
    private static final String CRIMSON_SEAGRASS_FEATURE_NAME = "crimson_seagrass_feature";
    private static final String VENT_FEATURE_NAME = "vent_feature";
    private static final String LAVA_SPONGE_FEATURE_NAME = "lava_sponge_feature";
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> WARPED_KELP = FEATURES.register(WARPED_KELP_FEATURE_NAME, () -> new WarpedKelpFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> WARPED_SEAGRASS = FEATURES.register(WARPED_SEAGRASS_FEATURE_NAME, () -> new WarpedSeagrassFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRIMSON_KELP = FEATURES.register(CRIMSON_KELP_FEATURE_NAME, () -> new CrimsonKelpFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> CRIMSON_SEAGRASS = FEATURES.register(CRIMSON_SEAGRASS_FEATURE_NAME, () -> new CrimsonSeagrassFeature(ProbabilityFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> VENT = FEATURES.register(VENT_FEATURE_NAME, () -> new VentFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LAVA_SPONGE = FEATURES.register(LAVA_SPONGE_FEATURE_NAME, () -> new SpongeFeature(NoneFeatureConfiguration.CODEC));

}
