package com.knightsheraldry.event;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.KHWeapons;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.ModTags;
import com.knightsheraldry.util.StaminaData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class StartTickHandler implements ServerTickEvents.StartTick {

    private static final int TOTAL_STAMINA = 200;
    private static final int MID_STAMINA_THRESHOLD = 60;
    private static final int LOW_STAMINA_THRESHOLD = 30;
    private static final int MINING_FATIGUE_LEVEL = 3;
    private static final int SLOWNESS_LEVEL = 3;

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
            if (!playerEntity.isSpectator()) {
                handlePlayerTick(playerEntity);
            }
        }
    }

    private void handlePlayerTick(ServerPlayerEntity playerEntity) {
        IEntityDataSaver dataSaver = (IEntityDataSaver) playerEntity;
        int stamina = dataSaver.knightsheraldry$getPersistentData().getInt("stamina_int");
        boolean ableStamina = dataSaver.knightsheraldry$getPersistentData().getBoolean("able_stamina");

        handleStaminaRecovery(playerEntity, stamina);
        handleStaminaEffects(playerEntity, stamina);
        handleStaminaUsage(playerEntity, stamina, ableStamina);
    }

    private void handleStaminaRecovery(ServerPlayerEntity playerEntity, int stamina) {
        double foodLevel = playerEntity.getHungerManager().getFoodLevel();
        double health = playerEntity.getHealth();
        double ticksPerRecovery = (foodLevel + health) / 5.0d;
        int roundOff = (int) (10 - Math.round(ticksPerRecovery));

        StaminaData.addStamina((IEntityDataSaver) playerEntity, 0);
        if (ticksPerRecovery != 0 && playerEntity.age % roundOff == 0 && stamina < TOTAL_STAMINA
                && !playerEntity.isTouchingWater()) {
            StaminaData.addStamina((IEntityDataSaver) playerEntity, Math.min(1, TOTAL_STAMINA - stamina));
        }
    }

    private void handleStaminaEffects(ServerPlayerEntity playerEntity, int stamina) {
        IEntityDataSaver dataSaver = (IEntityDataSaver) playerEntity;
        boolean staminaBlocked = dataSaver.knightsheraldry$getPersistentData().getBoolean("stamina_blocked");

        if (stamina < MID_STAMINA_THRESHOLD && stamina > LOW_STAMINA_THRESHOLD) {
            applyStaminaEffects(playerEntity, 0, 0);
        }

        if (stamina == 0) {
            StaminaData.setStaminaBlocked(dataSaver, true);
            applyStaminaEffects(playerEntity, MINING_FATIGUE_LEVEL, SLOWNESS_LEVEL);
        }

        if (staminaBlocked && stamina == LOW_STAMINA_THRESHOLD) {
            StaminaData.setStaminaBlocked(dataSaver, false);
            removeStaminaEffects(playerEntity);
        }

        if (stamina == MID_STAMINA_THRESHOLD) {
            StaminaData.setStaminaBlocked(dataSaver, false);
            removeStaminaEffects(playerEntity);
        }
    }

    private void handleStaminaUsage(ServerPlayerEntity playerEntity, int stamina, boolean ableStamina) {
        IEntityDataSaver dataSaver = (IEntityDataSaver) playerEntity;
        boolean staminaBlocked = dataSaver.knightsheraldry$getPersistentData().getBoolean("stamina_blocked");

        if (ableStamina) {
            if (isHoldingKHWeapon(playerEntity) && !staminaBlocked && !KnightsHeraldry.CONFIG.common.getBlocking
                    && playerEntity.isBlocking() && stamina >= 1 && playerEntity.age % 2 == 0) {
                StaminaData.removeStamina(dataSaver, 1);
            }

            if (isWearingKHArmor(playerEntity) && !staminaBlocked && playerEntity.isSprinting() && stamina >= 1) {
                StaminaData.removeStamina(dataSaver, 1);
            }

            if (isWearingKHArmor(playerEntity) && !staminaBlocked && !playerEntity.isOnGround()
                    && playerEntity.getVelocity().y > 0 && stamina >= 6 && !playerEntity.isBlocking()
                    && !playerEntity.hasVehicle() && !playerEntity.isTouchingWater()) {
                StaminaData.removeStamina(dataSaver, 6);
            } else if (isWearingKHArmor(playerEntity) && !staminaBlocked && !playerEntity.isOnGround()
                    && playerEntity.getVelocity().y > 0 && !playerEntity.isBlocking()
                    && !playerEntity.hasVehicle() && !playerEntity.isTouchingWater()) {
                StaminaData.removeStamina(dataSaver, stamina);
            }

            if (isWearingKHArmor(playerEntity) && playerEntity.isTouchingWater() && stamina >= 1
                    && playerEntity.age % 2 == 0) {
                StaminaData.removeStamina(dataSaver, 1);
            }
        }
    }

    private void applyStaminaEffects(ServerPlayerEntity playerEntity, int miningFatigueLevel, int slownessLevel) {
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, -1,
                miningFatigueLevel, false, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, -1,
                slownessLevel, false, false, false));
    }

    private void removeStaminaEffects(ServerPlayerEntity playerEntity) {
        playerEntity.removeStatusEffect(StatusEffects.SLOWNESS);
        playerEntity.removeStatusEffect(StatusEffects.MINING_FATIGUE);
    }

    private boolean isHoldingKHWeapon(ServerPlayerEntity playerEntity) {
        return isKHWeapon(playerEntity.getMainHandStack()) || isKHWeapon(playerEntity.getOffHandStack());
    }

    private boolean isKHWeapon(ItemStack stack) {
        return stack.getItem() instanceof KHWeapons;
    }

    private boolean isWearingKHArmor(ServerPlayerEntity playerEntity) {
        for (ItemStack armorStack : playerEntity.getArmorItems()) {
            if (isKHArmor(armorStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean isKHArmor(ItemStack stack) {
        return stack.isIn(ModTags.Items.KH_ARMORS);
    }
}