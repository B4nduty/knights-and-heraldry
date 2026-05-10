package banduty.knightsheraldry.items.item;

import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class TwoLayerDyeableItem extends Item {

    public TwoLayerDyeableItem(Properties properties) {
        super(properties);
    }

    public int getColor1(ItemStack stack) {
        DyedItemColor dyedItemColor = stack.get(KHDataComponents.COLOR_1.get());
        return dyedItemColor != null ? dyedItemColor.rgb() : 0xFFFFFF;
    }

    public int getColor2(ItemStack stack) {
        DyedItemColor dyedItemColor = stack.get(KHDataComponents.COLOR_2.get());
        return dyedItemColor != null ? dyedItemColor.rgb() : 0xFFFFFF;
    }

    public void setColor1(ItemStack stack, int color) {
        DyedItemColor dyedItemColor = new DyedItemColor(color, true);
        stack.set(KHDataComponents.COLOR_1.get(), dyedItemColor);
    }

    public void setColor2(ItemStack stack, int color) {
        DyedItemColor dyedItemColor = new DyedItemColor(color, true);
        stack.set(KHDataComponents.COLOR_2.get(), dyedItemColor);
    }
}