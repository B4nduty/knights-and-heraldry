package com.knightsheraldry.util.itemdata;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record ItemTooltipComponent(List<ItemStack> items) implements ClientTooltipComponent {

    @Override
    public int getHeight() {
        return 20;
    }

    @Override
    public int getWidth(Font font) {
        return items.size() * 18 + 2;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        for (int i = 0; i < items.size(); i++) {
            ItemStack stack = items.get(i);
            int xPos = x + i * 18;
            guiGraphics.renderItem(stack, xPos, y);
            guiGraphics.renderItemDecorations(font, stack, xPos, y);
        }
    }
}