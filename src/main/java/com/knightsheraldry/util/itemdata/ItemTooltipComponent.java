package com.knightsheraldry.util.itemdata;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemTooltipComponent implements TooltipComponent {
    private final List<ItemStack> items;

    public ItemTooltipComponent(List<ItemStack> items) {
        this.items = items;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    @Override
    public int getHeight() {
        return 20; // Height for item rendering
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return items.size() * 18 + 2; // Width based on number of items
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
        for (int i = 0; i < items.size(); i++) {
            ItemStack stack = items.get(i);
            int xPos = x + i * 18;
            context.drawItem(stack, xPos, y);
            context.drawItemInSlot(textRenderer, stack, xPos, y);
        }
    }
}