package com.scouter.netherdepthsupgrade.items;

import net.minecraft.world.food.FoodProperties;

public class NDUFoods {
    public static final FoodProperties LAVA_PUFFERFISH = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(1.2F)
            //.effect( new MobEffectInstance(com.scouter.netherdepthsupgrade.effect.MobEffects.LAVA_VISION.get(), 400, 0), 0.8F)
            .build();

    public static final FoodProperties OBSIDIANFISH = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(1.2F)
            .build();

    public static final FoodProperties SEARING_COD = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(1.2F)
            .build();

    public static final FoodProperties BONEFISH = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.5F)
            .build();

    public static final FoodProperties WITHER_BONEFISH = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.5F)
            .build();

    public static final FoodProperties BLAZEFISH = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.8F)
            .build();

    public static final FoodProperties MAGMACUBEFISH = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.5F)
            .build();

    public static final FoodProperties GLOWDINE = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.5F)
            .build();

    public static final FoodProperties FORTRESS_GROUPER = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(1.2F)
            .build();

    public static final FoodProperties EYEBALL_FISH = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(1.2F)
            //.effect(new MobEffectInstance(Optional.of(com.scouter.netherdepthsupgrade.effect.MobEffects.LAVA_VISION).get(), 400, 0), 0.8F)

            .build();
}
