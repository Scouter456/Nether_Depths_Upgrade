package com.scouter.netherdepthsupgrade.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LavaVisionEffect extends MobEffect {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    protected LavaVisionEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int i) {
        if(livingEntity.isOnFire()){
            livingEntity.clearFire();
        }
        return super.applyEffectTick(serverLevel, livingEntity, i);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }

}
