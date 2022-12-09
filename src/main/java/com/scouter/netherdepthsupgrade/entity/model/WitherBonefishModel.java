package com.scouter.netherdepthsupgrade.entity.model;

import com.scouter.netherdepthsupgrade.entity.entities.WitherBonefishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class WitherBonefishModel extends DefaultedEntityGeoModel<WitherBonefishEntity> {

    public WitherBonefishModel() {
        super(prefix("witherbonefish"));
    }
}
