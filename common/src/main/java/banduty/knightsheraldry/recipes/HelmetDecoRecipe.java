package banduty.knightsheraldry.recipes;

import banduty.knightsheraldry.items.DecoableItem;
import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.platform.Services;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class HelmetDecoRecipe extends CustomRecipe {

    private static final Ingredient DECO_ITEMS = Ingredient.of(
            HelmetDeco.getRegisteredItems().stream().map(ItemStack::new)
    );

    public HelmetDecoRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        int baseCount = 0;
        int decoCount = 0;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof DecoableItem) {
                baseCount++;
            } else if (DECO_ITEMS.test(stack)) {
                decoCount++;
            } else {
                return false;
            }
        }

        return baseCount == 1 && decoCount > 0;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack baseItem = ItemStack.EMPTY;

        // Find base helmet
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof DecoableItem) {
                baseItem = stack.copyWithCount(1);
                break;
            }
        }

        if (baseItem.isEmpty()) return ItemStack.EMPTY;

        CompoundTag rootTag = baseItem.getOrCreateTag();
        CompoundTag decoTag = rootTag.getCompound("HelmetDeco");

        // Apply each deco ingredient
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack ingredient = container.getItem(i);
            if (ingredient.isEmpty() || ingredient.getItem() instanceof DecoableItem) continue;

            HelmetDeco.getDecoFromItem(ingredient.getItem()).ifPresent(deco -> {

                // Remove existing deco in same group
                for (HelmetDeco other : HelmetDeco.getValues()) {
                    if (other.group() == deco.group()) {
                        decoTag.remove(other.getNbtKey());
                    }
                }

                applyIngredientToTag(decoTag, deco, ingredient);
            });
        }

        rootTag.put("HelmetDeco", decoTag);
        return baseItem;
    }

    private void applyIngredientToTag(CompoundTag decoTag, HelmetDeco deco, ItemStack ingredient) {

        switch (deco.color()) {

            // Two-layer dyeable
            case 2 -> {
                if (ingredient.getItem() instanceof TwoLayerDyeableItem twoLayer) {
                    CompoundTag colorTag = new CompoundTag();
                    colorTag.putInt("color1", twoLayer.getColor1(ingredient));
                    colorTag.putInt("color2", twoLayer.getColor2(ingredient));
                    decoTag.put(deco.getNbtKey(), colorTag);
                }
            }

            // Single color (like leather dye)
            case 1 -> {
                CompoundTag display = ingredient.getTagElement("display");
                int color = (display != null && display.contains("color", 99))
                        ? display.getInt("color")
                        : 0xFFFFFF;

                decoTag.putInt(deco.getNbtKey(), color);
            }

            // Boolean deco (no color)
            default -> decoTag.putBoolean(deco.getNbtKey(), true);
        }
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Services.PLATFORM.getHelmetDecoRecipe();
    }
}