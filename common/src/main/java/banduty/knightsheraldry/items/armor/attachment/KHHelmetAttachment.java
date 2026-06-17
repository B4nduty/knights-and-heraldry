package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHHelmetAttachmentRenderer;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.client.render.ArmorAttachmentPosition;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import banduty.stoneycore.items.custom.armor.custom.CrownItem;
import banduty.stoneycore.items.custom.armor.underarmor.UnderArmorContents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class KHHelmetAttachment extends Item
        implements ArmorAttachment, ArmorAttachmentRenderProvider, ArmorAttachmentPosition {

    private final boolean openVisor;
    private final Ingredient ingredient;

    private final Vector3f offset;
    private final Vector3f rotation;

    public ArmorAttachmentRenderer cachedRenderer;


    public KHHelmetAttachment(Properties properties, Ingredient ingredient) {
        this(properties, false, ingredient, new Vector3f(0, -4, 0), new Vector3f(0, 0, 0));
    }


    public KHHelmetAttachment(Properties properties,
                              boolean openVisor,
                              Ingredient ingredient) {
        this(properties, openVisor, ingredient, new Vector3f(0, -4, 0), new Vector3f(0, 0, 0));
    }


    public KHHelmetAttachment(Properties properties,
                              boolean openVisor,
                              Ingredient ingredient,
                              Vector3f offset,
                              Vector3f rotation) {
        super(properties);
        this.openVisor = openVisor;
        this.ingredient = ingredient;

        this.offset = offset;
        this.rotation = rotation;
    }

    @Override
    public Vector3f getOffset(ItemStack stack, LivingEntity entity, UnderArmorContents contents) {
        if (stack.getItem() instanceof CrownItem || stack.is(KHItems.TORSE.get())) return offset;
        return ArmorAttachmentPosition.super.getOffset(stack, entity, contents);
    }

    @Override
    public Vector3f getRotation(ItemStack stack, LivingEntity entity, UnderArmorContents contents) {
        if (stack.getItem() instanceof CrownItem || stack.is(KHItems.TORSE.get())) return rotation;
        return ArmorAttachmentPosition.super.getOffset(stack, entity, contents);
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
