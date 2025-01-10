package com.knightsheraldry.util;

import com.knightsheraldry.items.custom.armor.KHDyeableTrinketsItem;
import com.knightsheraldry.items.custom.armor.KHDyeableUnderArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class DyeUtil {
    public static float[] getDyeColor(ItemStack stack) {
        int color = getColor(stack);
        return new float[]{(color >> 16 & 255) / 255.0F, (color >> 8 & 255) / 255.0F, (color & 255) / 255.0F};
    }

    public static int getColor(ItemStack stack) {
        if (stack.getItem() instanceof KHDyeableTrinketsItem khDyeableTrinketsItem) {
            NbtCompound nbtCompound = stack.getSubNbt("display");
            return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : khDyeableTrinketsItem.getDefaultColor();
        }
        if (stack.getItem() instanceof KHDyeableTrinketsItem || stack.getItem() instanceof KHDyeableUnderArmorItem) {
            NbtCompound nbtCompound = stack.getSubNbt("display");
            return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 10511680;
        }
        return 0xFFFFFF;
    }
}
