package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.modcompat.FarmersDelightCompat;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, NetherDepthsUpgrade.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels(){
        for(Item i : Registry.ITEM){
            if (i instanceof SpawnEggItem && i.getRegistryName().getNamespace().equals(NetherDepthsUpgrade.MODID)){
                getBuilder(i.getRegistryName().getPath()).parent((getExistingFile(new ResourceLocation("item/template_spawn_egg"))));
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


        toBlock(NDUBlocks.LAVA_SPONGE.get());
        toBlock(NDUBlocks.WET_LAVA_SPONGE.get());
        toBlock(NDUBlocks.WARPED_KELP_BLOCK.get());
        //toBlock(NDUBlocks.LAVA_GLASS.get());

        singleTexFood(FarmersDelightCompat.BAKED_BLAZEFISH_STEW);
        singleTexFood(FarmersDelightCompat.BAKED_GLOWDINE_STEW);
        singleTexFood(FarmersDelightCompat.BAKED_LAVA_PUFFERFISH_STEW);
        singleTexFood(FarmersDelightCompat.BAKED_MAGMACUBEFISH_STEW);
        singleTexFood(FarmersDelightCompat.BAKED_OBSIDIANFISH_STEW);
        singleTexFood(FarmersDelightCompat.BAKED_SEARING_COD_STEW);
        singleTexFood(FarmersDelightCompat.BAKED_SOULSUCKER_STEW);
        singleTexFood(FarmersDelightCompat.SEARING_COD_SLICE);
        singleTexFood(FarmersDelightCompat.SOULSUCKER_SLICE);
        singleTexFood(FarmersDelightCompat.BLAZEFISH_SLICE);
        singleTexFood(FarmersDelightCompat.GLOWDINE_SLICE);
        singleTexFood(FarmersDelightCompat.LAVA_PUFFERFISH_SLICE);
        singleTexFood(FarmersDelightCompat.MAGMACUBEFISH_SLICE);
        singleTexFood(FarmersDelightCompat.OBSIDIANFISH_SLICE);
        singleTexFood(FarmersDelightCompat.COOKED_GLOWDINE_SLICE);
        singleTexFood(FarmersDelightCompat.COOKED_LAVA_PUFFERFISH_SLICE);
        singleTexFood(FarmersDelightCompat.COOKED_MAGMACUBEFISH_SLICE);
        singleTexFood(FarmersDelightCompat.COOKED_OBSIDIANFISH_SLICE);
        singleTexFood(FarmersDelightCompat.COOKED_SOULSUCKER_SLICE);
        singleTexFood(FarmersDelightCompat.BLAZEFISH_ROLL);
        singleTexFood(FarmersDelightCompat.SOULSUCKER_ROLL);
        singleTexFood(FarmersDelightCompat.GLOWDINE_ROLL);
        singleTexFood(FarmersDelightCompat.LAVA_PUFFERFISH_ROLL);
        singleTexFood(FarmersDelightCompat.MAGMA_CUBE_FISH_ROLL);
        singleTexFood(FarmersDelightCompat.SEARING_COD_ROLL);
        singleTexFood(FarmersDelightCompat.OBSIDIANFISH_ROLL);
        singleTexFood(FarmersDelightCompat.GRILLED_BLAZEFISH);
        singleTexFood(FarmersDelightCompat.GRILLED_LAVA_PUFFERFISH);
        singleTexFood(FarmersDelightCompat.GRILLED_GLOWDINE);
        singleTexFood(FarmersDelightCompat.GRILLED_OBSIDIANFISH);
        singleTexFood(FarmersDelightCompat.GRILLED_MAGMA_CUBE_FISH);
        singleTexFood(FarmersDelightCompat.GRILLED_SEARING_COD);
        singleTexFood(FarmersDelightCompat.GRILLED_SOULSUCKER);
        singleTexFood(FarmersDelightCompat.WARPED_KELP_ROLL_SLICE);
        singleTexFood(FarmersDelightCompat.WARPED_KELP_ROLL);
    }
    private void toBlock(Block b) {
        toBlockModel(b, b.getRegistryName().getPath());
    }

    private void toBlockModel(Block b, String model) {
        toBlockModel(b, prefix("block/" + model));
    }

    private void toBlockModel(Block b, ResourceLocation model) {
        withExistingParent(b.getRegistryName().getPath(), model);
    }

    private ItemModelBuilder singleTex(RegistryObject<Item> item) {
        return generated(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
    }
    private ItemModelBuilder singleTexFood(RegistryObject<Item> item) {
        return generated(item.getId().getPath(), prefix("item/farmersdelight_compat_food/" + item.getId().getPath()));
    }
    private ItemModelBuilder generated(String name, ResourceLocation... layers) {
        ItemModelBuilder builder = withExistingParent(name, "item/generated");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }
}
