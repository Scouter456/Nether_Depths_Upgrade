package com.scouter.netherdepthsupgrade.blocks.entity;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDUBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<BlockEntityType<LavaGlassBlockEntity>> LAVA_GLASS =
            BLOCK_ENTITIES.register("lava_glass_entity", () ->
                    BlockEntityType.Builder.of(LavaGlassBlockEntity::new,
                            NDUBlocks.LAVA_GLASS.get()).build(null));
}
