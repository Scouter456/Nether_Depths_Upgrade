package com.scouter.netherdepthsupgrade.potion;


import com.scouter.netherdepthsupgrade.effect.MobEffects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class NDUPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, MODID);
    public static final RegistryObject<Potion> LAVA_VISION = POTIONS.register("lava_vision", () -> new Potion(new EffectInstance(MobEffects.LAVA_VISION.get(), 3600)));
    public static final RegistryObject<Potion> LONG_LAVA_VISION = POTIONS.register("long_lava_vision", () -> new Potion("lava_vision", new EffectInstance(MobEffects.LAVA_VISION.get(), 9600)));
}
