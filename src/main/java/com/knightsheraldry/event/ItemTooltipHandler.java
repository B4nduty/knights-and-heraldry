package com.knightsheraldry.event;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.armor.KHTrinketsItem;
import com.knightsheraldry.items.armor.KHUnderArmorItem;
import com.knightsheraldry.items.item.KHRangeWeapon;
import com.knightsheraldry.items.item.KHWeapon;
import com.knightsheraldry.items.item.khweapon.Lance;
import com.knightsheraldry.util.weaponutil.KHArmorUtil;
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
        if (stack.getItem() instanceof KHWeapon || stack.getItem() instanceof Lance) lines.removeIf(line -> line.contains(attackDamage));

        if (stack.getItem() instanceof KHTrinketsItem khTrinketsItem
                && khTrinketsItem.hungerDrainAddition() != 0.0d) {
            double hungerDrainAddition = khTrinketsItem.hungerDrainAddition();
            lines.add(Text.translatable("text.tooltip.knightsheraldry.hungerDrain", ((int) (hungerDrainAddition * 100))).formatted(Formatting.BLUE));
        }

        if (stack.getItem() == ModItems.HOOD || stack.getItem() == ModItems.TORN_HOOD) lines.add(Text.translatable("text.tooltip.knightsheraldry.hideNameTag").formatted(Formatting.BLUE));
        if (stack.getItem() == ModItems.CLOAK || stack.getItem() == ModItems.TORN_CLOAK) lines.add(Text.translatable("text.tooltip.knightsheraldry.freezing").formatted(Formatting.BLUE));

        if (stack.getItem() instanceof KHRangeWeapon khRangeWeapons) {
            lines.add(Text.translatable("text.tooltip.knightsheraldry.baseDamage", (int) khRangeWeapons.baseDamage()).formatted(Formatting.GREEN));
        }

        if (stack.getItem() instanceof KHUnderArmorItem khUnderArmorItem) {
            double slashingResistance = KHArmorUtil.getResistance(KHArmorUtil.ResistanceType.SLASHING, khUnderArmorItem) * 100;
            double bludgeoningResistance = KHArmorUtil.getResistance(KHArmorUtil.ResistanceType.BLUDGEONING, khUnderArmorItem) * 100;
            double piercingResistance = KHArmorUtil.getResistance(KHArmorUtil.ResistanceType.PIERCING, khUnderArmorItem) * 100;
            if (slashingResistance != 0) lines.add(Text.translatable("text.tooltip.knightsheraldry.slashingResistance", (int) slashingResistance).formatted(Formatting.BLUE));
            if (bludgeoningResistance != 0) lines.add(Text.translatable("text.tooltip.knightsheraldry.bludgeoningResistance", (int) bludgeoningResistance).formatted(Formatting.BLUE));
            if (piercingResistance != 0) lines.add(Text.translatable("text.tooltip.knightsheraldry.piercingResistance", (int) piercingResistance).formatted(Formatting.BLUE));
        }
    }
}
