package com.knightsheraldry.util.itemdata;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record ItemTooltipData(List<ItemStack> items) implements TooltipComponent {
}