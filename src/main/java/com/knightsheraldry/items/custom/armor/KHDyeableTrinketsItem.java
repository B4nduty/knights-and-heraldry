package com.knightsheraldry.items.custom.armor;

import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;

public class KHDyeableTrinketsItem extends KHTrinketsItem implements DyeableItem {
    private final int defaultColor;
    private final boolean overlay;

    public KHDyeableTrinketsItem(Settings settings, Type type, double armor, double toughness, double hungerDrainAddition, Identifier texturePath, int defaultColor, boolean overlay) {
        super(settings, type, armor, toughness, hungerDrainAddition, texturePath);
        this.defaultColor = defaultColor;
        this.overlay = overlay;
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    public boolean getOverlay() {
        return overlay;
    }
}
