package banduty.knightsheraldry.items.armor.deco;

import banduty.knightsheraldry.client.item.deco.HelmetDecoRenderer;
import banduty.stoneycore.client.render.AccessoryRenderProvider;
import banduty.stoneycore.client.render.AccessoryRenderer;
import net.minecraft.world.item.Item;

public class HelmetDecoItem extends Item implements AccessoryRenderProvider {
    public HelmetDecoItem(Properties properties) {
        super(properties);
    }

    public AccessoryRenderer cachedRenderer;

    @Override
    public AccessoryRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new HelmetDecoRenderer();
        }
        return this.cachedRenderer;
    }
}