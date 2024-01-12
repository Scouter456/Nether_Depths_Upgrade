package com.scouter.netherdepthsupgrade.items;

import com.scouter.netherdepthsupgrade.entity.BucketableLava;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class FishBucketItem  extends MobBucketItem {
    public FishBucketItem(Supplier<? extends EntityType<?>> fishTypeIn, Fluid fluid, Properties builder) {
        super(fishTypeIn, () -> fluid, () -> SoundEvents.BUCKET_EMPTY_FISH, builder.stacksTo(1));
    }

    @Override
    public void spawn(ServerLevel pServerLevel, ItemStack pBucketedMobStack, BlockPos pPos) {
        Entity entity = getFishType().spawn(pServerLevel, pBucketedMobStack, (Player)null, pPos, MobSpawnType.BUCKET, true, false);
        if (entity instanceof BucketableLava bucketable) {
            bucketable.loadFromBucketTag(pBucketedMobStack.getOrCreateTag());
            bucketable.setFromBucket(true);
        }
    }
}
