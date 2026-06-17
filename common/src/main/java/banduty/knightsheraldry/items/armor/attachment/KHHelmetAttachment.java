package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHHelmetAttachmentRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentPosition;
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

public class KHHelmetAttachment extends Item
        implements ArmorAttachment, ArmorAttachmentRenderProvider, ArmorAttachmentPosition {

    private final boolean openVisor;
    private final Ingredient ingredient;

    private final float offsetX;
    private final float offsetY;
    private final float offsetZ;

    public ArmorAttachmentRenderer cachedRenderer;


    public KHHelmetAttachment(Properties properties, Ingredient ingredient) {
        this(properties, false, ingredient, 0, -4, 0);
    }


    public KHHelmetAttachment(Properties properties,
                              boolean openVisor,
                              Ingredient ingredient) {
        this(properties, openVisor, ingredient, 0, -4, 0);
    }


    public KHHelmetAttachment(Properties properties,
                              boolean openVisor,
                              Ingredient ingredient,
                              float offsetX,
                              float offsetY,
                              float offsetZ) {
        super(properties);
        this.openVisor = openVisor;
        this.ingredient = ingredient;

        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }


    @Override
    public float getOffsetX() {
        return offsetX;
    }

    @Override
    public float getOffsetY() {
        return offsetY;
    }

    @Override
    public float getOffsetZ() {
        return offsetZ;
    }


    @Override
    public boolean hasOpenVisor(ItemStack stack) {
        return openVisor;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.ingredient.test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHHelmetAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ArmorAttachment.super.use(level, player, hand, ArmorItem.Type.HELMET);
    }
}
