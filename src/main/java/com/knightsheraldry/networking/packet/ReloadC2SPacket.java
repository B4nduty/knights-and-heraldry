package com.knightsheraldry.networking.packet;

import com.knightsheraldry.items.custom.item.KHRangeWeapons;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ReloadC2SPacket {
    private static int reloadTick;
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            ItemStack itemStack = player.getMainHandStack();

            if(itemStack.getItem() instanceof KHRangeWeapons khRangeWeapons && khRangeWeapons.getFirstItem() != null) {
                if (khRangeWeapons.isCharged(itemStack)) {
                    return;
                }
                NbtCompound nbt = itemStack.getOrCreateNbt();
                int reloadSec = nbt.getInt("ReloadSec");
                if (khRangeWeapons.isReloading(itemStack)) reloadTick++;
                if (!khRangeWeapons.isReloading(itemStack)) {
                    ItemStack firstItem = getItemFromInventory(player, khRangeWeapons.getFirstItem());
                    if (firstItem == null) firstItem = getItemFromInventory(player, khRangeWeapons.getFirstItem2nOption());
                    ItemStack secondItem = getItemFromInventory(player, khRangeWeapons.getSecondItem());
                    if (secondItem == null) secondItem = getItemFromInventory(player, khRangeWeapons.getSecondItem2nOption());
                    ItemStack thirdItem = getItemFromInventory(player, khRangeWeapons.getThirdItem());
                    if (thirdItem == null) thirdItem = getItemFromInventory(player, khRangeWeapons.getThirdItem2nOption());
                    if (areItemsInInventory(firstItem, secondItem, thirdItem)) {
                        khRangeWeapons.setShooting(itemStack, false);
                        player.getInventory().removeStack(getItemSlot(player, firstItem), 1);
                        player.getInventory().removeStack(getItemSlot(player, secondItem), 1);
                        player.getInventory().removeStack(getItemSlot(player, thirdItem), 1);
                        khRangeWeapons.setReload(itemStack, true);
                        reloadTick++;
                    }
                }

                if (reloadTick % 20 == 0 && reloadTick != 0) {
                    nbt.putInt("ReloadSec", reloadSec + 1);
                    if (reloadSec < khRangeWeapons.getRechargeTime() - 1) player.sendMessage(Text.translatable("event.knightsheraldry.recharge_time", reloadSec + 1), true);
                }

                if (reloadSec >= khRangeWeapons.getRechargeTime()) {
                    player.sendMessage(Text.translatable("event.knightsheraldry.recharged"), true);
                    khRangeWeapons.setReload(itemStack, false);
                    khRangeWeapons.setCharged(itemStack, true);
                    nbt.putInt("ReloadSec", 0);
                    reloadTick = 0;
                }
            } else {
                reloadTick = 0;
            }
        });
    }

    private static ItemStack getItemFromInventory(ServerPlayerEntity player, Item item) {
        return player.getInventory().main.stream()
                .filter(stack -> stack.getItem() == item)
                .findFirst()
                .orElse(null);
    }

    private static int getItemSlot(ServerPlayerEntity player, ItemStack itemStack) {
        return player.getInventory().main.stream()
                .filter(stack -> stack == itemStack)
                .map(player.getInventory().main::indexOf)
                .findFirst()
                .orElse(-1);
    }

    private static boolean areItemsInInventory(ItemStack firstItem, ItemStack secondItem, ItemStack thirdItem) {
        return firstItem != null && secondItem != null && thirdItem != null;
    }
}