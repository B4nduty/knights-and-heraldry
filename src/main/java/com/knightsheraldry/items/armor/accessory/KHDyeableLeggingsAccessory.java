package com.knightsheraldry.items.armor.accessory;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

public class KHDyeableLeggingsAccessory extends KHLeggingsAccessory implements DyeableItem {
    boolean overlay;
    int defaultColor;

    public KHDyeableLeggingsAccessory(Settings settings, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(settings, ingredient);
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new RenderSettings(overlay, false, false);
    }
}
