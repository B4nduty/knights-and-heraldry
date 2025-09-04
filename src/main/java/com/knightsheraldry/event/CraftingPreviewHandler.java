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
        NbtCompound originalColors = null;
        String itemPath = Registries.ITEM.getId(original.getItem()).getPath();

        if (original.getItem() instanceof TwoLayerDyeableItem) {
            NbtCompound nbt = original.getNbt();
            if (nbt != null && nbt.contains(itemPath)) {
                originalColors = nbt.getCompound(itemPath);
                nbt.remove(itemPath);
            }
        }

        boolean appliedNewColors = false;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack ingredient = inventory.getStack(i);
            if (ingredient.getItem() == original.getItem()) continue;
            if (ingredient.getItem() instanceof DyeItem dye && original.getItem() instanceof TwoLayerDyeableItem) {
                NbtCompound nbt = original.getOrCreateNbt();
                int color = getColorFromComponents(dye.getColor().getColorComponents());
                NbtCompound colors = nbt.getCompound(itemPath);
                if (!colors.contains("color1")) {
                    colors.putInt("color1", color);
                } else if (!colors.contains("color2")) {
                    colors.putInt("color2", color);
                }
                nbt.put(itemPath, colors);
                appliedNewColors = true;
                continue;
            }
            if (ingredient.getItem() == ModItems.AVENTAIL.get() && original.getItem() != ModItems.AVENTAIL.get()) { original.getOrCreateNbt().putBoolean("aventail", true); continue; }
            if (ingredient.getItem() == ModItems.RIM_GUARDS.get() && original.getItem() != ModItems.RIM_GUARDS.get()) { original.getOrCreateNbt().putBoolean("rimmed", true); continue; }
            if (ingredient.getItem() == ModItems.BESAGEWS.get() && original.getItem() != ModItems.BESAGEWS.get()) { original.getOrCreateNbt().putBoolean("besagews", true); continue; }

            HelmetDeco deco = HelmetDeco.HELMET_DECO.get(ingredient.getItem());
            if (deco != null) {
                if (original.getNbt() != null) {
                    for (HelmetDeco otherDeco : HelmetDeco.HELMET_DECO.values()) {
                        if (!original.getNbt().contains(otherDeco.getNbtKey())) continue;
                        if (otherDeco.group() == deco.group()) {
                            original.getNbt().remove(otherDeco.getNbtKey());
                        }
                    }
                }

                if (deco.color() == 2 && ingredient.getItem() instanceof TwoLayerDyeableItem twoLayerDyeableItem) {
                    NbtCompound nbt = original.getOrCreateNbt();
                    NbtCompound colors = new NbtCompound();
                    colors.putInt("color1", twoLayerDyeableItem.getColor1(ingredient));
                    colors.putInt("color2", twoLayerDyeableItem.getColor2(ingredient));
                    nbt.put(deco.getNbtKey(), colors);
                    appliedNewColors = true;
                    continue;
                }

                String key = deco.getNbtKey();
                if (deco.color() == 1) {
                    NbtCompound display = ingredient.getSubNbt("display");
                    int color = display != null && display.contains("color", 99) ? display.getInt("color") : 0xFFFFFF;
                    original.getOrCreateNbt().putInt(key, color);
                } else {
                    original.getOrCreateNbt().putBoolean(key, true);
                }
            }
        }
        
        if (original.getItem() instanceof TwoLayerDyeableItem && !appliedNewColors && originalColors != null) {
            original.getOrCreateNbt().put(itemPath, originalColors);
        }

        return original;
    }

    private int getColorFromComponents(float[] components) {
        int r = Math.round(components[0] * 255);
        int g = Math.round(components[1] * 255);
        int b = Math.round(components[2] * 255);
        return (r << 16) | (g << 8) | b;
    }
}
