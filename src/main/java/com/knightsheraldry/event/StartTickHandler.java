package com.knightsheraldry.event;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.armor.accessory.KHHelmetAccessory;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class StartTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
            startArmorCheck(playerEntity);
        }
    }

    private void startArmorCheck(ServerPlayerEntity serverPlayerEntity) {
        boolean hasHelmet = false;
        ItemStack itemStack = null;
        if (AccessoriesCapability.getOptionally(serverPlayerEntity).isPresent()) {
            for (SlotEntryReference equipped : AccessoriesCapability.get(serverPlayerEntity).getAllEquipped()) {
                ItemStack accessoryStack = equipped.stack();
                if (accessoryStack.getItem() == ModItems.HELMET_HOOD.get() || accessoryStack.getItem() == ModItems.HELMET_TORN_HOOD.get()) {
                    itemStack = accessoryStack;
                }

                if (accessoryStack.getItem() instanceof KHHelmetAccessory && !hasHelmet) {
                    hasHelmet = true;
                }
            }

            if (itemStack != null && !hasHelmet) {
                serverPlayerEntity.giveItemStack(itemStack);
                itemStack.setCount(0);
            }
        }
    }
}