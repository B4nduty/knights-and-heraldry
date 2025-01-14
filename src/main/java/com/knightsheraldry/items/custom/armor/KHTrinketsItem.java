
package com.knightsheraldry.items.custom.armor;

import com.google.common.collect.Multimap;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.itemdata.KHTags;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.UUID;

public class KHTrinketsItem extends TrinketItem {
    public final TrinketAttributes attributes;
    public final Type type;

    public KHTrinketsItem(Settings settings, Type type, double armor, double toughness, double hungerDrainAddition, Identifier texturePath) {
        super(settings);
        this.type = type;
        this.attributes = new TrinketAttributes(armor, toughness, hungerDrainAddition, texturePath);
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return Arrays.stream(EquipmentSlot.values())
                .filter(this::isArmorSlot)
                .allMatch(slotType -> stack.isIn(KHTags.ALWAYS_WEARABLE.getTag()) ||
                        entity.getEquippedStack(slotType).getItem() instanceof KHUnderArmorItem);
    }

    private boolean isArmorSlot(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD, CHEST, LEGS, FEET -> true;
            default -> false;
        };
    }

    public double getHungerDrainAddition() {
        return this.attributes.hungerDrainAddition();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        double toughness = this.attributes.toughness() + (stack.getOrCreateNbt().getBoolean("aventail") ? 2 : 0);

        if (attributes.armor() > 0 || toughness > 0) {
            modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid,
                    KnightsHeraldry.MOD_ID + ":protection", attributes.armor(), EntityAttributeModifier.Operation.ADDITION));
            modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid,
                    KnightsHeraldry.MOD_ID + ":toughness", toughness, EntityAttributeModifier.Operation.ADDITION));
        }
        return modifiers;
    }

    @Override
    public Text getName(ItemStack stack) {
        StringBuilder translationKey = new StringBuilder(stack.getTranslationKey());
        if (stack.getOrCreateNbt().getBoolean("aventail")) translationKey.append("_aventail");
        if (stack.getOrCreateNbt().getBoolean("rimmed")) translationKey.append("_rimmed");
        if (stack.getOrCreateNbt().getBoolean("besagews")) translationKey.append("_besagews");
        return Text.translatable(translationKey.toString());
    }

    public Identifier getPath() {
        return attributes.texturePath();
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);
        if (player.currentScreenHandler instanceof CraftingScreenHandler craftingInventory) {
            applyCraftingModifiers(stack, craftingInventory.getCraftingSlotCount(), craftingInventory::getSlot);
        } else if (player.currentScreenHandler instanceof PlayerScreenHandler playerInventory) {
            applyCraftingModifiers(stack, 4, playerInventory::getSlot);
        }
    }

    private void applyCraftingModifiers(ItemStack stack, int slotCount, java.util.function.IntFunction<Slot> slotSupplier) {
        for (int i = 0; i < slotCount; i++) {
            ItemStack ingredient = slotSupplier.apply(i).getStack();
            if (ingredient.getItem() == ModItems.AVENTAIL) stack.getOrCreateNbt().putBoolean("aventail", true);
            if (ingredient.getItem() == ModItems.RIM_GUARDS) stack.getOrCreateNbt().putBoolean("rimmed", true);
            if (ingredient.getItem() == ModItems.BESAGEWS) stack.getOrCreateNbt().putBoolean("besagews", true);
        }
    }

    public enum Type {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS, CLOAK
    }

    public record TrinketAttributes(double armor, double toughness, double hungerDrainAddition, Identifier texturePath) { }
}
