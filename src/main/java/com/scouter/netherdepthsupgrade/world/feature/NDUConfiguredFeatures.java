package com.scouter.netherdepthsupgrade.world.feature;

import com.google.common.collect.ImmutableList;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class NDUConfiguredFeatures {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");

    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_SIMPLE_FEATURE_NAME = registerKey("warped_seagrass_simple_feature");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_KELP_FEATURE_NAME = registerKey("warped_kelp_feature");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_SHORT_FEATURE_NAME = registerKey("warped_seagrass_short_feature");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME = registerKey("warped_seagrass_slightly_less_short_feature");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_MID_FEATURE_NAME = registerKey("warped_seagrass_mid_feature");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_TALL_FEATURE_NAME = registerKey("warped_seagrass_tall_feature");
    private static final ResourceKey<ConfiguredFeature<?, ?>> VENT_FEATURE_NAME = registerKey("vent_feature");
    private static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_SPONGE_FEATURE_NAME = registerKey("lava_sponge_feature");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED = registerKey("warped_seagrass_simple_feature_placed");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_KELP_FEATURE_NAME_PLACED = registerKey("warped_kelp_feature_placed");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED = registerKey("warped_seagrass_short_feature_placed");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED = registerKey("warped_seagrass_slightly_less_short_feature_placed");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED = registerKey("warped_seagrass_mid_feature_placed");
    private static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED = registerKey("warped_seagrass_tall_feature_placed");
    private static final ResourceKey<ConfiguredFeature<?, ?>> VENT_FEATURE_NAME_PLACED = registerKey("vent_feature_placed");
    private static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_SPONGE_FEATURE_NAME_PLACED = registerKey("lava_sponge_feature_placed");
    private static Random rand = new Random();
    
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NetherDepthsUpgrade.MODID, name));
    }

    /*
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_SIMPLE = FeatureUtils.register(WARPED_SEAGRASS_SIMPLE_FEATURE_NAME, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(NDUBlocks.WARPED_SEAGRASS)));

    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_WARPED_KELP = FeatureUtils.register(WARPED_KELP_FEATURE_NAME, NDUFeatures.WARPED_KELP, new NoneFeatureConfiguration());

    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_SHORT = FeatureUtils.register(WARPED_SEAGRASS_SHORT_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS, new ProbabilityFeatureConfiguration(0.3F));

    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT = FeatureUtils.register(WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS, new ProbabilityFeatureConfiguration(0.4F));
    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_MID= FeatureUtils.register(WARPED_SEAGRASS_MID_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS, new ProbabilityFeatureConfiguration(0.6F));
    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_TALL = FeatureUtils.register(WARPED_SEAGRASS_TALL_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS, new ProbabilityFeatureConfiguration(0.8F));
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_VENT = FeatureUtils.register(VENT_FEATURE_NAME, NDUFeatures.VENT, new NoneFeatureConfiguration());
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_LAVA_SPONGE= FeatureUtils.register(LAVA_SPONGE_FEATURE_NAME, NDUFeatures.LAVA_SPONGE, new NoneFeatureConfiguration());

    public static final Holder<PlacedFeature> WARPED_SEAGRASS_SIMPLE_PLACED = PlacementUtils.register(WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED,  CONFIGURED_WARPED_SEAGRASS_SIMPLE, ImmutableList.of(
            CarvingMaskPlacement.forStep(GenerationStep.Carving.LIQUID),
            RarityFilter.onAverageOnceEvery(10),
            BlockPredicateFilter
                    .forPredicate(
                            BlockPredicate.allOf(
                                    BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(),
                                            Blocks.NETHERRACK),
                                    BlockPredicate.matchesBlocks(BlockPos.ZERO,
                                            Blocks.LAVA),
                                    BlockPredicate.matchesBlocks(Direction.UP.getNormal(),
                                            Blocks.LAVA))),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> WARPED_KELP_PLACED = PlacementUtils.register(WARPED_KELP_FEATURE_NAME_PLACED,  CONFIGURED_WARPED_KELP, ImmutableList.of(
            NoiseBasedCountPlacement.of(120, 80.0D, 0.0D),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> WARPED_SEAGRASS_SHORT_PLACED = PlacementUtils.register(WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED,  CONFIGURED_WARPED_SEAGRASS_SHORT, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(80),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_PLACED = PlacementUtils.register(WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED,  CONFIGURED_WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(48),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> WARPED_SEAGRASS_MID_PLACED = PlacementUtils.register(WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED,  CONFIGURED_WARPED_SEAGRASS_MID, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(64),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> WARPED_SEAGRASS_TALL_PLACED = PlacementUtils.register(WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED,  CONFIGURED_WARPED_SEAGRASS_TALL, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(80),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> VENT_PLACED = PlacementUtils.register(VENT_FEATURE_NAME_PLACED,  CONFIGURED_VENT, ImmutableList.of(
            RarityFilter.onAverageOnceEvery(25),
            //InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            //CountPlacement.of(80),
            BiomeFilter.biome()));
    public static final Holder<PlacedFeature> LAVA_SPONGE_PLACED = PlacementUtils.register(LAVA_SPONGE_FEATURE_NAME_PLACED,  CONFIGURED_LAVA_SPONGE, ImmutableList.of(
            RarityFilter.onAverageOnceEvery(40),
            //InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            //CountPlacement.of(80),
            BiomeFilter.biome()));
*/
    public static void CONFIGURED_FEATURES(){
        LOGGER.info("Registering ConfiguredFeatures for " + NetherDepthsUpgrade.MODID);
        LOGGER.info("Registering PlacedFeatures for " + NetherDepthsUpgrade.MODID);
    }

}
