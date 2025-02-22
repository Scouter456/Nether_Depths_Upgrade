package com.scouter.netherdepthsupgrade.entity;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.entity.entities.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUEntity {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");




    public static final EntityType<LavaPufferfishEntity> LAVA_PUFFERFISH = register("lava_pufferfish",
            EntityType.Builder.of(LavaPufferfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.7F,0.7F)
                    .clientTrackingRange(4));

    public static final EntityType<ObsidianfishEntity> OBSIDIAN_FISH = register("obsidianfish",
            EntityType.Builder.of(ObsidianfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.7F,0.4F)
                    .clientTrackingRange(4));

    public static final EntityType<SearingCodEntity> SEARING_COD = register("searing_cod",
            EntityType.Builder.of(SearingCodEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F,0.3F)
                    .clientTrackingRange(4)
                    );

    public static final EntityType<BonefishEntity> BONEFISH = register("bonefish",
            EntityType.Builder.of(BonefishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F,0.4F)
                    .clientTrackingRange(4)
                    );
    public static final EntityType<WitherBonefishEntity> WITHER_BONEFISH = register("wither_bonefish",
            EntityType.Builder.of(WitherBonefishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F,0.4F)
                    .clientTrackingRange(4)
                    );

    public static final EntityType<BlazefishEntity> BLAZEFISH = register("blazefish",
            EntityType.Builder.of(BlazefishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.7F,0.8F)
                    .clientTrackingRange(4)
                    );

    public static final EntityType<MagmaCubefishEntity> MAGMACUBEFISH = register("magmacubefish",
            EntityType.Builder.of(MagmaCubefishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F,0.6F)
                    .clientTrackingRange(4)
                    );
    public static final EntityType<GlowdineEntity> GLOWDINE = register("glowdine",
            EntityType.Builder.of(GlowdineEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F,0.4F)
                    .clientTrackingRange(4)
                    );

    public static final EntityType<SoulSuckerEntity> SOULSUCKER = register("soulsucker",
            EntityType.Builder.of(SoulSuckerEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F,0.4F)
                    .clientTrackingRange(4)
                    );

    public static final EntityType<FortressGrouperEntity> FORTRESS_GROUPER = register("fortressgrouper",
            EntityType.Builder.of(FortressGrouperEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(2F, 1.6F)
                    .clientTrackingRange(4)
                    );

    public static final EntityType<EyeballfishEntity> EYEBALL_FISH = register("eyeball_fish",
            EntityType.Builder.of(EyeballfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.7F, 0.7F)
                    .clientTrackingRange(4)
                    );
    public static final EntityType<LavaFishingBobberEntity> LAVA_BOBBER = register("lava_fishing_bobber",
            EntityType.Builder.<LavaFishingBobberEntity>of(LavaFishingBobberEntity::new, MobCategory.WATER_AMBIENT)
                    .noSave().noSummon()
                    .fireImmune()
                    .sized(0.25F,0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(5));

    private static ResourceKey<EntityType<?>> nduEntityId(String string) {
        return ResourceKey.create(Registries.ENTITY_TYPE, prefix(string));
    }

    private static <T extends Entity> EntityType<T> register(String string, EntityType.Builder<T> builder) {
        return register(nduEntityId(string), builder);
    }
    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> resourceKey, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceKey, builder.build(resourceKey));
    }

    public static void ENTITY_TYPES(){
        LOGGER.info("Registering Entity Types for " + NetherDepthsUpgrade.MODID);
    }
}
