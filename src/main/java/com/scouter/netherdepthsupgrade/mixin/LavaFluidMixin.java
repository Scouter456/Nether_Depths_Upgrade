package com.scouter.netherdepthsupgrade.mixin;


import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LiquidBlockRenderer.class)
public class LavaFluidMixin {
    private static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");

    @Inject(method = "shouldRenderFace(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/FluidState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/material/FluidState;)Z", at = @At("RETURN"))
    private static boolean netherdepthsupgrade$renderFace(BlockAndTintGetter level, BlockPos pos, FluidState fluidState, BlockState blockState, Direction side, FluidState neighborFluid, CallbackInfoReturnable ci) {
        return !LiquidBlockRenderer.isFaceOccludedBySelf(level, pos, blockState, side) && !LiquidBlockRenderer.isNeighborSameFluid(fluidState, neighborFluid) && !level.getBlockState(pos.relative(side)).is(NDUBlocks.LAVA_GLASS) && level.getBlockState(pos).getFluidState().is(FluidTags.LAVA) ||
                !LiquidBlockRenderer.isFaceOccludedBySelf(level, pos, blockState, side) && !LiquidBlockRenderer.isNeighborSameFluid(fluidState, neighborFluid) && !level.getBlockState(pos).getFluidState().is(FluidTags.LAVA);
    }

 //  @Inject(method = "isFaceOccludedByNeighbor(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;FLnet/minecraft/world/level/block/state/BlockState;)Z", at = @At("RETURN"))
 //  private static boolean occludedByNeighbor(BlockGetter level, BlockPos pos, Direction side, float height, BlockState blockState, CallbackInfoReturnable ci) {
 //      return LiquidBlockRenderer.isFaceOccludedByState(level, side, height, pos.relative(side), blockState) || level.getBlockState(pos.relative(side)).is(NDUBlocks.LAVA_GLASS) && level.getBlockState(pos).getFluidState().is(FluidTags.LAVA);
 //  }

}
