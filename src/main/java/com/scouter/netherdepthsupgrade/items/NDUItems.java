package com.scouter.netherdepthsupgrade.items;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.modcompat.InfernalExpansionCompat;
import com.scouter.netherdepthsupgrade.setup.Registration;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;


public class NDUItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NetherDepthsUpgrade.MODID);

    //From Blocks
    public static final RegistryObject<Item> LAVA_SPONGE = fromBlockFireRes(NDUBlocks.LAVA_SPONGE);
    public static final RegistryObject<Item> WET_LAVA_SPONGE = fromBlockFireRes(NDUBlocks.WET_LAVA_SPONGE);
    public static final RegistryObject<Item> WARPED_KELP = fromBlockFireRes(NDUBlocks.WARPED_KELP);
    public static final RegistryObject<Item> WARPED_SEAGRASS = fromBlockFireRes(NDUBlocks.WARPED_SEAGRASS);

    public static final RegistryObject<Item> WARPED_KELP_BLOCK = fromBlockFireResFuel(NDUBlocks.WARPED_KELP_BLOCK, 6400);
    public static final RegistryObject<Item> WARPED_KELP_CARPET_BLOCK = fromBlockFireRes(NDUBlocks.WARPED_KELP_CARPET_BLOCK);

    public static final RegistryObject<Item> CRIMSON_KELP = fromBlockFireRes(NDUBlocks.CRIMSON_KELP);
    public static final RegistryObject<Item> CRIMSON_SEAGRASS = fromBlockFireRes(NDUBlocks.CRIMSON_SEAGRASS);

    public static final RegistryObject<Item> CRIMSON_KELP_BLOCK = fromBlockFireResFuel(NDUBlocks.CRIMSON_KELP_BLOCK, 6400);
    public static final RegistryObject<Item> CRIMSON_KELP_CARPET_BLOCK = fromBlockFireRes(NDUBlocks.CRIMSON_KELP_CARPET_BLOCK);

    public static final RegistryObject<Item> LAVA_GLASS = fromBlockFireRes(NDUBlocks.LAVA_GLASS);
    //ITEM
    public static final RegistryObject<Item> SOUL_SUCKER_LEATHER = ITEMS.register("soul_sucker_leather", () -> new Item(Registration.defaultBuilder().fireResistant()));

    public static final RegistryObject<Item> FORTRESS_GROUPER_PLATE = ITEMS.register("fortress_grouper_plate", () -> new Item(Registration.defaultBuilder().fireResistant()));
    public static final RegistryObject<Item> EYEBALL_FISH_EYE = ITEMS.register("eyeball", () -> new Item(Registration.defaultBuilder().fireResistant()));

    //ARMOR
    public static final RegistryObject<ArmorItem> SOUL_SUCKER_BOOTS = ITEMS.register("soul_sucker_boots", () -> new SoulSuckerArmorItem(NDUMaterialInit.SOUL_SUCKER, EquipmentSlot.FEET, Registration.defaultBuilder().fireResistant()));


    //FISH
    public static final RegistryObject<Item> LAVA_PUFFERFISH = ITEMS.register("lava_pufferfish", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(NDUFoods.LAVA_PUFFERFISH)));
    public static final RegistryObject<Item> OBSIDIANFISH = ITEMS.register("obsidianfish", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(NDUFoods.OBSIDIANFISH)));
    public static final RegistryObject<Item> SEARING_COD = ITEMS.register("searing_cod", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(NDUFoods.SEARING_COD)){
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            return 3200;
        }
    });
    public static final RegistryObject<Item> BONEFISH = ITEMS.register("bonefish", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(NDUFoods.BONEFISH)));
    public static final RegistryObject<Item> WITHER_BONEFISH = ITEMS.register("wither_bonefish", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(NDUFoods.WITHER_BONEFISH)));
    public static final RegistryObject<Item> BLAZEFISH = ITEMS.register("blazefish", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(NDUFoods.BLAZEFISH)));
    public static final RegistryObject<Item> MAGMACUBEFISH = ITEMS.register("magmacubefish", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(NDUFoods.MAGMACUBEFISH)));
    public static final RegistryObject<Item> GLOWDINE = ITEMS.register("glowdine", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(InfernalExpansionCompat.getGlowdineFood().get())));
    public static final RegistryObject<Item> SOULSUCKER = ITEMS.register("soulsucker", () -> new Item(Registration.fishBuilder().fireResistant()));

    //FISH_BUCKET
    public static final RegistryObject<Item> LAVA_PUFFERFISH_BUCKET = ITEMS.register("lava_pufferfish_bucket", () -> new FishBucketItem(NDUEntity.LAVA_PUFFERFISH, Fluids.LAVA, (Registration.fishBuilder())));
    public static final RegistryObject<Item> OBSIDIANFISH_BUCKET = ITEMS.register("obsidianfish_bucket", () -> new FishBucketItem(NDUEntity.OBSIDIAN_FISH, Fluids.LAVA, (Registration.fishBuilder())));
    public static final RegistryObject<Item> SEARING_COD_BUCKET = ITEMS.register("searing_cod_bucket", () -> new FishBucketItem(NDUEntity.SEARING_COD, Fluids.LAVA, (Registration.fishBuilder())));
    public static final RegistryObject<Item> BONEFISH_BUCKET = ITEMS.register("bonefish_bucket", () -> new FishBucketItem(NDUEntity.BONEFISH, Fluids.LAVA, (Registration.fishBuilder())));
    public static final RegistryObject<Item> WITHER_BONEFISH_BUCKET = ITEMS.register("wither_bonefish_bucket", () -> new FishBucketItem(NDUEntity.WITHER_BONEFISH, Fluids.LAVA, (Registration.fishBuilder())));
    public static final RegistryObject<Item> BLAZEFISH_BUCKET = ITEMS.register("blazefish_bucket", () -> new FishBucketItem(NDUEntity.BLAZEFISH, Fluids.LAVA, (Registration.fishBuilder())));
    public static final RegistryObject<Item> MAGMACUBEFISH_BUCKET = ITEMS.register("magmacubefish_bucket", () -> new FishBucketItem(NDUEntity.MAGMACUBEFISH, Fluids.LAVA, (Registration.fishBuilder())));
    public static final RegistryObject<Item> GLOWDINE_BUCKET = ITEMS.register("glowdine_bucket", () -> new FishBucketItem(NDUEntity.GLOWDINE, Fluids.LAVA, (Registration.fishBuilder())));
    public static final RegistryObject<Item> SOULSUCKER_BUCKET = ITEMS.register("soulsucker_bucket", () -> new FishBucketItem(NDUEntity.SOULSUCKER, Fluids.LAVA, (Registration.fishBuilder())));


    //Spawn Eggs
    public static final RegistryObject<Item> LAVA_PUFFERFISH_SPAWN_EGG = ITEMS.register("lava_pufferfish_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.LAVA_PUFFERFISH,
            0xf47c7c, 0xE01313, Registration.fishBuilder()));

    public static final RegistryObject<Item> OBSIDIANFISH_SPAWN_EGG = ITEMS.register("obsidianfish_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.OBSIDIAN_FISH,
            0x000001, 0x3b2754, Registration.fishBuilder()));

    public static final RegistryObject<Item> SEARING_COD_SPAWN_EGG = ITEMS.register("searing_cod_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.SEARING_COD,
            0xe35507, 0xfb5e07, Registration.fishBuilder()));

    public static final RegistryObject<Item> BONEFISH_SPAWN_EGG = ITEMS.register("bonefish_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.BONEFISH,
            12698049, 4802889, Registration.fishBuilder()));

    public static final RegistryObject<Item> WITHER_BONEFISH_SPAWN_EGG = ITEMS.register("wither_bonefish_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.WITHER_BONEFISH,
            1315860, 4672845, Registration.fishBuilder()));

    public static final RegistryObject<Item> BLAZEFISH_SPAWN_EGG = ITEMS.register("blazefish_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.BLAZEFISH,
            16167425, 16775294, Registration.fishBuilder()));

    public static final RegistryObject<Item> MAGMACUBEFISH_SPAWN_EGG = ITEMS.register("magmacubefish_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.MAGMACUBEFISH,
            3407872, 16579584, Registration.fishBuilder()));

    public static final RegistryObject<Item> GLOWDINE_SPAWN_EGG = ITEMS.register("glowdine_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.GLOWDINE,
            0xfbda74, 0xcc8654, Registration.fishBuilder()));

    public static final RegistryObject<Item> SOULSUCKER_SPAWN_EGG = ITEMS.register("soulsucker_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.SOULSUCKER,
            0x796152, 0xcc8654, Registration.fishBuilder()));

    ////FISH
    public static final RegistryObject<Item> FORTRESS_GROUPER = ITEMS.register("fortress_grouper", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(NDUFoods.FORTRESS_GROUPER)));

    //FISH_BUCKET
    public static final RegistryObject<Item> FORTRESS_GROUPER_BUCKET = ITEMS.register("fortress_grouper_bucket", () -> new FishBucketItem(NDUEntity.FORTRESS_GROUPER, Fluids.LAVA, (Registration.fishBuilder())));

    //Spawn Eggs
    public static final RegistryObject<Item> FORTRESS_GROUPER_SPAWN_EGG = ITEMS.register("fortress_grouper_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.FORTRESS_GROUPER,
            0x38181e, 0xf09418, Registration.fishBuilder()));

    //FISH
    //public static final RegistryObject<Item> NETHER_URCHIN = ITEMS.register("nether_urchin", () -> new Item(Registration.fishBuilder().fireResistant()
    //        .food(NDUFoods.FORTRESS_GROUPER)));
    //
    ////FISH_BUCKET
    //public static final RegistryObject<Item> NETHER_URCHIN_BUCKET = ITEMS.register("nether_urchin_bucket", () -> new FishBucketItem(NDUEntity.NETHER_URCHIN, Fluids.LAVA, (Registration.fishBuilder())));
    //
    ////Spawn Eggs
    //public static final RegistryObject<Item> NETHER_URCHIN_SPAWN_EGG = ITEMS.register("nether_urchin_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.NETHER_URCHIN,
    //        0x5a0000, 0x7b0000, Registration.fishBuilder()));


    //FISH
    public static final RegistryObject<Item> EYEBALL_FISH = ITEMS.register("eyeball_fish", () -> new Item(Registration.fishBuilder().fireResistant()
            .food(NDUFoods.EYEBALL_FISH)));

    //FISH_BUCKET
    public static final RegistryObject<Item> EYEBALL_FISH_BUCKET = ITEMS.register("eyeball_fish_bucket", () -> new FishBucketItem(NDUEntity.EYEBALL_FISH, Fluids.LAVA, (Registration.fishBuilder())));

    //Spawn Eggs
    public static final RegistryObject<Item> EYEBALL_FISH_SPAWN_EGG = ITEMS.register("eyeball_fish_spawn_egg", () -> new ForgeSpawnEggItem(NDUEntity.EYEBALL_FISH,
            0x8a1010, 0xf0d57b, Registration.fishBuilder()));



    //FISHINGROD
    public static final RegistryObject<Item> LAVA_FISHING_ROD = ITEMS.register("lava_fishing_rod", () -> new LavaFishingRodItem(Registration.defaultBuilder().fireResistant().defaultDurability(256)));

    //ADV potion
    public static CreativeModeTab creativeTab = new CreativeModeTab("netherdepthsupgrade") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(WARPED_KELP.get());
        }
    };

    public static CreativeModeTab creativeTabFish = new CreativeModeTab("netherdepthsupgrade_fish") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SEARING_COD.get());
        }
    };

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), Registration.defaultBuilder()));
    }

    public static <B extends Block> RegistryObject<Item> fromBlockFireRes(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), Registration.defaultBuilder().fireResistant()));
    }

    public static <B extends Block> RegistryObject<Item> fromBlockFireResFuel(RegistryObject<B> block, int burntime) {
        return ITEMS.register(block.getId().getPath(), () -> new BurnableBlockItem(block.get(), Registration.defaultBuilder().fireResistant(), burntime));
    }
}
