package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaSchoolingFish;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GlowdineEntity extends AbstractLavaSchoolingFish implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = new AnimationFactory(this);

    public GlowdineEntity(EntityType<? extends AbstractLavaSchoolingFish> p_27523_, Level p_27524_) {
        super(p_27523_, p_27524_);
    }

    public void aiStep() {
        super.aiStep();
        this.level.addParticle(NDUParticle.GLOWDINE_PARTICLE.get(), this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.GLOWDINE_BUCKET.get());
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

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("glowdine.moving", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<GlowdineEntity> controller = new AnimationController<>(this, "controller",0, this::predicate);
        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }


    @Override
    public int getMaxSchoolSize() {
        return 20;
    }

}
