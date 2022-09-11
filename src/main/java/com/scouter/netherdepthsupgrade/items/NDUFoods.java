package com.scouter.netherdepthsupgrade.items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;


public class NDUFoods {
    public static final Food LAVA_PUFFERFISH = new Food.Builder()
            .nutrition(1)
            .saturationMod(1.2F)
            .effect(() -> new EffectInstance(Effects.WITHER, 400, 0), 0.8F)
            //.effect(() -> new EffectInstance(com.scouter.netherdepthsupgrade.effect.MobEffects.LAVA_VISION, 400, 0), 0.8F)
            .build();

    public static final Food OBSIDIANFISH = new Food.Builder()
            .nutrition(2)
            .saturationMod(1.2F)
            .effect( () -> new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 400, 0), 0.8F)
            .effect( () ->new EffectInstance(Effects.DAMAGE_RESISTANCE, 500, 0), 0.5F)
            .build();

    public static final Food SEARING_COD = new Food.Builder()
            .nutrition(3)
            .saturationMod(1.2F)
            .effect( () ->new EffectInstance(Effects.MOVEMENT_SPEED, 400, 0), 0.5F)
            .effect( () ->new EffectInstance(Effects.CONFUSION, 500, 0), 0.8F)
            .build();

    public static final Food BONEFISH = new Food.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .effect( () ->new EffectInstance(Effects.CONFUSION, 400, 0), 0.5F)
            .effect( () ->new EffectInstance(Effects.BLINDNESS, 500, 0), 0.9F)
            .effect( () ->new EffectInstance(Effects.WEAKNESS, 1000, 0), 0.8F)
            .build();

    public static final Food WITHER_BONEFISH = new Food.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .effect( () ->new EffectInstance(Effects.WITHER, 1000, 0), 1.0F)
            .effect( () ->new EffectInstance(Effects.BLINDNESS, 500, 0), 0.6F)
            .effect( () ->new EffectInstance(Effects.WEAKNESS, 5000, 0), 0.5F)
            .build();

    public static final Food BLAZEFISH = new Food.Builder()
            .nutrition(2)
            .saturationMod(0.8F)
            .effect( () ->new EffectInstance(Effects.WEAKNESS, 1000, 0), 1.0F)
            .effect( () ->new EffectInstance(Effects.DAMAGE_BOOST, 800, 0), 0.5F)
            .build();

    public static final Food MAGMACUBEFISH = new Food.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .effect( () ->new EffectInstance(Effects.FIRE_RESISTANCE, 800, 0), 0.5F)
            .build();

    public static final Food GLOWDINE = new Food.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .effect( () ->new EffectInstance(Effects.GLOWING, 1000, 0), 0.9F)
            .effect( () ->new EffectInstance(Effects.WEAKNESS, 1000, 0), 0.7F)
            .effect( () ->new EffectInstance(Effects.MOVEMENT_SPEED, 800, 1), 0.5F)
            .build();




}
