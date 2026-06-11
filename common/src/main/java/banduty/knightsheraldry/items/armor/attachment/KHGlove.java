package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHGloveAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHGlove extends KHChestplateAttachment {
    public KHGlove(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHGlove(Properties properties, int defaultColor, Ingredient ingredient) {
        super(properties, defaultColor, ingredient);
    }

    public KHGlove(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties, overlay, defaultColor, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHGloveAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
