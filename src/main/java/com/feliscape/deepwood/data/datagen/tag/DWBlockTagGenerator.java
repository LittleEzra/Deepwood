package com.feliscape.deepwood.data.datagen.tag;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.registry.DeepwoodBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DWBlockTagGenerator extends BlockTagsProvider {
    public DWBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Deepwood.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(DeepwoodBlocks.AMBOSS_ORE.get())
                .add(DeepwoodBlocks.AMBOSS_BLOCK.get())
                .add(DeepwoodBlocks.RAW_AMBOSS_BLOCK.get())

                .add(DeepwoodBlocks.SPIRISTONE.get())
                .add(DeepwoodBlocks.SPIRISTONE_STAIRS.get())
                .add(DeepwoodBlocks.SPIRISTONE_SLAB.get())
                .add(DeepwoodBlocks.SPIRISTONE_WALL.get())

                .add(DeepwoodBlocks.COBBLED_SPIRISTONE.get())
                .add(DeepwoodBlocks.COBBLED_SPIRISTONE_STAIRS.get())
                .add(DeepwoodBlocks.COBBLED_SPIRISTONE_SLAB.get())
                .add(DeepwoodBlocks.COBBLED_SPIRISTONE_WALL.get())

                .add(DeepwoodBlocks.POLISHED_SPIRISTONE.get())
                .add(DeepwoodBlocks.POLISHED_SPIRISTONE_STAIRS.get())
                .add(DeepwoodBlocks.POLISHED_SPIRISTONE_SLAB.get())
                .add(DeepwoodBlocks.POLISHED_SPIRISTONE_WALL.get())

                .add(DeepwoodBlocks.SPIRISTONE_BRICKS.get())
                .add(DeepwoodBlocks.SPIRISTONE_BRICK_STAIRS.get())
                .add(DeepwoodBlocks.SPIRISTONE_BRICK_SLAB.get())
                .add(DeepwoodBlocks.SPIRISTONE_BRICK_WALL.get())
                ;
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(DeepwoodBlocks.DEEP_SOIL.get())
                .add(DeepwoodBlocks.SPIRIT_GRASS_BLOCK.get())
                ;
        this.tag(BlockTags.WALLS)
                .add(DeepwoodBlocks.SPIRISTONE_WALL.get())
                .add(DeepwoodBlocks.COBBLED_SPIRISTONE_WALL.get())
                .add(DeepwoodBlocks.POLISHED_SPIRISTONE_WALL.get())
                .add(DeepwoodBlocks.SPIRISTONE_BRICK_WALL.get())
                ;
    }
}
