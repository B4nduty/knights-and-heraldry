package com.knightsheraldry.items.custom;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModToolMaterials;
import com.knightsheraldry.util.ModTags;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class KHWeapons extends SwordItem {
    public KHWeapons(float attackSpeed, Settings settings) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, settings);
    }

    public float getAttackDamage(int index) {
        float[] defaultDamageValues = getDefaultAttackDamageValues();
        return (index >= 0 && index < defaultDamageValues.length) ? defaultDamageValues[index] : 0.0F;
    }

    public float[] getDefaultAttackDamageValues() {
        return new float[0];
    }

    protected double getRadius(int index) {
        double[] defaultRadiusValues = getDefaultRadiusValues();
        validateRadiusValues(defaultRadiusValues);
        return (index >= 0 && index < defaultRadiusValues.length) ? defaultRadiusValues[index] : 0.0d;
    }

    public double[] getDefaultRadiusValues() {
        return new double[0];
    }

    private void validateRadiusValues(double[] radiusValues) {
        for (int i = 1; i < radiusValues.length; i++) {
            if (radiusValues[i - 1] > radiusValues[i]) {
                KnightsHeraldry.LOGGER.error("Critical error: Index {} is higher than index {}. Radius values: {}", i - 1, i, java.util.Arrays.toString(radiusValues));
                throw new IllegalStateException("Index " + (i - 1) + " is higher than index " + i + ". Radius values: " + java.util.Arrays.toString(radiusValues));
            }
        }
    }

    private float getSlashingDamage(double distance) {
        if (distance < getRadius(0)) return getAttackDamage(0);
        if (distance < getRadius(1)) return getAttackDamage(1);
        if (distance < getRadius(2)) return getAttackDamage(2);
        if (distance < getRadius(3)) return getAttackDamage(3);
        if (distance < getRadius(4)) return getAttackDamage(4);
        return 0.0F;
    }

    private float getPiercingDamage(double distance) {
        if (distance < getRadius(0)) return getAttackDamage(5);
        if (distance < getRadius(1)) return getAttackDamage(6);
        if (distance < getRadius(2)) return getAttackDamage(7);
        if (distance < getRadius(3)) return getAttackDamage(8);
        if (distance < getRadius(4)) return getAttackDamage(9);
        return 0.0F;
    }

    private float getBludgeoningDamage(double distance) {
        if (distance < getRadius(0)) return getAttackDamage(10);
        if (distance < getRadius(1)) return getAttackDamage(11);
        if (distance < getRadius(2)) return getAttackDamage(12);
        if (distance < getRadius(3)) return getAttackDamage(13);
        if (distance < getRadius(4)) return getAttackDamage(14);
        return 0.0F;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity playerEntity) {
            Vec3d playerPos = playerEntity.getPos();
            double maxDistance = getRadius(4);
            Box detectionBox = new Box(playerEntity.getBlockPos()).expand(maxDistance);

            playerEntity.getWorld().getEntitiesByClass(LivingEntity.class, detectionBox, entity ->
                    entity != playerEntity && entity == target && playerEntity.getBlockPos().isWithinDistance(entity.getBlockPos(), maxDistance + 1)).forEach(entity -> {
                double distance = playerPos.distanceTo(target.getPos());
                int comboCount = ((PlayerAttackProperties) playerEntity).getComboCount();

                float damage;
                boolean bludgeoning = stack.getOrCreateNbt().getInt("CustomModelData") == 1;
                boolean piercing = comboCount % 3 == 1;

                if (bludgeoning) {
                    damage = getBludgeoningDamage(distance);
                } else if (piercing) {
                    damage = getPiercingDamage(distance);
                } else {
                    damage = getSlashingDamage(distance);
                }

                if (stack.isIn(ModTags.Items.KH_WEAPONS_DAMAGE_BEHIND)) {
                    Vec3d targetToAttacker = playerPos.subtract(target.getPos()).normalize();
                    Vec3d targetFacing = target.getRotationVec(1.0F).normalize();
                    boolean isBehind = targetFacing.dotProduct(targetToAttacker) < -0.5;

                    if (isBehind) {
                        damage *= 2;
                    }
                }

                if (stack.isIn(ModTags.Items.KH_WEAPONS_IGNORES_ARMOR)) {
                    target.setHealth(target.getHealth() - damage);
                    if (target.getHealth() <= 0.0F) {
                        target.onDeath(playerEntity.getDamageSources().playerAttack(playerEntity));
                    }
                    return;
                }

                entity.damage(playerEntity.getWorld().getDamageSources().playerAttack(playerEntity), damage);
            });
        }
        return true;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        if (stack.isIn(ModTags.Items.KH_WEAPONS_SHIELD)) return UseAction.BLOCK;
        return UseAction.NONE;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient && itemStack.isIn(ModTags.Items.KH_WEAPONS_BLUDGEONING) && user.isSneaking()) {
            int currentVariant = itemStack.getOrCreateNbt().getInt("CustomModelData");
            int newVariant = (currentVariant + 1) % 2;

            itemStack.getOrCreateNbt().putInt("CustomModelData", newVariant);
            return TypedActionResult.success(itemStack);
        }
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }
}