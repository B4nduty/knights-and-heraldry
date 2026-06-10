package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHSalletHelmetAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHSalletHelmet extends KHHelmetAttachment {
    public KHSalletHelmet(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHSalletHelmet(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHSalletHelmetAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
