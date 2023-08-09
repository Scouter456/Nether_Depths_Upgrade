package com.scouter.netherdepthsupgrade.setup;

import com.scouter.netherdepthsupgrade.advancements.NDUAdvancementTriggers;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.blocks.entity.NDUBlockEntities;
import com.scouter.netherdepthsupgrade.creativetabs.NDUTabs;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.entity.entities.*;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
import com.scouter.netherdepthsupgrade.potion.NDUPotions;
import com.scouter.netherdepthsupgrade.structures.NDUStructures;
import com.scouter.netherdepthsupgrade.world.NDUGeneration;
import com.scouter.netherdepthsupgrade.world.feature.NDUConfiguredFeatures;
import com.scouter.netherdepthsupgrade.world.feature.NDUFeatures;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class Registration {

    public static void init(){
        NDUItems.ITEMS();
        NDUBlocks.BLOCKS();
        NDUBlockEntities.BLOCKENTITIES();
        NDUTabs.TABS();
        NDUEntity.ENTITY_TYPES();
        MobEffects.MOBEFFECTS();
        NDUPotions.POTIONS();
        NDUEnchantments.ENCHANTMENTS();
        NDUParticle.PARTICLE();
        NDUFeatures.FEATURES();
        NDUConfiguredFeatures.CONFIGURED_FEATURES();
        NDUStructures.STRUCTURES();
        registerAttributes();
        registerFuels();
        NDUGeneration.generateFeatures();
        NDUGeneration.spawnCreatures();
        NDUAdvancementTriggers.init();

    }


    public static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(NDUEntity.LAVA_PUFFERFISH, LavaPufferfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.OBSIDIAN_FISH, ObsidianfishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.SEARING_COD, SearingCodEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.BONEFISH, BonefishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.WITHER_BONEFISH, WitherBonefishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.BLAZEFISH, BlazefishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.MAGMACUBEFISH, MagmaCubefishEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.GLOWDINE, GlowdineEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.SOULSUCKER, SoulSuckerEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.FORTRESS_GROUPER, FortressGrouperEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(NDUEntity.EYEBALL_FISH, EyeballfishEntity.setAttributes());
    }

    private static void registerFuels(){
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(NDUBlocks.WARPED_KELP_BLOCK, 6400);
        registry.add(NDUBlocks.CRIMSON_KELP_BLOCK, 6400);
        registry.add(NDUItems.SEARING_COD, 3200);
    }

   // public static final CreativeModeTab defaultBuilder = FabricItemGroupBuilder.build(prefix("netherdepthsupgrade"), () -> new ItemStack(NDUItems.SOUL_SUCKER_LEATHER));
   // public static final CreativeModeTab fishBuilder = FabricItemGroupBuilder.build(prefix("netherdepthsupgrade_fish"), () -> new ItemStack(NDUItems.SEARING_COD));


}
