package com.knightsheraldry.util.playerdata;

import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import com.knightsheraldry.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class PlayerVelocity {
    public static void updateSpeedHistory(IEntityDataSaver player, float velocity) {
        CompoundTag compoundTag = player.stoneycore$getPersistentData();
        compoundTag.putFloat("speedHistory", velocity);
        syncSpeedHistory(velocity, (ServerPlayer) player);
    }

    public static void syncSpeedHistory(float speedHistory, ServerPlayer player) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeFloat(speedHistory);
        ServerPlayNetworking.send(player, ModMessages.VELOCITY_ID, buffer);
    }
}