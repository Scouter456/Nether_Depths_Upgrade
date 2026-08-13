package com.scouter.netherdepthsupgrade.world.feature;

import com.google.common.collect.ImmutableList;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

public class NDUPlacedFeatures {
    public static final ResourceKey<PlacedFeature> WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED = registerKey("warped_seagrass_simple_feature_placed");
    public static final ResourceKey<PlacedFeature> WARPED_KELP_FEATURE_NAME_PLACED = registerKey("warped_kelp_feature_placed");
    public static final ResourceKey<PlacedFeature> WARPED_KELP_FEATURE_NAME_PLACED_COMMON = registerKey("warped_kelp_feature_common_placed");

    public static final ResourceKey<PlacedFeature> WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED = registerKey("warped_seagrass_short_feature_placed");
    public static final ResourceKey<PlacedFeature> WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED = registerKey("warped_seagrass_slightly_less_short_feature_placed");
    public static final ResourceKey<PlacedFeature> WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED = registerKey("warped_seagrass_mid_feature_placed");
    public static final ResourceKey<PlacedFeature> WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED = registerKey("warped_seagrass_tall_feature_placed");
    public static final ResourceKey<PlacedFeature> WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED_COMMON = registerKey("warped_seagrass_tall_feature_common_placed");


    public static final ResourceKey<PlacedFeature> VENT_FEATURE_NAME_PLACED = registerKey("vent_feature_placed");
    public static final ResourceKey<PlacedFeature> LAVA_SPONGE_FEATURE_NAME_PLACED = registerKey("lava_sponge_feature_placed");

    public static final ResourceKey<PlacedFeature> CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED = registerKey("crimson_seagrass_simple_feature_placed");
    public static final ResourceKey<PlacedFeature> CRIMSON_KELP_FEATURE_NAME_PLACED = registerKey("crimson_kelp_feature_placed");
    public static final ResourceKey<PlacedFeature> CRIMSON_KELP_FEATURE_NAME_PLACED_COMMON = registerKey("crimson_kelp_feature_common_placed");



    public static final ResourceKey<PlacedFeature> CRIMSON_SEAGRASS_SHORT_FEATURE_NAME_PLACED = registerKey("crimson_seagrass_short_feature_placed");
    public static final ResourceKey<PlacedFeature> CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED = registerKey("crimson_seagrass_slightly_less_short_feature_placed");
    public static final ResourceKey<PlacedFeature> CRIMSON_SEAGRASS_MID_FEATURE_NAME_PLACED = registerKey("crimson_seagrass_mid_feature_placed");
    public static final ResourceKey<PlacedFeature> CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED = registerKey("crimson_seagrass_tall_feature_placed");
    public static final ResourceKey<PlacedFeature> CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED_COMMON = registerKey("crimson_seagrass_tall_feature_common_placed");



    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        context.register(WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.WARPED_SEAGRASS_SIMPLE_FEATURE_NAME), ImmutableList.of(
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
                BiomeFilter.biome())));

        context.register(WARPED_KELP_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.WARPED_KELP_FEATURE_NAME), ImmutableList.of(
                NoiseBasedCountPlacement.of(120, 80.0D, 0.0D),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(1),
                        VerticalAnchor.absolute(39)
                ),
                BiomeFilter.biome())));


        context.register(WARPED_KELP_FEATURE_NAME_PLACED_COMMON, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.WARPED_KELP_FEATURE_NAME), ImmutableList.of(
                NoiseBasedCountPlacement.of(256, 80.0D, 0.0D),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(1),
                        VerticalAnchor.absolute(39)
                ),
                BiomeFilter.biome())));

        context.register(WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED, new PlacedFeature(
                features.getOrThrow(NDUConfiguredFeatures.WARPED_SEAGRASS_SHORT_FEATURE_NAME),
                ImmutableList.of(
                        CountPlacement.of(80),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(1),
                                VerticalAnchor.absolute(39)
                        ),
                        BiomeFilter.biome()
                )
        ));

        context.register(WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED, new PlacedFeature(
                features.getOrThrow(NDUConfiguredFeatures.WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME),
                ImmutableList.of(
                        CountPlacement.of(48),
                        InSquarePlacement.spread(),

                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(1),
                                VerticalAnchor.absolute(39)
                        ),
                        BiomeFilter.biome()
                )
        ));

        context.register(WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED, new PlacedFeature(
                features.getOrThrow(NDUConfiguredFeatures.WARPED_SEAGRASS_MID_FEATURE_NAME),
                ImmutableList.of(
                        CountPlacement.of(64),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(1),
                                VerticalAnchor.absolute(39)
                        ),
                        BiomeFilter.biome()
                )
        ));

        context.register(WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED, new PlacedFeature(
                features.getOrThrow(NDUConfiguredFeatures.WARPED_SEAGRASS_TALL_FEATURE_NAME),
                ImmutableList.of(
                        CountPlacement.of(80),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(1),
                                VerticalAnchor.absolute(39)
                        ),
                        BiomeFilter.biome()
                )
        ));

        context.register(WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED_COMMON, new PlacedFeature(
                features.getOrThrow(NDUConfiguredFeatures.WARPED_SEAGRASS_TALL_FEATURE_NAME),
                ImmutableList.of(
                        CountPlacement.of(256),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(1),
                                VerticalAnchor.absolute(39)
                        ),
                        BiomeFilter.biome()
                )
        ));


        context.register(VENT_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.VENT_FEATURE_NAME),
                ImmutableList.of(
                        RarityFilter.onAverageOnceEvery(20),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(1),
                                VerticalAnchor.absolute(39)
                        ),
                        BiomeFilter.biome()
                )));

        context.register(LAVA_SPONGE_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.LAVA_SPONGE_FEATURE_NAME), ImmutableList.of(
                RarityFilter.onAverageOnceEvery(25),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_TOP_SOLID,
                BiomeFilter.biome())
        ));


        context.register(CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME), ImmutableList.of(
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
                BiomeFilter.biome())));

        context.register(CRIMSON_KELP_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.CRIMSON_KELP_FEATURE_NAME), ImmutableList.of(
                NoiseBasedCountPlacement.of(120, 80.0D, 0.0D),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(1),
                        VerticalAnchor.absolute(39)
                ),
                BiomeFilter.biome())));
        context.register(CRIMSON_KELP_FEATURE_NAME_PLACED_COMMON, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.CRIMSON_KELP_FEATURE_NAME), ImmutableList.of(
                NoiseBasedCountPlacement.of(256, 80.0D, 0.0D),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(1),
                        VerticalAnchor.absolute(39)
                ),
                BiomeFilter.biome())));

        context.register(CRIMSON_SEAGRASS_SHORT_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.CRIMSON_SEAGRASS_SHORT_FEATURE_NAME), ImmutableList.of(
                CountPlacement.of(80),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(1),
                        VerticalAnchor.absolute(39)
                ),
                BiomeFilter.biome())
        ));

        context.register(CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME), ImmutableList.of(
                CountPlacement.of(48),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(1),
                        VerticalAnchor.absolute(39)
                ),
                BiomeFilter.biome()
        )));

        context.register(CRIMSON_SEAGRASS_MID_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.CRIMSON_SEAGRASS_MID_FEATURE_NAME), ImmutableList.of(
                CountPlacement.of(64),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(1),
                        VerticalAnchor.absolute(39)
                ),
                BiomeFilter.biome()
        )));

        context.register(CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.CRIMSON_SEAGRASS_TALL_FEATURE_NAME), ImmutableList.of(
                CountPlacement.of(80),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(1),
                        VerticalAnchor.absolute(39)
                ),
                BiomeFilter.biome()
        )));
        context.register(CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED_COMMON, new PlacedFeature(features.getOrThrow(NDUConfiguredFeatures.CRIMSON_SEAGRASS_TALL_FEATURE_NAME), ImmutableList.of(
                CountPlacement.of(256),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(1),
                        VerticalAnchor.absolute(39)
                ),
                BiomeFilter.biome()
        )));

    }



    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, NetherDepthsUpgrade.prefix(name));
    }
}
