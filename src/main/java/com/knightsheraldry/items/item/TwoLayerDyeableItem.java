package com.knightsheraldry.items.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

public class TwoLayerDyeableItem extends Item {

    public TwoLayerDyeableItem(Settings settings) {
        super(settings);
    }

    public int getColor1(ItemStack stack) {
        String itemPath = Registries.ITEM.getId(stack.getItem()).getPath();
        if (stack.getNbt() != null && stack.getNbt().contains(itemPath)) {
            return stack.getNbt().getCompound(itemPath).getInt("color1");
        }
        return 0xFFFFFF;
    }

    public int getColor2(ItemStack stack) {
        String itemPath = Registries.ITEM.getId(stack.getItem()).getPath();
        if (stack.getNbt() != null && stack.getNbt().contains(itemPath)) {
            return stack.getNbt().getCompound(itemPath).getInt("color2");
        }
        return 0xFFFFFF;
    }

    public void setColor1(ItemStack stack, int color) {
        stack.getOrCreateNbt().putInt("color1", color);
    }

    // Apply the second dye
    public void setColor2(ItemStack stack, int color) {
        stack.getOrCreateNbt().putInt("color2", color);
    }
}
