package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHBlackSalletHelmetAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHBlackSalletHelmet extends KHHelmetAttachment {
    public KHBlackSalletHelmet(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHBlackSalletHelmet(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHBlackSalletHelmetAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
