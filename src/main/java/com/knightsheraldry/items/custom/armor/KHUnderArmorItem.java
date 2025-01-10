package com.knightsheraldry.items.custom.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.util.Identifier;

public class KHUnderArmorItem extends ArmorItem {
    private final ResistanceValues resistanceValues;
    private final Identifier texturePath;

    public KHUnderArmorItem(Settings settings, ArmorMaterial material, Type type,
                            double slashing, double bludgeoning, double piercing, Identifier texturePath) {
        super(material, type, settings);
        this.resistanceValues = new ResistanceValues(slashing, bludgeoning, piercing);
        this.texturePath = texturePath;
    }

    public double getResistance(ResistanceType type) {
        return switch (type) {
            case SLASHING -> resistanceValues.slashing();
            case BLUDGEONING -> resistanceValues.bludgeoning();
            case PIERCING -> resistanceValues.piercing();
        };
    }

    public Identifier getPath() {
        return texturePath;
    }

    public enum ResistanceType {
        SLASHING, BLUDGEONING, PIERCING
    }

    public record ResistanceValues(double slashing, double bludgeoning, double piercing) {}
}