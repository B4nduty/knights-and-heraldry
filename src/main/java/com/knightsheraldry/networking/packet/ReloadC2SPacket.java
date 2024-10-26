package com.knightsheraldry.networking.packet;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.item.KHRangeWeapons;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

            if(itemStack.getItem() instanceof KHRangeWeapons khRangeWeapons && khRangeWeapons.isOneShot()) {
                if (khRangeWeapons.isCharged(itemStack)) {
                    return;
                }
                NbtCompound nbt = itemStack.getOrCreateNbt();
                int reloadSec = nbt.getInt("ReloadSec");
                if (khRangeWeapons.isReloading(itemStack)) reloadTick++;
                if (!khRangeWeapons.isReloading(itemStack)) {
                    ItemStack blackPowder = getItemFromInventory(player, ModItems.BLACK_POWDER);
                    ItemStack ironNugget = getItemFromInventory(player, Items.IRON_NUGGET);
                    if (ironNugget == null) ironNugget = getItemFromInventory(player, Items.GRAVEL);
                    ItemStack paper = getItemFromInventory(player, Items.PAPER);
                    if (paper == null) paper = getItemFromInventory(player, Items.GRASS);
                    if (areItemsInInventory(blackPowder, ironNugget, paper)) {
                        khRangeWeapons.setShooting(itemStack, false);
                        player.getInventory().removeStack(getItemSlot(player, blackPowder), 1);
                        player.getInventory().removeStack(getItemSlot(player, ironNugget), 1);
                        player.getInventory().removeStack(getItemSlot(player, paper), 1);
                        khRangeWeapons.setReload(itemStack, true);
                        reloadTick++;
                    }
                }

                if (reloadTick % 20 == 0 && reloadTick != 0) {
                    nbt.putInt("ReloadSec", reloadSec + 1);
                    if (reloadSec < 14) player.sendMessage(Text.translatable("event.knightsheraldry.recharge_time", reloadSec + 1), true);
                }

                if (reloadSec >= 15) {
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

    private static boolean areItemsInInventory(ItemStack blackPowder, ItemStack ironNugget, ItemStack paper) {
        return blackPowder != null && ironNugget != null && paper != null;
    }
}