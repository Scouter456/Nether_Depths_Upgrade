package com.scouter.netherdepthsupgrade.world.feature;

import com.google.common.collect.ImmutableList;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public class NDUConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, NetherDepthsUpgrade.MODID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, NetherDepthsUpgrade.MODID);

    private static final String WARPED_SEAGRASS_SIMPLE_FEATURE_NAME = "warped_seagrass_simple_feature";
    private static final String WARPED_KELP_FEATURE_NAME = "warped_kelp_feature";
    private static final String WARPED_SEAGRASS_SHORT_FEATURE_NAME = "warped_seagrass_short_feature";
    private static final String WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME = "warped_seagrass_slightly_less_short_feature";
    private static final String WARPED_SEAGRASS_MID_FEATURE_NAME = "warped_seagrass_mid_feature";
    private static final String WARPED_SEAGRASS_TALL_FEATURE_NAME = "warped_seagrass_tall_feature";
    private static Random rand = new Random();

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_WARPED_SEAGRASS_SIMPLE = CONFIGURED_FEATURES.register(WARPED_SEAGRASS_SIMPLE_FEATURE_NAME, () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SEAGRASS))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_WARPED_KELP = CONFIGURED_FEATURES.register(WARPED_KELP_FEATURE_NAME, () -> new ConfiguredFeature<>(NDUFeatures.WARPED_KELP.get(), new NoneFeatureConfiguration()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_WARPED_SEAGRASS_SHORT = CONFIGURED_FEATURES.register(WARPED_SEAGRASS_SHORT_FEATURE_NAME, () -> new ConfiguredFeature<>(NDUFeatures.WARPED_SEAGRASS.get(), new ProbabilityFeatureConfiguration(0.3F)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT = CONFIGURED_FEATURES.register(WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME, () -> new ConfiguredFeature<>(NDUFeatures.WARPED_SEAGRASS.get(), new ProbabilityFeatureConfiguration(0.4F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_WARPED_SEAGRASS_MID= CONFIGURED_FEATURES.register(WARPED_SEAGRASS_MID_FEATURE_NAME, () -> new ConfiguredFeature<>(NDUFeatures.WARPED_SEAGRASS.get(), new ProbabilityFeatureConfiguration(0.6F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_WARPED_SEAGRASS_TALL = CONFIGURED_FEATURES.register(WARPED_SEAGRASS_TALL_FEATURE_NAME, () -> new ConfiguredFeature<>(NDUFeatures.WARPED_SEAGRASS.get(), new ProbabilityFeatureConfiguration(0.8F)));

    public static final RegistryObject<PlacedFeature> WARPED_SEAGRASS_SIMPLE_PLACED = PLACED_FEATURES.register(WARPED_SEAGRASS_SIMPLE_FEATURE_NAME, () -> new PlacedFeature(CONFIGURED_WARPED_SEAGRASS_SIMPLE.getHolder().orElseThrow(), ImmutableList.of(
            CarvingMaskPlacement.forStep(GenerationStep.Carving.LIQUID),
            RarityFilter.onAverageOnceEvery(10),
            BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                            BlockPredicate.matchesBlock(
                                    Blocks.NETHERRACK, new BlockPos(0, -1, 0)),
                            BlockPredicate.matchesBlock(Blocks.LAVA, BlockPos.ZERO),
                            BlockPredicate.matchesBlock(Blocks.LAVA, new BlockPos(0, 1, 0)))),
            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> WARPED_KELP_PLACED = PLACED_FEATURES.register(WARPED_KELP_FEATURE_NAME, () -> new PlacedFeature(CONFIGURED_WARPED_KELP.getHolder().orElseThrow(), ImmutableList.of(
            NoiseBasedCountPlacement.of(120, 80.0D, 0.0D),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> WARPED_SEAGRASS_SHORT_PLACED = PLACED_FEATURES.register(WARPED_SEAGRASS_SHORT_FEATURE_NAME, () -> new PlacedFeature(CONFIGURED_WARPED_SEAGRASS_SHORT.getHolder().orElseThrow(), ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(80),
            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_PLACED = PLACED_FEATURES.register(WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME, () -> new PlacedFeature(CONFIGURED_WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT.getHolder().orElseThrow(), ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(48),
            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> WARPED_SEAGRASS_MID_PLACED = PLACED_FEATURES.register(WARPED_SEAGRASS_MID_FEATURE_NAME, () -> new PlacedFeature(CONFIGURED_WARPED_SEAGRASS_MID.getHolder().orElseThrow(), ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(64),
            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> WARPED_SEAGRASS_TALL_PLACED = PLACED_FEATURES.register(WARPED_SEAGRASS_TALL_FEATURE_NAME, () -> new PlacedFeature(CONFIGURED_WARPED_SEAGRASS_TALL.getHolder().orElseThrow(), ImmutableList.of(
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            CountPlacement.of(80),
            BiomeFilter.biome())));


}
