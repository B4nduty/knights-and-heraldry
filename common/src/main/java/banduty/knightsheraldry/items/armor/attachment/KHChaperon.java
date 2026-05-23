package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHChaperonAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

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
    public ArmorItem.@NotNull Type getArmorSlot() {
        return ArmorItem.Type.HELMET;
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
