package com.feliscape.deepwood.data.datagen.recipe;

import com.feliscape.deepwood.data.datagen.builder.ArcSmeltingRecipeBuilder;
import com.feliscape.deepwood.registry.DeepwoodItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;

public class DWArcSmeltingRecipes {
    public static void register(HolderLookup.Provider pRegistries, RecipeOutput output){
        var itemHolderGetter = pRegistries.lookupOrThrow(Registries.ITEM);

        ArcSmeltingRecipeBuilder.smelting(DeepwoodItems.AMBOSS_INGOT, DeepwoodItems.WRAITH_AMBOSS_INGOT, 200)
                .requires(DeepwoodItems.OLD_BONES, 3)
                .unlockedByAnyIngredient(itemHolderGetter, DeepwoodItems.OLD_BONES);
    }
}
