package com.scouter.netherdepthsupgrade.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public abstract class LavaAnimal extends CreatureEntity {
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    protected LavaAnimal(EntityType<? extends LavaAnimal> p_30341_, World p_30342_) {
        super(p_30341_, p_30342_);
        this.setPathfindingMalus(PathNodeType.LAVA, 0.0F);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public CreatureAttribute getMobType() {
        return NDUMobType.LAVA;
    }

    public boolean checkSpawnObstruction(IWorldReader pLevel) {
        return pLevel.isUnobstructed(this);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getAmbientSoundInterval() {
        return 120;
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperienceReward(PlayerEntity pPlayer) {
        return 1 + this.level.random.nextInt(3);
    }

    protected void handleAirSupply(int p_30344_) {
        if (this.isAlive() && !this.isInLava()) {
            this.setAirSupply(p_30344_ - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(DamageSource.DROWN, 2.0F);
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

    public boolean canBeLeashed(PlayerEntity pPlayer) {
        return false;
    }

    public static boolean checkSurfaceLavaAnimalSpawnRules(EntityType<? extends LavaAnimal> fish, IWorld pLevel, SpawnReason pSpawnType, BlockPos pPos, Random pRandom) {
        LOGGER.info("trying to spawn " + fish);
 //       LOGGER.info("pos " + pPos);
        int i = 40;
        int j = i - 30;
 //      LOGGER.info("can spawn? " + (pPos.getY() >= j && pPos.getY() <= i && (pSpawnType == SpawnReason.SPAWNER || pLevel.getBlockState(pPos.below()).is(Blocks.LAVA) && pLevel.getBlockState(pPos.above()).is(Blocks.LAVA))));
 //      LOGGER.info("why not? y>? " + (pPos.getY() >= j));
 //      LOGGER.info("why not? y<? " + (pPos.getY() <= i));
 //      LOGGER.info("Fluid above?  " + pLevel.getBlockState(pPos.above()).is(Blocks.LAVA));
 //      LOGGER.info("Fluid below?  " + pLevel.getBlockState(pPos.below()).is(Blocks.LAVA));
        if(fish == NDUEntity.BLAZEFISH.get()){
            return (pLevel.getFluidState(pPos).is(FluidTags.LAVA));
        }
        return pPos.getY() >= j && pPos.getY() <= i && (pSpawnType == SpawnReason.SPAWNER || pLevel.getFluidState(pPos.below()).is(FluidTags.LAVA) && pLevel.getBlockState(pPos.above()).is(Blocks.LAVA));
    }
}