package com.scouter.netherdepthsupgrade.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class MFBehaviorUtils {
    @Nullable
    public static Vec3 getRandomSwimmableLavaPos(PathfinderMob pathfinderMob, int p_147446_, int p_147447_) {
        Vec3 vec3 = DefaultRandomPos.getPos(pathfinderMob, p_147446_, p_147447_);

        for(int i = 0; vec3 != null && !pathfinderMob.level().getFluidState(BlockPos.containing(vec3)).is(FluidTags.LAVA) && i++ < 10; vec3 = DefaultRandomPos.getPos(pathfinderMob, p_147446_, p_147447_)) {
        }

        return vec3;
    }
}
