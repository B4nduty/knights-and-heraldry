package banduty.knightsheraldry.recipes;

import banduty.knightsheraldry.KnightsHeraldry;
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
}
