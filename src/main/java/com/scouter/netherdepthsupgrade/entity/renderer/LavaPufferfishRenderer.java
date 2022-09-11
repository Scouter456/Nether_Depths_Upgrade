package com.scouter.netherdepthsupgrade.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.scouter.netherdepthsupgrade.entity.entities.LavaPufferfishEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PufferFishBigModel;
import net.minecraft.client.renderer.entity.model.PufferFishMediumModel;
import net.minecraft.client.renderer.entity.model.PufferFishSmallModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class LavaPufferfishRenderer extends MobRenderer<LavaPufferfishEntity, EntityModel<LavaPufferfishEntity>> {
    private static final ResourceLocation PUFFER_LOCATION = prefix("textures/entity/fish/lava_pufferfish.png");
    private int puffStateO = 3;
    private final PufferFishSmallModel<LavaPufferfishEntity> small = new PufferFishSmallModel<>();
    private final PufferFishMediumModel<LavaPufferfishEntity> mid = new PufferFishMediumModel<>();
    private final PufferFishBigModel<LavaPufferfishEntity> big = new PufferFishBigModel<>();

    public LavaPufferfishRenderer(EntityRendererManager p_i48863_1_) {
        super(p_i48863_1_, new PufferFishBigModel<>(), 0.2F);
        this.puffStateO = 3;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(LavaPufferfishEntity pEntity) {
        return PUFFER_LOCATION;
    }

    public void render(LavaPufferfishEntity pEntity, float pEntityYaw, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight) {
        int i = pEntity.getPuffState();
        if (i != this.puffStateO) {
            if (i == 0) {
                this.model = this.small;
            } else if (i == 1) {
                this.model = this.mid;
            } else {
                this.model = this.big;
            }
        }

        this.puffStateO = i;
        this.shadowRadius = 0.1F + 0.1F * (float)i;
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
    protected void setupRotations(LavaPufferfishEntity pEntityLiving, MatrixStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        pMatrixStack.translate(0.0D, (double)(MathHelper.cos(pAgeInTicks * 0.05F) * 0.08F), 0.0D);
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
    }

}