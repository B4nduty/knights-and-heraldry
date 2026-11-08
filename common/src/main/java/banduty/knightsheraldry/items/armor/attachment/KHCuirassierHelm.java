package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHCuirassierHelmAttachmentRenderer;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHCuirassierHelm extends KHHelmetAttachment {
    public KHCuirassierHelm(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHCuirassierHelm(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties, openVisor, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHCuirassierHelmAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
