package com.knightsheraldry.mixin;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingScreenHandler.class)
public abstract class CraftingScreenHandlerMixin {
    @Inject(
            method = "updateResult",
            at = @At(value = "TAIL")
    )
    private static void onUpdateResult(ScreenHandler handler, World world, PlayerEntity player, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory, CallbackInfo ci) {
        ItemStack craftingRecipeItem = null;
        for (int i = 0; i < craftingInventory.size(); i++) {
            craftingRecipeItem = craftingInventory.getStack(i);
            if (!craftingRecipeItem.isEmpty() && craftingRecipeItem.isOf(resultInventory.getStack(0).getItem())) break;
        }

        if (craftingRecipeItem == null || craftingRecipeItem.isEmpty()) return;

        if (craftingRecipeItem.getItem() instanceof SCAccessoryItem) {
            ItemStack modified = craftingRecipeItem.copy();
            applyPreviewModifiers(modified, craftingInventory);
            resultInventory.setStack(0, modified);
        }
    }

    @Unique
    private static void applyPreviewModifiers(ItemStack stack, RecipeInputInventory input) {
        for (int i = 0; i < input.size(); i++) {
            ItemStack ingredient = input.getStack(i);
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