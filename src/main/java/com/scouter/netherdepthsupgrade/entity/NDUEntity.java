package com.scouter.netherdepthsupgrade.entity;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.entity.entities.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.object.builder.FabricEntityType;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUEntity {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final EntityType<LavaPufferfishEntity> LAVA_PUFFERFISH = Registry.register(Registry.ENTITY_TYPE, prefix("lava_pufferfish"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, LavaPufferfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7F,0.7F))
                    .trackRangeChunks(4)
                    .build());

    public static final EntityType<ObsidianfishEntity> OBSIDIAN_FISH = Registry.register(Registry.ENTITY_TYPE, prefix("obsidianfish"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, ObsidianfishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7F,0.4F))
                    .trackRangeChunks(4)
                    .build());

    public static final EntityType<SearingCodEntity> SEARING_COD = Registry.register(Registry.ENTITY_TYPE, prefix("searing_cod"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, SearingCodEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F,0.3F))
                    .trackRangeChunks(4)
                    .build());

    public static final EntityType<BonefishEntity> BONEFISH = Registry.register(Registry.ENTITY_TYPE, prefix("bonefish"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, BonefishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F,0.4F))
                    .trackRangeChunks(4)
                    .build());
    public static final EntityType<WitherBonefishEntity> WITHER_BONEFISH = Registry.register(Registry.ENTITY_TYPE, prefix("wither_bonefish"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, WitherBonefishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F,0.4F))
                    .trackRangeChunks(4)
                    .build());

    public static final EntityType<BlazefishEntity> BLAZEFISH = Registry.register(Registry.ENTITY_TYPE, prefix("blazefish"),
            FabricEntityTypeBuilder.create(MobCategory.AMBIENT, BlazefishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7F,0.8F))
                    .trackRangeChunks(4)
                    .build());

    public static final EntityType<MagmaCubefishEntity> MAGMACUBEFISH = Registry.register(Registry.ENTITY_TYPE, prefix("magmacubefish"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, MagmaCubefishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F,0.6F))
                    .trackRangeChunks(4)
                    .build());
    public static final EntityType<GlowdineEntity> GLOWDINE = Registry.register(Registry.ENTITY_TYPE, prefix("glowdine"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, GlowdineEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F,0.4F))
                    .trackRangeChunks(4)
                    .build());

    public static final EntityType<SoulSuckerEntity> SOULSUCKER = Registry.register(Registry.ENTITY_TYPE, prefix("soulsucker"),
            FabricEntityTypeBuilder.create(MobCategory.WATER_AMBIENT, SoulSuckerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F,0.4F))
                    .trackRangeChunks(4)
                    .build());

    public static final EntityType<LavaFishingBobberEntity> LAVA_BOBBER = Registry.register(Registry.ENTITY_TYPE, prefix("lava_fishing_bobber"),
            FabricEntityTypeBuilder.<LavaFishingBobberEntity>create(MobCategory.WATER_AMBIENT, LavaFishingBobberEntity::new)
                    .disableSaving().disableSummon()
                    .fireImmune()
                    .dimensions(EntityDimensions.fixed(0.25F,0.25F))
                    .trackRangeChunks(4)
                    .trackedUpdateRate(5)
                    .build());



    public static void ENTITY_TYPES(){
        LOGGER.info("Registering Entity Types for " + NetherDepthsUpgrade.MODID);
    }
}
