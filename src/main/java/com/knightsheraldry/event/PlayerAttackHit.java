package com.knightsheraldry.event;

import com.knightsheraldry.items.item.KHWeapon;
import com.knightsheraldry.items.item.khweapon.Lance;
import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.playerdata.IEntityDataSaver;
import net.bettercombat.api.AttackHand;
import net.bettercombat.api.client.BetterCombatClientEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlayerAttackHit implements BetterCombatClientEvents.PlayerAttackHit {
    @Override
    public void onPlayerAttackStart(ClientPlayerEntity player, AttackHand attackHand, List<Entity> list, @Nullable Entity entity) {
        if (player.getMainHandStack().getItem() instanceof KHWeapon || player.getMainHandStack().getItem() instanceof Lance) {
            IEntityDataSaver dataSaver = (IEntityDataSaver) player;
            var persistentData = dataSaver.knightsheraldry$getPersistentData();

            if (player.isCreative()) return;

            boolean staminaBlocked = persistentData.getBoolean("stamina_blocked");
            int stamina = persistentData.getInt("stamina_int");
            int staminaCost = 10;

            if (stamina < staminaCost || staminaBlocked) {
                return;
            }

            ClientPlayNetworking.send(ModMessages.ATTACK_ID, PacketByteBufs.create());
        }
    }
}
