package banduty.knightsheraldry.networking;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.networking.payload.VelocityS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.resources.ResourceLocation;

public interface KHPayloadsClient {
    ResourceLocation VELOCITY_ID = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "velocity");

    static void init() {
        PayloadTypeRegistry.playS2C().register(VelocityS2CPacket.ID, VelocityS2CPacket.CODEC);
    }

    static void registerS2CReceivers() {
        ClientPlayNetworking.registerGlobalReceiver(VelocityS2CPacket.ID, VelocityS2CPacket::handle);
    }
}