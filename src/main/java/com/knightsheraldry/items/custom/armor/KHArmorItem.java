package com.knightsheraldry.items.custom.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class KHArmorItem extends ArmorItem {
    private final float slashingResistance;
    private final float bludgeoningResistance;
    private final float piercingResistance;
    private final String textureName;

    // If textureName isn't null, it will use the model set by Knights and Heraldry
    public KHArmorItem(Settings settings, ArmorMaterial material, Type type, float slashingResistance,
                       float bludgeoningResistance, float piercingResistance, String textureName) {
        super(material, type, settings);
        this.slashingResistance = slashingResistance;
        this.bludgeoningResistance = bludgeoningResistance;
        this.piercingResistance = piercingResistance;
        this.textureName = textureName;
    }

    public KHArmorItem(Settings settings, ArmorMaterial material, Type type, float slashingResistance,
                       float bludgeoningResistance, float piercingResistance) {
        super(material, type, settings);
        this.slashingResistance = slashingResistance;
        this.bludgeoningResistance = bludgeoningResistance;
        this.piercingResistance = piercingResistance;
        this.textureName = null;
    }

    public float getSlashingResistance() {return this.slashingResistance;}

    public float getBludgeoningResistance() {return this.bludgeoningResistance;}

    public float getPiercingResistance() {return this.piercingResistance;}

    public String getTextureName() {return this.textureName;}
}
