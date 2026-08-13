package com.scouter.netherdepthsupgrade.world;

import com.scouter.netherdepthsupgrade.blocks.LavaGlassBlock;
import com.scouter.netherdepthsupgrade.blocks.entity.LavaGlassBlockEntity;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.ArrayList;
import java.util.List;

public final class LavaGlassMigration {

    private LavaGlassMigration() {
    }

    public static void register() {
        ServerChunkEvents.CHUNK_LOAD.register(
                LavaGlassMigration::migrateChunk
        );
    }

    private static void migrateChunk(
            ServerLevel level,
            LevelChunk chunk
    ) {
        List<BlockPos> legacyPositions = new ArrayList<>();

        /*
         * Make a copy because the block entities will be removed from
         * the chunk later.
         */
        List<BlockEntity> blockEntities =
                List.copyOf(chunk.getBlockEntities().values());

        for (BlockEntity blockEntity : blockEntities) {
            if (blockEntity instanceof LavaGlassBlockEntity) {
                legacyPositions.add(
                        blockEntity.getBlockPos().immutable()
                );
            }
        }

        if (legacyPositions.isEmpty()) {
            return;
        }

        for (BlockPos position : legacyPositions) {
            BlockState currentState =
                    chunk.getBlockState(position);

            /*
             * Remove the obsolete block entity. The LavaGlassBlock itself
             * no longer creates a new one.
             */
            chunk.removeBlockEntity(position);

            if (!(currentState.getBlock()
                    instanceof LavaGlassBlock lavaGlass)) {
                continue;
            }

            BlockState connectedState =
                    lavaGlass.withConnections(
                            currentState,
                            level,
                            position
                    );

            if (!connectedState.equals(currentState)) {
                level.setBlock(
                        position,
                        connectedState,
                        Block.UPDATE_CLIENTS
                );
            }
        }

        /*
         * Forces the chunk to save without the obsolete block entities,
         * including chunks where the blockstate itself did not change.
         */
        chunk.setUnsaved(true);
    }
}