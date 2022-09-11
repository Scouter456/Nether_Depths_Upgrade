package com.scouter.netherdepthsupgrade.entity;

import com.scouter.netherdepthsupgrade.entity.ai.FishSwimGoal;
import com.scouter.netherdepthsupgrade.entity.ai.LavaBoundPathNavigation;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.EnumSet;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public abstract class AbstractLavaFish extends LavaAnimal {
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    private static final DataParameter<Boolean> FROM_BUCKET = EntityDataManager.defineId(AbstractLavaFish.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> JUMPING = EntityDataManager.defineId(AbstractLavaFish.class, DataSerializers.BOOLEAN);
    @Nullable
    public FishSwimGoal fishSwimGoal;


    public AbstractLavaFish(EntityType<? extends AbstractLavaFish> p_27461_, World p_27462_) {
        super(p_27461_, p_27462_);
        this.moveControl = new FishMoveControl(this);
    }

    protected float getStandingEyeHeight(Pose pPose, EntitySize pSize) {
        return pSize.height * 0.65F;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D);
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnClusterSize() {
        return 8;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(JUMPING, Boolean.valueOf(false));
    }

    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }
    public void setFromBucket(boolean p_27498_) {
        this.entityData.set(FROM_BUCKET, p_27498_);
    }
    public void setIsJumping(boolean isJumping){
        this.entityData.set(JUMPING, Boolean.valueOf(isJumping));
    }
    public boolean getIsJumping(){
        return this.entityData.get(JUMPING).booleanValue();
    }

    public void addAdditionalSaveData(CompoundNBT pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());
        pCompound.putBoolean("isJumping", this.getIsJumping());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundNBT pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
        setIsJumping(pCompound.getBoolean("isJumping"));
    }


    protected void registerGoals() {
        super.registerGoals();
        this.fishSwimGoal = new FishSwimGoal(this);
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6D, 1.4D, EntityPredicates.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishSwimGoal(this));
        this.fishSwimGoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    protected PathNavigator createNavigation(World pLevel) {
        return new LavaBoundPathNavigation(this, pLevel);
   }

    @Override
    public void travel(Vector3d pTravelVector) {
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
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void aiStep() {
        if(this.isInWater()){
            this.hurt(DamageSource.LAVA, 4.0F);
        }
        if (!this.isInLava() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.onGround = false;
            this.hasImpulse = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }

        super.aiStep();
    }

    protected ActionResultType mobInteract(PlayerEntity pPlayer, Hand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.getItem() == Items.LAVA_BUCKET && this.isAlive()) {
            this.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
            itemstack.shrink(1);
            ItemStack itemstack1 = this.getBucketItemStack();
            this.saveToBucketTag(itemstack1);
            if (!this.level.isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity)pPlayer, itemstack1);
            }

            if (itemstack.isEmpty()) {
                pPlayer.setItemInHand(pHand, itemstack1);
            } else if (!pPlayer.inventory.add(itemstack1)) {
                pPlayer.drop(itemstack1, false);
            }

            this.remove();
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }
    @Override
    public boolean fireImmune() {
        return true;
    }


    protected void saveToBucketTag(ItemStack pBucketStack) {
        if (this.hasCustomName()) {
            pBucketStack.setHoverName(this.getCustomName());
        }

    }

    public abstract ItemStack getBucketItemStack();

    protected boolean canRandomSwim() {
        return true;
    }

    protected abstract SoundEvent getFlopSound();

    protected SoundEvent getSwimSound() {
        return SoundEvents.FISH_SWIM;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
    }

    static class FishMoveControl extends MovementController {
        private final AbstractLavaFish fish;

        FishMoveControl(AbstractLavaFish p_27501_) {
            super(p_27501_);
            this.fish = p_27501_;
        }

        public void tick() {
            //LOGGER.info("in eyefluid? " + this.fish.isEyeInFluid(FluidTags.LAVA));
            if (this.fish.isEyeInFluid(FluidTags.LAVA)) {
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }


            if (this.operation == MovementController.Action.MOVE_TO && !this.fish.getNavigation().isDone()) {
                float f = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.fish.setSpeed(MathHelper.lerp(0.125F, this.fish.getSpeed(), f));
                double d0 = this.wantedX - this.fish.getX();
                double d1 = this.wantedY - this.fish.getY();
                double d2 = this.wantedZ - this.fish.getZ();
                if (d1 != 0.0D) {
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
                }

                if (d0 != 0.0D || d2 != 0.0D) {
                    float f1 = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.fish.yRot = this.rotlerp(this.fish.yRot, f1, 90.0F);
                    this.fish.yBodyRot = this.fish.yRot;
                }

            } else {
                this.fish.setSpeed(0.0F);
            }
        }
    }
}