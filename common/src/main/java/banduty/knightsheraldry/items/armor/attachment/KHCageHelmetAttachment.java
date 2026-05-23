package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHCageHelmetAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHCageHelmetAttachment extends KHHelmetAttachment {
    public KHCageHelmetAttachment(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHCageHelmetAttachment(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHCageHelmetAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
