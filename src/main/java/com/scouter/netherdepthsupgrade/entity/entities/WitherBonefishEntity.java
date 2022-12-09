package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaSchoolingFish;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WitherBonefishEntity extends AbstractLavaSchoolingFish implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public WitherBonefishEntity(EntityType<? extends AbstractLavaSchoolingFish> p_27461_, Level p_27462_) {
        super(p_27461_, p_27462_);
    }
    public static final RawAnimation MOVING_WITHERBONEFISH = RawAnimation.begin().thenLoop("witherbonefish.moving");
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_SKELETON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_SKELETON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.WITHER_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.WITHER_SKELETON_HURT;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.WITHER_BONEFISH_BUCKET.get());
    }

    @Override
    public void tick() {
        super.tick();
        this.removeAllEffects();
    }
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "witherbonefish.moving", 0, state -> state.setAndContinue(MOVING_WITHERBONEFISH)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public int getMaxSchoolSize() {
        return 6;
    }
}
