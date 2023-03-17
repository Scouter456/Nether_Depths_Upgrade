package com.scouter.netherdepthsupgrade.entity.model;

import com.scouter.netherdepthsupgrade.entity.entities.EyeballfishEntity;
import com.scouter.netherdepthsupgrade.entity.entities.FortressGrouperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class EyeballfishModel extends AnimatedTickingGeoModel<EyeballfishEntity> {
    @Override
    public ResourceLocation getModelLocation(EyeballfishEntity object) {
        return prefix("geo/eyeball.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EyeballfishEntity object) {
        return prefix("textures/entity/fish/eyeballfish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EyeballfishEntity animatable) {
        return prefix("animations/eyeball.animation.json");
    }
}
