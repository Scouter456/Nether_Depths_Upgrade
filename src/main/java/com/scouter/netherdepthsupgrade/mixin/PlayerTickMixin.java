package com.scouter.netherdepthsupgrade.mixin;

import com.scouter.netherdepthsupgrade.datacomponents.NDUDataComponents;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerTickMixin {

    @Inject(method = "tick", at = @At("RETURN"))
    private void ndu$applyHellStriderMovement(CallbackInfo callbackInfo) {
        Player player = (Player) (Object) this;

        if (player.isCreative() || player.isSpectator() || !player.isInLava() || !player.isAffectedByFluids()) {
            return;
        }

        BlockPos eyePosition = BlockPos.containing(player.getX(), player.getEyeY(), player.getZ());

        if (!player.level().getFluidState(eyePosition).is(FluidTags.LAVA)) {
            return;
        }

        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        if (!EnchantmentHelper.has(boots, NDUDataComponents.HAS_HELL_STRIDER)) {
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

            movementY = Math.min(movementY + upwardAcceleration,maximumUpwardSpeed);
        }

        if (player.horizontalCollision && player.isFree(movementX, movementY + 0.6D, movementZ)) {
            movementY = 0.3D;
        }

        player.setDeltaMovement(movementX, movementY, movementZ);
    }

    @Unique
    private static int getHellStriderLevel(Player player) {
        return player.registryAccess()
                .registry(Registries.ENCHANTMENT)
                .flatMap(registry -> registry.getHolder(NDUEnchantments.HELL_STRIDER))
                .map(enchantment -> EnchantmentHelper.getEnchantmentLevel(enchantment, player))
                .orElse(0);
    }
}

