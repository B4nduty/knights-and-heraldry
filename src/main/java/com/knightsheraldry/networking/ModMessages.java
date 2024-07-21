package com.knightsheraldry.networking;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.networking.packet.AttackC2SPacket;
import com.knightsheraldry.networking.packet.StaminaIntS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier STAMINA_INT_ID = new Identifier(KnightsHeraldry.MOD_ID, "stamina_int");
    public static final Identifier ATTACK_ID = new Identifier(KnightsHeraldry.MOD_ID, "attack");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(ATTACK_ID, AttackC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(STAMINA_INT_ID, StaminaIntS2CPacket::receive);
    }
}
