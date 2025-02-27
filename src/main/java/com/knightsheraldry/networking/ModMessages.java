package com.knightsheraldry.networking;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.networking.packet.NonTickS2CPacket;
import com.knightsheraldry.networking.packet.PreviousBlockPosS2CPacket;
import com.knightsheraldry.networking.packet.VelocityS2CPacket;
import com.knightsheraldry.networking.packet.VelocityUpdateS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier VELOCITY_ID = new Identifier(KnightsHeraldry.MOD_ID, "velocity");
    public static final Identifier NON_TICK_ID = new Identifier(KnightsHeraldry.MOD_ID, "non_tick");
    public static final Identifier PREVIOUS_BLOCK_POS_ID = new Identifier(KnightsHeraldry.MOD_ID, "previous_block_pos");
    public static final Identifier VELOCITY_UPDATE_ID = new Identifier(KnightsHeraldry.MOD_ID, "velocity_update");

    public static void registerC2SPackets() {
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(VELOCITY_ID, VelocityS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(NON_TICK_ID, NonTickS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(PREVIOUS_BLOCK_POS_ID, PreviousBlockPosS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(VELOCITY_UPDATE_ID, VelocityUpdateS2CPacket::receive);
    }
}