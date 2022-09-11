package com.scouter.netherdepthsupgrade.items;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Supplier;

public class FishBucketItem  extends net.minecraft.item.FishBucketItem {
    private final EntityType<?> type;

    public FishBucketItem(java.util.function.Supplier<? extends EntityType<?>> fishTypeIn, Fluid p_i49022_2_, Item.Properties builder) {
        super(fishTypeIn, () ->  Fluids.LAVA ,builder);
        this.type = null;
        this.fishTypeSupplier = fishTypeIn;
    }

    @Override
    public void checkExtraContent(World p_203792_1_, ItemStack p_203792_2_, BlockPos p_203792_3_) {
        if (p_203792_1_ instanceof ServerWorld) {
            this.spawn((ServerWorld)p_203792_1_, p_203792_2_, p_203792_3_);
        }

    }
    private void spawn(ServerWorld p_205357_1_, ItemStack p_205357_2_, BlockPos p_205357_3_) {
        Entity entity = this.fishTypeSupplier.get().spawn(p_205357_1_, p_205357_2_, (PlayerEntity)null, p_205357_3_, SpawnReason.BUCKET, true, false);
        if (entity != null) {
            ((AbstractLavaFish)entity).setFromBucket(true);
        }

    }
    private final java.util.function.Supplier<? extends EntityType<?>> fishTypeSupplier;
    protected EntityType<?> getFishType() {
        return fishTypeSupplier.get();
    }

}
