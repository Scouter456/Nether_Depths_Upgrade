package com.scouter.netherdepthsupgrade.mixin;

import com.scouter.netherdepthsupgrade.items.NDUItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Frog.class)
public abstract class FrogFeedMixin extends Animal {

    protected FrogFeedMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);

        if (!heldStack.is(NDUItems.MAGMACUBEFISH)) {
            return super.mobInteract(player, hand);
        }

        Frog frog = (Frog) (Object) this;
        Level level = level();

        if (!level.isClientSide) {
            ItemStack froglight = ndu$getFroglightForVariant(frog);

            level.addFreshEntity(new ItemEntity(level, frog.getX(), frog.getY(), frog.getZ(), froglight));

            level.playSound(null, frog.blockPosition(), SoundEvents.FROG_EAT, SoundSource.NEUTRAL, 1.0F, 1.0F);

            if (!player.isCreative()) {
                heldStack.shrink(1);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Unique
    private static ItemStack ndu$getFroglightForVariant(Frog frog) {
        if (frog.getVariant() == FrogVariant.COLD) {
            return new ItemStack(Items.VERDANT_FROGLIGHT);
        }

        if (frog.getVariant() == FrogVariant.WARM) {
            return new ItemStack(Items.PEARLESCENT_FROGLIGHT);
        }

        return new ItemStack(Items.OCHRE_FROGLIGHT);
    }
}
