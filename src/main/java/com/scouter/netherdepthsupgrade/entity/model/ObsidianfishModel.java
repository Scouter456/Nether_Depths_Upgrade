package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.ObsidianfishEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class ObsidianfishModel extends AnimatedTickingGeoModel<ObsidianfishEntity> {
    @Override
    public ResourceLocation getModelLocation(ObsidianfishEntity object) {
        return prefix("geo/obsidianfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ObsidianfishEntity object) {
        return prefix("textures/entity/fish/obsidianfish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ObsidianfishEntity animatable) {
        return prefix("animations/obsidianfish.animation.json");
    }
}
