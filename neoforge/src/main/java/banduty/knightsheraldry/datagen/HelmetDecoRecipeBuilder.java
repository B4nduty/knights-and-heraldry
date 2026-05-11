package banduty.knightsheraldry.datagen;

import banduty.knightsheraldry.recipes.HelmetDecoRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
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

public class HelmetDecoRecipeBuilder implements RecipeBuilder {
    private final NonNullList<Ingredient> ingredients;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    public HelmetDecoRecipeBuilder(NonNullList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public static HelmetDecoRecipeBuilder decoration(NonNullList<Ingredient> ingredients) {
        return new HelmetDecoRecipeBuilder(ingredients);
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
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

        this.criteria.forEach(advancementBuilder::addCriterion);

        AdvancementHolder advancementHolder = advancementBuilder.build(id.withPrefix("recipes/"));

        HelmetDecoRecipe recipe = new HelmetDecoRecipe(CraftingBookCategory.EQUIPMENT);

        exporter.accept(id, recipe, advancementHolder);
    }
}