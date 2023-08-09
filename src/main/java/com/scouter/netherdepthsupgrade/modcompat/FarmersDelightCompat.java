package com.scouter.netherdepthsupgrade.modcompat;

import com.mojang.logging.LogUtils;
import com.nhoryzon.mc.farmersdelight.block.FeastBlock;
import com.scouter.netherdepthsupgrade.items.NDUDescriptionBlockItem;
import com.scouter.netherdepthsupgrade.items.NDUDescriptionItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class FarmersDelightCompat {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static Optional<ItemStack> RICE_MEDLEY;
    public static void setupCompat() {
        LOGGER.info("Setting up compat for Farmers Delight");
        RICE_MEDLEY = Optional.ofNullable(new ItemStack(NETHER_RICE_ROLL_MEDLEY_BLOCK));
        ModChecker.farmersDelightPresent = true;

    }


    //public static CreativeModeTab foodBuilder = FabricItemGroupBuilder.build(prefix("netherdepthsupgrade_food"), () -> RICE_MEDLEY);

    //SLICES
     public static final Item LAVA_PUFFERFISH_SLICE = registerItem("lava_pufferfish_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.LAVA_PUFFERFISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.lava_pufferfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item OBSIDIANFISH_SLICE = registerItem("obsidianfish_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.OBSIDIANFISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.obsidianfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item SEARING_COD_SLICE = registerItem("searing_cod_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.SEARING_COD_SLICE),
            Component.translatable("item.netherdepthsupgrade.searing_cod_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item BLAZEFISH_SLICE = registerItem("blazefish_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.BLAZEFISH_SLICE),
            Component.translatable("item.netherdepthsupgrade.blazefish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item MAGMACUBEFISH_SLICE = registerItem("magmacubefish_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.MAGMA_CUBE_FISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.magmacubefish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));
     public static final Item GLOWDINE_SLICE = registerItem("glowdine_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.GLOWDINE_SLICE)
            , Component.translatable("item.netherdepthsupgrade.glowdine_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item SOULSUCKER_SLICE = registerItem("soulsucker_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.SOULSUCKER_SLICE)
            , Component.translatable("item.netherdepthsupgrade.soulsucker_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));


    //COOKED (after cooking)
     public static final Item COOKED_LAVA_PUFFERFISH_SLICE = registerItem("cooked_lava_pufferfish_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.COOKED_LAVA_PUFFERFISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_lava_pufferfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item COOKED_OBSIDIANFISH_SLICE = registerItem("cooked_obsidianfish_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.COOKED_OBSIDIANFISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_obsidianfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item COOKED_MAGMACUBEFISH_SLICE = registerItem("cooked_magmacubefish_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.COOKED_MAGMA_CUBE_FISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_magmacubefish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item COOKED_GLOWDINE_SLICE = registerItem("cooked_glowdine_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.COOKED_GLOWDINE_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_glowdine_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item COOKED_SOULSUCKER_SLICE = registerItem("cooked_soulsucker_slice",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.COOKED_SOULSUCKER_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_soulsucker_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    //RICE ROLLS
     public static final Item LAVA_PUFFERFISH_ROLL = registerItem("lava_pufferfish_roll",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.LAVA_PUFFERFISH_ROLL),
                    Component.translatable("item.netherdepthsupgrade.lava_pufferfish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item OBSIDIANFISH_ROLL = registerItem("obsidianfish_roll",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.OBSIDIANFISH_ROLL),
                    Component.translatable("item.netherdepthsupgrade.obsidianfish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item SEARING_COD_ROLL = registerItem("searing_cod_roll",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.SEARING_COD_ROLL),
                    Component.translatable("item.netherdepthsupgrade.searing_cod_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item BLAZEFISH_ROLL = registerItem("blazefish_roll",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.BLAZEFISH_ROLL),
                    Component.translatable("item.netherdepthsupgrade.blazefish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item MAGMA_CUBE_FISH_ROLL = registerItem("magmacubefish_roll",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.MAGMA_CUBE_FISH_ROLL),
                    Component.translatable("item.netherdepthsupgrade.magmacubefish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item GLOWDINE_ROLL = registerItem("glowdine_roll",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.GLOWDINE_ROLL),
                    Component.translatable("item.netherdepthsupgrade.glowdine_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item SOULSUCKER_ROLL = registerItem("soulsucker_roll",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.SOULSUCKER_ROLL),
                    Component.translatable("item.netherdepthsupgrade.soulsucker_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item WARPED_KELP_ROLL = registerItem("warped_kelp_roll",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.WARPED_KELP_ROLL),
                    Component.translatable("item.netherdepthsupgrade.warped_kelp_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));
     public static final Item WARPED_KELP_ROLL_SLICE = registerItem("warped_kelp_roll_slice",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.WARPED_KELP_ROLL_SLICE),
                    Component.translatable("item.netherdepthsupgrade.warped_kelp_roll_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    //GRILLED FOODS
     public static final Item GRILLED_LAVA_PUFFERFISH = registerItem("grilled_lava_pufferfish",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.GRILLED_LAVA_PUFFERFISH),
                    Component.translatable("item.netherdepthsupgrade.grilled_lava_pufferfish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item GRILLED_OBSIDIANFISH = registerItem("grilled_obsidianfish",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.GRILLED_OBSIDIANFISH),
                    Component.translatable("item.netherdepthsupgrade.grilled_obsidianfish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item GRILLED_SEARING_COD = registerItem("grilled_searing_cod",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.GRILLED_SEARING_COD),
                    Component.translatable("item.netherdepthsupgrade.grilled_searing_cod.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item GRILLED_BLAZEFISH = registerItem("grilled_blazefish",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.GRILLED_BLAZEFISH),
                    Component.translatable("item.netherdepthsupgrade.grilled_blazefish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item GRILLED_MAGMA_CUBE_FISH = registerItem("grilled_magmacubefish",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.GRILLED_MAGMA_CUBE_FISH),
                    Component.translatable("item.netherdepthsupgrade.grilled_magmacubefish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item GRILLED_GLOWDINE = registerItem("grilled_glowdine",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.GRILLED_GLOWDINE),
                    Component.translatable("item.netherdepthsupgrade.grilled_glowdine.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item GRILLED_SOULSUCKER = registerItem("grilled_soulsucker",
             new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.GRILLED_SOULSUCKER),
                    Component.translatable("item.netherdepthsupgrade.grilled_soulsucker.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));



    //STEWS
     public static final Item BAKED_LAVA_PUFFERFISH_STEW = registerItem("baked_lava_pufferfish_stew",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.BAKED_LAVA_PUFFERFISH_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_lava_pufferfish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item BAKED_OBSIDIANFISH_STEW = registerItem("baked_obsidianfish_stew",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.BAKED_OBSIDIANFISH_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_obsidianfish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item BAKED_SEARING_COD_STEW = registerItem("baked_searing_cod_stew",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.BAKED_SEARING_COD_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_searing_cod_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item BAKED_BLAZEFISH_STEW = registerItem("baked_blazefish_stew",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.BAKED_BLAZEFISH_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_blazefish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item BAKED_MAGMACUBEFISH_STEW = registerItem("baked_magmacubefish_stew",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.BAKED_MAGMA_CUBE_FISH_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_magmacubefish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item BAKED_GLOWDINE_STEW = registerItem("baked_glowdine_stew",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.BAKED_GLOWDINE_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_glowdine_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

     public static final Item BAKED_SOULSUCKER_STEW = registerItem("baked_soulsucker_stew",  new NDUDescriptionItem(new FabricItemSettings().fireproof().food(FarmersDelightCompatFoods.BAKED_SOULSUCKER_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_soulsucker_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));



    //BLOCKS
    public static final Block NETHER_RICE_ROLL_MEDLEY_BLOCK = registerBlock("nether_rice_roll_medley_block", new NetherRiceRollMedleyBlock());

    //BLOCKITEM
     public static final Item NETHER_RICE_ROLL_MEDLEY_BLOCKITEM = registerBlockItem(NETHER_RICE_ROLL_MEDLEY_BLOCK);

    private static final CreativeModeTab NDU_FARMERS_DELIGHT  = FabricItemGroup
            .builder()
            .title(Component.translatable("itemGroup.netherdepthsupgrade_food"))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(LAVA_PUFFERFISH_SLICE);
                entries.accept(OBSIDIANFISH_SLICE);
                entries.accept(SEARING_COD_SLICE);
                entries.accept(BLAZEFISH_SLICE);
                entries.accept(MAGMACUBEFISH_SLICE);
                entries.accept(GLOWDINE_SLICE);
                entries.accept(SOULSUCKER_SLICE);

                entries.accept(COOKED_LAVA_PUFFERFISH_SLICE);
                entries.accept(COOKED_OBSIDIANFISH_SLICE);
                entries.accept(COOKED_MAGMACUBEFISH_SLICE);
                entries.accept(COOKED_GLOWDINE_SLICE);
                entries.accept(COOKED_SOULSUCKER_SLICE);

                entries.accept(LAVA_PUFFERFISH_ROLL);
                entries.accept(OBSIDIANFISH_ROLL);
                entries.accept(SEARING_COD_ROLL);
                entries.accept(BLAZEFISH_ROLL);
                entries.accept(MAGMA_CUBE_FISH_ROLL);
                entries.accept(GLOWDINE_ROLL);
                entries.accept(SOULSUCKER_ROLL);
                entries.accept(WARPED_KELP_ROLL);
                entries.accept(WARPED_KELP_ROLL_SLICE);

                entries.accept(GRILLED_LAVA_PUFFERFISH);
                entries.accept(GRILLED_OBSIDIANFISH);
                entries.accept(GRILLED_SEARING_COD);
                entries.accept(GRILLED_BLAZEFISH);
                entries.accept(GRILLED_MAGMA_CUBE_FISH);
                entries.accept(GRILLED_GLOWDINE);
                entries.accept(GRILLED_SOULSUCKER);

                entries.accept(BAKED_LAVA_PUFFERFISH_STEW);
                entries.accept(BAKED_OBSIDIANFISH_STEW);
                entries.accept(BAKED_SEARING_COD_STEW);
                entries.accept(BAKED_BLAZEFISH_STEW);
                entries.accept(BAKED_MAGMACUBEFISH_STEW);
                entries.accept(BAKED_GLOWDINE_STEW);
                entries.accept(BAKED_SOULSUCKER_STEW);

                entries.accept(NETHER_RICE_ROLL_MEDLEY_BLOCKITEM);
            })
            .icon(NETHER_RICE_ROLL_MEDLEY_BLOCKITEM::getDefaultInstance)
            .build();


    public static final CreativeModeTab NDU_TAB = creativeModeTab("netherdepthsupgrade_food", NDU_FARMERS_DELIGHT);
    private static Block registerBlock(String name, Block block){
        return Registry.register(BuiltInRegistries.BLOCK, prefix(name), block);
    }
    
    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, prefix(name), item);
    }

    private static CreativeModeTab creativeModeTab(String name, CreativeModeTab item) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, prefix(name), item);
    }

    private static Item registerBlockItem(Block block){
        return Registry.register(BuiltInRegistries.ITEM, prefix(block.getDescriptionId().replace("block.netherdepthsupgrade.", "").toString()),
                new NDUDescriptionBlockItem(block, new FabricItemSettings().fireproof()
                        , Component.translatable("item.netherdepthsupgrade.nether_rice_roll_medley_block.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));
    }

    static class NetherRiceRollMedleyBlock extends FeastBlock {
        public static final int MAX_SERVING = 8;
        public static final IntegerProperty ROLL_SERVINGS = IntegerProperty.create("servings", 0, 8);
        protected static final VoxelShape PLATE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);
        protected static final VoxelShape FOOD_SHAPE;
        public final List<Item> riceRollServings;

        public NetherRiceRollMedleyBlock() {
            super(Properties.copy(Blocks.CAKE), FarmersDelightCompat.SOULSUCKER_ROLL, true);
            this.riceRollServings = List.of(FarmersDelightCompat.GLOWDINE_ROLL, FarmersDelightCompat.LAVA_PUFFERFISH_ROLL, FarmersDelightCompat.SEARING_COD_ROLL, FarmersDelightCompat.SOULSUCKER_ROLL, FarmersDelightCompat.MAGMA_CUBE_FISH_ROLL, FarmersDelightCompat.WARPED_KELP_ROLL_SLICE, FarmersDelightCompat.WARPED_KELP_ROLL_SLICE, FarmersDelightCompat.WARPED_KELP_ROLL_SLICE);
        }

        public IntegerProperty getServingsProperty() {
            return ROLL_SERVINGS;
        }

        public int getMaxServings() {
            return 8;
        }

        public ItemStack getServingStack(BlockState state) {
            return new ItemStack(((ItemLike)this.riceRollServings.get((Integer)state.getValue(this.getServingsProperty()) - 1)));
        }

        public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            return (Integer)state.getValue(this.getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
        }

        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(new Property[]{FACING, ROLL_SERVINGS});
        }

        static {
            FOOD_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0, 2.0, 2.0, 14.0, 4.0, 14.0), BooleanOp.OR);
        }
    }
}
