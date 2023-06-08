package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.ObsidianfishEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class ObsidianfishModel extends DefaultedEntityGeoModel<ObsidianfishEntity> {

    public ObsidianfishModel() {
        super(prefix("obsidianfish"));
    }
}
