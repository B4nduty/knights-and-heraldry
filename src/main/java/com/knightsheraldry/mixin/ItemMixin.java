package com.knightsheraldry.mixin;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getName(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/text/Text;", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$getName(ItemStack stack, CallbackInfoReturnable<Text> cir) {
        if (!(stack.getItem() instanceof SCAccessoryItem)) return;

        StringBuilder translationKey = new StringBuilder(stack.getTranslationKey());
        if (stack.getOrCreateNbt().getBoolean("kh_aventail")) translationKey.append("_aventail");
        if (stack.getOrCreateNbt().getBoolean("kh_rimmed")) translationKey.append("_rimmed");
        if (stack.getOrCreateNbt().getBoolean("kh_besagews")) translationKey.append("_besagews");
        cir.setReturnValue(Text.translatable(translationKey.toString()));
    }

    @Inject(method = "onCraft", at = @At("TAIL"))
    public void onCraft(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {
        if (!(stack.getItem() instanceof SCAccessoryItem)) return;

        if (player.currentScreenHandler instanceof CraftingScreenHandler craftingInventory) {
            applyCraftingModifiers(stack, craftingInventory.getCraftingSlotCount(), craftingInventory::getSlot);
        } else if (player.currentScreenHandler instanceof PlayerScreenHandler playerInventory) {
            applyCraftingModifiers(stack, 4, playerInventory::getSlot);
        }
    }

    @Unique
    private void applyCraftingModifiers(ItemStack stack, int slotCount, java.util.function.IntFunction<Slot> slotSupplier) {
        for (int i = 0; i < slotCount; i++) {
            ItemStack ingredient = slotSupplier.apply(i).getStack();
            if (ingredient.getItem() == ModItems.AVENTAIL.get() && stack.getItem() != ModItems.AVENTAIL.get()) stack.getOrCreateNbt().putBoolean("kh_aventail", true);
            if (ingredient.getItem() == ModItems.RIM_GUARDS.get() && stack.getItem() != ModItems.RIM_GUARDS.get()) stack.getOrCreateNbt().putBoolean("kh_rimmed", true);
            if (ingredient.getItem() == ModItems.BESAGEWS.get() && stack.getItem() != ModItems.BESAGEWS.get()) stack.getOrCreateNbt().putBoolean("kh_besagews", true);
            if (ingredient.getItem() == ModItems.PLUME.get() && stack.getItem() != ModItems.PLUME.get()) {
                NbtCompound nbtCompound = ingredient.getSubNbt("display");
                int color = nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 0xFFFFFF;
                stack.getOrCreateNbt().putInt("kh_plume", color);
            }
        }
    }
}