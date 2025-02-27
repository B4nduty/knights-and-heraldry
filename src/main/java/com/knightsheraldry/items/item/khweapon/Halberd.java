package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.items.item.SCWeapon;
import banduty.stoneycore.util.SCDamageCalculator;
import banduty.stoneycore.util.weaponutil.SCWeaponUtil;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Halberd extends SwordItem implements SCWeapon {
    public Halberd(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public int getAnimation() {
        return 2;
    }

    @Override
    public SCDamageCalculator.DamageType getOnlyDamageType() {
        return null;
    }

    @Override
    public double[] getRadiusValues() {
        return new double[] {
                4.0d, //1st Distance
                4.7d, //2nd Distance
                5.5d, //3rd Distance
                6.3d, //4th Distance
                7.0d  //5th Distance
        };
    }

    @Override
    public float[] getAttackDamageValues() {
        return new float[] {
                KnightsHeraldry.getConfig().getHalberdDamageSlashing(),
                KnightsHeraldry.getConfig().getHalberdDamagePiercing(),
                0.0F //Bludgeoning
        };
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
            SCDamageCalculator.DamageType damageType = SCWeaponUtil.calculateDamageType(stack, this, ((PlayerAttackProperties) playerEntity).getComboCount());
            double maxDistance = SCWeaponUtil.getMaxDistance(this);
            Box detectionBox = new Box(playerEntity.getBlockPos()).expand(maxDistance);
            Vec3d playerPos = playerEntity.getPos();
            playerEntity.getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                            entity != playerEntity && entity == target && playerEntity.getBlockPos().isWithinDistance(entity.getBlockPos(), maxDistance + 1))
                    .forEach(entity -> {
                        boolean critical = false;
                        double distance = playerPos.distanceTo(target.getPos());
                        float damage = SCDamageCalculator.getSCDamage(playerEntity, SCWeaponUtil.calculateDamage(this, distance,
                                damageType), damageType);
                        float maxDamage = getMaxValueDamage(((PlayerAttackProperties) playerEntity).getComboCount());

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

    private float getMaxValueDamage(int comboCount) {
        float[] damageValues = getAttackDamageValues();
        float maxDamage = 0.0F;
        boolean piercing = false;

        if (getAnimation() > 0) {
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

        if (getOnlyDamageType() == SCDamageCalculator.DamageType.PIERCING) piercing = true;

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