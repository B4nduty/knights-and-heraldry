package banduty.knightsheraldry.networking.packet;

import banduty.stoneycore.util.data.playerdata.IEntityDataSaver;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record VelocityS2CPacket(float speedHistory) {

    public static void handle(VelocityS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> {
                Minecraft mc = Minecraft.getInstance();

                if (mc.player != null) {
                    ((IEntityDataSaver) mc.player)
                            .stoneycore$getPersistentData()
                            .putFloat("speedHistory", msg.speedHistory);
                }
            });
        }

        context.setPacketHandled(true);
    }

    public static VelocityS2CPacket decode(FriendlyByteBuf buf) {
        return new VelocityS2CPacket(buf.readFloat());
    }

    public static void encode(VelocityS2CPacket msg, FriendlyByteBuf buf) {
        buf.writeFloat(msg.speedHistory);
    }
}