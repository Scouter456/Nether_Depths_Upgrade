package com.scouter.netherdepthsupgrade.datagen;

import com.google.common.collect.ImmutableMap;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Map;
import java.util.function.BiConsumer;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NetherDepthsUpgrade.MODID, existingFileHelper);
    }
    public static BlockFamily family = null;
    static final Map<BlockFamily.Variant, BiConsumer<ItemModelGenerator, Block>> SHAPE_CONSUMERS =
            ImmutableMap.<BlockFamily.Variant, BiConsumer<ItemModelGenerator, Block>>builder()
                    .put(BlockFamily.Variant.BUTTON, ItemModelGenerator::toBlockButton)
                    .put(BlockFamily.Variant.DOOR, ItemModelGenerator::singleTexBlockItem)
                    .put(BlockFamily.Variant.CHISELED, ItemModelGenerator::toBlock)
                    .put(BlockFamily.Variant.CRACKED, ItemModelGenerator::toBlock)
                    .put(BlockFamily.Variant.CUSTOM_FENCE, ItemModelGenerator::toBlockFence)
                    .put(BlockFamily.Variant.FENCE, ItemModelGenerator::toBlockFence)
                    .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, ItemModelGenerator::toBlock)
                    .put(BlockFamily.Variant.FENCE_GATE, ItemModelGenerator::toBlock)
                    .put(BlockFamily.Variant.SIGN, ItemModelGenerator::toBlock)
                    .put(BlockFamily.Variant.SLAB, ItemModelGenerator::toBlock)
                    .put(BlockFamily.Variant.STAIRS, ItemModelGenerator::toBlock)
                    .put(BlockFamily.Variant.PRESSURE_PLATE, ItemModelGenerator::toBlock)
                    .put(BlockFamily.Variant.TRAPDOOR, ItemModelGenerator::toBlockTrapdoor)
                    .put(BlockFamily.Variant.WALL, ItemModelGenerator::toBlockWall)
                    .build();
    @Override
    protected void registerModels() {
        for (Item i : BuiltInRegistries.ITEM) {
            if (i instanceof SpawnEggItem && BuiltInRegistries.ITEM.getKey(i).getNamespace().equals(NetherDepthsUpgrade.MODID)) {
                getBuilder(BuiltInRegistries.ITEM.getKey(i).getPath())
                        .parent(getExistingFile( ResourceLocation.withDefaultNamespace("item/template_spawn_egg")));
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
        singleTex(NDUItems.FORTRESS_GROUPER);
        singleTex(NDUItems.FORTRESS_GROUPER_BUCKET);
        singleTex(NDUItems.EYEBALL_FISH);
        singleTex(NDUItems.EYEBALL_FISH_BUCKET);
        singleTex(NDUItems.FORTRESS_GROUPER_PLATE);
        singleTex(NDUItems.EYEBALL_FISH_EYE);

        toBlock(NDUBlocks.LAVA_SPONGE);
        toBlock(NDUBlocks.WET_LAVA_SPONGE);
        toBlock(NDUBlocks.WARPED_KELP_BLOCK);
        toBlock(NDUBlocks.WARPED_KELP_CARPET_BLOCK);
        toBlock(NDUBlocks.CRIMSON_KELP_BLOCK);
        toBlock(NDUBlocks.CRIMSON_KELP_CARPET_BLOCK);
        toBlockModel(
                NDUBlocks.LAVA_GLASS,
                "lava_glass_all"
        );

        //singleTexFood(FarmersDelightCompat.BAKED_BLAZEFISH_STEW);
        //singleTexFood(FarmersDelightCompat.BAKED_GLOWDINE_STEW);
        //singleTexFood(FarmersDelightCompat.BAKED_LAVA_PUFFERFISH_STEW);
        //singleTexFood(FarmersDelightCompat.BAKED_MAGMACUBEFISH_STEW);
        //singleTexFood(FarmersDelightCompat.BAKED_OBSIDIANFISH_STEW);
        //singleTexFood(FarmersDelightCompat.BAKED_SEARING_COD_STEW);
        //singleTexFood(FarmersDelightCompat.BAKED_SOULSUCKER_STEW);
        //singleTexFood(FarmersDelightCompat.SEARING_COD_SLICE);
        //singleTexFood(FarmersDelightCompat.SOULSUCKER_SLICE);
        //singleTexFood(FarmersDelightCompat.BLAZEFISH_SLICE);
        //singleTexFood(FarmersDelightCompat.GLOWDINE_SLICE);
        //singleTexFood(FarmersDelightCompat.LAVA_PUFFERFISH_SLICE);
        //singleTexFood(FarmersDelightCompat.MAGMACUBEFISH_SLICE);
        //singleTexFood(FarmersDelightCompat.OBSIDIANFISH_SLICE);
        //singleTexFood(FarmersDelightCompat.COOKED_GLOWDINE_SLICE);
        //singleTexFood(FarmersDelightCompat.COOKED_LAVA_PUFFERFISH_SLICE);
        //singleTexFood(FarmersDelightCompat.COOKED_MAGMACUBEFISH_SLICE);
        //singleTexFood(FarmersDelightCompat.COOKED_OBSIDIANFISH_SLICE);
        //singleTexFood(FarmersDelightCompat.COOKED_SOULSUCKER_SLICE);
        //singleTexFood(FarmersDelightCompat.BLAZEFISH_ROLL);
        //singleTexFood(FarmersDelightCompat.SOULSUCKER_ROLL);
        //singleTexFood(FarmersDelightCompat.GLOWDINE_ROLL);
        //singleTexFood(FarmersDelightCompat.LAVA_PUFFERFISH_ROLL);
        //singleTexFood(FarmersDelightCompat.MAGMA_CUBE_FISH_ROLL);
        //singleTexFood(FarmersDelightCompat.SEARING_COD_ROLL);
        //singleTexFood(FarmersDelightCompat.OBSIDIANFISH_ROLL);
        //singleTexFood(FarmersDelightCompat.GRILLED_BLAZEFISH);
        //singleTexFood(FarmersDelightCompat.GRILLED_LAVA_PUFFERFISH);
        //singleTexFood(FarmersDelightCompat.GRILLED_GLOWDINE);
        //singleTexFood(FarmersDelightCompat.GRILLED_OBSIDIANFISH);
        //singleTexFood(FarmersDelightCompat.GRILLED_MAGMA_CUBE_FISH);
        //singleTexFood(FarmersDelightCompat.GRILLED_SEARING_COD);
        //singleTexFood(FarmersDelightCompat.GRILLED_SOULSUCKER);
        //singleTexFood(FarmersDelightCompat.WARPED_KELP_ROLL_SLICE);
        //singleTexFood(FarmersDelightCompat.WARPED_KELP_ROLL);
        //singleTexFood(FarmersDelightCompat.NETHER_RICE_ROLL_MEDLEY_BLOCKITEM);


    }

    private ItemModelBuilder simpleItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                 ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                 ResourceLocation.fromNamespaceAndPath(NetherDepthsUpgrade.MODID,"item/" + item.getId().getPath()));
    }



    private void toBlock(DeferredBlock<Block> b) {
        toBlockModel(b, b.getId().getPath());
    }

    private void toBlockWall(Block block) {
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        wallInventory(BuiltInRegistries.BLOCK.getKey(block).toString(),  prefix("block/" + BuiltInRegistries.BLOCK.getKey(block1).getPath()));
    }

    private void toBlockTrapdoor(Block b) {
        String s = BuiltInRegistries.BLOCK.getKey(b).getPath();
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.append("_bottom");
        toBlockModel(b, stringBuilder.toString());
    }
    private void toBlock(Block b) {
        toBlockModel(b, BuiltInRegistries.BLOCK.getKey(b).getPath());
    }

    private void toBlockItem(Block b) {
        toBlockModelItem(b, BuiltInRegistries.BLOCK.getKey(b).getPath());
    }
    private void toBlockFence(Block block) {
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        fenceInventory(BuiltInRegistries.BLOCK.getKey(block).toString(),  prefix("block/" + BuiltInRegistries.BLOCK.getKey(block1).getPath()));
    }

    private void toBlockButton(Block block) {
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        buttonInventory(BuiltInRegistries.BLOCK.getKey(block).toString(),  prefix("block/" + BuiltInRegistries.BLOCK.getKey(block1).getPath()));
    }
    private void toBlockModel(Block b, String model) {
        toBlockModel(b, prefix("block/" + model));
    }

    private void toBlockModelItem(Block b, String model) {
        toBlockModel(b, prefix("item/" + model));
    }
    private void toBlockModel(DeferredBlock<Block> b, String model) {
        toBlockModel(b, prefix("block/" + model));
    }

    private void toBlockModel(DeferredBlock<Block>  b, ResourceLocation model) {
        withExistingParent(b.getId().getPath(), model);
    }
    private ItemModelBuilder singleTex(DeferredItem<Item> item) {
        return generated(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder singleTexFood(DeferredItem<Item> item) {
        return generated(item.getId().getPath(), prefix("item/farmersdelight_compat_food/" + item.getId().getPath()));
    }
    private ItemModelBuilder singleTexBlockItem(Block  item) {
        return generated(key(item).getPath(), prefix("item/" + key(item).getPath()));
    }
    private ItemModelBuilder singleTexBlockItem(DeferredBlock<Block>  item) {
        return generated(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
    }
    private ItemModelBuilder singleTexBlock(DeferredBlock<Block>  item) {
        return generated(item.getId().getPath(), prefix("block/" + item.getId().getPath()));
    }
    private ItemModelBuilder generated(String name, ResourceLocation... layers) {
        ItemModelBuilder builder = withExistingParent(name, "item/generated");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }

    private ItemModelBuilder singleTexHandheld(DeferredItem<Item> item) {
        return generatedHandheld(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder generatedHandheld(String name, ResourceLocation... layers) {
        ItemModelBuilder builder = withExistingParent(name, "item/handheld");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }
    private void toBlockModel(Block b, ResourceLocation model) {
        withExistingParent(BuiltInRegistries.BLOCK.getKey(b).getPath(), model);
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}