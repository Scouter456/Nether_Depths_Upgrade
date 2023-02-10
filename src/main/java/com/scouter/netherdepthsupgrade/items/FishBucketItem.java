package com.scouter.netherdepthsupgrade.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class FishBucketItem  extends MobBucketItem {
    public FishBucketItem(Supplier<? extends EntityType<?>> fishTypeIn, Fluid fluid, Properties builder) {
        super(fishTypeIn, () -> fluid, () -> SoundEvents.BUCKET_EMPTY_FISH, builder.stacksTo(1));
    }
}
