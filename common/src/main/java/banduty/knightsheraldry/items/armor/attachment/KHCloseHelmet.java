package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHCloseHelmetAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHCloseHelmet extends KHHelmetAttachment {
    public KHCloseHelmet(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHCloseHelmet(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHCloseHelmetAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
