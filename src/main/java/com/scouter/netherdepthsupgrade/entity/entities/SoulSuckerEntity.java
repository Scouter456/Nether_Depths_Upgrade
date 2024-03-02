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
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import org.slf4j.Logger;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class SoulSuckerEntity extends AbstractLavaFish implements GeoEntity {
    private static final EntityDataAccessor<BlockPos> SOULSAND_POS = SynchedEntityData.defineId(SoulSuckerEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Integer> SEEK_SOULSAND_TIMER = SynchedEntityData.defineId(SoulSuckerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> COOLDOWN_TTIMER = SynchedEntityData.defineId(SoulSuckerEntity.class, EntityDataSerializers.INT);
    public static final RawAnimation MOVING_SOULSUCKER = RawAnimation.begin().thenLoop("soulsucker.moving");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Logger LOGGER = LogUtils.getLogger();
    public int suckTimer = 0;
    @Nullable
    protected FishSwimGoal fishSwimGoal;

    public SoulSuckerEntity(EntityType<? extends AbstractLavaFish> p_27523_, Level p_27524_) {
        super(p_27523_, p_27524_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.fishSwimGoal = new FishSwimGoal(this);
        this.goalSelector.addGoal(1, new FindSoulSandGoal3(this));
        this.goalSelector.addGoal(4, this.fishSwimGoal);
        this.fishSwimGoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        //this.goalSelector.addGoal(4, new AbstractLavaFish.FishSwimGoal(this));
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            if (this.getCooldownTimer().intValue() > 0) {
                this.setSeekSoulSandTimer(this.getSeekSoulSandTimer() - 1);
                this.setCooldownTimer(this.getSeekSoulSandTimer());
            }
        }
        //LOGGER.info("time " + this.getSeekSoulSandTimer().intValue());
        //LOGGER.info("time2 " + this.getCooldownTimer().intValue());
        //LOGGER.info("position to go to " + this.moveControl.getWantedX() + "" + this.moveControl.getWantedY() + "" + this.moveControl.getWantedZ());
        suckTimer++;
        BlockPos blockPos = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        if ((this.level().getBlockState(blockPos.below()).is(Blocks.SOUL_SAND) || (this.level().getBlockState(blockPos).is(Blocks.SOUL_SAND)) && this.isInLava())) {
            this.level().addParticle(ParticleTypes.SOUL, this.getRandomX(0.6D), this.getY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
        }
    }


    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.SOULSUCKER_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD.get();
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SOUL_ESCAPE;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SOUL_SAND_HIT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SOUL_SAND_FALL;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "soulsucker.moving", 0,state -> state.setAndContinue(MOVING_SOULSUCKER)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SOULSAND_POS, BlockPos.ZERO);
        this.entityData.define(SEEK_SOULSAND_TIMER, 0);
        this.entityData.define(COOLDOWN_TTIMER, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("SoulSandPosX", this.getSoulSandPos().getX());
        pCompound.putInt("SoulSandPosY", this.getSoulSandPos().getY());
        pCompound.putInt("SoulSandPosZ", this.getSoulSandPos().getZ());
        pCompound.putInt("seeksoulsandtimer", this.getSeekSoulSandTimer());
        pCompound.putInt("cooldowntimer", this.getCooldownTimer());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        int i = pCompound.getInt("SoulSandPosX");
        int j = pCompound.getInt("SoulSandPosY");
        int k = pCompound.getInt("SoulSandPosZ");
        this.setSoulsandPos(new BlockPos(i, j, k));
        setSeekSoulSandTimer(pCompound.getInt("seeksoulsandtimer"));
        setCooldownTimer(pCompound.getInt("cooldowntimer"));
        super.readAdditionalSaveData(pCompound);
    }

    public void setSoulsandPos(BlockPos pPos) {
        this.entityData.set(SOULSAND_POS, pPos);
    }

    public BlockPos getSoulSandPos() {
        return this.entityData.get(SOULSAND_POS);
    }

    public void setSeekSoulSandTimer(Integer time) {
        this.entityData.set(SEEK_SOULSAND_TIMER, Integer.valueOf(time));
    }

    public Integer getSeekSoulSandTimer() {
        return this.entityData.get(SEEK_SOULSAND_TIMER);
    }

    public void setCooldownTimer(Integer time) {
        this.entityData.set(COOLDOWN_TTIMER, Integer.valueOf(time));
    }

    public Integer getCooldownTimer() {
        return this.entityData.get(COOLDOWN_TTIMER);
    }

    static class FindSoulSandGoal3 extends Goal {
        private static final Logger LOGGER = LogUtils.getLogger();
        private final SoulSuckerEntity mob;
        private int i = 0;
        private int counter = 0;
        private int suckCounter = 30;
        private BlockPos lastPos;
        private RandomSource rand = RandomSource.create();
        public final List<BlockPos> soulSandList = new ArrayList<>();
        private boolean stuck;

        public FindSoulSandGoal3(SoulSuckerEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return (this.mob.isInLava() && !this.mob.level().getBlockState(this.mob.blockPosition().below()).is(Blocks.SOUL_SAND)) && this.mob.getSeekSoulSandTimer().intValue() == 0;
        }

        @Override
        public boolean canContinueToUse() {
            BlockPos blockpos = this.mob.getSoulSandPos();
            return this.mob.getSeekSoulSandTimer().intValue() == 0;
        }


        @Override
        public void start() {
            if (this.mob.level() instanceof ServerLevel) {
                this.mob.getNavigation().stop();

                for (int x = -5; x < 5; x++) {
                    for (int y = 0; y < 10; y++) {
                        for (int z = -5; z < 5; z++) {
                            double posX = this.mob.blockPosition().getX();
                            double posY = this.mob.blockPosition().getY();
                            double posZ = this.mob.blockPosition().getZ();
                            BlockPos blockPos = BlockPos.containing(posX - x, posY - y, posZ - z);
                            if (this.mob.level().getBlockState(blockPos).is(Blocks.SOUL_SAND) && this.mob.level().getFluidState(blockPos.above()).is(Fluids.LAVA) &&
                                    !this.mob.level().getFluidState(blockPos.below()).is(Fluids.LAVA) && this.mob.level().getBlockState(blockPos.north()).is(Blocks.SOUL_SAND) &&
                                    this.mob.level().getBlockState(blockPos.east()).is(Blocks.SOUL_SAND) && this.mob.level().getBlockState(blockPos.south()).is(Blocks.SOUL_SAND) &&
                                    this.mob.level().getBlockState(blockPos.west()).is(Blocks.SOUL_SAND)) {
                                soulSandList.add(blockPos);

                            }
                        }
                    }
                }


                if (soulSandList.size() > 0) {

                    this.mob.getNavigation().moveTo(soulSandList.get(i).getX(), soulSandList.get(i).getY() + 0.5, soulSandList.get(i).getZ(), 1.0F);
                } else {
                    this.mob.setSeekSoulSandTimer(500);
                    this.mob.setCooldownTimer(this.mob.getSeekSoulSandTimer());
                    this.mob.fishSwimGoal.trigger();
                    this.stop();
                }
            }
        }

        @Override
        public void stop() {
            BlockPos blockpos = this.mob.getSoulSandPos();
            super.stop();
            this.mob.setSeekSoulSandTimer(500);
            this.mob.setCooldownTimer(this.mob.getSeekSoulSandTimer());
            this.mob.fishSwimGoal.trigger();
            soulSandList.clear();
            suckCounter = 0;
            counter = 0;
            i = 0;
        }

        //TODO very bad change to better code plz
        @Override
        public void tick() {

            super.tick();

            lastPos = this.mob.blockPosition();

            if (soulSandList.size() == 0 || (i >= soulSandList.size())) {
                this.stop();
                this.mob.fishSwimGoal.trigger();
                return;
            }

            if (!this.mob.level().getBlockState(soulSandList.get(i)).is(Blocks.SOUL_SAND) && counter < soulSandList.size()) {
                i++;
                counter++;
            }
            if (soulSandList.size() == 0 || (i >= soulSandList.size())) {
                this.stop();
                this.mob.fishSwimGoal.trigger();
                return;
            }
            if (((this.mob.level().getBlockState(this.mob.blockPosition().below()).is(Blocks.SOUL_SAND)) || (this.mob.level().getBlockState(this.mob.blockPosition().below()).is(Blocks.SOUL_SOIL))) && checkDistance(this.mob.blockPosition(), soulSandList.get(i))) {
                suckCounter++;
                this.mob.getNavigation().moveTo(soulSandList.get(i).getX(), soulSandList.get(i).getY() + 0.5, soulSandList.get(i).getZ(), 1.0F);
                if (suckCounter == 100) {
                    if (!this.mob.level().getBlockState(this.mob.blockPosition()).is(Blocks.SOUL_SAND)) {
                        this.mob.level().setBlock(this.mob.blockPosition().below(), Blocks.SOUL_SOIL.defaultBlockState(), 3);
                    } else {
                        this.mob.level().setBlock(this.mob.blockPosition(), Blocks.SOUL_SOIL.defaultBlockState(), 3);
                    }
                    this.mob.invulnerableTime = 30;
                    i++;
                    counter++;
                }
            } else {
                this.mob.getNavigation().moveTo(soulSandList.get(i).getX(), soulSandList.get(i).getY() + 1, soulSandList.get(i).getZ(), 1.0F);
            }

            if (suckCounter >= 100) {
                if (counter < soulSandList.size()) {
                    this.mob.getNavigation().moveTo(soulSandList.get(i).getX(), soulSandList.get(i).getY() + 0.5, soulSandList.get(i).getZ(), 1.0F);

                } else {
                    BlockPos blockPos = new BlockPos(this.mob.blockPosition().getX() + rand.nextInt(-5, 5), this.mob.blockPosition().getY() + rand.nextInt(0, 3), this.mob.blockPosition().getZ() + rand.nextInt(-5, 5));
                    if (this.mob.level().getFluidState(blockPos).is(Fluids.LAVA)) {
                        this.mob.getNavigation().moveTo(this.mob.blockPosition().getX() + rand.nextInt(-5, 5), this.mob.blockPosition().getY() + rand.nextInt(0, 3), this.mob.blockPosition().getZ() + rand.nextInt(-5, 5), 1.0F);
                    }
                    this.mob.setSeekSoulSandTimer(500);
                    this.stop();
                }
                suckCounter = 0;
            }

        }
    }


    public static boolean checkDistance(BlockPos entityPos, BlockPos blockPos) {
        double x1 = entityPos.getX();
        double x2 = blockPos.getX();
        double x12 = x1 - x2;
        double y1 = entityPos.getY();
        double y2 = blockPos.getY();
        double y12 = y1 - y2;
        double z1 = entityPos.getZ();
        double z2 = entityPos.getZ();
        double z12 = z1 - z2;

        double disTot = (x12 * x12) + (y12 * y12) + (z12 * z12);
        //LOGGER.info("dis " + disTot);
        return (disTot <= 3.0);
    }
}
