package com.scouter.netherdepthsupgrade.entity.entities;


import com.scouter.netherdepthsupgrade.entity.AbstractLavaSchoolingFish;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SearingCodEntity extends AbstractLavaSchoolingFish {

    public SearingCodEntity(EntityType<? extends AbstractLavaSchoolingFish> p_27523_, Level p_27524_) {
        super(p_27523_, p_27524_);
    }
    public void tick() {
        super.tick();
        if(!this.isInLava()){
            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(1), this.getZ(), 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
        }
    }
    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.SEARING_COD_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.PLAYER_HURT_ON_FIRE;
    }

}
