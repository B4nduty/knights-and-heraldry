package com.knightsheraldry.items.armor.trinkets;

import net.minecraft.item.DyeableItem;

public class KHDyeableLeggingsTrinkets extends KHLeggingsTrinkets implements DyeableItem {
    boolean overlay;
    int defaultColor;

    public KHDyeableLeggingsTrinkets(Settings settings, double armor, double toughness, double hungerDrainAddition,
                                     boolean overlay, int defaultColor) {
        super(settings, armor, toughness, hungerDrainAddition);
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    public boolean isDyeable() {
        return true;
    }

    public boolean isDyeableWithOverlay() {
        return overlay;
    }

    public int getDefaultColor() {
        return defaultColor;
    }
}
