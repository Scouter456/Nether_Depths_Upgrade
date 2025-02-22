package com.scouter.netherdepthsupgrade.blocks.entity;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUBlockEntities {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final BlockEntityType<LavaGlassBlockEntity> LAVA_GLASS =
            registerBlockEntity("lava_glass_entity",
                    FabricBlockEntityTypeBuilder.create(
                            LavaGlassBlockEntity::new,
                            NDUBlocks.LAVA_GLASS
                    ).build());


    private static BlockEntityType registerBlockEntity(String name, BlockEntityType block){
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, prefix(name), block);
    }



    public static void BLOCKENTITIES(){
        LOGGER.info("Registering Block Entities for " + NetherDepthsUpgrade.MODID);
    }
}
