package banduty.knightsheraldry.util.playerdata;

import banduty.knightsheraldry.networking.ModMessages;
import banduty.knightsheraldry.networking.packet.VelocityS2CPacket;
import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

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
        ModMessages.get().send(
                PacketDistributor.PLAYER.with(() -> player),
                new VelocityS2CPacket(speedHistory)
        );
    }
}