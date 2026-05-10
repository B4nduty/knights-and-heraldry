package banduty.knightsheraldry.networking.payload;

import banduty.knightsheraldry.networking.KHPayloadsClient;
import banduty.stoneycore.util.data.entitydata.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record VelocityS2CPacket(float speed) implements CustomPacketPayload {
    public static final Type<VelocityS2CPacket> ID = new Type<>(KHPayloadsClient.VELOCITY_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, VelocityS2CPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, VelocityS2CPacket::speed,
            VelocityS2CPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void handle(VelocityS2CPacket payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            if (context.player() != null) {
                ((IEntityDataSaver) context.player())
                        .stoneycore$getPersistentData()
                        .putFloat("speedHistory", payload.speed());
            }
        });
    }
}