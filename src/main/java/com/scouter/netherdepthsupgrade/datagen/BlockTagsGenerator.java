package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsGenerator extends BlockTagsProvider {
    public BlockTagsGenerator(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, NetherDepthsUpgrade.MODID, helper);
    }

    @Override
    protected void addTags(){
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(NDUBlocks.WARPED_KELP_BLOCK.get())
                .add(NDUBlocks.CRIMSON_KELP_BLOCK.get())
                .add(NDUBlocks.WET_LAVA_SPONGE.get())
                .add(NDUBlocks.LAVA_SPONGE.get());
        tag(BlockTags.IMPERMEABLE)
                .add(NDUBlocks.LAVA_GLASS.get());
    }


    @Override
    public String getName() { return "Nether Depths Upgrade Tags";}
}
