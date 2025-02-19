package com.knightsheraldry.mixin;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.armor.KHTrinketsItem;
import com.knightsheraldry.items.armor.KHUnderArmorItem;
import com.knightsheraldry.util.itemdata.KHTags;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Mixin(TrinketItem.class)
public class TrinketMixin implements Trinket {
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = this.getTrinketsModifiers(stack, slot);

        if (!(stack.getItem() instanceof KHTrinketsItem khTrinketsItem)) return modifiers;
        double toughness = khTrinketsItem.toughness() + (stack.getOrCreateNbt().getBoolean("kh_aventail") ? 2 : 0);

        if (khTrinketsItem.armor() > 0 || toughness > 0) {
            modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid,
                    KnightsHeraldry.MOD_ID + ":protection", khTrinketsItem.armor(), EntityAttributeModifier.Operation.ADDITION));
            modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid,
                    KnightsHeraldry.MOD_ID + ":toughness", toughness, EntityAttributeModifier.Operation.ADDITION));
        }
        return modifiers;
    }

    @Unique
    Multimap<EntityAttribute, EntityAttributeModifier> getTrinketsModifiers(ItemStack stack, SlotReference slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> map = Multimaps.newMultimap(Maps.newLinkedHashMap(), ArrayList::new);
        if (stack.hasNbt() && stack.getNbt().contains("TrinketAttributeModifiers", 9)) {
            NbtList list = stack.getNbt().getList("TrinketAttributeModifiers", 10);

            for(int i = 0; i < list.size(); ++i) {
                NbtCompound tag = list.getCompound(i);
                if (tag.contains("Slot", 8)) {
                    String var10000 = tag.getString("Slot");
                    String var10001 = slot.inventory().getSlotType().getGroup();
                    if (!var10000.equals(var10001 + "/" + slot.inventory().getSlotType().getName())) {
                        continue;
                    }
                }

                Optional<EntityAttribute> optional = Registries.ATTRIBUTE.getOrEmpty(Identifier.tryParse(tag.getString("AttributeName")));
                if (optional.isPresent()) {
                    EntityAttributeModifier entityAttributeModifier = EntityAttributeModifier.fromNbt(tag);
                    if (entityAttributeModifier != null && entityAttributeModifier.getId().getLeastSignificantBits() != 0L && entityAttributeModifier.getId().getMostSignificantBits() != 0L) {
                        map.put((EntityAttribute)optional.get(), entityAttributeModifier);
                    }
                }
            }
        }

        return map;
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (!(stack.getItem() instanceof KHTrinketsItem)) return true;

        return Arrays.stream(EquipmentSlot.values())
                .filter(this::isArmorSlot)
                .allMatch(slotType -> stack.isIn(KHTags.ALWAYS_WEARABLE.getTag()) ||
                        entity.getEquippedStack(slotType).getItem() instanceof KHUnderArmorItem);
    }

    @Unique
    private boolean isArmorSlot(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD, CHEST, LEGS, FEET -> true;
            default -> false;
        };
    }
}
