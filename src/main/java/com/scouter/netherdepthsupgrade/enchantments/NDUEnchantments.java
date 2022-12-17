package com.scouter.netherdepthsupgrade.enchantments;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUEnchantments {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};


    public static final Enchantment HELL_STRIDER = registerEnchantments("hell_strider",  new HellStriderEnchantment(Enchantment.Rarity.RARE, ARMOR_SLOTS));

    public static Enchantment registerEnchantments(String name, Enchantment enchantment){
        return Registry.register(Registry.ENCHANTMENT, prefix(name), enchantment);
    }
    public static void ENCHANTMENTS()
    {
        LOGGER.info("Registering Enchantments for " + NetherDepthsUpgrade.MODID);
    }
}
