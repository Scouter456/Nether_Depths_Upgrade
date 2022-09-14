package com.scouter.netherdepthsupgrade.items;

import com.scouter.netherdepthsupgrade.entity.entities.LavaFishingBobberEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class LavaFishingRodItem extends FishingRodItem {
    public LavaFishingRodItem(Properties p_41285_) {
        super(p_41285_);
    }

    public ActionResult<ItemStack> use(World pLevel, PlayerEntity pPlayer, Hand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (pPlayer.fishing != null) {
            if (!pLevel.isClientSide) {
                int i = pPlayer.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(i, pPlayer, (p_220000_1_) -> {
                    p_220000_1_.broadcastBreakEvent(pHand);
                });
            }

            pLevel.playSound((PlayerEntity)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        } else {
            pLevel.playSound((PlayerEntity)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            if (!pLevel.isClientSide) {
                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int j = EnchantmentHelper.getFishingLuckBonus(itemstack);
                pLevel.addFreshEntity(new LavaFishingBobberEntity(pPlayer, pLevel, j, k));
            }

            pPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        return ActionResult.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getEnchantmentValue() {
        return 5;
    }
}
