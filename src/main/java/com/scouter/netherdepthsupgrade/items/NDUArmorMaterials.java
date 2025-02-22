package com.scouter.netherdepthsupgrade.items;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;

public class NDUArmorMaterials {

    public static final ArmorMaterial SOUL_SUCKER = new ArmorMaterial(
            5,
            Util.make(new EnumMap<>(ArmorType.class), p_323384_ -> {
                p_323384_.put(ArmorType.BOOTS, 1);
                p_323384_.put(ArmorType.LEGGINGS, 2);
                p_323384_.put(ArmorType.CHESTPLATE, 3);
                p_323384_.put(ArmorType.HELMET, 1);
                p_323384_.put(ArmorType.BODY, 3);
            }),
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            1F, 1F,
            ItemTags.REPAIRS_LEATHER_ARMOR, //todo change this one
            NDUEquipmentAssets.SOUL_SUCKER
    );

}
