package com.scouter.netherdepthsupgrade.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;

public class SoulSuckerArmorItem extends ArmorItem {

    public SoulSuckerArmorItem(ArmorMaterial holder, ArmorType type, Properties properties) {
        super(holder, type, properties);
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
    public void onCraftedPostProcess(ItemStack itemStack, Level level) {
        level.registryAccess().lookup(Registries.ENCHANTMENT).ifPresent(e -> {
            e.get(Enchantments.SOUL_SPEED).ifPresent(d -> {
                itemStack.enchant(d, 3);
            });
        });

    }
}
