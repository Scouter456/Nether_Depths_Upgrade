package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, NetherDepthsUpgrade.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels(){
        for (Item i : Registry.ITEM) {
            if (i instanceof SpawnEggItem && ForgeRegistries.ITEMS.getKey(i).getNamespace().equals(NetherDepthsUpgrade.MODID)) {
                getBuilder(ForgeRegistries.ITEMS.getKey(i).getPath())
                        .parent(getExistingFile(new ResourceLocation("item/template_spawn_egg")));
            }
        }


        singleTex(NDUItems.LAVA_PUFFERFISH_BUCKET);
        singleTex(NDUItems.LAVA_PUFFERFISH);
        singleTex(NDUItems.OBSIDIANFISH);
        singleTex(NDUItems.OBSIDIANFISH_BUCKET);
        singleTex(NDUItems.SEARING_COD_BUCKET);
        singleTex(NDUItems.SEARING_COD);
        singleTex(NDUItems.BONEFISH_BUCKET);
        singleTex(NDUItems.BONEFISH);
        singleTex(NDUItems.WITHER_BONEFISH_BUCKET);
        singleTex(NDUItems.WITHER_BONEFISH);
        singleTex(NDUItems.BLAZEFISH_BUCKET);
        singleTex(NDUItems.BLAZEFISH);
        singleTex(NDUItems.MAGMACUBEFISH_BUCKET);
        singleTex(NDUItems.MAGMACUBEFISH);
        singleTex(NDUItems.GLOWDINE_BUCKET);
        singleTex(NDUItems.GLOWDINE);
        singleTex(NDUItems.SOULSUCKER_BUCKET);
        singleTex(NDUItems.SOULSUCKER);
        singleTex(NDUItems.SOUL_SUCKER_LEATHER);


        toBlock(NDUBlocks.LAVA_SPONGE);
        toBlock(NDUBlocks.WET_LAVA_SPONGE);
        toBlock(NDUBlocks.WARPED_KELP_BLOCK);

    }
    private void toBlock(RegistryObject<Block> b) {
        toBlockModel(b, b.getId().getPath());
    }

    private void toBlockModel(RegistryObject<Block> b, String model) {
        toBlockModel(b, prefix("block/" + model));
    }

    private void toBlockModel(RegistryObject<Block> b, ResourceLocation model) {
        withExistingParent(b.getId().getPath(), model);
    }

    private ItemModelBuilder singleTex(RegistryObject<Item> item) {
        return generated(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder generated(String name, ResourceLocation... layers) {
        ItemModelBuilder builder = withExistingParent(name, "item/generated");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }
}
