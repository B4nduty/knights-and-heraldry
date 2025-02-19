package com.knightsheraldry.util.weaponutil;

import com.knightsheraldry.items.armor.KHUnderArmorItem;

public class KHArmorUtil {
    public static double getResistance(ResistanceType type, KHUnderArmorItem khUnderArmorItem) {
        return switch (type) {
            case SLASHING -> khUnderArmorItem.slashingResistance();
            case BLUDGEONING -> khUnderArmorItem.bludgeoningResistance();
            case PIERCING -> khUnderArmorItem.piercingResistance();
        };
    }

    public enum ResistanceType {
        SLASHING, BLUDGEONING, PIERCING
    }
}
