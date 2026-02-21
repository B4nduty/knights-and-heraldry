package banduty.knightsheraldry.recipes;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.recipe.TwoLayerDyeRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public interface ModRecipes {
    static void registerRecipes() {
        KnightsHeraldry.LOG.info("Registering ModRecipes for " + KnightsHeraldry.MOD_ID);
    }

    RecipeSerializer<HelmetDecoRecipe> HELMET_DECO_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            new ResourceLocation(KnightsHeraldry.MOD_ID, "helmet_deco_crafting"),
            new SimpleCraftingRecipeSerializer<>(HelmetDecoRecipe::new));

    RecipeSerializer<TwoLayerDyeRecipe> TWO_LAYER_DYE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            new ResourceLocation(KnightsHeraldry.MOD_ID, "two_layer_dyeing"),
            new SimpleCraftingRecipeSerializer<>(TwoLayerDyeRecipe::new));

    RecipeSerializer<SurcoatBannerRecipe> SURCOAT_BANNER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            new ResourceLocation(KnightsHeraldry.MOD_ID, "surcoat_banner"),
            new SimpleCraftingRecipeSerializer<>(SurcoatBannerRecipe::new));
}
