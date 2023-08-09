package com.scouter.netherdepthsupgrade.advancements;

import net.minecraft.advancements.CriteriaTriggers;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUAdvancementTriggers {
    public static PlaceWetLavaSpongeTrigger PLACE_WET_LAVA_SPONGE = new PlaceWetLavaSpongeTrigger(prefix("placed_block"));

    public static void init(){
        CriteriaTriggers.register(PLACE_WET_LAVA_SPONGE);
    }

}
