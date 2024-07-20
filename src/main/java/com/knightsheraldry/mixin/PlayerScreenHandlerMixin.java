package com.knightsheraldry.mixin;

import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.custom.WarSword;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerScreenHandler.class)
public class PlayerScreenHandlerMixin {
    @Unique
    private int currentVariant;

    @Inject(method = "quickMove", at = @At("HEAD"), cancellable = true)
    private void onQuickMoveStart(PlayerEntity player, int slot, CallbackInfoReturnable<ItemStack> cir) {
        PlayerScreenHandler screenHandler = (PlayerScreenHandler) (Object) this;
        Slot slot2 = screenHandler.slots.get(slot);

        if (slot2 != null && slot2.hasStack() && slot2.getStack().getItem() == ModItems.WARSWORD) {
            ItemStack itemStackBefore = slot2.getStack().copy();

            for (int i = PlayerScreenHandler.CRAFTING_INPUT_START; i < PlayerScreenHandler.CRAFTING_INPUT_END; i++) {
                Slot craftingSlot = screenHandler.slots.get(i);
                if (craftingSlot != null && craftingSlot.hasStack()) {
                    ItemStack stackInCraftingSlot = craftingSlot.getStack();
                    if (stackInCraftingSlot.getItem() == ModItems.WARSWORD) {
                        currentVariant = stackInCraftingSlot.getOrCreateNbt().getInt("CustomModelData");
                        break;
                    }
                }
            }
            ItemStack newStack = itemStackBefore.copy();

            if (!newStack.isEmpty() && newStack.getItem() instanceof WarSword) {
                if (!itemStackBefore.isEmpty() && itemStackBefore.getItem() instanceof WarSword) {
                    int newVariant = (currentVariant + 1) % 4;
                    newStack.getOrCreateNbt().putInt("CustomModelData", newVariant);

                    if (!insertItem(screenHandler, newStack, slot)) {
                        cir.setReturnValue(ItemStack.EMPTY);
                        return;
                    }

                    for (int i = PlayerScreenHandler.CRAFTING_INPUT_START; i < PlayerScreenHandler.CRAFTING_INPUT_END; i++) {
                        Slot craftingSlot = screenHandler.slots.get(i);
                        if (craftingSlot != null && craftingSlot.hasStack() && craftingSlot.getStack().getItem() == ModItems.WARSWORD) {
                            ItemStack stackInCraftingSlot = craftingSlot.getStack();
                            stackInCraftingSlot.decrement(1);
                            if (stackInCraftingSlot.isEmpty()) {
                                craftingSlot.setStack(ItemStack.EMPTY);
                            } else {
                                craftingSlot.markDirty();
                            }
                            break;
                        }
                    }

                    cir.setReturnValue(newStack);
                }
            }
        }
    }

    @Unique
    private boolean insertItem(ScreenHandler screenHandler, ItemStack stack, int slot) {
        ScreenHandlerAccessor accessor = (ScreenHandlerAccessor) screenHandler;
        if (slot == 0) {
            return accessor.invokeInsertItem(stack, 9, 45, true);
        } else if (slot >= 1 && slot < 5) {
            return accessor.invokeInsertItem(stack, 9, 45, false);
        } else if (slot >= 5 && slot < 9) {
            return accessor.invokeInsertItem(stack, 9, 45, false);
        } else if (slot >= 9 && slot < 36) {
            return accessor.invokeInsertItem(stack, 36, 45, false);
        } else if (slot >= 36 && slot < 45) {
            return accessor.invokeInsertItem(stack, 9, 36, false);
        } else {
            return accessor.invokeInsertItem(stack, 9, 45, false);
        }
    }
}