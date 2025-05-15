package com.knightsheraldry.items.armor.accessory;

import net.minecraft.item.DyeableItem;

public class KHDyeableLeggingsAccessory extends KHLeggingsAccessory implements DyeableItem {
    boolean overlay;
    int defaultColor;

    public KHDyeableLeggingsAccessory(Settings settings, boolean overlay, int defaultColor) {
        super(settings);
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    public boolean hasOverlay() {
        return overlay;
    }
}
