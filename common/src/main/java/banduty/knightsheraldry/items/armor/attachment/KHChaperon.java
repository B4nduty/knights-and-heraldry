package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHChaperonAttachmentRenderer;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import net.minecraft.world.item.Item;

public class KHChaperon extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {
    boolean hasOverlay;

    public ArmorAttachmentRenderer cachedRenderer;

    public KHChaperon(Properties properties, boolean hasOverlay) {
        super(properties);
        this.hasOverlay = hasOverlay;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHChaperonAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    public int getDefaultColor() {
        return 0xFFA06440;
    }
}
