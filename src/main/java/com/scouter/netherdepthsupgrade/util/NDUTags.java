package com.scouter.netherdepthsupgrade.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
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


        private static TagKey<Biome> tag(String name){
            return TagKey.create(Registries.BIOME, prefix(name));

        }
        private static TagKey<Biome> fabrictag(String name){
            return TagKey.create(Registries.BIOME, new ResourceLocation("fabric", name));

        }
    }
}
