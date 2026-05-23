package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHChestplateAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class KHChestplateAttachment extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {
    private final Ingredient ingredient;
    private final int numberSlot;
    boolean hasOverlay;
    int defaultColor;

    public ArmorAttachmentRenderer cachedRenderer;

    public KHChestplateAttachment(Properties properties, Ingredient ingredient, int numberSlot) {
        super(properties);
        this.ingredient = ingredient;
        this.numberSlot = numberSlot;
        this.hasOverlay = false;
        this.defaultColor = -1;
    }

    public KHChestplateAttachment(Properties properties, int defaultColor, Ingredient ingredient, int numberSlot) {
        super(properties);
        this.ingredient = ingredient;
        this.numberSlot = numberSlot;
        this.hasOverlay = false;
        this.defaultColor = defaultColor;
    }

    public KHChestplateAttachment(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient, int numberSlot) {
        super(properties);
        this.ingredient = ingredient;
        this.numberSlot = numberSlot;
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
    public ArmorItem.@NotNull Type getArmorSlot() {
        return ArmorItem.Type.CHESTPLATE;
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHChestplateAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    @Override
    public @Range(from = 0L, to = 2147483647L) int numberSlot() {
        return numberSlot;
    }
}
