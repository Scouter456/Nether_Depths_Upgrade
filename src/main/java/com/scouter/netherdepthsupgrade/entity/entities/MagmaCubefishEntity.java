package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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

public class MagmaCubefishEntity extends AbstractLavaFish implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = new AnimationFactory(this);
    public MagmaCubefishEntity(EntityType<? extends AbstractLavaFish> p_27461_, World p_27462_) {
        super(p_27461_, p_27462_);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.MAGMA_CUBE_SQUISH;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.MAGMA_CUBE_DEATH_SMALL;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.MAGMA_CUBE_HURT_SMALL;
    }


    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D);
        //.add(Attributes.MOVEMENT_SPEED, 3.0D)
    }
    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.MAGMA_CUBE_JUMP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.MAGMACUBEFISH_BUCKET.get());
    }

    @Override
    public void tick() {
        super.tick();
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("magmacubefish.moving", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<MagmaCubefishEntity> controller = new AnimationController<>(this, "controller",0, this::predicate);
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
}
