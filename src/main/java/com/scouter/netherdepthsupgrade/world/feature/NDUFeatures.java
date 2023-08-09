package com.scouter.netherdepthsupgrade.world.feature;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NDUFeatures {

    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    private static final String WARPED_KELP_FEATURE_NAME = "warped_kelp_feature";
    private static final String WARPED_SEAGRASS_FEATURE_NAME = "warped_seagrass_feature";
    private static final String CRIMSON_KELP_FEATURE_NAME = "crimson_kelp_feature";
    private static final String CRIMSON_SEAGRASS_FEATURE_NAME = "crimson_seagrass_feature";
    private static final String VENT_FEATURE_NAME = "vent_feature";
    private static final String LAVA_SPONGE_FEATURE_NAME = "lava_sponge_feature";
    public static final Feature<NoneFeatureConfiguration> WARPED_KELP = register(WARPED_KELP_FEATURE_NAME, new WarpedKelpFeature(NoneFeatureConfiguration.CODEC));

    public static final Feature<ProbabilityFeatureConfiguration> WARPED_SEAGRASS = register(WARPED_SEAGRASS_FEATURE_NAME,  new WarpedSeagrassFeature(ProbabilityFeatureConfiguration.CODEC));

    public static final Feature<NoneFeatureConfiguration> CRIMSON_KELP = register(CRIMSON_KELP_FEATURE_NAME, new CrimsonKelpFeature(NoneFeatureConfiguration.CODEC));

    public static final Feature<ProbabilityFeatureConfiguration> CRIMSON_SEAGRASS = register(CRIMSON_SEAGRASS_FEATURE_NAME,  new CrimsonSeagrassFeature(ProbabilityFeatureConfiguration.CODEC));

    public static final Feature<NoneFeatureConfiguration> VENT = register(VENT_FEATURE_NAME,  new VentFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> LAVA_SPONGE = register(LAVA_SPONGE_FEATURE_NAME,new SpongeFeature(NoneFeatureConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String key, F value) {
        return Registry.register(Registry.FEATURE, key, value);
    }

    public static void FEATURES(){
        LOGGER.info("Registering Features for " + NetherDepthsUpgrade.MODID);
    }
}
