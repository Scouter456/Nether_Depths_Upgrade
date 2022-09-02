package com.scouter.netherdepthsupgrade.enchantments;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDUEnchantments {
    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<Enchantment> HELL_STRIDER = ENCHANTMENT.register("hell_strider", () -> new HellStriderEnchantment(Enchantment.Rarity.RARE, ARMOR_SLOTS));
}
