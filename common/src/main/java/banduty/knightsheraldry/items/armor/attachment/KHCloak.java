package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHCloakAttachmentRenderer;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class KHCloak extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {
    boolean hasOverlay;
    int numberSlot;
    ArmorItem.Type armorType;

    public ArmorAttachmentRenderer cachedRenderer;

    public KHCloak(Properties properties, int numberSlot, ArmorItem.@NotNull Type armorType) {
        super(properties);
        this.hasOverlay = false;
        this.numberSlot = numberSlot;
        this.armorType = armorType;
    }

    public KHCloak(Properties properties, int numberSlot, boolean hasOverlay, ArmorItem.@NotNull Type armorType) {
        super(properties);
        this.hasOverlay = hasOverlay;
        this.numberSlot = numberSlot;
        this.armorType = armorType;
    }

    public boolean hasOverlay() {
        return hasOverlay;
    }

    @Override
    public ArmorItem.@NotNull Type getArmorSlot() {
        return armorType;
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
        if (this != KHItems.HELMET_HOOD.get() && this != KHItems.HELMET_TORN_HOOD.get()) return ArmorAttachment.super.canEquip(underArmorStack, player);
        for (ItemStack armorAttachments : SCUnderArmor.getArmorAttachments(underArmorStack)) {
            if (armorAttachments.getItem() instanceof KHCloak) return false;
            if (armorAttachments.getItem() instanceof KHHelmetAttachment) return true;
        }
        return false;
    }

    @Override
    public @Range(from = 0L, to = 2147483647L) int numberSlot() {
        return numberSlot;
    }
}
