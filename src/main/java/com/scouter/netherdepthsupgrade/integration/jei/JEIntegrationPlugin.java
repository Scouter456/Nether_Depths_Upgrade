package com.scouter.netherdepthsupgrade.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

@JeiPlugin
public class JEIntegrationPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return prefix("jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration){
        IModPlugin.super.registerRecipes(registration);
    }
}
