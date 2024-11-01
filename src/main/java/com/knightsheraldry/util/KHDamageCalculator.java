package com.knightsheraldry.util;

import com.knightsheraldry.items.custom.armor.KHUnderArmorItem;
import com.knightsheraldry.util.itemdata.KHTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class KHDamageCalculator {
    public static float getKHDamage(LivingEntity livingEntity, float initialDamage, DamageType damageType) {
        if (!(livingEntity instanceof PlayerEntity player)) {
            return initialDamage;
        }

        for (ItemStack armorStack : player.getArmorItems()) {
            if (armorStack.getItem() instanceof KHUnderArmorItem khArmorItem) {
                float resistance = (float) getResistance(khArmorItem, damageType);
                initialDamage *= Math.max(1 - resistance, 0);
            }
        }
        return initialDamage;
    }

    protected static double getResistance(KHUnderArmorItem armorItem, DamageType damageType) {
        return switch (damageType) {
            case SLASHING -> armorItem.getSlashingResistance();
            case PIERCING -> armorItem.getPiercingResistance();
            case BLUDGEONING -> armorItem.getBludgeoningResistance();
        } * 100;
    }

    public static void applyDamage(LivingEntity target, PlayerEntity playerEntity, ItemStack stack, float damage) {
        float enchantmentBonusDamage = EnchantmentHelper.getAttackDamage(stack, target.getGroup());
        damage += enchantmentBonusDamage;
        if (stack.isIn(KHTags.Weapon.KH_WEAPONS_IGNORES_ARMOR) && target.getHealth() - (damage - 1) > 0) {
            target.setHealth(target.getHealth() - (damage - 1));
        } else {
            target.damage(playerEntity.getWorld().getDamageSources().playerAttack(playerEntity), damage - 1);
        }
    }

    public enum DamageType {
        SLASHING("slashing", 4),
        PIERCING("piercing", 9),
        BLUDGEONING("bludgeoning", 14);

        private final String name;
        private final int index;

        DamageType(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }
}