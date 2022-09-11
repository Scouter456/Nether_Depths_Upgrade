package com.scouter.netherdepthsupgrade.entity;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.gen.Heightmap;

public class NDUEntityPlacement {
    public  static void entityPlacement() {
        EntitySpawnPlacementRegistry.register(NDUEntity.OBSIDIAN_FISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(NDUEntity.SEARING_COD.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(NDUEntity.BLAZEFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(NDUEntity.SOULSUCKER.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(NDUEntity.WITHER_BONEFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(NDUEntity.GLOWDINE.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(NDUEntity.MAGMACUBEFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(NDUEntity.BONEFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(NDUEntity.LAVA_PUFFERFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules);

    }
}
