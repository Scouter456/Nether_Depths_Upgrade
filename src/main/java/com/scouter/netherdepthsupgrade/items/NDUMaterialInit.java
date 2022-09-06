package com.scouter.netherdepthsupgrade.items;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class NDUMaterialInit {
    private NDUMaterialInit(){

    }
    protected static final ArmorMaterial SOUL_SUCKER = new NDUArmorMaterial(15, new int[] { 700, 800, 1200, 100},   new int[]{1, 2, 3, 1}, 1, 1, NetherDepthsUpgrade.MODID + ":soul_sucker", SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(NDUItems.SOUL_SUCKER_LEATHER.get()));
}
