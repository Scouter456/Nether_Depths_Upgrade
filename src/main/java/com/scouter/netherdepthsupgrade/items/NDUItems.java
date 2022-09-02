package com.scouter.netherdepthsupgrade.items;


import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import com.scouter.netherdepthsupgrade.setup.Registration;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



//import static com.scouter.monsterfood.setup.Registration.fromBlock;

public class NDUItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NetherDepthsUpgrade.MODID);

    //From Blocks
    public static final RegistryObject<Item> LAVA_SPONGE = fromBlock(NDUBlocks.LAVA_SPONGE);
    public static final RegistryObject<Item> WET_LAVA_SPONGE = fromBlock(NDUBlocks.WET_LAVA_SPONGE);
    public static final RegistryObject<Item> WARPED_KELP = fromBlockFireRes(NDUBlocks.WARPED_KELP);
    //public static final RegistryObject<Item> LAVA_KELP_PLANT = fromBlockFireRes(NDUBlocks.LAVA_KELP_PLANT);
    public static final RegistryObject<Item> WARPED_SEAGRASS = fromBlockFireRes(NDUBlocks.WARPED_SEAGRASS);
    public static final RegistryObject<Item> WARPED_KELP_BLOCK = fromBlockFireResFuel(NDUBlocks.WARPED_KELP_BLOCK);
    //public static final RegistryObject<Item> TALL_LAVA_SEAGRASS = fromBlockFireRes(NDUBlocks.TALL_LAVA_SEAGRASS);



    //FISH
    public static final RegistryObject<Item> LAVA_PUFFERFISH = ITEMS.register("lava_pufferfish", () -> new Item(Registration.fishBuilder().fireResistant()));
    public static final RegistryObject<Item> OBSIDIANFISH = ITEMS.register("obsidianfish", () -> new Item(Registration.fishBuilder().fireResistant()));
    public static final RegistryObject<Item> SEARING_COD = ITEMS.register("searing_cod", () -> new Item(Registration.fishBuilder().fireResistant()));
    public static final RegistryObject<Item> BONEFISH = ITEMS.register("bonefish", () -> new Item(Registration.fishBuilder().fireResistant()));
    public static final RegistryObject<Item> WITHER_BONEFISH = ITEMS.register("wither_bonefish", () -> new Item(Registration.fishBuilder().fireResistant()));
    public static final RegistryObject<Item> BLAZEFISH = ITEMS.register("blazefish", () -> new Item(Registration.fishBuilder().fireResistant()));
    public static final RegistryObject<Item> MAGMACUBEFISH = ITEMS.register("magmacubefish", () -> new Item(Registration.fishBuilder().fireResistant()));
    public static final RegistryObject<Item> GLOWDINE = ITEMS.register("glowdine", () -> new Item(Registration.fishBuilder().fireResistant()));
    public static final RegistryObject<Item> SOULSUCKER = ITEMS.register("soulsucker", () -> new Item(Registration.fishBuilder().fireResistant()));

    //FISH_BUCKET
    public static final RegistryObject<Item> LAVA_PUFFERFISH_BUCKET = ITEMS.register("lava_pufferfish_bucket", () -> new FishBucketItem(NDUEntity.LAVA_PUFFERFISH, Fluids.LAVA, (Registration.defaultBuilder())));
    public static final RegistryObject<Item> OBSIDIANFISH_BUCKET = ITEMS.register("obsidianfish_bucket", () -> new FishBucketItem(NDUEntity.OBSIDIAN_FISH, Fluids.LAVA, (Registration.defaultBuilder())));
    public static final RegistryObject<Item> SEARING_COD_BUCKET = ITEMS.register("searing_cod_bucket", () -> new FishBucketItem(NDUEntity.SEARING_COD, Fluids.LAVA, (Registration.defaultBuilder())));
    public static final RegistryObject<Item> BONEFISH_BUCKET = ITEMS.register("bonefish_bucket", () -> new FishBucketItem(NDUEntity.BONEFISH, Fluids.LAVA, (Registration.defaultBuilder())));
    public static final RegistryObject<Item> WITHER_BONEFISH_BUCKET = ITEMS.register("wither_bonefish_bucket", () -> new FishBucketItem(NDUEntity.WITHER_BONEFISH, Fluids.LAVA, (Registration.defaultBuilder())));
    public static final RegistryObject<Item> BLAZEFISH_BUCKET = ITEMS.register("blazefish_bucket", () -> new FishBucketItem(NDUEntity.BLAZEFISH, Fluids.LAVA, (Registration.defaultBuilder())));
    public static final RegistryObject<Item> MAGMACUBEFISH_BUCKET = ITEMS.register("magmacubefish_bucket", () -> new FishBucketItem(NDUEntity.MAGMACUBEFISH, Fluids.LAVA, (Registration.defaultBuilder())));
    public static final RegistryObject<Item> GLOWDINE_BUCKET = ITEMS.register("glowdine_bucket", () -> new FishBucketItem(NDUEntity.GLOWDINE, Fluids.LAVA, (Registration.defaultBuilder())));
    public static final RegistryObject<Item> SOULSUCKER_BUCKET = ITEMS.register("soulsucker_bucket", () -> new FishBucketItem(NDUEntity.SOULSUCKER, Fluids.LAVA, (Registration.defaultBuilder())));


    //Spawn Eggs
    public static final RegistryObject<Item> LAVA_PUFFERFISH_SPAWN_EGG = ITEMS.register("lava_pufferfish_spawn_egg", ()-> new ForgeSpawnEggItem(NDUEntity.LAVA_PUFFERFISH,
            0xFFF6F6, 0xE01313, Registration.defaultBuilder()));

    public static final RegistryObject<Item> OBSIDIANFISH_SPAWN_EGG = ITEMS.register("obsidianfish_spawn_egg", ()-> new ForgeSpawnEggItem(NDUEntity.OBSIDIAN_FISH,
            0xFFF6F6, 0xE01313, Registration.defaultBuilder()));

    public static final RegistryObject<Item> SEARING_COD_SPAWN_EGG = ITEMS.register("searing_cod_spawn_egg", ()-> new ForgeSpawnEggItem(NDUEntity.SEARING_COD,
            0xFFF6F6, 0xE01313, Registration.defaultBuilder()));

    public static final RegistryObject<Item> BONEFISH_SPAWN_EGG = ITEMS.register("bonefish_spawn_egg", ()-> new ForgeSpawnEggItem(NDUEntity.BONEFISH,
            0xFFF6F6, 0xE01313, Registration.defaultBuilder()));

    public static final RegistryObject<Item> WITHER_BONEFISH_SPAWN_EGG = ITEMS.register("wither_bonefish_spawn_egg", ()-> new ForgeSpawnEggItem(NDUEntity.WITHER_BONEFISH,
            0xFFF6F6, 0xE01313, Registration.defaultBuilder()));

    public static final RegistryObject<Item> BLAZEFISH_SPAWN_EGG = ITEMS.register("blazefish_spawn_egg", ()-> new ForgeSpawnEggItem(NDUEntity.BLAZEFISH,
            0xFFF6F6, 0xE01313, Registration.defaultBuilder()));

    public static final RegistryObject<Item> MAGMACUBEFISH_SPAWN_EGG = ITEMS.register("magmacubefish_spawn_egg", ()-> new ForgeSpawnEggItem(NDUEntity.MAGMACUBEFISH,
            0xFFF6F6, 0xE01313, Registration.defaultBuilder()));

    public static final RegistryObject<Item> GLOWDINE_SPAWN_EGG = ITEMS.register("glowdine_spawn_egg", ()-> new ForgeSpawnEggItem(NDUEntity.GLOWDINE,
            0xFFF6F6, 0xE01313, Registration.defaultBuilder()));

    public static final RegistryObject<Item> SOULSUCKER_SPAWN_EGG = ITEMS.register("soulsucker_spawn_egg", ()-> new ForgeSpawnEggItem(NDUEntity.SOULSUCKER,
            0xFFF6F6, 0xE01313, Registration.defaultBuilder()));
    public static final RegistryObject<Item> LAVA_FISHING_ROD = ITEMS.register("lava_fishing_rod", () -> new LavaFishingRod((Registration.defaultBuilder()).durability(128)));

    public static CreativeModeTab creativeTab = new CreativeModeTab(NetherDepthsUpgrade.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(WARPED_KELP.get());
        }
    };

    public static CreativeModeTab creativeTabFish = new CreativeModeTab(NetherDepthsUpgrade.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SEARING_COD.get());
        }
    };

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block){
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), Registration.defaultBuilder()));
    }
    public static <B extends Block> RegistryObject<Item> fromBlockFireRes(RegistryObject<B> block){
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), Registration.defaultBuilder().fireResistant()));
    }

    public static <B extends Block> RegistryObject<Item> fromBlockFireResFuel(RegistryObject<B> block){
        return ITEMS.register(block.getId().getPath(), () -> new BurnableBlockItem(block.get(), Registration.defaultBuilder().fireResistant()));
    }
    public static <B extends Block> RegistryObject<Item> fromBlockToFood(RegistryObject<B> block){
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), Registration.fishBuilder().stacksTo(1)));
    }
}
