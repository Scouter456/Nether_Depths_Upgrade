package com.scouter.netherdepthsupgrade.blocks;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUBlocks {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final Block LAVA_SPONGE = registerBlock("lava_sponge", new LavaSpongeBlock(FabricBlockSettings.of().mapColor(MapColor.COLOR_YELLOW).strength(0.6F).sounds(SoundType.GRASS)));
    public static final Block WET_LAVA_SPONGE = registerBlock("wet_lava_sponge", new WetLavaSpongeBlock(FabricBlockSettings.of().mapColor(MapColor.COLOR_YELLOW).strength(0.6F).sounds(SoundType.GRASS)));
    public static final Block WARPED_KELP_PLANT = registerBlock("warped_kelp_plant", new LavaKelpPlantBlock(FabricBlockSettings.of().mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));
    public static final Block WARPED_KELP = registerBlock("warped_kelp", new LavaKelpBlock(FabricBlockSettings.of().mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));
    public static final Block WARPED_SEAGRASS = registerBlock("warped_seagrass", new LavaSeagrassBlock(FabricBlockSettings.of().mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));
    public static final Block TALL_WARPED_SEAGRASS = registerBlock("tall_warped_seagrass", new TallLavaSeagrassBlock(FabricBlockSettings.of().mapColor(MapColor.WATER).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()));

    public static final Block WARPED_KELP_BLOCK = registerBlock("warped_kelp_block", new Block(FabricBlockSettings.of().mapColor(MapColor.WATER).strength(0.5F, 2.5F).sounds(SoundType.WET_GRASS)));
    public static final Block WARPED_KELP_CARPET_BLOCK = registerBlock("warped_kelp_carpet_block", new CarpetBlock(FabricBlockSettings.of().mapColor(MapColor.WATER).strength(0.1F).sounds(SoundType.WET_GRASS)));

    public static final Block LAVA_GLASS = registerBlock("lava_glass", new LavaGlassBlock(FabricBlockSettings.of().strength(0.6F).sound(SoundType.GLASS).noOcclusion()));

    private static Block registerBlock(String name, Block block){
        return Registry.register(BuiltInRegistries.BLOCK, prefix(name), block);
    }






    public static void BLOCKS(){
        LOGGER.info("Registering Blocks for " + NetherDepthsUpgrade.MODID);
    }
}
