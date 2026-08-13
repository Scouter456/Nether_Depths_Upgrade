package com.scouter.netherdepthsupgrade.particle;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NDUParticle {
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, NetherDepthsUpgrade.MODID);
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GLOWDINE_PARTICLE = PARTICLE.register("glowdine_particle" , () -> new SimpleParticleType(false));

}
