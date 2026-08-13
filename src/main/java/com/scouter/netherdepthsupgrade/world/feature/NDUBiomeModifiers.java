package com.scouter.netherdepthsupgrade.world.feature;

import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.utils.NDUTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED = registerKey("add_warped_seagrass_simple");
    public static final ResourceKey<BiomeModifier> ADD_WARPED_KELP_FEATURE_NAME_PLACED = registerKey("add_warped_kelp");
    public static final ResourceKey<BiomeModifier> ADD_WARPED_KELP_FEATURE_NAME_PLACED_COMMON = registerKey("add_warped_kelp_common");

    public static final ResourceKey<BiomeModifier> ADD_WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED = registerKey("add_warped_seagrass_short");
    public static final ResourceKey<BiomeModifier> ADD_WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED = registerKey("add_warped_seagrass_slightly_less_short");
    public static final ResourceKey<BiomeModifier> ADD_WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED = registerKey("add_warped_seagrass_mid");
    public static final ResourceKey<BiomeModifier> ADD_WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED = registerKey("add_warped_seagrass_tall");
    public static final ResourceKey<BiomeModifier> ADD_WARPED_SEAGRASS_COMMON_TALL_FEATURE_NAME_PLACED = registerKey("add_warped_seagrass_common_tall");

    public static final ResourceKey<BiomeModifier> ADD_VENT_FEATURE_NAME_PLACED = registerKey("add_vent");
    public static final ResourceKey<BiomeModifier> ADD_LAVA_SPONGE_FEATURE_NAME_PLACED = registerKey("add_lava_sponge");
    public static final ResourceKey<BiomeModifier> ADD_CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED = registerKey("add_crimson_seagrass_simple");
    public static final ResourceKey<BiomeModifier> ADD_CRIMSON_KELP_FEATURE_NAME_PLACED = registerKey("add_crimson_kelp");
    public static final ResourceKey<BiomeModifier> ADD_CRIMSON_KELP_FEATURE_NAME_PLACED_COMMON = registerKey("add_crimson_kelp_common");


    public static final ResourceKey<BiomeModifier> ADD_CRIMSON_SEAGRASS_SHORT_FEATURE_NAME_PLACED = registerKey("add_crimson_seagrass_short");
    public static final ResourceKey<BiomeModifier> ADD_CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED = registerKey("add_crimson_seagrass_slightly_less_short");
    public static final ResourceKey<BiomeModifier> ADD_CRIMSON_SEAGRASS_MID_FEATURE_NAME_PLACED = registerKey("add_crimson_seagrass_mid");
    public static final ResourceKey<BiomeModifier> ADD_CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED = registerKey("add_crimson_seagrass_tall");
    public static final ResourceKey<BiomeModifier> ADD_CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED_COMMON = registerKey("add_crimson_seagrass_tall_common");


    public static final ResourceKey<BiomeModifier> ADD_BONEFISH = registerKey("add_bonefish");
    public static final ResourceKey<BiomeModifier> ADD_EYEBALL_FISH = registerKey("add_eyeball_fish");
    public static final ResourceKey<BiomeModifier> ADD_FORTRESS_GROUPER = registerKey("add_fortress_grouper");
    public static final ResourceKey<BiomeModifier> ADD_GLOWDINE = registerKey("add_glowdine");
    public static final ResourceKey<BiomeModifier> ADD_LAVA_PUFFERFISH = registerKey("add_lava_pufferfish");
    public static final ResourceKey<BiomeModifier> ADD_MAGMACUBEFISH = registerKey("add_magmacubefish");
    public static final ResourceKey<BiomeModifier> ADD_OBSIDIANFISH = registerKey("add_obsidianfish");
    public static final ResourceKey<BiomeModifier> ADD_SEARING_COD = registerKey("add_searing_cod");
    public static final ResourceKey<BiomeModifier> ADD_SOUL_SUCKER = registerKey("add_soul_sucker");
    public static final ResourceKey<BiomeModifier> ADD_WITHER_BONEFISH= registerKey("add_wither_bonefish");



    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        context.register(ADD_WARPED_KELP_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.WARPED_KELP_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_WARPED_SEAGRASS_COMMON_TALL_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.WARPED_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED_COMMON)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_WARPED_KELP_FEATURE_NAME_PLACED_COMMON, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.WARPED_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.WARPED_KELP_FEATURE_NAME_PLACED_COMMON)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        context.register(ADD_VENT_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.VENT_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_LAVA_SPONGE_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.LAVA_SPONGE_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.CRIMSON_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_CRIMSON_KELP_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.CRIMSON_KELP_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_CRIMSON_SEAGRASS_SHORT_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.CRIMSON_SEAGRASS_SHORT_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.CRIMSON_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_CRIMSON_SEAGRASS_MID_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.CRIMSON_SEAGRASS_MID_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        context.register(ADD_CRIMSON_KELP_FEATURE_NAME_PLACED_COMMON, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.CRIMSON_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.CRIMSON_KELP_FEATURE_NAME_PLACED_COMMON)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED_COMMON, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.CRIMSON_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(NDUPlacedFeatures.CRIMSON_SEAGRASS_TALL_FEATURE_NAME_PLACED_COMMON)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        MobSpawnSettings.SpawnerData bonefish = new MobSpawnSettings.SpawnerData(NDUEntity.BONEFISH.get(), 8,1, 5);
        context.register(ADD_BONEFISH, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                List.of(bonefish)));


        MobSpawnSettings.SpawnerData eyeballFish = new MobSpawnSettings.SpawnerData(NDUEntity.EYEBALL_FISH.get(), 3,2, 4);
        context.register(ADD_EYEBALL_FISH, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(NDUTags.Biomes.IS_EYEBALL_FISH_BIOME),
                List.of(eyeballFish)));

        MobSpawnSettings.SpawnerData fg = new MobSpawnSettings.SpawnerData(NDUEntity.FORTRESS_GROUPER.get(), 3,1, 2);
        context.register(ADD_FORTRESS_GROUPER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(NDUTags.Biomes.IS_FORTRESS_GROUPER_BIOME),
                List.of(fg)));

        MobSpawnSettings.SpawnerData gd = new MobSpawnSettings.SpawnerData(NDUEntity.GLOWDINE.get(), 8,4, 6);
        context.register(ADD_GLOWDINE, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(NDUTags.Biomes.IS_GLOWDINE_BIOME),
                List.of(gd)));

        MobSpawnSettings.SpawnerData pf = new MobSpawnSettings.SpawnerData(NDUEntity.LAVA_PUFFERFISH.get(), 15,1, 5);
        context.register(ADD_LAVA_PUFFERFISH, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(NDUTags.Biomes.IS_LAVA_PUFFERFISH_BIOME),
                List.of(pf)));

        MobSpawnSettings.SpawnerData mc = new MobSpawnSettings.SpawnerData(NDUEntity.MAGMACUBEFISH.get(), 10,2, 4);
        context.register(ADD_MAGMACUBEFISH, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(NDUTags.Biomes.IS_MAGMACUBEFISH_BIOME),
                List.of(mc)));

        MobSpawnSettings.SpawnerData of = new MobSpawnSettings.SpawnerData(NDUEntity.OBSIDIAN_FISH.get(), 15,1, 5);
        context.register(ADD_OBSIDIANFISH, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(NDUTags.Biomes.IS_OBSIDIANFISH_BIOME),
                List.of(of)));


        MobSpawnSettings.SpawnerData sc = new MobSpawnSettings.SpawnerData(NDUEntity.SEARING_COD.get(), 10,3, 6);
        context.register(ADD_SEARING_COD, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                List.of(sc)));

        MobSpawnSettings.SpawnerData wbf = new MobSpawnSettings.SpawnerData(NDUEntity.WITHER_BONEFISH.get(), 3,1, 3);
        context.register(ADD_WITHER_BONEFISH, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(NDUTags.Biomes.IS_WITHER_BONEFISH_BIOME),
                List.of(wbf)));

        MobSpawnSettings.SpawnerData ss = new MobSpawnSettings.SpawnerData(NDUEntity.SOULSUCKER.get(), 10,1, 2);
        context.register(ADD_SOUL_SUCKER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(NDUTags.Biomes.IS_SOULSUCKER_BIOME),
                List.of(ss)));
    }
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, prefix( name));
    }
}
