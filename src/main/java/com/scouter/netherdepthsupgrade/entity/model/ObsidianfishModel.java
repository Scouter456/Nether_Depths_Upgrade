package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.ObsidianfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class ObsidianfishModel extends AnimatedTickingGeoModel<ObsidianfishEntity> {
    @Override
    public ResourceLocation getModelResource(ObsidianfishEntity object) {
        return prefix("geo/obsidianfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ObsidianfishEntity object) {
        return prefix("textures/entity/fish/obsidianfish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ObsidianfishEntity animatable) {
        return prefix("animations/obsidianfish.animation.json");
    }
}
