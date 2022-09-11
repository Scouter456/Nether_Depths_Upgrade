package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaSchoolingFish;
import com.scouter.netherdepthsupgrade.entity.ai.LavaFishJumpGoal;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
import com.scouter.netherdepthsupgrade.sounds.NDUSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
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

    public GlowdineEntity(EntityType<? extends AbstractLavaSchoolingFish> p_27523_, World p_27524_) {
        super(p_27523_, p_27524_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LavaFishJumpGoal(this,4));
    }

    public void aiStep() {
        super.aiStep();
        this.level.addParticle(NDUParticle.GLOWDINE_PARTICLE.get(), this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.GLOWDINE_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return NDUSounds.GLOWDINE_AMBIENT.get();
    }

    protected SoundEvent getDeathSound() {
        return NDUSounds.GLOWDINE_DEATH.get();
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return NDUSounds.GLOWDINE_HURT.get();
    }

    protected SoundEvent getFlopSound() {
        return NDUSounds.GLOWDINE_SQUIRT.get();
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
