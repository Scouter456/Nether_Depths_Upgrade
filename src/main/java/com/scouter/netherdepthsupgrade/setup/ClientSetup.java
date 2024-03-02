package com.scouter.netherdepthsupgrade.setup;

import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.entity.renderer.*;
import com.scouter.netherdepthsupgrade.items.NDUItemProperties;
import com.scouter.netherdepthsupgrade.particle.GlowdineParticle;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;

public class ClientSetup implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(NDUEntity.LAVA_PUFFERFISH, LavaPufferfishRenderer::new);
        EntityRendererRegistry.register(NDUEntity.OBSIDIAN_FISH, ObsidianfishRenderer::new);
        EntityRendererRegistry.register(NDUEntity.BONEFISH, BonefishRenderer::new);
        EntityRendererRegistry.register(NDUEntity.WITHER_BONEFISH, WitherBonefishRenderer::new);
        EntityRendererRegistry.register(NDUEntity.MAGMACUBEFISH, MagmaCubefishrenderer::new);
        EntityRendererRegistry.register(NDUEntity.GLOWDINE, GlowdineRenderer::new);
        EntityRendererRegistry.register(NDUEntity.SEARING_COD, SearingCodRenderer::new);
        EntityRendererRegistry.register(NDUEntity.SOULSUCKER, SoulSuckerRenderer::new);
        EntityRendererRegistry.register(NDUEntity.BLAZEFISH, BlazefishRenderer::new);
        EntityRendererRegistry.register(NDUEntity.FORTRESS_GROUPER, FortressGrouperRenderer::new);
        EntityRendererRegistry.register(NDUEntity.EYEBALL_FISH, EyeballfishRenderer::new);
        EntityRendererRegistry.register(NDUEntity.LAVA_BOBBER, LavaFishingBobberRenderer::new);
        RenderLayerRegistration();
        NDUItemProperties.addItemProperties();
    }


    public static void RenderLayerRegistration(){
        RenderType cutoutMipped = RenderType.cutoutMipped();
        RenderType cutout = RenderType.cutout();
        RenderType translucent = RenderType.translucent();
        RenderType translucentnocrumb = RenderType.translucentNoCrumbling();
        RenderType solid = RenderType.solid();

        BlockRenderLayerMap.INSTANCE.putBlock(NDUBlocks.WARPED_KELP, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(NDUBlocks.WARPED_KELP_PLANT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(NDUBlocks.WARPED_SEAGRASS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(NDUBlocks.TALL_WARPED_SEAGRASS, cutout);

        BlockRenderLayerMap.INSTANCE.putBlock(NDUBlocks.CRIMSON_KELP, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(NDUBlocks.CRIMSON_KELP_PLANT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(NDUBlocks.CRIMSON_SEAGRASS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(NDUBlocks.TALL_CRIMSON_SEAGRASS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(NDUBlocks.LAVA_GLASS, translucent);
        particleRegistration();
    }

    public static void particleRegistration(){
        ParticleFactoryRegistry.getInstance().register(NDUParticle.GLOWDINE_PARTICLE, GlowdineParticle.GlowdineProvider::new);

    }

    public static void init(){

    }
}
