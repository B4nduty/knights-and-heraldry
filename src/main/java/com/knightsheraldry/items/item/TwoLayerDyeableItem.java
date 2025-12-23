package com.knightsheraldry.items.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TwoLayerDyeableItem extends Item {

    public TwoLayerDyeableItem(Item.Properties properties) {
        super(properties);
    }

    public int getColor1(ItemStack stack) {
        String itemPath = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();
        if (stack.getTag() != null && stack.getTag().contains(itemPath)) {
            return stack.getTag().getCompound(itemPath).getInt("color1");
        }
        return 0xFFFFFF;
    }

    public int getColor2(ItemStack stack) {
        String itemPath = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();
        if (stack.getTag() != null && stack.getTag().contains(itemPath)) {
            return stack.getTag().getCompound(itemPath).getInt("color2");
        }
        return 0xFFFFFF;
    }

    public void setColor1(ItemStack stack, int color) {
        stack.getOrCreateTag().putInt("color1", color);
    }

    // Apply the second dye
    public void setColor2(ItemStack stack, int color) {
        stack.getOrCreateTag().putInt("color2", color);
    }
}
