package com.knightsheraldry.items.armor.trinkets;

import net.minecraft.item.DyeableItem;

public class KHDyeableChestplateTrinkets extends KHChestplateTrinkets implements DyeableItem {
    boolean overlay;
    int defaultColor;

    public KHDyeableChestplateTrinkets(Settings settings, double armor, double toughness, double hungerDrainAddition,
                                       boolean overlay, int defaultColor) {
        super(settings, armor, toughness, hungerDrainAddition);
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    public boolean hasOverlay() {
        return overlay;
    }
}
