/*package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;

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
        dropOther(NDUBlocks.WARPED_KELP_PLANT.get(), NDUBlocks.WARPED_KELP.get());
        add(NDUBlocks.WARPED_SEAGRASS.get(), BlockLoot::createShearsOnlyDrop);
        add(NDUBlocks.TALL_WARPED_SEAGRASS.get(), createDoublePlantShearsDrop(NDUBlocks.WARPED_SEAGRASS.get()));

    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }
}
*/