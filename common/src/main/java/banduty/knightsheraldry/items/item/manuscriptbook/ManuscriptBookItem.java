package banduty.knightsheraldry.items.item.manuscriptbook;

import banduty.knightsheraldry.platform.Services;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ManuscriptBookItem extends Item {

    public ManuscriptBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if (!world.isClientSide() && user instanceof ServerPlayer serverPlayer) {

            Services.MANUSCRIPT_BOOK.openManuscriptBookScreen(serverPlayer, itemStack);

            // user.playSound(sound, 1f, 1f);
        }
        return InteractionResultHolder.success(user.getItemInHand(hand));
    }
}