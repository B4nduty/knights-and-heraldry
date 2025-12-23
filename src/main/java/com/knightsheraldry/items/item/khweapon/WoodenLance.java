package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.util.SCDamageCalculator;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WoodenLance extends Lance implements DyeableLeatherItem {
    public WoodenLance(float attackSpeed, Item.Properties properties, SCDamageCalculator.DamageType onlyDamageType) {
        super(attackSpeed, properties, onlyDamageType);
    }

    @Override
    public int getColor(ItemStack stack) {
        CompoundTag tagElement = stack.getTagElement(TAG_DISPLAY);
        if (tagElement != null && tagElement.contains(TAG_COLOR, Tag.TAG_ANY_NUMERIC)) {
            return tagElement.getInt(TAG_COLOR);
        }
        return DEFAULT_LEATHER_COLOR;
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