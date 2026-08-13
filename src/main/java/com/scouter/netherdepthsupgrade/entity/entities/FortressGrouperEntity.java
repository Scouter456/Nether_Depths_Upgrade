package com.scouter.netherdepthsupgrade.entity.entities;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import com.scouter.netherdepthsupgrade.entity.ai.FishSwimGoal;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.List;

public class FortressGrouperEntity extends AbstractLavaFish implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final RawAnimation MOVING_FORTRESS_GROUPER = RawAnimation.begin().thenLoop("fortressgrouper.moving");
    public FortressGrouperEntity(EntityType<? extends AbstractLavaFish> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ARMOR, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)

                //.add(Attributes.MOVEMENT_SPEED, 3.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(4, new GulpGoal(this, 7F));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, GlowdineEntity.class,true, false));
        this.targetSelector.addGoal(4, new MoveTowardsTargetGoal(this, 2, 20F));

    }
    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInLava()) {
            this.moveRelative(0.01F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "fortressgrouper.moving", 0, state -> state.setAndContinue(MOVING_FORTRESS_GROUPER)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }



    @Override
    public boolean isPushable() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.NETHER_BRICKS_FALL;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.FORTRESS_GROUPER_BUCKET);
    }


    private static class GulpGoal extends Goal {

        private static final int ATTACK_INTERVAL = 20;
        private static final double PULL_SPEED = 0.15D;
        private static final double CONTACT_INFLATION = 2.0D;

        private final FortressGrouperEntity mob;
        private final double attackRangeSqr;

        private int attackCooldown;

        private GulpGoal(FortressGrouperEntity mob, float attackRange) {
            this.mob = mob;
            this.attackRangeSqr = attackRange * attackRange;

            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return this.hasValidTargetInRange();
        }

        @Override
        public boolean canContinueToUse() {
            return this.hasValidTargetInRange();
        }

        private boolean hasValidTargetInRange() {
            LivingEntity target = this.mob.getTarget();

            return target != null && target.isAlive() && this.mob.distanceToSqr(target) < this.attackRangeSqr && this.mob.getSensing().hasLineOfSight(target);
        }

        @Override
        public void start() {
            this.attackCooldown = 0;
            this.mob.getNavigation().stop();
        }

        @Override
        public void stop() {
            this.attackCooldown = 0;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();

            if (target == null || !target.isAlive()) {
                return;
            }

            this.mob.getLookControl().setLookAt(
                    target,
                    30.0F,
                    30.0F
            );

            Vec3 directionToGrouper = this.mob.position().subtract(target.position());

            if (directionToGrouper.lengthSqr() > 1.0E-6D) {
                Vec3 pullVelocity = directionToGrouper.normalize().scale(PULL_SPEED);

                target.setDeltaMovement(pullVelocity);
                target.hurtMarked = true;
            }

            if (this.attackCooldown > 0) {
                this.attackCooldown--;
            }

            boolean touchingTarget = this.mob.getBoundingBox().inflate(CONTACT_INFLATION).intersects(target.getBoundingBox());

            if (touchingTarget && this.attackCooldown <= 0) {
                this.mob.doHurtTarget(target);
                this.attackCooldown = ATTACK_INTERVAL;
            }
        }
    }

}
