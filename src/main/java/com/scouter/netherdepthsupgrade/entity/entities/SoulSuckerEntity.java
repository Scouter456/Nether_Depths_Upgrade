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
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class SoulSuckerEntity extends AbstractLavaFish implements IAnimatable, IAnimationTickable {
    private static final EntityDataAccessor<BlockPos> SOULSAND_POS = SynchedEntityData.defineId(SoulSuckerEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Integer> SEEK_SOULSAND_TIMER = SynchedEntityData.defineId(SoulSuckerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> COOLDOWN_TTIMER = SynchedEntityData.defineId(SoulSuckerEntity.class, EntityDataSerializers.INT);
    private AnimationFactory factory = new AnimationFactory(this);
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
        if (!this.level.isClientSide) {
            if (this.getCooldownTimer().intValue() > 0) {
                this.setSeekSoulSandTimer(this.getSeekSoulSandTimer() - 1);
                this.setCooldownTimer(this.getSeekSoulSandTimer());
            }
        }
        //LOGGER.info("time " + this.getSeekSoulSandTimer().intValue());
        //LOGGER.info("time2 " + this.getCooldownTimer().intValue());
        //LOGGER.info("position to go to " + this.moveControl.getWantedX() + "" + this.moveControl.getWantedY() + "" + this.moveControl.getWantedZ());
        suckTimer++;
        BlockPos blockPos = new BlockPos(this.getX(), this.getY(), this.getZ());
        if ((this.level.getBlockState(blockPos.below()).is(Blocks.SOUL_SAND) || (this.level.getBlockState(blockPos).is(Blocks.SOUL_SAND)) && this.isInLava())) {
            this.level.addParticle(ParticleTypes.SOUL, this.getRandomX(0.6D), this.getY(), this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
        }
    }


    public ItemStack getBucketItemStack() {
        return new ItemStack(NDUItems.SOULSUCKER_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD;
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

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("soulsucker.moving", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<SoulSuckerEntity> controller = new AnimationController<>(this, "controller", 0, this::predicate);
        data.addAnimationController(controller);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
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

    protected boolean closeToNextPos() {
        BlockPos blockpos = this.getNavigation().getTargetPos();
        return blockpos != null ? blockpos.closerToCenterThan(this.position(), 0.0D) : false;
    }

    static class FindSoulSandGoal extends Goal {
        private static final Logger LOGGER = LogUtils.getLogger();
        private final SoulSuckerEntity mob;
        private boolean stuck;

        public FindSoulSandGoal(SoulSuckerEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        @Override
        public boolean canUse() {
            //LOGGER.info("in lava? " + this.mob.isInLava());
            //LOGGER.info("Block below? " + (this.mob.level.getBlockState(this.mob.blockPosition().below()) != Blocks.SOUL_SAND.defaultBlockState()));
            //LOGGER.info("Pos" + this.mob.blockPosition());
            return (this.mob.isInLava() && !this.mob.level.getBlockState(this.mob.blockPosition().below()).is(Blocks.SOUL_SAND)) && this.mob.getSeekSoulSandTimer().intValue() == 0;
        }

        @Override
        public boolean canContinueToUse() {
            BlockPos blockpos = this.mob.getSoulSandPos();
            return (!(new BlockPos((double) blockpos.getX(), this.mob.getY(), (double) blockpos.getZ())).closerToCenterThan(this.mob.position(), 0.0D) && !this.stuck) || this.mob.getSeekSoulSandTimer().intValue() < 300;
        }


        @Override
        public void start() {
            if (this.mob.level instanceof ServerLevel) {
                this.mob.setSeekSoulSandTimer(this.mob.getSeekSoulSandTimer() + 1);
                ServerLevel serverlevel = (ServerLevel) this.mob.level;
                this.stuck = false;
                this.mob.getNavigation().stop();
                BlockPos blockpos = this.mob.blockPosition();
                for (BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(this.mob.getX() - 2.0D), Mth.floor(this.mob.getY() - 10.0D), Mth.floor(this.mob.getZ() - 2.0D), Mth.floor(this.mob.getX() + 2.0D), this.mob.getBlockY() + 2, Mth.floor(this.mob.getZ() + 2.0D))) {
                    //LOGGER.info("blocks " + this.mob.level.getBlockState(blockpos1));
                    if (this.mob.level.getBlockState(blockpos1).is(Blocks.SOUL_SAND) && this.mob.level.getFluidState(blockpos1.above()).is(Fluids.LAVA) && !this.mob.level.getFluidState(blockpos1.below()).is(Fluids.LAVA)) {
                        //LOGGER.info("I am getting set at " + this.mob.level.getBlockState(blockpos1));
                        //LOGGER.info("I am getting set at pos " + blockpos1);
                        this.mob.setSoulsandPos(blockpos1);
                        blockpos = blockpos1;
                        break;
                    } else {
                        this.mob.setSeekSoulSandTimer(300);
                        this.mob.setCooldownTimer(this.mob.getSeekSoulSandTimer());
                        this.mob.fishSwimGoal.trigger();
                    }
                }
            }
        }

        @Override
        public void stop() {
            BlockPos blockpos = this.mob.getSoulSandPos();
            if ((new BlockPos((double) blockpos.getX(), this.mob.getY(), (double) blockpos.getZ())).closerToCenterThan(this.mob.position(), 0.0D) || this.stuck || this.mob.getSeekSoulSandTimer().intValue() >= 300) {
                super.stop();
                this.mob.setSeekSoulSandTimer(300);
                this.mob.setCooldownTimer(this.mob.getSeekSoulSandTimer());
                this.mob.fishSwimGoal.trigger();
            }
        }

        @Override
        public void tick() {
            this.mob.setSeekSoulSandTimer(this.mob.getSeekSoulSandTimer() + 1);
            Level level = this.mob.level;
            if (this.mob.closeToNextPos() || this.mob.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atCenterOf(this.mob.getSoulSandPos());
                LOGGER.info("vec3" + vec3);
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.mob, 16, 0, vec3, (double) ((float) Math.PI / 8F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.mob, 8, 0, vec3, (double) ((float) Math.PI / 2F));
                }

                if (vec31 != null) {
                    BlockPos blockpos = new BlockPos(vec31);
                    if (!level.getFluidState(blockpos).is(FluidTags.LAVA)) {
                        vec31 = DefaultRandomPos.getPosTowards(this.mob, 8, 0, vec3, (double) ((float) Math.PI / 2F));
                    }
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }
                //LOGGER.info("vec31" + vec31);
                this.mob.getLookControl().setLookAt(vec3.x, vec3.y, vec3.z, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
                this.mob.getNavigation().moveTo(vec3.x - 1, vec3.y - 1, vec3.z - 1, 0.3D);
                if (level.random.nextInt(this.adjustedTickDelay(80)) == 0) {
                    level.broadcastEntityEvent(this.mob, (byte) 38);
                    this.mob.fishSwimGoal.trigger();

                }
            }

        }

    }

    static class FindSoulSandGoal3 extends Goal {
        private static final Logger LOGGER = LogUtils.getLogger();
        private final SoulSuckerEntity mob;
        private int i = 0;
        private int counter = 0;
        private int suckCounter = 30;
        private BlockPos lastPos;
        private Random rand = new Random();
        public final List<BlockPos> soulSandList = new ArrayList<>();
        private boolean stuck;

        public FindSoulSandGoal3(SoulSuckerEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return (this.mob.isInLava() && !this.mob.level.getBlockState(this.mob.blockPosition().below()).is(Blocks.SOUL_SAND)) && this.mob.getSeekSoulSandTimer().intValue() == 0;
        }

        @Override
        public boolean canContinueToUse() {
            BlockPos blockpos = this.mob.getSoulSandPos();
            return this.mob.getSeekSoulSandTimer().intValue() == 0;
        }


        @Override
        public void start() {
            //LOGGER.info("starting!!");
            if (this.mob.level instanceof ServerLevel) {
                ServerLevel serverlevel = (ServerLevel) this.mob.level;
                this.mob.getNavigation().stop();
                BlockPos blockpos = this.mob.blockPosition();
                for (int x = -5; x < 5; x++) {
                    for (int y = 0; y < 10; y++) {
                        for (int z = -5; z < 5; z++) {
                            double posX = this.mob.blockPosition().getX();
                            double posY = this.mob.blockPosition().getY();
                            double posZ = this.mob.blockPosition().getZ();
                            BlockPos blockPos = new BlockPos(posX - x, posY - y, posZ - z);
                            if (this.mob.level.getBlockState(blockPos).is(Blocks.SOUL_SAND) && this.mob.level.getFluidState(blockPos.above()).is(Fluids.LAVA) && !this.mob.level.getFluidState(blockPos.below()).is(Fluids.LAVA) && this.mob.level.getBlockState(blockPos.north()).is(Blocks.SOUL_SAND) && this.mob.level.getBlockState(blockPos.east()).is(Blocks.SOUL_SAND) && this.mob.level.getBlockState(blockPos.south()).is(Blocks.SOUL_SAND) && this.mob.level.getBlockState(blockPos.west()).is(Blocks.SOUL_SAND)) {
                                //LOGGER.info("I am getting set at " + this.mob.level.getBlockState(blockpos1));
                                //LOGGER.info("I am getting set at pos " + blockpos1);
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
            suckCounter = 0;
            counter =0;
            i = 0;
        }

        //TODO very bad change to better code plz
        @Override
        public void tick() {

            super.tick();

            lastPos = this.mob.blockPosition();

            if(soulSandList.size() == 0 || (i >= soulSandList.size())){
                this.stop();
                this.mob.fishSwimGoal.trigger();
                return;
            }

            if(!this.mob.level.getBlockState(soulSandList.get(i)).is(Blocks.SOUL_SAND) && counter < soulSandList.size()){
                i++;
                counter++;
            }
            if(soulSandList.size() == 0 || (i >= soulSandList.size())){
                this.stop();
                this.mob.fishSwimGoal.trigger();
                return;
            }
            if (((this.mob.level.getBlockState(this.mob.blockPosition().below()).is(Blocks.SOUL_SAND)) || (this.mob.level.getBlockState(this.mob.blockPosition().below()).is(Blocks.SOUL_SOIL)))&& checkDistance(this.mob.blockPosition(), soulSandList.get(i))) {
                suckCounter++;
                this.mob.getNavigation().moveTo(soulSandList.get(i).getX(), soulSandList.get(i).getY() + 0.5, soulSandList.get(i).getZ(), 1.0F);
                if (suckCounter == 100) {
                    if(!this.mob.level.getBlockState(this.mob.blockPosition()).is(Blocks.SOUL_SAND)) {
                        this.mob.level.setBlock(this.mob.blockPosition().below(), Blocks.SOUL_SOIL.defaultBlockState(), 3);
                    }else {
                        this.mob.level.setBlock(this.mob.blockPosition(), Blocks.SOUL_SOIL.defaultBlockState(), 3);
                    }
                    this.mob.invulnerableTime = 30;
                    i++;
                    counter++;
                }
            } else {
                this.mob.getNavigation().moveTo(soulSandList.get(i).getX(), soulSandList.get(i).getY() + 1, soulSandList.get(i).getZ(), 1.0F);
            }

            if(suckCounter >= 100) {
                if (counter < soulSandList.size()) {
                    this.mob.getNavigation().moveTo(soulSandList.get(i).getX(), soulSandList.get(i).getY() + 0.5, soulSandList.get(i).getZ(), 1.0F);

                } else {
                    BlockPos blockPos = new BlockPos(this.mob.blockPosition().getX() + rand.nextInt(-5, 5), this.mob.blockPosition().getY() + +rand.nextInt(0, 3), this.mob.blockPosition().getZ() + +rand.nextInt(-5, 5));
                    if (this.mob.level.getFluidState(blockPos).is(Fluids.LAVA)) {
                        this.mob.getNavigation().moveTo(this.mob.blockPosition().getX() + rand.nextInt(-5, 5), this.mob.blockPosition().getY() + +rand.nextInt(0, 3), this.mob.blockPosition().getZ() + +rand.nextInt(-5, 5), 1.0F);
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
        LOGGER.info("dis " + disTot);
        return (disTot <= 3.0);
    }
}
