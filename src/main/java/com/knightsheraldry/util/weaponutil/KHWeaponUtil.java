package com.knightsheraldry.util.weaponutil;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.itemdata.KHTags;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class KHWeaponUtil {
    public static KHDamageCalculator.DamageType calculateDamageType(ItemStack stack, KHWeapon khWeapon, int comboCount) {
        boolean bludgeoning = stack.getOrCreateNbt().getBoolean("Bludgeoning");
        if (stack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag())) bludgeoning = !bludgeoning;

        boolean piercing = (khWeapon.getAnimation() > 0 && isComboCountPiercing(khWeapon, comboCount)) ||
                khWeapon.getOnlyDamageType() == KHDamageCalculator.DamageType.PIERCING;

        return (bludgeoning || khWeapon.getOnlyDamageType() == KHDamageCalculator.DamageType.BLUDGEONING) ?
                KHDamageCalculator.DamageType.BLUDGEONING :
                (piercing || stack.isIn(KHTags.WEAPONS_BLUDGEONING_TO_PIERCING.getTag())) ?
                        KHDamageCalculator.DamageType.PIERCING :
                        KHDamageCalculator.DamageType.SLASHING;
    }
    
    private static boolean isComboCountPiercing(KHWeapon khWeapon, int comboCount) {
        int[] piercingAnimations = khWeapon.getPiercingAnimation();
        validatePiercingValues(khWeapon);
        int animationLength = khWeapon.getAnimation();
        for (int piercingAnimation : piercingAnimations) {
            if (comboCount % animationLength == piercingAnimation - 1) return true;
        }
        return piercingAnimations.length == animationLength;
    }

    public static float adjustDamageForBackstab(LivingEntity target, Vec3d playerPos, float damage) {
        boolean isBehind = target.getRotationVec(1.0F).normalize().dotProduct(playerPos.subtract(target.getPos()).normalize()) < -0.5;
        return isBehind ? damage * 2 : damage;
    }

    public static float getAttackDamage(KHWeapon khWeapon, int index) {
        float[] defaultDamageValues = khWeapon.getAttackDamageValues();
        return (index >= 0 && index < defaultDamageValues.length) ? defaultDamageValues[index] : 0.0F;
    }

    public static double getMaxDistance(KHWeapon khWeapon) {
        return getRadius(khWeapon, 4);
    }
    
    private static double getRadius(KHWeapon khWeapon, int index) {
        double[] defaultRadiusValues = khWeapon.getRadiusValues();
        validateRadiusValues(defaultRadiusValues);
        return (index >= 0 && index < defaultRadiusValues.length) ? defaultRadiusValues[index] : 0.0d;
    }
    
    private static void validateRadiusValues(double[] radiusValues) {
        for (int i = 1; i < radiusValues.length; i++) {
            if (radiusValues[i - 1] > radiusValues[i]) {
                KnightsHeraldry.LOGGER.error("Critical error: Index {} is higher than index {}. Radius values: {}", i - 1, i, java.util.Arrays.toString(radiusValues));
                throw new IndexOutOfBoundsException("Index " + (i - 1) + " is higher than index " + i + ". Radius values: " + java.util.Arrays.toString(radiusValues));
            }
        }
    }

    private static void validatePiercingValues(KHWeapon khWeapon) {
        if (khWeapon.getPiercingAnimation().length > 2) {
            KnightsHeraldry.LOGGER.error("Critical error: Piercing Animations Index is higher than 2");
            throw new IndexOutOfBoundsException("Critical error: Piercing Animations Index is higher than 2");
        }
    }
    
    public static float calculateDamage(KHWeapon khWeapon, double distance, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (distance < getRadius(khWeapon, i - startIndex) + 0.25F) {
                return getAttackDamage(khWeapon, i);
            }
        }
        return 0.0F;
    }

    public static void replantCrop(World world, BlockPos pos, CropBlock cropBlock, PlayerEntity player) {
        ItemStack seedStack = new ItemStack(cropBlock.asItem());
        if (!seedStack.isEmpty()) {
            world.setBlockState(pos, cropBlock.getDefaultState(), Block.NOTIFY_ALL);
            world.emitGameEvent(player, GameEvent.BLOCK_PLACE, pos);
            if (player.getAbilities().creativeMode) {
                return;
            }
            seedStack.decrement(1);
        }
    }
}
