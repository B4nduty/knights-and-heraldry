package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHGloveAttachmentRenderer;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderer;
import net.minecraft.world.item.crafting.Ingredient;

public class KHGlove extends KHChestplateAttachment {

    private final boolean canBeIgnited;

    // Default: true
    public KHGlove(Properties properties, Ingredient ingredient) {
        this(properties, ingredient, true);
    }

    // Explicit canBeIgnited
    public KHGlove(
            Properties properties,
            Ingredient ingredient,
            boolean canBeIgnited
    ) {
        super(properties, ingredient);
        this.canBeIgnited = canBeIgnited;
    }

    // Default: true
    public KHGlove(
            Properties properties,
            int defaultColor,
            Ingredient ingredient
    ) {
        this(properties, defaultColor, ingredient, true);
    }

    // Explicit canBeIgnited
    public KHGlove(
            Properties properties,
            int defaultColor,
            Ingredient ingredient,
            boolean canBeIgnited
    ) {
        super(properties, defaultColor, ingredient);
        this.canBeIgnited = canBeIgnited;
    }

    // Default: true
    public KHGlove(
            Properties properties,
            boolean overlay,
            int defaultColor,
            Ingredient ingredient
    ) {
        this(properties, overlay, defaultColor, ingredient, true);
    }

    // Explicit canBeIgnited
    public KHGlove(
            Properties properties,
            boolean overlay,
            int defaultColor,
            Ingredient ingredient,
            boolean canBeIgnited
    ) {
        super(properties, overlay, defaultColor, ingredient);
        this.canBeIgnited = canBeIgnited;
    }

    @Override
    public boolean canBeIgnited() {
        return canBeIgnited;
    }

    @Override
    public boolean destroysOnQuench() {
        return !canBeIgnited;
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHGloveAttachmentRenderer();
        }
        return this.cachedRenderer;
    }
}