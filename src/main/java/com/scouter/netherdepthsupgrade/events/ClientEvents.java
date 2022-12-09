package com.scouter.netherdepthsupgrade.events;

import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.FORGE)

public class ClientEvents {
    final static Minecraft minecraft = Minecraft.getInstance();
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    private static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/mob_effect/effect.png");
    @SubscribeEvent
    public static void renderBlockOverlayEvent(RenderBlockOverlayEvent event) {
        // Remove fire overlay from players when they have the lava vision enchantment
        if (event.getPlayer().hasEffect(MobEffects.LAVA_VISION.get())) {
            //if (event.getPlayer().isInLava()) {
                if (event.getBlockForOverlay() == Blocks.FIRE.defaultBlockState()) {
                    event.setCanceled(true);
                }
            //}
        }
    }


    @SubscribeEvent
    public static void fogDensityEvent(EntityViewRenderEvent.FogDensity event) {
        // Reduce lava fog from players when they have the lava vision enchantment
        PlayerEntity player = minecraft.player;
        if (player != null && player.hasEffect(MobEffects.LAVA_VISION.get())) {
            if (player.isInLava()) {
                if (minecraft.level != null) {
                    BlockState state = minecraft.level.getBlockState(new BlockPos(player.blockPosition().above(1)));
                    if (state.is(Blocks.LAVA) || state.is(NDUBlocks.TALL_WARPED_SEAGRASS.get()) || state.is(NDUBlocks.WARPED_KELP.get()) || state.is(NDUBlocks.WARPED_KELP_PLANT.get())) {
                        event.setDensity(0.04125f);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

}
