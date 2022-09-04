package com.scouter.netherdepthsupgrade.structures;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDUStructures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> NETHER_FORTRESS_PIECE = STRUCTURES.register("nether_fortress_piece", () -> (new NetherFortressPiece()));

    public static StructurePieceType LAVA_STRUCTURE_PIECE;

    public static void setupStructures(final RegistryEvent<StructureFeature<?>> event) {
        LAVA_STRUCTURE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(NetherDepthsUpgrade.MODID, "lava_structure_piece"), LavaStructurePiece::new);
    }
}
