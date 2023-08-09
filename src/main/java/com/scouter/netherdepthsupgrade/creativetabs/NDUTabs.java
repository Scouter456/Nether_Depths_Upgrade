package com.scouter.netherdepthsupgrade.creativetabs;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;
import static com.scouter.netherdepthsupgrade.items.NDUItems.LOGGER;

public class NDUTabs {


    private static final CreativeModeTab NDU_FISH = FabricItemGroup
            .builder()
            .title(Component.translatable("itemGroup.netherdepthsupgrade_fish"))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(NDUItems.LAVA_PUFFERFISH);
                entries.accept(NDUItems.OBSIDIANFISH);
                entries.accept(NDUItems.SEARING_COD);
                entries.accept(NDUItems.BONEFISH);
                entries.accept(NDUItems.WITHER_BONEFISH);
                entries.accept(NDUItems.BLAZEFISH);
                entries.accept(NDUItems.MAGMACUBEFISH);
                entries.accept(NDUItems.GLOWDINE);
                entries.accept(NDUItems.SOULSUCKER);
                entries.accept(NDUItems.LAVA_PUFFERFISH_BUCKET);
                entries.accept(NDUItems.OBSIDIANFISH_BUCKET);
                entries.accept(NDUItems.SEARING_COD_BUCKET);
                entries.accept(NDUItems.BONEFISH_BUCKET);
                entries.accept(NDUItems.WITHER_BONEFISH_BUCKET);
                entries.accept(NDUItems.BLAZEFISH_BUCKET);
                entries.accept(NDUItems.MAGMACUBEFISH_BUCKET);
                entries.accept(NDUItems.GLOWDINE_BUCKET);
                entries.accept(NDUItems.SOULSUCKER_BUCKET);
                entries.accept(NDUItems.LAVA_PUFFERFISH_SPAWN_EGG);
                entries.accept(NDUItems.OBSIDIANFISH_SPAWN_EGG);
                entries.accept(NDUItems.SEARING_COD_SPAWN_EGG);
                entries.accept(NDUItems.BONEFISH_SPAWN_EGG);
                entries.accept(NDUItems.WITHER_BONEFISH_SPAWN_EGG);
                entries.accept(NDUItems.BLAZEFISH_SPAWN_EGG);
                entries.accept(NDUItems.MAGMACUBEFISH_SPAWN_EGG);
                entries.accept(NDUItems.GLOWDINE_SPAWN_EGG);
                entries.accept(NDUItems.SOULSUCKER_SPAWN_EGG);
                entries.accept(NDUItems.FORTRESS_GROUPER);
                entries.accept(NDUItems.FORTRESS_GROUPER_BUCKET);
                entries.accept(NDUItems.FORTRESS_GROUPER_SPAWN_EGG);
                entries.accept(NDUItems.EYEBALL_FISH);
                entries.accept(NDUItems.EYEBALL_FISH_BUCKET);
                entries.accept(NDUItems.EYEBALL_FISH_SPAWN_EGG);
            })
            .icon(NDUItems.SEARING_COD::getDefaultInstance)
            .build();

    private static final CreativeModeTab NDU  = FabricItemGroup
            .builder()
            .title(Component.translatable("itemGroup.netherdepthsupgrade"))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(NDUItems.LAVA_SPONGE);
                entries.accept(NDUItems.WET_LAVA_SPONGE);
                entries.accept(NDUItems.WARPED_KELP);
                entries.accept(NDUItems.WARPED_SEAGRASS);
                entries.accept(NDUItems.WARPED_KELP_BLOCK);
                entries.accept(NDUItems.WARPED_KELP_BLOCK_CARPET);
                entries.accept(NDUItems.CRIMSON_KELP);
                entries.accept(NDUItems.CRIMSON_SEAGRASS);
                entries.accept(NDUItems.CRIMSON_KELP_BLOCK);
                entries.accept(NDUItems.CRIMSON_KELP_CARPET_BLOCK   );
                entries.accept(NDUItems.LAVA_GLASS);

                entries.accept(NDUItems.SOUL_SUCKER_LEATHER);
                entries.accept(NDUItems.FORTRESS_GROUPER_PLATE);
                entries.accept(NDUItems.EYEBALL_FISH_EYE);
                generateEnchantsForBoots(entries, NDUItems.SOUL_SUCKER_BOOTS, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                entries.accept(NDUItems.LAVA_FISHING_ROD);
            })
            .icon(NDUItems.WARPED_KELP::getDefaultInstance)
            .build();


    public static final CreativeModeTab NDU_TAB = creativeModeTab("netherdepthsupgrade", NDU);
    public static final CreativeModeTab NDU_FISH_TAB = creativeModeTab("netherdepthsupgrade_fish", NDU_FISH);
    private static CreativeModeTab creativeModeTab(String name, CreativeModeTab item) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, prefix(name), item);
    }
    private static void generateEnchantsForBoots(CreativeModeTab.Output output, Item item, CreativeModeTab.TabVisibility tabVisibility) {
        ItemStack soulsuckerBoots = new ItemStack(item);
        soulsuckerBoots.enchant(Enchantments.SOUL_SPEED, 3);
        output.accept(soulsuckerBoots, tabVisibility);
    }

    public static void TABS(){
        LOGGER.info("Registering tabs for " + NetherDepthsUpgrade.MODID);
    }
}
