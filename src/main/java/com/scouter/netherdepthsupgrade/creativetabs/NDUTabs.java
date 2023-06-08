package com.scouter.netherdepthsupgrade.creativetabs;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.items.NDUFoods;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.setup.Registration;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDUTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NetherDepthsUpgrade.MODID);

    private static final CreativeModeTab NDU_FISH = new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 9)
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .title(Component.translatable("itemGroup.netherdepthsupgrade_fish"))
            .icon(() -> new ItemStack(NDUItems.SEARING_COD.get()))
            .displayItems((d, entries) ->{
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
                entries.accept(NDUItems.FORTRESS_GROUPER.get());
                entries.accept(NDUItems.FORTRESS_GROUPER_BUCKET.get());
                entries.accept(NDUItems.FORTRESS_GROUPER_SPAWN_EGG.get());
                entries.accept(NDUItems.EYEBALL_FISH.get());
                entries.accept(NDUItems.EYEBALL_FISH_BUCKET.get());
                entries.accept(NDUItems.EYEBALL_FISH_SPAWN_EGG.get());

            })
            .build();

    private static final CreativeModeTab NDU = new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 9)
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .title(Component.translatable("itemGroup.netherdepthsupgrade"))
            .icon(() -> new ItemStack(NDUItems.WARPED_KELP.get()))
            .displayItems((d, entries) ->{
                entries.accept(NDUItems.LAVA_SPONGE.get());
                entries.accept(NDUItems.WET_LAVA_SPONGE.get());
                entries.accept(NDUItems.WARPED_KELP.get());
                entries.accept(NDUItems.WARPED_SEAGRASS.get());
                entries.accept(NDUItems.WARPED_KELP_BLOCK.get());
                entries.accept(NDUItems.WARPED_KELP_CARPET_BLOCK.get());
                entries.accept(NDUItems.LAVA_GLASS.get());

                entries.accept(NDUItems.SOUL_SUCKER_LEATHER.get());
                entries.accept(NDUItems.FORTRESS_GROUPER_PLATE.get());
                entries.accept(NDUItems.EYEBALL_FISH_EYE.get());
                generateEnchantsForBoots(entries, NDUItems.SOUL_SUCKER_BOOTS.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                entries.accept(NDUItems.LAVA_FISHING_ROD.get());
            })
            .build();


    public static final RegistryObject<CreativeModeTab> NDU_TAB = TABS.register("netherdepthsupgrade", () -> NDU);
    public static final RegistryObject<CreativeModeTab> NDU_FISH_TAB = TABS.register("netherdepthsupgrade_fish", () -> NDU_FISH);

    private static void generateEnchantsForBoots(CreativeModeTab.Output output, Item item, CreativeModeTab.TabVisibility tabVisibility) {
        ItemStack soulsuckerBoots = new ItemStack(item);
        soulsuckerBoots.enchant(Enchantments.SOUL_SPEED, 3);
        output.accept(soulsuckerBoots, tabVisibility);
    }
}
