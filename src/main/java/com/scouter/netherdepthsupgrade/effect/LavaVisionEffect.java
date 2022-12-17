package com.scouter.netherdepthsupgrade.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LavaVisionEffect extends MobEffect {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    protected LavaVisionEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        if(livingEntity.isOnFire()){
            livingEntity.clearFire();

        }
        super.applyEffectTick(livingEntity, amplifier);
    }


    @Override
    public boolean isDurationEffectTick(int p_19631_, int p_19632_) {
        return true;
    }
}
