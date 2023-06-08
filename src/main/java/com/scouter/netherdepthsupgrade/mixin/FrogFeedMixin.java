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

@Mixin(Frog.class)
public abstract class FrogFeedMixin extends Animal {

    protected FrogFeedMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        Frog frog = (Frog)(Object)this;
        ItemStack itemStack_ochre = new ItemStack(Items.OCHRE_FROGLIGHT);
        ItemStack itemStack_pearlescent = new ItemStack(Items.PEARLESCENT_FROGLIGHT);
        ItemStack itemStack_verdant = new ItemStack(Items.VERDANT_FROGLIGHT);
        ItemEntity itemEntity_ochre = new ItemEntity(level(), frog.getX(), frog.getY(), frog.getZ(), itemStack_ochre);
        ItemEntity itemEntity_pearlescent = new ItemEntity(level(), frog.getX(), frog.getY(), frog.getZ(), itemStack_pearlescent);
        ItemEntity itemEntity_verdant = new ItemEntity(level(), frog.getX(), frog.getY(), frog.getZ(), itemStack_verdant);
        ItemStack itemInHand = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);

        if(itemInHand.getItem() == NDUItems.MAGMACUBEFISH){
            if(frog.getVariant() == FrogVariant.COLD){
                level().addFreshEntity(itemEntity_verdant);
            }
            if(frog.getVariant() == FrogVariant.TEMPERATE){
                level().addFreshEntity(itemEntity_ochre);
            }
            if(frog.getVariant() == FrogVariant.WARM){
                level().addFreshEntity (itemEntity_pearlescent);
            }
            if(!pPlayer.isCreative()) {
                level().playSound(null, frog.blockPosition(), SoundEvents.FROG_EAT, SoundSource.NEUTRAL, 1, 1);
                itemInHand.setCount(itemInHand.getCount() - 1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(pPlayer, pHand);
    }
}
