package com.scouter.netherdepthsupgrade.events;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void lavaMovementSpeed(TickEvent.PlayerTickEvent event) {
        if (event.player == null || event.player.isCreative() || event.player.isSpectator()) {
            return;
        }
        double d0 = 0.08D;
        boolean flag = event.player.getDeltaMovement().y <= 0.0D;
        if (EnchantmentHelper.getEnchantments(event.player.getItemBySlot(EquipmentSlot.FEET)).containsKey(NDUEnchantments.HELL_STRIDER.get())) {
            double level = EnchantmentHelper.getEnchantments(event.player.getItemBySlot(EquipmentSlot.FEET)).get(NDUEnchantments.HELL_STRIDER.get());
            if (event.player.isInLava()) {
                float speed = (float) (1.15 + (0.1 * level));
                //LOGGER.info("player " + event.player.getScoreboardName());
                event.player.setDeltaMovement(event.player.getDeltaMovement().multiply(speed, 0.8F, speed));
                //LOGGER.info("vec " + event.player.getDeltaMovement());
                Vec3 vec33 = event.player.getFluidFallingAdjustedMovement(d0, flag, event.player.getDeltaMovement());
                //LOGGER.info("vec3 " + vec33);
                event.player.setDeltaMovement(vec33);
            }
            //event.player.makeStuckInBlock(Blocks.LAVA.defaultBlockState(), new Vec3(1.5D * level, 2.5D, 1.5D * level));
        }
    }

    @SubscribeEvent
    public static void changeFish(ItemFishedEvent event){
        Player fisher = (Player) event.getEntity();
        FishingHook bobber =  event.getHookEntity();
        List<ItemStack> drops = event.getDrops();

        for (ItemStack stack : drops) {
            Entity entity = null;
            if(stack.getItem() == NDUItems.SEARING_COD.get()){
                entity = NDUEntity.SEARING_COD.get().create(event.getEntity().level);
            }
            if(stack.getItem() == NDUItems.SOULSUCKER.get()){
                entity = NDUEntity.SOULSUCKER.get().create(event.getEntity().level);
            }
            if(stack.getItem() == NDUItems.LAVA_PUFFERFISH.get()){
                entity = NDUEntity.LAVA_PUFFERFISH.get().create(event.getEntity().level);
            }
            if(stack.getItem() == NDUItems.BONEFISH.get()){
                entity = NDUEntity.BONEFISH.get().create(event.getEntity().level);
            }
            if(stack.getItem() == NDUItems.WITHER_BONEFISH.get()){
                entity = NDUEntity.WITHER_BONEFISH.get().create(event.getEntity().level);
            }
            if(stack.getItem() == NDUItems.GLOWDINE.get()){
                entity = NDUEntity.GLOWDINE.get().create(event.getEntity().level);
            }
            if(stack.getItem() == NDUItems.MAGMACUBEFISH.get()){
                entity = NDUEntity.MAGMACUBEFISH.get().create(event.getEntity().level);
            }
            if(stack.getItem() == NDUItems.OBSIDIANFISH.get()){
                entity = NDUEntity.OBSIDIAN_FISH.get().create(event.getEntity().level);
            }

            if(entity == null){
                ItemEntity itementity = new ItemEntity(event.getEntity().level, bobber.getX(), bobber.getY() + 1, bobber.getZ(), stack);
                double d0 = fisher.position().x() - bobber.position().x();
                double d1 = fisher.position().y() - (bobber.position().y() + 1);
                double d2 = fisher.position().z() - bobber.position().z();
                double d3 = 0.1D;
                itementity.setDeltaMovement(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
                event.getEntity().level.addFreshEntity(itementity);
                fisher.level.addFreshEntity(new ExperienceOrb(fisher.level, fisher.getX(), fisher.getY() + 0.5D, fisher.getZ() + 0.5D, bobber.level.random.nextInt(6) + 1));
                event.setCanceled(true);
                event.damageRodBy(event.getRodDamage());
                return;
            }
            entity.moveTo(bobber.position().x(), bobber.position().y(), bobber.position().z(), bobber.xRotO, bobber.yRotO);
            double dX = fisher.position().x() - bobber.position().x();
            double dY = fisher.position().y() - bobber.position().y();
            double dZ = fisher.position().z() - bobber.position().z();
            double mult = 0.12;
            entity.setDeltaMovement(dX * mult, dY * mult + Math.sqrt(Math.sqrt(dX * dX + dY * dY + dZ * dZ)) * 0.14D, dZ * mult);
            event.getEntity().level.addFreshEntity(entity);
        }
        event.setCanceled(true);
        event.damageRodBy(event.getRodDamage());
    }
    
}

