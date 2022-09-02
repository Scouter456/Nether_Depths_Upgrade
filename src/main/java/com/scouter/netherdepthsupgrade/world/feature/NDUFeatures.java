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

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> WARPED_KELP = FEATURES.register(WARPED_KELP_FEATURE_NAME, () -> new WarpedKelpFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<ProbabilityFeatureConfiguration>> WARPED_SEAGRASS = FEATURES.register("seagrass", () -> new WarpedSeagrassFeature(ProbabilityFeatureConfiguration.CODEC));
}
