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

public class RecipeGenerator extends RecipeProvider implements IConditionBuilder {
    public RecipeGenerator(DataGenerator pGenerator) {
        super(pGenerator);
    }
    public static final int FAST_COOKING = 100;		// 5 seconds
    public static final int NORMAL_COOKING = 200;	// 10 seconds
    public static final int SLOW_COOKING = 400;		// 20 seconds
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(NDUItems.SOUL_SUCKER_LEATHER.get(), 3)
                .requires(Items.SHEARS)
                .requires(NDUItems.SOULSUCKER.get())
                .unlockedBy("has_soul_sucker", has(NDUItems.SOULSUCKER.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Items.BLAZE_POWDER, 2)
                .requires(NDUItems.BLAZEFISH.get())
                .unlockedBy("has_blazefish", has(NDUItems.BLAZEFISH.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(Items.GLOWSTONE_DUST, 2)
                .requires(NDUItems.GLOWDINE.get())
                .unlockedBy("has_glowdine", has(NDUItems.GLOWDINE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(Items.WITHER_ROSE, 1)
                .requires(NDUItems.WITHER_BONEFISH.get(), 8)
                .requires(Items.RED_TULIP)
                .unlockedBy("has_witherbonefish", has(NDUItems.WITHER_BONEFISH.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(NDUItems.SOUL_SUCKER_BOOTS.get())
                .define('s', Items.STRING)
                .define('L', NDUItems.SOUL_SUCKER_LEATHER.get())
                .pattern("sLs")
                        .pattern("LLL")
                .unlockedBy("has_soul_sucker_leather", has(NDUItems.SOUL_SUCKER_LEATHER.get()))
                .save(consumer);
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
                .save(consumer);

        nineBlockStorageRecipes(consumer, NDUItems.WARPED_KELP.get(),NDUItems.WARPED_KELP_BLOCK.get());
        //simpleCookingRecipe(consumer, "smelting", RecipeSerializer.SMELTING_RECIPE, 100, NDUItems.CUT_ONION.get(), NDUItems.COOKED_CUT_ONION.get(), 0.50F);
        //simpleCookingRecipe(consumer, "smelting", RecipeSerializer.SMELTING_RECIPE, 100, NDUItems.GARLIC_CLOVES.get(), NDUItems.COOKED_GARLIC_CLOVES.get(), 0.50F);
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
                .unlockedBy("has_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BOWL))
                , "food/nether_salad", consumer, modLoaded("farmersdelight"));
    }

    private void cookMeals(Consumer<FinishedRecipe> consumer) {
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(FarmersDelightCompat.BAKED_LAVA_PUFFERFISH_STEW.get(), 1, NORMAL_COOKING, 0.35F)
                .addIngredient(FarmersDelightCompat.LAVA_PUFFERFISH_SLICE.get())
                .addIngredient(Items.POTATO)
                .addIngredient(ForgeTags.EGGS)
                .addIngredient(ForgeTags.CROPS_TOMATO)
                ,"cooking/baked_lava_pufferfish_stew", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "lava_pufferfish_slice"),itemExists(NetherDepthsUpgrade.MODID, "baked_lava_pufferfish_stew"));

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

    private void cuttingAnimalItems(Consumer<FinishedRecipe> consumer) {
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.BLAZEFISH.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.BLAZEFISH_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                ,"cutting/blazefish", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "blazefish_slice"));

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

        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.MAGMACUBEFISH.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.MAGMACUBEFISH_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                , "cutting/magmacubefish", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "magmacubefish_slice"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDUItems.GLOWDINE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), FarmersDelightCompat.GLOWDINE_SLICE.get(), 2)
                .addResult(Items.BONE_MEAL)
                , "cutting/glowdine", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, "glowdine_slice"));

    }
    private void smeltingRecipes(Consumer<FinishedRecipe> consumer) {
        foodSmeltingRecipes("cooked_soulsucker_slice", FarmersDelightCompat.SOULSUCKER_SLICE.get(), FarmersDelightCompat.COOKED_SOULSUCKER_SLICE.get(), 0.35F, consumer);
        foodSmeltingRecipes("cooked_obsidianfish_slice", FarmersDelightCompat.OBSIDIANFISH_SLICE.get(), FarmersDelightCompat.COOKED_OBSIDIANFISH_SLICE.get(), 0.35F, consumer);
        foodSmeltingRecipes("cooked_magmacubefish_slice", FarmersDelightCompat.MAGMACUBEFISH_SLICE.get(), FarmersDelightCompat.COOKED_MAGMACUBEFISH_SLICE.get(), 0.35F, consumer);
        foodSmeltingRecipes("cooked_glowdine_slice", FarmersDelightCompat.GLOWDINE_SLICE.get(), FarmersDelightCompat.COOKED_GLOWDINE_SLICE.get(), 0.35F, consumer);
        foodSmeltingRecipes("cooked_lava_pufferfish_slice", FarmersDelightCompat.LAVA_PUFFERFISH_SLICE.get(), FarmersDelightCompat.COOKED_LAVA_PUFFERFISH_SLICE.get(), 0.35F, consumer);
    }

    private void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        String namePrefix = new ResourceLocation(FarmersDelight.MODID, name).toString();
        wrap(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient),
                        result, experience, 200)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                ,name, consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, ingredient.toString()) , itemExists(NetherDepthsUpgrade.MODID, result.toString()));
        wrap(SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
                        result, experience, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                ,name + "_from_campfire_cooking", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, ingredient.toString()) , itemExists(NetherDepthsUpgrade.MODID, result.toString()));
        wrap(SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
                        result, experience, 100, RecipeSerializer.SMOKING_RECIPE)
                .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                ,name + "_from_smoking", consumer, modLoaded("farmersdelight"), itemExists(NetherDepthsUpgrade.MODID, ingredient.toString()) , itemExists(NetherDepthsUpgrade.MODID, result.toString()));
    }
    private void wrap(CuttingBoardRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        wrap(builder, NetherDepthsUpgrade.MODID, name, consumer, conds);
    }

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

    private void wrap(CookingPotRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        wrap(builder, NetherDepthsUpgrade.MODID, name, consumer, conds);
    }

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

    private void wrap(RecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        wrap(builder, NetherDepthsUpgrade.MODID, name, consumer, conds);
    }

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
