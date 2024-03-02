package com.scouter.netherdepthsupgrade.blocks;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUBlocks {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final Block LAVA_SPONGE = registerBlock("lava_sponge", new LavaSpongeBlock(FabricBlockSettings.copyOf(Blocks.SPONGE).mapColor(MapColor.COLOR_YELLOW).strength(0.6F).sounds(SoundType.GRASS)));
    public static final Block WET_LAVA_SPONGE = registerBlock("wet_lava_sponge", new WetLavaSpongeBlock(FabricBlockSettings.copyOf(Blocks.WET_SPONGE).mapColor(MapColor.COLOR_YELLOW).strength(0.6F).sounds(SoundType.GRASS)));
    public static final Block WARPED_KELP_PLANT = registerBlock("warped_kelp_plant", new WarpedKelpPlantBlock(FabricBlockSettings.copyOf(Blocks.KELP).mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));
    public static final Block WARPED_KELP = registerBlock("warped_kelp", new WarpedKelpBlock(FabricBlockSettings.copyOf(Blocks.KELP).mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));
    public static final Block WARPED_SEAGRASS = registerBlock("warped_seagrass", new WarpedSeagrassBlock(FabricBlockSettings.copyOf(Blocks.SEAGRASS).mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));
    public static final Block TALL_WARPED_SEAGRASS = registerBlock("tall_warped_seagrass", new TallWarpedSeagrassBlock(FabricBlockSettings.copyOf(Blocks.SEAGRASS).mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));

    public static final Block WARPED_KELP_BLOCK = registerBlock("warped_kelp_block", new Block(FabricBlockSettings.copyOf(Blocks.KELP).mapColor(MapColor.WATER).strength(0.5F, 2.5F).sounds(SoundType.WET_GRASS)));
    public static final Block WARPED_KELP_CARPET_BLOCK = registerBlock("warped_kelp_carpet_block", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.CYAN_CARPET).mapColor(MapColor.WATER).strength(0.1F).sounds(SoundType.WET_GRASS)));

    public static final Block CRIMSON_KELP_PLANT = registerBlock("crimson_kelp_plant", new CrimsonKelpPlantBlock(FabricBlockSettings.copyOf(Blocks.KELP).mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));
    public static final Block CRIMSON_KELP = registerBlock("crimson_kelp", new CrimsonKelpBlock(FabricBlockSettings.copyOf(Blocks.KELP).mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));
    public static final Block CRIMSON_SEAGRASS = registerBlock("crimson_seagrass", new CrimsonSeagrassBlock(FabricBlockSettings.copyOf(Blocks.SEAGRASS).mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));
    public static final Block TALL_CRIMSON_SEAGRASS = registerBlock("tall_crimson_seagrass", new TallCrimsonSeagrassBlock(FabricBlockSettings.copyOf(Blocks.SEAGRASS).mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));

    public static final Block CRIMSON_KELP_BLOCK  = registerBlock("crimson_kelp_block", new Block(FabricBlockSettings.copyOf(Blocks.KELP).mapColor(MapColor.WATER).strength(0.5F, 2.5F).sounds(SoundType.WET_GRASS)));
    public static final Block CRIMSON_KELP_CARPET_BLOCK = registerBlock("crimson_kelp_carpet_block", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.RED_CARPET).mapColor(MapColor.WATER).strength(0.1F).sounds(SoundType.WET_GRASS)));
    public static final Block LAVA_GLASS = registerBlock("lava_glass", new LavaGlassBlock(FabricBlockSettings.of().mapColor(MapColor.WATER).strength(0.6F).sound(SoundType.GLASS)));

    private static Block registerBlock(String name, Block block){
        return Registry.register(BuiltInRegistries.BLOCK, prefix(name), block);
    }






    public static void BLOCKS(){
        LOGGER.info("Registering Blocks for " + NetherDepthsUpgrade.MODID);
    }
}
