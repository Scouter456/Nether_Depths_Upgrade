package com.scouter.netherdepthsupgrade.modcompat;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FarmersDelightCompatFoods {
    public static final int BRIEF_DURATION = 600;    // 30 seconds
    public static final int SHORT_DURATION = 1200;    // 1 minute
    public static final int MEDIUM_DURATION = 3600;    // 3 minutes
    public static final int LONG_DURATION = 6000;    // 5 minutes
    public static final FoodProperties BLAZEFISH_SLICE = (new FoodProperties.Builder()) //ALREADY COOKED
            .nutrition(3).saturationMod(0.5f).fast().build();

    public static final FoodProperties SEARING_COD_SLICE = (new FoodProperties.Builder()) //ALREADY COOKED
            .nutrition(3).saturationMod(0.5f).fast().build();

    public static final FoodProperties LAVA_PUFFERFISH_SLICE = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.1f)
            .effect(()-> new MobEffectInstance(MobEffects.POISON, BRIEF_DURATION,1), 0.1F)
            .fast().build();

    public static final FoodProperties OBSIDIANFISH_SLICE = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.1f).fast().build();

    public static final FoodProperties MAGMA_CUBE_FISH_SLICE = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.1f).fast().build();

    public static final FoodProperties GLOWDINE_SLICE = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.1f).fast().build();

    public static final FoodProperties SOULSUCKER_SLICE = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.1f).fast().build();

    public static final FoodProperties COOKED_LAVA_PUFFERFISH_SLICE = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.5f).fast().build();

    public static final FoodProperties COOKED_OBSIDIANFISH_SLICE = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.5f).fast().build();

    public static final FoodProperties COOKED_MAGMA_CUBE_FISH_SLICE = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.5f).fast().build();

    public static final FoodProperties COOKED_GLOWDINE_SLICE = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.5f).fast().build();

    public static final FoodProperties COOKED_SOULSUCKER_SLICE = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.5f).fast().build();


    public static final FoodProperties BAKED_SOULSUCKER_STEW = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.9f)
            .effect( () ->new MobEffectInstance(MobEffects.ABSORPTION, LONG_DURATION, 2), 1.0F)
            .effect( () ->new MobEffectInstance(MobEffects.DIG_SPEED, LONG_DURATION, 2), 1.0F)
            .build();

    public static final FoodProperties BAKED_LAVA_PUFFERFISH_STEW = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(com.scouter.netherdepthsupgrade.effect.MobEffects.LAVA_VISION.get(), LONG_DURATION, 1), 1.0F)
            .build();

    public static final FoodProperties BAKED_BLAZEFISH_STEW = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, LONG_DURATION, 2), 1.0F).build();

    public static final FoodProperties BAKED_SEARING_COD_STEW = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, LONG_DURATION, 2), 1.0F).build();

    public static final FoodProperties BAKED_MAGMA_CUBE_FISH_STEW = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, LONG_DURATION, 2), 1.0F).build();

    public static final FoodProperties BAKED_OBSIDIANFISH_STEW = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.9f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, LONG_DURATION, 2), 1.0F).build();

    public static final FoodProperties BAKED_GLOWDINE_STEW = (new FoodProperties.Builder())
            .nutrition(12).saturationMod(0.9f)
            .effect( () ->new MobEffectInstance(MobEffects.GLOWING, LONG_DURATION, 2), 1.0F)
            .effect( () ->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, LONG_DURATION, 2), 1.0F)
            .build();
}
