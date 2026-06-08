package banduty.knightsheraldry.items.armor.attachment;

import banduty.knightsheraldry.client.item.armor.KHHelmetAttachmentRenderer;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import static banduty.stoneycore.util.SCInventoryItemFinder.findUnderArmor;

public class KHHelmetAttachment extends Item implements ArmorAttachment, ArmorAttachmentRenderProvider {
    private final boolean openVisor;
    private final Ingredient ingredient;

    public ArmorAttachmentRenderer cachedRenderer;

    public KHHelmetAttachment(Properties properties, Ingredient ingredient) {
        super(properties);
        this.openVisor = false;
        this.ingredient = ingredient;
    }

    public KHHelmetAttachment(Properties properties, boolean openVisor, Ingredient ingredient) {
        super(properties);
        this.openVisor = openVisor;
        this.ingredient = ingredient;
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
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            ItemStack target = findUnderArmor(player, ArmorItem.Type.HELMET);
            if (!target.isEmpty()) {
                return InteractionResultHolder.sidedSuccess(stack, true);
            }
            return InteractionResultHolder.pass(stack);
        }

        ItemStack target = findUnderArmor(player, ArmorItem.Type.HELMET);

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
