package com.knightsheraldry.items.item.khweapon;

import banduty.stoneycore.util.SCDamageCalculator;
import banduty.stoneycore.util.weaponutil.SCWeaponUtil;
import com.knightsheraldry.items.ModToolMaterials;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Billhook extends SwordItem {
    public Billhook(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);

        if (attacker instanceof PlayerEntity playerEntity) {
            SCDamageCalculator.DamageType damageType = SCWeaponUtil.calculateDamageType(stack, ((PlayerAttackProperties) playerEntity).getComboCount());
            double maxDistance = SCWeaponUtil.getMaxDistance(this);
            Box detectionBox = new Box(playerEntity.getBlockPos()).expand(maxDistance);
            Vec3d playerPos = playerEntity.getPos();
            playerEntity.getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                            entity != playerEntity && entity == target && playerEntity.getBlockPos().isWithinDistance(entity.getBlockPos(), maxDistance + 1))
                    .forEach(entity -> {
                        boolean critical = false;
                        double distance = playerPos.distanceTo(target.getPos());
                        double damage = SCDamageCalculator.getSCDamage(playerEntity, SCWeaponUtil.calculateDamage(this, distance,
                                damageType), damageType);
                        double maxDamage = SCWeaponUtil.getDamageValues(damageType, this);

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
}