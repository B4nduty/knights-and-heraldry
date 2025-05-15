package com.knightsheraldry.event;

import banduty.stoneycore.StoneyCore;
import io.wispforest.accessories.api.attributes.AccessoryAttributeBuilder;
import io.wispforest.accessories.api.events.AdjustAttributeModifierCallback;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class AdjustAttributeModifierEvent implements AdjustAttributeModifierCallback {
    @Override
    public void adjustAttributes(ItemStack stack, SlotReference reference, AccessoryAttributeBuilder builder) {
        if (!(stack.getNbt() != null && stack.getNbt().getBoolean("kh_aventail"))) return;
        updatePlayerAttributes(reference, builder);
    }

    private static void handleAttribute(SlotReference reference, EntityAttribute attribute, String name, double value, AccessoryAttributeBuilder builder) {
        EntityAttributeInstance instance = reference.entity().getAttributeInstance(attribute);
        if (instance == null) return;

        builder.addStackable(attribute, new Identifier(StoneyCore.MOD_ID, name), value, EntityAttributeModifier.Operation.ADDITION);
    }

    private static void updatePlayerAttributes(SlotReference reference, AccessoryAttributeBuilder builder) {
        handleAttribute(reference, EntityAttributes.GENERIC_ARMOR_TOUGHNESS, "armor_toughness", 2, builder);
    }
}