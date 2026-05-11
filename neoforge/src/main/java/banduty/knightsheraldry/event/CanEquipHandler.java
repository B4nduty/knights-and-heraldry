package banduty.knightsheraldry.event;

import banduty.knightsheraldry.items.KHItems;
import banduty.knightsheraldry.items.armor.accessory.KHCloseHelmet;
import banduty.knightsheraldry.items.armor.accessory.KHHelmetAccessory;
import banduty.knightsheraldry.items.armor.accessory.KHSavoyard;
import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.events.CanEquipCallback;
import io.wispforest.accessories.api.slot.SlotEntryReference;
import io.wispforest.accessories.api.slot.SlotReference;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.world.item.ItemStack;

public class CanEquipHandler implements CanEquipCallback {
    @Override
    public TriState canEquip(ItemStack itemStack, SlotReference slotReference) {
        if (itemStack.getItem() == KHItems.HELMET_HOOD.get() || itemStack.getItem() == KHItems.HELMET_TORN_HOOD.get()) {
            if (AccessoriesCapability.getOptionally(slotReference.entity()).isPresent()) {
                for (SlotEntryReference equipped : AccessoriesCapability.get(slotReference.entity()).getAllEquipped()) {
                    switch (equipped.stack().getItem()) {
                        case KHHelmetAccessory khHelmetAccessory -> {
                            return TriState.DEFAULT;
                        }
                        case KHCloseHelmet khCloseHelmet -> {
                            return TriState.DEFAULT;
                        }
                        case KHSavoyard khSavoyard -> {
                            return TriState.DEFAULT;
                        }
                        default -> {
                        }
                    }
                }
            }
            return TriState.FALSE;
        }
        return TriState.DEFAULT;
    }
}
