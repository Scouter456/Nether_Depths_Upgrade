package com.scouter.netherdepthsupgrade.particle;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUParticle {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final SimpleParticleType GLOWDINE_PARTICLE = FabricParticleTypes.simple();


    public static void PARTICLE(){
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, prefix("glowdine_particle"), GLOWDINE_PARTICLE);

        LOGGER.info("Registering Particles for " + NetherDepthsUpgrade.MODID);
    }

}
