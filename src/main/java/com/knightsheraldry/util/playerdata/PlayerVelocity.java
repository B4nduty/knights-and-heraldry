package com.knightsheraldry.util.playerdata;

import com.knightsheraldry.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerVelocity {
    public static void updateSpeedHistory(IEntityDataSaver player, float velocity) {
        NbtCompound nbt = player.knightsheraldry$getPersistentData();
        nbt.putFloat("speedHistory", velocity);
        syncSpeedHistory(velocity, (ServerPlayerEntity) player);
    }

    public static void updateNonSprintingTicks(IEntityDataSaver player, int nonSprintingTicks) {
        NbtCompound nbt = player.knightsheraldry$getPersistentData();
        nbt.putInt("nonSprintingTicks", nonSprintingTicks);
        syncNonSprintingTicks(nonSprintingTicks, (ServerPlayerEntity) player);
    }

    public static void updatePreviousBlockPos(IEntityDataSaver player, long previousBlockPos) {
        NbtCompound nbt = player.knightsheraldry$getPersistentData();
        nbt.putLong("previousBlockPos", previousBlockPos);
        syncPreviousBlockPos(previousBlockPos, (ServerPlayerEntity) player);
    }

    public static void syncSpeedHistory(float speedHistory, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeFloat(speedHistory);
        ServerPlayNetworking.send(player, ModMessages.VELOCITY_ID, buffer);
    }

    public static void syncNonSprintingTicks(int nonSprintingTicks, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(nonSprintingTicks);
        ServerPlayNetworking.send(player, ModMessages.NON_TICK_ID, buffer);
    }

    public static void syncPreviousBlockPos(long previousBlockPos, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeLong(previousBlockPos);
        ServerPlayNetworking.send(player, ModMessages.PREVIOUS_BLOCK_POS_ID, buffer);
    }
}