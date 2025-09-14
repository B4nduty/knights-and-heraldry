package com.knightsheraldry.util.itemdata;

import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;

import java.util.List;

public record ItemTooltipData(List<ItemStack> items) implements TooltipData {
}