package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.HashSet;
import java.util.Set;

public class LootTableBlocks extends BlockLoot {

    private final Set<Block> knownBlocks = new HashSet<>();
    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    @Override
    protected void add(Block block, LootTable.Builder builder) {
        super.add(block, builder);
        knownBlocks.add(block);
    }

    @Override
    protected void addTables() {
        dropSelf(NDUBlocks.WARPED_KELP.get());
        dropSelf(NDUBlocks.LAVA_SPONGE.get());
        dropSelf(NDUBlocks.WET_LAVA_SPONGE.get());
        dropSelf(NDUBlocks.WARPED_KELP_BLOCK.get());
        dropOther(NDUBlocks.WARPED_KELP_PLANT.get(), NDUBlocks.WARPED_KELP.get());
        add(NDUBlocks.WARPED_SEAGRASS.get(), BlockLoot::createShearsOnlyDrop);
        add(NDUBlocks.TALL_WARPED_SEAGRASS.get(), createDoublePlantShearsDrop(NDUBlocks.WARPED_SEAGRASS.get()));
        dropSelf(NDUBlocks.CRIMSON_KELP.get());
        dropSelf(NDUBlocks.CRIMSON_KELP_BLOCK.get());
        dropSelf(NDUBlocks.CRIMSON_KELP_CARPET_BLOCK.get());
        dropSelf(NDUBlocks.WARPED_KELP_CARPET_BLOCK.get());
        dropOther(NDUBlocks.CRIMSON_KELP_PLANT.get(), NDUBlocks.CRIMSON_KELP.get());
        add(NDUBlocks.CRIMSON_SEAGRASS.get(), BlockLoot::createShearsOnlyDrop);
        add(NDUBlocks.TALL_CRIMSON_SEAGRASS.get(), createDoublePlantShearsDrop(NDUBlocks.CRIMSON_SEAGRASS.get()));
        dropWhenSilkTouch(NDUBlocks.LAVA_GLASS.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }
}
