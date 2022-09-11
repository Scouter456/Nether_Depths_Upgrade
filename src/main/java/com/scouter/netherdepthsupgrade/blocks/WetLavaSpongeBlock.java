package com.scouter.netherdepthsupgrade.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;

public class WetLavaSpongeBlock extends Block {
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public WetLavaSpongeBlock(Properties p_58222_) {
        super(p_58222_);
    }

    public void onPlace(BlockState pState, World pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (pLevel.getBlockState(pPos.above()).getFluidState().is(FluidTags.WATER)) {
            pLevel.setBlock(pPos, NDUBlocks.LAVA_SPONGE.get().defaultBlockState(), 3);
            pLevel.levelEvent(2009, pPos, 0);
            pLevel.playSound((PlayerEntity) null, pPos, SoundEvents.LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, (1.0F + pLevel.getRandom().nextFloat() * 0.2F) * 0.7F);
        }

    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    public void animateTick(BlockState pState, World pLevel, BlockPos pPos, Random pRand) {
        Direction direction = Direction.getRandom(pRand);
        if (direction != Direction.UP) {
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            if (!pState.canOcclude() || !blockstate.isFaceSturdy(pLevel, blockpos, direction.getOpposite())) {
                double d0 = (double)pPos.getX();
                double d1 = (double)pPos.getY();
                double d2 = (double)pPos.getZ();
                if (direction == Direction.DOWN) {
                    d1 -= 0.05D;
                    d0 += pRand.nextDouble();
                    d2 += pRand.nextDouble();
                } else {
                    d1 += pRand.nextDouble() * 0.8D;
                    if (direction.getAxis() == Direction.Axis.X) {
                        d2 += pRand.nextDouble();
                        if (direction == Direction.EAST) {
                            ++d0;
                        } else {
                            d0 += 0.05D;
                        }
                    } else {
                        d0 += pRand.nextDouble();
                        if (direction == Direction.SOUTH) {
                            ++d2;
                        } else {
                            d2 += 0.05D;
                        }
                    }
                }

                pLevel.addParticle(ParticleTypes.DRIPPING_LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
