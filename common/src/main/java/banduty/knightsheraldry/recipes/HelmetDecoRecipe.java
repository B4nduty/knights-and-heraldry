package banduty.knightsheraldry.recipes;

import banduty.knightsheraldry.items.DecoableItem;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class HelmetDecoRecipe extends CustomRecipe {

    public HelmetDecoRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        int baseCount = 0;
        int decoCount = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof DecoableItem) {
                baseCount++;
            } else if (HelmetDeco.getFromItem(stack.getItem()).isPresent()) {
                decoCount++;
            } else {
                return false;
            }
        }

        return baseCount == 1 && decoCount == 1;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack baseItem = ItemStack.EMPTY;
        ItemStack decoStack = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof DecoableItem) {
                baseItem = stack.copy();
            } else if (HelmetDeco.getFromItem(stack.getItem()).isPresent()) {
                decoStack = stack;
            }
        }

        if (baseItem.isEmpty() || decoStack.isEmpty()) return ItemStack.EMPTY;

        HelmetDeco definition = HelmetDeco.getFromItem(decoStack.getItem()).orElse(null);
        if (definition == null) return ItemStack.EMPTY;

        List<Integer> color = HelmetDeco.getColorFromStack(decoStack, definition.colorN(), -1);

        HelmetDeco resultDeco = new HelmetDeco(definition.item(), definition.colorN(), color, definition.group());
        baseItem.set(KHDataComponents.HELMET_DECO.get(), resultDeco);

        return baseItem;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KHRecipes.HELMET_DECO_SERIALIZER.get();
    }
}