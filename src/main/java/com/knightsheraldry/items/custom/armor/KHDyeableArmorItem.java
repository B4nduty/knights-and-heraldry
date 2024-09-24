package com.knightsheraldry.items.custom.armor;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

// Don't use it, not implemented yet, it exists for tests
public class KHDyeableArmorItem extends KHArmorItem implements DyeableItem {
    public KHDyeableArmorItem(Settings settings, ArmorMaterial material, Type type, float slashingResistance, float bludgeoningResistance, float piercingResistance, String textureName) {
        super(settings, material, type, slashingResistance, bludgeoningResistance, piercingResistance, textureName);
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 10511680;
    }
}
