package com.scouter.netherdepthsupgrade.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class SoulSuckerArmorItem extends ArmorItem {
    public SoulSuckerArmorItem(ArmorMaterial pMaterial, ArmorItem.Type pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }
/*
    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (livingEntity instanceof Player player) {
            {
                if (player.getInventory().getArmor(0).is(NDUItems.SOUL_SUCKER_BOOTS)) {
                    if (level.getBlockState(player.getOnPos()).is(Blocks.SOUL_SAND)) {
                        level.setBlock(player.getOnPos(), Blocks.SOUL_SOIL.defaultBlockState(), 3);
                    }
                }
            }
            super.onUseTick(level, livingEntity, stack, remainingUseDuration);
        }
    }

*/
    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        pStack.enchant(Enchantments.SOUL_SPEED, 3);
    }
}
