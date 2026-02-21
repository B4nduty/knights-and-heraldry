package banduty.knightsheraldry.items.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TwoLayerDyeableItem extends Item {

    public TwoLayerDyeableItem(Properties properties) {
        super(properties);
    }

    public int getColor1(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("color1")) {
            return stack.getTag().getInt("color1");
        }
        return 0xFFFFFF;
    }

    public int getColor2(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("color2")) {
            return stack.getTag().getInt("color2");
        }
        return 0xFFFFFF;
    }

    public void setColor1(ItemStack stack, int color) {
        stack.getOrCreateTag().putInt("color1", color);
    }

    public void setColor2(ItemStack stack, int color) {
        stack.getOrCreateTag().putInt("color2", color);
    }
}