package banduty.knightsheraldry.recipe;

import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class TwoLayerDyeRecipe extends CustomRecipe {

    public TwoLayerDyeRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {

        ItemStack dyeable = ItemStack.EMPTY;
        int dyeCount = 0;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);

            if (stack.isEmpty())
                continue;

            if (stack.getItem() instanceof TwoLayerDyeableItem) {
                if (!dyeable.isEmpty())
                    return false; // multiple dyeable items
                dyeable = stack;
            } else if (stack.getItem() instanceof DyeItem) {
                dyeCount++;
            } else {
                return false; // invalid ingredient
            }
        }

        return !dyeable.isEmpty() && dyeCount > 0 && dyeCount <= 2;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {

        ItemStack dyeableStack = ItemStack.EMPTY;
        int dyeCount = 0;
        int[] dyes = new int[2];

        for (int i = 0; i < container.getContainerSize(); i++) {

            ItemStack stack = container.getItem(i);
            if (stack.isEmpty())
                continue;

            if (stack.getItem() instanceof TwoLayerDyeableItem) {
                dyeableStack = stack.copy();
            } else if (stack.getItem() instanceof DyeItem dye) {

                if (dyeCount < 2) {
                    dyes[dyeCount] = getColorFromComponents(
                            dye.getDyeColor().getTextureDiffuseColors()
                    );
                    dyeCount++;
                }
            }
        }

        if (dyeableStack.isEmpty() || dyeCount == 0)
            return ItemStack.EMPTY;

        TwoLayerDyeableItem item =
                (TwoLayerDyeableItem) dyeableStack.getItem();

        boolean hasColor1 = dyeableStack.hasTag()
                && dyeableStack.getTag().contains("color1");

        boolean hasColor2 = dyeableStack.hasTag()
                && dyeableStack.getTag().contains("color2");

        /* ============================= */
        /* ===== TWO DYES: OVERRIDE ==== */
        /* ============================= */

        if (dyeCount == 2) {
            item.setColor1(dyeableStack, dyes[0]);
            item.setColor2(dyeableStack, dyes[1]);
            return dyeableStack;
        }

        /* ============================= */
        /* ===== ONE DYE: SMART FILL ==== */
        /* ============================= */

        int newColor = dyes[0];

        if (!hasColor1) {
            item.setColor1(dyeableStack, newColor);
        } else if (!hasColor2) {
            item.setColor2(dyeableStack, newColor);
        } else {
            // both exist → overwrite second
            item.setColor2(dyeableStack, newColor);
        }

        return dyeableStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Services.PLATFORM.getTwoLayerDyeRecipe();
    }

    private static int getColorFromComponents(float[] components) {
        int r = Math.round(components[0] * 255);
        int g = Math.round(components[1] * 255);
        int b = Math.round(components[2] * 255);
        return (r << 16) | (g << 8) | b;
    }
}