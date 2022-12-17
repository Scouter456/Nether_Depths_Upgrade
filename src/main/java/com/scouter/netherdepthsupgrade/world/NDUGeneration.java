package com.scouter.netherdepthsupgrade.world;

import com.scouter.netherdepthsupgrade.entity.LavaAnimal;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.util.NDUTags;
import com.scouter.netherdepthsupgrade.world.feature.NDUConfiguredFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;

public class NDUGeneration {

    public static void generateFeatures() {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_KELP_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_SIMPLE_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_SHORT_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_MID_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_TALL_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.VENT_PLACED.unwrapKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.LAVA_SPONGE_PLACED.unwrapKey().get());
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
}

