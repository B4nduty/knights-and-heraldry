package banduty.knightsheraldry.util.playerdata;

import banduty.knightsheraldry.platform.Services;
import banduty.stoneycore.util.data.entitydata.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerVelocity {

    public static void updateSpeedHistory(IEntityDataSaver player, float velocity) {
        CompoundTag tag = player.stoneycore$getPersistentData();

        float old = tag.getFloat("speedHistory");

        if (Math.abs(old - velocity) < 0.01f) return;

        tag.putFloat("speedHistory", velocity);

        if (player instanceof ServerPlayer serverPlayer) {
            Services.PLATFORM.syncSpeedHistory(serverPlayer, velocity);
        }
    }
}