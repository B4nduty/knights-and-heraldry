package banduty.knightsheraldry.items.armor.deco;

import banduty.knightsheraldry.client.item.deco.HelmetDecoRenderer;
import banduty.stoneycore.client.render.ArmorAttachmentRenderProvider;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import banduty.stoneycore.items.custom.armor.ArmorAttachment;
import banduty.stoneycore.items.custom.armor.underarmor.SCUnderArmor;
import banduty.stoneycore.items.custom.armor.underarmor.UnderArmorContents;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import banduty.stoneycore.util.definitionsloader.ArmorAttachmentSlotDefinitionData;
import banduty.stoneycore.util.definitionsloader.ArmorAttachmentSlotDefinitionsStorage;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

import static banduty.stoneycore.util.SCInventoryItemFinder.findUnderArmor;

public class DecoItem extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {

    public DecoItem(Properties properties) {
        super(properties);
    }

    public ArmorAttachmentRenderer cachedRenderer;

    @Override
    public ArmorAttachmentRenderer getRenderer() {
        if (this.cachedRenderer == null) {
            this.cachedRenderer = new HelmetDecoRenderer();
        }
        return this.cachedRenderer;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        ArmorAttachmentSlotDefinitionData slotDef = ArmorAttachmentSlotDefinitionsStorage.getData(stack, ArmorItem.Type.HELMET);
        if (Objects.equals(slotDef, ArmorAttachmentSlotDefinitionsStorage.getDefaultData())) {
            return InteractionResultHolder.pass(stack);
        }

        ArmorItem.Type targetedArmorType = ArmorAttachmentSlotDefinitionsStorage.getArmorType(slotDef);
        if (targetedArmorType == null) {
            return InteractionResultHolder.pass(stack);
        }

        ItemStack target = findUnderArmor(player, targetedArmorType);

        if (level.isClientSide) {
            if (!target.isEmpty()) {
                return InteractionResultHolder.sidedSuccess(stack, true);
            }
            return InteractionResultHolder.pass(stack);
        }

        if (!target.isEmpty() && target.getItem() instanceof SCUnderArmor underArmor) {
            UnderArmorContents contents =
                    target.getOrDefault(SCDataComponents.UNDER_ARMOR_CONTENTS.get(), UnderArmorContents.EMPTY);

            UnderArmorContents.Mutable mutable = new UnderArmorContents.Mutable(contents);

            ItemStack result = mutable.tryInsert(stack, player, target);

            if (!result.isEmpty()) {
                player.getInventory().placeItemBackInInventory(result);
            }

            target.set(
                    SCDataComponents.UNDER_ARMOR_CONTENTS.get(),
                    mutable.toImmutable()
            );

            underArmor.rebuildAttachmentAttributes(target);

            level.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.ARMOR_EQUIP_LEATHER.value(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );

            stack.shrink(1);

            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.pass(stack);
    }
}