package com.scouter.netherdepthsupgrade.setup;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.advancements.NDUAdvancementTriggers;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.blocks.entity.NDUBlockEntities;
import com.scouter.netherdepthsupgrade.config.NetherDepthsUpgradeConfig;
import com.scouter.netherdepthsupgrade.creativetabs.NDUTabs;
import com.scouter.netherdepthsupgrade.datacomponents.NDUDataComponents;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
import com.scouter.netherdepthsupgrade.potion.NDUPotions;
import com.scouter.netherdepthsupgrade.structures.NDUStructures;
import com.scouter.netherdepthsupgrade.world.feature.NDUFeatures;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;


public class Registration {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void init(){

        IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();
        ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, NetherDepthsUpgradeConfig.CONFIG_BUILDER);
        NDUDataComponents.ENCHANT_COMPONENT.register(bus);
        NDUBlocks.BLOCKS.register(bus);
        NDUBlockEntities.BLOCK_ENTITIES.register(bus);
        NDUEntity.ENTITY_TYPES.register(bus);
        MobEffects.MOB_EFFECTS.register(bus);
        NDUItems.ITEMS.register(bus);
        NDUTabs.TABS.register(bus);
        NDUStructures.STRUCTURES.register(bus);
        NDUParticle.PARTICLE.register(bus);
        NDUPotions.POTIONS.register(bus);
        NDUFeatures.FEATURES.register(bus);
        NDUAdvancementTriggers.TRIGGERS.register(bus);

        //if(ModChecker.farmersDelightPresent){
        //    FarmersDelightCompat.ITEMS_FARMERS_DELIGHT.register(bus);
        //    FarmersDelightCompat.BLOCKS_FARMERS_DELIGHT.register(bus);
        //    FarmersDelightCompat.TABS.register(bus);
        //}

    }
}
