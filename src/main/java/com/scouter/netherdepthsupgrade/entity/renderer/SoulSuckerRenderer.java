package com.scouter.netherdepthsupgrade.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.scouter.netherdepthsupgrade.entity.entities.SoulSuckerEntity;
import com.scouter.netherdepthsupgrade.entity.model.SoulSuckerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SoulSuckerRenderer extends GeoEntityRenderer<SoulSuckerEntity> {
    public SoulSuckerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SoulSuckerModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public RenderType getRenderType(SoulSuckerEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutoutNoCull(getTextureLocation(animatable));
    }


    @Override
    protected void applyRotations(SoulSuckerEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick, float nativeScale) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick, nativeScale);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!animatable.isInLava()) {
            poseStack.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}
