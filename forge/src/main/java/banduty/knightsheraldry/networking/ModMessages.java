package banduty.knightsheraldry.networking;

import banduty.knightsheraldry.networking.packet.VelocityS2CPacket;
import banduty.stoneycore.StoneyCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(StoneyCore.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        CHANNEL.registerMessage(packetId++,
                VelocityS2CPacket.class,
                VelocityS2CPacket::encode,
                VelocityS2CPacket::decode,
                VelocityS2CPacket::handle
        );
    }

    public static SimpleChannel get() {
        return CHANNEL;
    }
}