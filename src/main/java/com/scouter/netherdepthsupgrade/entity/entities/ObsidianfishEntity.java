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
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ObsidianfishEntity extends AbstractLavaSchoolingFish implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final RawAnimation MOVING_OBSIDIANFISH = RawAnimation.begin().thenLoop("obsidianfish.moving");
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
        return new ItemStack(NDUItems.OBSIDIANFISH_BUCKET);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.isAffectedByPotions()){
            this.removeAllEffects();
        }
    }
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "obsidianfish.moving", 0, state -> state.setAndContinue(MOVING_OBSIDIANFISH)));
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
