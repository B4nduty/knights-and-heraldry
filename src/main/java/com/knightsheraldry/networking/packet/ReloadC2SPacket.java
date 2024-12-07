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

            if (!(itemStack.getItem() instanceof KHRangeWeapons khRangeWeapons) || khRangeWeapons.config.ammoRequirement() == null) {
                reloadTick = 0;
                return;
            }

            if (player.isCreative()){
                player.sendMessage(Text.translatable("event.knightsheraldry.recharged"), true);
                khRangeWeapons.setWeaponState(itemStack, new KHRangeWeapons.WeaponState(false, true, false));
                reloadTick = 0;
                return;
            } else if (player.getItemCooldownManager().isCoolingDown(khRangeWeapons)) return;

            if (khRangeWeapons.getWeaponState(itemStack).isCharged()) return;

            NbtCompound nbt = itemStack.getOrCreateNbt();
            int reloadSec = nbt.getInt("ReloadSec");

            if (khRangeWeapons.getWeaponState(itemStack).isReloading()) {
                reloadTick++;
            } else {
                ItemStack firstItem = KHInventoryItemFinder.getItemFromInventory(
                        player, khRangeWeapons.config.ammoRequirement().firstItem(),
                        khRangeWeapons.config.ammoRequirement().firstItem2nOption()
                );
                ItemStack secondItem = KHInventoryItemFinder.getItemFromInventory(
                        player, khRangeWeapons.config.ammoRequirement().secondItem(),
                        khRangeWeapons.config.ammoRequirement().secondItem2nOption()
                );
                ItemStack thirdItem = KHInventoryItemFinder.getItemFromInventory(player,
                        khRangeWeapons.config.ammoRequirement().thirdItem(),
                        khRangeWeapons.config.ammoRequirement().thirdItem2nOption()
                );

                if (KHInventoryItemFinder.areItemsInInventory(new ItemStack[]{firstItem, secondItem, thirdItem},
                        new int[]{
                                khRangeWeapons.config.ammoRequirement().amountFirstItem(),
                                khRangeWeapons.config.ammoRequirement().amountSecondItem(),
                                khRangeWeapons.config.ammoRequirement().amountThirdItem()})) {
                    startReload(player, khRangeWeapons, itemStack, firstItem, secondItem, thirdItem);
                }
            }

            handleReloadProgress(player, khRangeWeapons, itemStack, reloadSec);
        });
    }

    private static void startReload(ServerPlayerEntity player, KHRangeWeapons khRangeWeapons, ItemStack itemStack,
                                    ItemStack firstItem, ItemStack secondItem, ItemStack thirdItem) {
        player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, firstItem), 1);
        player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, secondItem), 1);
        player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, thirdItem), 1);
        khRangeWeapons.setWeaponState(itemStack, new KHRangeWeapons.WeaponState(true, khRangeWeapons.getWeaponState(itemStack).isCharged(), false));
        reloadTick++;
    }

    private static void handleReloadProgress(ServerPlayerEntity player, KHRangeWeapons khRangeWeapons, ItemStack itemStack, int reloadSec) {
        NbtCompound nbt = itemStack.getOrCreateNbt();

        if (reloadTick % 20 == 0 && reloadTick != 0) {
            nbt.putInt("ReloadSec", reloadSec + 1);
            if (reloadSec < khRangeWeapons.config.rechargeTime() - 1) {
                player.sendMessage(Text.translatable("event.knightsheraldry.recharge_time", reloadSec + 1), true);
            }
        }

        if (reloadSec >= khRangeWeapons.config.rechargeTime()) {
            completeReload(player, khRangeWeapons, itemStack, nbt);
        }
    }

    private static void completeReload(ServerPlayerEntity player, KHRangeWeapons khRangeWeapons, ItemStack itemStack, NbtCompound nbt) {
        player.sendMessage(Text.translatable("event.knightsheraldry.recharged"), true);
        khRangeWeapons.setWeaponState(itemStack, new KHRangeWeapons.WeaponState(false, true, khRangeWeapons.getWeaponState(itemStack).isShooting()));
        nbt.putInt("ReloadSec", 0);
        reloadTick = 0;
    }
}