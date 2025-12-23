package com.knightsheraldry.items.armor.accessory;

import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHDyeableLeggingsAccessory extends KHLeggingsAccessory implements DyeableLeatherItem {
    boolean overlay;
    int defaultColor;

    public KHDyeableLeggingsAccessory(Item.Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties, ingredient);
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new RenderSettings(overlay, false, false);
    }
}
