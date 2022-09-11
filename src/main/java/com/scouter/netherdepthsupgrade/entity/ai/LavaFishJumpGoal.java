package com.scouter.netherdepthsupgrade.entity.ai;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.JumpGoal;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class LavaFishJumpGoal extends JumpGoal {
    private static final int[] STEPS_TO_CHECK = new int[]{0, 1, 4, 5, 6, 7,10,11};
    private final AbstractLavaFish lavaAnimal;
    private final int interval;
    private boolean breached;

    public LavaFishJumpGoal(AbstractLavaFish lavaAnimal, int pInterval) {
        this.lavaAnimal = lavaAnimal;
        this.interval = pInterval;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (this.lavaAnimal.getRandom().nextInt(this.interval) != 0) {
            return false;
        } else {
            Direction direction = this.lavaAnimal.getMotionDirection();
            int i = direction.getStepX();
            int j = direction.getStepZ();
            BlockPos blockpos = this.lavaAnimal.blockPosition();

            for(int k : STEPS_TO_CHECK) {
                if (!this.lavaIsClear(blockpos, i, j, k) || !this.surfaceIsClear(blockpos, i, j, k)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean lavaIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        BlockPos blockpos = pPos.offset(pDx * pScale, 0, pDz * pScale);
        return this.lavaAnimal.level.getFluidState(blockpos).is(FluidTags.LAVA) && !this.lavaAnimal.level.getBlockState(blockpos).getMaterial().blocksMotion();
    }

    private boolean surfaceIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        return this.lavaAnimal.level.getBlockState(pPos.offset(pDx * pScale, 1, pDz * pScale)).isAir() && this.lavaAnimal.level.getBlockState(pPos.offset(pDx * pScale, 2, pDz * pScale)).isAir();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        double d0 = this.lavaAnimal.getDeltaMovement().y;
        return (!(d0 * d0 < (double)0.03F) || this.lavaAnimal.xRot == 0.0F || !(Math.abs(this.lavaAnimal.xRot) < 10.0F) || !this.lavaAnimal.isInLava()) && !this.lavaAnimal.isOnGround();
    }

    public boolean isInterruptable() {
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        Direction direction = this.lavaAnimal.getMotionDirection();
        this.lavaAnimal.setDeltaMovement(this.lavaAnimal.getDeltaMovement().add((double)direction.getStepX() * 1.1D, 0.7D, (double)direction.getStepZ() * 1.1D));
        this.lavaAnimal.getNavigation().stop();
        this.lavaAnimal.setIsJumping(true);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.lavaAnimal.xRot = 0.0F;
        this.lavaAnimal.setIsJumping(false);
        this.lavaAnimal.fishSwimGoal.trigger();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        boolean flag = this.breached;
        if (!flag) {
            FluidState fluidstate = this.lavaAnimal.level.getFluidState(this.lavaAnimal.blockPosition());
            this.breached = fluidstate.is(FluidTags.LAVA);
        }

        if (this.breached && !flag) {
            this.lavaAnimal.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
        }

        Vector3d vec3 = this.lavaAnimal.getDeltaMovement();
        if (vec3.y * vec3.y < (double)0.03F && this.lavaAnimal.xRot != 0.0F) {
            this.lavaAnimal.xRot = MathHelper.rotlerp(this.lavaAnimal.xRot, 0.0F, 0.2F);
        } else if (vec3.length() > (double)1.0E-5F) {
            double d0 = Math.sqrt(Entity.getHorizontalDistanceSqr(vec3));
            double d1 = Math.signum(-vec3.y) * Math.acos(d0 / vec3.length()) * (double)(180F / (float)Math.PI);
            this.lavaAnimal.xRot = (float)d1;
        }

    }
}
