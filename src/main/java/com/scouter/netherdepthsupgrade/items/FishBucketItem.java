package com.scouter.netherdepthsupgrade.items;

import com.scouter.netherdepthsupgrade.entity.BucketableLava;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class FishBucketItem extends MobBucketItem {
    private final EntityType<?> type;
    private final SoundEvent emptySound;
    public FishBucketItem(EntityType<?> fishTypeIn, Fluid fluid, Properties builder) {
        super(fishTypeIn, fluid, SoundEvents.BUCKET_EMPTY_FISH, builder.stacksTo(1));
        this.type = fishTypeIn;
        this.emptySound = SoundEvents.BUCKET_EMPTY_FISH;
    }


    @Override
    public void checkExtraContent(@Nullable Player player, Level level, ItemStack containerStack, BlockPos pos) {
        if (level instanceof ServerLevel) {
            this.spawn((ServerLevel)level, containerStack, pos);
            level.gameEvent((Entity)player, GameEvent.ENTITY_PLACE, pos);
        }
    }

    @Override
    protected void playEmptySound(@Nullable Player player, LevelAccessor level, BlockPos pos) {
        level.playSound(player, pos, this.emptySound, SoundSource.NEUTRAL, 1.0f, 1.0f);
    }

    public final void spawn(ServerLevel serverLevel, ItemStack bucketedMobStack, BlockPos pos) {
        Object entity = this.type.spawn(serverLevel, bucketedMobStack, null, pos, MobSpawnType.BUCKET, true, false);
        if (entity instanceof BucketableLava) {
            BucketableLava bucketable = (BucketableLava)entity;
            bucketable.loadFromBucketTag(bucketedMobStack.getOrCreateTag());
            bucketable.setFromBucket(true);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        CompoundTag compoundTag;
        if (this.type == EntityType.TROPICAL_FISH && (compoundTag = stack.getTag()) != null && compoundTag.contains("BucketVariantTag", 3)) {
            int i = compoundTag.getInt("BucketVariantTag");
            ChatFormatting[] chatFormattings = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};
            String string = "color.minecraft." + TropicalFish.getBaseColor(i);
            String string2 = "color.minecraft." + TropicalFish.getPatternColor(i);
            for (int j = 0; j < TropicalFish.COMMON_VARIANTS.size(); ++j) {
                if (i != TropicalFish.COMMON_VARIANTS.get(j).getPackedId()) continue;
                tooltipComponents.add(Component.translatable(TropicalFish.getPredefinedName(j)).withStyle(chatFormattings));
                return;
            }
            tooltipComponents.add(TropicalFish.getPattern(i).displayName().plainCopy().withStyle(chatFormattings));
            MutableComponent mutableComponent = Component.translatable(string);
            if (!string.equals(string2)) {
                mutableComponent.append(", ").append(Component.translatable(string2));
            }
            mutableComponent.withStyle(chatFormattings);
            tooltipComponents.add(mutableComponent);
        }
    }

}
