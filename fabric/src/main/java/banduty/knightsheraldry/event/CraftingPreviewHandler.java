package banduty.knightsheraldry.event;

import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.stoneycore.event.custom.CraftingPreviewCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;


public class CraftingPreviewHandler implements CraftingPreviewCallback {
    @Override
    public ItemStack modifyResult(ServerPlayer player, CraftingContainer inventory, ItemStack original) {
        String itemPath = BuiltInRegistries.ITEM.getKey(original.getItem()).getPath();

        CompoundTag originalColors = backupColors(original, itemPath);

        boolean appliedNewColors = false;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack ingredient = inventory.getItem(i);

            if (ingredient.isEmpty() || ingredient.getItem() == original.getItem()) {
                continue;
            }

            if (applyDye(original, ingredient, itemPath)) {
                appliedNewColors = true;
                continue;
            }

            if (applyHelmetDeco(original, ingredient)) {
                appliedNewColors = true;
            }
        }

        if (original.getItem() instanceof TwoLayerDyeableItem && !appliedNewColors && originalColors != null) {
            if (!originalColors.isEmpty()) {
                original.getOrCreateTag().put(itemPath, originalColors);
            }
        }

        return original;
    }

    private CompoundTag backupColors(ItemStack stack, String itemPath) {
        if (!(stack.getItem() instanceof TwoLayerDyeableItem)) {
            return null;
        }

        CompoundTag nbt = stack.getTag();

        if (nbt != null && nbt.contains(itemPath)) {
            return nbt.getCompound(itemPath).copy();
        }
        return null;
    }

    private boolean applyDye(ItemStack target, ItemStack ingredient, String itemPath) {
        if (!(ingredient.getItem() instanceof DyeItem dye) || !(target.getItem() instanceof TwoLayerDyeableItem)) {
            return false;
        }

        CompoundTag nbt = target.getOrCreateTag();

        float[] colorComponents = dye.getDyeColor().getTextureDiffuseColors();

        int color = getColorFromComponents(colorComponents);

        CompoundTag colors = nbt.getCompound(itemPath);

        if (colors.isEmpty()) {
            colors = new CompoundTag();
        }

        if (!colors.contains("color1")) {
            colors.putInt("color1", color);
        } else if (!colors.contains("color2")) {
            colors.putInt("color2", color);
        } else {
            colors.putInt("color2", color);
        }

        nbt.put(itemPath, colors);

        return true;
    }

    private boolean applyHelmetDeco(ItemStack target, ItemStack ingredient) {
        HelmetDeco deco = HelmetDeco.getDecoFromItem(ingredient.getItem()).get();

        CompoundTag nbt = target.getOrCreateTag();

        HelmetDeco.getValues().stream()
                .filter(other -> other.group() == deco.group())
                .forEach(other -> nbt.remove(other.getNbtKey()));

        if (deco.color() == 2 && ingredient.getItem() instanceof TwoLayerDyeableItem twoLayer) {
            CompoundTag decoNbt = nbt.getCompound(deco.getNbtKey());
            int color1 = twoLayer.getColor1(ingredient);
            int color2 = twoLayer.getColor2(ingredient);
            decoNbt.putInt("color1", color1);
            decoNbt.putInt("color2", color2);
            nbt.put(deco.getNbtKey(), decoNbt);
            return true;
        }

        if (deco.color() == 1) {
            CompoundTag display = ingredient.getTagElement("display");
            int color = display != null && display.contains("color", 99) ? display.getInt("color") : 0xFFFFFF;
            nbt.putInt(deco.getNbtKey(), color);
        } else {
            nbt.putBoolean(deco.getNbtKey(), true);
        }

        return false;
    }

    private int getColorFromComponents(float[] components) {
        int r = Math.round(components[0] * 255);
        int g = Math.round(components[1] * 255);
        int b = Math.round(components[2] * 255);
        return (r << 16) | (g << 8) | b;
    }
}