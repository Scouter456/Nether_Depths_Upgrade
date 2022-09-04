package com.scouter.netherdepthsupgrade.datagen;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.potion.NDUPotions;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.common.data.LanguageProvider;
import org.slf4j.Logger;

import java.util.function.Supplier;

public class LanguageGenerator extends LanguageProvider {
    public LanguageGenerator(DataGenerator gen){
        super(gen, NetherDepthsUpgrade.MODID, "en_us");
    }
    private static final Logger LOGGER = LogUtils.getLogger();
    @Override
    protected void addTranslations(){

        //BLOCKS
        addBlock(NDUBlocks.LAVA_SPONGE, "Lava Sponge");
        addBlock(NDUBlocks.WET_LAVA_SPONGE, "Wet Lava Sponge");
        addBlock(NDUBlocks.WARPED_KELP, "Warped Kelp");
        addBlock(NDUBlocks.WARPED_SEAGRASS, "Warped Seagrass");
        addBlock(NDUBlocks.WARPED_KELP_BLOCK, "Warped Kelp Block");
        //EGGS
        addItem(NDUItems.LAVA_PUFFERFISH_SPAWN_EGG, "Lava Pufferfish Spawn Egg");
        addItem(NDUItems.OBSIDIANFISH_SPAWN_EGG, "Obsidianfish Spawn Egg");
        addItem(NDUItems.SEARING_COD_SPAWN_EGG, "Searing Cod Spawn Egg");
        addItem(NDUItems.BONEFISH_SPAWN_EGG, "Bonefish Spawn Egg");
        addItem(NDUItems.WITHER_BONEFISH_SPAWN_EGG, "Wither Bonefish Spawn Egg");
        addItem(NDUItems.BLAZEFISH_SPAWN_EGG, "Blazefish Spawn Egg");
        addItem(NDUItems.MAGMACUBEFISH_SPAWN_EGG, "Magma Cube fish Spawn Egg");
        addItem(NDUItems.GLOWDINE_SPAWN_EGG, "Glowdine Spawn Egg");
        addItem(NDUItems.SOULSUCKER_SPAWN_EGG, "Soul Sucker Spawn Egg");
        //POTIONS
        addPotion(NDUPotions.LAVA_VISION, "Potion of Lava Vision");
        addPotion(NDUPotions.LONG_LAVA_VISION, "Potion of Lava Vision");

        //ENTITIES
        addEntityType(NDUEntity.LAVA_PUFFERFISH, "Lava Pufferfish");
        addEntityType(NDUEntity.OBSIDIAN_FISH, "Obsidianfish");
        addEntityType(NDUEntity.SEARING_COD, "Searing Cod");
        addEntityType(NDUEntity.BONEFISH, "Bonefish");
        addEntityType(NDUEntity.WITHER_BONEFISH, "Wither Bonefish");
        addEntityType(NDUEntity.BLAZEFISH, "Blazefish");
        addEntityType(NDUEntity.MAGMACUBEFISH, "Magma Cube fish");
        addEntityType(NDUEntity.GLOWDINE, "Glowdine");
        addEntityType(NDUEntity.SOULSUCKER, "Soul Sucker");

        addItem(NDUItems.LAVA_PUFFERFISH_BUCKET, "Bucket of Lava Pufferfish");
        addItem(NDUItems.LAVA_PUFFERFISH, "Lava Pufferfish");
        addItem(NDUItems.OBSIDIANFISH, "Obsidianfish");
        addItem(NDUItems.OBSIDIANFISH_BUCKET, "Bucket of Obsidianfish");
        addItem(NDUItems.SEARING_COD_BUCKET, "Bucket of Searing Cod");
        addItem(NDUItems.SEARING_COD, "Searing Cod");
        addItem(NDUItems.BONEFISH_BUCKET, "Bucket of Bonefish");
        addItem(NDUItems.BONEFISH, "Bonefish");
        addItem(NDUItems.WITHER_BONEFISH_BUCKET, "Bucket of Wither Bonefish");
        addItem(NDUItems.WITHER_BONEFISH, "Wither Bonefish");
        addItem(NDUItems.BLAZEFISH_BUCKET, "Bucket of Blazefish");
        addItem(NDUItems.BLAZEFISH, "Blazefish");
        addItem(NDUItems.MAGMACUBEFISH_BUCKET, "Bucket of Magma Cube fish");
        addItem(NDUItems.MAGMACUBEFISH, "Magma Cube fish");
        addItem(NDUItems.GLOWDINE_BUCKET, "Bucket of Glowdine");
        addItem(NDUItems.GLOWDINE, "Glowdine");
        addItem(NDUItems.SOULSUCKER_BUCKET, "Bucket of Soul Sucker");
        addItem(NDUItems.SOULSUCKER, "Soul Sucker");
        //ENCHANTMENTS
        addEnchantment(NDUEnchantments.HELL_STRIDER, "Hell Strider");

        //EFFECTS
        addEffect(MobEffects.LAVA_VISION, "Lava Vision");

        //TABS
        addTabName(NDUItems.creativeTab, "Nether Depths Upgrade");
        addTabName(NDUItems.creativeTabFish, "Nether Depths Upgrade Fish");
    }

    @Override
    public String getName() {
        return "Nether Depths Upgrade Languages: en_us";
    }

    public void addTabName(CreativeModeTab key, String name){
        add(key.getDisplayName().getString(), name);
    }

    public void add(CreativeModeTab key, String name) {
        add(key.getDisplayName().getString(), name);
    }

    public void addPotion(Supplier<? extends Potion> key, String name) {
        add(key.get(), name);
    }

    public void add(Potion key, String name) {
        add("item.minecraft.potion.effect." + key.getRegistryName().getPath(), name);
        add("item.minecraft.splash_potion.effect." + key.getRegistryName().getPath(), "Splash " + name);
        add("item.minecraft.lingering_potion.effect." + key.getRegistryName().getPath(), "Lingering " + name);
    }
}
