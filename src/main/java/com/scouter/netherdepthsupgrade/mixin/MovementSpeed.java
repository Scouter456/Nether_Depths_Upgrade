/*package com.scouter.netherdepthsupgrade.mixin;

import com.mojang.math.Vector3d;
import com.scouter.netherdepthsupgrade.enchantments.NDUEnchantments;
import com.scouter.netherdepthsupgrade.util.VectorUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MovementSpeed extends Entity {

    public MovementSpeed(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot var1);

    @Inject(method = "travel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;isNoGravity()Z"
            ),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInLava()Z"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isFallFlying()Z")
            ))
    private void modifyLavaSpeed(Vec3 movementInput, CallbackInfo ci) {
        ItemStack feetStack = getItemBySlot(EquipmentSlot.FEET);
        int level = EnchantmentHelper.getItemEnchantmentLevel(NDUEnchantments.HELL_STRIDER, feetStack);
        double e = this.getY();


        boolean flag = this.getDeltaMovement().y <= 0.0D;
        float speed = (float) (0.065 + (0.065 * level));
        this.setDeltaMovement(VectorUtil.movementInputToVelocity(movementInput, speed, this.getYRot()));
        Vec3 vec3 = this.getFluidFallingAdjustedMovement(0.08D,flag, this.getDeltaMovement());
        this.setDeltaMovement(vec3);

        if(this.isShiftKeyDown()){
            this.setDeltaMovement(vec3.x, -0.075000001192092896 * level, vec3.z);
        }

        if (this.horizontalCollision && this.isFree(vec3.x, vec3.y + 0.6000000238418579 - this.getY() + e, vec3.z)) {
            this.setDeltaMovement(vec3.x, 0.30000001192092896, vec3.z);
        }
    }

    public Vec3 getFluidFallingAdjustedMovement(double gravity, boolean isFalling, Vec3 deltaMovement) {
        if (!this.isNoGravity() && !this.isSprinting()) {
            double d;
            if (isFalling && Math.abs(deltaMovement.y - 0.005) >= 0.003 && Math.abs(deltaMovement.y - gravity / 16.0) < 0.003) {
                d = -0.003;
            } else {
                d = deltaMovement.y - gravity / 16.0;
            }

            return new Vec3(deltaMovement.x, d, deltaMovement.z);
        } else {
            return deltaMovement;
        }
    }
}

*/