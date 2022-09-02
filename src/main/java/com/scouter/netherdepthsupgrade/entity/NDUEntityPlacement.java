package com.scouter.netherdepthsupgrade.entity;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class NDUEntityPlacement {
    public  static void entityPlacement() {
        SpawnPlacements.register(NDUEntity.OBSIDIAN_FISH.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.SEARING_COD.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);

    }
}
