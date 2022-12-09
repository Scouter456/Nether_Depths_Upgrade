package com.scouter.netherdepthsupgrade.entity.model;


import com.scouter.netherdepthsupgrade.entity.entities.GlowdineEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class GlowdineModel extends DefaultedEntityGeoModel<GlowdineEntity> {

    public GlowdineModel() {
        super(prefix("glowdine"));
    }
}
