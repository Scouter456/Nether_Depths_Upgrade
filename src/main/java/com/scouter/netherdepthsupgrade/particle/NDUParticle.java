package com.scouter.netherdepthsupgrade.particle;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NDUParticle {
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<BasicParticleType> GLOWDINE_PARTICLE = PARTICLE.register("glowdine_particle" , () -> new BasicParticleType(true));

}
