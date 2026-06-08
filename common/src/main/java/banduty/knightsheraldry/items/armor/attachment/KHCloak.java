package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHCloakAttachmentRenderer;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.items.custom.armor.underarmor.UnderArmorContents;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static banduty.stoneycore.util.SCInventoryItemFinder.findUnderArmor;

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
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            ItemStack target = findUnderArmor(player, armorType);
            if (!target.isEmpty()) {
                return InteractionResultHolder.sidedSuccess(stack, true);
            }
            return InteractionResultHolder.pass(stack);
        }

        ItemStack target = findUnderArmor(player, armorType);

        if (!target.isEmpty() && target.getItem() instanceof SCUnderArmor underArmor) {

            UnderArmorContents contents = target.getOrDefault(SCDataComponents.UNDER_ARMOR_CONTENTS.get(),
                    UnderArmorContents.EMPTY);

            UnderArmorContents.Mutable mutable = new UnderArmorContents.Mutable(contents);

            ItemStack result = mutable.tryInsert(stack, player, target);

            if (!result.isEmpty()) {
                player.getInventory().placeItemBackInInventory(result);
            }

            target.set(
                    SCDataComponents.UNDER_ARMOR_CONTENTS.get(),
                    mutable.toImmutable());

            underArmor.rebuildAttachmentAttributes(target);

            level.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.ARMOR_EQUIP_LEATHER.value(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F);

            stack.shrink(1);

            return InteractionResultHolder.success(stack);

        }

        return InteractionResultHolder.pass(stack);
    }
}
