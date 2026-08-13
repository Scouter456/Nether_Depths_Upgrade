package com.scouter.netherdepthsupgrade.events;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FogType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import org.slf4j.Logger;

@EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, value = Dist.CLIENT,bus = EventBusSubscriber.Bus.GAME)

public class ClientEvents {
    final static Minecraft minecraft = Minecraft.getInstance();
    private static final Logger LOGGER = LogUtils.getLogger();
    @SubscribeEvent
    public static void renderBlockOverlayEvent(RenderBlockScreenEffectEvent event) {
        // Remove fire overlay from players when they have the lava vision enchantment
        if (event.getPlayer().hasEffect(MobEffects.LAVA_VISION.getDelegate())) {
                if (event.getOverlayType() == RenderBlockScreenEffectEvent.OverlayType.FIRE) {
                    event.setCanceled(true);
           //     }
            }
        }
    }


    @SubscribeEvent
    public static void fogDensityEvent(ViewportEvent.RenderFog event) {
        // Reduce lava fog from players when they have the lava vision enchantment
        Player player = minecraft.player;
        if (player != null && player.hasEffect(MobEffects.LAVA_VISION.getDelegate())) {
                if (minecraft.level != null) {
                    if(event.getCamera().getFluidInCamera() == FogType.LAVA) {
                        event.setNearPlaneDistance(16.0f);
                        event.setFarPlaneDistance(32.0f);
                        event.setCanceled(true);
                    }
                }
        }
    }
}
