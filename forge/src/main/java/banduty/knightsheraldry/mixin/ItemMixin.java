package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.ModItems;
import banduty.knightsheraldry.items.item.TwoLayerDyeableItem;
import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.knightsheraldry.util.itemdata.ItemTooltipData;
import banduty.stoneycore.items.armor.SCAccessoryItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
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
    @Inject(method = "getTooltipImage", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$getTooltipData(ItemStack stack, CallbackInfoReturnable<Optional<TooltipComponent>> cir) {

        CompoundTag rootTag = stack.getTag();
        if (rootTag == null || !rootTag.contains("HelmetDeco")) return;

        CompoundTag decoRoot = rootTag.getCompound("HelmetDeco");

        List<ItemStack> itemsToShow = new ArrayList<>();

        for (HelmetDeco helmetDeco : HelmetDeco.getValues()) {

            String key = helmetDeco.getNbtKey();
            if (stack.is(helmetDeco.item()) || !decoRoot.contains(key)) continue;

            ItemStack itemStack = new ItemStack(helmetDeco.item());

            if (helmetDeco.color() == 2) {
                CompoundTag colorData = decoRoot.getCompound(key);

                itemStack.getOrCreateTag().putInt("color1", colorData.getInt("color1"));
                itemStack.getOrCreateTag().putInt("color2", colorData.getInt("color2"));
            } else if (helmetDeco.color() == 1) {
                int color = decoRoot.getInt(key);
                itemStack.getOrCreateTagElement("display").putInt("color", color);
            }

            itemsToShow.add(itemStack);
        }

        if (!itemsToShow.isEmpty()) {
            cir.setReturnValue(Optional.of(new ItemTooltipData(itemsToShow)));
        }
    }

    @Inject(method = "onCraftedBy", at = @At("TAIL"))
    public void onCraft(ItemStack stack, Level world, Player player, CallbackInfo ci) {
        if (!(stack.getItem() instanceof SCAccessoryItem)) return;

        if (player.containerMenu instanceof CraftingMenu craftingMenu) {
            knightsheraldry$applyCraftingModifiers(stack, craftingMenu.getSize(), craftingMenu::getSlot);
        } else if (player.containerMenu instanceof InventoryMenu inventoryMenu) {
            knightsheraldry$applyCraftingModifiers(stack, 4, inventoryMenu::getSlot);
        }
    }

    @Unique
    private void knightsheraldry$applyCraftingModifiers(ItemStack original, int slotCount, IntFunction<Slot> slotSupplier) {
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
            if (ingredient.getItem() == ModItems.RIM_GUARDS.get() && original.getItem() != ModItems.RIM_GUARDS.get()) {
                original.getOrCreateTag().putBoolean("rimmed", true);
                continue;
            }
            if (ingredient.getItem() == ModItems.BESAGEWS.get() && original.getItem() != ModItems.BESAGEWS.get()) {
                original.getOrCreateTag().putBoolean("besagews", true);
                continue;
            }

            Optional<HelmetDeco> deco = HelmetDeco.getDecoFromItem(ingredient.getItem());
            if (deco.isPresent()) {
                if (original.getTag() != null) {
                    for (HelmetDeco otherDeco : HelmetDeco.getValues()) {
                        if (!original.getTag().contains(otherDeco.getNbtKey())) continue;
                        if (otherDeco.group() == deco.get().group()) {
                            original.getTag().remove(otherDeco.getNbtKey());
                        }
                    }
                }

                if (deco.get().color() == 2 && ingredient.getItem() instanceof TwoLayerDyeableItem twoLayerDyeableItem) {
                    CompoundTag tag = original.getOrCreateTagElement(deco.get().getNbtKey());
                    tag.putInt("color1", twoLayerDyeableItem.getColor1(ingredient));
                    tag.putInt("color2", twoLayerDyeableItem.getColor2(ingredient));
                    appliedNewColors = true;
                    continue;
                }

                String key = deco.get().getNbtKey();
                if (deco.get().color() == 1) {
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