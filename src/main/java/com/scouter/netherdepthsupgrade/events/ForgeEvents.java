package com.scouter.netherdepthsupgrade.events;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.LavaGlassBlock;
import com.scouter.netherdepthsupgrade.blocks.entity.NDUBlockEntities;
import com.scouter.netherdepthsupgrade.datacomponents.NDUDataComponents;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.potion.NDUPotions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.status.ChunkType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.ChunkDataEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeEvents {

    private static final String LEGACY_LAVA_GLASS_ENTITY_ID =
            NetherDepthsUpgrade.MODID + ":lava_glass_entity";

    private static final Map<LevelChunk, List<BlockPos>> PENDING_LAVA_GLASS_MIGRATIONS = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        addMixes(event.getBuilder());
    }

    public static void addMixes(PotionBrewing.Builder builder) {
        builder.addMix(Potions.AWKWARD, NDUItems.LAVA_PUFFERFISH.get(), NDUPotions.WITHER.getDelegate());
        builder.addMix(Potions.AWKWARD, NDUItems.EYEBALL_FISH_EYE.get(), NDUPotions.LAVA_VISION.getDelegate());
        builder.addMix(Potions.AWKWARD, NDUItems.EYEBALL_FISH.get(), NDUPotions.LAVA_VISION.getDelegate());
        builder.addMix(Potions.AWKWARD, NDUItems.OBSIDIANFISH.get(), NDUPotions.RESISTANCE.getDelegate());
        builder.addMix(Potions.AWKWARD, NDUItems.GLOWDINE.get(), NDUPotions.GLOWING.getDelegate());
        builder.addMix(NDUPotions.GLOWING.getDelegate(), Items.REDSTONE, NDUPotions.LONG_GLOWING.getDelegate());
        builder.addMix(NDUPotions.RESISTANCE.getDelegate(), Items.REDSTONE, NDUPotions.LONG_RESISTANCE.getDelegate());
        builder.addMix(NDUPotions.RESISTANCE.getDelegate(), Items.GLOWSTONE_DUST, NDUPotions.STRONG_RESISTANCE.getDelegate());
        builder.addMix(NDUPotions.LAVA_VISION.getDelegate(), Items.REDSTONE, NDUPotions.LONG_LAVA_VISION.getDelegate());
        builder.addMix(NDUPotions.WITHER.getDelegate(),Items.REDSTONE, NDUPotions.LONG_WITHER.getDelegate());

    }

    @SubscribeEvent
    public static void lavaMovementSpeed(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.isCreative() || player.isSpectator() || !player.isInLava() || !player.isAffectedByFluids()) {
            return;
        }

        BlockPos eyePosition = BlockPos.containing(player.getX(), player.getEyeY(), player.getZ());

        if (!player.level().getFluidState(eyePosition).is(FluidTags.LAVA)) {
            return;
        }

        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        if (!EnchantmentHelper.has(boots, NDUDataComponents.HAS_HELL_STRIDER.get())) {
            return;
        }

        int enchantmentLevel = getHellStriderLevel(player);

        if (enchantmentLevel <= 0) {
            return;
        }

        Vec3 movement = player.getDeltaMovement();

        double maximumSpeed = 0.4D + 0.06D * enchantmentLevel;
        double acceleration = 0.045D * enchantmentLevel;

        double movementX = movement.x;
        double movementZ = movement.z;

        double localInputX = player.xxa;
        double localInputZ = player.zza;

        double inputLength = Math.sqrt(localInputX * localInputX + localInputZ * localInputZ);

        if (inputLength > 1.0E-4D) {
            double inputStrength = Math.min(inputLength, 1.0D);

            localInputX /= inputLength;
            localInputZ /= inputLength;

            float rotation = player.getYRot() * Mth.DEG_TO_RAD;
            double sin = Mth.sin(rotation);
            double cos = Mth.cos(rotation);

            double inputX = localInputX * cos - localInputZ * sin;
            double inputZ = localInputZ * cos + localInputX * sin;
            double speedInInputDirection = movementX * inputX + movementZ * inputZ;

            double speedToAdd = Math.min(acceleration * inputStrength, Math.max(maximumSpeed - speedInInputDirection, 0.0D));

            movementX += inputX * speedToAdd;
            movementZ += inputZ * speedToAdd;
        }


        double movementY = movement.y;

        if (player.isShiftKeyDown()) {
            movementY = -0.075D * enchantmentLevel;
        } else if (player.jumping) {
            double upwardAcceleration = 0.016D * enchantmentLevel;
            double maximumUpwardSpeed = 0.20D + 0.025D * enchantmentLevel;

            movementY = Math.min(movementY + upwardAcceleration, maximumUpwardSpeed);
        }


        if (player.horizontalCollision && player.isFree(movementX, movementY + 0.6D,movementZ)) {
            movementY = 0.3D;
        }

        player.setDeltaMovement(movementX, movementY, movementZ);
    }

    private static int getHellStriderLevel(Player entity) {
        return entity.registryAccess()
                .registry(Registries.ENCHANTMENT)
                .flatMap(e -> e.getHolder(NDUEnchantments.HELL_STRIDER))
                .map(d -> EnchantmentHelper.getEnchantmentLevel(d, entity))
                .orElse(0);
    }


    @SubscribeEvent
    public static void frogFeed(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Frog frog)) {
            return;
        }

        ItemStack heldStack = event.getItemStack();

        if (!heldStack.is(NDUItems.MAGMACUBEFISH.get())) {
            return;
        }

        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));

        if (event.getLevel().isClientSide) {
            return;
        }

        Player player = event.getEntity();
        Level level = event.getLevel();

        ItemStack froglight = getFroglightForVariant(frog);

        ItemEntity drop = new ItemEntity(level, frog.getX(),frog.getY(), frog.getZ(), froglight);

        level.addFreshEntity(drop);

        level.playSound(null, frog.blockPosition(), SoundEvents.FROG_EAT, SoundSource.NEUTRAL, 1.0F, 1.0F);

        if (!player.isCreative()) {
            heldStack.shrink(1);
        }
    }

    private static ItemStack getFroglightForVariant(Frog frog) {
        if (frog.getVariant() == FrogVariant.COLD) {
            return new ItemStack(Items.VERDANT_FROGLIGHT);
        }

        if (frog.getVariant() == FrogVariant.WARM) {
            return new ItemStack(Items.PEARLESCENT_FROGLIGHT);
        }

        return new ItemStack(Items.OCHRE_FROGLIGHT);
    }
}

