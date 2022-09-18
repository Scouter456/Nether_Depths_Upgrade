package com.scouter.netherdepthsupgrade.setup;

import com.google.common.collect.ImmutableList;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.entity.NDUEntityPlacement;
import com.scouter.netherdepthsupgrade.entity.entities.*;
import com.scouter.netherdepthsupgrade.events.ForgeEvents;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.particle.GlowdineParticle;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
import com.scouter.netherdepthsupgrade.potion.NDUPotions;
import com.scouter.netherdepthsupgrade.structures.NDUConfiguredStructures;
import com.scouter.netherdepthsupgrade.structures.NDUStructDimSpace;
import com.scouter.netherdepthsupgrade.structures.NDUStructures;
import com.scouter.netherdepthsupgrade.utils.BetterBrewingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityClassification;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {
    public static final List<MobSpawnInfo.Spawners> STRUCTURE_FISH_MOD = new ArrayList<>();
    public static final List<MobSpawnInfo.Spawners> STRUCTURE_FISH = Collections.unmodifiableList(STRUCTURE_FISH_MOD);
    public static void init(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            NDUEntityPlacement.entityPlacement();

            NDUStructures.setupStructures();
            NDUConfiguredStructures.registerConfiguredStructures();
            STRUCTURE_FISH_MOD.add(new MobSpawnInfo.Spawners(NDUEntity.BLAZEFISH.get(), 1500, 2, 4));
  //          BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
  //                  Potions.AWKWARD, NDUItems.LAVA_PUFFERFISH.get(), NDUPotions.LAVA_VISION.get()));
  //          BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
  //                  NDUPotions.LAVA_VISION.get(), Items.REDSTONE, NDUPotions.LONG_LAVA_VISION.get()));

            PotionBrewing.addMix(Potions.AWKWARD, NDUItems.LAVA_PUFFERFISH.get(), NDUPotions.LAVA_VISION.get());
            PotionBrewing.addMix(NDUPotions.LAVA_VISION.get(), Items.REDSTONE, NDUPotions.LONG_LAVA_VISION.get());
        });
    }

    public static void setup(){
        IEventBus bus = MinecraftForge.EVENT_BUS;
        //bus.addListener(EventPriority.HIGH, this::biomeModification);
        bus.addListener(EventPriority.NORMAL, NDUStructDimSpace::addDimensionalSpacing);
        //bus.addListener(EventPriority.HIGH, ForgeEvents::spawnListEvent);
    }



    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event){
        event.put(NDUEntity.LAVA_PUFFERFISH.get(), LavaPufferfishEntity.createAttributes().build());
        event.put(NDUEntity.OBSIDIAN_FISH.get(), ObsidianfishEntity.setAttributes().build());
        event.put(NDUEntity.SEARING_COD.get(), SearingCodEntity.createAttributes().build());
        event.put(NDUEntity.BONEFISH.get(), BonefishEntity.createAttributes().build());
        event.put(NDUEntity.WITHER_BONEFISH.get(), WitherBonefishEntity.createAttributes().build());
        event.put(NDUEntity.BLAZEFISH.get(), BlazefishEntity.setAttributes().build());
        event.put(NDUEntity.MAGMACUBEFISH.get(), MagmaCubefishEntity.setAttributes().build());
        event.put(NDUEntity.GLOWDINE.get(), GlowdineEntity.createAttributes().build());
        event.put(NDUEntity.SOULSUCKER.get(), SoulSuckerEntity.createAttributes().build());
    }





}
