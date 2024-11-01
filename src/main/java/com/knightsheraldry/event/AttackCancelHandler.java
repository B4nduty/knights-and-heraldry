package com.knightsheraldry.event;

import com.knightsheraldry.util.playerdata.IEntityDataSaver;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class AttackCancelHandler implements ClientPreAttackCallback {
    @Override
    public boolean onClientPlayerPreAttack(MinecraftClient client, ClientPlayerEntity player, int clickCount) {
        return ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getBoolean("stamina_blocked");
    }
}
