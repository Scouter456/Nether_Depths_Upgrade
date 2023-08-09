package com.scouter.netherdepthsupgrade.world.feature;

import com.google.common.collect.ImmutableList;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
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

    private static final String WARPED_SEAGRASS_SIMPLE_FEATURE_NAME = "warped_seagrass_simple_feature";
    private static final String WARPED_KELP_FEATURE_NAME = "warped_kelp_feature";
    private static final String WARPED_SEAGRASS_SHORT_FEATURE_NAME = "warped_seagrass_short_feature";
    private static final String WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME = "warped_seagrass_slightly_less_short_feature";
    private static final String WARPED_SEAGRASS_MID_FEATURE_NAME = "warped_seagrass_mid_feature";
    private static final String WARPED_SEAGRASS_TALL_FEATURE_NAME = "warped_seagrass_tall_feature";


    private static final String CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME = "crimson_seagrass_simple_feature";
    private static final String CRIMSON_KELP_FEATURE_NAME = "crimson_kelp_feature";
    private static final String CRIMSON_SEAGRASS_SHORT_FEATURE_NAME = "crimson_seagrass_short_feature";
    private static final String CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME = "crimson_seagrass_slightly_less_short_feature";
    private static final String CRIMSON_SEAGRASS_MID_FEATURE_NAME = "crimson_seagrass_mid_feature";
    private static final String CRIMSON_SEAGRASS_TALL_FEATURE_NAME = "crimson_seagrass_tall_feature";

    private static final String VENT_FEATURE_NAME = "vent_feature";
    private static final String LAVA_SPONGE_FEATURE_NAME = "lava_sponge_feature";
    private static final String WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED = "warped_seagrass_simple_feature_placed";
    private static final String WARPED_KELP_FEATURE_NAME_PLACED = "warped_kelp_feature_placed";
    private static final String WARPED_KELP_FEATURE_COMMON_NAME_PLACED = "warped_kelp_feature_common_placed";

    private static final String WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED = "warped_seagrass_short_feature_placed";
    private static final String WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED = "warped_seagrass_slightly_less_short_feature_placed";
    private static final String WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED = "warped_seagrass_mid_feature_placed";
    private static final String WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED = "warped_seagrass_tall_feature_placed";
    private static final String WARPED_SEAGRASS_COMMON_TALL_FEATURE_NAME_PLACED = "warped_seagrass_common_tall_feature_placed";


    private static final String CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED = "crimson_seagrass_simple_feature_placed";
    private static final String CRIMSON_KELP_FEATURE_NAME_PLACED = "crimson_kelp_feature_placed";
    private static final String CRIMSON_KELP_FEATURE_COMMON_NAME_PLACED = "crimson_kelp_feature_common_placed";
    private static final String CRIMSON_SEAGRASS_SHORT_FEATURE_NAME_PLACED = "crimson_seagrass_short_feature_placed";
    private static final String CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED = "crimson_seagrass_slightly_less_short_feature_placed";
    private static final String CRIMSON_SEAGRASS_MID_FEATURE_NAME_PLACED = "crimson_seagrass_mid_feature_placed";
    private static final String CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED = "crimson_seagrass_tall_feature_placed";
    private static final String CRIMSON_SEAGRASS_COMMON_TALL_FEATURE_NAME_PLACED = "crimson_seagrass_common_tall_feature_placed";

    private static final String VENT_FEATURE_NAME_PLACED = "vent_feature_placed";
    private static final String LAVA_SPONGE_FEATURE_NAME_PLACED = "lava_sponge_feature_placed";
    private static Random rand = new Random();

    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_SIMPLE = FeatureUtils.register(WARPED_SEAGRASS_SIMPLE_FEATURE_NAME, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(NDUBlocks.WARPED_SEAGRASS)));

    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_WARPED_KELP = FeatureUtils.register(WARPED_KELP_FEATURE_NAME, NDUFeatures.WARPED_KELP, new NoneFeatureConfiguration());

    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_SHORT = FeatureUtils.register(WARPED_SEAGRASS_SHORT_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS, new ProbabilityFeatureConfiguration(0.3F));

    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT = FeatureUtils.register(WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS, new ProbabilityFeatureConfiguration(0.4F));
    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_MID= FeatureUtils.register(WARPED_SEAGRASS_MID_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS, new ProbabilityFeatureConfiguration(0.6F));
    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_WARPED_SEAGRASS_TALL = FeatureUtils.register(WARPED_SEAGRASS_TALL_FEATURE_NAME, NDUFeatures.WARPED_SEAGRASS, new ProbabilityFeatureConfiguration(0.8F));

//CRIMSON
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> CONFIGURED_CRIMSON_SEAGRASS_SIMPLE = FeatureUtils.register(CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(NDUBlocks.CRIMSON_SEAGRASS)));

    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_CRIMSON_KELP = FeatureUtils.register(CRIMSON_KELP_FEATURE_NAME, NDUFeatures.CRIMSON_KELP, new NoneFeatureConfiguration());

    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_CRIMSON_SEAGRASS_SHORT = FeatureUtils.register(CRIMSON_SEAGRASS_SHORT_FEATURE_NAME, NDUFeatures.CRIMSON_SEAGRASS, new ProbabilityFeatureConfiguration(0.3F));

    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT = FeatureUtils.register(CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME, NDUFeatures.CRIMSON_SEAGRASS, new ProbabilityFeatureConfiguration(0.4F));
    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_CRIMSON_SEAGRASS_MID= FeatureUtils.register(CRIMSON_SEAGRASS_MID_FEATURE_NAME, NDUFeatures.CRIMSON_SEAGRASS, new ProbabilityFeatureConfiguration(0.6F));
    public static final Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> CONFIGURED_CRIMSON_SEAGRASS_TALL = FeatureUtils.register(CRIMSON_SEAGRASS_TALL_FEATURE_NAME, NDUFeatures.CRIMSON_SEAGRASS, new ProbabilityFeatureConfiguration(0.8F));

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
            NoiseBasedCountPlacement.of(80, 80.0D, 0.0D),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> WARPED_KELP_PLACED_COMMON = PlacementUtils.register(WARPED_KELP_FEATURE_COMMON_NAME_PLACED,  CONFIGURED_WARPED_KELP, ImmutableList.of(
            NoiseBasedCountPlacement.of(256, 80.0D, 0.0D),
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
            CountPlacement.of(60),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> WARPED_SEAGRASS_TALL_PLACED_COMMON = PlacementUtils.register(WARPED_SEAGRASS_COMMON_TALL_FEATURE_NAME_PLACED,  CONFIGURED_WARPED_SEAGRASS_TALL, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(256),
            BiomeFilter.biome()));

    //CRIMSON
    public static final Holder<PlacedFeature> CRIMSON_SEAGRASS_SIMPLE_PLACED = PlacementUtils.register(CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED,  CONFIGURED_CRIMSON_SEAGRASS_SIMPLE, ImmutableList.of(
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

    public static final Holder<PlacedFeature> CRIMSON_KELP_PLACED = PlacementUtils.register(CRIMSON_KELP_FEATURE_NAME_PLACED,  CONFIGURED_CRIMSON_KELP, ImmutableList.of(
            NoiseBasedCountPlacement.of(80, 80.0D, 0.0D),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> CRIMSON_KELP_PLACED_COMMON = PlacementUtils.register(CRIMSON_KELP_FEATURE_COMMON_NAME_PLACED,  CONFIGURED_CRIMSON_KELP, ImmutableList.of(
            NoiseBasedCountPlacement.of(256, 80.0D, 0.0D),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome()));
    public static final Holder<PlacedFeature> CRIMSON_SEAGRASS_SHORT_PLACED = PlacementUtils.register(CRIMSON_SEAGRASS_SHORT_FEATURE_NAME_PLACED,  CONFIGURED_CRIMSON_SEAGRASS_SHORT, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(80),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_PLACED = PlacementUtils.register(CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED,  CONFIGURED_CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(48),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> CRIMSON_SEAGRASS_MID_PLACED = PlacementUtils.register(CRIMSON_SEAGRASS_MID_FEATURE_NAME_PLACED,  CONFIGURED_CRIMSON_SEAGRASS_MID, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(64),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> CRIMSON_SEAGRASS_TALL_PLACED = PlacementUtils.register(CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED,  CONFIGURED_CRIMSON_SEAGRASS_TALL, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(60),
            BiomeFilter.biome()));

    public static final Holder<PlacedFeature> CRIMSON_SEAGRASS_TALL_PLACED_COMMON = PlacementUtils.register(CRIMSON_SEAGRASS_COMMON_TALL_FEATURE_NAME_PLACED,  CONFIGURED_CRIMSON_SEAGRASS_TALL, ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(256),
            BiomeFilter.biome()));


    public static final Holder<PlacedFeature> VENT_PLACED = PlacementUtils.register(VENT_FEATURE_NAME_PLACED,  CONFIGURED_VENT, ImmutableList.of(
            RarityFilter.onAverageOnceEvery(25),
            //InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            //CountPlacement.of(80),
            BiomeFilter.biome()));
    public static final Holder<PlacedFeature> LAVA_SPONGE_PLACED = PlacementUtils.register(LAVA_SPONGE_FEATURE_NAME_PLACED,  CONFIGURED_LAVA_SPONGE, ImmutableList.of(
            RarityFilter.onAverageOnceEvery(15),
            //InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            //CountPlacement.of(80),
            BiomeFilter.biome()));

    public static void CONFIGURED_FEATURES(){
        LOGGER.info("Registering ConfiguredFeatures for " + NetherDepthsUpgrade.MODID);
        LOGGER.info("Registering PlacedFeatures for " + NetherDepthsUpgrade.MODID);
    }

}
