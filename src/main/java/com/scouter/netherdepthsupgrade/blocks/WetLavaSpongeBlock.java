package com.scouter.netherdepthsupgrade.blocks;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.advancements.NDUAdvancementTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class WetLavaSpongeBlock extends Block {
    private static final Logger LOGGER = LogUtils.getLogger();

    public WetLavaSpongeBlock(Properties p_58222_) {
        super(p_58222_);
    }

    //@Nullable
    //@Override
    //public BlockState getStateForPlacement(BlockPlaceContext context) {
    //    Player player = context.getPlayer();
    //    BlockPos blockPos = context.getClickedPos();
    //    Level level = context.getLevel();
    //    ItemStack itemStack = context.getItemInHand();
    //    BlockState blockState2 = level.getBlockState(blockPos.above());
    //    if (blockState2.getFluidState().is(FluidTags.WATER)) {
    //
    //        if (player instanceof ServerPlayer serverPlayer) {
    //
    //            NDUAdvancementTriggers.PLACE_WET_LAVA_SPONGE.trigger(serverPlayer, blockPos, itemStack);
    //
    //        }
    //
    //    }
//
    //
    //    return super.getStateForPlacement(context);
    //}
//
    //@Override
    //public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
    //    NetherDepthsUpgrade.LOGGER.info("entity " + pPlacer);
    //    NetherDepthsUpgrade.LOGGER.info("level " + pLevel);
    //    if (pLevel.getBlockState(pPos.above()).getFluidState().is(FluidTags.WATER)) {
    //        if(pPlacer instanceof ServerPlayer player){
    //            NDUAdvancementTriggers.PLACE_WET_LAVA_SPONGE.trigger((ServerPlayer) player, pPos, pStack);
    //        }
    //    }
    //}

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (pLevel.getBlockState(pPos.above()).getFluidState().is(FluidTags.WATER)) {
            pLevel.setBlock(pPos, NDUBlocks.LAVA_SPONGE.defaultBlockState(), 3);
            pLevel.levelEvent(2009, pPos, 0);
            pLevel.playSound((Player) null, pPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0F, (1.0F + pLevel.getRandom().nextFloat() * 0.2F) * 0.7F);
        }

    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRand) {
        Direction direction = Direction.getRandom(pRand);
        if (direction != Direction.UP) {
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            if (!pState.canOcclude() || !blockstate.isFaceSturdy(pLevel, blockpos, direction.getOpposite())) {
                double d0 = (double) pPos.getX();
                double d1 = (double) pPos.getY();
                double d2 = (double) pPos.getZ();
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
