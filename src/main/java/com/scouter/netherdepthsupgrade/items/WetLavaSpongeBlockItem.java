package com.scouter.netherdepthsupgrade.items;

import com.scouter.netherdepthsupgrade.advancements.NDUAdvancementTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WetLavaSpongeBlockItem extends BlockItem {
    public WetLavaSpongeBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult interactionResult = this.place(new BlockPlaceContext(context));
        if (!interactionResult.consumesAction() && this.isEdible()) {
            InteractionResult interactionResult2 = this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
            return interactionResult2 == InteractionResult.CONSUME ? InteractionResult.CONSUME_PARTIAL : interactionResult2;
        }
        Player player = context.getPlayer();
        Direction dir = context.getClickedFace();
        BlockPos blockPos = context.getClickedPos().relative(dir);
        Level level = context.getLevel();
        ItemStack itemStack = context.getItemInHand();
        BlockState blockState2 = level.getBlockState(blockPos.above());
        if (blockState2.getFluidState().is(FluidTags.WATER)) {
            if (player instanceof ServerPlayer serverPlayer) {
                NDUAdvancementTriggers.PLACE_WET_LAVA_SPONGE.trigger(serverPlayer, blockPos, itemStack);
            }
        }



        return interactionResult;
    }
}
