package com.scouter.netherdepthsupgrade.modcompat;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.items.NDUDescriptionBlockItem;
import com.scouter.netherdepthsupgrade.items.NDUDescriptionItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class FarmersDelightCompat {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void setupCompat() {
        LOGGER.info("Setting up compat for Farmers Delight");

        ModChecker.farmersDelightPresent = true;

    }


    public static final Item.Properties foodBuilder() {
        return new Item.Properties().tab(creativeTabFood);
    }


    public static final DeferredRegister<Item> ITEMS_FARMERS_DELIGHT = DeferredRegister.create(ForgeRegistries.ITEMS, NetherDepthsUpgrade.MODID);
    public static final DeferredRegister<Block> BLOCKS_FARMERS_DELIGHT = DeferredRegister.create(ForgeRegistries.BLOCKS, NetherDepthsUpgrade.MODID);

    //SLICES
    public static final RegistryObject<Item> LAVA_PUFFERFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("lava_pufferfish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.LAVA_PUFFERFISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.lava_pufferfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> OBSIDIANFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("obsidianfish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.OBSIDIANFISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.obsidianfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> SEARING_COD_SLICE = ITEMS_FARMERS_DELIGHT.register("searing_cod_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SEARING_COD_SLICE),
            Component.translatable("item.netherdepthsupgrade.searing_cod_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BLAZEFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("blazefish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BLAZEFISH_SLICE),
            Component.translatable("item.netherdepthsupgrade.blazefish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> MAGMACUBEFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("magmacubefish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.MAGMA_CUBE_FISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.magmacubefish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));
    public static final RegistryObject<Item> GLOWDINE_SLICE = ITEMS_FARMERS_DELIGHT.register("glowdine_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GLOWDINE_SLICE)
            , Component.translatable("item.netherdepthsupgrade.glowdine_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> SOULSUCKER_SLICE = ITEMS_FARMERS_DELIGHT.register("soulsucker_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SOULSUCKER_SLICE)
            , Component.translatable("item.netherdepthsupgrade.soulsucker_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));


    //COOKED (after cooking)
    public static final RegistryObject<Item> COOKED_LAVA_PUFFERFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_lava_pufferfish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_LAVA_PUFFERFISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_lava_pufferfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> COOKED_OBSIDIANFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_obsidianfish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_OBSIDIANFISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_obsidianfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> COOKED_MAGMACUBEFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_magmacubefish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_MAGMA_CUBE_FISH_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_magmacubefish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> COOKED_GLOWDINE_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_glowdine_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_GLOWDINE_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_glowdine_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> COOKED_SOULSUCKER_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_soulsucker_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_SOULSUCKER_SLICE)
            , Component.translatable("item.netherdepthsupgrade.cooked_soulsucker_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    //RICE ROLLS
    public static final RegistryObject<Item> LAVA_PUFFERFISH_ROLL = ITEMS_FARMERS_DELIGHT.register("lava_pufferfish_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.LAVA_PUFFERFISH_ROLL),
                    Component.translatable("item.netherdepthsupgrade.lava_pufferfish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> OBSIDIANFISH_ROLL = ITEMS_FARMERS_DELIGHT.register("obsidianfish_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.OBSIDIANFISH_ROLL),
                    Component.translatable("item.netherdepthsupgrade.obsidianfish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> SEARING_COD_ROLL = ITEMS_FARMERS_DELIGHT.register("searing_cod_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SEARING_COD_ROLL),
                    Component.translatable("item.netherdepthsupgrade.searing_cod_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BLAZEFISH_ROLL = ITEMS_FARMERS_DELIGHT.register("blazefish_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BLAZEFISH_ROLL),
                    Component.translatable("item.netherdepthsupgrade.blazefish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> MAGMA_CUBE_FISH_ROLL = ITEMS_FARMERS_DELIGHT.register("magmacubefish_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.MAGMA_CUBE_FISH_ROLL),
                    Component.translatable("item.netherdepthsupgrade.magmacubefish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GLOWDINE_ROLL = ITEMS_FARMERS_DELIGHT.register("glowdine_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GLOWDINE_ROLL),
                    Component.translatable("item.netherdepthsupgrade.glowdine_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> SOULSUCKER_ROLL = ITEMS_FARMERS_DELIGHT.register("soulsucker_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SOULSUCKER_ROLL),
                    Component.translatable("item.netherdepthsupgrade.soulsucker_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> WARPED_KELP_ROLL = ITEMS_FARMERS_DELIGHT.register("warped_kelp_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.WARPED_KELP_ROLL),
                    Component.translatable("item.netherdepthsupgrade.warped_kelp_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));
    public static final RegistryObject<Item> WARPED_KELP_ROLL_SLICE = ITEMS_FARMERS_DELIGHT.register("warped_kelp_roll_slice",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.WARPED_KELP_ROLL_SLICE),
                    Component.translatable("item.netherdepthsupgrade.warped_kelp_roll_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    //GRILLED FOODS
    public static final RegistryObject<Item> GRILLED_LAVA_PUFFERFISH = ITEMS_FARMERS_DELIGHT.register("grilled_lava_pufferfish",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_LAVA_PUFFERFISH),
                    Component.translatable("item.netherdepthsupgrade.grilled_lava_pufferfish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_OBSIDIANFISH = ITEMS_FARMERS_DELIGHT.register("grilled_obsidianfish",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_OBSIDIANFISH),
                    Component.translatable("item.netherdepthsupgrade.grilled_obsidianfish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_SEARING_COD = ITEMS_FARMERS_DELIGHT.register("grilled_searing_cod",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_SEARING_COD),
                    Component.translatable("item.netherdepthsupgrade.grilled_searing_cod.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_BLAZEFISH = ITEMS_FARMERS_DELIGHT.register("grilled_blazefish",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_BLAZEFISH),
                    Component.translatable("item.netherdepthsupgrade.grilled_blazefish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_MAGMA_CUBE_FISH = ITEMS_FARMERS_DELIGHT.register("grilled_magmacubefish",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_MAGMA_CUBE_FISH),
                    Component.translatable("item.netherdepthsupgrade.grilled_magmacubefish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_GLOWDINE = ITEMS_FARMERS_DELIGHT.register("grilled_glowdine",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_GLOWDINE),
                    Component.translatable("item.netherdepthsupgrade.grilled_glowdine.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_SOULSUCKER = ITEMS_FARMERS_DELIGHT.register("grilled_soulsucker",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_SOULSUCKER),
                    Component.translatable("item.netherdepthsupgrade.grilled_soulsucker.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));



    //STEWS
    public static final RegistryObject<Item> BAKED_LAVA_PUFFERFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_lava_pufferfish_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_LAVA_PUFFERFISH_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_lava_pufferfish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_OBSIDIANFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_obsidianfish_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_OBSIDIANFISH_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_obsidianfish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_SEARING_COD_STEW = ITEMS_FARMERS_DELIGHT.register("baked_searing_cod_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_SEARING_COD_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_searing_cod_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_BLAZEFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_blazefish_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_BLAZEFISH_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_blazefish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_MAGMACUBEFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_magmacubefish_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_MAGMA_CUBE_FISH_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_magmacubefish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_GLOWDINE_STEW = ITEMS_FARMERS_DELIGHT.register("baked_glowdine_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_GLOWDINE_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_glowdine_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_SOULSUCKER_STEW = ITEMS_FARMERS_DELIGHT.register("baked_soulsucker_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_SOULSUCKER_STEW)
            , Component.translatable("item.netherdepthsupgrade.baked_soulsucker_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));


    //BLOCKS
    public static final RegistryObject<Block> NETHER_RICE_ROLL_MEDLEY_BLOCK = BLOCKS_FARMERS_DELIGHT.register("nether_rice_roll_medley_block", () -> new NetherRiceRollMedleyBlock(Block.Properties.copy(Blocks.CAKE)));

    //BLOCKITEM
    public static final RegistryObject<Item> NETHER_RICE_ROLL_MEDLEY_BLOCKITEM = fromBlockFireRes(NETHER_RICE_ROLL_MEDLEY_BLOCK);

    public static <B extends Block> RegistryObject<Item> fromBlockFireRes(RegistryObject<B> block) {
        return ITEMS_FARMERS_DELIGHT.register(block.getId().getPath(), () -> new NDUDescriptionBlockItem(block.get(), foodBuilder().fireResistant()
                , Component.translatable("item.netherdepthsupgrade.nether_rice_roll_medley_block.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    }
    public static CreativeModeTab creativeTabFood = new CreativeModeTab("netherdepthsupgrade_food") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(NETHER_RICE_ROLL_MEDLEY_BLOCKITEM.get());
        }
    };


     static class NetherRiceRollMedleyBlock extends FeastBlock {

        public static final IntegerProperty ROLL_SERVINGS = IntegerProperty.create("servings", 0, 8);
        protected static final VoxelShape PLATE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0);
        protected static final VoxelShape FOOD_SHAPE;
        public final List<Supplier<Item>> riceRollServings;

        public NetherRiceRollMedleyBlock(BlockBehaviour.Properties properties) {
            super(properties, FarmersDelightCompat.SOULSUCKER_ROLL, true);
            this.riceRollServings = Arrays.asList(FarmersDelightCompat.GLOWDINE_ROLL, FarmersDelightCompat.LAVA_PUFFERFISH_ROLL, FarmersDelightCompat.SEARING_COD_ROLL, FarmersDelightCompat.SOULSUCKER_ROLL, FarmersDelightCompat.MAGMA_CUBE_FISH_ROLL, FarmersDelightCompat.WARPED_KELP_ROLL_SLICE, FarmersDelightCompat.WARPED_KELP_ROLL_SLICE, FarmersDelightCompat.WARPED_KELP_ROLL_SLICE);
        }

        public IntegerProperty getServingsProperty() {
            return ROLL_SERVINGS;
        }

        public int getMaxServings() {
            return 8;
        }

        public ItemStack getServingItem(BlockState state) {
            return new ItemStack((ItemLike)((Supplier)this.riceRollServings.get((Integer)state.getValue(this.getServingsProperty()) - 1)).get());
        }

        public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
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
