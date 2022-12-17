package com.scouter.netherdepthsupgrade.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.scouter.netherdepthsupgrade.entity.entities.WitherBonefishEntity;
import com.scouter.netherdepthsupgrade.entity.model.WitherBonefishModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WitherBonefishRenderer extends GeoEntityRenderer<WitherBonefishEntity> {
    public WitherBonefishRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WitherBonefishModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public RenderType getRenderType(WitherBonefishEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutoutNoCull(getTextureLocation(animatable));
    }

    @Override
    protected void applyRotations(WitherBonefishEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw,
                                  float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
        if (!entityLiving.isInLava()) {
            matrixStackIn.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }

    }
}
