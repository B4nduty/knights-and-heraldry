package banduty.knightsheraldry.items.armor.attachment;

import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderer;
import banduty.stoneycore.items.client.SCBannersRendererProvider;
import net.minecraft.world.item.crafting.Ingredient;

public class KHSurcoatWBannerAttachment extends KHSurcoatAttachment implements SCBannersRendererProvider {
    public ArmorAttachmentRenderer cachedRenderer;

    public KHSurcoatWBannerAttachment(Properties properties, Ingredient ingredient) {
        super(properties, ingredient);
    }

    public KHSurcoatWBannerAttachment(Properties properties, int defaultColor, Ingredient ingredient) {
        super(properties, defaultColor, ingredient);
    }

    public KHSurcoatWBannerAttachment(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties, overlay, defaultColor, ingredient);
    }
}
