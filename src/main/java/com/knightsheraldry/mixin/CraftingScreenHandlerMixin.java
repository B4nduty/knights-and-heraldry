package com.knightsheraldry.mixin;

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
        ItemStack result = resultInventory.getStack(0);

        ItemStack modified = result.copy();
        applyPreviewModifiers(modified, craftingInventory);
        resultInventory.setStack(0, modified);
    }

    @Unique
    private static void applyPreviewModifiers(ItemStack stack, RecipeInputInventory input) {
        for (int i = 0; i < input.size(); i++) {
            ItemStack ingredient = input.getStack(i);
            if (ingredient.getItem() == ModItems.AVENTAIL && stack.getItem() != ModItems.AVENTAIL) stack.getOrCreateNbt().putBoolean("kh_aventail", true);
            if (ingredient.getItem() == ModItems.RIM_GUARDS && stack.getItem() != ModItems.RIM_GUARDS) stack.getOrCreateNbt().putBoolean("kh_rimmed", true);
            if (ingredient.getItem() == ModItems.BESAGEWS && stack.getItem() != ModItems.BESAGEWS) stack.getOrCreateNbt().putBoolean("kh_besagews", true);
            if (ingredient.getItem() == ModItems.PLUME && stack.getItem() != ModItems.PLUME) {
                NbtCompound nbtCompound = ingredient.getSubNbt("display");
                int color = nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 0xFFFFFF;
                stack.getOrCreateNbt().putInt("kh_plume", color);
            }
        }
    }
}