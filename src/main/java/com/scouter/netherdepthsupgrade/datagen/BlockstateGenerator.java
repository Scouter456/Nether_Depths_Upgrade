/*package com.scouter.netherdepthsupgrade.datagen;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

public class BlockstateGenerator extends BlockStateProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int DEFAULT_ANGLE_OFFSET = 180;

    public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, NetherDepthsUpgrade.MODID, exFileHelper);
    }

    private String blockName(Block block) {
        return block.getLootTable().getPath();
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
        //horizontalBlock(NDUBlocks.NIGHTMARE.get(), models().orientableVertical(NDUBlocks.NIGHTMARE.getId().getPath(), prefix("block/nightmare_side"), prefix("block/nightmare_fron")));

    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }


    public void tomatoBlock(Block block, ResourceLocation texture, String renderType) {
        tomatoBlock(block, key(block).toString(), texture, renderType);
    }

    public void tomatoBlock(Block block, String baseName, ResourceLocation texture, String  renderType) {
        ModelFile age1 = models().cross(baseName + "_stage0", new ResourceLocation(texture.toString() + "_stage0")).renderType(renderType);
        ModelFile age2 = models().cross(baseName + "_stage1",  new ResourceLocation(texture.toString() + "_stage1")).renderType(renderType);
        ModelFile age3 = models().cross(baseName + "_stage2",  new ResourceLocation(texture.toString() + "_stage2")).renderType(renderType);
        ModelFile age4 = models().cross(baseName + "_stage3",  new ResourceLocation(texture.toString() + "_stage3")).renderType(renderType);

        tomatoBlock(block, age1, age2, age3, age4);
    }
    private void tomatoBlock(Block block, ModelFile age1, ModelFile age2, ModelFile age3, ModelFile age4){
        getVariantBuilder(block).forAllStatesExcept((state -> {
            switch ( state.getValue(BlockStateProperties.AGE_3)){
                default: return ConfiguredModel.builder().modelFile(age1).build();
                case 1:return ConfiguredModel.builder().modelFile(age2).build();
                case 2:return ConfiguredModel.builder().modelFile(age3).build();
                case 3:return ConfiguredModel.builder().modelFile(age4).build();
            }
        }));
    }



    @Override
    public String getName() {
        return "Block States: " + NetherDepthsUpgrade.MODID;
    }
}*/
