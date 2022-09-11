package com.scouter.netherdepthsupgrade.entity.ai;

import com.scouter.netherdepthsupgrade.entity.AbstractLavaFish;
import net.minecraft.entity.MobEntity;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;

public class LavaBoundPathNavigation extends PathNavigator {
    private boolean allowBreaching;

    public LavaBoundPathNavigation(MobEntity p_26594_, World p_26595_) {
        super(p_26594_, p_26595_);
    }

    protected PathFinder createPathFinder(int p_26598_) {
        this.allowBreaching = this.mob instanceof AbstractLavaFish;
        this.nodeEvaluator = new LavaSwimNodeEvaluator(this.allowBreaching);
        return new PathFinder(this.nodeEvaluator, p_26598_);
    }

    /**
     * If on ground or swimming and can swim
     */
    protected boolean canUpdatePath() {
        return this.allowBreaching || this.isInLiquid();
    }

    protected Vector3d getTempMobPos() {
        return new Vector3d(this.mob.getX(), this.mob.getY(0.5D), this.mob.getZ());
    }


    public void tick() {
        ++this.tick;
        if (this.hasDelayedRecomputation) {
            this.recomputePath();
        }

        if (!this.isDone()) {
            if (this.canUpdatePath()) {
                this.followThePath();
            } else if (this.path != null && !this.path.isDone()) {
                Vector3d vector3d = this.path.getNextEntityPos(this.mob);
                if (MathHelper.floor(this.mob.getX()) == MathHelper.floor(vector3d.x) && MathHelper.floor(this.mob.getY()) == MathHelper.floor(vector3d.y) && MathHelper.floor(this.mob.getZ()) == MathHelper.floor(vector3d.z)) {
                    this.path.advance();
                }
            }

            DebugPacketSender.sendPathFindingPacket(this.level, this.mob, this.path, this.maxDistanceToWaypoint);
            if (!this.isDone()) {
                Vector3d vector3d1 = this.path.getNextEntityPos(this.mob);
                this.mob.getMoveControl().setWantedPosition(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
            }
        }
    }

    protected void followThePath() {
        if (this.path != null) {
            Vector3d vector3d = this.getTempMobPos();
            float f = this.mob.getBbWidth();
            float f1 = f > 0.75F ? f / 2.0F : 0.75F - f / 2.0F;
            Vector3d vector3d1 = this.mob.getDeltaMovement();
            if (Math.abs(vector3d1.x) > 0.2D || Math.abs(vector3d1.z) > 0.2D) {
                f1 = (float)((double)f1 * vector3d1.length() * 6.0D);
            }

            int i = 6;
            Vector3d vector3d2 = Vector3d.atBottomCenterOf(this.path.getNextNodePos());
            if (Math.abs(this.mob.getX() - vector3d2.x) < (double)f1 && Math.abs(this.mob.getZ() - vector3d2.z) < (double)f1 && Math.abs(this.mob.getY() - vector3d2.y) < (double)(f1 * 2.0F)) {
                this.path.advance();
            }

            for(int j = Math.min(this.path.getNextNodeIndex() + 6, this.path.getNodeCount() - 1); j > this.path.getNextNodeIndex(); --j) {
                vector3d2 = this.path.getEntityPosAtNode(this.mob, j);
                if (!(vector3d2.distanceToSqr(vector3d) > 36.0D) && this.canMoveDirectly(vector3d, vector3d2, 0, 0, 0)) {
                    this.path.setNextNodeIndex(j);
                    break;
                }
            }

            this.doStuckDetection(vector3d);
        }
    }

    protected void doStuckDetection(Vector3d pPositionVec3) {
        if (this.tick - this.lastStuckCheck > 100) {
            if (pPositionVec3.distanceToSqr(this.lastStuckCheckPos) < 2.25D) {
                this.stop();
            }

            this.lastStuckCheck = this.tick;
            this.lastStuckCheckPos = pPositionVec3;
        }

        if (this.path != null && !this.path.isDone()) {
            Vector3i vector3i = this.path.getNextNodePos();
            if (vector3i.equals(this.timeoutCachedNode)) {
                this.timeoutTimer += Util.getMillis() - this.lastTimeoutCheck;
            } else {
                this.timeoutCachedNode = vector3i;
                double d0 = pPositionVec3.distanceTo(Vector3d.atCenterOf(this.timeoutCachedNode));
                this.timeoutLimit = this.mob.getSpeed() > 0.0F ? d0 / (double)this.mob.getSpeed() * 100.0D : 0.0D;
            }

            if (this.timeoutLimit > 0.0D && (double)this.timeoutTimer > this.timeoutLimit * 2.0D) {
                this.timeoutCachedNode = Vector3i.ZERO;
                this.timeoutTimer = 0L;
                this.timeoutLimit = 0.0D;
                this.stop();
            }

            this.lastTimeoutCheck = Util.getMillis();
        }

    }

    protected boolean canMoveDirectly(Vector3d pPosVec31, Vector3d pPosVec32, int pSizeX, int pSizeY, int pSizeZ) {
        Vector3d vec3 = new Vector3d(pPosVec32.x, pPosVec32.y + (double)this.mob.getBbHeight() * 0.5D, pPosVec32.z);
        return this.level.clip(new RayTraceContext(pPosVec31, vec3, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this.mob)).getType() == RayTraceResult.Type.MISS;
    }

    public boolean isStableDestination(BlockPos pPos) {
        return !this.level.getBlockState(pPos).isSolidRender(this.level, pPos);
    }

    public void setCanFloat(boolean pCanSwim) {
    }

}
