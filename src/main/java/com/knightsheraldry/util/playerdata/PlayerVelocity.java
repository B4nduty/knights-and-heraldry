package com.knightsheraldry.util.playerdata;

import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import com.knightsheraldry.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerVelocity {
    public static void updateSpeedHistory(IEntityDataSaver player, float velocity) {
        NbtCompound nbt = player.stoneycore$getPersistentData();
        nbt.putFloat("speedHistory", velocity);
        syncSpeedHistory(velocity, (ServerPlayerEntity) player);
    }

    public static void syncSpeedHistory(float speedHistory, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeFloat(speedHistory);
        ServerPlayNetworking.send(player, ModMessages.VELOCITY_ID, buffer);
    }
}