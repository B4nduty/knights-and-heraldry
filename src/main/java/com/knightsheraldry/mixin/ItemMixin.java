package com.knightsheraldry.mixin;

import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.item.TwoLayerDyeableItem;
import com.knightsheraldry.util.itemdata.HelmetDeco;
import com.knightsheraldry.util.itemdata.ItemTooltipData;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getName(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/text/Text;", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$getName(ItemStack stack, CallbackInfoReturnable<Text> cir) {
        if (!(stack.getItem() instanceof SCAccessoryItem)) return;

        StringBuilder translationKey = new StringBuilder(stack.getTranslationKey());
        if (stack.getOrCreateNbt().getBoolean("rimmed")) translationKey.append("_rimmed");
        if (stack.getOrCreateNbt().getBoolean("besagews")) translationKey.append("_besagews");
        cir.setReturnValue(Text.translatable(translationKey.toString()));
    }

    @Inject(method = "getTooltipData", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$getTooltipData(ItemStack stack, CallbackInfoReturnable<Optional<TooltipData>> cir) {
        List<ItemStack> itemsToShow = new ArrayList<>();
        for (HelmetDeco helmetDeco : HelmetDeco.HELMET_DECO.values()) {
            NbtCompound nbt = stack.getNbt();
            String key = helmetDeco.getNbtKey();
            if (stack.isOf(helmetDeco.item()) || nbt == null || !nbt.contains(key)) continue;
            ItemStack itemStack = new ItemStack(helmetDeco.item());

            if (helmetDeco.color() == 2) {
                if (nbt.getCompound(key).contains("color1")) {
                    itemStack.getOrCreateSubNbt(key).putInt("color1", nbt.getCompound(key).getInt("color1"));
                } else itemStack.getOrCreateSubNbt(key).putInt("color1", -1);
                if (nbt.getCompound(key).contains("color2")) {
                    itemStack.getOrCreateSubNbt(key).putInt("color2", nbt.getCompound(key).getInt("color2"));
                } else itemStack.getOrCreateSubNbt(key).putInt("color2", -1);
            }

            if (helmetDeco.color() == 1) {
                itemStack.getOrCreateSubNbt("display").putInt("color", nbt.getInt(key));
            }

            itemsToShow.add(itemStack);
        }
        if (!itemsToShow.isEmpty()) cir.setReturnValue(Optional.of(new ItemTooltipData(itemsToShow)));
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
    private void applyCraftingModifiers(ItemStack original, int slotCount, java.util.function.IntFunction<Slot> slotSupplier) {
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

        for (int i = 0; i < slotCount; i++) {
            ItemStack ingredient = slotSupplier.apply(i).getStack();
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
                    NbtCompound nbt = original.getOrCreateSubNbt(deco.getNbtKey());
                    nbt.putInt("color1", twoLayerDyeableItem.getColor1(ingredient));
                    nbt.putInt("color2", twoLayerDyeableItem.getColor2(ingredient));
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
    }

    @Unique
    private int getColorFromComponents(float[] components) {
        int r = Math.round(components[0] * 255);
        int g = Math.round(components[1] * 255);
        int b = Math.round(components[2] * 255);
        return (r << 16) | (g << 8) | b;
    }
}