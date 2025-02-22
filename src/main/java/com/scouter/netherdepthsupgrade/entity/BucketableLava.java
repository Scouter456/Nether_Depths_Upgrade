package com.scouter.netherdepthsupgrade.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.component.CustomData;
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
    static void saveDefaultDataToBucketTag(Mob pMob, ItemStack pBucket) {
        pBucket.set(DataComponents.CUSTOM_NAME, pMob.getCustomName());
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, pBucket, p_331213_ -> {
            if (pMob.isNoAi()) {
                p_331213_.putBoolean("NoAI", pMob.isNoAi());
            }

            if (pMob.isSilent()) {
                p_331213_.putBoolean("Silent", pMob.isSilent());
            }

            if (pMob.isNoGravity()) {
                p_331213_.putBoolean("NoGravity", pMob.isNoGravity());
            }

            if (pMob.hasGlowingTag()) {
                p_331213_.putBoolean("Glowing", pMob.hasGlowingTag());
            }

            if (pMob.isInvulnerable()) {
                p_331213_.putBoolean("Invulnerable", pMob.isInvulnerable());
            }

            p_331213_.putFloat("Health", pMob.getHealth());
        });
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

    static <T extends LivingEntity & BucketableLava> Optional<InteractionResult> bucketMobPickup(Player p_148829_, InteractionHand p_148830_, T p_148831_) {
        ItemStack itemstack = p_148829_.getItemInHand(p_148830_);
        if (itemstack.getItem() == Items.LAVA_BUCKET && p_148831_.isAlive()) {
            p_148831_.playSound(p_148831_.getPickupSound(), 1.0F, 1.0F);
            ItemStack itemstack1 = p_148831_.getBucketItemStack();
            p_148831_.saveToBucketTag(itemstack1);
            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, p_148829_, itemstack1, false);
            p_148829_.setItemInHand(p_148830_, itemstack2);
            Level level = p_148831_.level();
            if (!level.isClientSide) {
                //TODO add custom lava advancement
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)p_148829_, itemstack1);
            }

            p_148831_.discard();
            return Optional.of(InteractionResult.SUCCESS);
        } else {
            return Optional.empty();
        }
    }
}