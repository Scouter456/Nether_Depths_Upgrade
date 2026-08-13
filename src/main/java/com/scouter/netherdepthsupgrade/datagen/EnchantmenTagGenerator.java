package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class EnchantmenTagGenerator extends EnchantmentTagsProvider {
    public EnchantmenTagGenerator(PackOutput p_341044_, CompletableFuture<HolderLookup.Provider> p_341146_, @org.jetbrains.annotations.Nullable net.neoforged.neoforge.common.data.ExistingFileHelper existingFileHelper) {
        super(p_341044_, p_341146_, NetherDepthsUpgrade.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(EnchantmentTags.TOOLTIP_ORDER).add(NDUEnchantments.HELL_STRIDER);
        this.tag(EnchantmentTags.BOOTS_EXCLUSIVE).add(NDUEnchantments.HELL_STRIDER);
        this.tag(EnchantmentTags.NON_TREASURE).add(NDUEnchantments.HELL_STRIDER);
        this.tag(Tags.Enchantments.ENTITY_SPEED_ENHANCEMENTS).add(NDUEnchantments.HELL_STRIDER);
    }
}