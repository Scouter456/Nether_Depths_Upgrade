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
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
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

        this.fishSwimGoal = new FishSwimGoal(this);
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));

        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishSwimGoal(this));
        this.fishSwimGoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
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
        return new ItemStack(NDUItems.FORTRESS_GROUPER_BUCKET.get());
    }


    class GulpGoal extends Goal {
        protected final AbstractLavaFish mob;
        protected final float attackRange;
        protected List<LivingEntity> entityList;
        public GulpGoal(AbstractLavaFish fish, float attackRange) {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            this.mob = fish;
            this.attackRange = attackRange;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (this.mob.getTarget() != null &&(this.mob.distanceToSqr(this.mob.getTarget()) < (double)(this.attackRange * this.attackRange))) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return this.mob.getTarget() != null && this.mob.getTarget().isAlive() && (this.mob.getTarget().distanceToSqr(this.mob) > (double)(this.attackRange * this.attackRange));
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
        //   LivingEntity livingentity = this.mob.getTarget();
        //   entityList = this.mob.level.getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate(3), (mobs) -> {
        //       return (!(mobs instanceof Player) || !mobs.isSpectator() && !((Player)mobs).isCreative()) && mobs != this.mob && !(mobs instanceof FortressGrouperEntity);});
        //   if (entityList.isEmpty() && livingentity != null) {
        //       this.stop();
        //   }
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void stop() {
            this.mob.fishSwimGoal.trigger();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = this.mob.getTarget();
            if(livingentity != null){
                double d0 = this.mob.getX();
                double d1 = this.mob.getY();
                double d2 = this.mob.getZ();

                double d3 = livingentity.getX();
                double d4 = livingentity.getY();
                double d5 = livingentity.getZ();

                double moveX = d0 - d3;
                double moveY = d1 - d4;
                double moveZ = d2 - d5;
                Vec3 vec3 = new Vec3(moveX, moveY, moveZ);
                livingentity.setDeltaMovement(vec3.scale(0.05));
                livingentity.hurtMarked = true;
                if (livingentity != null) {
                    if (this.mob.getBoundingBox().inflate(2).intersects(livingentity.getBoundingBox())) {
                        this.mob.doHurtTarget(livingentity);
                    }
                }
            }
           // for(LivingEntity entity : this.entityList){
           //     double d0 = this.mob.getX();
           //     double d1 = this.mob.getY();
           //     double d2 = this.mob.getZ();
//
           //     double d3 = entity.getX();
           //     double d4 = entity.getY();
           //     double d5 = entity.getZ();
//
           //     double moveX = d0 - d3;
           //     double moveY = d1 - d4;
           //     double moveZ = d2 - d5;
           //     Vec3 vec3 = new Vec3(moveX, moveY, moveZ);
           //     entity.setDeltaMovement(vec3.scale(0.5));
           //     entity.hurtMarked = true;
           //     if (entity != null) {
           //         if (this.mob.getBoundingBox().inflate(2).intersects(entity.getBoundingBox())) {
           //             this.mob.doHurtTarget(entity);
           //         }
           //     }
           // }
        }
    }

}
