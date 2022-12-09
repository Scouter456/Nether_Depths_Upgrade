package com.scouter.netherdepthsupgrade.utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

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
        private static TagKey<Item> tag(String name){
            return ItemTags.create(prefix(name));

        }
        private static TagKey<Item> forgeTag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));

        }
    }

    public static class EntityTypes {
        private static TagKey<EntityType<?>> tag(String name){
            return TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), prefix(name));

        }
        private static TagKey<EntityType<?>> forgeTag(String name){
            return TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), new ResourceLocation("forge", name));

        }
    }

    public static class Biomes {
        public static final TagKey<Biome> IS_LAVA_PUFFERFISH_BIOME = tag("is_lava_pufferfish_biome");
        public static final TagKey<Biome> IS_WITHER_BONEFISH_BIOME = tag("is_wither_bonefish_biome");

        private static TagKey<Biome> tag(String name){
            return TagKey.create(ForgeRegistries.BIOMES.getRegistryKey(), prefix(name));

        }
        private static TagKey<Biome> forgeTag(String name){
            return TagKey.create(ForgeRegistries.BIOMES.getRegistryKey(), new ResourceLocation("forge", name));

        }
    }
}
