package com.scouter.netherdepthsupgrade.entity.ai;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

import javax.annotation.Nullable;

public class RandomLavaSwimmingGoal extends RandomStrollGoal {
    private static final Logger LOGGER = LogUtils.getLogger();
    public RandomLavaSwimmingGoal(PathfinderMob p_25753_, double p_25754_, int p_25755_) {
        super(p_25753_, p_25754_, p_25755_);
    }

    @Nullable
    protected Vec3 getPosition() {
        return MFBehaviorUtils.getRandomSwimmableLavaPos(this.mob, 10, 7);
    }
}