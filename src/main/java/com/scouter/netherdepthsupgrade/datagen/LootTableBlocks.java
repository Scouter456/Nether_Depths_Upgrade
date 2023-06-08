/*package com.scouter.netherdepthsupgrade.datagen;

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
        dropOther(NDUBlocks.WARPED_KELP_PLANT.get(), NDUBlocks.WARPED_KELP.get());
        add(NDUBlocks.WARPED_SEAGRASS.get(), BlockLoot::createShearsOnlyDrop);
        add(NDUBlocks.TALL_WARPED_SEAGRASS.get(), createDoublePlantShearsDrop(NDUBlocks.WARPED_SEAGRASS.get()));
        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.BEETROOTS).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BeetrootBlock.AGE, 2)).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BeetrootBlock.AGE, 3));
        createCropDrops(Blocks.BEETROOTS, Items.BEETROOT, Items.BEETROOT_SEEDS, lootitemcondition$builder);
    }

    private static LootTable.Builder spices(Block block, Item item, Item item2, Item item3) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(AlternativesEntry.alternatives(AlternativesEntry.alternatives(
                                        //When the property has value 1 drop item
                                        applyExplosionDecay(block, LootItem.lootTableItem(item).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_3, 1)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))),
                                        //When the property has value 2 drop item2
                                        applyExplosionDecay(block, LootItem.lootTableItem(item2).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_3, 2)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))),
                                        applyExplosionDecay(block, LootItem.lootTableItem(item).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_3, 2)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))),
                                        //When the property has value 3 drop item3
                                        applyExplosionDecay(block, LootItem.lootTableItem(item3).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_3, 3)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))),
                                        applyExplosionDecay(block, LootItem.lootTableItem(item).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_3, 4)))).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                                .when(HAS_NO_SILK_TOUCH),
                        AlternativesEntry.alternatives(LootItem.lootTableItem(block)))));}
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }
}
*/