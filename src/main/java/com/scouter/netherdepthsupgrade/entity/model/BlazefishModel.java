package com.scouter.netherdepthsupgrade.entity.model;

import com.scouter.netherdepthsupgrade.entity.entities.BlazefishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class BlazefishModel extends DefaultedEntityGeoModel<BlazefishEntity> {
    public BlazefishModel() {
        super(prefix("blazefish"));
    }
}
