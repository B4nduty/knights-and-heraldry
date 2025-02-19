package com.knightsheraldry.items.armor.underarmor;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableItem;

public class KHDyeableUnderArmor extends KHUnderArmor implements DyeableItem {
    int defaultColor;

    public KHDyeableUnderArmor(Settings settings, ArmorMaterial material, Type type,
                               double slashingResistance, double bludgeoningResistance, double piercingResistance,
                               int defaultColor) {
        super(settings, material, type, slashingResistance, bludgeoningResistance, piercingResistance);
        this.defaultColor = defaultColor;
    }

    @Override
    public boolean isDyeable() {
        return true;
    }

    @Override
    public int getDefaultColor() {
        return defaultColor;
    }
}
