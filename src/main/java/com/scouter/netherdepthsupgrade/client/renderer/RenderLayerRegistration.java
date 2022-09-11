package com.scouter.netherdepthsupgrade.client.renderer;


import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class RenderLayerRegistration {
    public static void init() {
        RenderType cutoutMipped = RenderType.cutoutMipped();
        RenderType cutout = RenderType.cutout();
        RenderType translucent = RenderType.translucent();
        RenderType translucentnocrumb = RenderType.translucentNoCrumbling();
        RenderType solid = RenderType.solid();


        RenderTypeLookup.setRenderLayer(NDUBlocks.WARPED_KELP.get(), cutout);
        RenderTypeLookup.setRenderLayer(NDUBlocks.WARPED_KELP_PLANT.get(), cutout);
        RenderTypeLookup.setRenderLayer(NDUBlocks.WARPED_SEAGRASS.get(), cutout);
        RenderTypeLookup.setRenderLayer(NDUBlocks.TALL_WARPED_SEAGRASS.get(), cutout);
    }
}
