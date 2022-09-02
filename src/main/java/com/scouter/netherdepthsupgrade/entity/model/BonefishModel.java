package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.BonefishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class BonefishModel extends AnimatedTickingGeoModel<BonefishEntity> {
    @Override
    public ResourceLocation getModelLocation(BonefishEntity object) {
        return prefix("geo/bonefish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BonefishEntity object) {
        return prefix("textures/entity/fish/bonefish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BonefishEntity animatable) {
        return prefix("animations/bonefish.animation.json");
    }
}
