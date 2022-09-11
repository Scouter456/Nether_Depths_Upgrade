package com.scouter.netherdepthsupgrade.entity.entities;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import com.scouter.netherdepthsupgrade.entity.ai.FishSwimGoal;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class SoulSuckerEntity extends AbstractLavaFish implements IAnimatable, IAnimationTickable {
    private static final DataParameter<BlockPos> SOULSAND_POS = EntityDataManager.defineId(SoulSuckerEntity.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<Integer> SEEK_SOULSAND_TIMER = EntityDataManager.defineId(SoulSuckerEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> COOLDOWN_TTIMER = EntityDataManager.defineId(SoulSuckerEntity.class, DataSerializers.INT);
    private AnimationFactory factory = new AnimationFactory(this);
    public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MODID);

    public int suckTimer = 0;
    @Nullable
    protected FishSwimGoal fishSwimGoal;

    public SoulSuckerEntity(EntityType<? extends AbstractLavaFish> p_27523_, World p_27524_) {
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
            //LOGGER.info("timer? " + this.getSeekSoulSandTimer());

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

    public void addAdditionalSaveData(CompoundNBT pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("SoulSandPosX", this.getSoulSandPos().getX());
        pCompound.putInt("SoulSandPosY", this.getSoulSandPos().getY());
        pCompound.putInt("SoulSandPosZ", this.getSoulSandPos().getZ());
        pCompound.putInt("seeksoulsandtimer", this.getSeekSoulSandTimer());
        pCompound.putInt("cooldowntimer", this.getCooldownTimer());
    }

    public void readAdditionalSaveData(CompoundNBT pCompound) {
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
        return blockpos != null ? blockpos.closerThan(this.position(), 0.0D) : false;
    }



    static class FindSoulSandGoal3 extends Goal {
        public static final Logger LOGGER = LogManager.getLogger(MODID);

        private final SoulSuckerEntity mob;
        private int i = 0;
        private int counter = 0;
        private int suckCounter = 30;
        private BlockPos lastPos;
        private Random rand = new Random();
        public List<Integer> list = new ArrayList<>();
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
            //LOGGER.info("canContinue? " + this.mob.getSeekSoulSandTimer().intValue());
            return this.mob.getSeekSoulSandTimer().intValue() == 0;
        }


        @Override
        public void start() {
            //LOGGER.info("starting");
            for(int l = -5; l < 6;l++){
                list.add(l);
            }
            //LOGGER.info("starting!!");
            if (this.mob.level instanceof ServerWorld) {
                ServerWorld serverlevel = (ServerWorld) this.mob.level;
                this.mob.getNavigation().stop();
                BlockPos blockpos = this.mob.blockPosition();
                for (int x = -5; x < 5; x++) {
                    for (int y = 0; y < 10; y++) {
                        for (int z = -5; z < 5; z++) {
                            double posX = this.mob.blockPosition().getX();
                            double posY = this.mob.blockPosition().getY();
                            double posZ = this.mob.blockPosition().getZ();
                            BlockPos blockPos = new BlockPos(posX - x, posY - y, posZ - z);
                            //LOGGER.info("I am getting set at " + this.mob.level.getBlockState(blockPos));
                            if (this.mob.level.getBlockState(blockPos).is(Blocks.SOUL_SAND) && this.mob.level.getFluidState(blockPos.above()).is(FluidTags.LAVA) && !(this.mob.level.getFluidState(blockPos.below()).is(FluidTags.LAVA)) && this.mob.level.getBlockState(blockPos.north()).is(Blocks.SOUL_SAND) && this.mob.level.getBlockState(blockPos.east()).is(Blocks.SOUL_SAND) && this.mob.level.getBlockState(blockPos.south()).is(Blocks.SOUL_SAND) && this.mob.level.getBlockState(blockPos.west()).is(Blocks.SOUL_SAND)) {

                                //LOGGER.info("I am getting set at pos " + blockpos1);
                                soulSandList.add(blockPos);

                            }
                        }
                    }
                }
                //LOGGER.info("list " + soulSandList);
                if (soulSandList.size() > 0) {

                    this.mob.getNavigation().moveTo(soulSandList.get(i).getX(), soulSandList.get(i).getY() + 0.5, soulSandList.get(i).getZ(), 1.0F);
                } else {
                    //LOGGER.info("Going here ");
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
            list.clear();
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

            //LOGGER.info("?? " + (this.mob.level.getBlockState(this.mob.blockPosition().below())));
            if (((this.mob.level.getBlockState(this.mob.blockPosition().below()).is(Blocks.SOUL_SAND)) || (this.mob.level.getBlockState(this.mob.blockPosition().below()).is(Blocks.SOUL_SOIL)))&& checkDistance(this.mob.blockPosition(), soulSandList.get(i))) {
                suckCounter++;
                //LOGGER.info("counter " + suckCounter);
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
                    BlockPos blockPos = new BlockPos(this.mob.blockPosition().getX() + list.get(rand.nextInt(10)), this.mob.blockPosition().getY() + rand.nextInt( 3), this.mob.blockPosition().getZ() + list.get(rand.nextInt(10)));
                    if (this.mob.level.getFluidState(blockPos).equals(Fluids.LAVA)) {
                        this.mob.getNavigation().moveTo(this.mob.blockPosition().getX() + list.get(rand.nextInt(10)), this.mob.blockPosition().getY() + rand.nextInt(3), this.mob.blockPosition().getZ() + list.get(rand.nextInt(10)), 1.0F);
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
