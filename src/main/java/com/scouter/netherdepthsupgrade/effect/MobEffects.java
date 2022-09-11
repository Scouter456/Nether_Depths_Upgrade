package com.scouter.netherdepthsupgrade.effect;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MobEffects {
    public static final DeferredRegister<Effect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<Effect> LAVA_VISION = MOB_EFFECTS.register("lava_vision", () -> new LavaVisionEffect(EffectType.BENEFICIAL, 0xf4d919));
}
