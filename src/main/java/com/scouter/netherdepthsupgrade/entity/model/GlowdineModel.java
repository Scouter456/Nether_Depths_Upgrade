package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.GlowdineEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;


import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class GlowdineModel extends AnimatedTickingGeoModel<GlowdineEntity> {
    @Override
    public ResourceLocation getModelLocation(GlowdineEntity object) {
        return prefix("geo/glowdine.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GlowdineEntity object) {
        return prefix("textures/entity/fish/glowdine.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GlowdineEntity animatable) {
        return prefix("animations/glowdine.animation.json");
    }
}
