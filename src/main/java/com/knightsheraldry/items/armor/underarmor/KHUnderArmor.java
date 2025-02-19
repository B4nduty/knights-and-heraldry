package com.knightsheraldry.items.armor.underarmor;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.armor.KHUnderArmorItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class KHUnderArmor extends ArmorItem implements KHUnderArmorItem {
    double slashingResistance;
    double bludgeoningResistance;
    double piercingResistance;

    public KHUnderArmor(Settings settings, ArmorMaterial material, Type type,
                        double slashingResistance, double bludgeoningResistance, double piercingResistance) {
        super(material, type, settings);
        this.slashingResistance = slashingResistance;
        this.bludgeoningResistance = bludgeoningResistance;
        this.piercingResistance = piercingResistance;
    }

    @Override
    public double slashingResistance() {
        return slashingResistance;
    }

    @Override
    public double bludgeoningResistance() {
        return bludgeoningResistance;
    }

    @Override
    public double piercingResistance() {
        return piercingResistance;
    }

    @Override
    public @NotNull Identifier getTexturePath() {
        return new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/" + this.material.toString().toLowerCase() + ".png");
    }
}
