package com.scouter.netherdepthsupgrade.entity.ai;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Random;

public class LavaMoveGoal extends RandomStrollGoal {
    private int range = 5;
    public LavaMoveGoal(AbstractLavaFish p_27505_) {
        super(p_27505_, 1.0D, 40);
        this.range = 10;
    }

    @Override
    public boolean canUse() {
        if (!this.mob.isInLava() || this.mob.isPassenger() || mob.getTarget() != null) {
            return false;
        } else {
            if (!this.forceTrigger) {
                if (this.mob.getRandom().nextInt(this.interval) >= 100) {

                    return false;
                }
            }

            Vec3 vector3d = this.getPosition();
            if (vector3d == null) {
                return false;
            } else {
                this.wantedX = vector3d.x;
                this.wantedY = vector3d.y;
                this.wantedZ = vector3d.z;

                this.forceTrigger = false;
                return true;
            }
        }
    }


    @Nullable
    protected Vec3 getPosition() {
        if(this.mob.isInLava()) {
            BlockPos blockpos = null;
            Random random = new Random();
            for (int i = 0; i < 15; i++) {
                BlockPos blockpos1 = this.mob.blockPosition().offset(random.nextInt(range) - range / 2, 3, random.nextInt(range) - range / 2);
                while ((this.mob.level.isEmptyBlock(blockpos1) || this.mob.level.getFluidState(blockpos1).is(FluidTags.LAVA)) && blockpos1.getY() > 1) {
                    blockpos1 = blockpos1.below();
                }
                if (isBottomOfLava(this.mob.level, blockpos1.above())) {
                    blockpos = blockpos1;
                }
            }

            return blockpos != null ? new Vec3(blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F) : null;
        }else{
            return super.getPosition();

        }
    }

    private boolean isBottomOfLava(LevelAccessor world, BlockPos pos){
        return world.getFluidState(pos).is(FluidTags.LAVA) && world.getFluidState(pos.below()).isEmpty() && world.getBlockState(pos.below()).canOcclude();
    }
}
