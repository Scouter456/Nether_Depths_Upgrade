package com.scouter.netherdepthsupgrade.entity.entities;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import com.scouter.netherdepthsupgrade.entity.ai.FishSwimGoal;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.slf4j.Logger;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class SoulSuckerEntity extends AbstractLavaFish implements GeoEntity {
    private static final int SOUL_SAND_SEARCH_COOLDOWN = 500;

    private int soulSandSearchCooldown;

    public static final RawAnimation MOVING_SOULSUCKER = RawAnimation.begin().thenLoop("soulsucker.moving");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Logger LOGGER = LogUtils.getLogger();
    @Nullable
    protected FishSwimGoal fishSwimGoal;

    public SoulSuckerEntity(EntityType<? extends AbstractLavaFish> p_27523_, Level p_27524_) {
        super(p_27523_, p_27524_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FindSoulSandGoal(this));
    }

    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide) {
            if (this.soulSandSearchCooldown > 0) {
                this.soulSandSearchCooldown--;
            }

            return;
        }

        if (!this.isInLava() || this.getRandom().nextInt(3) != 0) {
            return;
        }

        BlockPos currentPos = this.blockPosition();

        boolean touchingSoulSand = this.level().getBlockState(currentPos).is(Blocks.SOUL_SAND) || this.level().getBlockState(currentPos.below()).is(Blocks.SOUL_SAND);

        if (!touchingSoulSand) {
            return;
        }

        this.level().addParticle(ParticleTypes.SOUL, this.getRandomX(0.6D), this.getY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
    }


    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.SOULSUCKER_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD.value();
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SOUL_ESCAPE.value();
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SOUL_SAND_HIT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SOUL_SAND_FALL;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this, "soulsucker.moving", 0, state -> state.setAndContinue(MOVING_SOULSUCKER)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("SoulSandSearchCooldown", this.soulSandSearchCooldown);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);

        if (pCompound.contains("SoulSandSearchCooldown")) {
            this.soulSandSearchCooldown = Math.max(0, pCompound.getInt("SoulSandSearchCooldown"));
        } else {
            int oldSeekTimer = pCompound.getInt("seeksoulsandtimer");
            int oldCooldownTimer = pCompound.getInt("cooldowntimer");
            this.soulSandSearchCooldown = Math.max(0, Math.max(oldSeekTimer, oldCooldownTimer));
        }
    }


   private boolean isSoulSandSearchReady() {
       return this.soulSandSearchCooldown <= 0;
   }

    private void resetSoulSandSearchCooldown() {
        this.soulSandSearchCooldown =
                SOUL_SAND_SEARCH_COOLDOWN;
    }

    private static class FindSoulSandGoal extends Goal {

        private static final int HORIZONTAL_SEARCH_RADIUS = 5;
        private static final int VERTICAL_SEARCH_DEPTH = 9;
        private static final int REQUIRED_DRAIN_TICKS = 100;
        private static final int MAX_GOAL_TICKS = 20 * 30;

        private static final double MOVEMENT_SPEED = 1.0D;
        private static final double ARRIVAL_DISTANCE_SQR = 2.25D;

        private final SoulSuckerEntity mob;

        private BlockPos targetPos;
        private int drainTicks;
        private int elapsedTicks;
        private boolean finished;

        private FindSoulSandGoal(SoulSuckerEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (!this.mob.isInLava()
                    || !this.mob.isSoulSandSearchReady()) {
                return false;
            }

            this.targetPos = this.findNearestSoulSand();

            if (this.targetPos == null) {
                // Prevent an expensive area scan every tick when
                // no valid Soul Sand exists nearby.
                this.mob.resetSoulSandSearchCooldown();
                return false;
            }

            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return !this.finished
                    && this.targetPos != null
                    && this.mob.isAlive()
                    && this.mob.isInLava()
                    && this.mob.isSoulSandSearchReady()
                    && this.elapsedTicks < MAX_GOAL_TICKS
                    && this.isValidSoulSandTarget(this.targetPos);
        }

        @Override
        public void start() {
            this.drainTicks = 0;
            this.elapsedTicks = 0;
            this.finished = false;

            this.moveToTarget();
        }

        @Override
        public void stop() {
            this.mob.getNavigation().stop();
            this.mob.resetSoulSandSearchCooldown();

            if (this.mob.fishSwimGoal != null) {
                this.mob.fishSwimGoal.trigger();
            }

            this.targetPos = null;
            this.drainTicks = 0;
            this.elapsedTicks = 0;
            this.finished = false;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            if (this.targetPos == null) {
                this.finished = true;
                return;
            }

            this.elapsedTicks++;

            double targetX = this.targetPos.getX() + 0.5D;
            double targetY = this.targetPos.getY() + 1.0D;
            double targetZ = this.targetPos.getZ() + 0.5D;

            this.mob.getLookControl().setLookAt(targetX, targetY, targetZ);

            double distanceSqr = this.mob.distanceToSqr(targetX, targetY, targetZ);

            if (distanceSqr > ARRIVAL_DISTANCE_SQR) {
                this.drainTicks = 0;

                if (this.mob.getNavigation().isDone()
                        || this.elapsedTicks % 20 == 0) {
                    this.moveToTarget();
                }

                return;
            }

            this.mob.getNavigation().stop();
            this.drainTicks++;

            if (this.drainTicks < REQUIRED_DRAIN_TICKS) {
                return;
            }

            if (this.isValidSoulSandTarget(this.targetPos)) {
                this.mob.level().setBlock(
                        this.targetPos,
                        Blocks.SOUL_SOIL.defaultBlockState(),
                        UPDATE_ALL
                );
            }

            this.finished = true;
        }

        private void moveToTarget() {
            if (this.targetPos == null) {
                return;
            }

            this.mob.getNavigation().moveTo(this.targetPos.getX() + 0.5D, this.targetPos.getY() + 1.0D, this.targetPos.getZ() + 0.5D, MOVEMENT_SPEED);
        }

        private BlockPos findNearestSoulSand() {
            BlockPos origin = this.mob.blockPosition();
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            BlockPos nearestTarget = null;
            double nearestDistanceSqr = Double.MAX_VALUE;

            for (int x = -HORIZONTAL_SEARCH_RADIUS;
                 x <= HORIZONTAL_SEARCH_RADIUS;
                 x++) {

                for (int y = 0;
                     y >= -VERTICAL_SEARCH_DEPTH;
                     y--) {

                    for (int z = -HORIZONTAL_SEARCH_RADIUS;
                         z <= HORIZONTAL_SEARCH_RADIUS;
                         z++) {

                        mutablePos.setWithOffset(
                                origin,
                                x,
                                y,
                                z
                        );

                        if (!this.isValidSoulSandTarget(mutablePos)) {
                            continue;
                        }

                        double distanceSqr =
                                origin.distSqr(mutablePos);

                        if (distanceSqr < nearestDistanceSqr) {
                            nearestDistanceSqr = distanceSqr;
                            nearestTarget = mutablePos.immutable();
                        }
                    }
                }
            }

            return nearestTarget;
        }

        private boolean isValidSoulSandTarget(BlockPos position) {
            if (!this.mob.level().getBlockState(position).is(Blocks.SOUL_SAND)) {
                return false;
            }

            FluidState fluidAbove = this.mob.level().getFluidState(position.above());

            if (!fluidAbove.is(FluidTags.LAVA) || !fluidAbove.isSource()) {
                return false;
            }

            if (this.mob.level().getFluidState(position.below()).is(FluidTags.LAVA)) {
                return false;
            }

            return this.mob.level().getBlockState(position.north()).is(Blocks.SOUL_SAND)
                    && this.mob.level().getBlockState(position.east()).is(Blocks.SOUL_SAND)
                    && this.mob.level().getBlockState(position.south()).is(Blocks.SOUL_SAND)
                    && this.mob.level().getBlockState(position.west()).is(Blocks.SOUL_SAND);
        }
    }
}
