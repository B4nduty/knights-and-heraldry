package com.knightsheraldry.items.custom;

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
                        float damage = calculateDamageBasedOnWeaponType(stack, distance, ((PlayerAttackProperties) playerEntity).getComboCount());
                        float maxDamage = getMaxValueDamage();

                        if (damage >= maxDamage) {
                            critical = true;
                        }

                        if (target.hasVehicle()) {
                            Entity vehicle = target.getVehicle();
                            if (vehicle != null && critical) {
                                target.stopRiding();
                            }
                        }
                    });
            return true;
        }

        return false;
    }

    private float getMaxValueDamage() {
        float[] damageValues = getDefaultAttackDamageValues();
        float maxDamage = 0.0F;
        for (float damage : damageValues) {
            if (damage > maxDamage) {
                maxDamage = damage;
            }
        }
        return maxDamage;
    }
}