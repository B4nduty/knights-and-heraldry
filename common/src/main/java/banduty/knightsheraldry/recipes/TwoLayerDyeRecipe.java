package banduty.knightsheraldry.recipes;

import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class TwoLayerDyeRecipe extends CustomRecipe {

    public TwoLayerDyeRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        ItemStack dyeable = ItemStack.EMPTY;
        int dyeCount = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof TwoLayerDyeableItem) {
                if (!dyeable.isEmpty()) return false;
                dyeable = stack;
            } else if (stack.getItem() instanceof DyeItem) {
                dyeCount++;
            } else {
                return false;
            }
        }

        return !dyeable.isEmpty() && dyeCount > 0 && dyeCount <= 2;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack dyeableStack = ItemStack.EMPTY;
        List<Integer> dyes = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof TwoLayerDyeableItem) {
                dyeableStack = stack.copy();
            } else if (stack.getItem() instanceof DyeItem dye) {
                dyes.add(dye.getDyeColor().getTextureDiffuseColor());
            }
        }

        if (dyeableStack.isEmpty() || dyes.isEmpty()) return ItemStack.EMPTY;

        boolean hasColor1 = dyeableStack.has(KHDataComponents.COLOR_1.get());
        boolean hasColor2 = dyeableStack.has(KHDataComponents.COLOR_2.get());

        if (dyes.size() == 2) {
            dyeableStack.set(KHDataComponents.COLOR_1.get(), new DyedItemColor(dyes.get(0), true));
            dyeableStack.set(KHDataComponents.COLOR_2.get(), new DyedItemColor(dyes.get(1), true));
        } else {
            int newColor = dyes.getFirst();
            if (!hasColor1) {
                dyeableStack.set(KHDataComponents.COLOR_1.get(), new DyedItemColor(newColor, true));
            } else if (!hasColor2) {
                dyeableStack.set(KHDataComponents.COLOR_2.get(), new DyedItemColor(newColor, true));
            } else {
                // overwrite second if both exist
                dyeableStack.set(KHDataComponents.COLOR_2.get(), new DyedItemColor(newColor, true));
            }
        }

        return dyeableStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KHRecipes.TWO_LAYER_DYE_SERIALIZER.get();
    }
}