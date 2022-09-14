package com.scouter.netherdepthsupgrade.setup;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.client.renderer.RenderLayerRegistration;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.entity.renderer.*;
import com.scouter.netherdepthsupgrade.items.NDUItemProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    private static final ResourceLocation TEXTURE = new ResourceLocation(NetherDepthsUpgrade.MODID, "textures/mob_effect/effect.png");

    public static void init(FMLClientSetupEvent event){
        RenderLayerRegistration.init();
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.LAVA_PUFFERFISH.get(), LavaPufferfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.OBSIDIAN_FISH.get(), ObsidianfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.SEARING_COD.get(), SearingCodRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.BONEFISH.get(), BonefishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.WITHER_BONEFISH.get(), WitherBonefishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.BLAZEFISH.get(), BlazefishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.MAGMACUBEFISH.get(), MagmaCubefishrenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.GLOWDINE.get(), GlowdineRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.SOULSUCKER.get(), SoulSuckerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NDUEntity.LAVA_BOBBER.get(), LavaFishingBobberRenderer::new);
        NDUItemProperties.addItemProperties();
    }

}

