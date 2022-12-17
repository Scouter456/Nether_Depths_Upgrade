package com.scouter.netherdepthsupgrade.world;

import com.scouter.netherdepthsupgrade.entity.LavaAnimal;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.util.NDUTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUGeneration {
    private static final String WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED = "warped_seagrass_simple_feature_placed";
    private static final String WARPED_KELP_FEATURE_NAME_PLACED = "warped_kelp_feature_placed";
    private static final String WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED = "warped_seagrass_short_feature_placed";
    private static final String WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED = "warped_seagrass_slightly_less_short_feature_placed";
    private static final String WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED = "warped_seagrass_mid_feature_placed";
    private static final String WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED = "warped_seagrass_tall_feature_placed";
    private static final String VENT_FEATURE_NAME_PLACED = "vent_feature_placed";
    private static final String LAVA_SPONGE_FEATURE_NAME_PLACED = "lava_sponge_feature_placed";
    public static void generateFeatures() {
        addPlacedFeature(WARPED_SEAGRASS_SIMPLE_FEATURE_NAME_PLACED);
        addPlacedFeature(WARPED_KELP_FEATURE_NAME_PLACED);
        addPlacedFeature(WARPED_SEAGRASS_SHORT_FEATURE_NAME_PLACED);
        addPlacedFeature(WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_FEATURE_NAME_PLACED);
        addPlacedFeature(WARPED_SEAGRASS_MID_FEATURE_NAME_PLACED);
        addPlacedFeature(WARPED_SEAGRASS_TALL_FEATURE_NAME_PLACED);
        addPlacedFeature(VENT_FEATURE_NAME_PLACED);
        addPlacedFeature(LAVA_SPONGE_FEATURE_NAME_PLACED);
       }

    public static void spawnCreatures() {
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.WATER_AMBIENT, NDUEntity.BONEFISH, 8,1,5);
        BiomeModifications.addSpawn(BiomeSelectors.tag(NDUTags.Biomes.IS_OBSIDIANFISH_BIOME), MobCategory.WATER_AMBIENT, NDUEntity.OBSIDIAN_FISH, 15,1,5);
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), MobCategory.WATER_AMBIENT, NDUEntity.SEARING_COD, 10,3,6);
        BiomeModifications.addSpawn(BiomeSelectors.tag(NDUTags.Biomes.IS_SOULSUCKER_BIOME), MobCategory.WATER_AMBIENT, NDUEntity.SOULSUCKER, 10,1,2);
        BiomeModifications.addSpawn(BiomeSelectors.tag(NDUTags.Biomes.IS_WITHER_BONEFISH_BIOME), MobCategory.WATER_AMBIENT, NDUEntity.WITHER_BONEFISH, 5,1,3);
        BiomeModifications.addSpawn(BiomeSelectors.tag(NDUTags.Biomes.IS_MAGMACUBEFISH_BIOME), MobCategory.WATER_AMBIENT, NDUEntity.MAGMACUBEFISH, 10,2,4);
        BiomeModifications.addSpawn(BiomeSelectors.tag(NDUTags.Biomes.IS_LAVA_PUFFERFISH_BIOME), MobCategory.WATER_AMBIENT, NDUEntity.LAVA_PUFFERFISH, 15,1,5);
        BiomeModifications.addSpawn(BiomeSelectors.tag(NDUTags.Biomes.IS_GLOWDINE_BIOME), MobCategory.WATER_AMBIENT, NDUEntity.GLOWDINE, 8,4,6);

        SpawnPlacements.register(NDUEntity.OBSIDIAN_FISH, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.SEARING_COD, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.BLAZEFISH, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.SOULSUCKER, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.WITHER_BONEFISH, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.GLOWDINE, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.MAGMACUBEFISH, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.BONEFISH, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.LAVA_PUFFERFISH, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);

    }

    public static void addPlacedFeature(String name){
        BiomeModifications.create(prefix(name)).add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInTheNether(), context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, prefix(name))));

    }
}

