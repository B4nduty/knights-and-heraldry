package com.knightsheraldry.event;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.StaminaData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
            if (!playerEntity.isSpectator()) {
                int stamina = ((IEntityDataSaver) playerEntity).bsroleplay$getPersistentData().getInt("stamina_int");
                int totalStamina = 200;

                double foodLevel = playerEntity.getHungerManager().getFoodLevel();
                double health = playerEntity.getHealth();
                double ticksPerRecovery = (foodLevel + health) / 20.0d;
                int roundOff = (int) (4 - Math.round(ticksPerRecovery));
                StaminaData.addStamina(((IEntityDataSaver) playerEntity), 0);
                if (stamina == 0) {
                    StaminaData.setStaminaBlocked((IEntityDataSaver) playerEntity, true);
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, -1, 3,
                            false, false, false));
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, -1, 1,
                            false, false, false));
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, -1, 3,
                            false, false, false));
                }
                boolean staminaBlocked = ((IEntityDataSaver) playerEntity).bsroleplay$getPersistentData().getBoolean("stamina_blocked");
                if (staminaBlocked && stamina == 30) {
                    StaminaData.setStaminaBlocked((IEntityDataSaver) playerEntity, false);
                    playerEntity.removeStatusEffect(StatusEffects.SLOWNESS);
                    playerEntity.removeStatusEffect(StatusEffects.MINING_FATIGUE);
                    playerEntity.removeStatusEffect(StatusEffects.WEAKNESS);
                }
                if (ticksPerRecovery != 0 && playerEntity.age % roundOff == 0 && stamina < totalStamina) {
                    StaminaData.addStamina(((IEntityDataSaver) playerEntity), Math.min(1, totalStamina - stamina));
                }
                if (!staminaBlocked && !KnightsHeraldry.CONFIG.common.getBlocking && playerEntity.isBlocking()
                        && stamina >= 1 && playerEntity.age % 4 == 0) {
                    StaminaData.removeStamina((IEntityDataSaver) playerEntity, 1);
                }
                if (!staminaBlocked && playerEntity.isSprinting() && stamina >= 1) {
                    StaminaData.removeStamina((IEntityDataSaver) playerEntity, 1);
                }
                if (!staminaBlocked && !playerEntity.isOnGround() && playerEntity.getVelocity().y > 0 && stamina >= 8) {
                    StaminaData.removeStamina((IEntityDataSaver) playerEntity, 8);
                } else if (!staminaBlocked && !playerEntity.isOnGround() && playerEntity.getVelocity().y > 0) {
                    StaminaData.removeStamina((IEntityDataSaver) playerEntity, stamina);
                }
            }
        }
    }
}