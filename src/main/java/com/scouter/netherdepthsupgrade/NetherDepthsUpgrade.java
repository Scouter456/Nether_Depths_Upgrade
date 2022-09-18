package com.scouter.netherdepthsupgrade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scouter.netherdepthsupgrade.events.ClientEvents;
import com.scouter.netherdepthsupgrade.events.ForgeEvents;
import com.scouter.netherdepthsupgrade.setup.ClientSetup;
import com.scouter.netherdepthsupgrade.setup.ModSetup;
import com.scouter.netherdepthsupgrade.setup.Registration;
import com.scouter.netherdepthsupgrade.world.NDUGeneration;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.Locale;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("netherdepthsupgrade")
public class NetherDepthsUpgrade
{
    public static final String MODID = "netherdepthsupgrade";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public NetherDepthsUpgrade()
    {
        Registration.init();
        ModSetup.setup();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            MinecraftForge.EVENT_BUS.register(ClientEvents.class);
            // static method with no client-only classes in method signature
        }
        MinecraftForge.EVENT_BUS.register(ForgeEvents.class);
        forgeBus.addListener(EventPriority.HIGH, NDUGeneration::generateFeatures);
        GeckoLib.initialize();

    }


    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
    }

}
