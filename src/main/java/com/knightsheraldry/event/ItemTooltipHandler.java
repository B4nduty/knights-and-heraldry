package com.knightsheraldry.event;

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
            lines.add(Text.literal("+" + ((int) (hungerDrainAddition * 100)) + "% Hunger Drain").formatted(Formatting.BLUE));
        }
        if (stack.getItem() instanceof KHArmorItem khArmorItem) {
            double slashingResistance = khArmorItem.getSlashingResistance();
            double bludgeoningResistance = khArmorItem.getBludgeoningResistance();
            double piercingResistance = khArmorItem.getPiercingResistance();
            if (slashingResistance != 0) lines.add(Text.literal("+" + slashingResistance + "% Slashing Resistance").formatted(Formatting.BLUE));
            if (bludgeoningResistance != 0) lines.add(Text.literal("+" + bludgeoningResistance + "% Bludgeoning Resistance").formatted(Formatting.BLUE));
            if (piercingResistance != 0) lines.add(Text.literal("+" + piercingResistance + "% Piercing Resistance").formatted(Formatting.BLUE));
        }

    }
}
