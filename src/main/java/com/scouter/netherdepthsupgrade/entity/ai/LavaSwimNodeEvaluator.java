package com.scouter.netherdepthsupgrade.entity.ai;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.MobEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.pathfinding.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.Map;

public class LavaSwimNodeEvaluator extends NodeProcessor {
    private final boolean allowBreaching;
    private final Long2ObjectMap<PathNodeType> pathTypesByPosCache = new Long2ObjectOpenHashMap<>();

    public LavaSwimNodeEvaluator(boolean p_77457_) {
        this.allowBreaching = p_77457_;
    }

    /**
     * This method is called when all nodes have been processed and PathEntity is created.
     * {@link net.minecraft.world.pathfinder.WalkNodeProcessor WalkNodeProcessor} uses this to change its field {@link
     * net.minecraft.world.pathfinder.WalkNodeProcessor#avoidsWater avoidsWater}
     */
    public void done() {
        super.done();
        this.pathTypesByPosCache.clear();
    }

    public PathPoint getStart() {
        return super.getNode(MathHelper.floor(this.mob.getBoundingBox().minX), MathHelper.floor(this.mob.getBoundingBox().minY + 0.5D), MathHelper.floor(this.mob.getBoundingBox().minZ));
    }

    public FlaggedPathPoint getGoal(double p_224768_1_, double p_224768_3_, double p_224768_5_) {
        return new FlaggedPathPoint(super.getNode(MathHelper.floor(p_224768_1_ - (double)(this.mob.getBbWidth() / 2.0F)), MathHelper.floor(p_224768_3_ + 0.5D), MathHelper.floor(p_224768_5_ - (double)(this.mob.getBbWidth() / 2.0F))));
    }

    public int getNeighbors(PathPoint[] p_77483_, PathPoint p_77484_) {
        int i = 0;
        Map<Direction, PathPoint> map = Maps.newEnumMap(Direction.class);

        for(Direction direction : Direction.values()) {
            PathPoint node = this.getNode(p_77484_.x + direction.getStepX(), p_77484_.y + direction.getStepY(), p_77484_.z + direction.getStepZ());
            map.put(direction, node);
            if (this.isNodeValid(node)) {
                p_77483_[i++] = node;
            }
        }

        for(Direction direction1 : Direction.Plane.HORIZONTAL) {
            Direction direction2 = direction1.getClockWise();
            PathPoint node1 = this.getNode(p_77484_.x + direction1.getStepX() + direction2.getStepX(), p_77484_.y, p_77484_.z + direction1.getStepZ() + direction2.getStepZ());
            if (this.isDiagonalNodeValid(node1, map.get(direction1), map.get(direction2))) {
                p_77483_[i++] = node1;
            }
        }

        return i;
    }

    protected boolean isNodeValid(@Nullable PathPoint p_192962_) {
        return p_192962_ != null && !p_192962_.closed;
    }

    protected boolean isDiagonalNodeValid(@Nullable PathPoint p_192964_, @Nullable PathPoint p_192965_, @Nullable PathPoint p_192966_) {
        return this.isNodeValid(p_192964_) && p_192965_ != null && p_192965_.costMalus >= 0.0F && p_192966_ != null && p_192966_.costMalus >= 0.0F;
    }

    /**
     * Returns a mapped point or creates and adds one
     */
    @Nullable
    protected PathPoint getNode(int pX, int pY, int pZ) {
        PathPoint node = null;
        PathNodeType blockpathtypes = this.getBlockPathType(this.mob.level, pX, pY, pZ);
        if (this.allowBreaching && blockpathtypes == PathNodeType.BREACH || blockpathtypes == PathNodeType.WATER ||  blockpathtypes == PathNodeType.LAVA) {
            float f = this.mob.getPathfindingMalus(blockpathtypes);
            if (f >= 0.0F) {
                node = super.getNode(pX, pY, pZ);
                node.type = blockpathtypes;
                node.costMalus = Math.max(node.costMalus, f);
                if (this.level.getFluidState(new BlockPos(pX, pY, pZ)).isEmpty()) {
                    node.costMalus += 8.0F;
                }
            }
        }

        return node;
    }

    /**
     * Returns the node type at the specified postion taking the block below into account
     */
    public PathNodeType getBlockPathType(IBlockReader pLevel, int pX, int pY, int pZ, MobEntity pEntityliving,int pXSize, int pYSize, int pZSize, boolean pCanBreakDoors, boolean pCanEnterDoors) {
        return this.getBlockPathType(pLevel, pX, pY, pZ);
    }

    /**
     * Returns the significant (e.g LAVA if the entity were half in lava) node type at the location taking the
     * surroundings and the entity size in account
     */
    public PathNodeType getBlockPathType(IBlockReader pLevel, int pX, int pY, int pZ) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int i = pX; i < pX + this.entityWidth; ++i) {
            for(int j = pY; j < pY + this.entityHeight; ++j) {
                for(int k = pZ; k < pZ + this.entityDepth; ++k) {
                    FluidState fluidstate = this.level.getFluidState(blockpos$mutable.set(i, j, k));
                    BlockState blockstate = this.level.getBlockState(blockpos$mutable.set(i, j, k));
                    if (fluidstate.isEmpty() && blockstate.isPathfindable(pLevel, blockpos$mutable.below(), PathType.WATER) && blockstate.isAir()) {
                        return PathNodeType.BREACH;
                    }
                    else {
                        return fluidstate.is(FluidTags.LAVA) ? PathNodeType.WATER : PathNodeType.BLOCKED;
                    }
                }
            }
        }

        BlockState blockstate1 = pLevel.getBlockState(blockpos$mutable);
        FluidState fluidState1 = pLevel.getFluidState(blockpos$mutable);
        return fluidState1.is(FluidTags.LAVA) ? PathNodeType.WATER : PathNodeType.BLOCKED;
    }
}
