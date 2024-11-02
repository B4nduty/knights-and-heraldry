package com.knightsheraldry.event;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.KHUnderArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.items.custom.item.KHWeapons;
import com.knightsheraldry.util.playerdata.IEntityDataSaver;
import com.knightsheraldry.util.itemdata.KHTags;
import com.knightsheraldry.util.playerdata.StaminaData;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
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
    private int damageTick;

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
            if (!playerEntity.isSpectator()) {
                handlePlayerTick(playerEntity);
            }
            if (!isWearingFullKHArmorSet(playerEntity)) {
                TrinketsApi.getTrinketComponent(playerEntity).ifPresent(trinketComponent -> {
                    trinketComponent.getAllEquipped().forEach(pair -> {
                        ItemStack trinketStack = pair.getRight();
                        if (trinketStack.getItem() instanceof KHTrinketsItem && !trinketStack.isIn(KHTags.ALWAYS_WEARABLE.getTag())) {
                            playerEntity.giveItemStack(trinketStack);
                            trinketStack.setCount(0);
                        }
                    });
                });
            }

            int swallowTailArrowCount = ((IEntityDataSaver) playerEntity).knightsheraldry$getPersistentData().getInt("swallowtail_arrow_count");
            if (swallowTailArrowCount >= 1 && !playerEntity.isCreative()) {
                damageTick++;
                if (damageTick % 20 == 0 && (playerEntity.isSprinting() || playerEntity.getVelocity().horizontalLengthSquared() > 0)) {
                    playerEntity.damage(playerEntity.getDamageSources().genericKill(), 0.2f);
                }
            }
        }
    }

    private boolean isWearingFullKHArmorSet(LivingEntity entity) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (isArmorSlot(slot)) {
                ItemStack armorPiece = entity.getEquippedStack(slot);
                if (!(armorPiece.getItem() instanceof KHUnderArmorItem)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isArmorSlot(EquipmentSlot slot) {
        return slot == EquipmentSlot.HEAD || slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;
    }

    private void handlePlayerTick(ServerPlayerEntity playerEntity) {
        IEntityDataSaver dataSaver = (IEntityDataSaver) playerEntity;
        int stamina = dataSaver.knightsheraldry$getPersistentData().getInt("stamina_int");

        if ((playerEntity.isCreative() || playerEntity.isSpectator())) {
            if (stamina < 200) StaminaData.addStamina((IEntityDataSaver) playerEntity, 200 - stamina);
            removeStaminaEffects(playerEntity);
            StaminaData.setStaminaBlocked((IEntityDataSaver) playerEntity, false);
        }

        if (stamina > 200)
            StaminaData.removeStamina((IEntityDataSaver) playerEntity, stamina - 200);

        if (!playerEntity.isCreative() || !playerEntity.isSpectator()) {
            handleStaminaRecovery(playerEntity, stamina);
            handleStaminaEffects(playerEntity, stamina);
            handleStaminaUsage(playerEntity, stamina);
        }
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

    private void handleStaminaUsage(ServerPlayerEntity playerEntity, int stamina) {
        IEntityDataSaver dataSaver = (IEntityDataSaver) playerEntity;
        boolean staminaBlocked = dataSaver.knightsheraldry$getPersistentData().getBoolean("stamina_blocked");

        if (isHoldingKHWeapon(playerEntity) && !staminaBlocked && !KnightsHeraldry.config().getBlocking()
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
        return stack.getItem() instanceof KHUnderArmorItem;
    }
}