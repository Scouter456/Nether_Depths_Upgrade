package com.scouter.netherdepthsupgrade.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class GlowdineParticle extends SpriteTexturedParticle {
    static final Random RANDOM = new Random();
    private final IAnimatedSprite sprites;

    GlowdineParticle(ClientWorld pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, IAnimatedSprite pSprites) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        //this.friction = 0.96F;
        //this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = pSprites;
        this.quadSize *= 0.75F;
        this.hasPhysics = false;
        this.setSpriteFromAge(pSprites);
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getLightColor(float pPartialTick) {
        float f = ((float)this.age + pPartialTick) / (float)this.lifetime;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
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

    @OnlyIn(Dist.CLIENT)
    public static class ElectricSparkProvider implements IParticleFactory<BasicParticleType> {
        private final double SPEED_FACTOR = 0.25D;
        private final IAnimatedSprite sprite;

        public ElectricSparkProvider(IAnimatedSprite pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(BasicParticleType pType, ClientWorld pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            GlowdineParticle glowparticle = new GlowdineParticle(pLevel, pX, pY, pZ, 0.0D, 0.0D, 0.0D, this.sprite);
            glowparticle.setColor(2.0F, 0.0F, 2.0F);
            glowparticle.setParticleSpeed(pXSpeed * 0.25D, pYSpeed * 0.25D, pZSpeed * 0.25D);
            int i = 2;
            int j = 4;
            glowparticle.setLifetime(pLevel.random.nextInt(2) + 2);
            return glowparticle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class GlowdineProvider implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public GlowdineProvider(IAnimatedSprite pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(BasicParticleType pType, ClientWorld pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            GlowdineParticle glowparticle = new GlowdineParticle(pLevel, pX, pY, pZ, 0.5D - GlowdineParticle.RANDOM.nextDouble(), pYSpeed, 0.5D - GlowdineParticle.RANDOM.nextDouble(), this.sprite);
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

    @OnlyIn(Dist.CLIENT)
    public static class ScrapeProvider implements IParticleFactory<BasicParticleType> {
        private final double SPEED_FACTOR = 0.01D;
        private final IAnimatedSprite sprite;

        public ScrapeProvider(IAnimatedSprite pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(BasicParticleType pType, ClientWorld pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            GlowdineParticle glowparticle = new GlowdineParticle(pLevel, pX, pY, pZ, 0.0D, 0.0D, 0.0D, this.sprite);
            if (pLevel.random.nextBoolean()) {
                glowparticle.setColor(0.29F, 0.58F, 0.51F);
            } else {
                glowparticle.setColor(0.43F, 0.77F, 0.62F);
            }

            glowparticle.setParticleSpeed(pXSpeed * 0.01D, pYSpeed * 0.01D, pZSpeed * 0.01D);
            int i = 10;
            int j = 40;
            glowparticle.setLifetime(pLevel.random.nextInt(30) + 10);
            return glowparticle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class WaxOffProvider implements IParticleFactory<BasicParticleType> {
        private final double SPEED_FACTOR = 0.01D;
        private final IAnimatedSprite sprite;

        public WaxOffProvider(IAnimatedSprite pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(BasicParticleType pType, ClientWorld pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            GlowdineParticle glowparticle = new GlowdineParticle(pLevel, pX, pY, pZ, 0.0D, 0.0D, 0.0D, this.sprite);
            glowparticle.setColor(1.0F, 0.9F, 1.0F);
            glowparticle.setParticleSpeed(pXSpeed * 0.01D / 2.0D, pYSpeed * 0.01D, pZSpeed * 0.01D / 2.0D);
            int i = 10;
            int j = 40;
            glowparticle.setLifetime(pLevel.random.nextInt(30) + 10);
            return glowparticle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class WaxOnProvider implements IParticleFactory<BasicParticleType> {
        private final double SPEED_FACTOR = 0.01D;
        private final IAnimatedSprite sprite;

        public WaxOnProvider(IAnimatedSprite pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(BasicParticleType pType, ClientWorld pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            GlowdineParticle glowparticle = new GlowdineParticle(pLevel, pX, pY, pZ, 0.0D, 0.0D, 0.0D, this.sprite);
            glowparticle.setColor(0.91F, 0.55F, 0.08F);
            glowparticle.setParticleSpeed(pXSpeed * 0.01D / 2.0D, pYSpeed * 0.01D, pZSpeed * 0.01D / 2.0D);
            int i = 10;
            int j = 40;
            glowparticle.setLifetime(pLevel.random.nextInt(30) + 10);
            return glowparticle;
        }
    }

    public void setParticleSpeed(double pXd, double pYd, double pZd) {
        this.xd = pXd;
        this.yd = pYd;
        this.zd = pZd;
    }
}
