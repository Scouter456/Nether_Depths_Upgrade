package com.scouter.netherdepthsupgrade.config;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraftforge.common.ForgeConfigSpec;

public class NetherDepthsUpgradeConfig {



    public static final ForgeConfigSpec CONFIG_BUILDER;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        CONFIG_BUILDER = configBuilder.build();
    }

    public static ForgeConfigSpec.ConfigValue<Boolean> FISH_ENTITIES;


    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.comment(NetherDepthsUpgrade.MODID + " Config");

        builder.comment("Config for the Nether Depths Upgrade");
        FISH_ENTITIES = builder.comment("When set to true it will allow you to fish entities instead of items (default is true)").define("fish_entities", true);

    }
}
