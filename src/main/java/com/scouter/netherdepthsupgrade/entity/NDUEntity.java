package com.scouter.netherdepthsupgrade.entity;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.entity.entities.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class NDUEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, NetherDepthsUpgrade.MODID);


    public static final RegistryObject<EntityType<LavaPufferfishEntity>> LAVA_PUFFERFISH = ENTITY_TYPES.register("lava_pufferfish",
            () -> EntityType.Builder.of(LavaPufferfishEntity::new, EntityClassification.WATER_AMBIENT)
                    .sized(0.7F, 0.7F).fireImmune().clientTrackingRange(4)
                    .build(prefix("lava_pufferfish").toString()));

    public static final RegistryObject<EntityType<ObsidianfishEntity>> OBSIDIAN_FISH = ENTITY_TYPES.register("obsidianfish",
            () -> EntityType.Builder.of(ObsidianfishEntity::new, EntityClassification.WATER_AMBIENT)
                    .sized(0.7F, 0.4F).clientTrackingRange(4)
                    .build(prefix("obsidianfish").toString()));

    public static final RegistryObject<EntityType<SearingCodEntity>> SEARING_COD = ENTITY_TYPES.register("searing_cod",
            () -> EntityType.Builder.of(SearingCodEntity::new, EntityClassification.WATER_AMBIENT)
                    .sized(0.5F, 0.3F).clientTrackingRange(4)
                    .build(prefix("searing_cod").toString()));

    public static final RegistryObject<EntityType<BonefishEntity>> BONEFISH = ENTITY_TYPES.register("bonefish",
            () -> EntityType.Builder.of(BonefishEntity::new, EntityClassification.WATER_AMBIENT)
                    .sized(0.6F, 0.4F).clientTrackingRange(4)
                    .build(prefix("bonefish").toString()));

    public static final RegistryObject<EntityType<WitherBonefishEntity>> WITHER_BONEFISH = ENTITY_TYPES.register("wither_bonefish",
            () -> EntityType.Builder.of(WitherBonefishEntity::new, EntityClassification.WATER_AMBIENT)
                    .sized(0.6F, 0.4F).clientTrackingRange(4)
                    .build(prefix("wither_bonefish").toString()));

    public static final RegistryObject<EntityType<BlazefishEntity>> BLAZEFISH = ENTITY_TYPES.register("blazefish",
            () -> EntityType.Builder.of(BlazefishEntity::new, EntityClassification.AMBIENT)
                    .sized(0.7F, 0.8F).clientTrackingRange(4)
                    .build(prefix("blazefish").toString()));

    public static final RegistryObject<EntityType<MagmaCubefishEntity>> MAGMACUBEFISH = ENTITY_TYPES.register("magmacubefish",
            () -> EntityType.Builder.of(MagmaCubefishEntity::new, EntityClassification.WATER_AMBIENT)
                    .sized(0.6F, 0.6F).clientTrackingRange(4)
                    .build(prefix("magmacubefish").toString()));

    public static final RegistryObject<EntityType<GlowdineEntity>> GLOWDINE = ENTITY_TYPES.register("glowdine",
            () -> EntityType.Builder.of(GlowdineEntity::new, EntityClassification.WATER_AMBIENT)
                    .sized(0.6F, 0.6F).clientTrackingRange(4)
                    .build(prefix("glowdine").toString()));

    public static final RegistryObject<EntityType<SoulSuckerEntity>> SOULSUCKER = ENTITY_TYPES.register("soulsucker",
            () -> EntityType.Builder.of(SoulSuckerEntity::new, EntityClassification.WATER_AMBIENT)
                    .sized(0.6F, 0.6F).clientTrackingRange(4)
                    .build(prefix("soulsucker").toString()));
}
