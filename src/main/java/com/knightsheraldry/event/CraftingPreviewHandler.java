package com.knightsheraldry.event;

import banduty.stoneycore.event.custom.CraftingPreviewCallback;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.item.TwoLayerDyeableItem;
import com.knightsheraldry.util.itemdata.HelmetDeco;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;

public class CraftingPreviewHandler implements CraftingPreviewCallback {
    @Override
    public ItemStack modifyResult(ServerPlayerEntity player, RecipeInputInventory inventory, ItemStack original) {
        String itemPath = Registries.ITEM.getId(original.getItem()).getPath();

        NbtCompound originalColors = backupColors(original, itemPath);

        boolean appliedNewColors = false;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack ingredient = inventory.getStack(i);
            if (ingredient.isEmpty() || ingredient.getItem() == original.getItem()) continue;

            if (applyDye(original, ingredient, itemPath)) {
                appliedNewColors = true;
                continue;
            }

            if (applyArmorUpgrade(original, ingredient)) continue;

            if (applyHelmetDeco(original, ingredient)) {
                appliedNewColors = true;
            }
        }

        if (original.getItem() instanceof TwoLayerDyeableItem && !appliedNewColors && originalColors != null) {
            original.getOrCreateNbt().put(itemPath, originalColors);
        }
        return original;
    }

    private NbtCompound backupColors(ItemStack stack, String itemPath) {
        if (!(stack.getItem() instanceof TwoLayerDyeableItem)) return null;
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains(itemPath)) {
            NbtCompound saved = nbt.getCompound(itemPath);
            nbt.remove(itemPath);
            return saved;
        }
        return null;
    }

    private boolean applyDye(ItemStack target, ItemStack ingredient, String itemPath) {
        if (!(ingredient.getItem() instanceof DyeItem dye) || !(target.getItem() instanceof TwoLayerDyeableItem)) return false;

        NbtCompound nbt = target.getOrCreateNbt();
        int color = getColorFromComponents(dye.getColor().getColorComponents());
        NbtCompound colors = nbt.getCompound(itemPath);

        if (!colors.contains("color1")) {
            colors.putInt("color1", color);
        } else if (!colors.contains("color2")) {
            colors.putInt("color2", color);
        }
        nbt.put(itemPath, colors);
        return true;
    }

    private boolean applyArmorUpgrade(ItemStack target, ItemStack ingredient) {
        if (ingredient.getItem() == ModItems.RIM_GUARDS.get() && target.getItem() != ModItems.RIM_GUARDS.get()) {
            target.getOrCreateNbt().putBoolean("rimmed", true);
            return true;
        }
        if (ingredient.getItem() == ModItems.BESAGEWS.get() && target.getItem() != ModItems.BESAGEWS.get()) {
            target.getOrCreateNbt().putBoolean("besagews", true);
            return true;
        }
        return false;
    }

    private boolean applyHelmetDeco(ItemStack target, ItemStack ingredient) {
        HelmetDeco deco = HelmetDeco.HELMET_DECO.get(ingredient.getItem());
        if (deco == null) return false;

        NbtCompound nbt = target.getOrCreateNbt();
        HelmetDeco.HELMET_DECO.values().stream()
                .filter(other -> other.group() == deco.group())
                .forEach(other -> nbt.remove(other.getNbtKey()));

        if (deco.color() == 2 && ingredient.getItem() instanceof TwoLayerDyeableItem twoLayer) {
            NbtCompound decoNbt = nbt.getCompound(deco.getNbtKey());
            decoNbt.putInt("color1", twoLayer.getColor1(ingredient));
            decoNbt.putInt("color2", twoLayer.getColor2(ingredient));
            nbt.put(deco.getNbtKey(), decoNbt);
            return true;
        }

        if (deco.color() == 1) {
            NbtCompound display = ingredient.getSubNbt("display");
            int color = display != null && display.contains("color", 99) ? display.getInt("color") : 0xFFFFFF;
            nbt.putInt(deco.getNbtKey(), color);
        } else {
            nbt.putBoolean(deco.getNbtKey(), true);
        }
        return true;
    }

    private int getColorFromComponents(float[] components) {
        int r = Math.round(components[0] * 255);
        int g = Math.round(components[1] * 255);
        int b = Math.round(components[2] * 255);
        return (r << 16) | (g << 8) | b;
    }
}