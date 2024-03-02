package com.scouter.netherdepthsupgrade.mixin;

import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(Player.class)
public class PlayerTickMixin {
    private static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");

    @Inject(method = "tick", at = @At("RETURN"))
    private void movement$playerTick(CallbackInfo ci) {
        Player player = (Player) (Object) this;


        if (player != null && !player.isCreative() && !player.isSpectator()) {
            double d0 = 0.000D;
            boolean flag = player.getDeltaMovement().y <= 0.0D;
            if (flag && player.hasEffect(MobEffects.SLOW_FALLING)) {
                d0 = 0.01;
            }
            Map<Enchantment,Integer> enchantsMap = EnchantmentHelper.getEnchantments(player.getItemBySlot(EquipmentSlot.FEET));
            if (enchantsMap != null && enchantsMap.containsKey(NDUEnchantments.HELL_STRIDER)) {
                double level = enchantsMap.get(NDUEnchantments.HELL_STRIDER);
                BlockPos eyePos = new BlockPos((int) player.getEyePosition().x(), (int) player.getEyePosition().y(), (int) player.getEyePosition().z());
                FluidState state = player.level.getFluidState(eyePos);
                if (player.isInLava() && player.isAffectedByFluids() && state.is(FluidTags.LAVA)) {
                    double e = player.getY();
                    float speed = (float) (1.65 + (0.1 * level));
                    player.setDeltaMovement(player.getDeltaMovement().multiply(speed, 0.8F, speed));
                    Vec3 vec33 = player.getFluidFallingAdjustedMovement(d0, flag, player.getDeltaMovement());

                    player.setDeltaMovement(vec33);
                    if (player.isShiftKeyDown()) {
                        player.setDeltaMovement(vec33.x, -0.075000001192092896 * level, vec33.z);
                    }

                    if (player.horizontalCollision && player.isFree(vec33.x, vec33.y + 0.6000000238418579 - player.getY() + e, vec33.z)) {
                        player.setDeltaMovement(vec33.x, 0.30000001192092896, vec33.z);
                    }
                }
            }
        }

    }
}

