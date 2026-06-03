package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHLeggingsAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class KHLeggingsAttachment extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {
    private final Ingredient ingredient;
    boolean overlay;
    int defaultColor;

    public ArmorAttachmentRenderer cachedRenderer;

    public KHLeggingsAttachment(Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.overlay = false;
        this.defaultColor = -1;
    }

    public KHLeggingsAttachment(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.overlay = overlay;
        this.defaultColor = defaultColor;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }

    @Override
    public ArmorItem.@NotNull Type getArmorSlot() {
        return ArmorItem.Type.LEGGINGS;
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHLeggingsAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    public int getDefaultColor() {
        return this.defaultColor;
    }
}
