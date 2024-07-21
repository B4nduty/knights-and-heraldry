package com.knightsheraldry.networking.packet;

import com.knightsheraldry.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class StaminaBlockedS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        if (client.player != null) {
            ((IEntityDataSaver) client.player).bsroleplay$getPersistentData().putBoolean("stamina_blocked", buf.readBoolean());
        }
    }
}