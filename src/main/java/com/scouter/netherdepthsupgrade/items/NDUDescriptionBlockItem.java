package com.scouter.netherdepthsupgrade.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NDUDescriptionBlockItem extends BlockItem {
    public NDUDescriptionBlockItem(Block pBlock, Properties pProperties, Component component) {
        super(pBlock, pProperties);
        this.descriptionComponent = component;
    }

    Component descriptionComponent;

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(descriptionComponent);
        } else {
            pTooltipComponents.add(new TranslatableComponent("item.netherdepthsupgrade.descriptionitem.hover_info").withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
