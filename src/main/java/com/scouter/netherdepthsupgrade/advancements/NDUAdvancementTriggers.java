package com.scouter.netherdepthsupgrade.advancements;

import net.minecraft.advancements.CriteriaTriggers;

public class NDUAdvancementTriggers {
    public static PlaceWetLavaSpongeTrigger PLACE_WET_LAVA_SPONGE = new PlaceWetLavaSpongeTrigger();

    public static void init(){
        CriteriaTriggers.register(PLACE_WET_LAVA_SPONGE);
    }

}
