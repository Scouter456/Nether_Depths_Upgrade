package com.scouter.netherdepthsupgrade.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class NDUFoods {
    public static final FoodProperties LAVA_PUFFERFISH = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(1.2F)
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 400, 0), 0.8F)
            .effect(() -> new MobEffectInstance(com.scouter.netherdepthsupgrade.effect.MobEffects.LAVA_VISION.get(), 400, 0), 0.8F)
            .build();

    public static final FoodProperties OBSIDIANFISH = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(1.2F)
            .effect( () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 0), 0.8F)
            .effect( () ->new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 500, 0), 0.5F)
            .build();

    public static final FoodProperties SEARING_COD = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(1.2F)
            .effect( () ->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0), 0.5F)
            .effect( () ->new MobEffectInstance(MobEffects.CONFUSION, 500, 0), 0.8F)
            .build();

    public static final FoodProperties BONEFISH = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .effect( () ->new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 0.5F)
            .effect( () ->new MobEffectInstance(MobEffects.BLINDNESS, 500, 0), 0.9F)
            .effect( () ->new MobEffectInstance(MobEffects.WEAKNESS, 1000, 0), 0.8F)
            .build();

    public static final FoodProperties WITHER_BONEFISH = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .effect( () ->new MobEffectInstance(MobEffects.WITHER, 1000, 0), 1.0F)
            .effect( () ->new MobEffectInstance(MobEffects.BLINDNESS, 500, 0), 0.6F)
            .effect( () ->new MobEffectInstance(MobEffects.WEAKNESS, 5000, 0), 0.5F)
            .build();

    public static final FoodProperties BLAZEFISH = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.8F)
            .effect( () ->new MobEffectInstance(MobEffects.WEAKNESS, 1000, 0), 1.0F)
            .effect( () ->new MobEffectInstance(MobEffects.DAMAGE_BOOST, 800, 0), 0.5F)
            .build();

    public static final FoodProperties MAGMACUBEFISH = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .effect( () ->new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0), 0.5F)
            .build();

    public static final FoodProperties GLOWDINE = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .effect( () ->new MobEffectInstance(MobEffects.GLOWING, 1000, 0), 0.9F)
            .effect( () ->new MobEffectInstance(MobEffects.WEAKNESS, 1000, 0), 0.7F)
            .effect( () ->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 800, 1), 0.5F)
            .build();

    public static final FoodProperties FORTRESS_GROUPER = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(1.2F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 400, 0), 0.8F)
            .build();

    public static final FoodProperties NETHER_URCHIN = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(1.2F)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 400, 0), 0.8F)
            .build();
}
