package com.knightsheraldry.util;

import com.knightsheraldry.items.armor.KHTrinketsItem;
import com.knightsheraldry.items.armor.KHUnderArmorItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class DyeUtil {
    public static float[] getDyeColor(ItemStack stack) {
        int color = getColor(stack);
        return new float[]{(color >> 16 & 255) / 255.0F, (color >> 8 & 255) / 255.0F, (color & 255) / 255.0F};
    }

    public static int getColor(ItemStack stack) {
        if (stack.getItem() instanceof KHTrinketsItem khTrinketsItem && stack.getItem() instanceof DyeableItem) {
            NbtCompound nbtCompound = stack.getSubNbt("display");
            return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : khTrinketsItem.getDefaultColor();
        }
        if (stack.getItem() instanceof KHUnderArmorItem khUnderArmorItem && khUnderArmorItem.isDyeable()) {
            NbtCompound nbtCompound = stack.getSubNbt("display");
            return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : khUnderArmorItem.getDefaultColor();
        }
        return 0xFFFFFF;
    }
}
