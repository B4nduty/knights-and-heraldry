package com.knightsheraldry.items.custom;

import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.util.math.*;

public class WoodenLance extends KHWeapons implements DyeableItem {
    public WoodenLance(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE)) {
            return nbtCompound.getInt(COLOR_KEY);
        }
        return DEFAULT_COLOR;
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F, //Slashing
                0.0F, 0.1F, 0.2F, 0.1F, 0.0F, //Piercing
                0.0F, 0.0F, 0.0F, 0.0F, 0.0F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher or equal than its next value
                4.4d, //1st Distance
                4.5d, //2nd Distance
                4.7d, //3rd Distance
                4.8d, //4th Distance
                5.0d //5th Distance
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