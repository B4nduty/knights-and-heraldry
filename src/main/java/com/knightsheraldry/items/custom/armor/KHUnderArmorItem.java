package com.knightsheraldry.items.custom.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class KHUnderArmorItem extends ArmorItem implements DyeableItem {

    private final ResistanceValues resistanceValues;
    private final Identifier texturePath;
    private final boolean dyeable;

    public KHUnderArmorItem(Settings settings, ArmorMaterial material, Type type,
                            ResistanceValues resistanceValues, Identifier texturePath, boolean dyeable) {
        super(material, type, settings);
        this.resistanceValues = resistanceValues;
        this.texturePath = texturePath;
        this.dyeable = dyeable;
    }

    public KHUnderArmorItem(Settings settings, ArmorMaterial material, Type type, ResistanceValues resistanceValues) {
        this(settings, material, type, resistanceValues, null, false);
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

    public boolean isDyeable() {
        return dyeable;
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 10511680;
    }

    public enum ResistanceType {
        SLASHING, BLUDGEONING, PIERCING
    }

    public record ResistanceValues(double slashing, double bludgeoning, double piercing) {}
}