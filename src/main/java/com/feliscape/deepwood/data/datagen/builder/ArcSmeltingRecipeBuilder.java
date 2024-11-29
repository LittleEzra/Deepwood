package com.feliscape.deepwood.data.datagen.builder;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.content.crafting.ArcSmeltingRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.LinkedHashMap;
import java.util.Map;
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ArcSmeltingRecipeBuilder implements RecipeBuilder {
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Item result;
    private final ItemStack resultStack;
    private final Item base;
    private final int smeltTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    private ArcSmeltingRecipeBuilder(ItemLike base, ItemLike result, int count, int smeltTime){
        this(base.asItem(), new ItemStack(result, count), smeltTime);
    }
    private ArcSmeltingRecipeBuilder(Item base, ItemStack result, int smeltTime){
        this.resultStack = result;
        this.result = result.getItem();
        this.smeltTime = smeltTime;
        this.base = base;
    }

    public static ArcSmeltingRecipeBuilder smelting(ItemLike base, ItemLike result, int count, int bakingTime){
        return new ArcSmeltingRecipeBuilder(base, result, count, bakingTime);
    }
    public static ArcSmeltingRecipeBuilder smelting(ItemLike base, ItemLike result, int bakingTime){
        return new ArcSmeltingRecipeBuilder(base, result, 1, bakingTime);
    }

    public ArcSmeltingRecipeBuilder requires(ItemLike item){
        return requires(item, 1);
    }
    public ArcSmeltingRecipeBuilder requires(ItemLike item, int quantity){
        return requires(Ingredient.of(item), quantity);
    }
    public ArcSmeltingRecipeBuilder requires(Ingredient ingredient){
        return requires(ingredient, 1);
    }

    public ArcSmeltingRecipeBuilder requires(Ingredient ingredient, int quantity){
        for (int i = 0; i < quantity; i++){
            ingredients.add(ingredient);
        }
        return this;
    }

    @Override
    public ArcSmeltingRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger) {
        this.criteria.put(criterionName, criterionTrigger);
        return this;
    }

    public ArcSmeltingRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
        return unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
    }

    public ArcSmeltingRecipeBuilder unlockedByAnyIngredient(HolderGetter<Item> getter, ItemLike... items) {
        this.criteria.put("has_any_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(getter, items).build()));
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String s) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.result.asItem();
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> key) {
        Advancement.Builder advancementBuilder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(key))
                .rewards(AdvancementRewards.Builder.recipe(key))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancementBuilder::addCriterion);
        ArcSmeltingRecipe recipe = new ArcSmeltingRecipe(
                Ingredient.of(this.base),
                this.ingredients,
                this.resultStack,
                this.smeltTime
        );
        recipeOutput.accept(key, recipe, advancementBuilder.build(key.location()));
    }

    public void build(RecipeOutput output) {
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(result.asItem());
        save(output, ResourceKey.create(Registries.RECIPE, Deepwood.asResource(location.getPath())));
    }

    public void build(RecipeOutput outputIn, String save) {
        ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(result.asItem());
        if ((ResourceLocation.parse(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Arc Smelting Recipe " + save + " should remove its 'save' argument");
        } else {
            save(outputIn, ResourceKey.create(Registries.RECIPE, ResourceLocation.parse(save)));
        }
    }
}