package com.scouter.netherdepthsupgrade.effect;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class MobEffects {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static MobEffect LAVA_VISION;
    //public static final MobEffect LAVA_VISION = registerEffect("lava_vision", new LavaVisionEffect(MobEffectCategory.BENEFICIAL, 0xf4d919));

    public static MobEffect registerEffect(String name, MobEffect effect){
        return Registry.register(BuiltInRegistries.MOB_EFFECT, prefix(name), effect);
    }
    public static void MOBEFFECTS()
    {
        LAVA_VISION = registerEffect("lava_vision", new LavaVisionEffect(MobEffectCategory.BENEFICIAL, 0xf4d919));
        LOGGER.info("Registering Effects for " + NetherDepthsUpgrade.MODID);
    }
}
