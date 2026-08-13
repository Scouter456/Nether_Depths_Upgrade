package com.scouter.netherdepthsupgrade.setup;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.client.renderer.RenderLayerRegistration;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.entity.renderer.*;
import com.scouter.netherdepthsupgrade.items.NDUItemProperties;
import com.scouter.netherdepthsupgrade.particle.GlowdineParticle;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, value = Dist.CLIENT,bus = EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(FMLClientSetupEvent event){
        RenderLayerRegistration.init();
        EntityRenderers.register(NDUEntity.LAVA_PUFFERFISH.get(), LavaPufferfishRenderer::new);
        EntityRenderers.register(NDUEntity.OBSIDIAN_FISH.get(), ObsidianfishRenderer::new);
        EntityRenderers.register(NDUEntity.SEARING_COD.get(), SearingCodRenderer::new);
        EntityRenderers.register(NDUEntity.BONEFISH.get(), BonefishRenderer::new);
        EntityRenderers.register(NDUEntity.WITHER_BONEFISH.get(), WitherBonefishRenderer::new);
        EntityRenderers.register(NDUEntity.BLAZEFISH.get(), BlazefishRenderer::new);
        EntityRenderers.register(NDUEntity.MAGMACUBEFISH.get(), MagmaCubefishrenderer::new);
        EntityRenderers.register(NDUEntity.GLOWDINE.get(), GlowdineRenderer::new);
        EntityRenderers.register(NDUEntity.SOULSUCKER.get(), SoulSuckerRenderer::new);
        EntityRenderers.register(NDUEntity.FORTRESS_GROUPER.get(), FortressGrouperRenderer::new);
        EntityRenderers.register(NDUEntity.EYEBALL_FISH.get(), EyeballfishRenderer::new);
        EntityRenderers.register(NDUEntity.LAVA_BOBBER.get(), LavaFishingBobberRenderer::new);
        event.enqueueWork(NDUItemProperties::addItemProperties);
    }

    @SubscribeEvent
    public static void registerParticleTypes(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(NDUParticle.GLOWDINE_PARTICLE.get(), GlowdineParticle.GlowdineProvider::new);
    }
}

