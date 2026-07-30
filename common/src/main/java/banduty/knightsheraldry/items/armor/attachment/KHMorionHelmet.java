package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHMorionHelmetAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHMorionHelmet extends KHHelmetAttachment {
    public KHMorionHelmet(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHMorionHelmet(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHMorionHelmetAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
