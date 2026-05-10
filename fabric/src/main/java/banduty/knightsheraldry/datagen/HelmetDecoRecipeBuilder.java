package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.recipes.HelmetDecoRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public record HelmetDecoRecipeBuilder(NonNullList<Ingredient> ingredients) implements RecipeBuilder {

    private static final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        criteria.put(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getResult() {
        return Items.AIR;
    }

    @Override
    public void save(RecipeOutput exporter, ResourceLocation id) {
        Advancement.Builder advancementBuilder = exporter.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);

        criteria.forEach(advancementBuilder::addCriterion);

        HelmetDecoRecipe recipe = new HelmetDecoRecipe(CraftingBookCategory.EQUIPMENT);

        exporter.accept(id, recipe, advancementBuilder.build(id.withPrefix("recipes/")));
    }
}