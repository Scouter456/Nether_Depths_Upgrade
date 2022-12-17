package com.scouter.netherdepthsupgrade.mixin.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(ScreenEffectRenderer.class)
public class ScreenFireEffect {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isOnFire()Z"), method = "Lnet/minecraft/client/renderer/ScreenEffectRenderer;renderScreenEffect(Lnet/minecraft/client/Minecraft;Lcom/mojang/blaze3d/vertex/PoseStack;)V")
    private static boolean renderOverlaysMixin(LocalPlayer playerEntity, Minecraft minecraft,
                                               PoseStack poseStack) {
        boolean hasLavaVision = playerEntity.hasEffect(MobEffects.LAVA_VISION);
        boolean isOnFire = playerEntity.isOnFire();

        return isOnFire && !hasLavaVision;
    }
}

