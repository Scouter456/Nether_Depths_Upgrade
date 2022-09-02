package com.scouter.netherdepthsupgrade.entity;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.entity.entities.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class NDUEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, NetherDepthsUpgrade.MODID);


    public static final RegistryObject<EntityType<LavaPufferfishEntity>> LAVA_PUFFERFISH = ENTITY_TYPES.register("lava_pufferfish",
            () -> EntityType.Builder.of(LavaPufferfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.7F, 0.7F).clientTrackingRange(4)
                    .build(prefix("lava_pufferfish").toString()));

    public static final RegistryObject<EntityType<ObsidianfishEntity>> OBSIDIAN_FISH = ENTITY_TYPES.register("obsidian_fish",
            () -> EntityType.Builder.of(ObsidianfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.7F, 0.4F).clientTrackingRange(4)
                    .build(prefix("obsidian_fish").toString()));

    public static final RegistryObject<EntityType<SearingCodEntity>> SEARING_COD = ENTITY_TYPES.register("searing_cod",
            () -> EntityType.Builder.of(SearingCodEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.3F).clientTrackingRange(4)
                    .build(prefix("searing_cod").toString()));

    public static final RegistryObject<EntityType<BonefishEntity>> BONEFISH = ENTITY_TYPES.register("bonefish",
            () -> EntityType.Builder.of(BonefishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F, 0.4F).clientTrackingRange(4)
                    .build(prefix("bonefish").toString()));

    public static final RegistryObject<EntityType<WitherBonefishEntity>> WITHER_BONEFISH = ENTITY_TYPES.register("wither_bonefish",
            () -> EntityType.Builder.of(WitherBonefishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F, 0.4F).clientTrackingRange(4)
                    .build(prefix("wither_bonefish").toString()));

    public static final RegistryObject<EntityType<BlazefishEntity>> BLAZEFISH = ENTITY_TYPES.register("blazefish",
            () -> EntityType.Builder.of(BlazefishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.7F, 0.8F).clientTrackingRange(4)
                    .build(prefix("blazefish").toString()));

    public static final RegistryObject<EntityType<MagmaCubefishEntity>> MAGMACUBEFISH = ENTITY_TYPES.register("magmacubefish",
            () -> EntityType.Builder.of(MagmaCubefishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F, 0.6F).clientTrackingRange(4)
                    .build(prefix("magmacubefish").toString()));

    public static final RegistryObject<EntityType<GlowdineEntity>> GLOWDINE = ENTITY_TYPES.register("glowdine",
            () -> EntityType.Builder.of(GlowdineEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F, 0.6F).clientTrackingRange(4)
                    .build(prefix("glowdine").toString()));

    public static final RegistryObject<EntityType<SoulSuckerEntity>> SOULSUCKER = ENTITY_TYPES.register("soulsucker",
            () -> EntityType.Builder.of(SoulSuckerEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F, 0.6F).clientTrackingRange(4)
                    .build(prefix("soulsucker").toString()));
    public static final RegistryObject<EntityType<LavaFishingHook>> LAVA_BOBBER = ENTITY_TYPES.register("lava_bobber",
            () -> EntityType.Builder.<LavaFishingHook>of(LavaFishingHook::new, MobCategory.MISC).noSave().noSummon().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(5).build("lava_bobber"));

}
