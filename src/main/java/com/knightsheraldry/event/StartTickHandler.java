package com.knightsheraldry.event;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.armor.accessory.KHCloseHelmet;
import com.knightsheraldry.items.armor.accessory.KHHelmetAccessory;
import com.knightsheraldry.items.armor.accessory.KHSavoyard;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class StartTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayer playerEntity : server.getPlayerList().getPlayers()) {
            startArmorCheck(playerEntity);
        }
    }

    private void startArmorCheck(ServerPlayer serverPlayer) {
        boolean hasHelmet = false;
        ItemStack itemStack = null;
        if (AccessoriesCapability.getOptionally(serverPlayer).isPresent()) {
            for (SlotEntryReference equipped : AccessoriesCapability.get(serverPlayer).getAllEquipped()) {
                ItemStack accessoryStack = equipped.stack();
                if (accessoryStack.getItem() == ModItems.HELMET_HOOD.get() || accessoryStack.getItem() == ModItems.HELMET_TORN_HOOD.get()) {
                    itemStack = accessoryStack;
                }

                if ((accessoryStack.getItem() instanceof KHHelmetAccessory ||
                        accessoryStack.getItem() instanceof KHCloseHelmet ||
                        accessoryStack.getItem() instanceof KHSavoyard) &&
                                !hasHelmet) {
                    hasHelmet = true;
                }
            }

            if (itemStack != null && !hasHelmet) {
                serverPlayer.addItem(itemStack);
                itemStack.setCount(0);
            }
        }
    }
}