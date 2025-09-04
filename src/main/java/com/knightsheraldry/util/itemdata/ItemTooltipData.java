package com.knightsheraldry.util.itemdata;

import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemTooltipData implements TooltipData {
    private final List<ItemStack> items;

    public ItemTooltipData(List<ItemStack> items) {
        this.items = items;
    }

    public List<ItemStack> getItems() {
        return items;
    }
}