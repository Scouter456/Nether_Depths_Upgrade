package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.modcompat.FarmersDelightCompat;
import com.scouter.netherdepthsupgrade.utils.NDUTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;
import static net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance.hasItems;

public class RecipeGenerator extends RecipeProvider implements IConditionBuilder {

    public static final int FAST_COOKING = 100;		// 5 seconds
    public static final int NORMAL_COOKING = 200;	// 10 seconds
    public static final int SLOW_COOKING = 400;		// 20 seconds

    public RecipeGenerator(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(NDUItems.SOUL_SUCKER_LEATHER.get(), 3)
                .requires(Items.SHEARS)
                .requires(NDUItems.SOULSUCKER.get())
                .unlockedBy("has_soul_sucker", has(NDUItems.SOULSUCKER.get()))
                .save(consumer, prefix(NDUItems.SOUL_SUCKER_LEATHER.get().getDescriptionId().replaceAll("item.netherdepthsupgrade.", "")));

        ShapelessRecipeBuilder.shapeless(NDUItems.FORTRESS_GROUPER_PLATE.get(), 4)
                .requires(Items.SHEARS)
                .requires(NDUItems.FORTRESS_GROUPER.get())
                .unlockedBy("has_fortress_grouper", has(NDUItems.FORTRESS_GROUPER.get()))
                .save(consumer, prefix(NDUItems.FORTRESS_GROUPER_PLATE.get().getDescriptionId().replaceAll("item.netherdepthsupgrade.", "")));

        ShapelessRecipeBuilder.shapeless(NDUItems.EYEBALL_FISH_EYE.get(), 1)
                .requires(Items.SHEARS)
                .requires(NDUItems.EYEBALL_FISH.get())
                .unlockedBy("has_eyeball_fish", has(NDUItems.EYEBALL_FISH.get()))
                .save(consumer, prefix(NDUItems.EYEBALL_FISH_EYE.get().getDescriptionId().replaceAll("item.netherdepthsupgrade.", "")));





        ShapelessRecipeBuilder.shapeless(Items.BONE_MEAL, 3)
                .requires(NDUItems.BONEFISH.get())
                .unlockedBy("has_bonefish", has(NDUItems.BONEFISH.get()))
                .save(consumer,prefix(Items.BONE_MEAL.getDescriptionId().replaceAll("item.minecraft.", "")));
        ShapelessRecipeBuilder.shapeless(Items.OBSIDIAN, 1)
                .requires(NDUItems.OBSIDIANFISH.get())
                .unlockedBy("has_obsidianfish", has(NDUItems.OBSIDIANFISH.get()))
                .save(consumer,prefix(Items.OBSIDIAN.getDescriptionId().replaceAll("block.minecraft.", "")));
        ShapelessRecipeBuilder.shapeless(Items.BLAZE_POWDER, 2)
                .requires(NDUItems.BLAZEFISH.get())
                .unlockedBy("has_blazefish", has(NDUItems.BLAZEFISH.get()))
                .save(consumer, prefix(Items.BLAZE_POWDER.getDescriptionId().replaceAll("item.minecraft.", "")));
        ShapelessRecipeBuilder.shapeless(Items.GLOWSTONE_DUST, 2)
                .requires(NDUItems.GLOWDINE.get())
                .unlockedBy("has_glowdine", has(NDUItems.GLOWDINE.get()))
                .save(consumer, prefix(Items.GLOWSTONE_DUST.getDescriptionId().replaceAll("item.minecraft.", "")));
        ShapelessRecipeBuilder.shapeless(Items.WITHER_ROSE, 1)
                .requires(NDUItems.WITHER_BONEFISH.get(), 8)
                .requires(Items.RED_TULIP)
                .unlockedBy("has_witherbonefish", has(NDUItems.WITHER_BONEFISH.get()))
                .save(consumer, prefix(Items.WITHER_ROSE.getDescriptionId().replaceAll("block.minecraft.", "")));

        ShapelessRecipeBuilder.shapeless(Items.ENDER_EYE, 1)
                .requires(NDUItems.EYEBALL_FISH_EYE.get(), 1)
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_eyeball_fish_eye", has(NDUItems.EYEBALL_FISH_EYE.get()))
                .save(consumer, prefix(Items.ENDER_EYE.getDescriptionId().replaceAll("item.minecraft.", "")));

        ShapelessRecipeBuilder.shapeless(Items.COAL, 2)
                .requires(NDUItems.SEARING_COD.get(), 1)
                .unlockedBy("has_searing_cod", has(NDUItems.SEARING_COD.get()))
                .save(consumer, prefix(Items.COAL.getDescriptionId().replaceAll("item.minecraft.", "")));

        ShapedRecipeBuilder.shaped(NDUItems.SOUL_SUCKER_BOOTS.get())
                .define('s', Items.STRING)
                .define('L', NDUItems.SOUL_SUCKER_LEATHER.get())
                .pattern("sLs")
                .pattern("LLL")
                .unlockedBy("has_soul_sucker_leather", has(NDUItems.SOUL_SUCKER_LEATHER.get()))
                .save(consumer,  prefix(NDUItems.SOUL_SUCKER_BOOTS.get().getDescriptionId().replaceAll("item.netherdepthsupgrade.", "")));
        ShapedRecipeBuilder.shaped(NDUItems.LAVA_FISHING_ROD.get())
                .define('b', Items.BLAZE_ROD)
                .define('n', Items.NETHERITE_SCRAP)
                .define('c', Items.CHAIN)
                .pattern(" nb")
                .pattern("nbc")
                .pattern("b c")
                .unlockedBy("has_blaze_rod", has(Items.BLAZE_ROD))
                .unlockedBy("has_netherite_scrap", has(Items.NETHERITE_SCRAP))
                .unlockedBy("has_chain", has(Items.CHAIN))
                .save(consumer,  prefix(NDUItems.LAVA_FISHING_ROD.get().getDescriptionId().replaceAll("item.netherdepthsupgrade.", "")));

        ShapedRecipeBuilder.shaped(NDUItems.WARPED_KELP_CARPET_BLOCK.get(), 3)
                .define('c', NDUItems.WARPED_KELP_BLOCK.get())
                .pattern("ccc")
                .unlockedBy("has_warped_kelp_block", has(NDUItems.WARPED_KELP_BLOCK.get()))
                .save(consumer,  prefix(NDUItems.WARPED_KELP_CARPET_BLOCK.get().getDescriptionId().replaceAll("block.netherdepthsupgrade.", "")));

        ShapedRecipeBuilder.shaped(NDUItems.CRIMSON_KELP_CARPET_BLOCK.get(), 3)
                .define('c', NDUItems.CRIMSON_KELP_BLOCK.get())
                .pattern("ccc")
                .unlockedBy("has_crimson_kelp_block", has(NDUItems.CRIMSON_KELP_BLOCK.get()))
                .save(consumer,  prefix(NDUItems.CRIMSON_KELP_BLOCK.get().getDescriptionId().replaceAll("block.netherdepthsupgrade.", "")));

        ShapedRecipeBuilder.shaped(NDUItems.LAVA_GLASS.get(), 32)
                .define('c', NDUItems.FORTRESS_GROUPER_PLATE.get())
                .define('e', NDUItems.EYEBALL_FISH_EYE.get())
                .define('g', Items.GLASS)
                .pattern("ece")
                .pattern("cgc")
                .pattern("ece")
                .unlockedBy("has_fortress_grouper_plate", has(NDUItems.FORTRESS_GROUPER_PLATE.get()))
                .unlockedBy("has_eyeball_fish_eye", has(NDUItems.EYEBALL_FISH_EYE.get()))
                .unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer,  prefix(NDUItems.LAVA_GLASS.get().getDescriptionId().replaceAll("block.netherdepthsupgrade.", "")));

        ShapedRecipeBuilder.shaped(Items.NETHER_BRICK, 4)
                .define('c', NDUItems.FORTRESS_GROUPER_PLATE.get())
                .pattern("cc")
                .pattern("cc")
                .unlockedBy("has_fortress_grouper_plate", has(NDUItems.FORTRESS_GROUPER_PLATE.get()))
                .save(consumer,  prefix(Items.NETHER_BRICK.getDescriptionId().replaceAll("item.minecraft.", "")));


        nineBlockStorageRecipes(consumer, NDUItems.WARPED_KELP.get(),NDUItems.WARPED_KELP_BLOCK.get());
        nineBlockStorageRecipes(consumer, NDUItems.CRIMSON_KELP.get(),NDUItems.CRIMSON_KELP_BLOCK.get());

        farmersDelightRecipes(consumer);
        cookMeals(consumer);
        cuttingAnimalItems(consumer);
        smeltingRecipes(consumer);
    }

    private void farmersDelightRecipes(Consumer<FinishedRecipe> consumer){
        wrap(ShapelessRecipeBuilder.shapeless(ModItems.NETHER_SALAD.get())
                        .requires(NDUTags.Items.NETHER_SALAD_FOODS)
                        .requires(NDUTags.Items.NETHER_SALAD_FOODS)
                        .requires(Items.BOWL)
                        .unlockedBy("has_bowl", hasItems(Items.BOWL))
                , "food/nether_salad", consumer, modLoaded("farmersdelight"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.BLAZEFISH_ROLL.get())
                        .requires(FarmersDelightCompat.BLAZEFISH_SLICE.get(), 2)
                        .requires(ModItems.COOKED_RICE.get())
                        .unlockedBy("has_blazefish_slice", hasItems(FarmersDelightCompat.BLAZEFISH_SLICE.get()))
                , "food/blazefish_roll", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cooked_rice"),
                itemExists(NetherDepthsUpgrade.MODID, "blazefish_slice"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.SEARING_COD_ROLL.get())
                        .requires(FarmersDelightCompat.SEARING_COD_SLICE.get(), 2)
                        .requires(ModItems.COOKED_RICE.get())
                        .unlockedBy("has_searing_cod_slice", hasItems(FarmersDelightCompat.SEARING_COD_SLICE.get()))
                , "food/searing_cod_roll", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cooked_rice"),
                itemExists(NetherDepthsUpgrade.MODID, "searing_cod_slice"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.OBSIDIANFISH_ROLL.get())
                        .requires(FarmersDelightCompat.OBSIDIANFISH_SLICE.get(), 2)
                        .requires(ModItems.COOKED_RICE.get())
                        .unlockedBy("has_obsidianfish_slice", hasItems(FarmersDelightCompat.OBSIDIANFISH_SLICE.get()))
                , "food/obsidianfish_roll", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cooked_rice"),
                itemExists(NetherDepthsUpgrade.MODID, "obsidianfish_slice"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.GLOWDINE_ROLL.get())
                        .requires(FarmersDelightCompat.GLOWDINE_SLICE.get(), 2)
                        .requires(ModItems.COOKED_RICE.get())
                        .unlockedBy("has_glowdine_slice", hasItems(FarmersDelightCompat.GLOWDINE_SLICE.get()))
                , "food/glowdine_roll", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cooked_rice"),
                itemExists(NetherDepthsUpgrade.MODID, "glowdine_slice"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.LAVA_PUFFERFISH_ROLL.get())
                        .requires(FarmersDelightCompat.LAVA_PUFFERFISH_SLICE.get(), 2)
                        .requires(ModItems.COOKED_RICE.get())
                        .unlockedBy("has_lava_pufferfish_slice", hasItems(FarmersDelightCompat.LAVA_PUFFERFISH_SLICE.get()))
                , "food/lava_pufferfish_roll", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cooked_rice"),
                itemExists(NetherDepthsUpgrade.MODID, "lava_pufferfish_slice"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.SOULSUCKER_ROLL.get())
                        .requires(FarmersDelightCompat.SOULSUCKER_SLICE.get(), 2)
                        .requires(ModItems.COOKED_RICE.get())
                        .unlockedBy("has_soulsucker_slice", hasItems(FarmersDelightCompat.SOULSUCKER_SLICE.get()))
                , "food/soulsucker_roll", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cooked_rice"),
                itemExists(NetherDepthsUpgrade.MODID, "soulsucker_slice"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.MAGMA_CUBE_FISH_ROLL.get())
                        .requires(FarmersDelightCompat.MAGMACUBEFISH_SLICE.get(), 2)
                        .requires(ModItems.COOKED_RICE.get())
                        .unlockedBy("has_magmacubefish_slice", hasItems(FarmersDelightCompat.MAGMACUBEFISH_SLICE.get()))
                , "food/magmacubefish_roll", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cooked_rice"),
                itemExists(NetherDepthsUpgrade.MODID, "magmacubefish_slice"));
//
        wrap(ShapedRecipeBuilder.shaped(FarmersDelightCompat.WARPED_KELP_ROLL.get(), 1)
                        .pattern("RXR")
                        .pattern("###")
                        .define('#', NDUItems.WARPED_KELP.get())
                        .define('R', ModItems.COOKED_RICE.get())
                        .define('X', Items.CARROT)
                        .unlockedBy("has_warped_kelp", InventoryChangeTrigger.TriggerInstance.hasItems(NDUItems.WARPED_KELP.get())),
                "food/warped_kelp_roll", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cooked_rice"),
                itemExists(NetherDepthsUpgrade.MODID, "warped_kelp"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.NETHER_RICE_ROLL_MEDLEY_BLOCKITEM.get())
                        .requires(FarmersDelightCompat.WARPED_KELP_ROLL_SLICE.get())
                        .requires(FarmersDelightCompat.WARPED_KELP_ROLL_SLICE.get())
                        .requires(FarmersDelightCompat.WARPED_KELP_ROLL_SLICE.get())
                        .requires(FarmersDelightCompat.LAVA_PUFFERFISH_ROLL.get())
                        .requires(FarmersDelightCompat.SEARING_COD_ROLL.get())
                        .requires(FarmersDelightCompat.MAGMA_CUBE_FISH_ROLL.get())
                        .requires(FarmersDelightCompat.GLOWDINE_ROLL.get())
                        .requires(Items.BOWL)
                        .requires(FarmersDelightCompat.SOULSUCKER_ROLL.get())
                        .unlockedBy("has_rice_roll", InventoryChangeTrigger.TriggerInstance.hasItems(FarmersDelightCompat.WARPED_KELP_ROLL_SLICE.get(), FarmersDelightCompat.LAVA_PUFFERFISH_ROLL.get(), FarmersDelightCompat.SEARING_COD_ROLL.get(),FarmersDelightCompat.MAGMA_CUBE_FISH_ROLL.get(),FarmersDelightCompat.GLOWDINE_ROLL.get(),FarmersDelightCompat.SOULSUCKER_ROLL.get()))
                ,"food/nether_rice_roll_medley_block", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cooked_rice"),
                itemExists(NetherDepthsUpgrade.MODID, "warped_kelp_roll_slice"),
                itemExists(NetherDepthsUpgrade.MODID, "lava_pufferfish_roll"),
                itemExists(NetherDepthsUpgrade.MODID, "searing_cod_roll"),
                itemExists(NetherDepthsUpgrade.MODID, "magmacubefish_roll"),
                itemExists(NetherDepthsUpgrade.MODID, "glowdine_roll"),
                itemExists(NetherDepthsUpgrade.MODID, "soulsucker_roll"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.GRILLED_BLAZEFISH.get())
                        .requires(FarmersDelightCompat.BLAZEFISH_SLICE.get())
                        .requires(Items.SWEET_BERRIES)
                        .requires(Items.BOWL)
                        .requires(ForgeTags.CROPS_CABBAGE)
                        .requires(ForgeTags.CROPS_ONION)
                        .unlockedBy("has_blazefish",InventoryChangeTrigger.TriggerInstance.hasItems(NDUItems.BLAZEFISH.get()))
                , "food/grilled_blazefish", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cabbage"),
                itemExists("farmersdelight", "onion"),
                itemExists(NetherDepthsUpgrade.MODID, "blazefish_slice"),
                itemExists(NetherDepthsUpgrade.MODID, "grilled_blazefish"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.GRILLED_GLOWDINE.get())
                        .requires(FarmersDelightCompat.COOKED_GLOWDINE_SLICE.get())
                        .requires(Items.SWEET_BERRIES)
                        .requires(Items.BOWL)
                        .requires(ForgeTags.CROPS_CABBAGE)
                        .requires(ForgeTags.CROPS_ONION)
                        .unlockedBy("has_glowdine",InventoryChangeTrigger.TriggerInstance.hasItems(NDUItems.GLOWDINE.get()))
                , "food/grilled_glowdine", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cabbage"),
                itemExists("farmersdelight", "onion"),
                itemExists(NetherDepthsUpgrade.MODID, "cooked_glowdine_slice"),
                itemExists(NetherDepthsUpgrade.MODID, "grilled_glowdine"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.GRILLED_LAVA_PUFFERFISH.get())
                        .requires(FarmersDelightCompat.COOKED_LAVA_PUFFERFISH_SLICE.get())
                        .requires(Items.SWEET_BERRIES)
                        .requires(Items.BOWL)
                        .requires(ForgeTags.CROPS_CABBAGE)
                        .requires(ForgeTags.CROPS_ONION)
                        .unlockedBy("has_lava_pufferfish",InventoryChangeTrigger.TriggerInstance.hasItems(NDUItems.LAVA_PUFFERFISH.get()))
                , "food/grilled_lava_pufferfish", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cabbage"),
                itemExists("farmersdelight", "onion"),
                itemExists(NetherDepthsUpgrade.MODID, "cooked_lava_pufferfish_slice"),
                itemExists(NetherDepthsUpgrade.MODID, "grilled_lava_pufferfish"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.GRILLED_OBSIDIANFISH.get())
                        .requires(FarmersDelightCompat.COOKED_OBSIDIANFISH_SLICE.get())
                        .requires(Items.SWEET_BERRIES)
                        .requires(Items.BOWL)
                        .requires(ForgeTags.CROPS_CABBAGE)
                        .requires(ForgeTags.CROPS_ONION)
                        .unlockedBy("has_obsidianfish",InventoryChangeTrigger.TriggerInstance.hasItems(NDUItems.OBSIDIANFISH.get()))
                , "food/grilled_obsidianfish", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cabbage"),
                itemExists("farmersdelight", "onion"),
                itemExists(NetherDepthsUpgrade.MODID, "cooked_obsidianfish_slice"),
                itemExists(NetherDepthsUpgrade.MODID, "grilled_obsidianfish"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.GRILLED_SOULSUCKER.get())
                        .requires(FarmersDelightCompat.COOKED_SOULSUCKER_SLICE.get())
                        .requires(Items.SWEET_BERRIES)
                        .requires(Items.BOWL)
                        .requires(ForgeTags.CROPS_CABBAGE)
                        .requires(ForgeTags.CROPS_ONION)
                        .unlockedBy("has_soulsucker",InventoryChangeTrigger.TriggerInstance.hasItems(NDUItems.SOULSUCKER.get()))
                , "food/grilled_soulsucker", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cabbage"),
                itemExists("farmersdelight", "onion"),
                itemExists(NetherDepthsUpgrade.MODID, "cooked_soulsucker_slice"),
                itemExists(NetherDepthsUpgrade.MODID, "grilled_soulsucker"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.GRILLED_SEARING_COD.get())
                        .requires(FarmersDelightCompat.SEARING_COD_SLICE.get())
                        .requires(Items.SWEET_BERRIES)
                        .requires(Items.BOWL)
                        .requires(ForgeTags.CROPS_CABBAGE)
                        .requires(ForgeTags.CROPS_ONION)
                        .unlockedBy("has_searing_cod",InventoryChangeTrigger.TriggerInstance.hasItems(NDUItems.SEARING_COD.get()))
                , "food/grilled_searing_cod", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cabbage"),
                itemExists("farmersdelight", "onion"),
                itemExists(NetherDepthsUpgrade.MODID, "searing_cod_slice"),
                itemExists(NetherDepthsUpgrade.MODID, "grilled_searing_cod"));
//
        wrap(ShapelessRecipeBuilder.shapeless(FarmersDelightCompat.GRILLED_MAGMA_CUBE_FISH.get())
                        .requires(FarmersDelightCompat.COOKED_MAGMACUBEFISH_SLICE.get())
                        .requires(Items.SWEET_BERRIES)
                        .requires(Items.BOWL)
                        .requires(ForgeTags.CROPS_CABBAGE)
                        .requires(ForgeTags.CROPS_ONION)
                        .unlockedBy("has_magmacubefish",InventoryChangeTrigger.TriggerInstance.hasItems(NDUItems.MAGMACUBEFISH.get()))
                , "food/grilled_magmacubefish", consumer,
                modLoaded("farmersdelight"),
                itemExists("farmersdelight", "cabbage"),
                itemExists("farmersdelight", "onion"),
                itemExists(NetherDepthsUpgrade.MODID, "cooked_magmacubefish_slice"),
                itemExists(NetherDepthsUpgrade.MODID, "grilled_magmacubefish"));
//
    }
//
    private void cookMeals(Consumer<FinishedRecipe> consumer) {
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(FarmersDelightCompat.BAKED_LAVA_PUFFERFISH_STEW.get(), 1, NORMAL_COOKING, 0.35F)
                        .addIngredient(FarmersDelightCompat.LAVA_PUFFERFISH_SLICE.get())
                        .addIngredient(Items.POTATO)
                        .addIngredient(ForgeTags.EGGS)
                        .addIngredient(ForgeTags.CROPS_TOMATO)
                ,"cooking/baked_lava_pufferfish_stew", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "lava_pufferfish_slice"),itemExists(NetherDepthsUpgrade.MODID, "baked_lava_pufferfish_stew"));
//
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(FarmersDelightCompat.BAKED_SEARING_COD_STEW.get(), 1, NORMAL_COOKING, 0.35F)
                        .addIngredient(FarmersDelightCompat.SEARING_COD_SLICE.get())
                        .addIngredient(Items.POTATO)
                        .addIngredient(ForgeTags.EGGS)
                        .addIngredient(ForgeTags.CROPS_TOMATO)
                ,"cooking/baked_searing_cod_stew", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "searing_cod_slice"),itemExists(NetherDepthsUpgrade.MODID, "baked_searing_cod_stew"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(FarmersDelightCompat.BAKED_GLOWDINE_STEW.get(), 1, NORMAL_COOKING, 0.35F)
                        .addIngredient(FarmersDelightCompat.GLOWDINE_SLICE.get())
                        .addIngredient(Items.POTATO)
                        .addIngredient(ForgeTags.EGGS)
                        .addIngredient(ForgeTags.CROPS_TOMATO)
                ,"cooking/baked_glowdine_stew", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "glowdine_slice"),itemExists(NetherDepthsUpgrade.MODID, "baked_glowdine_stew"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(FarmersDelightCompat.BAKED_BLAZEFISH_STEW.get(), 1, NORMAL_COOKING, 0.35F)
                        .addIngredient(FarmersDelightCompat.BLAZEFISH_SLICE.get())
                        .addIngredient(Items.POTATO)
                        .addIngredient(ForgeTags.EGGS)
                        .addIngredient(ForgeTags.CROPS_TOMATO)
                ,"cooking/baked_blazefish_stew", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "blazefish_slice"),itemExists(NetherDepthsUpgrade.MODID, "baked_blazefish_stew"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(FarmersDelightCompat.BAKED_MAGMACUBEFISH_STEW.get(), 1, NORMAL_COOKING, 0.35F)
                        .addIngredient(FarmersDelightCompat.MAGMACUBEFISH_SLICE.get())
                        .addIngredient(Items.POTATO)
                        .addIngredient(ForgeTags.EGGS)
                        .addIngredient(ForgeTags.CROPS_TOMATO)
                ,"cooking/baked_magmacubefish_stew", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "magmacubefish_slice"),itemExists(NetherDepthsUpgrade.MODID, "baked_magmacubefish_stew"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(FarmersDelightCompat.BAKED_OBSIDIANFISH_STEW.get(), 1, NORMAL_COOKING, 0.35F)
                        .addIngredient(FarmersDelightCompat.OBSIDIANFISH_SLICE.get())
                        .addIngredient(Items.POTATO)
                        .addIngredient(ForgeTags.EGGS)
                        .addIngredient(ForgeTags.CROPS_TOMATO)
                ,"cooking/baked_obsidianfish_stew", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "obsidianfish_slice"),itemExists(NetherDepthsUpgrade.MODID, "baked_obsidianfish_stew"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(FarmersDelightCompat.BAKED_SOULSUCKER_STEW.get(), 1, NORMAL_COOKING, 0.35F)
                        .addIngredient(FarmersDelightCompat.SOULSUCKER_SLICE.get())
                        .addIngredient(Items.POTATO)
                        .addIngredient(ForgeTags.EGGS)
                        .addIngredient(ForgeTags.CROPS_TOMATO)
                ,"cooking/baked_soulsucker_stew", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "soulsucker_slice"),itemExists(NetherDepthsUpgrade.MODID, "baked_soulsucker_stew"));
    }
//
    private void cuttingAnimalItems(Consumer<FinishedRecipe> consumer) {
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.BLAZEFISH.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.BLAZEFISH_SLICE.get(), 2)
                        .addResult(Items.BONE_MEAL)
                ,"cutting/blazefish", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "blazefish_slice"));
//
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.OBSIDIANFISH.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.OBSIDIANFISH_SLICE.get(), 2)
                        .addResult(Items.BONE_MEAL)
                ,"cutting/obsidianfish", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "obsidianfish_slice"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.SEARING_COD.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.SEARING_COD_SLICE.get(), 2)
                        .addResult(Items.BONE_MEAL)
                , "cutting/searing_cod", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "searing_cod_slice"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.SOULSUCKER.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.SOULSUCKER_SLICE.get(), 2)
                        .addResult(Items.BONE_MEAL)
                , "cutting/soulsucker", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "soulsucker_slice"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.LAVA_PUFFERFISH.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.LAVA_PUFFERFISH_SLICE.get(), 2)
                        .addResult(Items.BONE_MEAL)
                , "cutting/lava_pufferfish", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "lava_pufferfish_slice"));
//
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.MAGMACUBEFISH.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.MAGMACUBEFISH_SLICE.get(), 2)
                        .addResult(Items.BONE_MEAL)
                , "cutting/magmacubefish", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "magmacubefish_slice"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.GLOWDINE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.GLOWDINE_SLICE.get(), 2)
                        .addResult(Items.BONE_MEAL)
                , "cutting/glowdine", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "glowdine_slice"));
//
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FarmersDelightCompat.WARPED_KELP_ROLL.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.WARPED_KELP_ROLL_SLICE.get(), 3),
                "cutting/warped_kelp_roll_slice", consumer,
                modLoaded("farmersdelight"),
                itemExists(NetherDepthsUpgrade.MODID, "warped_kelp_roll"));
    }
    private void smeltingRecipes(Consumer<FinishedRecipe> consumer) {
        foodSmeltingRecipes("cooked_soulsucker_slice", FarmersDelightCompat.SOULSUCKER_SLICE.get(), FarmersDelightCompat.COOKED_SOULSUCKER_SLICE.get(), 0.35F, consumer);
        foodSmeltingRecipes("cooked_obsidianfish_slice", FarmersDelightCompat.OBSIDIANFISH_SLICE.get(), FarmersDelightCompat.COOKED_OBSIDIANFISH_SLICE.get(), 0.35F, consumer);
        foodSmeltingRecipes("cooked_magmacubefish_slice", FarmersDelightCompat.MAGMACUBEFISH_SLICE.get(), FarmersDelightCompat.COOKED_MAGMACUBEFISH_SLICE.get(), 0.35F, consumer);
        foodSmeltingRecipes("cooked_glowdine_slice", FarmersDelightCompat.GLOWDINE_SLICE.get(), FarmersDelightCompat.COOKED_GLOWDINE_SLICE.get(), 0.35F, consumer);
        foodSmeltingRecipes("cooked_lava_pufferfish_slice", FarmersDelightCompat.LAVA_PUFFERFISH_SLICE.get(), FarmersDelightCompat.COOKED_LAVA_PUFFERFISH_SLICE.get(), 0.35F, consumer);
    }
//
    private void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        String namePrefix = new ResourceLocation(FarmersDelight.MODID, name).toString();
        wrap(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient),
                                result, experience, 200)
                        .unlockedBy(name, hasItems(ingredient))
                ,name, consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, ingredient.toString()) , itemExists(NetherDepthsUpgrade.MODID, result.toString()));
        wrap(SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
                                result, experience, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                        .unlockedBy(name, hasItems(ingredient))
                ,name + "_from_campfire_cooking", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, ingredient.toString()) , itemExists(NetherDepthsUpgrade.MODID, result.toString()));
        wrap(SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
                                result, experience, 100, RecipeSerializer.SMOKING_RECIPE)
                        .unlockedBy(name, hasItems(ingredient))
                ,name + "_from_smoking", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, ingredient.toString()) , itemExists(NetherDepthsUpgrade.MODID, result.toString()));
    }
    private void wrap(CuttingBoardRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        wrap(builder, NetherDepthsUpgrade.MODID, name, consumer, conds);
    }
//
    private void wrap(CuttingBoardRecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        ResourceLocation loc = new ResourceLocation(modid, name);
        ConditionalRecipe.Builder cond;
        if (conds.length > 1) {
            cond = ConditionalRecipe.builder().addCondition(and(conds));
        } else if (conds.length == 1) {
            cond = ConditionalRecipe.builder().addCondition(conds[0]);
        } else {
            cond = ConditionalRecipe.builder();
        }
        FinishedRecipe[] recipe = new FinishedRecipe[1];
        builder.build(f -> recipe[0] = f, loc);
        cond.addRecipe(recipe[0])
                .build(consumer, loc);
    }
//
    private void wrap(CookingPotRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        wrap(builder, NetherDepthsUpgrade.MODID, name, consumer, conds);
    }
//
    private void wrap(CookingPotRecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        ResourceLocation loc = new ResourceLocation(modid, name);
        ConditionalRecipe.Builder cond;
        if (conds.length > 1) {
            cond = ConditionalRecipe.builder().addCondition(and(conds));
        } else if (conds.length == 1) {
            cond = ConditionalRecipe.builder().addCondition(conds[0]);
        } else {
            cond = ConditionalRecipe.builder();
        }
        FinishedRecipe[] recipe = new FinishedRecipe[1];
        builder.build(f -> recipe[0] = f, loc);
        cond.addRecipe(recipe[0])
                .build(consumer, loc);
    }
//
    private void wrap(RecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        wrap(builder, NetherDepthsUpgrade.MODID, name, consumer, conds);
    }
//
    private void wrap(RecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        ResourceLocation loc = new ResourceLocation(modid, name);
        ConditionalRecipe.Builder cond;
        if (conds.length > 1) {
            cond = ConditionalRecipe.builder().addCondition(and(conds));
        } else if (conds.length == 1) {
            cond = ConditionalRecipe.builder().addCondition(conds[0]);
        } else {
            cond = ConditionalRecipe.builder();
        }
        FinishedRecipe[] recipe = new FinishedRecipe[1];
        builder.save(f -> recipe[0] = f, loc);
        cond.addRecipe(recipe[0])
                .generateAdvancement()
                .build(consumer, loc);
    }
}
