package com.feliscape.deepwood.registry;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.crafting.ArcSmeltingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DeepwoodRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Deepwood.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ArcSmeltingRecipe>> ARC_SMELTING =
            SERIALIZERS.register("arc_smelting", ArcSmeltingRecipe.Serializer::new);
    //public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ShapelessBakingRecipe>> SHAPELESS_CONFECTIONERY_BAKING =
    //        SERIALIZERS.register("shapeless_confectionery_baking", ShapelessBakingRecipe.Serializer::new);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
