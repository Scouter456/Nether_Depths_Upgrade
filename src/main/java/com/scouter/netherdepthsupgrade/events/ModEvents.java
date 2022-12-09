package com.scouter.netherdepthsupgrade.events;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.items.SoulSuckerArmorItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

@Mod.EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    public static void registerCreativeTabs(CreativeModeTabEvent.Register event){
        event.registerCreativeModeTab(prefix("netherdepthsupgrade_fish"),
                e -> e.icon(() -> new ItemStack(NDUItems.SEARING_COD.get()))
                        .title(Component.translatable("itemGroup.netherdepthsupgrade_fish"))
                        .displayItems((enabledFeatures, entries, operatorEnabled) -> {
                            entries.accept(NDUItems.LAVA_PUFFERFISH.get());
                            entries.accept(NDUItems.OBSIDIANFISH.get());
                            entries.accept(NDUItems.SEARING_COD.get());
                            entries.accept(NDUItems.BONEFISH.get());
                            entries.accept(NDUItems.WITHER_BONEFISH.get());
                            entries.accept(NDUItems.BLAZEFISH.get());
                            entries.accept(NDUItems.MAGMACUBEFISH.get());
                            entries.accept(NDUItems.GLOWDINE.get());
                            entries.accept(NDUItems.SOULSUCKER.get());
                            entries.accept(NDUItems.LAVA_PUFFERFISH_BUCKET.get());
                            entries.accept(NDUItems.OBSIDIANFISH_BUCKET.get());
                            entries.accept(NDUItems.SEARING_COD_BUCKET.get());
                            entries.accept(NDUItems.BONEFISH_BUCKET.get());
                            entries.accept(NDUItems.WITHER_BONEFISH_BUCKET.get());
                            entries.accept(NDUItems.BLAZEFISH_BUCKET.get());
                            entries.accept(NDUItems.MAGMACUBEFISH_BUCKET.get());
                            entries.accept(NDUItems.GLOWDINE_BUCKET.get());
                            entries.accept(NDUItems.SOULSUCKER_BUCKET.get());
                            entries.accept(NDUItems.LAVA_PUFFERFISH_SPAWN_EGG.get());
                            entries.accept(NDUItems.OBSIDIANFISH_SPAWN_EGG.get());
                            entries.accept(NDUItems.SEARING_COD_SPAWN_EGG.get());
                            entries.accept(NDUItems.BONEFISH_SPAWN_EGG.get());
                            entries.accept(NDUItems.WITHER_BONEFISH_SPAWN_EGG.get());
                            entries.accept(NDUItems.BLAZEFISH_SPAWN_EGG.get());
                            entries.accept(NDUItems.MAGMACUBEFISH_SPAWN_EGG.get());
                            entries.accept(NDUItems.GLOWDINE_SPAWN_EGG.get());
                            entries.accept(NDUItems.SOULSUCKER_SPAWN_EGG.get());
                        }));

        event.registerCreativeModeTab(prefix("netherdepthsupgrade"),
                e -> e.icon(() -> new ItemStack(NDUItems.WARPED_KELP.get()))
                        .title(Component.translatable("itemGroup.netherdepthsupgrade"))
                        .displayItems((enabledFeatures, entries, operatorEnabled) -> {
                            entries.accept(NDUItems.LAVA_SPONGE.get());
                            entries.accept(NDUItems.WET_LAVA_SPONGE.get());
                            entries.accept(NDUItems.WARPED_KELP.get());
                            entries.accept(NDUItems.WARPED_SEAGRASS.get());
                            entries.accept(NDUItems.WARPED_KELP_BLOCK.get());
                            entries.accept(NDUItems.SOUL_SUCKER_LEATHER.get());
                            generateEnchantsForBoots(entries, NDUItems.SOUL_SUCKER_BOOTS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                            entries.accept(NDUItems.LAVA_FISHING_ROD.get());
                        }));


    }

    private static void generateEnchantsForBoots(CreativeModeTab.Output output, Item item, CreativeModeTab.TabVisibility tabVisibility) {
        ItemStack soulsuckerBoots = new ItemStack(item);
        soulsuckerBoots.enchant(Enchantments.SOUL_SPEED, 3);
        output.accept(soulsuckerBoots, tabVisibility);
    }


}