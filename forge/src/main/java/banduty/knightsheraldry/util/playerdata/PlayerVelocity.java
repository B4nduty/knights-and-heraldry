package banduty.knightsheraldry.util.playerdata;

import banduty.knightsheraldry.networking.ModMessages;
import banduty.knightsheraldry.networking.packet.VelocityS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerVelocity {
    public static void updateSpeedHistory(ServerPlayer player, float velocity) {
        CompoundTag compoundTag = player.getPersistentData();
        compoundTag.putFloat("speedHistory", velocity);
        syncSpeedHistory(velocity);
    }

    public static void syncSpeedHistory(float speedHistory) {
        ModMessages.INSTANCE.sendToServer(new VelocityS2CPacket(speedHistory));
    }
}