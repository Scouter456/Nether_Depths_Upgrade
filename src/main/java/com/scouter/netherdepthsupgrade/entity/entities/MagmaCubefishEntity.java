package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
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
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MagmaCubefishEntity extends AbstractLavaFish implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final RawAnimation MOVING_MAGMACUBEFISH = RawAnimation.begin().thenLoop("magmacubefish.moving");
    public MagmaCubefishEntity(EntityType<? extends AbstractLavaFish> p_27461_, Level p_27462_) {
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


    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D)
                //.add(Attributes.MOVEMENT_SPEED, 3.0D)
                .build();
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
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "magmacubefish.moving", 0, state -> state.setAndContinue(MOVING_MAGMACUBEFISH)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
