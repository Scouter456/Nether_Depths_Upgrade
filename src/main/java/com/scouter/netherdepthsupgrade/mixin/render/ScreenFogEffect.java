package com.scouter.netherdepthsupgrade.mixin.render;

import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(FogRenderer.class)
public class ScreenFogEffect {
    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogStart(F)V", shift = At.Shift.BEFORE), method = "setupFog", locals = LocalCapture.CAPTURE_FAILHARD)
    private static void setupFog(Camera camera, FogRenderer.FogMode fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci, FogType cameraSubmersionType, Entity entity, FogRenderer.FogData fogParameters) {
        if (cameraSubmersionType == FogType.LAVA) {
            if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(MobEffects.LAVA_VISION)) {
                fogParameters.start = 16.0F;
                fogParameters.end = 32.0F;
            }
        }
    }
}
