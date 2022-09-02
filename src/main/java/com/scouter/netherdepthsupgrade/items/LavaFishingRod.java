package com.scouter.netherdepthsupgrade.items;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.entity.LavaFishingHook;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.slf4j.Logger;

public class LavaFishingRod extends FishingRodItem implements Vanishable {
    private static final Logger LOGGER = LogUtils.getLogger();
    public LavaFishingRod(Properties p_41285_) {
        super(p_41285_);
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     */
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (pPlayer.fishing != null) {
            if (!pLevel.isClientSide) {
                int i = pPlayer.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(i, pPlayer, (p_41288_) -> {
                    p_41288_.broadcastBreakEvent(pHand);
                });
            }

            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            pLevel.gameEvent(pPlayer, GameEvent.FISHING_ROD_REEL_IN, pPlayer);
        } else {
            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!pLevel.isClientSide) {
                LOGGER.info("I am here 231312");
                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int j = EnchantmentHelper.getFishingLuckBonus(itemstack);
                LOGGER.info("entity" +  pLevel.addFreshEntity(new LavaFishingHook(pPlayer, pLevel, j, k)));
                pLevel.addFreshEntity(new LavaFishingHook(pPlayer, pLevel, j, k));
            }

            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            pLevel.gameEvent(pPlayer, GameEvent.FISHING_ROD_CAST, pPlayer);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getEnchantmentValue() {
        return 1;
    }
}
