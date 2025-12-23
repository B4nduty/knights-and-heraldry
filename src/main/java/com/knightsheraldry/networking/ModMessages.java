package com.knightsheraldry.networking;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.networking.packet.VelocityS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class ModMessages {
    public static final ResourceLocation VELOCITY_ID = new ResourceLocation(KnightsHeraldry.MOD_ID, "velocity");

    public static void registerC2SPackets() {
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(VELOCITY_ID, VelocityS2CPacket::receive);
    }
}