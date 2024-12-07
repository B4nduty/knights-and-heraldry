package com.knightsheraldry.event;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.armor.KHUnderArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.items.custom.item.KHRangeWeapons;
import com.knightsheraldry.util.itemdata.KHTags;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ItemTooltipHandler implements ItemTooltipCallback {
    @Override
    public void getTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
        Text attackDamage = Text.translatable("attribute.name.generic.attack_damage");
        if (stack.isIn(KHTags.WEAPONS.getTag())) lines.removeIf(line -> line.contains(attackDamage));

        if (stack.getItem() instanceof KHTrinketsItem khTrinketsItem
                && khTrinketsItem.getHungerDrainAddition() != 0.0d) {
            double hungerDrainAddition = khTrinketsItem.getHungerDrainAddition();
            lines.add(Text.translatable("text.tooltip.knightsheraldry.hungerDrain", ((int) (hungerDrainAddition * 100))).formatted(Formatting.BLUE));
        }

        if (stack.getItem() == ModItems.HOOD || stack.getItem() == ModItems.TORN_HOOD) lines.add(Text.translatable("text.tooltip.knightsheraldry.hideNameTag").formatted(Formatting.BLUE));
        if (stack.getItem() == ModItems.CLOAK || stack.getItem() == ModItems.TORN_CLOAK) lines.add(Text.translatable("text.tooltip.knightsheraldry.freezing").formatted(Formatting.BLUE));

        if (stack.getItem() instanceof KHRangeWeapons khRangeWeapons) {
            lines.add(Text.translatable("text.tooltip.knightsheraldry.baseDamage", (int) khRangeWeapons.config.damageSettings().damage()).formatted(Formatting.GREEN));
            lines.add(Text.translatable("text.tooltip.knightsheraldry.blockRange", (int) khRangeWeapons.config.damageSettings().blockRange()).formatted(Formatting.GREEN));
        }

        if (stack.getItem() instanceof KHUnderArmorItem khArmorItem) {
            double slashingResistance = khArmorItem.getResistance(KHUnderArmorItem.ResistanceType.SLASHING) * 100;
            double bludgeoningResistance = khArmorItem.getResistance(KHUnderArmorItem.ResistanceType.BLUDGEONING) * 100;
            double piercingResistance = khArmorItem.getResistance(KHUnderArmorItem.ResistanceType.PIERCING) * 100;
            if (slashingResistance != 0) lines.add(Text.translatable("text.tooltip.knightsheraldry.slashingResistance", (int) slashingResistance).formatted(Formatting.BLUE));
            if (bludgeoningResistance != 0) lines.add(Text.translatable("text.tooltip.knightsheraldry.bludgeoningResistance", (int) bludgeoningResistance).formatted(Formatting.BLUE));
            if (piercingResistance != 0) lines.add(Text.translatable("text.tooltip.knightsheraldry.piercingResistance", (int) piercingResistance).formatted(Formatting.BLUE));
        }
    }
}
