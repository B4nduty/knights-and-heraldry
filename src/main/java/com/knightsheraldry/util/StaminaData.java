package com.knightsheraldry.util;

import com.knightsheraldry.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class StaminaData {
    public static void setStaminaBlocked(IEntityDataSaver player, boolean blocked) {
        NbtCompound nbt = player.bsroleplay$getPersistentData();
        nbt.putBoolean("stamina_blocked", blocked);
        syncStaminaBlocked(blocked, (ServerPlayerEntity) player);
    }

    public static void addStamina(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.bsroleplay$getPersistentData();
        int stamina_int = nbt.getInt("stamina_int");
        int totalStamina = 200;
        if (stamina_int + amount >= totalStamina) {
            stamina_int = totalStamina;
        } else {
            stamina_int += amount;
        }

        nbt.putInt("stamina_int", stamina_int);
        if (player instanceof ServerPlayerEntity) {
            syncStamina(stamina_int, (ServerPlayerEntity) player);
        }
    }

    public static void removeStamina(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.bsroleplay$getPersistentData();
        int stamina_int = nbt.getInt("stamina_int");
        if (stamina_int - amount < 0) {
            stamina_int = 0;
        } else {
            stamina_int -= amount;
        }

        nbt.putInt("stamina_int", stamina_int);
        syncStamina(stamina_int, (ServerPlayerEntity) player);
    }

    public static void syncStamina(int stamina_int, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(stamina_int);
        ServerPlayNetworking.send(player, ModMessages.STAMINA_INT_ID, buffer);
    }

    public static void syncStaminaBlocked(boolean blocked, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeBoolean(blocked);
        ServerPlayNetworking.send(player, ModMessages.STAMINA_BLOCKED_ID, buffer);
    }
}