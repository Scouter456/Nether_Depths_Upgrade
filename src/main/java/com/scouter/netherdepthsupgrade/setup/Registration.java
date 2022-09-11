package com.scouter.netherdepthsupgrade.setup;

import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
import com.scouter.netherdepthsupgrade.potion.NDUPotions;
import com.scouter.netherdepthsupgrade.sounds.NDUSounds;
import com.scouter.netherdepthsupgrade.structures.NDUStructures;
import com.scouter.netherdepthsupgrade.world.feature.NDUConfiguredFeatures;
import com.scouter.netherdepthsupgrade.world.feature.NDUFeatures;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.scouter.netherdepthsupgrade.items.NDUItems.creativeTab;
import static com.scouter.netherdepthsupgrade.items.NDUItems.creativeTabFish;


public class Registration {
    public static void init(){

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        NDUBlocks.BLOCKS.register(bus);
        NDUEntity.ENTITY_TYPES.register(bus);
        MobEffects.MOB_EFFECTS.register(bus);
        NDUItems.ITEMS.register(bus);
        NDUStructures.DEFERRED_REGISTRY_STRUCTURE.register(bus);
        NDUParticle.PARTICLE.register(bus);
        NDUEnchantments.ENCHANTMENT.register(bus);
        NDUPotions.POTIONS.register(bus);
        NDUFeatures.FEATURES.register(bus);
        NDUSounds.SOUNDS.register(bus);


    }

    public static final Item.Properties defaultBuilder() {
        return new Item.Properties().tab(creativeTab);
    }
    public static final Item.Properties fishBuilder() {return new Item.Properties().tab(creativeTabFish);}


}
