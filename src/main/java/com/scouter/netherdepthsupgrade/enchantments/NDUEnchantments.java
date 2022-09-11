package com.scouter.netherdepthsupgrade.enchantments;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NDUEnchantments {
    private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};

    public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<Enchantment> HELL_STRIDER = ENCHANTMENT.register("hell_strider", () -> new HellStriderEnchantment(Enchantment.Rarity.RARE, ARMOR_SLOTS));
}
