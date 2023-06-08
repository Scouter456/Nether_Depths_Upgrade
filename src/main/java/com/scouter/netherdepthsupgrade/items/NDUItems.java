package com.scouter.netherdepthsupgrade.items;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.setup.Registration;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;


public class NDUItems {
    public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");

    public static CreativeModeTab NETHER_DEPTHS_UPGRADE_CREATIVE_TAB_DEFAULT;
    public static CreativeModeTab NETHER_DEPTHS_UPGRADE_CREATIVE_TAB_FISH;


    //BLOCK ITEMS
    public static final Item LAVA_SPONGE = registerBlockItem(NDUBlocks.LAVA_SPONGE);
    public static final Item WET_LAVA_SPONGE = registerBlockItem(NDUBlocks.WET_LAVA_SPONGE);
    public static final Item WARPED_KELP = registerBlockItem(NDUBlocks.WARPED_KELP);
    public static final Item WARPED_SEAGRASS = registerBlockItem(NDUBlocks.WARPED_SEAGRASS);
    public static final Item WARPED_KELP_BLOCK = registerBlockItem(NDUBlocks.WARPED_KELP_BLOCK);
    public static final Item WARPED_KELP_BLOCK_CARPET = registerBlockItem(NDUBlocks.WARPED_KELP_CARPET_BLOCK);
    public static final Item LAVA_GLASS = registerBlockItem(NDUBlocks.LAVA_GLASS);

    //ITEMS
    public static final Item SOUL_SUCKER_LEATHER = registerItem("soul_sucker_leather", new Item(new FabricItemSettings().fireproof()));

    public static final Item FORTRESS_GROUPER_PLATE = registerItem("fortress_grouper_plate", new Item(new FabricItemSettings().fireproof()));
    public static final Item EYEBALL_FISH_EYE = registerItem("eyeball", new Item(new FabricItemSettings().fireproof()));


    public static final Item SOUL_SUCKER_BOOTS = registerItem("soul_sucker_boots", new SoulSuckerArmorItem(NDUMaterialInit.SOUL_SUCKER, ArmorItem.Type.BOOTS, new FabricItemSettings().fireproof()));


    //FISH

    public static final Item LAVA_PUFFERFISH = registerItem("lava_pufferfish", new Item(new FabricItemSettings().fireproof().food(
            NDUFoods.LAVA_PUFFERFISH
    )));

    public static final Item OBSIDIANFISH = registerItem("obsidianfish", new Item(new FabricItemSettings().fireproof().food(
            NDUFoods.OBSIDIANFISH
    )));

    public static final Item SEARING_COD = registerItem("searing_cod", new Item(new FabricItemSettings().fireproof().food(
            NDUFoods.SEARING_COD
    )));
    public static final Item BONEFISH = registerItem("bonefish", new Item(new FabricItemSettings().fireproof().food(
            NDUFoods.BONEFISH
    )));

    public static final Item WITHER_BONEFISH = registerItem("wither_bonefish", new Item(new FabricItemSettings().fireproof().food(
            NDUFoods.WITHER_BONEFISH
    )));

    public static final Item BLAZEFISH = registerItem("blazefish", new Item(new FabricItemSettings().fireproof().food(
            NDUFoods.BLAZEFISH
    )));

    public static final Item MAGMACUBEFISH = registerItem("magmacubefish", new Item(new FabricItemSettings().fireproof().food(
            NDUFoods.MAGMACUBEFISH
    )));

    public static final Item GLOWDINE = registerItem("glowdine", new Item(new FabricItemSettings().fireproof().food(
            NDUFoods.GLOWDINE
    )));


    public static final Item SOULSUCKER = registerItem("soulsucker", new Item(new FabricItemSettings().fireproof()
    ));

    //FISH BUCKETS
    public static final Item LAVA_PUFFERFISH_BUCKET = registerItem("lava_pufferfish_bucket", new FishBucketItem(NDUEntity.LAVA_PUFFERFISH, Fluids.LAVA,new FabricItemSettings().fireproof()));
    public static final Item OBSIDIANFISH_BUCKET = registerItem("obsidianfish_bucket", new FishBucketItem(NDUEntity.OBSIDIAN_FISH, Fluids.LAVA,new FabricItemSettings().fireproof()));
    public static final Item SEARING_COD_BUCKET = registerItem("searing_cod_bucket", new FishBucketItem(NDUEntity.SEARING_COD, Fluids.LAVA,new FabricItemSettings().fireproof()));
    public static final Item BONEFISH_BUCKET = registerItem("bonefish_bucket", new FishBucketItem(NDUEntity.BONEFISH, Fluids.LAVA,new FabricItemSettings().fireproof()));
    public static final Item WITHER_BONEFISH_BUCKET = registerItem("wither_bonefish_bucket", new FishBucketItem(NDUEntity.WITHER_BONEFISH, Fluids.LAVA,new FabricItemSettings().fireproof()));
    public static final Item BLAZEFISH_BUCKET = registerItem("blazefish_bucket", new FishBucketItem(NDUEntity.BLAZEFISH, Fluids.LAVA,new FabricItemSettings().fireproof()));
    public static final Item MAGMACUBEFISH_BUCKET = registerItem("magmacubefish_bucket", new FishBucketItem(NDUEntity.MAGMACUBEFISH, Fluids.LAVA,new FabricItemSettings().fireproof()));
    public static final Item GLOWDINE_BUCKET = registerItem("glowdine_bucket", new FishBucketItem(NDUEntity.GLOWDINE, Fluids.LAVA,new FabricItemSettings().fireproof()));
    public static final Item SOULSUCKER_BUCKET = registerItem("soulsucker_bucket", new FishBucketItem(NDUEntity.SOULSUCKER, Fluids.LAVA,new FabricItemSettings().fireproof()));



    //SPAWN EGGS
    public static final Item LAVA_PUFFERFISH_SPAWN_EGG = registerItem("lava_pufferfish_spawn_egg", new SpawnEggItem(NDUEntity.LAVA_PUFFERFISH,
            0xf47c7c, 0xE01313,
            new FabricItemSettings()));

    public static final Item OBSIDIANFISH_SPAWN_EGG = registerItem("obsidianfish_spawn_egg", new SpawnEggItem(NDUEntity.OBSIDIAN_FISH,
            0x000001, 0x3b2754,
            new FabricItemSettings()));

    public static final Item SEARING_COD_SPAWN_EGG = registerItem("searing_cod_spawn_egg", new SpawnEggItem(NDUEntity.SEARING_COD,
            0xe35507, 0xfb5e07,
            new FabricItemSettings()));

    public static final Item BONEFISH_SPAWN_EGG = registerItem("bonefish_spawn_egg", new SpawnEggItem(NDUEntity.BONEFISH,
            12698049, 4802889,
            new FabricItemSettings()));

    public static final Item WITHER_BONEFISH_SPAWN_EGG = registerItem("wither_bonefish_spawn_egg", new SpawnEggItem(NDUEntity.WITHER_BONEFISH,
            1315860, 4672845,
            new FabricItemSettings()));

    public static final Item BLAZEFISH_SPAWN_EGG = registerItem("blazefish_spawn_egg", new SpawnEggItem(NDUEntity.BLAZEFISH,
            16167425, 16775294,
            new FabricItemSettings()));

    public static final Item MAGMACUBEFISH_SPAWN_EGG = registerItem("magmacubefish_spawn_egg", new SpawnEggItem(NDUEntity.MAGMACUBEFISH,
            3407872, 16579584,
            new FabricItemSettings()));

    public static final Item GLOWDINE_SPAWN_EGG = registerItem("glowdine_spawn_egg", new SpawnEggItem(NDUEntity.GLOWDINE,
            0xfbda74, 0xcc8654,
            new FabricItemSettings()));

    public static final Item SOULSUCKER_SPAWN_EGG = registerItem("soulsucker_spawn_egg", new SpawnEggItem(NDUEntity.SOULSUCKER,
            0x796152, 0xcc8654,
            new FabricItemSettings()));

    ////FISH
    public static final Item FORTRESS_GROUPER = registerItem("fortress_grouper", new Item(new FabricItemSettings().fireproof().food(NDUFoods.FORTRESS_GROUPER)));

    //FISH_BUCKET
    public static final Item FORTRESS_GROUPER_BUCKET = registerItem("fortress_grouper_bucket",  new FishBucketItem(NDUEntity.FORTRESS_GROUPER, Fluids.LAVA,
            new FabricItemSettings()));

    //Spawn Eggs
    public static final Item FORTRESS_GROUPER_SPAWN_EGG = registerItem("fortress_grouper_spawn_egg", new SpawnEggItem(NDUEntity.FORTRESS_GROUPER,
            0x38181e, 0xf09418,
            new FabricItemSettings()));

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
    public static final Item EYEBALL_FISH = registerItem("eyeball_fish", new Item(new FabricItemSettings().fireproof()
            .food(NDUFoods.EYEBALL_FISH)));

    //FISH_BUCKET
    public static final Item EYEBALL_FISH_BUCKET = registerItem("eyeball_fish_bucket", new FishBucketItem(NDUEntity.EYEBALL_FISH, Fluids.LAVA, (new FabricItemSettings())));

    //Spawn Eggs
    public static final Item EYEBALL_FISH_SPAWN_EGG = registerItem("eyeball_fish_spawn_egg", new SpawnEggItem(NDUEntity.EYEBALL_FISH,
            0x8a1010, 0xf0d57b,
            new FabricItemSettings()));
    
    
    public static final Item LAVA_FISHING_ROD = registerItem("lava_fishing_rod", new LavaFishingRodItem(new FabricItemSettings().fireproof().fireResistant().defaultDurability(256)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, prefix(name), item);
    }

    private static Item registerBlockItem(Block block){
        return Registry.register(BuiltInRegistries.ITEM, prefix(block.getDescriptionId().replace("block.netherdepthsupgrade.", "").toString()),
                new BlockItem(block, new FabricItemSettings().fireproof()));
    }

    public static void registerItems() {



    }


    private static void generateEnchantsForBoots(CreativeModeTab.Output output, Item item, CreativeModeTab.TabVisibility tabVisibility) {
        ItemStack soulsuckerBoots = new ItemStack(item);
        soulsuckerBoots.enchant(Enchantments.SOUL_SPEED, 3);
        output.accept(soulsuckerBoots, tabVisibility);
    }
    public static void ITEMS(){
        LOGGER.info("Registering Items for " + NetherDepthsUpgrade.MODID);
    }
}
