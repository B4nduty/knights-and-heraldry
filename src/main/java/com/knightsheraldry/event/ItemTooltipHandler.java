package com.knightsheraldry.event;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.armor.KHArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.util.ModTags;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ItemTooltipHandler implements ItemTooltipCallback {
    @Override
    public void getTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
        if (stack.isIn(ModTags.Items.KH_WEAPONS)) lines.removeIf(line -> line.getString().contains("Attack Damage"));

        if (stack.getItem() instanceof KHTrinketsItem khTrinketsItem
                && khTrinketsItem.getHungerDrainAddition() != 0.0d) {
            double hungerDrainAddition = khTrinketsItem.getHungerDrainAddition();
            lines.add(Text.translatable("text.tooltip.knightsheraldry.hungerDrain", ((int) (hungerDrainAddition * 100))).formatted(Formatting.BLUE));
        }

        if (stack.getItem() == ModItems.HOOD || stack.getItem() == ModItems.TORN_HOOD) lines.add(Text.translatable("text.tooltip.knightsheraldry.hideNameTag").formatted(Formatting.BLUE));
        if (stack.getItem() == ModItems.CLOAK || stack.getItem() == ModItems.TORN_CLOAK) lines.add(Text.translatable("text.tooltip.knightsheraldry.freezing").formatted(Formatting.BLUE));

        if (stack.getItem() instanceof KHArmorItem khArmorItem) {
            double slashingResistance = khArmorItem.getSlashingResistance();
            double bludgeoningResistance = khArmorItem.getBludgeoningResistance();
            double piercingResistance = khArmorItem.getPiercingResistance();
            if (slashingResistance != 0) lines.add(Text.translatable("text.tooltip.knightsheraldry.slashingResistance", slashingResistance).formatted(Formatting.BLUE));
            if (bludgeoningResistance != 0) lines.add(Text.translatable("text.tooltip.knightsheraldry.bludgeoningResistance", bludgeoningResistance).formatted(Formatting.BLUE));
            if (piercingResistance != 0) lines.add(Text.translatable("text.tooltip.knightsheraldry.piercingResistance", piercingResistance).formatted(Formatting.BLUE));
        }
    }
}
