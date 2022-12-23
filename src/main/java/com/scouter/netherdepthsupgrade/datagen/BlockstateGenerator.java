package com.scouter.netherdepthsupgrade.datagen;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

public class BlockstateGenerator extends BlockStateProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int DEFAULT_ANGLE_OFFSET = 180;

    public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, NetherDepthsUpgrade.MODID, exFileHelper);
    }

    private String blockName(Block block) {
        return block.getRegistryName().getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(NetherDepthsUpgrade.MODID, "block/" + path);
    }

    public ModelFile existingModel(Block block) {
        return new ModelFile.ExistingModelFile(resourceBlock(blockName(block)), models().existingFileHelper);
    }

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(resourceBlock(path), models().existingFileHelper);
    }
    @Override
    protected void registerStatesAndModels(){
        simpleBlock(NDUBlocks.LAVA_SPONGE.get());
        simpleBlock(NDUBlocks.WET_LAVA_SPONGE.get());
        //simpleBlock(NDUBlocks.LAVA_GLASS.get());
        //horizontalBlock(NDUBlocks.NIGHTMARE.get(), models().orientableVertical(NDUBlocks.NIGHTMARE.getId().getPath(), prefix("block/nightmare_side"), prefix("block/nightmare_fron")));
    }


    @Override
    public String getName() {
        return "Block States: " + NetherDepthsUpgrade.MODID;
    }
}
