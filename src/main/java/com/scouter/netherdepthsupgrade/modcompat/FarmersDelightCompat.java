package com.scouter.netherdepthsupgrade.modcompat;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.items.LavaFishingRodItem;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.setup.Registration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

public class FarmersDelightCompat {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void setupCompat() {
        LOGGER.info("Setting up compat for Farmers Delight");

        ModChecker.farmersDelightPresent = true;

    }

    public static final Item.Properties foodBuilder() {return new Item.Properties().tab(creativeTabFood);}

    public static final DeferredRegister<Item> ITEMS_FARMERS_DELIGHT = DeferredRegister.create(ForgeRegistries.ITEMS, NetherDepthsUpgrade.MODID);

    //COOKED (from knife)
    public static final RegistryObject<Item> BLAZEFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("blazefish_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BLAZEFISH_SLICE)));
    public static final RegistryObject<Item> SEARING_COD_SLICE = ITEMS_FARMERS_DELIGHT.register("searing_cod_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SEARING_COD_SLICE)));

    //UNCOOKED
    public static final RegistryObject<Item> SOULSUCKER_SLICE = ITEMS_FARMERS_DELIGHT.register("soulsucker_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SOULSUCKER_SLICE)));
    public static final RegistryObject<Item> OBSIDIANFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("obsidianfish_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.OBSIDIANFISH_SLICE)));
    public static final RegistryObject<Item> MAGMACUBEFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("magmacubefish_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.MAGMA_CUBE_FISH_SLICE)));
    public static final RegistryObject<Item> GLOWDINE_SLICE = ITEMS_FARMERS_DELIGHT.register("glowdine_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GLOWDINE_SLICE)));
    public static final RegistryObject<Item> LAVA_PUFFERFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("lava_pufferfish_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.LAVA_PUFFERFISH_SLICE)));

    //COOKED (after cooking)
    public static final RegistryObject<Item> COOKED_SOULSUCKER_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_soulsucker_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_SOULSUCKER_SLICE)));
    public static final RegistryObject<Item> COOKED_OBSIDIANFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_obsidianfish_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_OBSIDIANFISH_SLICE)));
    public static final RegistryObject<Item> COOKED_MAGMACUBEFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_magmacubefish_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_MAGMA_CUBE_FISH_SLICE)));
    public static final RegistryObject<Item> COOKED_GLOWDINE_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_glowdine_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_GLOWDINE_SLICE)));
    public static final RegistryObject<Item> COOKED_LAVA_PUFFERFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_lava_pufferfish_slice", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_LAVA_PUFFERFISH_SLICE)));

    //STEWS
    public static final RegistryObject<Item> BAKED_SOULSUCKER_STEW = ITEMS_FARMERS_DELIGHT.register("baked_soulsucker_stew", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_SOULSUCKER_STEW)));
    public static final RegistryObject<Item> BAKED_OBSIDIANFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_obsidianfish_stew", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_OBSIDIANFISH_STEW)));
    public static final RegistryObject<Item> BAKED_MAGMACUBEFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_magmacubefish_stew", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_MAGMA_CUBE_FISH_STEW)));
    public static final RegistryObject<Item> BAKED_GLOWDINE_STEW = ITEMS_FARMERS_DELIGHT.register("baked_glowdine_stew", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_GLOWDINE_STEW)));
    public static final RegistryObject<Item> BAKED_LAVA_PUFFERFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_lava_pufferfish_stew", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_LAVA_PUFFERFISH_STEW)));
    public static final RegistryObject<Item> BAKED_BLAZEFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_blazefish_stew", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_BLAZEFISH_STEW)));
    public static final RegistryObject<Item> BAKED_SEARING_COD_STEW = ITEMS_FARMERS_DELIGHT.register("baked_searing_cod_stew", () -> new Item(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_SEARING_COD_STEW)));

    public static CreativeModeTab creativeTabFood = new CreativeModeTab("netherdepthsupgrade_food") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SOULSUCKER_SLICE.get());
        }
    };
}
