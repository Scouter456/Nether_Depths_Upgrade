package com.scouter.netherdepthsupgrade.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.scouter.netherdepthsupgrade.entity.NDUEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.MODID;
import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;

public class NetherFortressPiece extends Structure<NoFeatureConfig> {
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    static Random rand = new Random();

    public NetherFortressPiece(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return NetherFortressPiece.Start::new;
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    private static final List<MobSpawnInfo.Spawners> STRUCTURE_FISH = ImmutableList.of(
            new MobSpawnInfo.Spawners(NDUEntity.BLAZEFISH.get(), 1500, 2, 4));

    //@Override
    //public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
    //    return STRUCTURE_FISH;
    //}
    public boolean getDefaultRestrictsSpawnsToInside(){
        return false;
    }
    //}


//    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
//        // Grabs the chunk position we are at
//        ChunkPos chunkpos = chunkPos;
//        BlockPos blockPos = new BlockPos(chunkX, 0, chunkZ);
//        // Checks to make sure our structure does not spawn within 10 chunks of an Ocean Monument
//        // to demonstrate how this method is good for checking extra conditions for spawning
//        return !chunkGenerator.findNearestMapFeature(biomeSource., Structure.NETHER_BRIDGE, blockPos, 10, false);
//    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkgenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
            // Check if the spot is valid for our structure. This is just as another method for cleanness.
            // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
            int x = chunkX * 16;
            int z = chunkZ * 16;
            int y = rand.nextInt(30) + 15;
            BlockPos centerPos = new BlockPos(x, y, z);


            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)


            BlockPos.Mutable mutable = new BlockPos.Mutable();
            ChunkGenerator chunkGenerator = chunkgenerator;
            mutable = mutable.set(centerPos);
            IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerPos.getX(), centerPos.getZ());
            BlockState state = columnOfBlocks.getBlockState(centerPos);
            if (!state.getBlockState().is(Blocks.LAVA)) {
                return;
            }

            while (state.getBlockState().is(Blocks.LAVA) || centerPos.getY() > 0) {
                centerPos = centerPos.below();
                state = columnOfBlocks.getBlockState(centerPos);
                //LOGGER.info("centerPos " + centerPos);
                if (!state.getBlockState().is(Blocks.LAVA)) {
                    break;
                }
            }
            // Set's our spawning blockpos's y offset to be 60 blocks up.
            // Since we are going to have heightmap/terrain height spawning set to true further down, this will make it so we spawn 60 blocks above terrain.
            // If we wanted to spawn on ocean floor, we would set heightmap/terrain height spawning to false and the grab the y value of the terrain with OCEAN_FLOOR_WG heightmap.

            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            .get(prefix("nether_fortress_side/start_pool")),
                            // How many pieces outward from center can a recursive jigsaw structure spawn.
                            // Our structure is only 1 piece outward and isn't recursive so any value of 1 or more doesn't change anything.
                            // However, I recommend you keep this a decent value like 10 so people can use datapacks to add additional pieces to your structure easily.
                            // But don't make it too large for recursive structures like villages or you'll crash server due to hundreds of pieces attempting to generate!
                            10),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    templateManagerIn,
                    centerPos, // Position of the structure. Y value is ignored if last parameter is set to true.
                    this.pieces, // The list that will be populated with the jigsaw pieces after this method.
                    this.random,
                    false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                    // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                    false);  // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
            // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.

            // **THE FOLLOWING LINE IS OPTIONAL**
            //
            // Right here, you can do interesting stuff with the pieces in this.pieces such as offset the
            // center piece by 50 blocks up for no reason, remove repeats of a piece or add a new piece so
            // only 1 of that piece exists, etc. But you do not have access to the piece's blocks as this list
            // holds just the piece's size and positions. Blocks will be placed much later by the game.
            //
            // In this case, we do `piece.offset` to raise pieces up by 1 block so that the house is not right on
            // the surface of water or sunken into land a bit. NOTE: land added by Structure.NOISE_AFFECTING_FEATURES
            // will also be moved alongside the piece. If you do not want this land, do not add your structure to the
            // Structure.NOISE_AFFECTING_FEATURES field and now your pieces can be set on the regular terrain instead.
            this.pieces.forEach(piece -> piece.move(0, 1, 0));

            // Since by default, the start piece of a structure spawns with it's corner at centerPos
            // and will randomly rotate around that corner, we will center the piece on centerPos instead.
            // This is so that our structure's start piece is now centered on the water check done in isFeatureChunk.
            // Whatever the offset done to center the start piece, that offset is applied to all other pieces
            // so the entire structure is shifted properly to the new spot.
            Vector3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
            int xOffset = centerPos.getX() - structureCenter.getX();
            int zOffset = centerPos.getZ() - structureCenter.getZ();
            for (StructurePiece structurePiece : this.pieces) {
                structurePiece.move(xOffset, 0, zOffset);
            }
            this.calculateBoundingBox();

            /*
             * Note, you are always free to make your own JigsawPlacement class and implementation of how the structure
             * should generate. It is tricky but extremely powerful if you are doing something that vanilla's jigsaw system cannot do.
             * Such as for example, forcing 3 pieces to always spawn every time, limiting how often a piece spawns, or remove the intersection limitation of pieces.
             *
             * An example of a custom JigsawPlacement.addPieces in action can be found here (warning, it is using Mojmap mappings):
             * https://github.com/TelepathicGrunt/RepurposedStructures/blob/1.18.2/src/main/java/com/telepathicgrunt/repurposedstructures/world/structures/pieces/PieceLimitedJigsawManager.java
             */
            //LOGGER.info("Fortress piece at " + this.pieces.get(0).getBoundingBox().x0 + " " +
            //        this.pieces.get(0).getBoundingBox().y0 + " " +
            //        this.pieces.get(0).getBoundingBox().z0);
            // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
        }


    }
}

