package com.scouter.netherdepthsupgrade.events;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    final static Minecraft minecraft = Minecraft.getInstance();
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void lavaMovementSpeed(TickEvent.PlayerTickEvent event){
        if(event.player == null || event.player.isCreative() || event.player.isSpectator()){
            return;
        }
        double d0 = 0.08D;
        boolean flag = event.player.getDeltaMovement().y <= 0.0D;
        AttributeInstance gravity = event.player.getAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
        if(EnchantmentHelper.getEnchantments(event.player.getItemBySlot(EquipmentSlot.FEET)).containsKey(NDUEnchantments.HELL_STRIDER.get())){
            double level = EnchantmentHelper.getEnchantments(event.player.getItemBySlot(EquipmentSlot.FEET)).get(NDUEnchantments.HELL_STRIDER.get());
            if(event.player.isInLava()){
                event.player.setDeltaMovement(event.player.getDeltaMovement().multiply(0.45D * level, (double)0.3F * level, 0.45D * level));
                Vec3 vec33 = event.player.getFluidFallingAdjustedMovement(d0, flag,event.player.getDeltaMovement());
                event.player.setDeltaMovement(vec33);
            }
               //event.player.makeStuckInBlock(Blocks.LAVA.defaultBlockState(), new Vec3(1.5D * level, 2.5D, 1.5D * level));
            }
        }
    }

