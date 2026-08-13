package com.scouter.netherdepthsupgrade.datagen;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import com.scouter.netherdepthsupgrade.NetherDepthsUpgrade;
import com.scouter.netherdepthsupgrade.blocks.LavaGlassBlock;
import com.scouter.netherdepthsupgrade.blocks.NDUBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static com.scouter.netherdepthsupgrade.NetherDepthsUpgrade.prefix;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.DUSTED;
import static net.neoforged.neoforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class SBlockStateGenerator extends BlockStateProvider {
    private static final Logger LOGGER = LogUtils.getLogger();

    public SBlockStateGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, NetherDepthsUpgrade.MODID, exFileHelper);
    }
    public static final List<Pair<BooleanProperty, Function<ResourceLocation, Variant>>> MULTIFACE_GENERATOR = List.of(
            Pair.of(BlockStateProperties.NORTH, p_176234_ -> Variant.variant().with(VariantProperties.MODEL, p_176234_)),
            Pair.of(
                    BlockStateProperties.EAST,
                    p_176229_ -> Variant.variant()
                            .with(VariantProperties.MODEL, p_176229_)
                            .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                            .with(VariantProperties.UV_LOCK, true)
            ),
            Pair.of(
                    BlockStateProperties.SOUTH,
                    p_176225_ -> Variant.variant()
                            .with(VariantProperties.MODEL, p_176225_)
                            .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                            .with(VariantProperties.UV_LOCK, true)
            ),
            Pair.of(
                    BlockStateProperties.WEST,
                    p_176213_ -> Variant.variant()
                            .with(VariantProperties.MODEL, p_176213_)
                            .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                            .with(VariantProperties.UV_LOCK, true)
            ),
            Pair.of(
                    BlockStateProperties.UP,
                    p_176204_ -> Variant.variant()
                            .with(VariantProperties.MODEL, p_176204_)
                            .with(VariantProperties.X_ROT, VariantProperties.Rotation.R270)
                            .with(VariantProperties.UV_LOCK, true)
            ),
            Pair.of(
                    BlockStateProperties.DOWN,
                    p_176195_ -> Variant.variant()
                            .with(VariantProperties.MODEL, p_176195_)
                            .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                            .with(VariantProperties.UV_LOCK, true)
            )
    );
    public static BlockFamily family = null;
    static final Map<BlockFamily.Variant, BiConsumer<SBlockStateGenerator, Block>> SHAPE_CONSUMERS =
            ImmutableMap.<BlockFamily.Variant, BiConsumer<SBlockStateGenerator, Block>>builder()
                    .put(BlockFamily.Variant.BUTTON, SBlockStateGenerator::generateButton)
                    .put(BlockFamily.Variant.DOOR, SBlockStateGenerator::generateDoor)
                    .put(BlockFamily.Variant.CHISELED, SBlockStateGenerator::generateChiseled)
                    .put(BlockFamily.Variant.CRACKED, SBlockStateGenerator::generateCracked)
                    .put(BlockFamily.Variant.CUSTOM_FENCE, SBlockStateGenerator::generateFence)
                    .put(BlockFamily.Variant.FENCE, SBlockStateGenerator::generateFence)
                    .put(BlockFamily.Variant.CUSTOM_FENCE_GATE, SBlockStateGenerator::generateFenceGate)
                    .put(BlockFamily.Variant.FENCE_GATE, SBlockStateGenerator::generateFenceGate)
                    .put(BlockFamily.Variant.SIGN, SBlockStateGenerator::generateSign)
                    .put(BlockFamily.Variant.SLAB, SBlockStateGenerator::generateSlab)
                    .put(BlockFamily.Variant.STAIRS, SBlockStateGenerator::generateStair)
                    .put(BlockFamily.Variant.PRESSURE_PLATE, SBlockStateGenerator::generatePressurePlate)
                    .put(BlockFamily.Variant.TRAPDOOR, SBlockStateGenerator::generateTrapDoor)
                    .put(BlockFamily.Variant.WALL, SBlockStateGenerator::generateWall)
                    .build();


    @Override
    protected void registerStatesAndModels() {

       simpleBlock(NDUBlocks.LAVA_SPONGE.get());
       simpleBlock(NDUBlocks.WET_LAVA_SPONGE.get());
       //simpleBlock(NDUBlocks.LAVA_GLASS.get());
       createConnectedLavaGlass();
    }


    private void createConnectedLavaGlass() {
        LavaGlassBlock block =
                (LavaGlassBlock) NDUBlocks.LAVA_GLASS.get();

        ResourceLocation baseTexture =
                prefix("block/lava_glass");

        ResourceLocation allTexture =
                prefix("block/lava_glass_all");

        ResourceLocation blankTexture =
                prefix("block/lava_glass_blank");

        ResourceLocation upTexture =
                prefix("block/lava_glass_up");

        ResourceLocation downTexture =
                prefix("block/lava_glass_down");

        ResourceLocation leftTexture =
                prefix("block/lava_glass_left");

        ResourceLocation rightTexture =
                prefix("block/lava_glass_right");

        BlockModelBuilder baseModel =
                createLavaGlassBaseModel(blankTexture, allTexture);

        models()
                .cubeAll(
                        "lava_glass_all",
                        allTexture
                )
                .renderType("cutout_mipped");

        BlockModelBuilder upModel = models()
                .withExistingParent(
                        "lava_glass_up",
                        mcLoc("block/cube")
                )
                .renderType("cutout_mipped")
                .texture("particle", allTexture)
                .texture("down", blankTexture)
                .texture("up", blankTexture)
                .texture("north", upTexture)
                .texture("east", upTexture)
                .texture("south", upTexture)
                .texture("west", upTexture);

        BlockModelBuilder downModel = models()
                .withExistingParent(
                        "lava_glass_down",
                        mcLoc("block/cube")
                )
                .renderType("cutout_mipped")
                .texture("particle", allTexture)
                .texture("down", blankTexture)
                .texture("up", blankTexture)
                .texture("north", downTexture)
                .texture("east", downTexture)
                .texture("south", downTexture)
                .texture("west", downTexture);

        BlockModelBuilder leftModel = models()
                .withExistingParent(
                        "lava_glass_left",
                        mcLoc("block/cube")
                )
                .renderType("cutout_mipped")
                .texture("particle", allTexture)
                .texture("down", leftTexture)
                .texture("up", leftTexture)
                .texture("north", rightTexture)
                .texture("east", blankTexture)
                .texture("south", leftTexture)
                .texture("west", blankTexture);

        BlockModelBuilder rightModel = models()
                .withExistingParent(
                        "lava_glass_right",
                        mcLoc("block/cube")
                )
                .renderType("cutout_mipped")
                .texture("particle", allTexture)
                .texture("down", rightTexture)
                .texture("up", rightTexture)
                .texture("north", leftTexture)
                .texture("east", blankTexture)
                .texture("south", rightTexture)
                .texture("west", blankTexture);

        MultiPartBlockStateBuilder builder =
                getMultipartBuilder(block);

        builder.part()
                .modelFile(baseModel)
                .addModel()
                .end();

        builder.part()
                .modelFile(upModel)
                .uvLock(false)
                .addModel()
                .condition(LavaGlassBlock.UP, false)
                .end();

        builder.part()
                .modelFile(downModel)
                .uvLock(false)
                .addModel()
                .condition(LavaGlassBlock.DOWN, false)
                .end();

        builder.part()
                .modelFile(rightModel)
                .uvLock(false)
                .addModel()
                .condition(LavaGlassBlock.EAST, false)
                .end();

        builder.part()
                .modelFile(leftModel)
                .uvLock(false)
                .addModel()
                .condition(LavaGlassBlock.WEST, false)
                .end();

        builder.part()
                .modelFile(leftModel)
                .rotationY(90)
                .uvLock(false)
                .addModel()
                .condition(LavaGlassBlock.NORTH, false)
                .end();

        builder.part()
                .modelFile(rightModel)
                .rotationY(90)
                .uvLock(false)
                .addModel()
                .condition(LavaGlassBlock.SOUTH, false)
                .end();
    }

    private BlockModelBuilder createLavaGlassBaseModel(
            ResourceLocation visualTexture,
            ResourceLocation particleTexture
    ) {
        BlockModelBuilder model = models()
                .withExistingParent(
                        "lava_glass",
                        mcLoc("block/block")
                )
                .renderType("cutout_mipped")
                .texture("particle", particleTexture)
                .texture("tex", visualTexture);

        var element = model.element()
                .from(0.0F, 0.0F, 0.0F)
                .to(16.0F, 16.0F, 16.0F);

        for (Direction direction : Direction.values()) {
            element.face(direction)
                    .uvs(0.0F, 0.0F, 16.0F, 16.0F)
                    .texture("#tex")
                    .cullface(direction)
                    .tintindex(0)
                    .end();
        }

        element.end();

        return model;
    }

    private void createPottedPlant(DeferredBlock<Block> plant, DeferredBlock<Block> pottedPlant, String renderType){
        ConfiguredModel cFfile = new ConfiguredModel(pottedPlant(name(pottedPlant.get()), blockTexture(plant.get()), renderType));
        getVariantBuilder(pottedPlant.get()).partialState().setModels(cFfile);
        //impleBlockItem(plant.get(), file);
    }

    public ModelFile pottedPlant(String name, ResourceLocation plant, String renderType) {
        return singleTexture(name, BLOCK_FOLDER + "/flower_pot_cross", "plant", plant, renderType);
    }

    public void createFlatWaterEgg(Block block){
        createFlatWaterEgg(block, "");
        flatWaterEgg(block);
        singleTexWaterEgg(block);
    }

    private BlockModelBuilder singleTexWaterEgg(Block block) {
        return generated(getName(block),  ResourceLocation.fromNamespaceAndPath(NetherDepthsUpgrade.MODID,"item/" + getName(block)));
    }

    private void flatWaterEgg(Block block) {
        getVariantBuilder(block).forAllStatesExcept((state) -> ConfiguredModel.builder().modelFile(existingModel("eggs/"+getName(block)))
                .build());
    }
    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(resourceBlock(path), models().existingFileHelper);
    }

    public ResourceLocation resourceBlock(String path) {
        return  ResourceLocation.fromNamespaceAndPath(NetherDepthsUpgrade.MODID, "block/" + path);
    }


    public void createHangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        createHangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void createHangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }
    private void logBlock(DeferredBlock<Block> block) {
        logBlock(block.get());
    }

    private void logBlock(Block block) {
        axisBlock((RotatedPillarBlock) block, prefix("block/" + key(block).getPath()), prefix("block/" +key(block).getPath() + "_top"));
    }

    private void createAge3Block(Block pBlock, String renderTyp, boolean item) {
        String baseName = name(pBlock);
        getVariantBuilder(pBlock).forAllStatesExcept((state ->
        {
            int age = state.getValue(BlockStateProperties.AGE_3);
            ResourceLocation textureSuspicious = prefix("block/" + baseName + "_" + age);
            ModelFile text = cross(baseName + "_" + age, textureSuspicious, renderTyp);
            return ConfiguredModel.builder().modelFile(text).build();
        }
        ));
        ModelFile file = models().getExistingFile(prefix(baseName + "_0"));
        if(item) simpleBlockItem(pBlock, file);
    }

    private void createCropBlock(Block pBlock, String renderTyp) {
        String baseName = name(pBlock);
        getVariantBuilder(pBlock).forAllStatesExcept((state ->
        {
            int age = state.getValue(CropBlock.AGE);
            ResourceLocation textureSuspicious = prefix("block/" + baseName + "_" + age);
            ModelFile text = cross(baseName + "_" + age, textureSuspicious, renderTyp);
            return ConfiguredModel.builder().modelFile(text).build();
        }
        ));
        ModelFile file = models().getExistingFile(prefix(baseName + "_0"));
        simpleBlockItem(pBlock, file);
    }

    //private void createFogBlock(Block pBlock, String renderTyp) {
    //    String baseName = name(pBlock);
    //    getVariantBuilder(pBlock).forAllStatesExcept((state ->
    //    {
    //        int strength = state.getValue(FogBlock.STRENGTH);
    //        ResourceLocation textureSuspicious = prefix("block/" + baseName + "_" + strength);
    //        ModelFile text = cubeAll(baseName + "_" + strength, textureSuspicious, renderTyp);
    //        return ConfiguredModel.builder().modelFile(text).build();
    //    }
    //    ));
    //    ModelFile file = models().getExistingFile(prefix(baseName + "_0"));
    //    simpleBlockItem(pBlock, file);
    //}

    private void createSkullBlocks(DeferredBlock<Block> deferredBlock) {
        Block block = deferredBlock.get();
        String baseName = name(block);
        getVariantBuilder(block).forAllStates(state -> {
            ResourceLocation skullLoc = prefix("block/skulls/" + baseName);
            ModelFile text = models().getExistingFile(skullLoc);
            return ConfiguredModel.builder().modelFile(text).build();
        });
        getVariantBuilder(getBlock(prefix(baseName+"_wall"))).forAllStates(state -> {
            ResourceLocation skullLoc = prefix("block/skulls/" + baseName+"_wall");
            ModelFile text = models().getExistingFile(skullLoc);
            return ConfiguredModel.builder().modelFile(text).build();
        });

        singleTexItem(block);
        singleTexItem(getBlock(prefix(baseName+"_wall")));
    }
    private void createBrushableBlock(Block pBlock) {
        String baseName = name(pBlock);
        getVariantBuilder(pBlock).forAllStatesExcept((state ->
        {
            int dusted = state.getValue(DUSTED);
            ResourceLocation textureSuspicious = prefix("block/" + baseName + "_" + dusted);
            ModelFile text = models().cubeAll(baseName + "_" + dusted, textureSuspicious);
            return ConfiguredModel.builder().modelFile(text).build();
        }
        ));
        ModelFile file = models().getExistingFile(prefix(baseName + "_0"));
        simpleBlockItem(pBlock, file);
    }


    private void simpleBlockLeaves(DeferredBlock<Block> b, String renderType) {
        simpleBlock(b.get(), leaves(b.get(), renderType));
    }

    private void simpleBlock(DeferredBlock<Block> b, String renderType) {
        simpleBlock(b.get(), cubeAll(b.get(), renderType));
    }

    public void cubeAllWithItem(Block block, String renderType) {
        ModelFile file = cubeAll(name(block), blockTexture(block), renderType);
        simpleBlockItem(block, file);
    }
    public ModelFile cubeAll(Block block, String renderType) {
      return cubeAll(name(block), blockTexture(block), renderType);
    }

    public ModelFile leaves(Block block, String renderType) {
        ModelFile leaves = leaves(name(block), blockTexture(block), renderType);
        simpleBlockItem(block, leaves);
        return leaves;
    }
    public ModelFile leaves(String name, ResourceLocation texture, String renderType) {
        return singleTexture(name, BLOCK_FOLDER + "/leaves", "all", texture, renderType);
    }
    public ModelFile cubeAll(String name, ResourceLocation texture, String renderType) {
        return singleTexture(name, BLOCK_FOLDER + "/cube_all", "all", texture, renderType);
    }
    private void buildLamp(Block block) {
        String baseName = name(block);
        ResourceLocation textureOn = prefix("block/" + baseName + "_on");
        ResourceLocation textureOff = prefix("block/" + baseName + "_off");
        ModelFile on = models().cubeAll(baseName + "_on", textureOn);
        ModelFile off = models().cubeAll(baseName + "_off", textureOff);
        simpleBlockItem(block, off);
        buildLamp(block, on, off);
    }

    private void buildLamp(Block block, ModelFile on, ModelFile off) {
        getVariantBuilder(block).forAllStatesExcept((state -> {
            boolean isLit = state.getValue(BlockStateProperties.LIT);

            return ConfiguredModel.builder().modelFile(isLit ? on : off).build();
        }));
    }

    public void generatePressurePlate(Block block) {
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        pressurePlateBlock((PressurePlateBlock) block, prefix("block/" + key(block1).getPath()));
    }

    public void generateWall(Block block) {
        wallBlockWithRenderType((WallBlock) block, prefix("block/" + key(block).getPath().replace("_wall", "")), "cutout");
        generatedWall(name(block), ResourceLocation.tryParse(blockTexture(block).toString().replace("_wall", "")));
    }

    public void generateTrapDoor(Block block) {
        trapdoorBlockWithRenderType((TrapDoorBlock) block, prefix("block/" + key(block).getPath()), true, "cutout");
    }

    public void generateStair(Block block) {
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        stairsBlockWithRenderType((StairBlock) block, prefix("block/" + key(block1).getPath()), "cutout");
    }

    public void generateSlab(Block block) {
        //blockWithItemSlab(block);
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        slabBlock((SlabBlock) block, prefix("block/" + key(block1).getPath()), prefix("block/" + key(block1).getPath()), prefix("block/" + key(block1).getPath()), prefix("block/" + key(block1).getPath()));
    }

    public void generateSign(Block signBlock) {
        LOGGER.error("Sign gen is not yet implemented!");

        String p =key(signBlock).getPath();
        String z = p.replace("_sign","_wall_sign").replace("blocks/", "");
        ResourceLocation hanging = prefix(z);
        Block block = getBlock(hanging);
        //if(block == null)
        signBlock((StandingSignBlock) signBlock, (WallSignBlock) block, prefix("entity/sign/" + key(signBlock).getPath().replace("_sign", "")));

    }

    public void generateFenceGate(Block block) {
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        fenceGateBlockWithRenderType((FenceGateBlock) block, prefix("block/" + key(block1).getPath()), "cutout");
    }

    public void generateFence(Block block) {
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        fenceBlockWithRenderType((FenceBlock) block, prefix("block/" + key(block1).getPath()), "cutout");
    }

    public void generateCracked(Block block) {
        blockWithItem(block);
    }

    public void generateChiseled(Block block) {
        blockWithItem(block);
    }

    public void generateDoor(Block doorBlock) {
        doorBlockWithRenderType((DoorBlock) doorBlock,
                prefix("block/" + key(doorBlock).getPath() + "_bottom"),
                prefix("block/" + key(doorBlock).getPath() + "_top"),
                "cutout");
    }

    public void generateButton(Block block) {
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        buttonBlock((ButtonBlock) block, prefix("block/" + key(block1).getPath()));
    }

    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockWithItem(Block blockRegistryObject, ModelFile file) {
        simpleBlockWithItem(blockRegistryObject, file);
    }

    private void blockWithItem(Block blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject, cubeAll(blockRegistryObject));
    }

    private void blockWithItemSlab(Block blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject, stripSlab(blockRegistryObject));
    }

    private ModelFile stripSlab(Block block) {
        Block block1 = block;
        if(this.family != null) {
            block1 = this.family.getBaseBlock();
        }
        return models().cubeAll(name(block), ResourceLocation.tryParse(blockTexture(block1).toString().replace("_slab", "")));
    }

    private void createWallFan(DeferredBlock<Block> b, String renderType) {
        ModelFile file = new ConfiguredModel(wallCoral(name(b.get()), blockTexture(b.get()), renderType)).model;
        getVariantBuilder(b.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(file)
                        .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                        .build()
                );
        simpleBlockItem(b.get(), file);
    }




    private void createTintedCross(DeferredBlock<Block> b, String renderType) {
        getVariantBuilder(b.get()).partialState().setModels(new ConfiguredModel(tintedCross(name(b.get()), blockTexture(b.get()), renderType)));
    }

    public ModelFile tintedCross(String name, ResourceLocation cross, String renderType) {
        return singleTexture(name, BLOCK_FOLDER + "/tinted_cross", "cross", cross, renderType);
    }
    private void createDoubleCross(DeferredBlock<Block> b, String renderType) {
        String baseName = name(b.get());
        getVariantBuilder(b.get()).forAllStatesExcept(e -> {
            DoubleBlockHalf val = e.getValue(DoublePlantBlock.HALF);
            String mod = val == DoubleBlockHalf.LOWER ? "bottom" : "top";
            ResourceLocation texture = prefix("block/" + baseName + "_" + mod);
            ModelFile text = cross(baseName + "_" + mod, texture, renderType);
            return ConfiguredModel.builder().modelFile(text).build();
        });

        //ModelFile file = models().getExistingFile(prefix(baseName + "_top"));
        singleTex(b.get(), baseName + "_top");
    }

    private void createCross(DeferredBlock<Block> b, String renderType) {
        ConfiguredModel cFfile = new ConfiguredModel(cross(name(b.get()), blockTexture(b.get()), renderType));
        ModelFile file = cFfile.model;
        String baseName = name(b.get());
        getVariantBuilder(b.get()).partialState().setModels(cFfile);

        singleTex(b.get(), baseName);
        //simpleBlockItem(b.get(), file);
    }

    public ModelFile cross(String name, ResourceLocation cross, String renderType) {
        return singleTexture(name, BLOCK_FOLDER + "/cross", "cross", cross, renderType);
    }

    private ModelFile singleTexture(String name, String parent, String textureKey, ResourceLocation texture, String renderType) {
        return singleTexture(name, mcLoc(parent), textureKey, texture, renderType);
    }

    public ModelFile wallCoral(String name, ResourceLocation fan, String renderType) {
        return singleTexture(name, BLOCK_FOLDER + "/coral_wall_fan", "fan", fan, renderType);
    }

    private void createCoralFan(DeferredBlock<Block> b, String renderType) {
        getVariantBuilder(b.get()).partialState().setModels(new ConfiguredModel(coralFan(name(b.get()), blockTexture(b.get()), renderType)));
        singleTex(b.get());
    }

    public ModelFile coralFan(String name, ResourceLocation fan, String renderType) {
        return singleTexture(name, BLOCK_FOLDER + "/coral_fan", "fan", fan, renderType);
    }

    public ModelFile singleTexture(String name, ResourceLocation parent, String textureKey, ResourceLocation texture, String renderType) {
        return models().withExistingParent(name, parent)
                .texture(textureKey, texture).renderType(renderType);
    }

    private BlockModelBuilder generatedWall(String name, ResourceLocation... layers) {
        BlockModelBuilder builder = models().withExistingParent("block/" + name, "block/wall_inventory");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }

    private BlockModelBuilder generatedSlab(String name, ResourceLocation... layers) {
        BlockModelBuilder builder = models().withExistingParent("block/" + name, "block/slab");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }

    private void blockWithTop(DeferredBlock<Block> blockRegistryObject) {
        horizontalBlock(blockRegistryObject.get(), prefix("block/" + key(blockRegistryObject.get()).getPath()), prefix("block/" + key(blockRegistryObject.get()).getPath()), prefix("block/" + key(blockRegistryObject.get()).getPath() + "_top"));
        simpleBlockItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private BlockModelBuilder singleTexItem(Block block, ResourceLocation name) {
        return generated(name(block), name);
    }
    private BlockModelBuilder singleTexItem(Block block) {
        return generated(name(block), ResourceLocation.fromNamespaceAndPath(NetherDepthsUpgrade.MODID, "item/" + name(block)));
    }

    private BlockModelBuilder singleTex(Block block) {
        return singleTex(block,  name(block));
    }

    private BlockModelBuilder singleTex(Block block, String name) {
        return generated(name(block), ResourceLocation.fromNamespaceAndPath(NetherDepthsUpgrade.MODID, "block/" + name));
    }

    private BlockModelBuilder generated(String name, ResourceLocation... layers) {
        BlockModelBuilder builder = models().withExistingParent("item/" + name, "item/generated");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }
    private String getName(Block block) {
        return key(block).toString().replace(NetherDepthsUpgrade.MODID + ":", "");
    }
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
    public ModelFile createFlatWaterEgg(Block block, String modifier){
        String baseName = getName(block);
        return models().singleTexture("block/eggs/" + modifier + baseName.replace(NetherDepthsUpgrade.MODID + ":", ""),  ResourceLocation.fromNamespaceAndPath(NetherDepthsUpgrade.MODID, "block/template_eggs/template_flat_water_egg"), blockTextureEggs(block));
    }

    public ResourceLocation blockTextureEggs(Block block) {
        ResourceLocation name = key(block);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/eggs/" + name.getPath());
    }
    private String name(Block block) {
        return key(block).getPath();
    }

    private Block getBlock(ResourceLocation resourceLocation) {
        return BuiltInRegistries.BLOCK.get(resourceLocation);
    }
}