package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.MagmaCubefishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;


import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class MagmaCubefishModel extends AnimatedTickingGeoModel<MagmaCubefishEntity> {
    @Override
    public ResourceLocation getModelLocation(MagmaCubefishEntity object) {
        return prefix("geo/magmacubefish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MagmaCubefishEntity object) {
        return prefix("textures/entity/fish/magmacubefish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MagmaCubefishEntity animatable) {
        return prefix("animations/magmacubefish.animation.json");
    }
}
