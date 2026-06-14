package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHCloakAttachmentRenderer;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class KHCloak extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {
    boolean hasOverlay;
    ArmorItem.Type armorType;

    public ArmorAttachmentRenderer cachedRenderer;

    public KHCloak(Properties properties, ArmorItem.@NotNull Type armorType) {
        super(properties);
        this.hasOverlay = false;
        this.armorType = armorType;
    }

    public KHCloak(Properties properties, boolean hasOverlay, ArmorItem.@NotNull Type armorType) {
        super(properties);
        this.hasOverlay = hasOverlay;
        this.armorType = armorType;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new KHCloakAttachmentRenderer();
        }
        return this.cachedRenderer;
    }

    public int getDefaultColor() {
        return 0xFFA06440;
    }

    @Override
    public boolean canEquip(ItemStack underArmorStack, Player player) {
        if (this != KHItems.HELMET_HOOD.get() && this != KHItems.HELMET_TORN_HOOD.get())
            return ArmorAttachment.super.canEquip(underArmorStack, player);
        for (ItemStack armorAttachments : SCUnderArmor.getArmorAttachments(underArmorStack)) {
            if (armorAttachments.getItem() instanceof KHCloak)
                return false;
            if (armorAttachments.getItem() instanceof KHHelmetAttachment)
                return true;
        }
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ArmorAttachment.super.use(level, player, hand, armorType);
    }
}
