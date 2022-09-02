package com.scouter.netherdepthsupgrade.effect;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<MobEffect> LAVA_VISION = MOB_EFFECTS.register("lava_vision", () -> new LavaVisionEffect(MobEffectCategory.BENEFICIAL, 1761122));
}
