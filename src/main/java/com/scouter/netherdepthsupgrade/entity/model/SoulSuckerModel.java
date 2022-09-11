package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.SoulSuckerEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class SoulSuckerModel extends AnimatedTickingGeoModel<SoulSuckerEntity> {
    @Override
    public ResourceLocation getModelLocation(SoulSuckerEntity object) {
        return prefix("geo/soulsucker.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SoulSuckerEntity object) {
        return prefix("textures/entity/fish/soulsucker.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SoulSuckerEntity animatable) {
        return prefix("animations/soulsucker.animation.json");
    }
}
