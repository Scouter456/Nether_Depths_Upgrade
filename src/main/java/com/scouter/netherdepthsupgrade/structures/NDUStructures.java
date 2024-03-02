package com.scouter.netherdepthsupgrade.structures;

import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class NDUStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, NetherDepthsUpgrade.MODID);
    public static final RegistryObject<StructureType<NetherFortressPiece>> NETHER_FORTRESS_PIECE = STRUCTURES.register("nether_fortress_piece", () -> () -> NetherFortressPiece.CODEC);
}
