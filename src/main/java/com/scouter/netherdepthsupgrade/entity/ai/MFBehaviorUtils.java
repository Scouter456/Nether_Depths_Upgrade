package com.scouter.netherdepthsupgrade.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class MFBehaviorUtils {
    @Nullable
    public static Vector3d getRandomSwimmableLavaPos(CreatureEntity pathfinderMob, int p_147446_, int p_147447_) {
        Vector3d vec3 = RandomPositionGenerator.getPos(pathfinderMob, p_147446_, p_147447_);

        for(int i = 0; vec3 != null && !pathfinderMob.level.getFluidState(new BlockPos(vec3)).is(FluidTags.LAVA) && i++ < 10; vec3 = RandomPositionGenerator.getPos(pathfinderMob, p_147446_, p_147447_)) {
        }

        return vec3;
    }
}
