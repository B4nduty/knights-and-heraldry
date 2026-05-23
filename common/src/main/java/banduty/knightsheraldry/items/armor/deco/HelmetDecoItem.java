package banduty.knightsheraldry.items.armor.deco;

import banduty.knightsheraldry.client.item.deco.HelmetDecoRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.Item;

public class HelmetDecoItem extends Item implements ArmorAttachmentRenderProvider {
    public HelmetDecoItem(Properties properties) {
        super(properties);
    }

    public ArmorAttachmentRenderer cachedRenderer;

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new HelmetDecoRenderer();
        }
        return this.cachedRenderer;
    }
}