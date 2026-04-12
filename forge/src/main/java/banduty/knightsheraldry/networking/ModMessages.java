package banduty.knightsheraldry.networking;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.networking.packet.VelocityS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static final String PROTOCOL_VERSION = "1";

    public static SimpleChannel INSTANCE;

    private static int packetId = 0;

    public static void register() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(KnightsHeraldry.MOD_ID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        packetId = 0;

        INSTANCE.registerMessage(packetId++,
                VelocityS2CPacket.class,
                VelocityS2CPacket::encode,
                VelocityS2CPacket::decode,
                VelocityS2CPacket::handle
        );
    }

    public static SimpleChannel get() {
        if (INSTANCE == null) {
            throw new IllegalStateException("ModMessages not initialized! Did you forget to call register() in commonSetup?");
        }
        return INSTANCE;
    }
}