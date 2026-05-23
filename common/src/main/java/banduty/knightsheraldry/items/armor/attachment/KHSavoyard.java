package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHSavoyardAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHSavoyard extends KHHelmetAttachment {
    public KHSavoyard(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHSavoyard(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHSavoyardAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
