package com.scouter.netherdepthsupgrade.entity.model;

import com.scouter.netherdepthsupgrade.entity.entities.FortressGrouperEntity;
import com.scouter.netherdepthsupgrade.entity.entities.NetherUrchinEntity;
import com.scouter.netherdepthsupgrade.entity.renderer.NetherUrchinRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NetherUrchinModel extends AnimatedTickingGeoModel<NetherUrchinEntity> {
    @Override
    public ResourceLocation getModelLocation(NetherUrchinEntity object) {
        return prefix("geo/netherurchin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NetherUrchinEntity object) {
        return prefix("textures/entity/netherurchin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NetherUrchinEntity animatable) {
        return prefix("animations/netherurchin.animation.json");
    }
}
