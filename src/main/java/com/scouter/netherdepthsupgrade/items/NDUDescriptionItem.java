package com.scouter.netherdepthsupgrade.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class NDUDescriptionItem extends Item {
    public NDUDescriptionItem(Properties pProperties, Component component) {
        super(pProperties);
        this.descriptionComponent = component;
    }

    Component descriptionComponent;

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pFlag) {
        String string = I18n.get(descriptionComponent.getString());
        List<String> partsLang = List.of(string.split("\\s"));

        String segments = "";

        if (Screen.hasShiftDown()) {
            for(int i = 0; i < partsLang.size(); i++){
                if(!partsLang.get(i).isEmpty()) {
                    segments = segments.toString() + " " + partsLang.get(i).strip().trim();
                }
                if(i % 7 == 6){
                    pTooltipComponents.add(Component.literal(segments.trim()).withStyle(descriptionComponent.getStyle()));
                    segments = "";
                }
            }
            pTooltipComponents.add(Component.literal(segments.trim()).withStyle(descriptionComponent.getStyle()));

        } else {
            pTooltipComponents.add(Component.translatable("item.netherdepthsupgrade.descriptionitem.hover_info").withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
