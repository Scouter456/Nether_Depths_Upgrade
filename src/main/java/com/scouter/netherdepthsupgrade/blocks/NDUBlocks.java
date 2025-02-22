package com.scouter.netherdepthsupgrade.blocks;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUBlocks {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final Block LAVA_SPONGE = register("lava_sponge", LavaSpongeBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE).mapColor(MapColor.COLOR_YELLOW).strength(0.6F).sound(SoundType.GRASS));
    public static final Block WET_LAVA_SPONGE = register("wet_lava_sponge", WetLavaSpongeBlock::new,BlockBehaviour.Properties.ofFullCopy(Blocks.WET_SPONGE).mapColor(MapColor.COLOR_YELLOW).strength(0.6F).sound(SoundType.GRASS));
    public static final Block WARPED_KELP_PLANT = register("warped_kelp_plant",  WarpedKelpPlantBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP).mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final Block WARPED_KELP = register("warped_kelp",  WarpedKelpBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP).mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final Block WARPED_SEAGRASS = register("warped_seagrass", WarpedSeagrassBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.SEAGRASS).mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final Block TALL_WARPED_SEAGRASS = register("tall_warped_seagrass",  TallWarpedSeagrassBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.SEAGRASS).mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));

    public static final Block WARPED_KELP_BLOCK = register("warped_kelp_block",  Block::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP).mapColor(MapColor.WATER).strength(0.5F, 2.5F).sound(SoundType.WET_GRASS)));
    public static final Block WARPED_KELP_CARPET_BLOCK = register("warped_kelp_carpet_block", CarpetBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_CARPET).mapColor(MapColor.WATER).strength(0.1F).sound(SoundType.WET_GRASS)));

    public static final Block CRIMSON_KELP_PLANT = register("crimson_kelp_plant", CrimsonKelpPlantBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP).mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final Block CRIMSON_KELP = register("crimson_kelp", CrimsonKelpBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP).mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final Block CRIMSON_SEAGRASS = register("crimson_seagrass", CrimsonSeagrassBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.SEAGRASS).mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final Block TALL_CRIMSON_SEAGRASS = register("tall_crimson_seagrass", TallCrimsonSeagrassBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.SEAGRASS).mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));

    public static final Block CRIMSON_KELP_BLOCK  = register("crimson_kelp_block", Block::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP).mapColor(MapColor.WATER).strength(0.5F, 2.5F).sound(SoundType.WET_GRASS)));
    public static final Block CRIMSON_KELP_CARPET_BLOCK = register("crimson_kelp_carpet_block", CarpetBlock::new,(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_CARPET).mapColor(MapColor.WATER).strength(0.1F).sound(SoundType.WET_GRASS)));
    public static final Block LAVA_GLASS = register("lava_glass", LavaGlassBlock::new,(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).strength(0.6F).sound(SoundType.GLASS)));


    private static ResourceKey<Block> nduBlockId(String string) {
        return ResourceKey.create(Registries.BLOCK, prefix(string));
    }
    public static Block register(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        Block block = (Block)function.apply(properties.setId(resourceKey));
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }

    private static Block register(String string, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        return register(nduBlockId(string), function, properties);
    }

    private static Block register(String string, BlockBehaviour.Properties properties) {
        return register(string, Block::new, properties);
    }

    private static Block registerBlock(String name, Block block){
        return register(name, properties ->  block ,block.properties());
    }






    public static void BLOCKS(){
        LOGGER.info("Registering Blocks for " + NetherDepthsUpgrade.MODID);
    }
}
