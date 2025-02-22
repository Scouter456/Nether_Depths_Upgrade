package com.scouter.netherdepthsupgrade.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.scouter.netherdepthsupgrade.entity.entities.LavaPufferfishEntity;
import com.scouter.netherdepthsupgrade.entity.model.LavaPufferfishModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LavaPufferfishRenderer extends GeoEntityRenderer<LavaPufferfishEntity> {

    private int puffStateO = 3;

    public LavaPufferfishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LavaPufferfishModel());
        this.shadowRadius = 0.2F;

    }

    @Override
    public void renderFinal(PoseStack poseStack, LavaPufferfishEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay, int renderColor) {
        int i = animatable.getPuffState();
        this.puffStateO = i;
        this.shadowRadius = 0.1F + 0.1F * (float)i;
        super.renderFinal(poseStack, animatable, model, bufferSource, buffer, partialTick, packedLight, packedOverlay, renderColor);
    }



    @Override
    protected void applyRotations(LavaPufferfishEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick, float nativeScale) {
        poseStack.translate(0.0D, (double)(Mth.cos(ageInTicks * 0.05F) * 0.08F), 0.0D);
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick, nativeScale);
    }
}