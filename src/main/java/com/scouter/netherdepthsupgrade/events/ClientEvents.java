package com.scouter.netherdepthsupgrade.events;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderBlockScreenEffectEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.FORGE)

public class ClientEvents {
    final static Minecraft minecraft = Minecraft.getInstance();
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ResourceLocation TEXTURE = new ResourceLocation(NetherDepthsUpgrade.MODID, "textures/mob_effect/effect.png");
    @SubscribeEvent
    public static void renderBlockOverlayEvent(RenderBlockScreenEffectEvent event) {
        // Remove fire overlay from players when they have the lava vision enchantment
        if (event.getPlayer().hasEffect(MobEffects.LAVA_VISION.get())) {
            //if (event.getPlayer().isInLava()) {
                if (event.getBlockState() == Blocks.FIRE.defaultBlockState()) {
                    event.setCanceled(true);
           //     }
            }
        }
    }


    @SubscribeEvent
    public static void fogDensityEvent(ViewportEvent.RenderFog event) {
        // Reduce lava fog from players when they have the lava vision enchantment
        Player player = minecraft.player;
        if (player != null && player.hasEffect(MobEffects.LAVA_VISION.get())) {
                if (minecraft.level != null) {
                    BlockState state = minecraft.level.getBlockState(new BlockPos(player.blockPosition().above(1)));
                    if (state.is(Blocks.LAVA) || state.is(NDUBlocks.TALL_WARPED_SEAGRASS.get()) || state.is(NDUBlocks.WARPED_KELP.get()) || state.is(NDUBlocks.WARPED_KELP_PLANT.get())) {
                        event.setNearPlaneDistance(16.0f);
                        event.setFarPlaneDistance(32.0f);
                        event.setCanceled(true);
                    }
                }
        }
    }

}
