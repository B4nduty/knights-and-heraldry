
package com.knightsheraldry.items.custom.armor;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GambesonItem extends ArmorItem {
    public GambesonItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Slashing Resistance: +4%").formatted(Formatting.BLUE));
        tooltip.add(Text.literal("Bludgeoning Resistance: +10%").formatted(Formatting.BLUE));
    }
}