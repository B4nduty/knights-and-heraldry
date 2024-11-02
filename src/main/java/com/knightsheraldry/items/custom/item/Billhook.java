package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.itemdata.KHTags;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Billhook extends KHWeapons {
    public Billhook(float attackSpeed, Settings settings, KHDamageCalculator.DamageType onlyDamageType) {
        super(attackSpeed, settings, onlyDamageType);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 4.0F, 6.0F, 4.0F, 2.0F, //Slashing
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                4.0d, //1st Distance
                4.8d, //2nd Distance
                5.6d, //3rd Distance
                6.4d, //4th Distance
                7.0d  //5th Distance
        };
    }

    @Override
    public int getAnimation() {
        return 2;
    }

    @Override
    public int[] getPiercingAnimation() {
        return new int[] {
                2
        };
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);

        if (attacker instanceof PlayerEntity playerEntity) {
            Box detectionBox = new Box(playerEntity.getBlockPos()).expand(getMaxDistance());
            Vec3d playerPos = playerEntity.getPos();
            playerEntity.getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                            entity != playerEntity && entity == target && playerEntity.getBlockPos().isWithinDistance(entity.getBlockPos(), getMaxDistance() + 1))
                    .forEach(entity -> {
                        boolean critical = false;
                        double distance = playerPos.distanceTo(target.getPos());
                        KHDamageCalculator.DamageType damageType = calculateDamageType(stack, ((PlayerAttackProperties) playerEntity).getComboCount());
                        float damage = KHDamageCalculator.getKHDamage(playerEntity, calculateDamage(distance,
                                damageType.getIndex() - 4, damageType.getIndex()), damageType);
                        float maxDamage = getMaxValueDamage(stack, ((PlayerAttackProperties) playerEntity).getComboCount());

                        if (damage >= maxDamage) {
                            critical = true;
                        }

                        if (target.hasControllingPassenger()) {
                            Entity passenger = target.getControllingPassenger();
                            if (passenger != null && critical) {
                                passenger.stopRiding();
                            }
                        }
                    });
            return true;
        }

        return false;
    }

    private float getMaxValueDamage(ItemStack stack, int comboCount) {
        float[] damageValues = getDefaultAttackDamageValues();
        float maxDamage = 0.0F;
        boolean piercing = false;

        if (stack.isIn(KHTags.WEAPONS_PIERCING.getTag())) {
            int[] piercingAnimations = getPiercingAnimation();
            for (int piercingAnimation : piercingAnimations) {
                if (comboCount == 0 && piercingAnimation == 1) {
                    piercing = true;
                    break;
                }
                if (piercingAnimations.length == 1 && comboCount % piercingAnimation == getAnimation() - 1) {
                    piercing = true;
                    break;
                }

                if (piercingAnimations.length == 2 && (comboCount - (piercingAnimation - 1)) % getAnimation() == 0) {
                    piercing = true;
                    break;
                }
            }

            if (piercingAnimations.length == getAnimation()) piercing = true;
        }

        if (getOnlyDamageType() == KHDamageCalculator.DamageType.PIERCING) piercing = true;

        int startIndex = piercing ? 5 : 0;
        int endIndex = piercing ? 9 : 4;

        for (int i = startIndex; i <= endIndex; i++) {
            if (damageValues[i] > maxDamage) {
                maxDamage = damageValues[i];
            }
        }

        return maxDamage;
    }
}