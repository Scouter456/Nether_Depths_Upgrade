package com.scouter.netherdepthsupgrade.datagen;

import com.google.common.collect.ImmutableMap;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.utils.NDUTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NetherDepthsUpgrade.MODID, existingFileHelper);
    }
    static final Map<BlockFamily.Variant, BiConsumer<BlockTagGenerator, Block>> SHAPE_CONSUMERS =
            ImmutableMap.<BlockFamily.Variant, BiConsumer<BlockTagGenerator, Block>>builder()
                    .put(BlockFamily.Variant.BUTTON, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.DOOR, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.CHISELED, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.CRACKED, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.CUSTOM_FENCE, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.FENCE, BlockTagGenerator::addToTagFence)
                    .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.FENCE_GATE, BlockTagGenerator::addToTagFenceGate)
                    .put(BlockFamily.Variant.SIGN, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.SLAB, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.STAIRS, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.PRESSURE_PLATE, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.TRAPDOOR, BlockTagGenerator::addToTag)
                    .put(BlockFamily.Variant.WALL, BlockTagGenerator::addToTagWall)
                    .build();
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(NDUBlocks.WARPED_KELP_BLOCK.get())
                .add(NDUBlocks.CRIMSON_KELP_BLOCK.get())
                .add(NDUBlocks.WET_LAVA_SPONGE.get())
                .add(NDUBlocks.LAVA_SPONGE.get());
        tag(BlockTags.IMPERMEABLE)
                .add(NDUBlocks.LAVA_GLASS.get());
        tag(NDUTags.Blocks.LAVA_PLANTS)
                .add(
                        NDUBlocks.WARPED_SEAGRASS.get(),
                        NDUBlocks.TALL_WARPED_SEAGRASS.get(),
                        NDUBlocks.WARPED_KELP.get(),
                        NDUBlocks.WARPED_KELP_PLANT.get(),
                        NDUBlocks.CRIMSON_SEAGRASS.get(),
                        NDUBlocks.TALL_CRIMSON_SEAGRASS.get(),
                        NDUBlocks.CRIMSON_KELP.get(),
                        NDUBlocks.CRIMSON_KELP_PLANT.get()
                );
    }
    
    public void addToTag(Block block){
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(block);
    }

    public void addToTagWall(Block block){
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(block);
        this.tag(BlockTags.WALLS)
                .add(block);

    }

    public void addToTagFence(Block block){
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(block);
        this.tag(BlockTags.WOODEN_FENCES)
                .add(block);
        this.tag(BlockTags.FENCES)
                .add(block);
    }

    public void addToTagFenceGate(Block block){
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(block);
        this.tag(BlockTags.WOODEN_FENCES)
                .add(block);
        this.tag(BlockTags.FENCE_GATES)
                .add(block);
    }

}
