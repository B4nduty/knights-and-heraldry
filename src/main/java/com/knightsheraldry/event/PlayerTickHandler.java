package com.knightsheraldry.event;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.StaminaData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class PlayerTickHandler implements ServerTickEvents.StartTick{
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
            int stamina = ((IEntityDataSaver) playerEntity).bsroleplay$getPersistentData().getInt("stamina_int");
            int staminaRecoverTime = 20;
            int totalStamina = 200;

            int ticksPerRecovery = (int) (staminaRecoverTime * (20.0 / totalStamina));
            StaminaData.addStamina(((IEntityDataSaver) playerEntity), 0);
            if (playerEntity.age % ticksPerRecovery == 0 && stamina < totalStamina) {
                StaminaData.addStamina(((IEntityDataSaver) playerEntity), Math.min(1, totalStamina - stamina));
            }
            if (!KnightsHeraldry.CONFIG.common.getBlocking) {
                if (playerEntity.isBlocking()) {
                    int staminaCost = 5;

                    if (stamina >= staminaCost) {
                        if (playerEntity.age % 20 == 0) {
                            StaminaData.removeStamina((IEntityDataSaver) playerEntity, staminaCost);
                            playerEntity.sendMessage(Text.literal("Shield block performed! Stamina remaining: " + (stamina - staminaCost)), true);
                        }
                    } else {
                        playerEntity.sendMessage(Text.literal("Not enough stamina to block!"), true);
                    }
                }
            }
        }
    }
}