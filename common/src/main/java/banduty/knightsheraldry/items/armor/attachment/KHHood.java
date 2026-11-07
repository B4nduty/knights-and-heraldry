package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.client.item.armor.KHHoodAttachmentRenderer;
import banduty.knightsheraldry.items.KHArmorMaterials;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.client.render.armor.ArmorAttachmentPosition;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import banduty.stoneycore.items.custom.armor.CrownItem;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.items.custom.armor.underarmor.UnderArmorContents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

import java.util.List;

import static banduty.stoneycore.util.SCInventoryItemFinder.findUnderArmor;

/**
 * A hood that works like StoneyCore's crown: with nothing else on the head it is worn straight in the helmet slot,
 * and with under-armor on the head it attaches to it instead. Attached over a helmet it switches to its
 * over-the-helmet model ({@code helmet_<name>} texture).
 */
public class KHHood extends ArmorItem implements ArmorAttachment, ArmorAttachmentRenderProvider, ArmorAttachmentPosition {
    private static final Vector3f OFFSET = new Vector3f(0f, -4f, 0f);
    private static final Vector3f HELMET_OFFSET = new Vector3f(0f, -6f, 0f);
    public static final int DEFAULT_COLOR = 0xFFA06440;

    private ArmorAttachmentRenderer cachedRenderer;

    public KHHood(Properties properties) {
        super(KHArmorMaterials.HOOD, Type.HELMET, properties);
    }

    public static boolean hasHelmet(List<ItemStack> attachments) {
        for (ItemStack attachment : attachments) {
            if (attachment.getItem() instanceof KHHelmetAttachment) return true;
        }
        return false;
    }

    public static boolean isHood(ItemStack stack) {
        return stack.getItem() instanceof KHHood;
    }

    /** This hood's texture, using the over-the-helmet variant when it is attached over a helmet. */
    public static ResourceLocation getTexture(ItemStack stack, boolean overHelmet) {
        String path = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();
        return ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID,
                "textures/entity/attachment/" + (overHelmet ? "helmet_" + path : path) + ".png");
    }

    public int getDefaultColor() {
        return DEFAULT_COLOR;
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHHoodAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    @Override
    public Vector3f getOffset(ItemStack stack, LivingEntity entity, UnderArmorContents contents) {
        if (stack.getItem() instanceof CrownItem || stack.is(KHItems.TORSE.get()))
            return hasHelmet(contents.attachments()) ? HELMET_OFFSET : OFFSET;
        return ArmorAttachmentPosition.super.getOffset(stack, entity, contents);
    }

    @Override
    public int getPriority(ItemStack stack, LivingEntity entity, UnderArmorContents contents) {
        if (stack.getItem() instanceof CrownItem || stack.is(KHItems.TORSE.get())) return 1;
        return ArmorAttachmentPosition.super.getPriority(stack, entity, contents);
    }

    @Override
    public boolean canEquip(ItemStack underArmorStack, Player player, ItemStack attachmentStack) {
        // A hood goes over a bare coif or a helmet, but not over a chaperon or jester hood
        for (ItemStack existing : SCUnderArmor.getArmorAttachments(underArmorStack)) {
            if (existing.getItem() instanceof KHChaperon || existing.getItem() instanceof KHCloak) return false;
        }
        return ArmorAttachment.super.canEquip(underArmorStack, player, attachmentStack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack target = findUnderArmor(player, type);
        if (!target.isEmpty() && target.getItem() instanceof SCUnderArmor) {
            return ArmorAttachment.super.use(level, player, hand, Type.HELMET);
        }
        return super.use(level, player, hand);
    }
}
