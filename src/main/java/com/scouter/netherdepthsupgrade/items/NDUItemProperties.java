package com.scouter.netherdepthsupgrade.items;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;

public class NDUItemProperties {
    public static void addItemProperties(){
        makeFishingRod(NDUItems.LAVA_FISHING_ROD.get());
    }

    private static void makeFishingRod(Item item){ItemProperties.register(item, new ResourceLocation("cast"), (p_174595_, p_174596_, p_174597_, p_174598_) -> {
        if (p_174597_ == null) {
            return 0.0F;
        } else {
            boolean flag = p_174597_.getMainHandItem() == p_174595_;
            boolean flag1 = p_174597_.getOffhandItem() == p_174595_;
            if (p_174597_.getMainHandItem().getItem() instanceof FishingRodItem) {
                flag1 = false;
            }

            return (flag || flag1) && p_174597_ instanceof Player && ((Player)p_174597_).fishing != null ? 1.0F : 0.0F;
        }
    });}
}
