package com.scouter.netherdepthsupgrade.potion;


import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class NDUPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);
    public static final RegistryObject<Potion> LAVA_VISION = POTIONS.register("lava_vision", () -> new Potion(new MobEffectInstance(MobEffects.LAVA_VISION.get(), 3600)));
    public static final RegistryObject<Potion> LONG_LAVA_VISION = POTIONS.register("long_lava_vision", () -> new Potion("lava_vision", new MobEffectInstance(MobEffects.LAVA_VISION.get(), 9600)));
    public static final RegistryObject<Potion> WITHER = POTIONS.register("lava_puffer_wither", () -> new Potion(new MobEffectInstance(net.minecraft.world.effect.MobEffects.WITHER, 450)));
    public static final RegistryObject<Potion> LONG_WITHER = POTIONS.register("lava_puffer_long_wither", () -> new Potion("lava_puffer_long_wither", new MobEffectInstance(net.minecraft.world.effect.MobEffects.WITHER, 900)));
    public static final RegistryObject<Potion> RESISTANCE = POTIONS.register("obsidianfish_resistance", () -> new Potion(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE, 900), new MobEffectInstance(net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE, 900)));
    public static final RegistryObject<Potion> LONG_RESISTANCE = POTIONS.register("obsidianfish_long_resistance", () -> new Potion("obsidianfish_long_resistance", new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE, 1800), new MobEffectInstance(net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE, 1800)));
    public static final RegistryObject<Potion> STRONG_RESISTANCE = POTIONS.register("obsidianfish_strong_resistance", () -> new Potion("obsidianfish_strong_resistance", new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_RESISTANCE, 900, 2), new MobEffectInstance(net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE, 900)));
    public static final RegistryObject<Potion> GLOWING = POTIONS.register("glowdine_glowing", () -> new Potion("glowdine_glowing", new MobEffectInstance(net.minecraft.world.effect.MobEffects.GLOWING, 1800)));
    public static final RegistryObject<Potion> LONG_GLOWING = POTIONS.register("glowdine_long_glowing", () -> new Potion("glowdine_long_glowing", new MobEffectInstance(net.minecraft.world.effect.MobEffects.GLOWING, 3600)));

}
