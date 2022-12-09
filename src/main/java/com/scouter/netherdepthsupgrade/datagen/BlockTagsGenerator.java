/*package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsGenerator extends BlockTagsProvider {
    public BlockTagsGenerator(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, NetherDepthsUpgrade.MODID, helper);
    }

    @Override
    protected void addTags(){
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(NDUBlocks.WARPED_KELP_BLOCK.get());

        //tag(BlockTags.MINEABLE_WITH_PICKAXE)
        //        .add(NDUBlocks.ORETEST.get());

        //Add a tag for minimum requirement of tool for block
        //tag(BlockTags.NEEDS_IRON_TOOL)
        //        .add(NDUBlocks.ORETEST.get());
    }


    @Override
    public String getName() { return "Nether Depths Upgrade Tags";}
}
*/