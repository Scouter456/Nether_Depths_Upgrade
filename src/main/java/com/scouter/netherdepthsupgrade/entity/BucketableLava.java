package com.scouter.netherdepthsupgrade.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Optional;

public interface BucketableLava {
    boolean fromBucket();

    void setFromBucket(boolean p_148834_);

    void saveToBucketTag(ItemStack p_148833_);

    void loadFromBucketTag(CompoundTag p_148832_);

    ItemStack getBucketItemStack();

    SoundEvent getPickupSound();

    /** @deprecated */
    @Deprecated
    static void saveDefaultDataToBucketTag(Mob p_148823_, ItemStack p_148824_) {
        CompoundTag compoundtag = p_148824_.getOrCreateTag();
        if (p_148823_.hasCustomName()) {
            p_148824_.setHoverName(p_148823_.getCustomName());
        }

        if (p_148823_.isNoAi()) {
            compoundtag.putBoolean("NoAI", p_148823_.isNoAi());
        }

        if (p_148823_.isSilent()) {
            compoundtag.putBoolean("Silent", p_148823_.isSilent());
        }

        if (p_148823_.isNoGravity()) {
            compoundtag.putBoolean("NoGravity", p_148823_.isNoGravity());
        }

        if (p_148823_.hasGlowingTag()) {
            compoundtag.putBoolean("Glowing", p_148823_.hasGlowingTag());
        }

        if (p_148823_.isInvulnerable()) {
            compoundtag.putBoolean("Invulnerable", p_148823_.isInvulnerable());
        }

        compoundtag.putFloat("Health", p_148823_.getHealth());
    }

    /** @deprecated */
    @Deprecated
    static void loadDefaultDataFromBucketTag(Mob p_148826_, CompoundTag p_148827_) {
        if (p_148827_.contains("NoAI")) {
            p_148826_.setNoAi(p_148827_.getBoolean("NoAI"));
        }

        if (p_148827_.contains("Silent")) {
            p_148826_.setSilent(p_148827_.getBoolean("Silent"));
        }

        if (p_148827_.contains("NoGravity")) {
            p_148826_.setNoGravity(p_148827_.getBoolean("NoGravity"));
        }

        if (p_148827_.contains("Glowing")) {
            p_148826_.setGlowingTag(p_148827_.getBoolean("Glowing"));
        }

        if (p_148827_.contains("Invulnerable")) {
            p_148826_.setInvulnerable(p_148827_.getBoolean("Invulnerable"));
        }

        if (p_148827_.contains("Health", 99)) {
            p_148826_.setHealth(p_148827_.getFloat("Health"));
        }

    }

    static <T extends LivingEntity & BucketableLava> Optional<InteractionResult> bucketMobPickup(Player player, InteractionHand hand, T entity) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() == Items.LAVA_BUCKET && entity.isAlive()) {
            entity.playSound(entity.getPickupSound(), 1.0F, 1.0F);
            ItemStack itemstack1 = entity.getBucketItemStack();
            entity.saveToBucketTag(itemstack1);
            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, itemstack1, false);
            player.setItemInHand(hand, itemstack2);
            Level level = entity.level;
            if (!level.isClientSide) {
                //TODO add custom lava advancement
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, itemstack1);
            }

            entity.discard();
            return Optional.of(InteractionResult.sidedSuccess(level.isClientSide));
        } else {
            return Optional.empty();
        }
    }
}