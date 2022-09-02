package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ItemTagsGenerator extends ItemTagsProvider {
    public ItemTagsGenerator(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagProvider, NetherDepthsUpgrade.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        this.registerModTags();
    }

    private void registerModTags() {


    }

    private void registerForgeTags(){


    }

    @Override
    public String getName() { return "Monster Food Item Tags";}
}
