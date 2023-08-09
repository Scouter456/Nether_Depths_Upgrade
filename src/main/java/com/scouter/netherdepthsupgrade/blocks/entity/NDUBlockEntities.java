package com.scouter.netherdepthsupgrade.blocks.entity;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NDUBlockEntities {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");
    public static final BlockEntityType<LavaGlassBlockEntity> LAVA_GLASS =
            registerBlockEntity("lava_glass_entity",
                    BlockEntityType.Builder.of(LavaGlassBlockEntity::new,
                            NDUBlocks.LAVA_GLASS).build(null));


    private static BlockEntityType registerBlockEntity(String name, BlockEntityType block){
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, prefix(name), block);
    }

    public static void BLOCKENTITIES(){
        LOGGER.info("Registering Block Entities for " + NetherDepthsUpgrade.MODID);
    }
}
