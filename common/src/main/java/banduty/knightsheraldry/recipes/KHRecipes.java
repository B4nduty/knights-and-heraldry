package banduty.knightsheraldry.recipes;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

import java.util.function.Supplier;

public interface KHRecipes {
    Supplier<RecipeSerializer<HelmetDecoRecipe>> HELMET_DECO_SERIALIZER = registerSerializer("helmet_deco_crafting",
            new SimpleCraftingRecipeSerializer<>(HelmetDecoRecipe::new));

    Supplier<RecipeSerializer<TwoLayerDyeRecipe>> TWO_LAYER_DYE_SERIALIZER = registerSerializer("two_layer_dyeing",
            new SimpleCraftingRecipeSerializer<>(TwoLayerDyeRecipe::new));

    @SuppressWarnings("unchecked")
    static <S extends RecipeSerializer<T>, T extends Recipe<?>> Supplier<S> registerSerializer(String key, S recipeSerializer) {
        return (Supplier<S>) Services.PLATFORM.register(BuiltInRegistries.RECIPE_SERIALIZER, key, () -> recipeSerializer);
    }

    static void init() {
        KnightsHeraldry.LOG.info("Registering Recipes for " + KnightsHeraldry.MOD_ID);
    }
}