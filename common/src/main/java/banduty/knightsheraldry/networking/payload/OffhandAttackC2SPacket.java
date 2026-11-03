package banduty.knightsheraldry.networking.payload;

import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Sent by the client when it swings its offhand dual-wieldable weapon at an entity (vanilla combat only).
 */
public record OffhandAttackC2SPacket(int entityId, boolean sneaking) implements CustomPacketPayload {
    public static final Type<OffhandAttackC2SPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "offhand_attack")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, OffhandAttackC2SPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, OffhandAttackC2SPacket::entityId,
            ByteBufCodecs.BOOL, OffhandAttackC2SPacket::sneaking,
            OffhandAttackC2SPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
