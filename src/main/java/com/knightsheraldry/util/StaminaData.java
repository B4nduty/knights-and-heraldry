package com.knightsheraldry.util;

import com.knightsheraldry.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class StaminaData {

    // Updates the ability to use stamina and syncs the change with the player
    public static void setAbleStamina(IEntityDataSaver player, boolean ableStamina) {
        NbtCompound nbt = player.knightsheraldry$getPersistentData();
        nbt.putBoolean("able_stamina", ableStamina);
        syncAbleStamina(ableStamina, (ServerPlayerEntity) player);
    }

    // Updates whether stamina is blocked and syncs the change with the player
    public static void setStaminaBlocked(IEntityDataSaver player, boolean blocked) {
        NbtCompound nbt = player.knightsheraldry$getPersistentData();
        nbt.putBoolean("stamina_blocked", blocked);
        syncStaminaBlocked(blocked, (ServerPlayerEntity) player);
    }

    // Adds stamina to the player, ensuring it doesn't exceed the maximum limit
    public static void addStamina(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.knightsheraldry$getPersistentData();
        int currentStamina = nbt.getInt("stamina_int");
        int maxStamina = 200;

        currentStamina = Math.min(currentStamina + amount, maxStamina);

        nbt.putInt("stamina_int", currentStamina);
        if (player instanceof ServerPlayerEntity) {
            syncStamina(currentStamina, (ServerPlayerEntity) player);
        }
    }

    // Removes stamina from the player, ensuring it doesn't go below zero
    public static void removeStamina(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.knightsheraldry$getPersistentData();
        int currentStamina = nbt.getInt("stamina_int");

        currentStamina = Math.max(currentStamina - amount, 0);

        nbt.putInt("stamina_int", currentStamina);
        syncStamina(currentStamina, (ServerPlayerEntity) player);
    }

    // Sends updated stamina value to the player
    public static void syncStamina(int stamina, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(stamina);
        ServerPlayNetworking.send(player, ModMessages.STAMINA_INT_ID, buffer);
    }

    // Sends updated stamina blocked status to the player
    public static void syncStaminaBlocked(boolean blocked, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeBoolean(blocked);
        ServerPlayNetworking.send(player, ModMessages.STAMINA_BLOCKED_ID, buffer);
    }

    // Sends updated ability to use stamina to the player
    public static void syncAbleStamina(boolean ableStamina, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeBoolean(ableStamina);
        ServerPlayNetworking.send(player, ModMessages.ABLE_STAMINA_ID, buffer);
    }
}