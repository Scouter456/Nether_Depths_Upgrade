package com.scouter.netherdepthsupgrade.entity.ai;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class FishSwimGoal extends RandomWalkingGoal {
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public FishSwimGoal(AbstractLavaFish p_27505_) {
        super(p_27505_, 1.0D, 40);
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
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

            Vector3d vector3d = this.getPosition();
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
    @Override
    protected Vector3d getPosition() {
        Vector3d vector3d = RandomPositionGenerator.getPos(this.mob, 10, 7);
        for (int i = 0; vector3d != null && !this.mob.level.getFluidState(new BlockPos(vector3d)).is(FluidTags.LAVA) && i++ < 10; vector3d = RandomPositionGenerator.getPos(this.mob, 10, 7)) {
        }
        return vector3d;

    }
}