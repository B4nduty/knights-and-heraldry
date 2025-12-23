package com.knightsheraldry.event;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.armor.accessory.KHCloseHelmet;
import com.knightsheraldry.items.armor.accessory.KHHelmetAccessory;
import com.knightsheraldry.items.armor.accessory.KHSavoyard;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.events.CanEquipCallback;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.world.item.ItemStack;

public class CanEquipHandler implements CanEquipCallback {
    @Override
    public TriState canEquip(ItemStack itemStack, SlotReference slotReference) {
        if (itemStack.getItem() == ModItems.HELMET_HOOD.get() || itemStack.getItem() == ModItems.HELMET_TORN_HOOD.get()) {
            if (AccessoriesCapability.getOptionally(slotReference.entity()).isPresent()) {
                for (SlotEntryReference equipped : AccessoriesCapability.get(slotReference.entity()).getAllEquipped()) {
                    if (equipped.stack().getItem() instanceof KHHelmetAccessory) return TriState.DEFAULT;
                    if (equipped.stack().getItem() instanceof KHCloseHelmet) return TriState.DEFAULT;
                    if (equipped.stack().getItem() instanceof KHSavoyard) return TriState.DEFAULT;
                }
            }
            return TriState.FALSE;
        }
        return TriState.DEFAULT;
    }
}
