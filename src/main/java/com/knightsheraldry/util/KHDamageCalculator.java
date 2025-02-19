package com.knightsheraldry.util;

import com.knightsheraldry.items.armor.KHUnderArmorItem;
import com.knightsheraldry.util.itemdata.KHTags;
import com.knightsheraldry.util.weaponutil.KHArmorUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class KHDamageCalculator {
    public static float getKHDamage(LivingEntity livingEntity, float initialDamage, DamageType damageType) {
        for (ItemStack armorStack : livingEntity.getArmorItems()) {
            if (armorStack.getItem() instanceof KHUnderArmorItem khUnderArmorItem) {
                float resistance = (float) getResistance(khUnderArmorItem, damageType);
                initialDamage *= Math.max(1 - resistance, 0);
            }
        }
        return initialDamage;
    }

    protected static double getResistance(KHUnderArmorItem khUnderArmorItem, DamageType damageType) {
        return switch (damageType) {
            case SLASHING -> KHArmorUtil.getResistance(KHArmorUtil.ResistanceType.SLASHING, khUnderArmorItem);
            case PIERCING -> KHArmorUtil.getResistance(KHArmorUtil.ResistanceType.PIERCING, khUnderArmorItem);
            case BLUDGEONING -> KHArmorUtil.getResistance(KHArmorUtil.ResistanceType.BLUDGEONING, khUnderArmorItem);
        } * 100;
    }

    public static void applyDamage(LivingEntity target, PlayerEntity playerEntity, ItemStack stack, float damage) {
        float enchantmentBonusDamage = EnchantmentHelper.getAttackDamage(stack, target.getGroup());
        damage += enchantmentBonusDamage;
        if (stack.isIn(KHTags.WEAPONS_IGNORES_ARMOR.getTag()) && target.getHealth() - (damage - 1) > 0) {
            target.setHealth(target.getHealth() - (damage - 1));
        } else {
            target.damage(playerEntity.getWorld().getDamageSources().playerAttack(playerEntity), damage - 1);
        }
    }

    public enum DamageType {
        SLASHING("slashing", 0),
        PIERCING("piercing", 1),
        BLUDGEONING("bludgeoning", 2);

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