package com.scouter.netherdepthsupgrade.entity.model;

import com.scouter.netherdepthsupgrade.entity.entities.FortressGrouperEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class FortressGrouperModel extends DefaultedEntityGeoModel<FortressGrouperEntity> {

    public FortressGrouperModel() {
        super(prefix("fortressgrouper"));
    }
}
