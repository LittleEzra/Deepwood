package com.feliscape.deepwood.data.datagen.recipe;

import com.feliscape.deepwood.content.crafting.ArcSmeltingRecipe;
import com.feliscape.deepwood.data.datagen.builder.ArcSmeltingRecipeBuilder;
import com.feliscape.deepwood.registry.DeepwoodBlocks;
import com.feliscape.deepwood.registry.DeepwoodItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class DWRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public static final ImmutableList<ItemLike> AMBOSS_SMELTABLES = ImmutableList.of(DeepwoodItems.RAW_AMBOSS, DeepwoodBlocks.AMBOSS_ORE);

    public DWRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
        super(pRegistries, pOutput);
    }

    @Override
    protected void buildRecipes() {
        DWArcSmeltingRecipes.register(registries, output);

        this.nineBlockStorageRecipesRecipesWithCustomUnpacking(
                RecipeCategory.MISC, DeepwoodItems.AMBOSS_INGOT, RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.AMBOSS_BLOCK, "amboss_ingot_from_amboss_block", "amboss_ingot"
        );
        this.nineBlockStorageRecipesWithCustomPacking(
                RecipeCategory.MISC, DeepwoodItems.AMBOSS_NUGGET, RecipeCategory.MISC, DeepwoodItems.AMBOSS_INGOT, "amboss_ingot_from_nuggets", "amboss_ingot"
        );
        this.oreSmelting(AMBOSS_SMELTABLES, RecipeCategory.MISC, DeepwoodItems.AMBOSS_INGOT, 0.7F, 200, "amboss_ingot");
        this.oreBlasting(AMBOSS_SMELTABLES, RecipeCategory.MISC, DeepwoodItems.AMBOSS_INGOT, 0.7F, 100, "amboss_ingot");
        this.nineBlockStorageRecipes(RecipeCategory.MISC, DeepwoodItems.RAW_AMBOSS, RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.RAW_AMBOSS_BLOCK);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.POLISHED_SPIRISTONE, 4)
                .define('S', DeepwoodBlocks.COBBLED_SPIRISTONE)
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_cobbled_spiristone", this.has(DeepwoodBlocks.COBBLED_SPIRISTONE))
                .group("polished_spiristone")
                .save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.POLISHED_SPIRISTONE, 4)
                .define('S', DeepwoodBlocks.SPIRISTONE)
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_spiristone", this.has(DeepwoodBlocks.SPIRISTONE))
                .group("polished_spiristone")
                .save(this.output, "polished_spiristone_from_spiristone");
        this.shaped(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICKS, 4)
                .define('S', DeepwoodBlocks.POLISHED_SPIRISTONE)
                .pattern("SS")
                .pattern("SS")
                .unlockedBy("has_polished_spiristone", this.has(DeepwoodBlocks.POLISHED_SPIRISTONE))
                .save(this.output);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(DeepwoodBlocks.COBBLED_SPIRISTONE), RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE.asItem(), 0.1F, 200)
                .unlockedBy("has_cobbled_spiristone", this.has(DeepwoodBlocks.COBBLED_SPIRISTONE))
                .save(this.output);


        //region Spiristone
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE, DeepwoodBlocks.SPIRISTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_SLAB, DeepwoodBlocks.SPIRISTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_STAIRS, DeepwoodBlocks.SPIRISTONE);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DeepwoodBlocks.SPIRISTONE), RecipeCategory.DECORATIONS, DeepwoodBlocks.SPIRISTONE_WALL)
                .unlockedBy("has_spiristone", this.has(DeepwoodBlocks.SPIRISTONE))
                .save(this.output, "spiristone_wall_from_spiristone_stonecutting");

        this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_SLAB, Ingredient.of(DeepwoodBlocks.SPIRISTONE))
                .unlockedBy("has_spiristone", this.has(DeepwoodBlocks.SPIRISTONE))
                .save(this.output);
        this.stairBuilder(DeepwoodBlocks.SPIRISTONE_STAIRS, Ingredient.of(DeepwoodBlocks.SPIRISTONE))
                .unlockedBy("has_spiristone", this.has(DeepwoodBlocks.SPIRISTONE))
                .save(this.output);
        this.wall(RecipeCategory.DECORATIONS, DeepwoodBlocks.SPIRISTONE_WALL, DeepwoodBlocks.SPIRISTONE);
        //endregion

        //region Cobbled Spiristone
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.COBBLED_SPIRISTONE, DeepwoodBlocks.COBBLED_SPIRISTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.COBBLED_SPIRISTONE_SLAB, DeepwoodBlocks.COBBLED_SPIRISTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.COBBLED_SPIRISTONE_STAIRS, DeepwoodBlocks.COBBLED_SPIRISTONE);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DeepwoodBlocks.COBBLED_SPIRISTONE), RecipeCategory.DECORATIONS, DeepwoodBlocks.COBBLED_SPIRISTONE_WALL)
                .unlockedBy("has_cobbled_spiristone", this.has(DeepwoodBlocks.COBBLED_SPIRISTONE))
                .save(this.output, "cobbled_spiristone_wall_from_cobbled_spiristone_stonecutting");

        this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.COBBLED_SPIRISTONE_SLAB, Ingredient.of(DeepwoodBlocks.COBBLED_SPIRISTONE))
                .unlockedBy("has_cobbled_spiristone", this.has(DeepwoodBlocks.COBBLED_SPIRISTONE))
                .save(this.output);
        this.stairBuilder(DeepwoodBlocks.COBBLED_SPIRISTONE_STAIRS, Ingredient.of(DeepwoodBlocks.COBBLED_SPIRISTONE))
                .unlockedBy("has_cobbled_spiristone", this.has(DeepwoodBlocks.COBBLED_SPIRISTONE))
                .save(this.output);
        this.wall(RecipeCategory.DECORATIONS, DeepwoodBlocks.COBBLED_SPIRISTONE_WALL, DeepwoodBlocks.COBBLED_SPIRISTONE);
        //endregion

        //region Polished Spiristone
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.POLISHED_SPIRISTONE, DeepwoodBlocks.COBBLED_SPIRISTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.POLISHED_SPIRISTONE, DeepwoodBlocks.SPIRISTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.POLISHED_SPIRISTONE_SLAB, DeepwoodBlocks.SPIRISTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.POLISHED_SPIRISTONE_STAIRS, DeepwoodBlocks.SPIRISTONE);

        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.POLISHED_SPIRISTONE_SLAB, DeepwoodBlocks.POLISHED_SPIRISTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.POLISHED_SPIRISTONE_STAIRS, DeepwoodBlocks.POLISHED_SPIRISTONE);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DeepwoodBlocks.POLISHED_SPIRISTONE), RecipeCategory.DECORATIONS, DeepwoodBlocks.POLISHED_SPIRISTONE_WALL)
                .unlockedBy("has_polished_spiristone", this.has(DeepwoodBlocks.POLISHED_SPIRISTONE))
                .save(this.output, "polished_spiristone_wall_from_polished_spiristone_stonecutting");

        this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.POLISHED_SPIRISTONE_SLAB, Ingredient.of(DeepwoodBlocks.POLISHED_SPIRISTONE))
                .unlockedBy("has_polished_spiristone", this.has(DeepwoodBlocks.POLISHED_SPIRISTONE))
                .save(this.output);
        this.stairBuilder(DeepwoodBlocks.POLISHED_SPIRISTONE_STAIRS, Ingredient.of(DeepwoodBlocks.POLISHED_SPIRISTONE))
                .unlockedBy("has_polished_spiristone", this.has(DeepwoodBlocks.POLISHED_SPIRISTONE))
                .save(this.output);
        this.wall(RecipeCategory.DECORATIONS, DeepwoodBlocks.POLISHED_SPIRISTONE_WALL, DeepwoodBlocks.POLISHED_SPIRISTONE);
        //endregion

        //region Polished Spiristone
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICKS, DeepwoodBlocks.SPIRISTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICK_SLAB, DeepwoodBlocks.SPIRISTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICK_STAIRS, DeepwoodBlocks.SPIRISTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICKS, DeepwoodBlocks.POLISHED_SPIRISTONE);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICK_SLAB, DeepwoodBlocks.POLISHED_SPIRISTONE, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICK_STAIRS, DeepwoodBlocks.POLISHED_SPIRISTONE);

        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICK_SLAB, DeepwoodBlocks.SPIRISTONE_BRICKS, 2);
        this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICK_STAIRS, DeepwoodBlocks.SPIRISTONE_BRICKS);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DeepwoodBlocks.SPIRISTONE_BRICKS), RecipeCategory.DECORATIONS, DeepwoodBlocks.SPIRISTONE_BRICK_WALL)
                .unlockedBy("has_spiristone_bricks", this.has(DeepwoodBlocks.SPIRISTONE_BRICKS))
                .save(this.output, "spiristone_brick_wall_from_spiristone_bricks_stonecutting");

        this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, DeepwoodBlocks.SPIRISTONE_BRICK_SLAB, Ingredient.of(DeepwoodBlocks.SPIRISTONE_BRICKS))
                .unlockedBy("has_spiristone_bricks", this.has(DeepwoodBlocks.SPIRISTONE_BRICKS))
                .save(this.output);
        this.stairBuilder(DeepwoodBlocks.SPIRISTONE_BRICK_STAIRS, Ingredient.of(DeepwoodBlocks.SPIRISTONE_BRICKS))
                .unlockedBy("has_spiristone_bricks", this.has(DeepwoodBlocks.SPIRISTONE_BRICKS))
                .save(this.output);
        this.wall(RecipeCategory.DECORATIONS, DeepwoodBlocks.SPIRISTONE_BRICK_WALL, DeepwoodBlocks.SPIRISTONE_BRICKS);
        //endregion
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(packOutput, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider recipeProvider, RecipeOutput output) {
            return new DWRecipeProvider(recipeProvider, output);
        }

        @Override
        public String getName() {
            return "Deepwood Recipes";
        }
    }
}