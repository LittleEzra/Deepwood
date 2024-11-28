package com.feliscape.deepwood.data.datagen.loot;

import com.feliscape.deepwood.registry.DeepwoodBlocks;
import com.feliscape.deepwood.registry.DeepwoodItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class DWBlockLootTableProvider extends BlockLootSubProvider {
    public DWBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(DeepwoodBlocks.DEEP_SOIL.get());
        this.dropOtherWithoutSilkTouch(DeepwoodBlocks.SPIRIT_GRASS_BLOCK.get(), DeepwoodBlocks.DEEP_SOIL);
        this.add(DeepwoodBlocks.AMBOSS_ORE.get(), b -> this.createOreDrop(b, DeepwoodItems.RAW_AMBOSS.get()));
        this.dropSelf(DeepwoodBlocks.AMBOSS_BLOCK.get());
        this.dropSelf(DeepwoodBlocks.RAW_AMBOSS_BLOCK.get());
        this.dropSelf(DeepwoodBlocks.WRAITH_AMBOSS_BLOCK.get());

        this.dropOtherWithoutSilkTouch(DeepwoodBlocks.SPIRISTONE.get(), DeepwoodBlocks.COBBLED_SPIRISTONE);
        this.dropSelf(DeepwoodBlocks.SPIRISTONE_STAIRS.get());
        this.dropSelf(DeepwoodBlocks.SPIRISTONE_WALL.get());
        this.add(DeepwoodBlocks.SPIRISTONE_SLAB.get(), this::createSlabItemTable);

        this.dropSelf(DeepwoodBlocks.COBBLED_SPIRISTONE.get());
        this.dropSelf(DeepwoodBlocks.COBBLED_SPIRISTONE_STAIRS.get());
        this.dropSelf(DeepwoodBlocks.COBBLED_SPIRISTONE_WALL.get());
        this.add(DeepwoodBlocks.COBBLED_SPIRISTONE_SLAB.get(), this::createSlabItemTable);

        this.dropSelf(DeepwoodBlocks.POLISHED_SPIRISTONE.get());
        this.dropSelf(DeepwoodBlocks.POLISHED_SPIRISTONE_STAIRS.get());
        this.dropSelf(DeepwoodBlocks.POLISHED_SPIRISTONE_WALL.get());
        this.add(DeepwoodBlocks.POLISHED_SPIRISTONE_SLAB.get(), this::createSlabItemTable);

        this.dropSelf(DeepwoodBlocks.SPIRISTONE_BRICKS.get());
        this.dropSelf(DeepwoodBlocks.SPIRISTONE_BRICK_STAIRS.get());
        this.dropSelf(DeepwoodBlocks.SPIRISTONE_BRICK_WALL.get());
        this.add(DeepwoodBlocks.SPIRISTONE_BRICK_SLAB.get(), this::createSlabItemTable);
    }

    protected void dropOtherWithoutSilkTouch(Block block, ItemLike other){
        this.add(block, b -> this.createSingleItemTableWithSilkTouch(b, other));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return DeepwoodBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
