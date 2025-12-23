package com.knightsheraldry.event;

import com.knightsheraldry.items.item.khammo.ClothArrow;
import com.knightsheraldry.items.item.khrangeweapon.Handgonne;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ItemTooltipHandler implements ItemTooltipCallback {
    @Override
    public void getTooltip(ItemStack itemStack, TooltipFlag tooltipFlag, List<Component> list) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        if (itemStack.getItem() instanceof ClothArrow) {
            if (itemStack.getTag() != null && itemStack.getTag().getBoolean("ignited")) {
                list.add(Component.translatable("text.tooltip.knightsheraldry.extinguish"));
            } else if (itemStack.getTag() == null || !itemStack.getTag().getBoolean("extinguished")) {
                list.add(Component.translatable("text.tooltip.knightsheraldry.ignite"));
            }
        }

        if (itemStack.getItem() instanceof Handgonne && itemStack.getTag() != null && itemStack.getTag().getBoolean("charged")) {
            list.add(Component.translatable("text.tooltip.knightsheraldry.right_click-flint_steel-fire"));
        }
    }
}