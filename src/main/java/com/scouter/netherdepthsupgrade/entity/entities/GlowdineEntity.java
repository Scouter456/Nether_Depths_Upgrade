package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaSchoolingFish;
import com.scouter.netherdepthsupgrade.entity.ai.LavaFishJumpGoal;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
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

public class GlowdineEntity extends AbstractLavaSchoolingFish implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final RawAnimation MOVING_GLOWDINE = RawAnimation.begin().thenLoop("glowdine.moving");
    public GlowdineEntity(EntityType<? extends AbstractLavaSchoolingFish> p_27523_, Level p_27524_) {
        super(p_27523_, p_27524_);
    }

    public void aiStep() {
        super.aiStep();
        this.level.addParticle(NDUParticle.GLOWDINE_PARTICLE, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LavaFishJumpGoal(this,4));
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.GLOWDINE_BUCKET);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.GLOW_SQUID_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GLOW_SQUID_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GLOW_SQUID_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.GLOW_SQUID_SQUIRT;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "glowdine.moving", 0, state -> state.setAndContinue(MOVING_GLOWDINE)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    @Override
    public int getMaxSchoolSize() {
        return 20;
    }

}
