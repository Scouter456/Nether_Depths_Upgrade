package com.scouter.netherdepthsupgrade.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUTags {
    public static class Biomes {
        public static final TagKey<Biome> IS_LAVA_PUFFERFISH_BIOME = tag("is_lava_pufferfish_biome");
        public static final TagKey<Biome> IS_WITHER_BONEFISH_BIOME = tag("is_wither_bonefish_biome");
        public static final TagKey<Biome> IS_BONEFISH_BIOME = tag("is_bonefish_biome");
        public static final TagKey<Biome> IS_GLOWDINE_BIOME = tag("is_glowdine_biome");
        public static final TagKey<Biome> IS_MAGMACUBEFISH_BIOME = tag("is_magmacubefish_biome");
        public static final TagKey<Biome> IS_SOULSUCKER_BIOME = tag("is_soulsucker_biome");
        public static final TagKey<Biome> IS_BLAZEFISH_BIOME = tag("is_blazefish_biome");
        public static final TagKey<Biome> IS_SEARING_COD_BIOME = tag("is_searing_cod_biome");
        public static final TagKey<Biome> IS_OBSIDIANFISH_BIOME = tag("is_obsidianfish_biome");
        public static final TagKey<Biome> IS_FORTRESS_GROUPER_BIOME = tag("is_fortress_grouper_biome");
        public static final TagKey<Biome> IS_EYEBALL_FISH_BIOME = tag("is_eyeball_fish_biome");

        private static TagKey<Biome> tag(String name){
            return TagKey.create(Registry.BIOME_REGISTRY, prefix(name));

        }
        private static TagKey<Biome> fabrictag(String name){
            return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("fabric", name));

        }
    }
}
