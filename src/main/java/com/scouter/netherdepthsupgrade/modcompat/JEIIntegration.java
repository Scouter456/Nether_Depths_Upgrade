package com.scouter.netherdepthsupgrade.modcompat;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIIntegration implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(NetherDepthsUpgrade.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration){
        IModPlugin.super.registerRecipes(registration);
    }
}
