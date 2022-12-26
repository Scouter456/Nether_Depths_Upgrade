package com.scouter.netherdepthsupgrade.modcompat;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.items.NDUFoods;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Supplier;

public class InfernalExpansionCompat {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static @NotNull Optional<Holder<MobEffect>> LUMINOUS;

    public static void setupCompatPreInit(){
        LOGGER.info("Setting up compat for Infernal Expansion");
        ModChecker.infernalExpansionPresent = true;
    }

    public static void setupCompatCommonSetup(){
        LOGGER.info("Setting up common setup compat for Infernal Expansion");
        LUMINOUS =  ForgeRegistries.MOB_EFFECTS.getHolder(LUMINOUS_RL);
    }
    private static ResourceLocation LUMINOUS_RL = new ResourceLocation("infernalexp","luminous");

    public static final Supplier<FoodProperties> getGlowdineFood(){
        if(ModChecker.infernalExpansionPresent){
            return () -> GLOWDINE_IE;
        } else {
            return () -> NDUFoods.GLOWDINE;
        }
    }

    public static final FoodProperties GLOWDINE_IE = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.5F)
            .effect( () ->new MobEffectInstance(MobEffects.GLOWING, 1000, 0), 0.9F)
            .effect( () ->new MobEffectInstance(LUMINOUS.get().value(), 1000, 0), 0.9F)
            .effect( () ->new MobEffectInstance(MobEffects.WEAKNESS, 1000, 0), 0.7F)
            .effect( () ->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 800, 1), 0.5F)
            .build();
}
