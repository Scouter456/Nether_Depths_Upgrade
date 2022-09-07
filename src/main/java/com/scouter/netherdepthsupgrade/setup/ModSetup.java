package com.scouter.netherdepthsupgrade.setup;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.entity.NDUEntityPlacement;
import com.scouter.netherdepthsupgrade.entity.entities.*;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.particle.GlowdineParticle;
import com.scouter.netherdepthsupgrade.particle.NDUParticle;
import com.scouter.netherdepthsupgrade.potion.NDUPotions;
import com.scouter.netherdepthsupgrade.utils.BetterBrewingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static void init(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            NDUEntityPlacement.entityPlacement();
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                    Potions.AWKWARD, NDUItems.LAVA_PUFFERFISH.get(), NDUPotions.LAVA_VISION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                    NDUPotions.LAVA_VISION.get(), Items.REDSTONE, NDUPotions.LONG_LAVA_VISION.get()));
        });
    }

    public static void setup(){
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }



    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event){
        event.put(NDUEntity.LAVA_PUFFERFISH.get(), LavaPufferfishEntity.setAttributes());
        event.put(NDUEntity.OBSIDIAN_FISH.get(), ObsidianfishEntity.setAttributes());
        event.put(NDUEntity.SEARING_COD.get(), SearingCodEntity.setAttributes());
        event.put(NDUEntity.BONEFISH.get(), BonefishEntity.setAttributes());
        event.put(NDUEntity.WITHER_BONEFISH.get(), WitherBonefishEntity.setAttributes());
        event.put(NDUEntity.BLAZEFISH.get(), BlazefishEntity.setAttributes());
        event.put(NDUEntity.MAGMACUBEFISH.get(), MagmaCubefishEntity.setAttributes());
        event.put(NDUEntity.GLOWDINE.get(), GlowdineEntity.setAttributes());
        event.put(NDUEntity.SOULSUCKER.get(), SoulSuckerEntity.setAttributes());
    }

    @SubscribeEvent
    public static void registerParticleTypes(RegisterParticleProvidersEvent event){
        Minecraft.getInstance().particleEngine.register(NDUParticle.GLOWDINE_PARTICLE.get(), GlowdineParticle.GlowdineProvider::new);
    }


}
