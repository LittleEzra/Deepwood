package com.feliscape.deepwood.content.crafting;

import com.feliscape.deepwood.registry.DeepwoodRecipeSerializers;
import com.feliscape.deepwood.registry.DeepwoodRecipeTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ArcSmeltingRecipe implements Recipe<RecipeWrapper> {

    public static final int INPUT_SLOTS = 3;
    final Ingredient base;
    private final NonNullList<Ingredient> ingredients;
    final ItemStack result;
    final int smeltTime;
    private final boolean isSimple;
    @Nullable
    private PlacementInfo placementInfo;

    public ArcSmeltingRecipe(Ingredient base, NonNullList<Ingredient> ingredients, ItemStack result, int smeltTime) {
        this.base = base;
        this.ingredients = ingredients;
        this.result = result;
        this.smeltTime = smeltTime;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level level) {
        java.util.List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for (int j = 0; j < INPUT_SLOTS; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }
        return inv.getItem(3).is(this.result.getItem()) && i == this.ingredients.size() && RecipeMatcher.findMatches(inputs, this.ingredients) != null;
    }

    public int getSmeltTime(){
        return smeltTime;
    }

    @Override
    public ItemStack assemble(RecipeWrapper input, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    public ItemStack getResult(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public RecipeSerializer<? extends Recipe<RecipeWrapper>> getSerializer() {
        return DeepwoodRecipeSerializers.ARC_SMELTING.get();
    }

    @Override
    public RecipeType<? extends Recipe<RecipeWrapper>> getType() {
        return DeepwoodRecipeTypes.ARC_SMELTING.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        if (placementInfo == null){
            this.placementInfo = PlacementInfo.create(ingredients);
        }
        return this.placementInfo;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public static class Serializer implements RecipeSerializer<ArcSmeltingRecipe> {
        private static final MapCodec<ArcSmeltingRecipe> CODEC = RecordCodecBuilder.mapCodec((apply) -> {
            return apply.group(Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                    Ingredient.CODEC.listOf().fieldOf("ingredients").flatXmap((array) -> {
                Ingredient[] aingredient = array.toArray(Ingredient[]::new);
                if (aingredient.length == 0) {
                    return DataResult.error(() -> "No ingredients for shapeless recipe");
                } else {
                    return aingredient.length > 3 ? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(9)) : DataResult.success(NonNullList.of(Ingredient.of(), aingredient));
                }
            }, DataResult::success).forGetter((shapelessBakingRecipe) -> {
                return shapelessBakingRecipe.ingredients;
            }), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((recipe) -> recipe.result),
                Codec.INT.fieldOf("baking_time").forGetter((recipe) -> recipe.smeltTime))
                    .apply(apply, ArcSmeltingRecipe::new);
        });
        public static final StreamCodec<RegistryFriendlyByteBuf, ArcSmeltingRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);


        @Override
        public @NotNull MapCodec<ArcSmeltingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, ArcSmeltingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static ArcSmeltingRecipe fromNetwork(RegistryFriendlyByteBuf byteBuf) {
            Ingredient base = Ingredient.CONTENTS_STREAM_CODEC.decode(byteBuf);

            int ingredientCount = byteBuf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientCount, Ingredient.of());
            ingredients.replaceAll((ingredient) -> Ingredient.CONTENTS_STREAM_CODEC.decode(byteBuf));

            int smeltTime = byteBuf.readInt();
            ItemStack result = ItemStack.STREAM_CODEC.decode(byteBuf);

            return new ArcSmeltingRecipe(base, ingredients, result, smeltTime);
        }

        private static void toNetwork(RegistryFriendlyByteBuf byteBuf, ArcSmeltingRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(byteBuf, recipe.base);
            byteBuf.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(byteBuf, ingredient);
            }
            byteBuf.writeInt(recipe.smeltTime);
            ItemStack.STREAM_CODEC.encode(byteBuf, recipe.result);
        }
    }
}


/* SHAPED VERSION
public class ConfectioneryBakingRecipe implements CraftingRecipe {
    static int MAX_WIDTH = 3;
    static int MAX_HEIGHT = 3;
public static void setCraftingSize(int width, int height) {
    if (MAX_WIDTH < width) MAX_WIDTH = width;
    if (MAX_HEIGHT < height) MAX_HEIGHT = height;
}
final ShapedRecipePattern pattern;
final ItemStack result;
final String group;
final int bakingTime;
final CraftingBookCategory category;
final boolean showNotification;

public ConfectioneryBakingRecipe(String pGroup, CraftingBookCategory pCategory, ShapedRecipePattern pPattern, ItemStack pResult, int pBakingTime, boolean pShowNotification) {
    this.group = pGroup;
    this.category = pCategory;
    this.pattern = pPattern;
    this.result = pResult;
    this.showNotification = pShowNotification;
    this.bakingTime = pBakingTime;
}

public ConfectioneryBakingRecipe(String pGroup, CraftingBookCategory pCategory, ShapedRecipePattern pPattern, ItemStack pResult, int pBakingTime) {
    this(pGroup, pCategory, pPattern, pResult, pBakingTime, true);
}

@Override
public RecipeSerializer<?> getSerializer() {
    return WinterShineRecipeSerializers.CONFECTIONERY_BAKING.get();
}

@Override
public String getGroup() {
    return this.group;
}

@Override
public CraftingBookCategory category() {
    return this.category;
}

@Override
public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
    return this.result;
}

public int getBakingTime() {
    return this.bakingTime;
}

@Override
public NonNullList<Ingredient> getIngredients() {
    return this.pattern.ingredients();
}

@Override
public boolean showNotification() {
    return this.showNotification;
}

@Override
public boolean canCraftInDimensions(int pWidth, int pHeight) {
    return pWidth >= this.pattern.width() && pHeight >= this.pattern.height();
}

public boolean matches(CraftingInput pInput, Level pLevel) {
    return this.pattern.matches(pInput);
}

public ItemStack assemble(CraftingInput pInput, HolderLookup.Provider pRegistries) {
    return this.getResultItem(pRegistries).copy();
}

public int getWidth() {
    return this.pattern.width();
}

public int getHeight() {
    return this.pattern.height();
}

@Override
public RecipeType<?> getType() {
    return WinterShineRecipeTypes.CONFECTIONERY_BAKING.get();
}

@Override
public boolean isIncomplete() {
    NonNullList<Ingredient> nonnulllist = this.getIngredients();
    return nonnulllist.isEmpty() || nonnulllist.stream().filter((p_151277_) -> {
        return !p_151277_.isEmpty();
    }).anyMatch(Ingredient::hasNoItems);
}

public static class Serializer implements RecipeSerializer<ConfectioneryBakingRecipe> {
    public static final MapCodec<ConfectioneryBakingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            recipeInstance -> recipeInstance.group(
                            Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                            CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(recipe -> recipe.category),
                            ShapedRecipePattern.MAP_CODEC.forGetter(shapedRecipe -> shapedRecipe.pattern),
                            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(confectioneryBakingRecipe -> confectioneryBakingRecipe.result),
                            Codec.INT.fieldOf("baking_time").orElse(400).forGetter(recipe -> recipe.bakingTime),
                            Codec.BOOL.optionalFieldOf("show_notification", Boolean.valueOf(true)).forGetter(recipe -> recipe.showNotification)
                    )
                    .apply(recipeInstance, ConfectioneryBakingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, ConfectioneryBakingRecipe> STREAM_CODEC = StreamCodec.of(
            Serializer::toNetwork, Serializer::fromNetwork
    );

    @Override
    public MapCodec<ConfectioneryBakingRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ConfectioneryBakingRecipe> streamCodec() {
        return STREAM_CODEC;
    }

    private static ConfectioneryBakingRecipe fromNetwork(RegistryFriendlyByteBuf byteBuf) {
        String s = byteBuf.readUtf();
        CraftingBookCategory craftingbookcategory = byteBuf.readEnum(CraftingBookCategory.class);
        ShapedRecipePattern shapedrecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(byteBuf);
        ItemStack itemstack = ItemStack.STREAM_CODEC.decode(byteBuf);
        int bakingTime = byteBuf.readInt();
        boolean showNotification = byteBuf.readBoolean();
        return new ConfectioneryBakingRecipe(s, craftingbookcategory, shapedrecipepattern, itemstack, bakingTime, showNotification);
    }

    private static void toNetwork(RegistryFriendlyByteBuf byteBuf, ConfectioneryBakingRecipe recipe) {
        byteBuf.writeUtf(recipe.group);
        byteBuf.writeEnum(recipe.category);
        ShapedRecipePattern.STREAM_CODEC.encode(byteBuf, recipe.pattern);
        ItemStack.STREAM_CODEC.encode(byteBuf, recipe.result);
        byteBuf.writeInt(recipe.bakingTime);
        byteBuf.writeBoolean(recipe.showNotification);
    }
}
}

 */