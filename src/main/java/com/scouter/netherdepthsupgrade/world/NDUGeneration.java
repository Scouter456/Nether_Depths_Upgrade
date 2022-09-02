package com.scouter.netherdepthsupgrade.world;

import com.mojang.logging.LogUtils;

import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.world.feature.NDUConfiguredFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;

import java.util.List;

public class NDUGeneration {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void generateFeatures(BiomeLoadingEvent event) {
        //LOGGER.info("Categories: " + event.getCategory().getName().toLowerCase() + " temperature: " + event.getClimate().temperature + " downfall: " + event.getClimate().downfall + " effects: " + event.getEffects().getAmbientAdditionsSettings());
        if(event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_SIMPLE_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_KELP_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_SHORT_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_MID_PLACED.getHolder().orElseThrow());
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.WARPED_SEAGRASS_TALL_PLACED.getHolder().orElseThrow());

        }

        spawnCreatures(event);
    }

    public static void spawnCreatures(final BiomeLoadingEvent event) {
        if(event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT);
            base.add(new MobSpawnSettings.SpawnerData(NDUEntity.OBSIDIAN_FISH.get(), 15, 1, 5));
            base.add(new MobSpawnSettings.SpawnerData(NDUEntity.SEARING_COD.get(), 10, 3, 6));

        }
    }
}
