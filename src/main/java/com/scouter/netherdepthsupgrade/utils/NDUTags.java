package com.scouter.netherdepthsupgrade.utils;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUTags {
    public static class Blocks {
        private static TagKey<Block> tag(String name){
            return BlockTags.create(prefix(name));

        }
        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));

        }
    }

    public static class Items {
        public static final TagKey<Item> NETHER_SALAD_FOODS = tag("nether_salad_foods");
        private static TagKey<Item> tag(String name){
            return ItemTags.create(prefix(name));

        }
        private static TagKey<Item> forgeTag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));

        }
    }

    public static class EntityTypes {
        private static TagKey<EntityType<?>> tag(String name){
            return TagKey.create(Registries.ENTITY_TYPE, prefix(name));

        }
        private static TagKey<EntityType<?>> forgeTag(String name){
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge", name));

        }
    }

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
            return TagKey.create(Registries.BIOME, prefix(name));

        }
        private static TagKey<Biome> forgeTag(String name){
            return TagKey.create(Registries.BIOME, new ResourceLocation("forge", name));

        }
    }
}
