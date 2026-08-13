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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.slf4j.Logger;

import java.util.List;

@EventBusSubscriber(modid = NetherDepthsUpgrade.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeEvents {


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
    }

    @SubscribeEvent
    public static void lavaMovementSpeed(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.isCreative() || player.isSpectator() || !player.isInLava() || !player.isAffectedByFluids()) {
            return;
        }

        /*
         * BlockPos.containing floors coordinates correctly.
         * Casting to int was incorrect for negative coordinates:
         * for example, (int) -0.5 becomes 0 instead of -1.
         */
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

        Vec3 originalMovement = player.getDeltaMovement();
        boolean falling = originalMovement.y <= 0.0D;

        double gravity = falling && player.hasEffect(MobEffects.SLOW_FALLING) ? 0.01D : 0.0D;

        double horizontalSpeed = 1.15D + 0.35D * enchantmentLevel;

        Vec3 boostedMovement = originalMovement.multiply(horizontalSpeed, 0.8D, horizontalSpeed);

        Vec3 adjustedMovement = player.getFluidFallingAdjustedMovement(gravity, falling, boostedMovement);

        Vec3 finalMovement = adjustedMovement;

        if (player.isShiftKeyDown()) {
            finalMovement = new Vec3(adjustedMovement.x, -0.075D * enchantmentLevel, adjustedMovement.z);
        }

        /*
         * Preserve the vanilla-style upward movement when colliding with a ledge.
         */
        if (player.horizontalCollision && player.isFree(adjustedMovement.x, adjustedMovement.y + 0.6D,adjustedMovement.z)) {
            finalMovement = new Vec3(adjustedMovement.x, 0.3D, adjustedMovement.z);
        }

        player.setDeltaMovement(finalMovement);
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

    @SubscribeEvent
    public static void migrateOldLavaGlass(ChunkEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)
                || !(event.getChunk() instanceof LevelChunk chunk)) {
            return;
        }

        List<BlockPos> legacyPositions = chunk
                .getBlockEntities()
                .entrySet()
                .stream()
                .filter(entry ->
                        entry.getValue().getType()
                                == NDUBlockEntities.LAVA_GLASS.get()
                )
                .map(entry -> entry.getKey().immutable())
                .toList();

        if (legacyPositions.isEmpty()) {
            return;
        }

        // Remove the obsolete data from the chunk. It will no
        // longer be present after the chunk is saved.
        for (BlockPos position : legacyPositions) {
            chunk.removeBlockEntity(position);
        }

        // Chunk load events can happen before the chunk has
        // completely finished loading. Delay level operations
        // until the server thread processes its next tasks.
        serverLevel.getServer().execute(() -> {
            for (BlockPos position : legacyPositions) {
                BlockState currentState =
                        serverLevel.getBlockState(position);

                if (!(currentState.getBlock()
                        instanceof LavaGlassBlock lavaGlass)) {
                    continue;
                }

                BlockState connectedState =
                        lavaGlass.withConnections(
                                currentState,
                                serverLevel,
                                position
                        );

                if (!connectedState.equals(currentState)) {
                    serverLevel.setBlock(
                            position,
                            connectedState,
                            Block.UPDATE_CLIENTS
                    );
                }
            }
        });
    }


}

