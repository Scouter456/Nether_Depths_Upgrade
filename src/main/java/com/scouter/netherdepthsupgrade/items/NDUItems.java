package com.scouter.netherdepthsupgrade.items;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;


public class NDUItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NetherDepthsUpgrade.MODID);

    //From Blocks
    public static final DeferredItem<BlockItem> LAVA_SPONGE = fromBlockFireRes(NDUBlocks.LAVA_SPONGE);
    public static final DeferredItem<BlockItem> WET_LAVA_SPONGE = fromBlockFireRes(NDUBlocks.WET_LAVA_SPONGE);
    public static final DeferredItem<BlockItem> WARPED_KELP = fromBlockFireRes(NDUBlocks.WARPED_KELP);
    public static final DeferredItem<BlockItem> WARPED_SEAGRASS = fromBlockFireRes(NDUBlocks.WARPED_SEAGRASS);

    public static final DeferredItem<BurnableBlockItem> WARPED_KELP_BLOCK = fromBlockFireResFuel(NDUBlocks.WARPED_KELP_BLOCK, 6400);
    public static final DeferredItem<BlockItem> WARPED_KELP_CARPET_BLOCK = fromBlockFireRes(NDUBlocks.WARPED_KELP_CARPET_BLOCK);

    public static final DeferredItem<BlockItem> CRIMSON_KELP = fromBlockFireRes(NDUBlocks.CRIMSON_KELP);
    public static final DeferredItem<BlockItem> CRIMSON_SEAGRASS = fromBlockFireRes(NDUBlocks.CRIMSON_SEAGRASS);

    public static final DeferredItem<BurnableBlockItem> CRIMSON_KELP_BLOCK = fromBlockFireResFuel(NDUBlocks.CRIMSON_KELP_BLOCK, 6400);
    public static final DeferredItem<BlockItem> CRIMSON_KELP_CARPET_BLOCK = fromBlockFireRes(NDUBlocks.CRIMSON_KELP_CARPET_BLOCK);
    public static final DeferredItem<BlockItem> LAVA_GLASS = fromBlockFireRes(NDUBlocks.LAVA_GLASS);
    //ITEM
    public static final DeferredItem<Item> SOUL_SUCKER_LEATHER = ITEMS.register("soul_sucker_leather", () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> FORTRESS_GROUPER_PLATE = ITEMS.register("fortress_grouper_plate", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> EYEBALL_FISH_EYE = ITEMS.register("eyeball", () -> new Item(new Item.Properties().fireResistant()));

    //ARMOR
    public static final DeferredItem<SoulSuckerArmorItem> SOUL_SUCKER_BOOTS = ITEMS.register("soul_sucker_boots", () -> new SoulSuckerArmorItem(NDUArmorMaterials.SOUL_SUCKER, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(7))));

    //FISH
    public static final DeferredItem<Item> LAVA_PUFFERFISH = ITEMS.register("lava_pufferfish", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.LAVA_PUFFERFISH)));
    public static final DeferredItem<Item> OBSIDIANFISH = ITEMS.register("obsidianfish", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.OBSIDIANFISH)));
    public static final DeferredItem<Item> SEARING_COD = ITEMS.register("searing_cod", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.SEARING_COD)){
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            return 3200;
        }
    });
    public static final DeferredItem<Item> BONEFISH = ITEMS.register("bonefish", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.BONEFISH)));
    public static final DeferredItem<Item> WITHER_BONEFISH = ITEMS.register("wither_bonefish", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.WITHER_BONEFISH)));
    public static final DeferredItem<Item> BLAZEFISH = ITEMS.register("blazefish", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.BLAZEFISH)));
    public static final DeferredItem<Item> MAGMACUBEFISH = ITEMS.register("magmacubefish", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.MAGMACUBEFISH)));
    public static final DeferredItem<Item> GLOWDINE = ITEMS.register("glowdine", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.GLOWDINE)));
    public static final DeferredItem<Item> SOULSUCKER = ITEMS.register("soulsucker", () -> new Item(new Item.Properties().fireResistant()));

    //FISH_BUCKET
    public static final DeferredItem<Item> LAVA_PUFFERFISH_BUCKET = ITEMS.register("lava_pufferfish_bucket", () -> new FishBucketItem(NDUEntity.LAVA_PUFFERFISH.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));
    public static final DeferredItem<Item> OBSIDIANFISH_BUCKET = ITEMS.register("obsidianfish_bucket", () -> new FishBucketItem(NDUEntity.OBSIDIAN_FISH.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));
    public static final DeferredItem<Item> SEARING_COD_BUCKET = ITEMS.register("searing_cod_bucket", () -> new FishBucketItem(NDUEntity.SEARING_COD.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));
    public static final DeferredItem<Item> BONEFISH_BUCKET = ITEMS.register("bonefish_bucket", () -> new FishBucketItem(NDUEntity.BONEFISH.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));
    public static final DeferredItem<Item> WITHER_BONEFISH_BUCKET = ITEMS.register("wither_bonefish_bucket", () -> new FishBucketItem(NDUEntity.WITHER_BONEFISH.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));
    public static final DeferredItem<Item> BLAZEFISH_BUCKET = ITEMS.register("blazefish_bucket", () -> new FishBucketItem(NDUEntity.BLAZEFISH.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));
    public static final DeferredItem<Item> MAGMACUBEFISH_BUCKET = ITEMS.register("magmacubefish_bucket", () -> new FishBucketItem(NDUEntity.MAGMACUBEFISH.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));
    public static final DeferredItem<Item> GLOWDINE_BUCKET = ITEMS.register("glowdine_bucket", () -> new FishBucketItem(NDUEntity.GLOWDINE.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));
    public static final DeferredItem<Item> SOULSUCKER_BUCKET = ITEMS.register("soulsucker_bucket", () -> new FishBucketItem(NDUEntity.SOULSUCKER.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));


    //Spawn Eggs
    public static final DeferredItem<Item> LAVA_PUFFERFISH_SPAWN_EGG = ITEMS.register("lava_pufferfish_spawn_egg", () -> new DeferredSpawnEggItem(() -> NDUEntity.LAVA_PUFFERFISH.get(),
            0xf47c7c, 0xE01313, new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> OBSIDIANFISH_SPAWN_EGG = ITEMS.register("obsidianfish_spawn_egg", () -> new DeferredSpawnEggItem(() ->NDUEntity.OBSIDIAN_FISH.get(),
            0x000001, 0x3b2754, new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> SEARING_COD_SPAWN_EGG = ITEMS.register("searing_cod_spawn_egg", () -> new DeferredSpawnEggItem(() ->NDUEntity.SEARING_COD.get(),
            0xe35507, 0xfb5e07, new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> BONEFISH_SPAWN_EGG = ITEMS.register("bonefish_spawn_egg", () -> new DeferredSpawnEggItem(() ->NDUEntity.BONEFISH.get(),
            12698049, 4802889, new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> WITHER_BONEFISH_SPAWN_EGG = ITEMS.register("wither_bonefish_spawn_egg", () -> new DeferredSpawnEggItem(() ->NDUEntity.WITHER_BONEFISH.get(),
            1315860, 4672845, new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> BLAZEFISH_SPAWN_EGG = ITEMS.register("blazefish_spawn_egg", () -> new DeferredSpawnEggItem(() ->NDUEntity.BLAZEFISH.get(),
            16167425, 16775294, new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> MAGMACUBEFISH_SPAWN_EGG = ITEMS.register("magmacubefish_spawn_egg", () -> new DeferredSpawnEggItem(() ->NDUEntity.MAGMACUBEFISH.get(),
            3407872, 16579584, new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> GLOWDINE_SPAWN_EGG = ITEMS.register("glowdine_spawn_egg", () -> new DeferredSpawnEggItem(() ->NDUEntity.GLOWDINE.get(),
            0xfbda74, 0xcc8654, new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> SOULSUCKER_SPAWN_EGG = ITEMS.register("soulsucker_spawn_egg", () -> new DeferredSpawnEggItem(() ->NDUEntity.SOULSUCKER.get(),
            0x796152, 0xcc8654, new Item.Properties().fireResistant()));

    ////FISH
    public static final DeferredItem<Item> FORTRESS_GROUPER = (DeferredItem<Item>) ITEMS.register("fortress_grouper", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.FORTRESS_GROUPER)));

    //FISH_BUCKET
    public static final DeferredItem<Item> FORTRESS_GROUPER_BUCKET = ITEMS.register("fortress_grouper_bucket", () -> new FishBucketItem(NDUEntity.FORTRESS_GROUPER.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));

    //Spawn Eggs
    public static final DeferredItem<Item> FORTRESS_GROUPER_SPAWN_EGG = ITEMS.register("fortress_grouper_spawn_egg", () -> new DeferredSpawnEggItem(() ->NDUEntity.FORTRESS_GROUPER.get(),
            0x38181e, 0xf09418, new Item.Properties().fireResistant()));

    //FISH
    //public static final DeferredItem<Item> NETHER_URCHIN = ITEMS.register("nether_urchin", () -> new Item(new Item.Properties().fireResistant()
    //        .food(NDUFoods.FORTRESS_GROUPER)));
    //
    ////FISH_BUCKET
    //public static final DeferredItem<Item> NETHER_URCHIN_BUCKET = ITEMS.register("nether_urchin_bucket", () -> new FishBucketItem(NDUEntity.NETHER_URCHIN, Fluids.LAVA, (new Item.Properties().fireResistant())));
    //
    ////Spawn Eggs
    //public static final DeferredItem<Item> NETHER_URCHIN_SPAWN_EGG = ITEMS.register("nether_urchin_spawn_egg", () -> new DeferredSpawnEggItem(NDUEntity.NETHER_URCHIN,
    //        0x5a0000, 0x7b0000, new Item.Properties().fireResistant()));


    //FISH
    public static final DeferredItem<Item> EYEBALL_FISH = ITEMS.register("eyeball_fish", () -> new Item(new Item.Properties().fireResistant()
            .food(NDUFoods.EYEBALL_FISH)));

    //FISH_BUCKET
    public static final DeferredItem<Item> EYEBALL_FISH_BUCKET = ITEMS.register("eyeball_fish_bucket", () -> new FishBucketItem(NDUEntity.EYEBALL_FISH.get(), Fluids.LAVA, (new Item.Properties().fireResistant())));

    //Spawn Eggs
    public static final DeferredItem<Item> EYEBALL_FISH_SPAWN_EGG = ITEMS.register("eyeball_fish_spawn_egg", () -> new DeferredSpawnEggItem(() -> NDUEntity.EYEBALL_FISH.get(),
            0x8a1010, 0xf0d57b, new Item.Properties().fireResistant()));



    //FISHINGROD
    public static final DeferredItem<LavaFishingRodItem> LAVA_FISHING_ROD = ITEMS.register("lava_fishing_rod", () -> new LavaFishingRodItem(new Item.Properties().fireResistant().durability(256)));

    public static <B extends Block> DeferredItem<BlockItem> fromBlock(DeferredBlock<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(),new Item.Properties()));
    }

    public static <B extends Block> DeferredItem<BlockItem> fromBlockFireRes(DeferredBlock<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().fireResistant()));
    }

    public static <B extends Block> DeferredItem<BurnableBlockItem> fromBlockFireResFuel(DeferredBlock<B> block, int burntime) {
        return ITEMS.register(block.getId().getPath(), () -> new BurnableBlockItem(block.get(), new Item.Properties().fireResistant(), burntime));
    }

}
