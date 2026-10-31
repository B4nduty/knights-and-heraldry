package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHPauldronsAttachmentRenderer;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KHPauldrons extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {
    Ingredient ingredient;
    boolean hasOverlay;
    int defaultColor;

    public ArmorAttachmentRenderer cachedRenderer;

    public KHPauldrons(Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.hasOverlay = false;
        this.defaultColor = -1;
    }

    public KHPauldrons(Properties properties, int defaultColor, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.hasOverlay = false;
        this.defaultColor = defaultColor;
    }

    public KHPauldrons(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.hasOverlay = overlay;
        this.defaultColor = defaultColor;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHPauldronsAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }
}