package banduty.knightsheraldry.items.item;

import banduty.knightsheraldry.client.item.deco.TwoLayerDyeableDecoRenderer;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class TwoLayerDyeableDeco extends Item implements AccessoryRenderProvider {

    public AccessoryRenderer cachedRenderer;

    public TwoLayerDyeableDeco(Properties properties) {
        super(properties);
    }

    public static int getColor1(ItemStack stack) {
        DyedItemColor dyedItemColor = stack.get(KHDataComponents.COLOR_1.get());
        return dyedItemColor != null ? dyedItemColor.rgb() : -1;
    }

    public static int getColor2(ItemStack stack) {
        DyedItemColor dyedItemColor = stack.get(KHDataComponents.COLOR_2.get());
        return dyedItemColor != null ? dyedItemColor.rgb() : -1;
    }

    public void setColor1(ItemStack stack, int color) {
        DyedItemColor dyedItemColor = new DyedItemColor(color, true);
        stack.set(KHDataComponents.COLOR_1.get(), dyedItemColor);
    }

    public void setColor2(ItemStack stack, int color) {
        DyedItemColor dyedItemColor = new DyedItemColor(color, true);
        stack.set(KHDataComponents.COLOR_2.get(), dyedItemColor);
    }

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new TwoLayerDyeableDecoRenderer();
        }
        return this.cachedRenderer;
    }
}