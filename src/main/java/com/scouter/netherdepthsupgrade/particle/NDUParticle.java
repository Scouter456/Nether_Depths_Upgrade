package com.scouter.netherdepthsupgrade.particle;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDUParticle {
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<SimpleParticleType> GLOWDINE_PARTICLE = PARTICLE.register("glowdine_particle" , () -> new SimpleParticleType(true));

}
