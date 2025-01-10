package com.knightsheraldry.items.custom.armor;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;

public class KHDyeableUnderArmorItem extends KHUnderArmorItem implements DyeableItem {
    public KHDyeableUnderArmorItem(Settings settings, ArmorMaterial material, Type type, double slashing, double bludgeoning, double piercing, Identifier texturePath) {
        super(settings, material, type, slashing, bludgeoning, piercing, texturePath);
    }
}
