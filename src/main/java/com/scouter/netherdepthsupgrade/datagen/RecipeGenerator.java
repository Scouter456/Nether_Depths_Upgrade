package com.scouter.netherdepthsupgrade.datagen;

import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        /*ShapelessRecipeBuilder.shapeless(NDUItems.NIGHTMARE.get())
                .requires(NDUItems.WHITE_SPICE.get(), 2)
                .requires(NDUItems.CUT_WALKING_MUSHROOM_BODY.get())
                .requires(NDUItems.CUT_WALKING_MUSHROOM_FEET.get())
                .unlockedBy("has_mushroom_feet", has(NDUItems.CUT_WALKING_MUSHROOM_FEET.get()))
                .save(consumer);
*/


        nineBlockStorageRecipes(consumer, NDUItems.WARPED_KELP.get(),NDUItems.WARPED_KELP_BLOCK.get());
        //simpleCookingRecipe(consumer, "smelting", RecipeSerializer.SMELTING_RECIPE, 100, NDUItems.CUT_ONION.get(), NDUItems.COOKED_CUT_ONION.get(), 0.50F);
        //simpleCookingRecipe(consumer, "smelting", RecipeSerializer.SMELTING_RECIPE, 100, NDUItems.GARLIC_CLOVES.get(), NDUItems.COOKED_GARLIC_CLOVES.get(), 0.50F);

    }
}
