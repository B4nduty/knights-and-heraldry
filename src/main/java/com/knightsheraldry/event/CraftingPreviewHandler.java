package com.knightsheraldry.event;

import banduty.stoneycore.event.custom.CraftingPreviewCallback;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.item.TwoLayerDyeableItem;
import com.knightsheraldry.util.itemdata.HelmetDeco;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;

public class CraftingPreviewHandler implements CraftingPreviewCallback {
    @Override
    public ItemStack modifyResult(ServerPlayer player, CraftingContainer inventory, ItemStack original) {
        System.out.println("=== CraftingPreviewHandler START ===");
        System.out.println("Original item: " + original.getItem());

        String itemPath = BuiltInRegistries.ITEM.getKey(original.getItem()).getPath();
        System.out.println("Item path: " + itemPath);

        System.out.println("Original NBT before backup: " + original.getTag());

        CompoundTag originalColors = backupColors(original, itemPath);
        System.out.println("Backed up colors: " + originalColors);

        boolean appliedNewColors = false;
        System.out.println("Inventory size: " + inventory.getContainerSize());

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack ingredient = inventory.getItem(i);
            System.out.println("Slot " + i + " ingredient: " + ingredient.getItem() +
                    ", isEmpty: " + ingredient.isEmpty());

            if (ingredient.isEmpty() || ingredient.getItem() == original.getItem()) {
                System.out.println("  Skipping ingredient");
                continue;
            }

            if (applyDye(original, ingredient, itemPath)) {
                System.out.println("  Applied dye successfully");
                appliedNewColors = true;
                continue;
            }

            if (applyArmorUpgrade(original, ingredient)) {
                System.out.println("  Applied armor upgrade");
                continue;
            }

            if (applyHelmetDeco(original, ingredient)) {
                System.out.println("  Applied helmet decoration");
                appliedNewColors = true;
            }
        }

        System.out.println("Applied new colors: " + appliedNewColors);
        System.out.println("Original item instance of TwoLayerDyeableItem: " + (original.getItem() instanceof TwoLayerDyeableItem));

        if (original.getItem() instanceof TwoLayerDyeableItem && !appliedNewColors && originalColors != null) {
            if (!originalColors.isEmpty()) {
                System.out.println("Restoring original colors: " + originalColors);
                original.getOrCreateTag().put(itemPath, originalColors);
            } else {
                System.out.println("Original colors were empty, not restoring");
            }
        }

        System.out.println("Final NBT: " + original.getTag());
        System.out.println("=== CraftingPreviewHandler END ===");
        return original;
    }

    private CompoundTag backupColors(ItemStack stack, String itemPath) {
        System.out.println("  backupColors called for item path: " + itemPath);
        if (!(stack.getItem() instanceof TwoLayerDyeableItem)) {
            System.out.println("  Not a TwoLayerDyeableItem, returning null");
            return null;
        }

        CompoundTag nbt = stack.getTag();
        System.out.println("  Current NBT: " + nbt);

        if (nbt != null && nbt.contains(itemPath)) {
            CompoundTag colors = nbt.getCompound(itemPath).copy();
            System.out.println("  Found and copied colors: " + colors);
            return colors;
        }
        System.out.println("  No colors found to backup");
        return null;
    }

    private boolean applyDye(ItemStack target, ItemStack ingredient, String itemPath) {
        System.out.println("  applyDye called");
        System.out.println("  Ingredient is DyeItem: " + (ingredient.getItem() instanceof DyeItem));
        System.out.println("  Target is TwoLayerDyeableItem: " + (target.getItem() instanceof TwoLayerDyeableItem));

        if (!(ingredient.getItem() instanceof DyeItem dye) || !(target.getItem() instanceof TwoLayerDyeableItem)) {
            System.out.println("  Not a dye or not dyeable item, returning false");
            return false;
        }

        CompoundTag nbt = target.getOrCreateTag();
        System.out.println("  Target NBT before dyeing: " + nbt);

        float[] colorComponents = dye.getDyeColor().getTextureDiffuseColors();
        System.out.println("  Color components: R=" + colorComponents[0] + ", G=" + colorComponents[1] + ", B=" + colorComponents[2]);

        int color = getColorFromComponents(colorComponents);
        System.out.println("  Converted color (hex): 0x" + Integer.toHexString(color));

        CompoundTag colors = nbt.getCompound(itemPath);
        System.out.println("  Existing colors tag: " + colors);
        System.out.println("  Colors isEmpty: " + colors.isEmpty());
        System.out.println("  Has color1: " + colors.contains("color1"));
        System.out.println("  Has color2: " + colors.contains("color2"));

        if (colors.isEmpty()) {
            colors = new CompoundTag();
            System.out.println("  Created new colors compound");
        }

        if (!colors.contains("color1")) {
            colors.putInt("color1", color);
            System.out.println("  Setting color1 to: 0x" + Integer.toHexString(color));
        } else if (!colors.contains("color2")) {
            colors.putInt("color2", color);
            System.out.println("  Setting color2 to: 0x" + Integer.toHexString(color));
        } else {
            colors.putInt("color2", color);
            System.out.println("  Overriding color2 to: 0x" + Integer.toHexString(color));
        }

        nbt.put(itemPath, colors);
        System.out.println("  Final colors in NBT: " + colors);
        System.out.println("  Target NBT after dyeing: " + nbt);

        return true;
    }

    private boolean applyArmorUpgrade(ItemStack target, ItemStack ingredient) {
        System.out.println("  applyArmorUpgrade called");
        if (ingredient.getItem() == ModItems.RIM_GUARDS.get() && target.getItem() != ModItems.RIM_GUARDS.get()) {
            target.getOrCreateTag().putBoolean("rimmed", true);
            System.out.println("  Applied rimmed upgrade");
            return true;
        }
        if (ingredient.getItem() == ModItems.BESAGEWS.get() && target.getItem() != ModItems.BESAGEWS.get()) {
            target.getOrCreateTag().putBoolean("besagews", true);
            System.out.println("  Applied besagews upgrade");
            return true;
        }
        System.out.println("  Not an armor upgrade item");
        return false;
    }

    private boolean applyHelmetDeco(ItemStack target, ItemStack ingredient) {
        System.out.println("  applyHelmetDeco called");
        HelmetDeco deco = HelmetDeco.HELMET_DECO.get(ingredient.getItem());
        if (deco == null) {
            System.out.println("  Not a helmet decoration");
            return false;
        }

        System.out.println("  Found helmet decoration: " + deco);

        CompoundTag nbt = target.getOrCreateTag();
        System.out.println("  Target NBT before decoration: " + nbt);

        HelmetDeco.HELMET_DECO.values().stream()
                .filter(other -> other.group() == deco.group())
                .forEach(other -> {
                    System.out.println("  Removing decoration in same group: " + other.getNbtKey());
                    nbt.remove(other.getNbtKey());
                });

        if (deco.color() == 2 && ingredient.getItem() instanceof TwoLayerDyeableItem twoLayer) {
            System.out.println("  Two-layer decoration");
            CompoundTag decoNbt = nbt.getCompound(deco.getNbtKey());
            int color1 = twoLayer.getColor1(ingredient);
            int color2 = twoLayer.getColor2(ingredient);
            System.out.println("  Color1 from ingredient: 0x" + Integer.toHexString(color1));
            System.out.println("  Color2 from ingredient: 0x" + Integer.toHexString(color2));
            decoNbt.putInt("color1", color1);
            decoNbt.putInt("color2", color2);
            nbt.put(deco.getNbtKey(), decoNbt);
            System.out.println("  Applied two-layer decoration colors");
            return true;
        }

        if (deco.color() == 1) {
            System.out.println("  Single-color decoration");
            CompoundTag display = ingredient.getTagElement("display");
            int color = display != null && display.contains("color", 99) ? display.getInt("color") : 0xFFFFFF;
            System.out.println("  Color from ingredient: 0x" + Integer.toHexString(color));
            nbt.putInt(deco.getNbtKey(), color);
        } else {
            System.out.println("  Boolean decoration (no color)");
            nbt.putBoolean(deco.getNbtKey(), true);
        }

        System.out.println("  Target NBT after decoration: " + nbt);
        return true;
    }

    private int getColorFromComponents(float[] components) {
        int r = Math.round(components[0] * 255);
        int g = Math.round(components[1] * 255);
        int b = Math.round(components[2] * 255);
        int result = (r << 16) | (g << 8) | b;
        System.out.println("    getColorFromComponents: R=" + r + ", G=" + g + ", B=" + b + " (0x" + Integer.toHexString(result) + ")");
        return result;
    }
}