package com.scouter.netherdepthsupgrade.entity;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class NDUEntityPlacement {
    public  static void entityPlacement() {
        SpawnPlacements.register(NDUEntity.OBSIDIAN_FISH.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.SEARING_COD.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.BLAZEFISH.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.SOULSUCKER.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.WITHER_BONEFISH.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.GLOWDINE.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.MAGMACUBEFISH.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.BONEFISH.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        SpawnPlacements.register(NDUEntity.LAVA_PUFFERFISH.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);

    }
}
