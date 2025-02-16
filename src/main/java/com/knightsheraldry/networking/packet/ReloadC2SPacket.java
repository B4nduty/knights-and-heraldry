package com.knightsheraldry.networking.packet;

import com.knightsheraldry.items.custom.item.KHRangeWeapon;
import com.knightsheraldry.util.weaponutil.KHRangeWeaponUtil;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ReloadC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            ItemStack itemStack = player.getMainHandStack();

            if (itemStack.getItem() instanceof KHRangeWeapon khRangeWeapons && khRangeWeapons.ammoRequirement() != null) {
                if (!KHRangeWeaponUtil.getWeaponState(itemStack).isCharged()) itemStack.getOrCreateNbt().putBoolean("kh_recharge", true);
            }
        });
    }

}