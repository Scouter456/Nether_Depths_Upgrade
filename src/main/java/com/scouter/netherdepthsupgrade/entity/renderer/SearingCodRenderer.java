package com.scouter.netherdepthsupgrade.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.scouter.netherdepthsupgrade.entity.entities.SearingCodEntity;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class SearingCodRenderer extends MobRenderer<SearingCodEntity, CodModel<SearingCodEntity>> {
    private static final ResourceLocation COD_LOCATION = prefix("textures/entity/fish/searing_cod.png");

    public SearingCodRenderer(EntityRendererProvider.Context p_173954_) {
        super(p_173954_, new CodModel<>(p_173954_.bakeLayer(ModelLayers.COD)), 0.3F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(SearingCodEntity pEntity) {
        return COD_LOCATION;
    }

    protected void setupRotations(SearingCodEntity pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!pEntityLiving.isInLava()) {
            pMatrixStack.translate((double) 0.1F, (double) 0.1F, (double) -0.1F);
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }
}
