package banduty.knightsheraldry.screen;

import banduty.stoneycore.items.custom.Manuscript;
import banduty.stoneycore.recipes.CraftmanAnvilRecipe;
import banduty.stoneycore.recipes.SCRecipes;
import banduty.stoneycore.recipes.StackIngredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import java.util.*;

public final class ManuscriptRecipeIndex {

    public record Entry(CraftmanAnvilRecipe recipe, Set<Item> ingredientItems, Set<Item> manuscripts) {
        public Item resultItem() {
            return recipe.output().getItem();
        }
    }

    private final List<Entry> entries = new ArrayList<>();

    private ManuscriptRecipeIndex() {}

    public static ManuscriptRecipeIndex build(Level level) {
        ManuscriptRecipeIndex index = new ManuscriptRecipeIndex();
        for (RecipeHolder<CraftmanAnvilRecipe> holder :
                level.getRecipeManager().getAllRecipesFor(SCRecipes.CRAFTMAN_ANVIL_RECIPE_TYPE.get())) {
            CraftmanAnvilRecipe recipe = holder.value();
            Set<Item> items = new HashSet<>();
            Set<Item> manuscripts = new LinkedHashSet<>();
            for (StackIngredient ingredient : ingredientsOf(recipe)) {
                for (ItemStack stack : ingredient.asItemStacks()) {
                    items.add(stack.getItem());
                    if (stack.getItem() instanceof Manuscript) manuscripts.add(stack.getItem());
                }
            }
            index.entries.add(new Entry(recipe, items, manuscripts));
        }
        return index;
    }

    public List<Entry> recipesUsing(Item manuscript) {
        return entries.stream().filter(e -> e.manuscripts().contains(manuscript)).toList();
    }

    /**
     * Relacionados = manuscritos de recetas que producen un ingrediente de las mías
     * o que usan como ingrediente lo que produzco yo.
     */
    public List<Item> related(Item manuscript) {
        Set<Item> result = new LinkedHashSet<>();
        for (Entry mine : recipesUsing(manuscript)) {
            for (Entry other : entries) {
                if (other == mine) continue;
                boolean feedsMe = mine.ingredientItems().contains(other.resultItem());
                boolean fedByMe = other.ingredientItems().contains(mine.resultItem());
                if (feedsMe || fedByMe) result.addAll(other.manuscripts());
            }
        }
        result.remove(manuscript);
        return new ArrayList<>(result);
    }

    public static List<StackIngredient> ingredientsOf(CraftmanAnvilRecipe recipe) {
        if (recipe.pattern().isPresent()) {
            return recipe.pattern().get().slots().stream().flatMap(Optional::stream).toList();
        }
        return recipe.ingredients();
    }

    /** 6 celdas (3x2). Cada una con sus ítems posibles (varios si es un tag). */
    public static List<List<ItemStack>> gridCells(CraftmanAnvilRecipe recipe) {
        List<List<ItemStack>> cells = new ArrayList<>();
        if (recipe.pattern().isPresent()) {
            for (Optional<StackIngredient> slot : recipe.pattern().get().slots()) {
                cells.add(slot.map(StackIngredient::asItemStacks).orElse(List.of()));
            }
        } else {
            for (StackIngredient ingredient : recipe.ingredients()) {
                cells.add(ingredient.asItemStacks());
            }
        }
        while (cells.size() < CraftmanAnvilRecipe.GRID_SIZE) cells.add(List.of());
        return cells;
    }
}