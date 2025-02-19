package com.knightsheraldry.items.armor;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public interface KHUnderArmorItem {
    double slashingResistance();
    double bludgeoningResistance();
    double piercingResistance();

    @NotNull Identifier getTexturePath();

    default boolean isDyeable() {
        return false;
    }

    default int getDefaultColor() {
        return 0;
    }
}