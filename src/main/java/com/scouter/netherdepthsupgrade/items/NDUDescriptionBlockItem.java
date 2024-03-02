package com.scouter.netherdepthsupgrade.items;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.List;

public class NDUDescriptionBlockItem extends BlockItem {
    private static final Logger LOGGER = LogUtils.getLogger();
    public NDUDescriptionBlockItem(Block pBlock, Properties pProperties, Component component) {
        super(pBlock, pProperties);
        this.descriptionComponent = component;
    }

    Component descriptionComponent;

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pFlag) {
        String string = I18n.get(descriptionComponent.getString());
        String[] test =    string.split("\\s");
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
