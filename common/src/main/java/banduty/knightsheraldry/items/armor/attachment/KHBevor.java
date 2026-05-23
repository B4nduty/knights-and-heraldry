package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHBevorAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Range;

public class KHBevor extends KHHelmetAttachment {
    public KHBevor(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHBevorAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    @Override
    public @Range(from = 0L, to = 2147483647L) int numberSlot() {
        return 1;
    }
}
