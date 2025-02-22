package com.scouter.netherdepthsupgrade.entity.model;

import com.scouter.netherdepthsupgrade.entity.entities.LavaPufferfishEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class LavaPufferfishModel extends GeoModel<LavaPufferfishEntity> {
    private static final ResourceLocation PUFFER_LOCATION = prefix("textures/entity/lava_pufferfish.png");
    private static final ResourceLocation PUFFER_MODEL_BIG_LOCATION = prefix("geo/entity/lava_puffer_fish_big.geo.json");
    private static final ResourceLocation PUFFER_MODEL_SMALL_LOCATION = prefix("geo/entity/lava_puffer_fish_small.geo.json");
    private static final ResourceLocation PUFFER_MODEL_MEDIUM_LOCATION = prefix("geo/entity/lava_puffer_fish_medium.geo.json");
    private static final ResourceLocation PUFFER_ANIM_LOCATION = prefix("animations/entity/lavapufferfish.animation.json");
    private static final ResourceLocation PUFFER_ANIM_MEDIUM_LOCATION = prefix("animations/entity/lava_puffer_fish_medium.animation.json");
    private static final ResourceLocation PUFFER_ANIM_SMALL_LOCATION = prefix("animations/entity/lava_puffer_fish_small.animation.json");



    @Override
    public ResourceLocation getModelResource(LavaPufferfishEntity animatable, @Nullable GeoRenderer<LavaPufferfishEntity> renderer) {

        return switch (animatable.getPuffState()) {
            case 0 -> PUFFER_MODEL_SMALL_LOCATION;
            case 1 -> PUFFER_MODEL_MEDIUM_LOCATION;
            default -> PUFFER_MODEL_BIG_LOCATION;
        };
    }

    @Override
    public ResourceLocation getTextureResource(LavaPufferfishEntity animatable, @Nullable GeoRenderer<LavaPufferfishEntity> renderer) {
        return PUFFER_LOCATION;
    }

    @Override
    public ResourceLocation getAnimationResource(LavaPufferfishEntity animatable) {
        return PUFFER_ANIM_LOCATION;
        //return switch (animatable.getPuffState()) {
        //    case 0 -> PUFFER_ANIM_SMALL_LOCATION;
        //    case 1 -> PUFFER_ANIM_MEDIUM_LOCATION;
        //    default -> PUFFER_ANIM_BIG_LOCATION;
        //};
    }
}
