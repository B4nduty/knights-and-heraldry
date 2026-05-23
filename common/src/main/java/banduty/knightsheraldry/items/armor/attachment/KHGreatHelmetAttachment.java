package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHGreatHelmetAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHGreatHelmetAttachment extends KHHelmetAttachment {
    public KHGreatHelmetAttachment(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHGreatHelmetAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
