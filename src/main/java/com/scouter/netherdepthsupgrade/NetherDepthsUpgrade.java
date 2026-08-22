package com.scouter.netherdepthsupgrade;

import com.scouter.netherdepthsupgrade.config.NetherDepthsUpgradeConfig;
import com.scouter.netherdepthsupgrade.items.NDUItems;
import com.scouter.netherdepthsupgrade.modcompat.ModChecker;
import com.scouter.netherdepthsupgrade.potion.NDUPotions;
import com.scouter.netherdepthsupgrade.setup.Registration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class NetherDepthsUpgrade implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MODID = "netherdepthsupgrade";
	public static final Logger LOGGER = LoggerFactory.getLogger("netherdepthsupgrade");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		NetherDepthsUpgradeConfig.registerConfigs();

		Registration.init();

		registerBrewingRecipes();
		//GeckoLib.initialize();
		ModChecker.setupModCompatPreInit();
		
	}
	public static void registerBrewingRecipes(){
		FabricBrewingRecipeRegistryBuilder.BUILD.register((e) -> {
			e.addMix(Potions.AWKWARD, NDUItems.LAVA_PUFFERFISH, NDUPotions.WITHER);
			e.addMix(NDUPotions.WITHER, Items.REDSTONE, NDUPotions.LONG_WITHER);
			e.addMix(Potions.AWKWARD, NDUItems.EYEBALL_FISH_EYE, NDUPotions.LAVA_VISION);
			e.addMix(Potions.AWKWARD, NDUItems.EYEBALL_FISH, NDUPotions.LAVA_VISION);
			e.addMix(Potions.AWKWARD, NDUItems.OBSIDIANFISH, NDUPotions.RESISTANCE);
			e.addMix(Potions.AWKWARD, NDUItems.GLOWDINE, NDUPotions.GLOWING);
			e.addMix(NDUPotions.GLOWING, Items.REDSTONE, NDUPotions.LONG_GLOWING);
			e.addMix(NDUPotions.RESISTANCE, Items.REDSTONE, NDUPotions.LONG_RESISTANCE);
			e.addMix(NDUPotions.RESISTANCE, Items.GLOWSTONE_DUST, NDUPotions.STRONG_RESISTANCE);
			e.addMix(NDUPotions.LAVA_VISION, Items.REDSTONE, NDUPotions.LONG_LAVA_VISION);
		});

	}

	public static ResourceLocation prefix(String name) {
		return  ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
	}


}
