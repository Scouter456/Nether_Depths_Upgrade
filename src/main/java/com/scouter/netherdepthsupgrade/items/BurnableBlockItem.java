package com.scouter.netherdepthsupgrade.items;


import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

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
