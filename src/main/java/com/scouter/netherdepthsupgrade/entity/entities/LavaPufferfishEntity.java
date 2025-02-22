package com.scouter.netherdepthsupgrade.entity.entities;


import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class LavaPufferfishEntity extends AbstractLavaFish implements GeoEntity {
    public static final RawAnimation MOVING_PUFFERFISH = RawAnimation.begin().thenLoop("animation.pufferfish.swim");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Integer> PUFF_STATE = SynchedEntityData.defineId(LavaPufferfishEntity.class, EntityDataSerializers.INT);
    int inflateCounter;
    int deflateTimer;
    private static final TargetingConditions.Selector SCARY_MOB = (livingEntity, serverLevel) -> {
        if (livingEntity instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
        }

        return !livingEntity.getType().is(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH);
    };
    static final TargetingConditions targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(SCARY_MOB);
    public static final int STATE_SMALL = 0;
    public static final int STATE_MID = 1;
    public static final int STATE_FULL = 2;

    public LavaPufferfishEntity(EntityType<? extends LavaPufferfishEntity> p_29602_, Level p_29603_) {
        super(p_29602_, p_29603_);
        this.refreshDimensions();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(PUFF_STATE, 0);

    }

    public int getPuffState() {
        return this.entityData.get(PUFF_STATE);
    }

    public void setPuffState(int p_29619_) {
        this.entityData.set(PUFF_STATE, p_29619_);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (PUFF_STATE.equals(pKey)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(pKey);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("PuffState", this.getPuffState());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setPuffState(Math.min(pCompound.getInt("PuffState"), 2));
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.LAVA_PUFFERFISH_BUCKET);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new PufferfishPuffGoal(this));
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        if (!this.level().isClientSide && this.isAlive() && this.isEffectiveAi()) {
            if (this.inflateCounter > 0) {
                if (this.getPuffState() == 0) {
                    this.playSound(SoundEvents.PUFFER_FISH_BLOW_UP, this.getSoundVolume(), this.getVoicePitch());
                    this.setPuffState(1);
                } else if (this.inflateCounter > 40 && this.getPuffState() == 1) {
                    this.playSound(SoundEvents.PUFFER_FISH_BLOW_UP, this.getSoundVolume(), this.getVoicePitch());
                    this.setPuffState(2);
                }

                ++this.inflateCounter;
            } else if (this.getPuffState() != 0) {
                if (this.deflateTimer > 60 && this.getPuffState() == 2) {
                    this.playSound(SoundEvents.PUFFER_FISH_BLOW_OUT, this.getSoundVolume(), this.getVoicePitch());
                    this.setPuffState(1);
                } else if (this.deflateTimer > 100 && this.getPuffState() == 1) {
                    this.playSound(SoundEvents.PUFFER_FISH_BLOW_OUT, this.getSoundVolume(), this.getVoicePitch());
                    this.setPuffState(0);
                }

                ++this.deflateTimer;
            }
        }

        super.tick();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void aiStep() {
        super.aiStep();
        Level level = this.level();

            if(level instanceof ServerLevel serverLevel) {
                if (this.isAlive() && this.getPuffState() > 0) {
                    for(Mob mob : this.level().getEntitiesOfClass(Mob.class, this.getBoundingBox().inflate(0.3D), (p_149013_) -> {
                        return targetingConditions.test(serverLevel, this, p_149013_);
                    })) {
                        if (mob.isAlive()) {
                            this.touch(serverLevel, mob);
                        }
                    }
                }
            }





    }

    private void touch(ServerLevel serverLevel, Mob mob) {
        int i = this.getPuffState();
        if (mob.hurtServer(serverLevel, this.damageSources().mobAttack(this), (float)(1 + i))) {
            mob.addEffect(new MobEffectInstance(MobEffects.WITHER, 60 * i, 0), this);
            this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0F, 1.0F);
        }

    }


    /**
     * Called by a player entity when they collide with an entity
     */

    public void playerTouch(Player player) {
        int i = this.getPuffState();
        if (player instanceof ServerPlayer serverPlayer) {
            if (i > 0 && player.hurtServer(serverPlayer.serverLevel(), this.damageSources().mobAttack(this), (float)(1 + i))) {
                if (!this.isSilent()) {
                    serverPlayer.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0F));
                }

                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 60 * i, 0), this);
            }
        }

    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PUFFER_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PUFFER_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PUFFER_FISH_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.PUFFER_FISH_FLOP;
    }

    @Override
    protected EntityDimensions getDefaultDimensions(Pose pPose) {
        return  this.getType().getDimensions().scale(getScale(this.getPuffState()));
    }

    private static float getScale(int p_29639_) {
        switch(p_29639_) {
            case 0:
                return 0.5F;
            case 1:
                return 0.7F;
            default:
                return 1.0F;
        }
    }


    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "pufferfish.moving", 0, state -> state.setAndContinue(MOVING_PUFFERFISH)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    static class PufferfishPuffGoal extends Goal {
        private final LavaPufferfishEntity fish;

        public PufferfishPuffGoal(LavaPufferfishEntity p_29642_) {
            this.fish = p_29642_;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            Level level = this.fish.level();
            if(level instanceof ServerLevel serverLevel) {
                List<LivingEntity> list = this.fish.level().getEntitiesOfClass(LivingEntity.class, this.fish.getBoundingBox().inflate(2.0D), (p_149015_) -> {
                    return LavaPufferfishEntity.targetingConditions.test(serverLevel, this.fish, p_149015_);
                });
                return !list.isEmpty();
            }
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.fish.inflateCounter = 1;
            this.fish.deflateTimer = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.fish.inflateCounter = 0;
        }
    }
}

