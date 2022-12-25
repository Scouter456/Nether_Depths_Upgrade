package com.scouter.netherdepthsupgrade.world;

import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.modcompat.ModChecker;
import com.scouter.netherdepthsupgrade.world.feature.NDUConfiguredFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class NDUGeneration {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void generateFeatures(BiomeLoadingEvent event) {
        if(event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_SIMPLE_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_KELP_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_SHORT_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_MID_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_TALL_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.VENT_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.LAVA_SPONGE_PLACED.getHolder().orElseThrow());
        }

        spawnCreatures(event);
    }

    public static void spawnCreatures(final BiomeLoadingEvent event) {
        if(event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT);

            base.add(new MobSpawnSettings.SpawnerData(NDUEntity.BONEFISH.get(), 8, 1, 5));
            base.add(new MobSpawnSettings.SpawnerData(NDUEntity.SEARING_COD.get(), 10, 3, 6));

            if (event.getName().equals(new ResourceLocation("minecraft", "soul_sand_valley"))) {
                base.add(new MobSpawnSettings.SpawnerData(NDUEntity.SOULSUCKER.get(), 10, 1, 2));
                base.add(new MobSpawnSettings.SpawnerData(NDUEntity.WITHER_BONEFISH.get(), 5, 1, 3));
            }

            if (event.getName().equals(new ResourceLocation("minecraft", "basalt_deltas"))) {
                base.add(new MobSpawnSettings.SpawnerData(NDUEntity.OBSIDIAN_FISH.get(), 15, 1, 5));
                base.add(new MobSpawnSettings.SpawnerData(NDUEntity.MAGMACUBEFISH.get(), 10, 2, 4));
            }

            if (event.getName().equals(new ResourceLocation("minecraft", "crimson_forest"))) {
                base.add(new MobSpawnSettings.SpawnerData(NDUEntity.LAVA_PUFFERFISH.get(), 15, 1, 5));
                base.add(new MobSpawnSettings.SpawnerData(NDUEntity.WITHER_BONEFISH.get(), 3, 1, 3));
            }

            if (event.getName().equals(new ResourceLocation("minecraft", "warped_forest"))) {
                base.add(new MobSpawnSettings.SpawnerData(NDUEntity.LAVA_PUFFERFISH.get(), 15, 1, 5));
            }


            if ((!event.getName().equals(new ResourceLocation("minecraft", "crimson_forest")) || (!event.getName().equals(new ResourceLocation("minecraft", "warped_forest"))))) {
                base.add(new MobSpawnSettings.SpawnerData(NDUEntity.GLOWDINE.get(), 8, 4, 6));
            }
            //Todo add this to modchecker
            if(ModChecker.infernalExpansionPresent){
                if(event.getName().equals(new ResourceLocation("infernalexp", "glowstone_canyon"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.GLOWDINE.get(), 8, 4, 6));
                }
            }
            if(ModChecker.biomesYoullGoPresent){
                if(event.getName().equals(new ResourceLocation("byg", "glowstone_gardens"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.GLOWDINE.get(), 8, 4, 6));
                }
                if(event.getName().equals(new ResourceLocation("byg", "warped_desert"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.SOULSUCKER.get(), 5, 1, 2));
                }

                if(event.getName().equals(new ResourceLocation("byg", "wailing_garth"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.SOULSUCKER.get(), 5, 1, 2));
                }

                if(event.getName().equals(new ResourceLocation("byg", "brimstone_caverns"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.OBSIDIAN_FISH.get(), 7, 1, 5));
                }

                if(event.getName().equals(new ResourceLocation("byg", "embur_bog"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.MAGMACUBEFISH.get(), 10, 2, 4));
                }

                if(event.getName().equals(new ResourceLocation("byg", "magma_wastes"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.MAGMACUBEFISH.get(), 10, 2, 4));
                }

                if(event.getName().equals(new ResourceLocation("byg", "crimson_gardens"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.LAVA_PUFFERFISH.get(), 15, 1, 5));
                }

                if(event.getName().equals(new ResourceLocation("byg", "sythian_torrids"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.LAVA_PUFFERFISH.get(), 15, 1, 5));
                }

                if(event.getName().equals(new ResourceLocation("byg", "subzero_hypogeal"))){
                    base.add(new MobSpawnSettings.SpawnerData(NDUEntity.WITHER_BONEFISH.get(), 3, 1, 3));
                }
            }
        }
    }
}
