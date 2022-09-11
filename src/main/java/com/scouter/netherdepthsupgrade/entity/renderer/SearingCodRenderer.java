package com.scouter.netherdepthsupgrade.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.scouter.netherdepthsupgrade.entity.entities.SearingCodEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CodModel;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class SearingCodRenderer extends MobRenderer<SearingCodEntity, CodModel<SearingCodEntity>> {
    private static final ResourceLocation COD_LOCATION = prefix("textures/entity/fish/searing_cod.png");

    public SearingCodRenderer(EntityRendererManager p_i48864_1_) {
        super(p_i48864_1_, new CodModel<>(), 0.3F);
    }


    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(SearingCodEntity pEntity) {
        return COD_LOCATION;
    }

    protected void setupRotations(SearingCodEntity pEntityLiving, MatrixStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!pEntityLiving.isInLava()) {
            pMatrixStack.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }
}
