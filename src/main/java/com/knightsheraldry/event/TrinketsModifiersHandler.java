package com.knightsheraldry.event;

import banduty.stoneycore.event.custom.TrinketsModifiersEvents;
import banduty.stoneycore.items.armor.SCTrinketsItem;
import com.google.common.collect.Multimap;
import com.knightsheraldry.KnightsHeraldry;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class TrinketsModifiersHandler implements TrinketsModifiersEvents {
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(Multimap<EntityAttribute, EntityAttributeModifier> multimap, ItemStack itemStack, SlotReference slotReference, LivingEntity livingEntity, UUID uuid) {
        if (!(itemStack.getItem() instanceof SCTrinketsItem scTrinketsItem)) return multimap;
        double toughness = scTrinketsItem.toughness() + (itemStack.getOrCreateNbt().getBoolean("sc_aventail") ? 2 : 0);

        if (scTrinketsItem.armor() > 0 || toughness > 0) {
            multimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid,
                    KnightsHeraldry.MOD_ID + ":protection", scTrinketsItem.armor(), EntityAttributeModifier.Operation.ADDITION));
            multimap.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid,
                    KnightsHeraldry.MOD_ID + ":toughness", toughness, EntityAttributeModifier.Operation.ADDITION));
        }

        return multimap;
    }
}