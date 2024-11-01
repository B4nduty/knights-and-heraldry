package com.knightsheraldry.networking.packet;

import com.knightsheraldry.items.custom.item.KHRangeWeapons;
import com.knightsheraldry.util.KHInventoryItemFinder;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
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

            if (!(itemStack.getItem() instanceof KHRangeWeapons khRangeWeapons) || khRangeWeapons.getFirstItem() == null) {
                reloadTick = 0;
                return;
            }

            if (player.isCreative()){
                player.sendMessage(Text.translatable("event.knightsheraldry.recharged"), true);
                khRangeWeapons.setShooting(itemStack, false);
                khRangeWeapons.setReload(itemStack, false);
                khRangeWeapons.setCharged(itemStack, true);
                reloadTick = 0;
                return;
            } else if (player.getItemCooldownManager().isCoolingDown(khRangeWeapons)) return;

            if (khRangeWeapons.isCharged(itemStack)) return;

            NbtCompound nbt = itemStack.getOrCreateNbt();
            int reloadSec = nbt.getInt("ReloadSec");

            if (khRangeWeapons.isReloading(itemStack)) {
                reloadTick++;
            } else {
                ItemStack firstItem = KHInventoryItemFinder.getItemFromInventory(player, khRangeWeapons.getFirstItem(), khRangeWeapons.getFirstItem2nOption());
                ItemStack secondItem = KHInventoryItemFinder.getItemFromInventory(player, khRangeWeapons.getSecondItem(), khRangeWeapons.getSecondItem2nOption());
                ItemStack thirdItem = KHInventoryItemFinder.getItemFromInventory(player, khRangeWeapons.getThirdItem(), khRangeWeapons.getThirdItem2nOption());

                if (KHInventoryItemFinder.areItemsInInventory(new ItemStack[]{firstItem, secondItem, thirdItem},
                        new int[]{khRangeWeapons.getAmountFirstItem(), khRangeWeapons.getAmountSecondItem(), khRangeWeapons.getAmountThirdItem()})) {
                    startReload(player, khRangeWeapons, itemStack, firstItem, secondItem, thirdItem);
                }
            }

            handleReloadProgress(player, khRangeWeapons, itemStack, reloadSec);
        });
    }

    private static void startReload(ServerPlayerEntity player, KHRangeWeapons khRangeWeapons, ItemStack itemStack,
                                    ItemStack firstItem, ItemStack secondItem, ItemStack thirdItem) {
        khRangeWeapons.setShooting(itemStack, false);
        player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, firstItem), 1);
        player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, secondItem), 1);
        player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, thirdItem), 1);
        khRangeWeapons.setReload(itemStack, true);
        reloadTick++;
    }

    private static void handleReloadProgress(ServerPlayerEntity player, KHRangeWeapons khRangeWeapons, ItemStack itemStack, int reloadSec) {
        NbtCompound nbt = itemStack.getOrCreateNbt();

        if (reloadTick % 20 == 0 && reloadTick != 0) {
            nbt.putInt("ReloadSec", reloadSec + 1);
            if (reloadSec < khRangeWeapons.getRechargeTime() - 1) {
                player.sendMessage(Text.translatable("event.knightsheraldry.recharge_time", reloadSec + 1), true);
            }
        }

        if (reloadSec >= khRangeWeapons.getRechargeTime()) {
            completeReload(player, khRangeWeapons, itemStack, nbt);
        }
    }

    private static void completeReload(ServerPlayerEntity player, KHRangeWeapons khRangeWeapons, ItemStack itemStack, NbtCompound nbt) {
        player.sendMessage(Text.translatable("event.knightsheraldry.recharged"), true);
        khRangeWeapons.setReload(itemStack, false);
        khRangeWeapons.setCharged(itemStack, true);
        nbt.putInt("ReloadSec", 0);
        reloadTick = 0;
    }
}