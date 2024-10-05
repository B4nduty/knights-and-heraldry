package com.knightsheraldry.util;

import com.knightsheraldry.items.custom.armor.KHArmorItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class KHDamageCalculator {
    public float getKHDamage(LivingEntity livingEntity, float initialDamage, DamageType damageType) {
        if (!(livingEntity instanceof PlayerEntity player)) {
            return initialDamage;
        }

        for (ItemStack armorStack : player.getArmorItems()) {
            if (armorStack.getItem() instanceof KHArmorItem khArmorItem) {
                float resistance = (float) getResistance(khArmorItem, damageType);
                initialDamage *= Math.max(1 - resistance, 0);
            }
        }
        return initialDamage;
    }

    private double getResistance(KHArmorItem armorItem, DamageType damageType) {
        return switch (damageType) {
            case SLASHING -> armorItem.getSlashingResistance();
            case PIERCING -> armorItem.getPiercingResistance();
            case BLUDGEONING -> armorItem.getBludgeoningResistance();
        } * 100;
    }

    public enum DamageType {
        SLASHING("slashing"),
        PIERCING("piercing"),
        BLUDGEONING("bludgeoning");

        private final String name;

        DamageType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}