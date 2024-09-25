package com.knightsheraldry.items.custom.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class KHArmorItem extends ArmorItem implements DyeableItem {
    private final float slashingResistance;
    private final float bludgeoningResistance;
    private final float piercingResistance;
    private final Identifier texturePath;
    private final boolean dyeable;

    // If textureName isn't null, it will use the model set by Knights and Heraldry
    public KHArmorItem(Settings settings, ArmorMaterial material, Type type, float slashingResistance,
                       float bludgeoningResistance, float piercingResistance, Identifier texturePath, boolean dyeable) {
        super(material, type, settings);
        this.slashingResistance = slashingResistance;
        this.bludgeoningResistance = bludgeoningResistance;
        this.piercingResistance = piercingResistance;
        this.texturePath = texturePath;
        this.dyeable = dyeable;
    }

    public KHArmorItem(Settings settings, ArmorMaterial material, Type type, float slashingResistance,
                       float bludgeoningResistance, float piercingResistance) {
        super(material, type, settings);
        this.slashingResistance = slashingResistance;
        this.bludgeoningResistance = bludgeoningResistance;
        this.piercingResistance = piercingResistance;
        this.texturePath = null;
        this.dyeable = false;
    }

    public float getSlashingResistance() {return this.slashingResistance;}

    public float getBludgeoningResistance() {return this.bludgeoningResistance;}

    public float getPiercingResistance() {return this.piercingResistance;}

    public Identifier getPath() {return this.texturePath;}

    public boolean isDyeable() {return this.dyeable;}

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 10511680;
    }
}
