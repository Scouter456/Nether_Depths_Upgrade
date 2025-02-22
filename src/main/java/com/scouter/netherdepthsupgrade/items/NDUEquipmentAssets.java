package com.scouter.netherdepthsupgrade.items;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUEquipmentAssets {
    public static final  ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("equipment_asset"));

    public static final ResourceKey<EquipmentAsset> SOUL_SUCKER = EquipmentAssets.createId("soul_sucker");

    public static ResourceKey<EquipmentAsset> createId(String string) {
        return ResourceKey.create(ROOT_ID, prefix(string));
    }
}
