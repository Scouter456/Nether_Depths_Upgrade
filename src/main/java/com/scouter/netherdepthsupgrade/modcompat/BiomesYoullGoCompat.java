package com.scouter.netherdepthsupgrade.modcompat;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class BiomesYoullGoCompat {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void setupCompatPreInit(){
        LOGGER.info("Setting up compat for Biomes You'll go");
        ModChecker.biomesYoullGoPresent = true;
    }
}
