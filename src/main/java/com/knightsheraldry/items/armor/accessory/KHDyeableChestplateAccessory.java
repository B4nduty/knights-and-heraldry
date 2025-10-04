package com.knightsheraldry.items.armor.accessory;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;

public class KHDyeableChestplateAccessory extends KHChestplateAccessory implements DyeableItem {
    boolean overlay;
    int defaultColor;

    public KHDyeableChestplateAccessory(Settings settings, Ingredient ingredient) {
        super(settings, ingredient);
        this.overlay = false;
        this.defaultColor = 0xffffff;
    }

    public KHDyeableChestplateAccessory(Settings settings, int defaultColor, Ingredient ingredient) {
        super(settings, ingredient);
        this.overlay = false;
        this.defaultColor = defaultColor;
    }

    public KHDyeableChestplateAccessory(Settings settings, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(settings, ingredient);
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new RenderSettings(overlay, false, false);
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : defaultColor;
    }
}
