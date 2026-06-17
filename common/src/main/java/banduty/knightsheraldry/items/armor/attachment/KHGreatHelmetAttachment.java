package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHGreatHelmetAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;
import org.joml.Vector3f;

public class KHGreatHelmetAttachment extends KHHelmetAttachment {
    public KHGreatHelmetAttachment(Properties properties, Ingredient ingredient) {
        super(properties, false, ingredient, new Vector3f(0, -5, 0), new Vector3f(0, 0, 0));
    }

    public KHGreatHelmetAttachment(Properties properties, Ingredient ingredient,
                                   Vector3f offset,
                                   Vector3f rotation) {
        super(properties, false, ingredient, offset, rotation);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHGreatHelmetAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}
