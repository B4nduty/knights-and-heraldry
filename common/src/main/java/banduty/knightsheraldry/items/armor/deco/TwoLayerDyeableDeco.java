package banduty.knightsheraldry.items.armor.deco;

import banduty.knightsheraldry.client.item.deco.TwoLayerDyeableDecoRenderer;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class TwoLayerDyeableDeco extends DecoItem {

    public ArmorAttachmentRenderer cachedRenderer;

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

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new TwoLayerDyeableDecoRenderer();
        }
        return this.cachedRenderer;
    }
}