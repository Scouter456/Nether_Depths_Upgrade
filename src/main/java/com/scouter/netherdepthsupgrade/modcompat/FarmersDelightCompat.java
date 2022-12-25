package com.scouter.netherdepthsupgrade.modcompat;

import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.items.NDUDescriptionItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import vectorwing.farmersdelight.common.FoodValues;

public class FarmersDelightCompat {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void setupCompat() {
        LOGGER.info("Setting up compat for Farmers Delight");

        ModChecker.farmersDelightPresent = true;

    }

    public static final Item.Properties foodBuilder() {
        return new Item.Properties().tab(creativeTabFood);
    }


    public static final DeferredRegister<Item> ITEMS_FARMERS_DELIGHT = DeferredRegister.create(ForgeRegistries.ITEMS, NetherDepthsUpgrade.MODID);
    //SLICES
    public static final RegistryObject<Item> LAVA_PUFFERFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("lava_pufferfish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.LAVA_PUFFERFISH_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.lava_pufferfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> OBSIDIANFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("obsidianfish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.OBSIDIANFISH_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.obsidianfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> SEARING_COD_SLICE = ITEMS_FARMERS_DELIGHT.register("searing_cod_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SEARING_COD_SLICE),
            new TranslatableComponent("item.netherdepthsupgrade.searing_cod_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BLAZEFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("blazefish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BLAZEFISH_SLICE),
            new TranslatableComponent("item.netherdepthsupgrade.blazefish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> MAGMACUBEFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("magmacubefish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.MAGMA_CUBE_FISH_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.magmacubefish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));
    public static final RegistryObject<Item> GLOWDINE_SLICE = ITEMS_FARMERS_DELIGHT.register("glowdine_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GLOWDINE_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.glowdine_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> SOULSUCKER_SLICE = ITEMS_FARMERS_DELIGHT.register("soulsucker_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SOULSUCKER_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.soulsucker_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));


    //COOKED (after cooking)
    public static final RegistryObject<Item> COOKED_LAVA_PUFFERFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_lava_pufferfish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_LAVA_PUFFERFISH_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.cooked_lava_pufferfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> COOKED_OBSIDIANFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_obsidianfish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_OBSIDIANFISH_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.cooked_obsidianfish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> COOKED_MAGMACUBEFISH_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_magmacubefish_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_MAGMA_CUBE_FISH_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.cooked_magmacubefish_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> COOKED_GLOWDINE_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_glowdine_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_GLOWDINE_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.cooked_glowdine_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> COOKED_SOULSUCKER_SLICE = ITEMS_FARMERS_DELIGHT.register("cooked_soulsucker_slice", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.COOKED_SOULSUCKER_SLICE)
            , new TranslatableComponent("item.netherdepthsupgrade.cooked_soulsucker_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    //RICE ROLLS
    public static final RegistryObject<Item> LAVA_PUFFERFISH_ROLL = ITEMS_FARMERS_DELIGHT.register("lava_pufferfish_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.LAVA_PUFFERFISH_ROLL),
                    new TranslatableComponent("item.netherdepthsupgrade.lava_pufferfish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> OBSIDIANFISH_ROLL = ITEMS_FARMERS_DELIGHT.register("obsidianfish_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.OBSIDIANFISH_ROLL),
                    new TranslatableComponent("item.netherdepthsupgrade.obsidianfish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> SEARING_COD_ROLL = ITEMS_FARMERS_DELIGHT.register("searing_cod_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SEARING_COD_ROLL),
                    new TranslatableComponent("item.netherdepthsupgrade.searing_cod_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BLAZEFISH_ROLL = ITEMS_FARMERS_DELIGHT.register("blazefish_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BLAZEFISH_ROLL),
                    new TranslatableComponent("item.netherdepthsupgrade.blazefish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> MAGMA_CUBE_FISH_ROLL = ITEMS_FARMERS_DELIGHT.register("magmacubefish_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.MAGMA_CUBE_FISH_ROLL),
                    new TranslatableComponent("item.netherdepthsupgrade.magmacubefish_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GLOWDINE_ROLL = ITEMS_FARMERS_DELIGHT.register("glowdine_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GLOWDINE_ROLL),
                    new TranslatableComponent("item.netherdepthsupgrade.glowdine_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> SOULSUCKER_ROLL = ITEMS_FARMERS_DELIGHT.register("soulsucker_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.SOULSUCKER_ROLL),
                    new TranslatableComponent("item.netherdepthsupgrade.soulsucker_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> WARPED_KELP_ROLL = ITEMS_FARMERS_DELIGHT.register("warped_kelp_roll",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.WARPED_KELP_ROLL),
                    new TranslatableComponent("item.netherdepthsupgrade.warped_kelp_roll.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));
    public static final RegistryObject<Item> WARPED_KELP_ROLL_SLICE = ITEMS_FARMERS_DELIGHT.register("warped_kelp_roll_slice",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.WARPED_KELP_ROLL_SLICE),
                    new TranslatableComponent("item.netherdepthsupgrade.warped_kelp_roll_slice.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    //GRILLED FOODS
    public static final RegistryObject<Item> GRILLED_LAVA_PUFFERFISH = ITEMS_FARMERS_DELIGHT.register("grilled_lava_pufferfish",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_LAVA_PUFFERFISH),
                    new TranslatableComponent("item.netherdepthsupgrade.grilled_lava_pufferfish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_OBSIDIANFISH = ITEMS_FARMERS_DELIGHT.register("grilled_obsidianfish",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_OBSIDIANFISH),
                    new TranslatableComponent("item.netherdepthsupgrade.grilled_obsidianfish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_SEARING_COD = ITEMS_FARMERS_DELIGHT.register("grilled_searing_cod",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_SEARING_COD),
                    new TranslatableComponent("item.netherdepthsupgrade.grilled_searing_cod.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_BLAZEFISH = ITEMS_FARMERS_DELIGHT.register("grilled_blazefish",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_BLAZEFISH),
                    new TranslatableComponent("item.netherdepthsupgrade.grilled_blazefish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_MAGMA_CUBE_FISH = ITEMS_FARMERS_DELIGHT.register("grilled_magmacubefish",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_MAGMA_CUBE_FISH),
                    new TranslatableComponent("item.netherdepthsupgrade.grilled_magmacubefish.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_GLOWDINE = ITEMS_FARMERS_DELIGHT.register("grilled_glowdine",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_GLOWDINE),
                    new TranslatableComponent("item.netherdepthsupgrade.grilled_glowdine.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> GRILLED_SOULSUCKER = ITEMS_FARMERS_DELIGHT.register("grilled_soulsucker",
            () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.GRILLED_SOULSUCKER),
                    new TranslatableComponent("item.netherdepthsupgrade.grilled_soulsucker.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));



    //STEWS
    public static final RegistryObject<Item> BAKED_LAVA_PUFFERFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_lava_pufferfish_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_LAVA_PUFFERFISH_STEW)
            , new TranslatableComponent("item.netherdepthsupgrade.baked_lava_pufferfish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_OBSIDIANFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_obsidianfish_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_OBSIDIANFISH_STEW)
            , new TranslatableComponent("item.netherdepthsupgrade.baked_obsidianfish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_SEARING_COD_STEW = ITEMS_FARMERS_DELIGHT.register("baked_searing_cod_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_SEARING_COD_STEW)
            , new TranslatableComponent("item.netherdepthsupgrade.baked_searing_cod_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_BLAZEFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_blazefish_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_BLAZEFISH_STEW)
            , new TranslatableComponent("item.netherdepthsupgrade.baked_blazefish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_MAGMACUBEFISH_STEW = ITEMS_FARMERS_DELIGHT.register("baked_magmacubefish_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_MAGMA_CUBE_FISH_STEW)
            , new TranslatableComponent("item.netherdepthsupgrade.baked_magmacubefish_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_GLOWDINE_STEW = ITEMS_FARMERS_DELIGHT.register("baked_glowdine_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_GLOWDINE_STEW)
            , new TranslatableComponent("item.netherdepthsupgrade.baked_glowdine_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));

    public static final RegistryObject<Item> BAKED_SOULSUCKER_STEW = ITEMS_FARMERS_DELIGHT.register("baked_soulsucker_stew", () -> new NDUDescriptionItem(foodBuilder().fireResistant().food(FarmersDelightCompatFoods.BAKED_SOULSUCKER_STEW)
            , new TranslatableComponent("item.netherdepthsupgrade.baked_soulsucker_stew.flavor_text").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)));


    public static CreativeModeTab creativeTabFood = new CreativeModeTab("netherdepthsupgrade_food") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SOULSUCKER_SLICE.get());
        }
    };
}
