package com.knightsheraldry.items.custom.item;

import net.minecraft.item.*;
import net.minecraft.nbt.*;

public class WoodenLance extends Lance implements DyeableItem {
    public WoodenLance(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE)) {
            return nbtCompound.getInt(COLOR_KEY);
        }
        return DEFAULT_COLOR;
    }

    @Override
    public double getRange() {
        return 4.0d;
    }

    @Override
    public float getLanceDamage() {
        return 0.1f;
    }
}