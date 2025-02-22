package com.scouter.netherdepthsupgrade.mixin.render;

import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogParameters;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(FogRenderer.class)
public class ScreenFogEffect {
    @Inject(at = @At("RETURN"), method = "setupFog", cancellable = true)
    private static void setupFog(Camera camera, FogRenderer.FogMode fogMode, Vector4f vector4f, float f, boolean bl, float g, CallbackInfoReturnable<FogParameters> cir) {
        FogParameters fogParameters = cir.getReturnValue();

        if (camera.getFluidInCamera() == FogType.LAVA) {
            Entity entity = camera.getEntity();
            if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(MobEffects.LAVA_VISION)) {
                fogParameters = new FogParameters(16.0F, 32.0F, fogParameters.shape(), fogParameters.red(), fogParameters.green(), fogParameters.blue(), fogParameters.alpha());
                cir.setReturnValue(fogParameters);
            }
        }
    }
}

