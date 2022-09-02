package com.scouter.netherdepthsupgrade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.events.ClientEvents;
import com.scouter.netherdepthsupgrade.events.ForgeEvents;
import com.scouter.netherdepthsupgrade.setup.ClientSetup;
import com.scouter.netherdepthsupgrade.setup.ModSetup;
import com.scouter.netherdepthsupgrade.setup.Registration;
import com.scouter.netherdepthsupgrade.world.NDUGeneration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.Locale;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("netherdepthsupgrade")
public class NetherDepthsUpgrade
{
    public static final String MODID = "netherdepthsupgrade";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public NetherDepthsUpgrade()
    {
        Registration.init();
        ModSetup.setup();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        //forgeBus.addListener(ForgeEvents::OnEntityJoinWorld);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        MinecraftForge.EVENT_BUS.register(ClientEvents.class);
        MinecraftForge.EVENT_BUS.register(ForgeEvents.class);
        forgeBus.addListener(EventPriority.HIGH, NDUGeneration::generateFeatures);
        //forgeBus.addListener(EventPriority.HIGH, MFGeneration::spawnCreatures);
        GeckoLib.initialize();

    }


    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
    }

}
