package com.scouter.netherdepthsupgrade.entity;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathType;
import org.slf4j.Logger;

public abstract class LavaAnimal extends PathfinderMob {
    private static final Logger LOGGER = LogUtils.getLogger();
    protected LavaAnimal(EntityType<? extends LavaAnimal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
        this.setPathfindingMalus(PathType.LAVA, 0.0F);
    }



    public boolean checkSpawnObstruction(LevelReader pLevel) {
        return pLevel.isUnobstructed(this);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getAmbientSoundInterval() {
        return 120;
    }



    @Override
    protected int getBaseExperienceReward(ServerLevel serverLevel) {
        return 1 + serverLevel.random.nextInt(3);
    }

    protected void handleAirSupply(int p_30344_) {
        if (this.isAlive() && !this.isInLava()) {
            this.setAirSupply(p_30344_ - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(this.damageSources().drown(), 2.0F);
            }
        } else {
            this.setAirSupply(300);
        }

    }

    /**
     * Gets called every tick from main Entity class
     */
    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        this.handleAirSupply(i);
    }

    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean canBeLeashed() {

            return false;

    }


    public static boolean checkSurfaceLavaAnimalSpawnRules(EntityType<? extends LavaAnimal> entityType, LevelAccessor level, EntitySpawnReason entitySpawnReason,
             BlockPos pos, RandomSource randomSource) {
        int i = 40;
        int j = i - 30;
        return pos.getY() >= j && pos.getY() <= i && (entitySpawnReason == EntitySpawnReason.SPAWNER || level.getFluidState(pos.below()).is(FluidTags.LAVA) && level.getBlockState(pos.above()).is(Blocks.LAVA));
    }
}