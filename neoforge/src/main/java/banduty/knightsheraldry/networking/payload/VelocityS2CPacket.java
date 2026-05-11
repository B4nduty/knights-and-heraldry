package banduty.knightsheraldry.networking.payload;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.stoneycore.util.data.entitydata.IEntityDataSaver;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record VelocityS2CPacket(float speedHistory) implements CustomPacketPayload {
    public static final Type<VelocityS2CPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "velocity_packet")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, VelocityS2CPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, VelocityS2CPacket::speedHistory,
            VelocityS2CPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();

            if (mc.player != null) {
                ((IEntityDataSaver) mc.player)
                        .stoneycore$getPersistentData()
                        .putFloat("speedHistory", speedHistory);
            }
        });
    }
}