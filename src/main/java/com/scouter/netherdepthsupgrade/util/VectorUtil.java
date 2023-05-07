package com.scouter.netherdepthsupgrade.util;


import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VectorUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static Vec3 movementInputToVelocity(Vec3 movementInput, float speed, float yaw) {
        double d = movementInput.lengthSqr();
        if (d < 1.0E-7) {
            return Vec3.ZERO;
        }
        Vec3 vec3d = (d > 1.0 ? movementInput.normalize() : movementInput).multiply(speed, speed, speed);
        float f = (float)Math.sin(yaw * ((float)Math.PI / 180));
        float g = (float)Math.cos(yaw * ((float)Math.PI / 180));
        return new Vec3(vec3d.x * (double)g - vec3d.z * (double)f, vec3d.y, vec3d.z * (double)g + vec3d.x * (double)f);
    }


}