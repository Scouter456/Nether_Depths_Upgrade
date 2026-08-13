package com.scouter.netherdepthsupgrade.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class GlowdineParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    GlowdineParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.friction = 0.96F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = pSprites;
        this.quadSize *= 0.75F;
        this.hasPhysics = false;
        this.setSpriteFromAge(pSprites);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getLightColor(float pPartialTick) {
        float f = ((float)this.age + pPartialTick) / (float)this.lifetime;
        f = Mth.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(pPartialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    public static class GlowdineProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public GlowdineProvider(SpriteSet pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            GlowdineParticle glowparticle = new GlowdineParticle(pLevel, pX, pY, pZ, 0.5D - pLevel.random.nextDouble(), pYSpeed, 0.5D - pLevel.random.nextDouble(), this.sprite);
            if (pLevel.random.nextBoolean()) {
                glowparticle.setColor(0.9F, 1.0F, 1.0F);
            } else {
                glowparticle.setColor(0.9F, 0.6F, 0.0F);
            }

            glowparticle.yd *= (double)0.2F;
            if (pXSpeed == 0.0D && pZSpeed == 0.0D) {
                glowparticle.xd *= (double)0.1F;
                glowparticle.zd *= (double)0.1F;
            }

            glowparticle.setLifetime((int)(8.0D / (pLevel.random.nextDouble() * 0.8D + 0.2D)));
            return glowparticle;
        }
    }

}
