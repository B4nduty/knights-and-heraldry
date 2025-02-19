package com.knightsheraldry.event;

import com.knightsheraldry.items.item.KHRangeWeapon;
import com.knightsheraldry.util.KHInventoryItemFinder;
import com.knightsheraldry.util.playerdata.IEntityDataSaver;
import com.knightsheraldry.util.weaponutil.KHRangeWeaponUtil;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class ReloadTickHandler implements ServerTickEvents.StartTick {
    private ItemStack lastItemStack;

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            handlePlayerReload(player);
        }
    }

    private void handlePlayerReload(ServerPlayerEntity player) {
        ItemStack currentItem = player.getMainHandStack();

        if (currentItem != lastItemStack) {
            if (lastItemStack != null) {
                if (lastItemStack.getItem() instanceof KHRangeWeapon) {
                    lastItemStack.getOrCreateNbt().putBoolean("kh_recharge", false);
                    resetWeaponState(player, lastItemStack);
                }
            }
            lastItemStack = currentItem;
            return;
        }

        if (!isReloading(currentItem)) {
            resetRechargeTime(player);
            return;
        }

        if (!isValidRangeWeapon(currentItem)) {
            resetRechargeTime(player);
            return;
        }

        if (player.isCreative()) {
            completeReload(player, currentItem);
            return;
        }

        Item weapon = currentItem.getItem();
        if (player.getItemCooldownManager().isCoolingDown(weapon)) {
            return;
        }

        if (KHRangeWeaponUtil.getWeaponState(currentItem).isCharged()) {
            return;
        }

        if (KHRangeWeaponUtil.getWeaponState(currentItem).isReloading()) {
            incrementRechargeTime(player);
        } else if (hasRequiredAmmo(player, (KHRangeWeapon) weapon)) {
            startReload(player, currentItem);
        } else {
            currentItem.getOrCreateNbt().putBoolean("kh_recharge", false);
        }

        if (getRechargeTime(player) >= ((KHRangeWeapon) weapon).rechargeTime() * 20) {
            completeReload(player, currentItem);
        }
    }

    private void resetWeaponState(ServerPlayerEntity player, ItemStack itemStack) {
        KHRangeWeaponUtil.setWeaponState(itemStack, new KHRangeWeaponUtil.WeaponState(false, KHRangeWeaponUtil.getWeaponState(itemStack).isCharged(), false));
        setRechargeTime(player, 0);
    }

    private boolean isReloading(ItemStack itemStack) {
        return itemStack.getOrCreateNbt().getBoolean("kh_recharge");
    }

    private boolean isValidRangeWeapon(ItemStack itemStack) {
        return itemStack.getItem() instanceof KHRangeWeapon && ((KHRangeWeapon) itemStack.getItem()).ammoRequirement() != null;
    }

    private boolean hasRequiredAmmo(ServerPlayerEntity player, KHRangeWeapon weapon) {
        ItemStack[] requiredItems = {
                KHInventoryItemFinder.getItemFromInventory(player, weapon.ammoRequirement().firstItem(), weapon.ammoRequirement().firstItem2nOption()),
                KHInventoryItemFinder.getItemFromInventory(player, weapon.ammoRequirement().secondItem(), weapon.ammoRequirement().secondItem2nOption()),
                KHInventoryItemFinder.getItemFromInventory(player, weapon.ammoRequirement().thirdItem(), weapon.ammoRequirement().thirdItem2nOption())
        };

        int[] requiredAmounts = {
                weapon.ammoRequirement().amountFirstItem(),
                weapon.ammoRequirement().amountSecondItem(),
                weapon.ammoRequirement().amountThirdItem()
        };

        return KHInventoryItemFinder.areItemsInInventory(requiredItems, requiredAmounts);
    }

    private void startReload(ServerPlayerEntity player, ItemStack itemStack) {
        KHRangeWeaponUtil.setWeaponState(itemStack, new KHRangeWeaponUtil.WeaponState(true, KHRangeWeaponUtil.getWeaponState(itemStack).isCharged(), false));
        incrementRechargeTime(player);
    }

    private void completeReload(ServerPlayerEntity player, ItemStack itemStack) {
        if (!player.isCreative()) {
            KHRangeWeapon weapon = ((KHRangeWeapon) itemStack.getItem());
            ItemStack[] ammoItems = {
                    KHInventoryItemFinder.getItemFromInventory(player, weapon.ammoRequirement().firstItem(), weapon.ammoRequirement().firstItem2nOption()),
                    KHInventoryItemFinder.getItemFromInventory(player, weapon.ammoRequirement().secondItem(), weapon.ammoRequirement().secondItem2nOption()),
                    KHInventoryItemFinder.getItemFromInventory(player, weapon.ammoRequirement().thirdItem(), weapon.ammoRequirement().thirdItem2nOption())
            };

            for (ItemStack ammoItem : ammoItems) {
                player.getInventory().removeStack(KHInventoryItemFinder.getItemSlot(player, ammoItem), 1);
            }
        }

        KHRangeWeaponUtil.setWeaponState(itemStack, new KHRangeWeaponUtil.WeaponState(false, true, false));
        setRechargeTime(player, 0);
        itemStack.getOrCreateNbt().putBoolean("kh_recharge", false);
    }

    private void resetRechargeTime(PlayerEntity player) {
        setRechargeTime(player, 0);
    }

    private void incrementRechargeTime(PlayerEntity player) {
        setRechargeTime(player, getRechargeTime(player) + 1);
    }

    private int getRechargeTime(PlayerEntity player) {
        return ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getInt("rechargeTime");
    }

    private void setRechargeTime(PlayerEntity player, int time) {
        ((IEntityDataSaver) player).knightsheraldry$getPersistentData().putInt("rechargeTime", time);
    }
}