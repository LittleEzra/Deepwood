package com.feliscape.deepwood.registry;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.block.SpiritGrassBlock;
import com.mojang.datafixers.kinds.Const;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class DeepwoodBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Deepwood.MOD_ID);

    public static final DeferredBlock<Block> DEEP_SOIL = registerBlock("deep_soil",
            b -> new Block(b.mapColor(MapColor.COLOR_BLUE).strength(0.5F).sound(SoundType.GRAVEL)));
    public static final DeferredBlock<Block> SPIRIT_GRASS_BLOCK = registerBlock("spirit_grass_block",
            b -> new SpiritGrassBlock(b.randomTicks().strength(0.6F).sound(SoundType.GRASS).mapColor(MapColor.COLOR_BLUE)));
    public static final DeferredBlock<Block> AMBOSS_ORE = registerBlock("amboss_ore",
            b -> new DropExperienceBlock(ConstantInt.of(0), b.mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> AMBOSS_BLOCK = registerBlock("amboss_block",
            b -> new DropExperienceBlock(ConstantInt.of(0), b.mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> RAW_AMBOSS_BLOCK = registerBlock("raw_amboss_block",
            b -> new Block(b.mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> WRAITH_AMBOSS_BLOCK = registerBlock("wraith_amboss_block",
            b -> new DropExperienceBlock(ConstantInt.of(0), b.mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.COW_BELL)
                    .requiresCorrectToolForDrops().strength(8.0F, 1200.0F).sound(SoundType.METAL)));

    // SPIRISTONE PALETTE

    // Normal
    public static final DeferredBlock<Block> SPIRISTONE = registerBlock("spiristone",
            b -> new Block(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)
            ));
    public static final DeferredBlock<StairBlock> SPIRISTONE_STAIRS = registerBlock("spiristone_stairs",
            b -> stair(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F), SPIRISTONE.get()));
    public static final DeferredBlock<SlabBlock> SPIRISTONE_SLAB = registerBlock("spiristone_slab",
            b -> new SlabBlock(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)
            ));
    public static final DeferredBlock<WallBlock> SPIRISTONE_WALL = registerBlock("spiristone_wall",
            b -> new WallBlock(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));

    // Cobbled
    public static final DeferredBlock<Block> COBBLED_SPIRISTONE = registerBlock("cobbled_spiristone",
            b -> new Block(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.2F, 6.0F)
            ));
    public static final DeferredBlock<StairBlock> COBBLED_SPIRISTONE_STAIRS = registerBlock("cobbled_spiristone_stairs",
            b -> stair(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.2F, 6.0F), SPIRISTONE.get()));
    public static final DeferredBlock<SlabBlock> COBBLED_SPIRISTONE_SLAB = registerBlock("cobbled_spiristone_slab",
            b -> new SlabBlock(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.2F, 6.0F)
            ));
    public static final DeferredBlock<WallBlock> COBBLED_SPIRISTONE_WALL = registerBlock("cobbled_spiristone_wall",
            b -> new WallBlock(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.2F, 6.0F)));

    // Polished
    public static final DeferredBlock<Block> POLISHED_SPIRISTONE = registerBlock("polished_spiristone",
            b -> new Block(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)
            ));
    public static final DeferredBlock<StairBlock> POLISHED_SPIRISTONE_STAIRS = registerBlock("polished_spiristone_stairs",
            b -> stair(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F), POLISHED_SPIRISTONE.get()));
    public static final DeferredBlock<SlabBlock> POLISHED_SPIRISTONE_SLAB = registerBlock("polished_spiristone_slab",
            b -> new SlabBlock(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)
            ));
    public static final DeferredBlock<WallBlock> POLISHED_SPIRISTONE_WALL = registerBlock("polished_spiristone_wall",
            b -> new WallBlock(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));

    // Bricks
    public static final DeferredBlock<Block> SPIRISTONE_BRICKS = registerBlock("spiristone_bricks",
            b -> new Block(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)
            ));
    public static final DeferredBlock<StairBlock> SPIRISTONE_BRICK_STAIRS = registerBlock("spiristone_brick_stairs",
            b -> stair(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F), SPIRISTONE_BRICKS.get()));
    public static final DeferredBlock<SlabBlock> SPIRISTONE_BRICK_SLAB = registerBlock("spiristone_brick_slab",
            b -> new SlabBlock(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)
            ));
    public static final DeferredBlock<WallBlock> SPIRISTONE_BRICK_WALL = registerBlock("spiristone_brick_wall",
            b -> new WallBlock(b.mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block)
    {
        return DeepwoodItems.ITEMS.registerItem(name, p -> new BlockItem(block.get(), p));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    private static StairBlock stair(BlockBehaviour.Properties properties, Block baseBlock) {
        return new StairBlock(baseBlock.defaultBlockState(), properties);
    }
}
