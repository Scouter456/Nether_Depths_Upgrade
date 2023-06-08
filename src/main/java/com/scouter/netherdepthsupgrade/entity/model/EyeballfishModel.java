package com.scouter.netherdepthsupgrade.entity.model;

import com.scouter.netherdepthsupgrade.entity.entities.EyeballfishEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class EyeballfishModel extends DefaultedEntityGeoModel<EyeballfishEntity> {

    public EyeballfishModel() {
        super(prefix("eyeball"));
    }
}
