package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.util.itemdata.HelmetDeco;
import banduty.knightsheraldry.util.itemdata.ItemTooltipData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getTooltipImage", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$getTooltipData(ItemStack stack, CallbackInfoReturnable<Optional<TooltipComponent>> cir) {
        List<ItemStack> itemsToShow = new ArrayList<>();
        for (HelmetDeco helmetDeco : HelmetDeco.getValues()) {
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
}