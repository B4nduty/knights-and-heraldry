package banduty.knightsheraldry.items.armor.horse;

import banduty.stoneycore.items.custom.armor.underarmor.UnderArmorContents;
import banduty.stoneycore.items.custom.armor.underarmor.UnderArmorTooltip;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class HorseBardingArmorItem extends AnimalArmorItem {

    public HorseBardingArmorItem(Properties properties) {
        super(ArmorMaterials.IRON, AnimalArmorItem.BodyType.EQUESTRIAN, true, properties);
    }

    public ResourceLocation getTexture() {
        return ResourceLocation.fromNamespaceAndPath("stoneycore", "textures/models/armor/a_layer_1.png");
    }

    public int getDefaultColor() {
        return -1;
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack bardingStack, Slot slot, ClickAction action, Player player) {
        return handleStackInteraction(bardingStack, action, player, slot::getItem, slot::set);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack bardingStack, ItemStack incomingStack, Slot slot, ClickAction action, Player player, SlotAccess access) {
        return handleStackInteraction(bardingStack, action, player, () -> incomingStack, access::set);
    }

    private boolean handleStackInteraction(ItemStack bardingStack, ClickAction action, Player player,
                                           Supplier<ItemStack> incomingSupplier, Consumer<ItemStack> outputCons) {
        if (action != ClickAction.SECONDARY) return false;

        ItemStack incomingStack = incomingSupplier.get();
        UnderArmorContents contents = bardingStack.getOrDefault(SCDataComponents.UNDER_ARMOR_CONTENTS.get(), UnderArmorContents.EMPTY);
        UnderArmorContents.Mutable mutable = new UnderArmorContents.Mutable(contents);

        if (incomingStack.isEmpty()) {
            ItemStack extracted = mutable.removeLast();
            if (!extracted.isEmpty()) {
                bardingStack.set(SCDataComponents.UNDER_ARMOR_CONTENTS.get(), mutable.toImmutable());
                outputCons.accept(extracted);
                playRemoveSound(player);
                return true;
            }
        } else {
            ItemStack result = mutable.tryInsert(incomingStack, player, bardingStack);

            if (result != null) {
                incomingStack.shrink(1);

                if (!result.isEmpty()) {
                    outputCons.accept(result);
                }

                bardingStack.set(SCDataComponents.UNDER_ARMOR_CONTENTS.get(), mutable.toImmutable());
                playInsertSound(player);
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        UnderArmorContents contents = stack.get(SCDataComponents.UNDER_ARMOR_CONTENTS.get());
        return Optional.of(new UnderArmorTooltip(contents, this.getType()));
    }

    private void playInsertSound(Player player) {
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BUNDLE_INSERT, SoundSource.PLAYERS, 0.8F, 0.8F + player.getRandom().nextFloat() * 0.4F);
    }

    private void playRemoveSound(Player player) {
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BUNDLE_REMOVE_ONE, SoundSource.PLAYERS, 0.8F, 0.8F + player.getRandom().nextFloat() * 0.4F);
    }
}