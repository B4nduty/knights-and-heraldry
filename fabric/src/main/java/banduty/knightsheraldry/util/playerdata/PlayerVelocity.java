package banduty.knightsheraldry.util.playerdata;

import banduty.knightsheraldry.networking.ModMessages;
import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class PlayerVelocity {

    public static void updateSpeedHistory(IEntityDataSaver player, float velocity) {
        CompoundTag tag = player.stoneycore$getPersistentData();

        float old = tag.getFloat("speedHistory");

        if (Math.abs(old - velocity) < 0.01f) return;

        tag.putFloat("speedHistory", velocity);

        if (player instanceof ServerPlayer serverPlayer) {
            syncSpeedHistory(serverPlayer, velocity);
        }
    }

    public static void syncSpeedHistory(ServerPlayer player, float speedHistory) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeFloat(speedHistory);

        ServerPlayNetworking.send(player, ModMessages.VELOCITY_ID, buffer);
    }
}