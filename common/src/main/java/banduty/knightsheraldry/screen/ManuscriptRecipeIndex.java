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
    public record Entry(CraftmanAnvilRecipe recipe, Set<Item> ingredientItems, Set<Item> manuscripts,
                        List<List<ItemStack>> cells) {
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
            index.entries.add(new Entry(recipe, items, manuscripts, gridCells(recipe)));
        }
        return index;
    }

    public List<Entry> recipesUsing(Item manuscript) {
        return entries.stream().filter(e -> e.manuscripts().contains(manuscript)).toList();
    }

    public static final int MAX_TREE_DEPTH = 3;
    public static final int MAX_TREE_NODES = 80;
    public static final int MAX_CHILDREN = 8;

    public record TreeNode(Item item, Item parent, List<Entry> recipes, int depth, List<TreeNode> children) {}

    public TreeNode tree(Item manuscript) {
        int[] budget = {MAX_TREE_NODES};
        List<TreeNode> children = expand(manuscript, recipesUsing(manuscript), Set.of(manuscript), 1, budget);
        return new TreeNode(manuscript, null, List.of(), 0, children);
    }

    private List<TreeNode> expand(Item parent, List<Entry> producers, Set<Item> path, int depth, int[] budget) {
        Map<Item, List<Entry>> byResult = new LinkedHashMap<>();
        for (Entry e : producers) {
            Item result = e.resultItem();
            if (path.contains(result)) continue;
            byResult.computeIfAbsent(result, k -> new ArrayList<>()).add(e);
        }

        List<TreeNode> nodes = new ArrayList<>();
        for (Map.Entry<Item, List<Entry>> me : byResult.entrySet()) {
            if (nodes.size() >= MAX_CHILDREN || budget[0] <= 0) break;
            budget[0]--;
            Item result = me.getKey();

            List<TreeNode> kids = List.of();
            if (depth < MAX_TREE_DEPTH) {
                Set<Item> next = new HashSet<>(path);
                next.add(result);
                List<Entry> consumers = entries.stream()
                        .filter(e -> e.ingredientItems().contains(result))
                        .toList();
                kids = expand(result, consumers, next, depth + 1, budget);
            }
            nodes.add(new TreeNode(result, parent, List.copyOf(me.getValue()), depth, kids));
        }
        return nodes;
    }

    public static List<StackIngredient> ingredientsOf(CraftmanAnvilRecipe recipe) {
        if (recipe.pattern().isPresent()) {
            return recipe.pattern().get().slots().stream().flatMap(Optional::stream).toList();
        }
        return recipe.ingredients();
    }

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