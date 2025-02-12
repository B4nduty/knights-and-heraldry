package com.knightsheraldry.event;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.event.custom.LivingEntityDamageEvents;
import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.itemdata.KHTags;
import com.knightsheraldry.util.playerdata.IEntityDataSaver;
import com.knightsheraldry.util.playerdata.StaminaData;
import com.knightsheraldry.util.weaponutil.KHWeaponUtil;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class EntityDamageHandler implements LivingEntityDamageEvents {
    private static final float STRENGTH_MULTIPLIER = 3.0F;
    private static final float WEAKNESS_MULTIPLIER = 4.0F;
    private static final int PARRY_WINDOW_TICKS = 10;
    private static final float PARRY_KNOCKBACK_STRENGTH = 0.5F;
    private static final int STAMINA_COST_ON_PARRY = 20;

    @Override
    public float onDamage(LivingEntity target, DamageSource source, float amount) {
        if (!(source.getAttacker() instanceof LivingEntity attacker)) {
            return amount;
        }

        if (handleParry(target, attacker)) {
            return 0.0F;
        }

        if (!(source.getAttacker() instanceof PlayerEntity player)) {
            return amount;
        }

        ItemStack weaponStack = player.getMainHandStack();
        if (weaponStack.getItem() instanceof KHWeapon khWeapon) {
            amount = calculateWeaponDamage(player, target, khWeapon, weaponStack, amount);
        }

        amount = applyStatusEffectModifiers(player, amount);
        return Math.max(amount, 0.0F);
    }

    private boolean handleParry(LivingEntity target, LivingEntity attacker) {
        if (!KnightsHeraldry.config().getParry() || !(target instanceof PlayerEntity player)) {
            return false;
        }

        if (!player.isBlocking()) {
            return false;
        }

        NbtCompound persistentData = ((IEntityDataSaver) player).knightsheraldry$getPersistentData();
        if (!persistentData.contains("BlockStartTick")) {
            return false;
        }

        int blockStartTick = persistentData.getInt("BlockStartTick");
        int currentTick = (int) player.getWorld().getTime();

        if (currentTick - blockStartTick > PARRY_WINDOW_TICKS) {
            return false;
        }

        performParryEffects(player, attacker);
        StaminaData.removeStamina((IEntityDataSaver) player, STAMINA_COST_ON_PARRY);
        return true;
    }

    private void performParryEffects(PlayerEntity player, LivingEntity attacker) {
        Vec3d playerPos = player.getPos();
        Vec3d attackerPos = attacker.getPos();
        Vec3d knockbackDirection = playerPos.subtract(attackerPos).normalize();

        attacker.takeKnockback(PARRY_KNOCKBACK_STRENGTH, knockbackDirection.x, knockbackDirection.z);

        player.getWorld().playSound(
                null, attacker.getX(), attacker.getY(), attacker.getZ(),
                SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.PLAYERS, 1.0F, 1.5F
        );
    }

    private float calculateWeaponDamage(PlayerEntity player, LivingEntity target,
                                        KHWeapon weapon, ItemStack stack, float originalDamage) {
        double maxDistance = KHWeaponUtil.getMaxDistance(weapon);
        double actualDistance = player.getPos().distanceTo(target.getPos());

        if (actualDistance > maxDistance + 1) {
            return originalDamage;
        }

        int comboCount = ((PlayerAttackProperties) player).getComboCount();
        KHDamageCalculator.DamageType damageType = KHWeaponUtil.calculateDamageType(stack, weapon, comboCount);

        float baseDamage = KHWeaponUtil.calculateDamage(weapon, actualDistance,
                damageType.getIndex() - 4, damageType.getIndex());

        float calculatedDamage = KHDamageCalculator.getKHDamage(target, baseDamage, damageType);

        if (stack.isIn(KHTags.WEAPONS_DAMAGE_BEHIND.getTag())) {
            calculatedDamage = KHWeaponUtil.adjustDamageForBackstab(target, player.getPos(), calculatedDamage);
        }

        return calculatedDamage != 0 ? calculatedDamage : originalDamage;
    }

    private float applyStatusEffectModifiers(PlayerEntity player, float damage) {
        StatusEffectInstance strength = player.getStatusEffect(StatusEffects.STRENGTH);
        StatusEffectInstance weakness = player.getStatusEffect(StatusEffects.WEAKNESS);

        if (strength != null) {
            damage += STRENGTH_MULTIPLIER * (strength.getAmplifier() + 1);
        }

        if (weakness != null) {
            damage -= WEAKNESS_MULTIPLIER * (weakness.getAmplifier() + 1);
        }

        return damage;
    }
}