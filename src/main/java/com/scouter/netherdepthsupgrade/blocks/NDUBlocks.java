package com.scouter.netherdepthsupgrade.blocks;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.setup.Registration;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUBlocks {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final Block LAVA_SPONGE = registerBlock("lava_sponge", new LavaSpongeBlock(FabricBlockSettings.of(Material.SPONGE).strength(0.6F).sounds(SoundType.GRASS)), Registration.defaultBuilder);
    public static final Block WET_LAVA_SPONGE = registerBlock("wet_lava_sponge", new WetLavaSpongeBlock(FabricBlockSettings.of(Material.SPONGE).strength(0.6F).sounds(SoundType.GRASS)), Registration.defaultBuilder);
    public static final Block WARPED_KELP_PLANT = registerBlock("warped_kelp_plant", new LavaKelpPlantBlock(FabricBlockSettings.of(Material.WATER_PLANT).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()), Registration.defaultBuilder);
    public static final Block WARPED_KELP = registerBlock("warped_kelp", new LavaKelpBlock(FabricBlockSettings.of(Material.WATER_PLANT).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()), Registration.defaultBuilder);
    public static final Block WARPED_SEAGRASS = registerBlock("warped_seagrass", new LavaSeagrassBlock(FabricBlockSettings.of(Material.REPLACEABLE_WATER_PLANT).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()), Registration.defaultBuilder);
    public static final Block TALL_WARPED_SEAGRASS = registerBlock("tall_warped_seagrass", new TallLavaSeagrassBlock(FabricBlockSettings.of(Material.REPLACEABLE_WATER_PLANT).noCollision().breakInstantly().sounds(SoundType.WET_GRASS).nonOpaque()), Registration.defaultBuilder);

    public static final Block WARPED_KELP_BLOCK = registerBlock("warped_kelp_block", new Block(FabricBlockSettings.of(Material.REPLACEABLE_WATER_PLANT, MaterialColor.COLOR_GREEN).strength(0.5F, 2.5F).sounds(SoundType.WET_GRASS)), Registration.defaultBuilder);


    private static Block registerBlock(String name, Block block, CreativeModeTab group){
        return Registry.register(Registry.BLOCK, prefix(name), block);
    }






    public static void BLOCKS(){
        LOGGER.info("Registering Blocks for " + NetherDepthsUpgrade.MODID);
    }
}
