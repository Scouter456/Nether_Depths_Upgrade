package com.scouter.netherdepthsupgrade.blocks;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NDUBlocks {
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final RegistryObject<Block> LAVA_SPONGE = BLOCKS.register("lava_sponge", () -> new LavaSpongeBlock(AbstractBlock.Properties.of(Material.SPONGE).strength(0.6F).sound(SoundType.GRASS).harvestTool(ToolType.HOE)));
    public static final RegistryObject<Block> WET_LAVA_SPONGE = BLOCKS.register("wet_lava_sponge", () -> new WetLavaSpongeBlock(AbstractBlock.Properties.of(Material.SPONGE).strength(0.6F).sound(SoundType.GRASS).harvestTool(ToolType.HOE)));
    public static final RegistryObject<Block> WARPED_KELP_PLANT = BLOCKS.register("warped_kelp_plant", () -> new LavaKelpPlantBlock(AbstractBlock.Properties.of(Material.WATER_PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final RegistryObject<Block> WARPED_KELP = BLOCKS.register("warped_kelp", () -> new LavaKelpBlock(AbstractBlock.Properties.of(Material.WATER_PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final RegistryObject<Block> WARPED_SEAGRASS = BLOCKS.register("warped_seagrass", () -> new LavaSeagrassBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_WATER_PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final RegistryObject<Block> TALL_WARPED_SEAGRASS = BLOCKS.register("tall_warped_seagrass", () -> new TallLavaSeagrassBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_WATER_PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS).noOcclusion()));
    public static final RegistryObject<Block> WARPED_KELP_BLOCK = BLOCKS.register("warped_kelp_block", () -> new Block(AbstractBlock.Properties.of(Material.REPLACEABLE_WATER_PLANT, MaterialColor.COLOR_GREEN).strength(0.5F, 2.5F).sound(SoundType.WET_GRASS).harvestTool(ToolType.HOE)));
}
