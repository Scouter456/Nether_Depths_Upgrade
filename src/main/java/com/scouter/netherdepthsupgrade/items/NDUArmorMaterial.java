package com.scouter.netherdepthsupgrade.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class NDUArmorMaterial implements IArmorMaterial {
    private final int enchantability;
    private final int[] durability, damageReduction;
    private final float knockbackResistance, toughness;
    private final String name;
    private final SoundEvent equipSound;
    private final Supplier<Ingredient> repairMaterial;

    public NDUArmorMaterial(int enchantability, int[] durability, int[] damageReduction,
                             float knockbackResistance, float toughness, String name, SoundEvent equipSound,
                             Supplier<Ingredient> repairMaterial) {
        this.enchantability = enchantability;
        this.durability = durability;
        this.damageReduction = damageReduction;
        this.knockbackResistance = knockbackResistance;
        this.toughness = toughness;
        this.name = name;
        this.equipSound = equipSound;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.damageReduction[slot.getIndex()];
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return this.durability[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
}
