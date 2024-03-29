package com.scouter.netherdepthsupgrade.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.scouter.netherdepthsupgrade.entity.entities.FortressGrouperEntity;
import com.scouter.netherdepthsupgrade.entity.entities.GlowdineEntity;
import com.scouter.netherdepthsupgrade.entity.model.FortressGrouperModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FortressGrouperRenderer extends GeoEntityRenderer<FortressGrouperEntity> {
    public FortressGrouperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FortressGrouperModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public RenderType getRenderType(FortressGrouperEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int PackedLightIn, ResourceLocation textureLocation){
        stack.scale(2f,2f,2f);
        return RenderType.entityCutoutNoCull(getTextureLocation(animatable));
    }

    @Override
    protected void applyRotations(FortressGrouperEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw,
                                  float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInLava()) {
            matrixStackIn.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }
}
