package com.scouter.netherdepthsupgrade.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.scouter.netherdepthsupgrade.entity.entities.FortressGrouperEntity;
import com.scouter.netherdepthsupgrade.entity.model.FortressGrouperModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FortressGrouperRenderer extends GeoEntityRenderer<FortressGrouperEntity> {
    public FortressGrouperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FortressGrouperModel());
        this.shadowRadius = 0.3F;
    }


    @Override
    public RenderType getRenderType(FortressGrouperEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutoutNoCull(getTextureLocation(animatable));
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack, FortressGrouperEntity animatable, BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
            poseStack.scale(2F,2F,2F);
    }

    @Override
    protected void applyRotations(FortressGrouperEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick, float nativeScale) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick, nativeScale);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!animatable.isInLava()) {
            poseStack.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}
