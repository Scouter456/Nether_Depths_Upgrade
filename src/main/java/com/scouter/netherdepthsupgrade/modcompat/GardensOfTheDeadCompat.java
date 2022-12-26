package com.scouter.netherdepthsupgrade.modcompat;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class GardensOfTheDeadCompat {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void setupCompatPreInit(){
        LOGGER.info("Setting up compat for Gardens of the dead");
        ModChecker.gardensofTheDeadPresent = true;
    }
}
