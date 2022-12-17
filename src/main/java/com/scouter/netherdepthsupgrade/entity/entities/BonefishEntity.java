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
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BonefishEntity extends AbstractLavaSchoolingFish implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public BonefishEntity(EntityType<? extends AbstractLavaSchoolingFish> p_27461_, Level p_27462_) {
        super(p_27461_, p_27462_);
    }
    public static final RawAnimation MOVING_BONEFISH = RawAnimation.begin().thenLoop("bonefish.moving");
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.BONEFISH_BUCKET);
    }

    @Override
    public void tick() {
        super.tick();
        this.removeAllEffects();
    }
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "bonefish.moving", 0, state -> state.setAndContinue(MOVING_BONEFISH)));
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
