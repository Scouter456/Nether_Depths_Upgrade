package com.scouter.netherdepthsupgrade.structures;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDUStructures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> NETHER_FORTRESS_PIECE = STRUCTURES.register("nether_fortress_piece", () -> (new NetherFortressPiece()));
}
