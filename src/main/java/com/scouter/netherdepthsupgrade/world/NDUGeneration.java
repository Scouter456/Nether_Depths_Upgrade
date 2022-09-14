package com.scouter.netherdepthsupgrade.world;

import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.structures.NDUConfiguredStructures;
import com.scouter.netherdepthsupgrade.structures.NDUStructures;
import com.scouter.netherdepthsupgrade.world.feature.NDUConfiguredFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class NDUGeneration {
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void generateFeatures(BiomeLoadingEvent event) {
        if(event.getCategory().equals(Biome.Category.NETHER)) {


            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.CONFIGURED_WARPED_SEAGRASS_SIMPLE);
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.CONFIGURED_WARPED_KELP);
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.CONFIGURED_WARPED_SEAGRASS_SLIGHTLY_LESS_SHORT);
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.CONFIGURED_WARPED_SEAGRASS_SHORT);
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.CONFIGURED_WARPED_SEAGRASS_MID);
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.CONFIGURED_WARPED_SEAGRASS_TALL);
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.CONFIGURED_VENT);
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, NDUConfiguredFeatures.CONFIGURED_LAVA_SPONGE);
            event.getGeneration().getStructures().add(() -> NDUConfiguredStructures.CONFIGURED_NETHER_FORTRESS_PIECE);
        }

        spawnCreatures(event);
    }

    public static void spawnCreatures(final BiomeLoadingEvent event) {
        if(event.getCategory().equals(Biome.Category.NETHER)) {
            List<MobSpawnInfo.Spawners> base = event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT);

            base.add(new MobSpawnInfo.Spawners(NDUEntity.BONEFISH.get(), 8, 1, 5));
            base.add(new MobSpawnInfo.Spawners(NDUEntity.SEARING_COD.get(), 10, 3, 6));

            if (event.getName().equals(new ResourceLocation("minecraft", "soul_sand_valley"))) {
                base.add(new MobSpawnInfo.Spawners(NDUEntity.SOULSUCKER.get(), 10, 1, 2));
                base.add(new MobSpawnInfo.Spawners(NDUEntity.WITHER_BONEFISH.get(), 5, 1, 3));
            }

            if (event.getName().equals(new ResourceLocation("minecraft", "basalt_deltas"))) {
                base.add(new MobSpawnInfo.Spawners(NDUEntity.OBSIDIAN_FISH.get(), 15, 1, 5));
                base.add(new MobSpawnInfo.Spawners(NDUEntity.MAGMACUBEFISH.get(), 10, 2, 4));
            }

            if (event.getName().equals(new ResourceLocation("minecraft", "crimson_forest"))) {
                base.add(new MobSpawnInfo.Spawners(NDUEntity.LAVA_PUFFERFISH.get(), 15, 1, 5));
                base.add(new MobSpawnInfo.Spawners(NDUEntity.WITHER_BONEFISH.get(), 3, 1, 3));
            }

            if (event.getName().equals(new ResourceLocation("minecraft", "warped_forest"))) {
                base.add(new MobSpawnInfo.Spawners(NDUEntity.LAVA_PUFFERFISH.get(), 15, 1, 5));
            }

            if ((!event.getName().equals(new ResourceLocation("minecraft", "crimson_forest")) || (!event.getName().equals(new ResourceLocation("minecraft", "warped_forest"))))) {
                base.add(new MobSpawnInfo.Spawners(NDUEntity.GLOWDINE.get(), 8, 4, 6));
            }

        }
    }
}
