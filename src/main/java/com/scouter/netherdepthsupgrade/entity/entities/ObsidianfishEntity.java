package com.scouter.netherdepthsupgrade.entity.entities;


import com.scouter.netherdepthsupgrade.entity.AbstractLavaSchoolingFish;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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

public class ObsidianfishEntity extends AbstractLavaSchoolingFish implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = new AnimationFactory(this);
    public ObsidianfishEntity(EntityType<? extends AbstractLavaSchoolingFish> p_27461_, Level p_27462_) {
        super(p_27461_, p_27462_);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ANVIL_HIT;
    }


    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.ARMOR, 5.0D)
                //.add(Attributes.MOVEMENT_SPEED, 3.0D)
                .build();
    }
    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ANVIL_BREAK;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.OBSIDIANFISH_BUCKET.get());
    }

    @Override
    public void tick() {
        super.tick();
        if(this.isAffectedByPotions()){
            this.removeAllEffects();
        }
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("obsidianfish.moving", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<ObsidianfishEntity> controller = new AnimationController<>(this, "controller",0, this::predicate);
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
        return 6;
    }
}
