package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.SoulSuckerEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class SoulSuckerModel extends DefaultedEntityGeoModel<SoulSuckerEntity> {

    public SoulSuckerModel() {
        super(prefix("soulsucker"));
    }
}

