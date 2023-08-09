package com.scouter.netherdepthsupgrade.blocks;

import com.scouter.netherdepthsupgrade.blocks.entity.LavaGlassBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class LavaGlassBlock extends BaseEntityBlock {
    protected static final VoxelShape SHAPE_DOWN = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.01D, 16.0D);
    protected static final VoxelShape SHAPE_UP = Block.box(0.0D, 15.99D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 0.01D);
    protected static final VoxelShape SHAPE_EAST = Block.box(15.99D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(0.0D, 0.0D, 15.99D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_WEST = Block.box(0.0D, 0.0D, 0.0D, 0.01D, 16.0D, 16.0D);
    private static final Map<Direction, VoxelShape> occlusionShapes = new HashMap<Direction, VoxelShape>() {{
        put(Direction.DOWN, SHAPE_DOWN);
        put(Direction.UP, SHAPE_UP);
        put(Direction.NORTH, SHAPE_NORTH);
        put(Direction.EAST, SHAPE_EAST);
        put(Direction.SOUTH, SHAPE_SOUTH);
        put(Direction.WEST, SHAPE_WEST);
    }};
    public LavaGlassBlock(Properties p_53970_) {
        super(p_53970_);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if(!pLevel.isClientSide){
            LavaGlassBlockEntity blockEntity = (LavaGlassBlockEntity) pLevel.getBlockEntity(pPos);
            blockEntity.getOcclusionDirs().clear();
            blockEntity.setOcclusionShape(Shapes.empty());
            VoxelShape shape = blockEntity.getOcclusionShape();
            for(Direction direction : Direction.values()){
                if(pLevel.getFluidState(pPos.relative(direction)).is(FluidTags.LAVA)){
                   shape = Shapes.or(shape, occlusionShapes.get(direction));
                   blockEntity.addDirection(direction);
                }
            }

            blockEntity.setOcclusionShape(shape);
        }
        pLevel.sendBlockUpdated(pPos, pState, pState, Block.UPDATE_IMMEDIATE);


    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if(!pLevel.isClientSide()){
            LavaGlassBlockEntity blockEntity = (LavaGlassBlockEntity) pLevel.getBlockEntity(pPos);
            blockEntity.getOcclusionDirs().clear();
            blockEntity.setOcclusionShape(Shapes.empty());
            VoxelShape shape = blockEntity.getOcclusionShape();

            for(Direction direction : Direction.values()){
                if(pLevel.getFluidState(pPos.relative(direction)).is(FluidTags.LAVA)){
                    shape = Shapes.or(shape, occlusionShapes.get(direction));
                    blockEntity.addDirection(direction);
                }
            }

            blockEntity.setOcclusionShape(shape);
        }
        pLevel.sendBlockUpdated(pPos, pState, pState, Block.UPDATE_IMMEDIATE);
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
    }



    public VoxelShape getVisualShape(BlockState pState, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }

    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        LavaGlassBlockEntity blockEntity = (LavaGlassBlockEntity) pLevel.getBlockEntity(pPos);
        if(blockEntity != null) {
            VoxelShape shape = blockEntity.getOcclusionShape();
            return shape;
        }
        return Shapes.empty();
    }

    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.is(this) ? true : super.skipRendering(pState, pAdjacentBlockState, pSide);
    }

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new LavaGlassBlockEntity(pPos, pState);
    }
}
