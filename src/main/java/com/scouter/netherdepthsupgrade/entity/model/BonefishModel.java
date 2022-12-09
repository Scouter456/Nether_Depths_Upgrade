package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.BonefishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class BonefishModel extends DefaultedEntityGeoModel<BonefishEntity> {

    public BonefishModel() {
        super(prefix("bonefish"));
    }
}
