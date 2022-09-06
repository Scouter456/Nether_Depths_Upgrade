package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(NDUItems.SOUL_SUCKER_LEATHER.get(), 3)
                .requires(Items.SHEARS)
                .requires(NDUItems.SOULSUCKER.get())
                .unlockedBy("has_soul_sucker", has(NDUItems.SOULSUCKER.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(NDUItems.SOUL_SUCKER_BOOTS.get())
                .define('s', Items.STRING)
                .define('L', NDUItems.SOUL_SUCKER_LEATHER.get())
                .pattern("sLs")
                        .pattern("LLL")
                .unlockedBy("has_soul_sucker_leather", has(NDUItems.SOUL_SUCKER_LEATHER.get()))
                .save(consumer);

        nineBlockStorageRecipes(consumer, NDUItems.WARPED_KELP.get(),NDUItems.WARPED_KELP_BLOCK.get());
        //simpleCookingRecipe(consumer, "smelting", RecipeSerializer.SMELTING_RECIPE, 100, NDUItems.CUT_ONION.get(), NDUItems.COOKED_CUT_ONION.get(), 0.50F);
        //simpleCookingRecipe(consumer, "smelting", RecipeSerializer.SMELTING_RECIPE, 100, NDUItems.GARLIC_CLOVES.get(), NDUItems.COOKED_GARLIC_CLOVES.get(), 0.50F);

    }
}
