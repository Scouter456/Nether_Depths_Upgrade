package com.scouter.netherdepthsupgrade.items;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class SoulSuckerArmorItem extends ArmorItem {
    public SoulSuckerArmorItem(IArmorMaterial pMaterial, EquipmentSlotType pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, World level, PlayerEntity player){
        if(player.inventory.getArmor(0).equals(NDUItems.SOUL_SUCKER_BOOTS.get())){
            if(level.getBlockState(player.blockPosition()).is(Blocks.SOUL_SAND)){
                level.setBlock(player.blockPosition(), Blocks.SOUL_SOIL.defaultBlockState(), 3);
            }
        }
    }

    @Override
    public void onCraftedBy(ItemStack pStack, World pLevel, PlayerEntity pPlayer) {
        pStack.enchant(Enchantments.SOUL_SPEED, 3);
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (allowdedIn(tab)) {
            ItemStack istack = new ItemStack(this);
            switch (this.getSlot()) {
                case HEAD:
                    break;
                case CHEST:
                case LEGS:
                    break;
                case FEET:
                    istack.enchant(Enchantments.SOUL_SPEED, 3);
                    break;
                default:
                    break;
            }
            list.add(istack);
        }
    }
}
