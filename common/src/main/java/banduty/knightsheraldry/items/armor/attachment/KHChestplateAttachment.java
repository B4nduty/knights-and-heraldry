package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHChestplateAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class KHChestplateAttachment extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {
    Ingredient ingredient;
    boolean hasOverlay;
    int defaultColor;

    public ArmorAttachmentRenderer cachedRenderer;

    public KHChestplateAttachment(Properties properties, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.hasOverlay = false;
        this.defaultColor = -1;
    }

    public KHChestplateAttachment(Properties properties, int defaultColor, Ingredient ingredient) {
        super(properties);
        this.ingredient = ingredient;
        this.hasOverlay = false;
        this.defaultColor = defaultColor;
    }

    public KHChestplateAttachment(Properties properties, boolean overlay, int defaultColor, Ingredient ingredient) {
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
            this.cachedRenderer = new KHChestplateAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ArmorAttachment.super.use(level, player, hand, ArmorItem.Type.CHESTPLATE);
    }
}
