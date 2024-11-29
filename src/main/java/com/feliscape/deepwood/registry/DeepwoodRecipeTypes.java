package com.feliscape.deepwood.registry;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.crafting.ArcSmeltingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DeepwoodRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_TYPE, Deepwood.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<ArcSmeltingRecipe>> ARC_SMELTING =
            SERIALIZERS.register("arc_smelting", () -> registerRecipeType("arc_smelting"));
    //public static final DeferredHolder<RecipeType<?>, RecipeType<ShapelessBakingRecipe>> SHAPELESS_CONFECTIONERY_BAKING =
    //        SERIALIZERS.register("shapeless_confectionery_baking", () -> registerRecipeType("shapeless_confectionery_baking"));

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<>()
        {
            public String toString() {
                return Deepwood.MOD_ID + ":" + identifier;
            }
        };
    }

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }

}
