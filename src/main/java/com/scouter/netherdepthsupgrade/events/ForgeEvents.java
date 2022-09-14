package com.scouter.netherdepthsupgrade.events;

import com.google.common.collect.ImmutableList;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.structures.NDUConfiguredStructures;
import com.scouter.netherdepthsupgrade.structures.NDUStructures;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;
import static com.scouter.netherdepthsupgrade.setup.ModSetup.STRUCTURE_FISH;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    final static Minecraft minecraft = Minecraft.getInstance();
    public static final Logger LOGGER = LogManager.getLogger(MODID);


    @SubscribeEvent
    public static void lavaMovementSpeed(TickEvent.PlayerTickEvent event) {
        if (event.player == null || event.player.isCreative() || event.player.isSpectator()) {
            return;
        }
        double d0 = 0.08D;
        boolean flag = event.player.getDeltaMovement().y <= 0.0D;
        if (EnchantmentHelper.getEnchantments(event.player.getItemBySlot(EquipmentSlotType.FEET)).containsKey(NDUEnchantments.HELL_STRIDER.get())) {
            double level = EnchantmentHelper.getEnchantments(event.player.getItemBySlot(EquipmentSlotType.FEET)).get(NDUEnchantments.HELL_STRIDER.get());
            if (event.player.isInLava()) {
                float speed = 0;
                if(level == 1){
                    speed = 1.25F;
                }else if(level == 2){
                    speed = 1.35F;
                }else if(level == 3){
                    speed = 1.35F;
                }
                event.player.setDeltaMovement(event.player.getDeltaMovement().multiply(speed, 0.8F, speed));
                Vector3d vec33 = event.player.getFluidFallingAdjustedMovement(d0, flag, event.player.getDeltaMovement());
                event.player.setDeltaMovement(vec33);
            }
            //event.player.makeStuckInBlock(Blocks.LAVA.defaultBlockState(), new Vec3(1.5D * level, 2.5D, 1.5D * level));
        }
    }

    @SubscribeEvent
    public static void changeFish(ItemFishedEvent event){
        PlayerEntity fisher = (PlayerEntity) event.getEntity();
        FishingBobberEntity bobber =  event.getHookEntity();
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
                fisher.level.addFreshEntity(new ExperienceOrbEntity(fisher.level, fisher.getX(), fisher.getY() + 0.5D, fisher.getZ() + 0.5D, bobber.level.random.nextInt(6) + 1));
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

   @SubscribeEvent
   public static void spawnListEvent(StructureSpawnListGatherEvent event) {

       if(event.getStructure() == NDUStructures.NETHER_FORTRESS_PIECE.get()){
           event.setInsideOnly(false);
           event.addEntitySpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(NDUEntity.BLAZEFISH.get(), 5,3,4));
       }
    }

}

