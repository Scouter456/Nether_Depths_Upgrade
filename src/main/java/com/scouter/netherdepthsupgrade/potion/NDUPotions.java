package com.scouter.netherdepthsupgrade.potion;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUPotions {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final Potion LAVA_VISION = registerPotion("lava_vision",  new Potion(new MobEffectInstance(MobEffects.LAVA_VISION, 3600)));
    public static final Potion LONG_LAVA_VISION = registerPotion("long_lava_vision", new Potion("lava_vision", new MobEffectInstance(MobEffects.LAVA_VISION, 9600)));

    public static Potion registerPotion(String name, Potion effect){
        return Registry.register(Registry.POTION, prefix(name), effect);
    }
    public static void POTIONS()
    {
        LOGGER.info("Registering Potions for " + NetherDepthsUpgrade.MODID);
    }

}
