package banduty.knightsheraldry.networking.payload;

import banduty.knightsheraldry.KnightsHeraldry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Sent by the client when the grip key is pressed, to switch the main-hand versatile weapon between
 * its one-handed and two-handed grip.
 */
public record ToggleGripC2SPacket() implements CustomPacketPayload {
    public static final ToggleGripC2SPacket INSTANCE = new ToggleGripC2SPacket();

    public static final Type<ToggleGripC2SPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "toggle_grip")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleGripC2SPacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
