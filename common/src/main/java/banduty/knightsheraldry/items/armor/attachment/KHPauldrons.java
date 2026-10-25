package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHPauldronsAttachmentRenderer;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHPauldrons extends KHChestplateAttachment {

    private final boolean destroysOnQuench;

    // Default: true
    public KHPauldrons(Properties properties, Ingredient ingredient) {
        this(properties, ingredient, false);
    }

    // Explicit destroysOnQuench
    public KHPauldrons(
            Properties properties,
            Ingredient ingredient,
            boolean destroysOnQuench
    ) {
        super(properties, ingredient);
        this.destroysOnQuench = destroysOnQuench;
    }

    // Default: true
    public KHPauldrons(
            Properties properties,
            int defaultColor,
            Ingredient ingredient
    ) {
        this(properties, defaultColor, ingredient, true);
    }

    // Explicit destroysOnQuench
    public KHPauldrons(
            Properties properties,
            int defaultColor,
            Ingredient ingredient,
            boolean destroysOnQuench
    ) {
        super(properties, defaultColor, ingredient);
        this.destroysOnQuench = destroysOnQuench;
    }

    // Default: true
    public KHPauldrons(
            Properties properties,
            boolean overlay,
            int defaultColor,
            Ingredient ingredient
    ) {
        this(properties, overlay, defaultColor, ingredient, true);
    }

    // Explicit destroysOnQuench
    public KHPauldrons(
            Properties properties,
            boolean overlay,
            int defaultColor,
            Ingredient ingredient,
            boolean destroysOnQuench
    ) {
        super(properties, overlay, defaultColor, ingredient);
        this.destroysOnQuench = destroysOnQuench;
    }

    @Override
    public boolean destroysOnQuench() {
        return destroysOnQuench;
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHPauldronsAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}