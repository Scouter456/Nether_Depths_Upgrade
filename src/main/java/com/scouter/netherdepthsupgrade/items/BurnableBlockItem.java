package com.scouter.netherdepthsupgrade.items;


import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class BurnableBlockItem extends BlockItem {
    public BurnableBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }
/*
    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType){
        if(itemStack.is(NDUBlocks.WARPED_KELP_BLOCK.asItem())) {
            return 6000;
        }
        return 0;
    }*/
}
