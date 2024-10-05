package com.knightsheraldry.items.custom.item;

import com.knightsheraldry.util.KHTags;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Halberd extends KHWeapons {
    public Halberd(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Slashing
                0.0F, 7.0F, 10.5F, 7.0F, 3.5F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher or equal than its next value
                4.0d, //1st Distance
                4.2d, //2nd Distance
                4.5d, //3rd Distance
                4.8d, //4th Distance
                5.0d //5th Distance
        };
    }

    @Override
    public int getAnimation() {
        // How many animations has your item
        return 2;
    }

    @Override
    public int[] getPiercingAnimation() {
        // In which animation is Piercing Damage applied
        // Item needs to have Tag KH_WEAPONS_PIERCING
        // Max length of index is 2
        return new int[] {
                2
        };
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);

        if (attacker instanceof PlayerEntity playerEntity) {
            double maxDistance = getRadius(4);
            Box detectionBox = new Box(playerEntity.getBlockPos()).expand(maxDistance);
            Vec3d playerPos = playerEntity.getPos();
            playerEntity.getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                            entity != playerEntity && entity == target && playerEntity.getBlockPos().isWithinDistance(entity.getBlockPos(), maxDistance + 1))
                    .forEach(entity -> {
                        boolean critical = false;
                        double distance = playerPos.distanceTo(target.getPos());
                        float damage = calculateDamageBasedOnWeaponType(playerEntity, stack, distance, ((PlayerAttackProperties) playerEntity).getComboCount());
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

        if (stack.isIn(KHTags.Weapon.KH_WEAPONS_PIERCING)) {
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

        if (stack.isIn(KHTags.Weapon.KH_WEAPONS_ONLY_PIERCING)) piercing = true;

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