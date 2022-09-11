package com.scouter.netherdepthsupgrade.world.feature;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NDUFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, NetherDepthsUpgrade.MODID);

    private static final String WARPED_KELP_FEATURE_NAME = "warped_kelp_feature";
    private static final String WARPED_SEAGRASS_FEATURE_NAME = "warped_seagrass_feature";
    private static final String VENT_FEATURE_NAME = "vent_feature";
    private static final String LAVA_SPONGE_FEATURE_NAME = "lava_sponge_feature";
    public static final RegistryObject<WarpedKelpFeature> WARPED_KELP = FEATURES.register(WARPED_KELP_FEATURE_NAME, () -> new WarpedKelpFeature(NoFeatureConfig.CODEC));

    public static final RegistryObject<WarpedSeagrassFeature> WARPED_SEAGRASS = FEATURES.register(WARPED_SEAGRASS_FEATURE_NAME, () -> new WarpedSeagrassFeature(ProbabilityConfig.CODEC));
    public static final RegistryObject<VentFeature> VENT = FEATURES.register(VENT_FEATURE_NAME, () -> new VentFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<SpongeFeature> LAVA_SPONGE = FEATURES.register(LAVA_SPONGE_FEATURE_NAME, () -> new SpongeFeature(NoFeatureConfig.CODEC));

}
