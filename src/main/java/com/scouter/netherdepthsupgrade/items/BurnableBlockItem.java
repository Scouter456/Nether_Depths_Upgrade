package com.scouter.netherdepthsupgrade.items;


import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class BurnableBlockItem extends BlockItem {
    private final int burntime;
    public BurnableBlockItem(Block pBlock, Properties pProperties,int burn) {
        super(pBlock, pProperties);
        this.burntime = burn;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType)
    {
        return burntime;
    }
}
