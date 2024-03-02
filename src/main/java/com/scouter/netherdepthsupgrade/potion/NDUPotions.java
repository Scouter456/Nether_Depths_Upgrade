package com.scouter.netherdepthsupgrade.potion;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUPotions {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final Potion LAVA_VISION = registerPotion("lava_vision",  new Potion(new MobEffectInstance(MobEffects.LAVA_VISION, 3600)));
    public static final Potion LONG_LAVA_VISION = registerPotion("long_lava_vision", new Potion("lava_vision", new MobEffectInstance(MobEffects.LAVA_VISION, 9600)));
    public static final Potion WITHER = registerPotion("lava_puffer_wither", new Potion(new MobEffectInstance(net.minecraft.world.effect.MobEffects.WITHER, 450)));
    public static final Potion LONG_WITHER = registerPotion("lava_puffer_long_wither", new Potion("lava_puffer_long_wither", new MobEffectInstance(net.minecraft.world.effect.MobEffects.WITHER, 900)));
    public static final Potion RESISTANCE = registerPotion("obsidianfish_resistance", new Potion(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE, 900), new MobEffectInstance(net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE, 900)));
    public static final Potion LONG_RESISTANCE = registerPotion("obsidianfish_long_resistance", new Potion("obsidianfish_long_resistance", new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE, 1800), new MobEffectInstance(net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE, 1800)));
    public static final Potion STRONG_RESISTANCE = registerPotion("obsidianfish_strong_resistance", new Potion("obsidianfish_strong_resistance", new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE, 900, 2), new MobEffectInstance(net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE, 900)));
    public static final Potion GLOWING = registerPotion("glowdine_glowing", new Potion("glowdine_glowing", new MobEffectInstance(net.minecraft.world.effect.MobEffects.GLOWING, 1800)));
    public static final Potion LONG_GLOWING = registerPotion("glowdine_long_glowing", new Potion("glowdine_long_glowing", new MobEffectInstance(net.minecraft.world.effect.MobEffects.GLOWING, 3600)));

    public static Potion registerPotion(String name, Potion effect){
        return Registry.register(BuiltInRegistries.POTION, prefix(name), effect);
    }
    public static void POTIONS()
    {
        LOGGER.info("Registering Potions for " + NetherDepthsUpgrade.MODID);
    }

}
