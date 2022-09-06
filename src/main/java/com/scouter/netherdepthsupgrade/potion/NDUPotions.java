package com.scouter.netherdepthsupgrade.potion;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class NDUPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);
    public static final RegistryObject<Potion> LAVA_VISION = POTIONS.register("lava_vision", () -> new Potion(new MobEffectInstance(MobEffects.LAVA_VISION.get(), 3600)));
    public static final RegistryObject<Potion> LONG_LAVA_VISION = POTIONS.register("long_lava_vision", () -> new Potion("lava_vision", new MobEffectInstance(MobEffects.LAVA_VISION.get(), 9600)));
}
