package com.scouter.netherdepthsupgrade.client.renderer;


import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class RenderLayerRegistration {
    public static void init() {
        RenderType cutoutMipped = RenderType.cutoutMipped();
        RenderType cutout = RenderType.cutout();
        RenderType translucent = RenderType.translucent();
        RenderType translucentnocrumb = RenderType.translucentNoCrumbling();
        RenderType solid = RenderType.solid();


        ItemBlockRenderTypes.setRenderLayer(NDUBlocks.WARPED_KELP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(NDUBlocks.WARPED_KELP_PLANT.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(NDUBlocks.WARPED_SEAGRASS.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(NDUBlocks.TALL_WARPED_SEAGRASS.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(NDUBlocks.LAVA_GLASS.get(), translucent);
    }
}
