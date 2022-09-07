package com.scouter.netherdepthsupgrade.entity.model;

import com.scouter.netherdepthsupgrade.entity.entities.WitherBonefishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;


import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class WitherBonefishModel extends AnimatedTickingGeoModel<WitherBonefishEntity> {
    @Override
    public ResourceLocation getModelResource(WitherBonefishEntity object) {
        return prefix("geo/witherbonefish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WitherBonefishEntity object) {
        return prefix("textures/entity/fish/wither_bonefish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WitherBonefishEntity animatable) {
        return prefix("animations/witherbonefish.animation.json");
    }
}
