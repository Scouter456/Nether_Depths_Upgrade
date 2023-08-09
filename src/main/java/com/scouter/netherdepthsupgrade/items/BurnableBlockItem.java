package com.scouter.netherdepthsupgrade.items;


import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class BurnableBlockItem extends BlockItem {
    private int burntime;
    public BurnableBlockItem(Block pBlock, Properties pProperties, int burntime) {
        super(pBlock, pProperties);
        this.burntime = burntime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType){
        return burntime;
    }
}
