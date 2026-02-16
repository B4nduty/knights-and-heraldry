package banduty.knightsheraldry.items.item.khweapon;

import banduty.knightsheraldry.entity.custom.WarDartEntity;
import banduty.knightsheraldry.items.ModToolMaterials;
import banduty.knightsheraldry.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class WarDart extends SwordItem {
    public WarDart(float attackSpeed, Properties properties) {
        super(ModToolMaterials.WEAPONS, 1, attackSpeed, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        user.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int remainingUseTicks) {
        if (user instanceof Player player) {
            int i = this.getUseDuration(stack) - remainingUseTicks;
            if (i >= 10 && !level.isClientSide()) {
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(user.getUsedItemHand()));
                WarDartEntity wardartEntity = new WarDartEntity(player, level, stack);
                wardartEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                if (player.isCreative()) {
                    wardartEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                level.addFreshEntity(wardartEntity);
                level.playSound(user, user.getOnPos(), SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!player.isCreative())  {
                    player.getInventory().removeItem(stack);
                    player.getCooldowns().addCooldown(this, Services.PLATFORM.getConfig().getWardartCooldown() * 20);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        list.add(Component.translatable("text.tooltip.knightsheraldry.throw-to-pin"));
    }
}