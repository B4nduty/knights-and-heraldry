package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.recipes.ModRecipes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public record HelmetDecoRecipeBuilder(ResourceLocation id, NonNullList<Ingredient> ingredients) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject json) {
        JsonArray jsonArray = new JsonArray();
        for (Ingredient ingredient : ingredients) {
            jsonArray.add(ingredient.toJson());
        }
        json.add("ingredients", jsonArray);
    }

    @Override
    public ResourceLocation getId() { return id; }

    @Override
    public RecipeSerializer<?> getType() {
        return ModRecipes.HELMET_DECO_SERIALIZER;
    }

    @Override
    public JsonObject serializeRecipe() {
        JsonObject json = new JsonObject();
        json.addProperty("type", "knightsheraldry:helmet_deco_crafting");
        serializeRecipeData(json);
        return json;
    }

    @Override public JsonObject serializeAdvancement() { return null; }
    @Override public ResourceLocation getAdvancementId() { return null; }
}