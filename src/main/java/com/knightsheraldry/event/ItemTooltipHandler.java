package com.knightsheraldry.event;

import com.knightsheraldry.items.item.khammo.ClothArrow;
import com.knightsheraldry.items.item.khrangeweapon.Handgonne;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class ItemTooltipHandler implements ItemTooltipCallback {
    @Override
    public void getTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (stack.getItem() instanceof ClothArrow) {
            if (stack.getNbt() != null && stack.getNbt().getBoolean("ignited")) {
                lines.add(Text.translatable("text.tooltip.knightsheraldry.extinguish"));
            } else if (stack.getNbt() == null || !stack.getNbt().getBoolean("extinguished"))
                lines.add(Text.translatable("text.tooltip.knightsheraldry.ignite"));
        }

        if (stack.getItem() instanceof Handgonne && stack.getNbt() != null && stack.getNbt().getBoolean("sc_charged"))
                lines.add(Text.translatable("text.tooltip.knightsheraldry.right_click-flint_steel-fire"));
    }
}
