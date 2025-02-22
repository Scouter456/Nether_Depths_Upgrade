package com.scouter.netherdepthsupgrade.items;

import com.mojang.serialization.MapCodec;
import com.scouter.netherdepthsupgrade.entity.BucketableLava;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class FishBucketItem extends MobBucketItem {
    private static final MapCodec<TropicalFish.Variant> VARIANT_FIELD_CODEC = TropicalFish.Variant.CODEC.fieldOf("BucketVariantTag");;
    private final EntityType<?> type;
    private final SoundEvent emptySound;
    public FishBucketItem(EntityType<? extends Mob> fishTypeIn, Fluid fluid, Properties builder) {
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

    private void spawn(ServerLevel serverLevel, ItemStack bucketedMobStack, BlockPos pos) {
        Entity entity = this.type.spawn(serverLevel, bucketedMobStack, (Player)null, pos, EntitySpawnReason.BUCKET, true, false);
        if (entity instanceof BucketableLava bucketable) {
            CustomData customData = (CustomData)bucketedMobStack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
            bucketable.loadFromBucketTag(customData.copyTag());
            bucketable.setFromBucket(true);
        }

    }

    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        if (this.type == EntityType.TROPICAL_FISH) {
            CustomData customData = (CustomData)itemStack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
            if (customData.isEmpty()) {
                return;
            }

            Optional<TropicalFish.Variant> optional = customData.read(VARIANT_FIELD_CODEC).result();
            if (optional.isPresent()) {
                TropicalFish.Variant variant = (TropicalFish.Variant)optional.get();
                ChatFormatting[] chatFormattings = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};
                String string = "color.minecraft." + String.valueOf(variant.baseColor());
                String string2 = "color.minecraft." + String.valueOf(variant.patternColor());
                int i = TropicalFish.COMMON_VARIANTS.indexOf(variant);
                if (i != -1) {
                    list.add(Component.translatable(TropicalFish.getPredefinedName(i)).withStyle(chatFormattings));
                    return;
                }

                list.add(variant.pattern().displayName().plainCopy().withStyle(chatFormattings));
                MutableComponent mutableComponent = Component.translatable(string);
                if (!string.equals(string2)) {
                    mutableComponent.append(", ").append(Component.translatable(string2));
                }

                mutableComponent.withStyle(chatFormattings);
                list.add(mutableComponent);
            }
        }

    }

}
