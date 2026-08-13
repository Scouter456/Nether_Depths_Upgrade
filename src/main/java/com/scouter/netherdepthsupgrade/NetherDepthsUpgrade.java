package com.scouter.netherdepthsupgrade;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.events.ClientEvents;
import com.scouter.netherdepthsupgrade.events.ForgeEvents;
import com.scouter.netherdepthsupgrade.setup.ClientSetup;
import com.scouter.netherdepthsupgrade.setup.ModSetup;
import com.scouter.netherdepthsupgrade.setup.Registration;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import java.util.Locale;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(NetherDepthsUpgrade.MODID)
public class NetherDepthsUpgrade
{
    public static final String MODID = "netherdepthsupgrade";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public NetherDepthsUpgrade()
    {
        //ModChecker.setupModCompatPreInit();
        Registration.init();
        ModSetup.setup();
        IEventBus forgeBus = NeoForge.EVENT_BUS;

        IEventBus modbus = ModLoadingContext.get().getActiveContainer().getEventBus();
        modbus.addListener(ModSetup::init);

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            //NeoForge.EVENT_BUS.register(ClientEvents.class);
            modbus.addListener(ClientSetup::init);
            // static method with no client-only classes in method signature
        }
        //NeoForge.EVENT_BUS.register(ForgeEvents.class);


        //GeckoLib.initialize();

    }


    public static ResourceLocation prefix(String name) {
        return  ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
    }

    public static MutableComponent getTranslation(String key, Object... args) {
        return Component.translatable("netherdepthsupgrade." + key, args);
    }
}
