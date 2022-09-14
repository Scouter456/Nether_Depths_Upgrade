package com.scouter.netherdepthsupgrade.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;

public class NDUItemProperties {
    public static void addItemProperties(){
        makeFishingRod(NDUItems.LAVA_FISHING_ROD.get());
    }

    private static void makeFishingRod(Item item){
        ItemModelsProperties.register(item, new ResourceLocation("cast"), (p_239422_0_, p_239422_1_, p_239422_2_) -> {
            if (p_239422_2_ == null) {
                return 0.0F;
            } else {
                boolean flag = p_239422_2_.getMainHandItem() == p_239422_0_;
                boolean flag1 = p_239422_2_.getOffhandItem() == p_239422_0_;
                if (p_239422_2_.getMainHandItem().getItem() instanceof FishingRodItem) {
                    flag1 = false;
                }

                return (flag || flag1) && p_239422_2_ instanceof PlayerEntity && ((PlayerEntity)p_239422_2_).fishing != null ? 1.0F : 0.0F;
            }
        });}

}
