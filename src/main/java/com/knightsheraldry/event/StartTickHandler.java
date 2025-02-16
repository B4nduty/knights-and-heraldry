package com.knightsheraldry.event;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.KHUnderArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.util.SharedParameters;
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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class StartTickHandler implements ServerTickEvents.StartTick {
    private int damageTick;

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
            if (!playerEntity.isSpectator()) {
                handlePlayerTick(playerEntity);
                if (KnightsHeraldry.getConfig().getParry()) handleParry(playerEntity);
            }
            if (!isWearingFullKHArmorSet(playerEntity)) {
                TrinketsApi.getTrinketComponent(playerEntity).ifPresent(trinketComponent ->
                        trinketComponent.getAllEquipped().forEach(pair -> {
                            ItemStack trinketStack = pair.getRight();
                            if (trinketStack.getItem() instanceof KHTrinketsItem && !trinketStack.isIn(KHTags.ALWAYS_WEARABLE.getTag())) {
                                playerEntity.giveItemStack(trinketStack);
                                trinketStack.setCount(0);
                            }
                }));
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

    private void handleParry(ServerPlayerEntity player) {
        boolean isBlocking = player.isBlocking();
        ItemStack activeItem = player.getActiveItem();
        boolean usingCustomShield = activeItem.isIn(KHTags.WEAPONS_SHIELD.getTag());

        NbtCompound persistentData = ((IEntityDataSaver) player).knightsheraldry$getPersistentData();
        boolean wasBlocking = persistentData.contains("BlockStartTick");

        if (isBlocking && usingCustomShield) {
            if (!wasBlocking) {
                persistentData.putInt("BlockStartTick", (int) player.getWorld().getTime());
            }
        } else {
            persistentData.remove("BlockStartTick");
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
            if (stamina < SharedParameters.TOTAL_STAMINA) StaminaData.addStamina((IEntityDataSaver) playerEntity, SharedParameters.TOTAL_STAMINA - stamina);
            removeStaminaEffects(playerEntity);
            StaminaData.setStaminaBlocked((IEntityDataSaver) playerEntity, false);
        }

        if (stamina > SharedParameters.TOTAL_STAMINA)
            StaminaData.removeStamina((IEntityDataSaver) playerEntity, stamina - SharedParameters.TOTAL_STAMINA);

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
        if (ticksPerRecovery != 0 && playerEntity.age % roundOff == 0 && stamina < SharedParameters.TOTAL_STAMINA
                && !playerEntity.isTouchingWater()) {
            StaminaData.addStamina((IEntityDataSaver) playerEntity, Math.min(1, SharedParameters.TOTAL_STAMINA - stamina));
        }
    }

    private void handleStaminaEffects(ServerPlayerEntity playerEntity, int stamina) {
        IEntityDataSaver dataSaver = (IEntityDataSaver) playerEntity;
        boolean staminaBlocked = dataSaver.knightsheraldry$getPersistentData().getBoolean("stamina_blocked");
        long firstLevel = Math.absExact((int) (SharedParameters.TOTAL_STAMINA * 0.3f));
        long secondLevel = Math.absExact((int) (SharedParameters.TOTAL_STAMINA * 0.15f));

        if (stamina < firstLevel && stamina > secondLevel) {
            applyStaminaEffects(playerEntity, 0, 0);
        }

        if (stamina == 0) {
            StaminaData.setStaminaBlocked(dataSaver, true);
            applyStaminaEffects(playerEntity, 3, 3);
        }

        if (staminaBlocked && stamina == secondLevel) {
            StaminaData.setStaminaBlocked(dataSaver, false);
            removeStaminaEffects(playerEntity);
        }

        if (stamina == firstLevel) {
            StaminaData.setStaminaBlocked(dataSaver, false);
            removeStaminaEffects(playerEntity);
        }
    }

    private void handleStaminaUsage(ServerPlayerEntity playerEntity, int stamina) {
        IEntityDataSaver dataSaver = (IEntityDataSaver) playerEntity;
        boolean staminaBlocked = dataSaver.knightsheraldry$getPersistentData().getBoolean("stamina_blocked");

        if (isHoldingKHWeapon(playerEntity) && !staminaBlocked && !KnightsHeraldry.getConfig().getBlocking()
                && playerEntity.isBlocking() && stamina >= 1 && playerEntity.age % 2 == 0) {
            StaminaData.removeStamina(dataSaver, 1);
        }

        if (isWearingKHArmor(playerEntity) && !staminaBlocked && playerEntity.isSprinting() && stamina >= 1) {
            StaminaData.removeStamina(dataSaver, 1);
        }

        if (isWearingKHArmor(playerEntity) && !staminaBlocked && !playerEntity.isOnGround()
                && playerEntity.getVelocity().y > 0 && !playerEntity.isBlocking()
                && !playerEntity.hasVehicle() && !playerEntity.isTouchingWater()) {
            StaminaData.removeStamina(dataSaver, Math.min(stamina, 6));
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
        return stack.getItem() instanceof KHWeapon;
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