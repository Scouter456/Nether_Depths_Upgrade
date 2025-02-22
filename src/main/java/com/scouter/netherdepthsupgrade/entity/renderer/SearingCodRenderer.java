package com.scouter.netherdepthsupgrade.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.scouter.netherdepthsupgrade.entity.entities.SearingCodEntity;
import com.scouter.netherdepthsupgrade.entity.model.SearingCodModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class SearingCodRenderer extends GeoEntityRenderer<SearingCodEntity> {

    private static final ResourceLocation COD_LOCATION = prefix("textures/entity/searing_cod.png");

    public SearingCodRenderer(EntityRendererProvider.Context p_173954_) {
        super(p_173954_, new SearingCodModel());
        this.shadowRadius = 0.3F;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(SearingCodEntity pEntity) {
        return COD_LOCATION;
    }



    @Override
    protected void applyRotations(SearingCodEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick, float nativeScale) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick, nativeScale);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!animatable.isInLava()) {
            poseStack.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}
