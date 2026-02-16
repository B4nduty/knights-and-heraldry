package banduty.knightsheraldry.networking;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.networking.packet.VelocityS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public interface ModMessages {
    ResourceLocation VELOCITY_ID = new ResourceLocation(KnightsHeraldry.MOD_ID, "velocity");

    static void registerC2SPackets() {
    }

    static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(VELOCITY_ID, VelocityS2CPacket::receive);
    }
}