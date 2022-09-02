package com.scouter.netherdepthsupgrade.entity.ai;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.*;

import javax.annotation.Nullable;
import java.util.Map;

public class LavaSwimNodeEvaluator extends NodeEvaluator {
    private final boolean allowBreaching;
    private final Long2ObjectMap<BlockPathTypes> pathTypesByPosCache = new Long2ObjectOpenHashMap<>();

    public LavaSwimNodeEvaluator(boolean p_77457_) {
        this.allowBreaching = p_77457_;
    }

    public void prepare(PathNavigationRegion p_192959_, Mob p_192960_) {
        super.prepare(p_192959_, p_192960_);
        this.pathTypesByPosCache.clear();
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

    public Node getStart() {
        return super.getNode(Mth.floor(this.mob.getBoundingBox().minX), Mth.floor(this.mob.getBoundingBox().minY + 0.5D), Mth.floor(this.mob.getBoundingBox().minZ));
    }

    public Target getGoal(double p_77459_, double p_77460_, double p_77461_) {
        return new Target(super.getNode(Mth.floor(p_77459_), Mth.floor(p_77460_), Mth.floor(p_77461_)));
    }

    public int getNeighbors(Node[] p_77483_, Node p_77484_) {
        int i = 0;
        Map<Direction, Node> map = Maps.newEnumMap(Direction.class);

        for(Direction direction : Direction.values()) {
            Node node = this.getNode(p_77484_.x + direction.getStepX(), p_77484_.y + direction.getStepY(), p_77484_.z + direction.getStepZ());
            map.put(direction, node);
            if (this.isNodeValid(node)) {
                p_77483_[i++] = node;
            }
        }

        for(Direction direction1 : Direction.Plane.HORIZONTAL) {
            Direction direction2 = direction1.getClockWise();
            Node node1 = this.getNode(p_77484_.x + direction1.getStepX() + direction2.getStepX(), p_77484_.y, p_77484_.z + direction1.getStepZ() + direction2.getStepZ());
            if (this.isDiagonalNodeValid(node1, map.get(direction1), map.get(direction2))) {
                p_77483_[i++] = node1;
            }
        }

        return i;
    }

    protected boolean isNodeValid(@Nullable Node p_192962_) {
        return p_192962_ != null && !p_192962_.closed;
    }

    protected boolean isDiagonalNodeValid(@Nullable Node p_192964_, @Nullable Node p_192965_, @Nullable Node p_192966_) {
        return this.isNodeValid(p_192964_) && p_192965_ != null && p_192965_.costMalus >= 0.0F && p_192966_ != null && p_192966_.costMalus >= 0.0F;
    }

    /**
     * Returns a mapped point or creates and adds one
     */
    @Nullable
    protected Node getNode(int pX, int pY, int pZ) {
        Node node = null;
        BlockPathTypes blockpathtypes = this.getCachedBlockType(pX, pY, pZ);
        if (this.allowBreaching && blockpathtypes == BlockPathTypes.BREACH || blockpathtypes == BlockPathTypes.WATER ||  blockpathtypes == BlockPathTypes.LAVA) {
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

    protected BlockPathTypes getCachedBlockType(int p_192968_, int p_192969_, int p_192970_) {
        return this.pathTypesByPosCache.computeIfAbsent(BlockPos.asLong(p_192968_, p_192969_, p_192970_), (p_192957_) -> {
            return this.getBlockPathType(this.level, p_192968_, p_192969_, p_192970_);
        });
    }

    /**
     * Returns the node type at the specified postion taking the block below into account
     */
    public BlockPathTypes getBlockPathType(BlockGetter pLevel, int pX, int pY, int pZ) {
        return this.getBlockPathType(pLevel, pX, pY, pZ, this.mob, this.entityWidth, this.entityHeight, this.entityDepth, this.canOpenDoors(), this.canPassDoors());
    }

    /**
     * Returns the significant (e.g LAVA if the entity were half in lava) node type at the location taking the
     * surroundings and the entity size in account
     */
    public BlockPathTypes getBlockPathType(BlockGetter pBlockaccess, int pX, int pY, int pZ, Mob pEntityliving, int pXSize, int pYSize, int pZSize, boolean pCanBreakDoors, boolean pCanEnterDoors) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int i = pX; i < pX + pXSize; ++i) {
            for(int j = pY; j < pY + pYSize; ++j) {
                for(int k = pZ; k < pZ + pZSize; ++k) {
                    FluidState fluidstate = pBlockaccess.getFluidState(blockpos$mutableblockpos.set(i, j, k));
                    BlockState blockstate = pBlockaccess.getBlockState(blockpos$mutableblockpos.set(i, j, k));
                    if (fluidstate.isEmpty() && blockstate.isPathfindable(pBlockaccess, blockpos$mutableblockpos.below(), PathComputationType.WATER) && blockstate.isAir()) {
                        return BlockPathTypes.BREACH;
                    }
                    else {
                        return fluidstate.is(FluidTags.LAVA) ? BlockPathTypes.WATER : BlockPathTypes.BLOCKED;
                    }
                }
            }
        }

        BlockState blockstate1 = pBlockaccess.getBlockState(blockpos$mutableblockpos);
        FluidState fluidState1 = pBlockaccess.getFluidState(blockpos$mutableblockpos);
        return fluidState1.is(FluidTags.LAVA) ? BlockPathTypes.WATER : BlockPathTypes.BLOCKED;
    }
}
