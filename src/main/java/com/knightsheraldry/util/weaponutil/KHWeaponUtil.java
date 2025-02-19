package com.knightsheraldry.util.weaponutil;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.item.KHWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public final class KHWeaponUtil {
    private static final double BACKSTAB_ANGLE_THRESHOLD = -0.5;
    private static final double RADIUS_TOLERANCE = 0.25;
    private static final int MAX_PIERCING_ANIMATIONS = 2;

    private KHWeaponUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static KHDamageCalculator.DamageType calculateDamageType(ItemStack stack, KHWeapon khWeapon, int comboCount) {
        boolean bludgeoningToPiercing = khWeapon.getAttackDamageValues()[0] == 0
                && khWeapon.getAttackDamageValues()[1] > 0 && khWeapon.getAttackDamageValues()[2] > 0;
        boolean isBludgeoning = stack.getOrCreateNbt().getBoolean("kh_bludgeoning");
        boolean isPiercing = isPiercingWeapon(khWeapon, comboCount);

        if (isBludgeoning || khWeapon.getOnlyDamageType() == KHDamageCalculator.DamageType.BLUDGEONING) {
            return KHDamageCalculator.DamageType.BLUDGEONING;
        }
        if (isPiercing || bludgeoningToPiercing) {
            return KHDamageCalculator.DamageType.PIERCING;
        }
        return KHDamageCalculator.DamageType.SLASHING;
    }

    private static boolean isPiercingWeapon(KHWeapon khWeapon, int comboCount) {
        return (khWeapon.getAnimation() > 0 && isComboCountPiercing(khWeapon, comboCount)) ||
                khWeapon.getOnlyDamageType() == KHDamageCalculator.DamageType.PIERCING;
    }

    private static boolean isComboCountPiercing(KHWeapon khWeapon, int comboCount) {
        int[] piercingAnimations = khWeapon.getPiercingAnimation();
        validatePiercingAnimations(piercingAnimations);

        int animationLength = khWeapon.getAnimation();
        for (int piercingAnimation : piercingAnimations) {
            if (comboCount % animationLength == piercingAnimation - 1) {
                return true;
            }
        }
        return piercingAnimations.length == animationLength;
    }

    public static float adjustDamageForBackstab(LivingEntity target, Vec3d playerPos, float damage) {
        Vec3d targetFacing = target.getRotationVec(1.0F).normalize();
        Vec3d attackDirection = playerPos.subtract(target.getPos()).normalize();
        boolean isBehind = targetFacing.dotProduct(attackDirection) < BACKSTAB_ANGLE_THRESHOLD;
        return isBehind ? damage * 2 : damage;
    }

    public static float getAttackDamage(KHWeapon khWeapon, int index) {
        float[] damageValues = khWeapon.getAttackDamageValues();
        return isValidIndex(index, damageValues.length) ? damageValues[index] : 0.0F;
    }

    public static double getMaxDistance(KHWeapon khWeapon) {
        return getRadius(khWeapon, 4);
    }

    public static double getRadius(KHWeapon khWeapon, int index) {
        double[] radiusValues = khWeapon.getRadiusValues();
        validateRadiusValues(radiusValues);

        return isValidIndex(index, radiusValues.length) ? radiusValues[index] : 0.0;
    }

    private static boolean isValidIndex(int index, int arrayLength) {
        return index >= 0 && index < arrayLength;
    }

    private static void validateRadiusValues(double[] radiusValues) {
        for (int i = 1; i < radiusValues.length; i++) {
            if (radiusValues[i - 1] > radiusValues[i]) {
                String errorMessage = String.format("Critical error: Radius values are not sorted. Index %d > Index %d. Values: %s",
                        i - 1, i, java.util.Arrays.toString(radiusValues));
                KnightsHeraldry.LOGGER.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }
        }
    }

    private static void validatePiercingAnimations(int[] piercingAnimations) {
        if (piercingAnimations.length > MAX_PIERCING_ANIMATIONS) {
            String errorMessage = "Critical error: Piercing Animations Index exceeds maximum allowed value of " + MAX_PIERCING_ANIMATIONS;
            KnightsHeraldry.LOGGER.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static float calculateDamage(KHWeapon khWeapon, double distance, KHDamageCalculator.DamageType damageType) {
        for (int i = 0; i <= 4; i++) {
            double radius = getRadius(khWeapon, i);
            if (distance < radius + RADIUS_TOLERANCE) {
                float attackDamage = getAttackDamage(khWeapon, damageType.getIndex());
                float percentage = calculatePercentageForIndex(i);
                return attackDamage * percentage;
            }
        }
        return 0.0F;
    }

    private static float calculatePercentageForIndex(int index) {
        return switch (index) {
            case 4 -> (1f/3f);
            case 3, 1 -> (2f/3f);
            case 2 -> 1.0f;
            default -> 0f;
        };
    }

    public static void replantCrop(World world, BlockPos pos, CropBlock cropBlock, PlayerEntity player, ItemStack stack, Hand hand) {
        ItemStack seedStack = new ItemStack(cropBlock.asItem());
        if (!seedStack.isEmpty()) {
            world.setBlockState(pos, cropBlock.getDefaultState(), Block.NOTIFY_ALL);
            world.emitGameEvent(player, GameEvent.BLOCK_PLACE, pos);

            if (!player.getAbilities().creativeMode) {
                seedStack.decrement(1);
            }
        }

        stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
    }
}