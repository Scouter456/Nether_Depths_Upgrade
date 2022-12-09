package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.utils.NDUTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;

public class NDUBiomeTagsProvider extends BiomeTagsProvider {
    public NDUBiomeTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
        super(pGenerator, NetherDepthsUpgrade.MODID, existingFileHelper);
    }

    protected void addTags() {
        tag(NDUTags.Biomes.IS_LAVA_PUFFERFISH_BIOME).add(Biomes.CRIMSON_FOREST).add(Biomes.WARPED_FOREST);
        tag(NDUTags.Biomes.IS_WITHER_BONEFISH_BIOME).add(Biomes.CRIMSON_FOREST).add(Biomes.SOUL_SAND_VALLEY);
        tag(NDUTags.Biomes.IS_GLOWDINE_BIOME).add(Biomes.BASALT_DELTAS).add(Biomes.SOUL_SAND_VALLEY).add(Biomes.NETHER_WASTES);
        tag(NDUTags.Biomes.IS_MAGMACUBEFISH_BIOME).add(Biomes.BASALT_DELTAS);
        tag(NDUTags.Biomes.IS_SOULSUCKER_BIOME).add(Biomes.SOUL_SAND_VALLEY);
        tag(NDUTags.Biomes.IS_OBSIDIANFISH_BIOME).add(Biomes.BASALT_DELTAS);


    }
}
