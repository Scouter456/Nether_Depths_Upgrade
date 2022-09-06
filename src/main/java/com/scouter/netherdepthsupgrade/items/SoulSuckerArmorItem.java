package com.scouter.netherdepthsupgrade.items;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class SoulSuckerArmorItem extends ArmorItem {
    public SoulSuckerArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player){
        if(player.getInventory().getArmor(0).is(NDUItems.SOUL_SUCKER_BOOTS.get())){
            if(level.getBlockState(player.getOnPos()).is(Blocks.SOUL_SAND)){
                level.setBlock(player.getOnPos(), Blocks.SOUL_SOIL.defaultBlockState(), 3);
            }
        }
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        pStack.enchant(Enchantments.SOUL_SPEED, 3);
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
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
