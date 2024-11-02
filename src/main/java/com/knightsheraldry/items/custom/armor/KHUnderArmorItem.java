package com.knightsheraldry.items.custom.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class KHUnderArmorItem extends ArmorItem implements DyeableItem {
    private final double slashingResistance;
    private final double bludgeoningResistance;
    private final double piercingResistance;
    private final Identifier texturePath;
    private final boolean dyeable;

    // If texturePath is null, it won't use the model set by Knights and Heraldry
    public KHUnderArmorItem(Settings settings, ArmorMaterial material, Type type, double slashingResistance,
                            double bludgeoningResistance, double piercingResistance, Identifier texturePath, boolean dyeable) {
        super(material, type, settings);
        this.slashingResistance = slashingResistance;
        this.bludgeoningResistance = bludgeoningResistance;
        this.piercingResistance = piercingResistance;
        this.texturePath = texturePath;
        this.dyeable = dyeable;
    }

    public KHUnderArmorItem(Settings settings, ArmorMaterial material, Type type, double slashingResistance,
                            double bludgeoningResistance, double piercingResistance) {
        super(material, type, settings);
        this.slashingResistance = slashingResistance;
        this.bludgeoningResistance = bludgeoningResistance;
        this.piercingResistance = piercingResistance;
        this.texturePath = null;
        this.dyeable = false;
    }

    public final double getSlashingResistance() {return this.slashingResistance;}

    public final double getBludgeoningResistance() {return this.bludgeoningResistance;}

    public final double getPiercingResistance() {return this.piercingResistance;}

    public final Identifier getPath() {return this.texturePath;}

    public final boolean isDyeable() {return this.dyeable;}

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 10511680;
    }
}
