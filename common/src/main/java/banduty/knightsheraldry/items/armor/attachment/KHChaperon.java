package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHChaperonAttachmentRenderer;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.armor.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class KHChaperon extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {
    boolean hasOverlay;

    public ArmorAttachmentRenderer cachedRenderer;

    public KHChaperon(Properties properties, boolean hasOverlay) {
        super(properties);
        this.hasOverlay = hasOverlay;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHChaperonAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    public int getDefaultColor() {
        return 0xFFA06440;
    }

    @Override
    public boolean canEquip(ItemStack underArmorStack, Player player, ItemStack attachmentStack) {
        // A chaperon can't be worn over a hood.
        for (ItemStack existing : SCUnderArmor.getArmorAttachments(underArmorStack)) {
            if (KHHood.isHood(existing)) return false;
        }
        return ArmorAttachment.super.canEquip(underArmorStack, player, attachmentStack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ArmorAttachment.super.use(level, player, hand, ArmorItem.Type.HELMET);
    }
}
