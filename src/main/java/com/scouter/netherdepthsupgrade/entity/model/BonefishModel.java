package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.BonefishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class BonefishModel extends AnimatedTickingGeoModel<BonefishEntity> {
    @Override
    public ResourceLocation getModelResource(BonefishEntity object) {
        return prefix("geo/bonefish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BonefishEntity object) {
        return prefix("textures/entity/fish/bonefish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BonefishEntity animatable) {
        return prefix("animations/bonefish.animation.json");
    }
}
