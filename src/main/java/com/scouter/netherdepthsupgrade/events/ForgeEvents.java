package com.scouter.netherdepthsupgrade.events;

import com.google.common.collect.ImmutableList;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.structures.NDUConfiguredStructures;
import com.scouter.netherdepthsupgrade.structures.NDUStructures;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.TickEvent;
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

        if (EnchantmentHelper.getEnchantments(event.player.getItemBySlot(EquipmentSlotType.FEET)).containsKey(NDUEnchantments.HELL_STRIDER.get())) {
            double level = EnchantmentHelper.getEnchantments(event.player.getItemBySlot(EquipmentSlotType.FEET)).get(NDUEnchantments.HELL_STRIDER.get());
            if (event.player.isInLava()) {
                Vector3d vector3d6 = event.player.getDeltaMovement();
                event.player.setDeltaMovement(vector3d6.multiply((double)level * 0.45, (double)0.8F, (double)level * 0.45));
            }
        }
    }
   @SubscribeEvent
   public static void spawnListEvent(StructureSpawnListGatherEvent event) {

       if(event.getStructure() == NDUStructures.NETHER_FORTRESS_PIECE.get()){
           event.setInsideOnly(false);
           event.addEntitySpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(NDUEntity.BLAZEFISH.get(), 5,3,4));
       }
    }

}

