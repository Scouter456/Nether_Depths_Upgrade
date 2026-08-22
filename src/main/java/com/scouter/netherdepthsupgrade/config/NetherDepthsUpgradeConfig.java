package com.scouter.netherdepthsupgrade.config;

import com.mojang.datafixers.util.Pair;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;

public class NetherDepthsUpgradeConfig {
    public static SimpleConfig CONFIG;
    private static NetherDepthsUpgradeConfigProvider configs;
    public static boolean FISH_ENTITIES;

    public static void registerConfigs() {
        configs = new NetherDepthsUpgradeConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(NetherDepthsUpgrade.MODID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("fish_entities", true), "boolean");
    }

    private static void assignConfigs() {
        FISH_ENTITIES = CONFIG.getOrDefault("fish_entities", true);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}