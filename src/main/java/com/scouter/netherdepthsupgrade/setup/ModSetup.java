package com.scouter.netherdepthsupgrade.setup;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.entity.LavaAnimal;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.entity.entities.*;
import com.scouter.netherdepthsupgrade.modcompat.ModChecker;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static void init(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            ModChecker.setupModCompatCommonSetup();
        });
    }

    public static void setup(){
    }


    @SubscribeEvent
    public static void registerMobSpawns(RegisterSpawnPlacementsEvent event) {
        entityPlacement(event);
    }
    public  static void entityPlacement(RegisterSpawnPlacementsEvent event) {
        event.register(NDUEntity.OBSIDIAN_FISH.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.SEARING_COD.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.BLAZEFISH.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.SOULSUCKER.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.WITHER_BONEFISH.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.GLOWDINE.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.MAGMACUBEFISH.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.BONEFISH.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.LAVA_PUFFERFISH.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.EYEBALL_FISH.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
        event.register(NDUEntity.FORTRESS_GROUPER.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaAnimal::checkSurfaceLavaAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event){
        event.put(NDUEntity.LAVA_PUFFERFISH.get(), LavaPufferfishEntity.setAttributes());
        event.put(NDUEntity.OBSIDIAN_FISH.get(), ObsidianfishEntity.setAttributes());
        event.put(NDUEntity.SEARING_COD.get(), SearingCodEntity.setAttributes());
        event.put(NDUEntity.BONEFISH.get(), BonefishEntity.setAttributes());
        event.put(NDUEntity.WITHER_BONEFISH.get(), WitherBonefishEntity.setAttributes());
        event.put(NDUEntity.BLAZEFISH.get(), BlazefishEntity.setAttributes());
        event.put(NDUEntity.MAGMACUBEFISH.get(), MagmaCubefishEntity.setAttributes());
        event.put(NDUEntity.GLOWDINE.get(), GlowdineEntity.setAttributes());
        event.put(NDUEntity.SOULSUCKER.get(), SoulSuckerEntity.setAttributes());
        event.put(NDUEntity.EYEBALL_FISH.get(), EyeballfishEntity.setAttributes());
        event.put(NDUEntity.FORTRESS_GROUPER.get(), FortressGrouperEntity.setAttributes());
    }




}
