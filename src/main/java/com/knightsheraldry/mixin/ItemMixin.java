package com.knightsheraldry.mixin;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.item.TwoLayerDyeableItem;
import com.knightsheraldry.util.itemdata.HelmetDeco;
import com.knightsheraldry.util.itemdata.ItemTooltipData;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getName", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$getName(ItemStack stack, CallbackInfoReturnable<Component> cir) {
        if (!(stack.getItem() instanceof SCAccessoryItem)) return;

        StringBuilder translationKey = new StringBuilder(stack.getDescriptionId());
        if (stack.getOrCreateTag().getBoolean("rimmed")) translationKey.append("_rimmed");
        if (stack.getOrCreateTag().getBoolean("besagews")) translationKey.append("_besagews");
        cir.setReturnValue(Component.translatable(translationKey.toString()));
    }

    @Inject(method = "getTooltipImage", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$getTooltipData(ItemStack stack, CallbackInfoReturnable<Optional<TooltipComponent>> cir) {
        List<ItemStack> itemsToShow = new ArrayList<>();
        for (HelmetDeco helmetDeco : HelmetDeco.HELMET_DECO.values()) {
            CompoundTag compoundTag = stack.getTag();
            String key = helmetDeco.getNbtKey();
            if (stack.is(helmetDeco.item()) || compoundTag == null || !compoundTag.contains(key)) continue;
            ItemStack itemStack = new ItemStack(helmetDeco.item());

            if (helmetDeco.color() == 2) {
                if (compoundTag.getCompound(key).contains("color1")) {
                    itemStack.getOrCreateTagElement(key).putInt("color1", compoundTag.getCompound(key).getInt("color1"));
                } else itemStack.getOrCreateTagElement(key).putInt("color1", -1);
                if (compoundTag.getCompound(key).contains("color2")) {
                    itemStack.getOrCreateTagElement(key).putInt("color2", compoundTag.getCompound(key).getInt("color2"));
                } else itemStack.getOrCreateTagElement(key).putInt("color2", -1);
            }

            if (helmetDeco.color() == 1) {
                itemStack.getOrCreateTagElement("display").putInt("color", compoundTag.getInt(key));
            }

            itemsToShow.add(itemStack);
        }
        if (!itemsToShow.isEmpty()) cir.setReturnValue(Optional.of(new ItemTooltipData(itemsToShow)));
    }

    @Inject(method = "onCraftedBy", at = @At("TAIL"))
    public void onCraft(ItemStack stack, Level world, Player player, CallbackInfo ci) {
        if (!(stack.getItem() instanceof SCAccessoryItem)) return;

        if (player.containerMenu instanceof CraftingMenu craftingMenu) {
            applyCraftingModifiers(stack, craftingMenu.getSize(), craftingMenu::getSlot);
        } else if (player.containerMenu instanceof InventoryMenu inventoryMenu) {
            applyCraftingModifiers(stack, 4, inventoryMenu::getSlot);
        }
    }

    @Unique
    private void applyCraftingModifiers(ItemStack original, int slotCount, IntFunction<Slot> slotSupplier) {
        CompoundTag originalColors = null;
        String itemPath = BuiltInRegistries.ITEM.getKey(original.getItem()).getPath();

        if (original.getItem() instanceof TwoLayerDyeableItem) {
            CompoundTag tag = original.getTag();
            if (tag != null && tag.contains(itemPath)) {
                originalColors = tag.getCompound(itemPath);
                tag.remove(itemPath);
            }
        }

        boolean appliedNewColors = false;

        for (int i = 0; i < slotCount; i++) {
            ItemStack ingredient = slotSupplier.apply(i).getItem();
            if (ingredient.getItem() == original.getItem()) continue;
            if (ingredient.getItem() instanceof DyeItem dye && original.getItem() instanceof TwoLayerDyeableItem) {
                CompoundTag nbt = original.getOrCreateTag();
                int color = getColorFromComponents(dye.getDyeColor().getTextureDiffuseColors());
                CompoundTag colors = nbt.getCompound(itemPath);
                if (!colors.contains("color1")) {
                    colors.putInt("color1", color);
                } else if (!colors.contains("color2")) {
                    colors.putInt("color2", color);
                }
                nbt.put(itemPath, colors);
                appliedNewColors = true;
                continue;
            }
            if (ingredient.getItem() == ModItems.RIM_GUARDS.get() && original.getItem() != ModItems.RIM_GUARDS.get()) { original.getOrCreateTag().putBoolean("rimmed", true); continue; }
            if (ingredient.getItem() == ModItems.BESAGEWS.get() && original.getItem() != ModItems.BESAGEWS.get()) { original.getOrCreateTag().putBoolean("besagews", true); continue; }

            HelmetDeco deco = HelmetDeco.HELMET_DECO.get(ingredient.getItem());
            if (deco != null) {
                if (original.getTag() != null) {
                    for (HelmetDeco otherDeco : HelmetDeco.HELMET_DECO.values()) {
                        if (!original.getTag().contains(otherDeco.getNbtKey())) continue;
                        if (otherDeco.group() == deco.group()) {
                            original.getTag().remove(otherDeco.getNbtKey());
                        }
                    }
                }

                if (deco.color() == 2 && ingredient.getItem() instanceof TwoLayerDyeableItem twoLayerDyeableItem) {
                    CompoundTag tag = original.getOrCreateTagElement(deco.getNbtKey());
                    tag.putInt("color1", twoLayerDyeableItem.getColor1(ingredient));
                    tag.putInt("color2", twoLayerDyeableItem.getColor2(ingredient));
                    appliedNewColors = true;
                    continue;
                }

                String key = deco.getNbtKey();
                if (deco.color() == 1) {
                    CompoundTag display = ingredient.getTagElement("display");
                    int color = display != null && display.contains("color", 99) ? display.getInt("color") : 0xFFFFFF;
                    original.getOrCreateTag().putInt(key, color);
                } else {
                    original.getOrCreateTag().putBoolean(key, true);
                }
            }
        }

        if (original.getItem() instanceof TwoLayerDyeableItem && !appliedNewColors && originalColors != null) {
            original.getOrCreateTag().put(itemPath, originalColors);
        }
    }

    @Unique
    private int getColorFromComponents(float[] components) {
        int r = Math.round(components[0] * 255);
        int g = Math.round(components[1] * 255);
        int b = Math.round(components[2] * 255);
        return (r << 16) | (g << 8) | b;
    }
}