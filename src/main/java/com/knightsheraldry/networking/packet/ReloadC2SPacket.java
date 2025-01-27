package com.knightsheraldry.networking.packet;

import com.knightsheraldry.items.custom.item.KHRangeWeapon;
import com.knightsheraldry.util.KHInventoryItemFinder;
import com.knightsheraldry.util.weaponutil.KHRangeWeaponUtil;
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

            if (!(itemStack.getItem() instanceof KHRangeWeapon khRangeWeapons) || khRangeWeapons.ammoRequirement() == null) {
                reloadTick = 0;
                return;
            }

            if (player.isCreative()){
                player.sendMessage(Text.translatable("event.knightsheraldry.recharged"), true);
                KHRangeWeaponUtil.setWeaponState(itemStack, new KHRangeWeaponUtil.WeaponState(false, true, false));
                reloadTick = 0;
                return;
            } else if (player.getItemCooldownManager().isCoolingDown((Item) khRangeWeapons)) return;

            if (KHRangeWeaponUtil.getWeaponState(itemStack).isCharged()) return;

            NbtCompound nbt = itemStack.getOrCreateNbt();
            int reloadSec = nbt.getInt("ReloadSec");

            if (KHRangeWeaponUtil.getWeaponState(itemStack).isReloading()) {
                reloadTick++;
            } else {
                ItemStack firstItem = KHInventoryItemFinder.getItemFromInventory(
                        player, khRangeWeapons.ammoRequirement().firstItem(),
                        khRangeWeapons.ammoRequirement().firstItem2nOption()
                );
                ItemStack secondItem = KHInventoryItemFinder.getItemFromInventory(
                        player, khRangeWeapons.ammoRequirement().secondItem(),
                        khRangeWeapons.ammoRequirement().secondItem2nOption()
                );
                ItemStack thirdItem = KHInventoryItemFinder.getItemFromInventory(player,
                        khRangeWeapons.ammoRequirement().thirdItem(),
                        khRangeWeapons.ammoRequirement().thirdItem2nOption()
                );

                if (KHInventoryItemFinder.areItemsInInventory(new ItemStack[]{firstItem, secondItem, thirdItem},
                        new int[]{
                                khRangeWeapons.ammoRequirement().amountFirstItem(),
                                khRangeWeapons.ammoRequirement().amountSecondItem(),
                                khRangeWeapons.ammoRequirement().amountThirdItem()})) {
                    startReload(player, khRangeWeapons, itemStack, firstItem, secondItem, thirdItem);
                }
            }

            handleReloadProgress(player, khRangeWeapons, itemStack, reloadSec);
        });
    }

    private static void startReload(ServerPlayerEntity player, KHRangeWeapon khRangeWeapons, ItemStack itemStack,
                                    ItemStack firstItem, ItemStack secondItem, ItemStack thirdItem) {
        player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, firstItem), 1);
        player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, secondItem), 1);
        player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, thirdItem), 1);
        KHRangeWeaponUtil.setWeaponState(itemStack, new KHRangeWeaponUtil.WeaponState(true, KHRangeWeaponUtil.getWeaponState(itemStack).isCharged(), false));
        reloadTick++;
    }

    private static void handleReloadProgress(ServerPlayerEntity player, KHRangeWeapon khRangeWeapons, ItemStack itemStack, int reloadSec) {
        NbtCompound nbt = itemStack.getOrCreateNbt();

        if (reloadTick % 20 == 0 && reloadTick != 0) {
            nbt.putInt("ReloadSec", reloadSec + 1);
            if (reloadSec < khRangeWeapons.rechargeTime() - 1) {
                player.sendMessage(Text.translatable("event.knightsheraldry.recharge_time", reloadSec + 1), true);
            }
        }

        if (reloadSec >= khRangeWeapons.rechargeTime()) {
            completeReload(player, khRangeWeapons, itemStack, nbt);
        }
    }

    private static void completeReload(ServerPlayerEntity player, KHRangeWeapon khRangeWeapons, ItemStack itemStack, NbtCompound nbt) {
        player.sendMessage(Text.translatable("event.knightsheraldry.recharged"), true);
        KHRangeWeaponUtil.setWeaponState(itemStack, new KHRangeWeaponUtil.WeaponState(false, true, KHRangeWeaponUtil.getWeaponState(itemStack).isShooting()));
        nbt.putInt("ReloadSec", 0);
        reloadTick = 0;
    }
}