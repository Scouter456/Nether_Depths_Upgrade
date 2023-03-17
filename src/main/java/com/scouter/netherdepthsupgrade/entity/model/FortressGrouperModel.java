package com.scouter.netherdepthsupgrade.entity.model;

import com.scouter.netherdepthsupgrade.entity.entities.FortressGrouperEntity;
import com.scouter.netherdepthsupgrade.entity.entities.GlowdineEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class FortressGrouperModel extends AnimatedTickingGeoModel<FortressGrouperEntity> {
    @Override
    public ResourceLocation getModelLocation(FortressGrouperEntity object) {
        return prefix("geo/fortressgrouper.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FortressGrouperEntity object) {
        return prefix("textures/entity/fish/fortressgrouper.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FortressGrouperEntity animatable) {
        return prefix("animations/fortressgrouper.animation.json");
    }
}
