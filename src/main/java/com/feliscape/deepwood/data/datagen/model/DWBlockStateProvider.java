package com.feliscape.deepwood.data.datagen.model;

import com.feliscape.deepwood.Config;
import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.block.ArcfurnaceBlock;
import com.feliscape.deepwood.registry.DeepwoodBlocks;
import com.feliscape.deepwood.registry.DeepwoodItems;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class DWBlockStateProvider extends BlockStateProvider {

    public DWBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Deepwood.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItemAndVariants(DeepwoodBlocks.DEEP_SOIL,
                configuredWithWeight(cubeAll(DeepwoodBlocks.DEEP_SOIL.get()), 10),
                configuredWithWeight(models().cubeAll("deep_soil_cracked", Deepwood.asResource("block/deep_soil_cracked")), 4),
                configuredWithWeight(models().cubeAll("deep_soil_crumbling", Deepwood.asResource("block/deep_soil_crumbling")), 1));


        blockWithItemAndVariants(DeepwoodBlocks.AMBOSS_ORE,
                new ConfiguredModel(cubeAll(DeepwoodBlocks.AMBOSS_ORE.get())),
                new ConfiguredModel(models().cubeAll("amboss_ore_1", Deepwood.asResource("block/amboss_ore_1"))),
                new ConfiguredModel(models().cubeAll("amboss_ore_2", Deepwood.asResource("block/amboss_ore_2")))
                );

        cubeBottomTop(DeepwoodBlocks.SPIRIT_GRASS_BLOCK.get());
        blockWithItem(DeepwoodBlocks.AMBOSS_BLOCK);
        blockWithItem(DeepwoodBlocks.RAW_AMBOSS_BLOCK);
        blockWithItem(DeepwoodBlocks.WRAITH_AMBOSS_BLOCK);

        blockWithItemAndVariants(DeepwoodBlocks.SPIRISTONE,
                new ConfiguredModel(cubeAll(DeepwoodBlocks.SPIRISTONE.get())),
                new ConfiguredModel(cubeMirroredAll("spiristone_mirrored", Deepwood.asResource("block/spiristone"))));
        stairsBlock(DeepwoodBlocks.SPIRISTONE_STAIRS, DeepwoodBlocks.SPIRISTONE);
        slabBlock(DeepwoodBlocks.SPIRISTONE_SLAB, DeepwoodBlocks.SPIRISTONE);
        wallBlockWithItem(DeepwoodBlocks.SPIRISTONE_WALL, DeepwoodBlocks.SPIRISTONE);

        blockWithItem(DeepwoodBlocks.COBBLED_SPIRISTONE);
        stairsBlock(DeepwoodBlocks.COBBLED_SPIRISTONE_STAIRS, DeepwoodBlocks.COBBLED_SPIRISTONE);
        slabBlock(DeepwoodBlocks.COBBLED_SPIRISTONE_SLAB, DeepwoodBlocks.COBBLED_SPIRISTONE);
        wallBlockWithItem(DeepwoodBlocks.COBBLED_SPIRISTONE_WALL, DeepwoodBlocks.COBBLED_SPIRISTONE);

        blockWithItem(DeepwoodBlocks.POLISHED_SPIRISTONE);
        stairsBlock(DeepwoodBlocks.POLISHED_SPIRISTONE_STAIRS, DeepwoodBlocks.POLISHED_SPIRISTONE);
        slabBlock(DeepwoodBlocks.POLISHED_SPIRISTONE_SLAB, DeepwoodBlocks.POLISHED_SPIRISTONE);
        wallBlockWithItem(DeepwoodBlocks.POLISHED_SPIRISTONE_WALL, DeepwoodBlocks.POLISHED_SPIRISTONE);

        blockWithItem(DeepwoodBlocks.SPIRISTONE_BRICKS);
        stairsBlock(DeepwoodBlocks.SPIRISTONE_BRICK_STAIRS, DeepwoodBlocks.SPIRISTONE_BRICKS);
        slabBlock(DeepwoodBlocks.SPIRISTONE_BRICK_SLAB, DeepwoodBlocks.SPIRISTONE_BRICKS);
        wallBlockWithItem(DeepwoodBlocks.SPIRISTONE_BRICK_WALL, DeepwoodBlocks.SPIRISTONE_BRICKS);

        makeArcFurnace(DeepwoodBlocks.ARC_FURNACE, state -> state.getValue(ArcfurnaceBlock.LIT));
    }

    private void makeFurnaceLikeBlock(DeferredBlock<? extends Block> block, Function<BlockState, Boolean> onOffFunc) {
        ResourceLocation baseTexture = blockTexture(block.get());
        ModelFile off = models().orientableWithBottom(name(block.get()),
                extend(baseTexture, "_side"),
                extend(baseTexture, "_front"),
                extend(baseTexture, "_bottom"),
                extend(baseTexture, "_top")
        );
        ModelFile on = models().orientableWithBottom(name(block.get()) + "_on",
                extend(baseTexture, "_side"),
                extend(baseTexture, "_front_on"),
                extend(baseTexture, "_bottom"),
                extend(baseTexture, "_top")
        );
        simpleBlockItem(block.get(), off);

        horizontalBlock(block.get(), state -> onOffFunc.apply(state) ? on : off);
    }

    private void makeArcFurnace(DeferredBlock<? extends Block> block, Function<BlockState, Boolean> onOffFunc) {
        ResourceLocation baseTexture = blockTexture(block.get());
        ModelFile off = models().orientableWithBottom(name(block.get()),
                extend(baseTexture, "_side"),
                extend(baseTexture, "_front"),
                extend(baseTexture, "_bottom"),
                extend(baseTexture, "_top")
        );
        ModelFile on = models().orientableWithBottom(name(block.get()) + "_on",
                extend(baseTexture, "_side"),
                extend(baseTexture, "_front_on"),
                extend(baseTexture, "_bottom"),
                extend(baseTexture, "_top_on")
        );
        simpleBlockItem(block.get(), off);

        horizontalBlock(block.get(), state -> onOffFunc.apply(state) ? on : off);
    }

    public void pillarBlock(RotatedPillarBlock pillarBlock) {
        axisBlock(pillarBlock, blockTexture(pillarBlock), extend(blockTexture(pillarBlock), "_end"));
    }
    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private void blockItem(DeferredBlock<? extends Block> blockRegistryObject){
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Deepwood.MOD_ID +
                ":block/" + key(blockRegistryObject.get()).getPath()));
    }

    public void stairsBlock(DeferredBlock<StairBlock> block, DeferredBlock<? extends Block> baseBlock) {
        stairsBlock(block.get(), blockTexture(baseBlock.get()));
        blockItem(block);
    }

    public void slabBlock(DeferredBlock<SlabBlock> block, DeferredBlock<? extends Block> baseBlock) {
        slabBlock(block.get(), blockTexture(baseBlock.get()), blockTexture(baseBlock.get()));
        blockItem(block);
    }

    public void wallBlockWithItem(DeferredBlock<WallBlock> block, DeferredBlock<? extends Block> baseBlock) {
        super.wallBlock(block.get(), blockTexture(baseBlock.get()));

        ModelFile inventoryModel = models().withExistingParent(block.getId().getPath() + "_inventory", "minecraft:block/wall_inventory")
                .texture("wall", blockTexture(baseBlock.get()));

        simpleBlockItem(block.get(), inventoryModel);
    }
    public void wallBlockWithItem(DeferredBlock<WallBlock> block, ResourceLocation texture) {
        super.wallBlock(block.get(), texture);

        ModelFile inventoryModel = models().withExistingParent(block.getId().getPath() + "_inventory", "minecraft:block/wall_inventory")
                .texture("wall", texture);

        simpleBlockItem(block.get(), inventoryModel);
    }

    private void blockWithItem(DeferredBlock<? extends Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void blockWithItemAndVariants(DeferredBlock<? extends Block> blockRegistryObject, ConfiguredModel mainModel, ConfiguredModel... variants){
        var builder = getVariantBuilder(blockRegistryObject.get()).partialState().setModels(mainModel);
        for (ConfiguredModel file : variants){
            builder.partialState().addModels(file);
        }
        simpleBlockItem(blockRegistryObject.get(), mainModel.model);
    }
    private void blockWithItemAndRenderType(DeferredBlock<? extends Block> blockRegistryObject, String renderType){
        simpleBlockWithItem(blockRegistryObject.get(), models().cubeAll(name(blockRegistryObject.get()), blockTexture(blockRegistryObject.get())).renderType(renderType));
    }

    private void leavesBlock(DeferredBlock<? extends Block> blockRegistryObject, String renderType){
        ModelFile model = models().withExistingParent(blockRegistryObject.getId().getPath(), "minecraft:block/leaves")
                .texture("all", blockTexture(blockRegistryObject.get())).renderType(renderType);
        getVariantBuilder(blockRegistryObject.get())
                .partialState().setModels( new ConfiguredModel(model));
        simpleBlockItem(blockRegistryObject.get(), model);
    }

    public void logBlockWithItem(RotatedPillarBlock block) {
        axisBlockWithItem(block, blockTexture(block), extend(blockTexture(block), "_top"));
    }

    public void axisBlockWithItem(RotatedPillarBlock block) {
        axisBlockWithItem(block, extend(blockTexture(block), "_side"),
                extend(blockTexture(block), "_end"));
    }

    public void axisBlockWithItem(RotatedPillarBlock block, ResourceLocation side, ResourceLocation end) {
        axisBlockWithItem(block,
                models().cubeColumn(name(block), side, end),
                models().cubeColumnHorizontal(name(block) + "_horizontal", side, end));
    }

    public void axisBlockWithItem(RotatedPillarBlock block, ModelFile vertical, ModelFile horizontal) {
        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(vertical).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(horizontal).rotationX(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
        simpleBlockItem(block, vertical);
    }

    public void crossBlockWithRenderType(Block block, String renderType) {
        getVariantBuilder(block).partialState().setModels(new ConfiguredModel(models().cross(name(block), blockTexture(block)).renderType(renderType)));
    }

    public void horizontalBlockWithItem(DeferredBlock<? extends Block> block, ModelFile model){
        horizontalBlock(block.get(), model);
        simpleBlockItem(block.get(), model);
    }
    public void cubeBottomTop(Block block){
        simpleBlockWithItem(block, models().cubeBottomTop(name(block),
                extend(blockTexture(block), "_side"),
                extend(blockTexture(block), "_bottom"),
                extend(blockTexture(block), "_top")));
    }

    // ::models() Extensions //

    protected ModelFile cubeMirroredAll(String name, ResourceLocation texture){
        return models().withExistingParent(name, "minecraft:block/cube_mirrored_all")
                .texture("all", texture);
    }

    protected ConfiguredModel configuredWithWeight(ModelFile file, int weight){
        return new ConfiguredModel(file, 0, 0, false, weight);
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
    private String name(Block block) {
        return key(block).getPath();
    }
    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath() + suffix);
    }
}
