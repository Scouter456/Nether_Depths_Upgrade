package com.scouter.netherdepthsupgrade.setup;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.client.renderer.RenderLayerRegistration;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.entity.renderer.*;
import com.scouter.netherdepthsupgrade.items.NDUItemProperties;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    private static final ResourceLocation TEXTURE = new ResourceLocation(NetherDepthsUpgrade.MODID, "textures/mob_effect/effect.png");

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
        EntityRenderers.register(NDUEntity.LAVA_BOBBER.get(), LavaFishingBobberRenderer::new);
        NDUItemProperties.addItemProperties();
    }

}

