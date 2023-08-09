package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.utils.NDUTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
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

        tag(NDUTags.Items.NETHER_SALAD_FOODS)
                .add(NDUItems.WARPED_KELP.get())
                .add(NDUItems.WARPED_SEAGRASS.get())
                .add(Items.CRIMSON_FUNGUS)
                .add(Items.WARPED_FUNGUS);
        tag(ItemTags.FISHES)
                .add(NDUItems.LAVA_PUFFERFISH.get())
                .add(NDUItems.GLOWDINE.get())
                .add(NDUItems.SOULSUCKER.get())
                .add(NDUItems.OBSIDIANFISH.get())
                .add(NDUItems.BONEFISH.get())
                .add(NDUItems.WITHER_BONEFISH.get())
                .add(NDUItems.FORTRESS_GROUPER.get())
                .add(NDUItems.EYEBALL_FISH.get())
                .add(NDUItems.BLAZEFISH.get())
                .add(NDUItems.SEARING_COD.get())
                .add(NDUItems.MAGMACUBEFISH.get());


    }

    private void registerForgeTags(){


    }

    @Override
    public String getName() { return "Nether Depths Upgrade Item Tags";}
}
