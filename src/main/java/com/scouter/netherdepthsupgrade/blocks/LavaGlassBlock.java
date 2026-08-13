package com.scouter.netherdepthsupgrade.blocks;

import com.mojang.serialization.MapCodec;
import com.scouter.netherdepthsupgrade.blocks.entity.LavaGlassBlockEntity;
import com.scouter.netherdepthsupgrade.blocks.entity.NDUBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class LavaGlassBlock extends Block {
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
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    private static final VoxelShape[] FLUID_FACE_SHAPES = createFluidFaceShapes();

    private static final VoxelShape[] OCCLUSION_SHAPES = createOcclusionShapes();

    public static final MapCodec<LavaGlassBlock> CODEC = simpleCodec(LavaGlassBlock::new);
    public LavaGlassBlock(Properties p_53970_) {
        super(p_53970_);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.withConnections(this.defaultBlockState(), context.getLevel(), context.getClickedPos());
    }

    public BlockState withConnections(BlockState state, BlockGetter level, BlockPos position) {
        for (Direction direction : Direction.values()) {
            boolean connected = level.getBlockState(position.relative(direction)).is(this);

            state = state.setValue(getConnectionProperty(direction), connected);
        }

        return state;
    }

  // @Override
  // public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
  //     if(!pLevel.isClientSide()){
  //         LavaGlassBlockEntity blockEntity = (LavaGlassBlockEntity) pLevel.getBlockEntity(pPos);
  //         blockEntity.getOcclusionDirs().clear();
  //         blockEntity.setOcclusionShape(Shapes.empty());
  //         VoxelShape shape = blockEntity.getOcclusionShape();

  //         for(Direction direction : Direction.values()){
  //             if(pLevel.getFluidState(pPos.relative(direction)).is(FluidTags.LAVA)){
  //                 shape = Shapes.or(shape, occlusionShapes.get(direction));
  //                 blockEntity.addDirection(direction);
  //             }
  //         }

  //         blockEntity.setOcclusionShape(shape);
  //     }
  //     pLevel.sendBlockUpdated(pPos, pState, pState, Block.UPDATE_IMMEDIATE);
  //     super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
  // }
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos position, BlockPos neighborPosition) {
        return state.setValue(getConnectionProperty(direction), neighborState.is(this));
    }


    public VoxelShape getVisualShape(BlockState pState, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }

    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1.0F;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        int fluidMask = 0;

        for (Direction direction : Direction.values()) {
            if (pLevel.getFluidState(pPos.relative(direction))
                    .is(FluidTags.LAVA)) {
                fluidMask |= 1 << direction.ordinal();
            }
        }

        return OCCLUSION_SHAPES[fluidMask];
    }

    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.is(this) ? true : super.skipRendering(pState, pAdjacentBlockState, pSide);
    }

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return true;
    }

    private static BooleanProperty getConnectionProperty(Direction direction) {
        return switch (direction) {
            case UP -> UP;
            case DOWN -> DOWN;
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
        };
    }

    private static VoxelShape[] createFluidFaceShapes() {
        VoxelShape[] shapes = new VoxelShape[Direction.values().length];

        shapes[Direction.DOWN.ordinal()] = SHAPE_DOWN;
        shapes[Direction.UP.ordinal()] = SHAPE_UP;
        shapes[Direction.NORTH.ordinal()] = SHAPE_NORTH;
        shapes[Direction.EAST.ordinal()] = SHAPE_EAST;
        shapes[Direction.SOUTH.ordinal()] = SHAPE_SOUTH;
        shapes[Direction.WEST.ordinal()] = SHAPE_WEST;

        return shapes;
    }

    private static VoxelShape[] createOcclusionShapes() {
        VoxelShape[] shapes = new VoxelShape[64];

        for (int mask = 0; mask < shapes.length; mask++) {
            VoxelShape combinedShape = Shapes.empty();

            for (Direction direction : Direction.values()) {
                int directionBit = 1 << direction.ordinal();
                if ((mask & directionBit) != 0) {
                    combinedShape = Shapes.or(combinedShape, FLUID_FACE_SHAPES[direction.ordinal()]
                    );
                }
            }
            shapes[mask] = combinedShape.optimize();
        }

        return shapes;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
    }


}
