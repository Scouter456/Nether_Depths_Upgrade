package com.scouter.netherdepthsupgrade.items;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class NDUItems {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");

    public static CreativeModeTab NETHER_DEPTHS_UPGRADE_CREATIVE_TAB_DEFAULT;
    public static CreativeModeTab NETHER_DEPTHS_UPGRADE_CREATIVE_TAB_FISH;


    //BLOCK ITEMS
    public static final Item LAVA_SPONGE = registerBlock(NDUBlocks.LAVA_SPONGE);
    public static final Item WET_LAVA_SPONGE = registerWetLavaSpongeBlockItem(NDUBlocks.WET_LAVA_SPONGE);
    public static final Item WARPED_KELP = registerBlock(NDUBlocks.WARPED_KELP);
    public static final Item WARPED_SEAGRASS = registerBlock(NDUBlocks.WARPED_SEAGRASS);
    public static final Item WARPED_KELP_BLOCK = registerBlock(NDUBlocks.WARPED_KELP_BLOCK);
    public static final Item WARPED_KELP_BLOCK_CARPET = registerBlock(NDUBlocks.WARPED_KELP_CARPET_BLOCK);

    public static final Item CRIMSON_KELP = registerBlock(NDUBlocks.CRIMSON_KELP);
    public static final Item CRIMSON_SEAGRASS = registerBlock(NDUBlocks.CRIMSON_SEAGRASS);
    public static final Item CRIMSON_KELP_BLOCK = registerBlock(NDUBlocks.CRIMSON_KELP_BLOCK);
    public static final Item CRIMSON_KELP_CARPET_BLOCK = registerBlock(NDUBlocks.CRIMSON_KELP_CARPET_BLOCK);
    public static final Item LAVA_GLASS = registerBlock(NDUBlocks.LAVA_GLASS);

    //ITEMS
    public static final Item SOUL_SUCKER_LEATHER = registerItemFireRes("soul_sucker_leather");

    public static final Item FORTRESS_GROUPER_PLATE = registerItemFireRes("fortress_grouper_plate");
    public static final Item EYEBALL_FISH_EYE = registerItemFireRes("eyeball");


    public static final Item SOUL_SUCKER_BOOTS = registerItem("soul_sucker_boots", properties ->  new SoulSuckerArmorItem(NDUArmorMaterials.SOUL_SUCKER, ArmorType.BOOTS, properties), new Item.Properties().fireResistant().durability(ArmorType.HELMET.getDurability(7)));


    //FISH

    public static final Item LAVA_PUFFERFISH = registerItem("lava_pufferfish", new Item.Properties().fireResistant().food(
            NDUFoods.LAVA_PUFFERFISH, NDUConsumables.LAVA_PUFFERFISH
    ));

    public static final Item OBSIDIANFISH = registerItem("obsidianfish", new Item.Properties().fireResistant().food(
            NDUFoods.OBSIDIANFISH, NDUConsumables.OBSIDIANFISH
    ));

    public static final Item SEARING_COD = registerItem("searing_cod", new Item.Properties().fireResistant().food(
            NDUFoods.SEARING_COD, NDUConsumables.SEARING_COD
    ));
    public static final Item BONEFISH = registerItem("bonefish",new Item.Properties().fireResistant().food(
            NDUFoods.BONEFISH, NDUConsumables.BONEFISH
    ));

    public static final Item WITHER_BONEFISH = registerItem("wither_bonefish", new Item.Properties().fireResistant().food(
            NDUFoods.WITHER_BONEFISH, NDUConsumables.WITHER_BONEFISH
    ));

    public static final Item BLAZEFISH = registerItem("blazefish", new Item.Properties().fireResistant().food(
            NDUFoods.BLAZEFISH, NDUConsumables.BLAZEFISH
    ));

    public static final Item MAGMACUBEFISH = registerItem("magmacubefish", new Item.Properties().fireResistant().food(
            NDUFoods.MAGMACUBEFISH, NDUConsumables.MAGMACUBEFISH
    ));

    public static final Item GLOWDINE = registerItem("glowdine", new Item.Properties().fireResistant().food(
            NDUFoods.GLOWDINE, NDUConsumables.GLOWDINE
    ));


    public static final Item SOULSUCKER = registerItemFireRes("soulsucker"
    );

    //FISH BUCKETS
    public static final Item LAVA_PUFFERFISH_BUCKET = registerItem("lava_pufferfish_bucket", properties ->  new FishBucketItem(NDUEntity.LAVA_PUFFERFISH, Fluids.LAVA, properties), new Item.Properties().fireResistant());
    public static final Item OBSIDIANFISH_BUCKET = registerItem("obsidianfish_bucket",properties -> new FishBucketItem(NDUEntity.OBSIDIAN_FISH, Fluids.LAVA,properties),new Item.Properties().fireResistant());
    public static final Item SEARING_COD_BUCKET = registerItem("searing_cod_bucket",properties -> new FishBucketItem(NDUEntity.SEARING_COD, Fluids.LAVA,properties ),new Item.Properties().fireResistant());
    public static final Item BONEFISH_BUCKET = registerItem("bonefish_bucket",properties -> new FishBucketItem(NDUEntity.BONEFISH, Fluids.LAVA,properties),new Item.Properties().fireResistant());
    public static final Item WITHER_BONEFISH_BUCKET = registerItem("wither_bonefish_bucket",properties -> new FishBucketItem(NDUEntity.WITHER_BONEFISH, Fluids.LAVA,properties),new Item.Properties().fireResistant());
    public static final Item BLAZEFISH_BUCKET = registerItem("blazefish_bucket", properties ->new FishBucketItem(NDUEntity.BLAZEFISH, Fluids.LAVA,properties),new Item.Properties().fireResistant());
    public static final Item MAGMACUBEFISH_BUCKET = registerItem("magmacubefish_bucket", properties ->new FishBucketItem(NDUEntity.MAGMACUBEFISH, Fluids.LAVA,properties),new Item.Properties().fireResistant());
    public static final Item GLOWDINE_BUCKET = registerItem("glowdine_bucket", properties ->new FishBucketItem(NDUEntity.GLOWDINE, Fluids.LAVA,properties),new Item.Properties().fireResistant());
    public static final Item SOULSUCKER_BUCKET = registerItem("soulsucker_bucket", properties ->new FishBucketItem(NDUEntity.SOULSUCKER, Fluids.LAVA,properties),new Item.Properties().fireResistant());



    //SPAWN EGGS
    public static final Item LAVA_PUFFERFISH_SPAWN_EGG = registerItem("lava_pufferfish_spawn_egg", properties ->  new SpawnEggItem(NDUEntity.LAVA_PUFFERFISH,
           // 0xf47c7c, 0xE01313,
            properties));

    public static final Item OBSIDIANFISH_SPAWN_EGG = registerItem("obsidianfish_spawn_egg", properties -> new SpawnEggItem(NDUEntity.OBSIDIAN_FISH,
            //0x000001, 0x3b2754,
            properties));

    public static final Item SEARING_COD_SPAWN_EGG = registerItem("searing_cod_spawn_egg", properties -> new SpawnEggItem(NDUEntity.SEARING_COD,
            //0xe35507, 0xfb5e07,
            properties));

    public static final Item BONEFISH_SPAWN_EGG = registerItem("bonefish_spawn_egg", properties -> new SpawnEggItem(NDUEntity.BONEFISH,
            //12698049, 4802889,
            properties));

    public static final Item WITHER_BONEFISH_SPAWN_EGG = registerItem("wither_bonefish_spawn_egg",  properties ->new SpawnEggItem(NDUEntity.WITHER_BONEFISH,
            //1315860, 4672845,
            properties));

    public static final Item BLAZEFISH_SPAWN_EGG = registerItem("blazefish_spawn_egg",  properties ->new SpawnEggItem(NDUEntity.BLAZEFISH,
            //16167425, 16775294,
            properties));

    public static final Item MAGMACUBEFISH_SPAWN_EGG = registerItem("magmacubefish_spawn_egg",  properties ->new SpawnEggItem(NDUEntity.MAGMACUBEFISH,
           // 3407872, 16579584,
            properties));

    public static final Item GLOWDINE_SPAWN_EGG = registerItem("glowdine_spawn_egg", properties -> new SpawnEggItem(NDUEntity.GLOWDINE,
            //0xfbda74, 0xcc8654,
            properties));

    public static final Item SOULSUCKER_SPAWN_EGG = registerItem("soulsucker_spawn_egg",  properties ->new SpawnEggItem(NDUEntity.SOULSUCKER,
            //0x796152, 0xcc8654,
            properties));

    ////FISH
    public static final Item FORTRESS_GROUPER = registerItem("fortress_grouper", new Item.Properties().fireResistant().food(
            NDUFoods.FORTRESS_GROUPER, NDUConsumables.FORTRESS_GROUPER
    ));

    //FISH_BUCKET
    public static final Item FORTRESS_GROUPER_BUCKET = registerItem("fortress_grouper_bucket", properties ->  new FishBucketItem(NDUEntity.FORTRESS_GROUPER, Fluids.LAVA,
            properties));

    //Spawn Eggs
    public static final Item FORTRESS_GROUPER_SPAWN_EGG = registerItem("fortress_grouper_spawn_egg",properties -> new SpawnEggItem(NDUEntity.FORTRESS_GROUPER,
            //0x38181e, 0xf09418,
            properties));

    //FISH
    //public static final Item NETHER_URCHIN = registerItem("nether_urchin", () -> new Item(Registration.fishBuilder().fireResistant()
    //        .food(NDUFoods.FORTRESS_GROUPER)));
    //
    ////FISH_BUCKET
    //public static final Item NETHER_URCHIN_BUCKET = registerItem("nether_urchin_bucket", () -> new FishBucketItem(NDUEntity.NETHER_URCHIN, Fluids.LAVA, (Registration.fishBuilder())));
    //
    ////Spawn Eggs
    //public static final Item NETHER_URCHIN_SPAWN_EGG = registerItem("nether_urchin_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.NETHER_URCHIN,
    //        0x5a0000, 0x7b0000, Registration.fishBuilder()));


    //FISH
    public static final Item EYEBALL_FISH = registerItem("eyeball_fish",new Item.Properties().fireResistant()
            .food(NDUFoods.EYEBALL_FISH, NDUConsumables.EYEBALL_FISH
            ));

    //FISH_BUCKET
    public static final Item EYEBALL_FISH_BUCKET = registerItem("eyeball_fish_bucket",properties -> new FishBucketItem(NDUEntity.EYEBALL_FISH, Fluids.LAVA, properties ));

    //Spawn Eggs
    public static final Item EYEBALL_FISH_SPAWN_EGG = registerItem("eyeball_fish_spawn_egg", properties ->new SpawnEggItem(NDUEntity.EYEBALL_FISH,
            //0x8a1010, 0xf0d57b,
            properties));
    
    
    public static final Item LAVA_FISHING_ROD = registerItem("lava_fishing_rod", LavaFishingRodItem::new,new Item.Properties().fireResistant().fireResistant().durability(256));



    private static Item registerBlockItem(Block block){
        return Registry.register(BuiltInRegistries.ITEM, prefix(block.getDescriptionId().replace("block.netherdepthsupgrade.", "").toString()),
                new BlockItem(block, new Item.Properties().fireResistant()));
    }


    public static Item registerItem(ResourceKey<Item> resourceKey, Function<Item.Properties, Item> function, Item.Properties properties) {
        Item item = (Item)function.apply(properties.setId(resourceKey));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, resourceKey, item);
    }

    public static Item registerBlock(Block block, BiFunction<Block, Item.Properties, Item> biFunction) {
        return registerBlock(block, biFunction, new Item.Properties());
    }
    public static Item registerBlock(Block block) {
        return registerBlock(block, BlockItem::new);
    }

    public static Item registerBlock(Block block, BiFunction<Block, Item.Properties, Item> biFunction, Item.Properties properties) {
        return registerItem(
                blockIdToItemId(block.builtInRegistryHolder().key()), propertiesx -> (Item)biFunction.apply(block, propertiesx), properties.useBlockDescriptionPrefix()
        );
    }

    private static ResourceKey<Item> blockIdToItemId(ResourceKey<Block> resourceKey) {
        return ResourceKey.create(Registries.ITEM, resourceKey.location());
    }



    private static Item registerWetLavaSpongeBlockItem(Block block){
        return registerBlock( block, WetLavaSpongeBlockItem::new, new Item.Properties().fireResistant());
    }


    private static ResourceKey<Item> nduItemId(String string) {
        return ResourceKey.create(Registries.ITEM, prefix(string));
    }
    public static Item registerItemFireRes(String string, Function<Item.Properties, Item> function) {
        return registerItem(nduItemId(string), function, new Item.Properties().fireResistant());
    }
    public static Item registerItem(String string, Function<Item.Properties, Item> function) {
        return registerItem(nduItemId(string), function, new Item.Properties());
    }

    public static Item registerItem(String string, Function<Item.Properties, Item> function, Item.Properties properties) {
        return registerItem(nduItemId(string), function, properties);
    }

    public static Item registerItem(String string, Item.Properties properties) {
        return registerItem(nduItemId(string), Item::new, properties);
    }

    public static Item registerItem(String string) {
        return registerItem(nduItemId(string), Item::new, new Item.Properties());
    }

    public static Item registerItemFireRes(String string) {
        return registerItem(nduItemId(string), Item::new, new Item.Properties().fireResistant());
    }

    public static Item registerItem(ResourceKey<Item> resourceKey, Function<Item.Properties, Item> function) {
        return registerItem(resourceKey, function, new Item.Properties());
    }




    public static void ITEMS(){
        LOGGER.info("Registering Items for " + NetherDepthsUpgrade.MODID);
    }
}
