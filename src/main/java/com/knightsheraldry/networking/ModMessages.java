package com.knightsheraldry.networking;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.networking.packet.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier STAMINA_INT_ID = new Identifier(KnightsHeraldry.MOD_ID, "stamina_int");
    public static final Identifier STAMINA_BLOCKED_ID = new Identifier(KnightsHeraldry.MOD_ID, "stamina_blocked");
    public static final Identifier VELOCITY_ID = new Identifier(KnightsHeraldry.MOD_ID, "velocity");
    public static final Identifier NON_TICK_ID = new Identifier(KnightsHeraldry.MOD_ID, "non_tick");
    public static final Identifier PREVIOUS_BLOCK_POS_ID = new Identifier(KnightsHeraldry.MOD_ID, "previous_block_pos");
    public static final Identifier ATTACK_ID = new Identifier(KnightsHeraldry.MOD_ID, "attack");
    public static final Identifier VELOCITY_UPDATE_ID = new Identifier(KnightsHeraldry.MOD_ID, "velocity_update");
    public static final Identifier RELOAD_PACKET_ID = new Identifier(KnightsHeraldry.MOD_ID, "reload_packet");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(ATTACK_ID, AttackC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(RELOAD_PACKET_ID, ReloadC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(STAMINA_INT_ID, StaminaIntS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(STAMINA_BLOCKED_ID, StaminaBlockedS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(VELOCITY_ID, VelocityS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(NON_TICK_ID, NonTickS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(PREVIOUS_BLOCK_POS_ID, PreviousBlockPosS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(VELOCITY_UPDATE_ID, VelocityUpdateS2CPacket::receive);
    }
}