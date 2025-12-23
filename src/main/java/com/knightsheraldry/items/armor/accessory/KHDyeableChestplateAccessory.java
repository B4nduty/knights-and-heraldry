package com.knightsheraldry.items.armor.accessory;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHDyeableChestplateAccessory extends KHChestplateAccessory implements DyeableLeatherItem {
    boolean overlay;
    int defaultColor;

    public KHDyeableChestplateAccessory(Item.Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
        this.overlay = false;
        this.defaultColor = 0xffffff;
    }

    public KHDyeableChestplateAccessory(Item.Properties properties, int defaultColor, Ingredient ingredient) {
        super(properties, ingredient);
        this.overlay = false;
        this.defaultColor = defaultColor;
    }

    public KHDyeableChestplateAccessory(Item.Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties, ingredient);
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    @Override
    public RenderSettings getRenderSettings(ItemStack stack) {
        return new RenderSettings(overlay, false, false);
    }

    @Override
    public int getColor(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTagElement("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : defaultColor;
    }
}
