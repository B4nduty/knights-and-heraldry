package banduty.knightsheraldry.items.item.khammo;

import banduty.knightsheraldry.items.item.KHExtendedArrowItem;
import banduty.knightsheraldry.util.itemdata.KHDataComponents;
import banduty.stoneycore.util.data.itemdata.SCDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;

public class ClothArrow extends KHExtendedArrowItem {
    static final int IGNITE_DURATION_TICKS = 20 * 60;

    public ClothArrow(Properties properties, BiFunction<LivingEntity, Level, AbstractArrow> arrowEntityFactory) {
        super(properties, arrowEntityFactory);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide() && Boolean.TRUE.equals(stack.get(SCDataComponents.IGNITED.get()))) {
            long igniteTime = stack.getOrDefault(SCDataComponents.IGNITE_TIME.get(), 0L);
            long currentTime = level.getGameTime();

            if (currentTime - igniteTime >= IGNITE_DURATION_TICKS) {
                stack.remove(SCDataComponents.IGNITED.get());
                stack.remove(SCDataComponents.IGNITE_TIME.get());
                stack.set(KHDataComponents.EXTINGUISHED.get(), true);

                level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                        SoundEvents.GENERIC_EXTINGUISH_FIRE, entity.getSoundSource(), 0.5f,
                        1.8f + level.getRandom().nextFloat() * (3.4f - 1.8f)
                );
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = context.getItemInHand();

        if (player == null) return InteractionResult.PASS;

        if (level.getBlockState(pos).is(Blocks.FIRE)) {
            if (!level.isClientSide()) {
                if (stack.getCount() > 1) {
                    ItemStack ignitedArrow = stack.split(1);
                    stack.set(SCDataComponents.IGNITED.get(), true);
                    stack.set(SCDataComponents.IGNITE_TIME.get(), level.getGameTime());

                    if (!player.getInventory().add(ignitedArrow)) {
                        player.drop(ignitedArrow, false);
                    }
                } else {
                    stack.set(SCDataComponents.IGNITED.get(), true);
                    stack.set(SCDataComponents.IGNITE_TIME.get(), level.getGameTime());
                }
            } else {
                float pitch = 0.5f + player.getRandom().nextFloat() * 1.5f;
                player.playSound(SoundEvents.FIRECHARGE_USE, 0.5f, pitch);
            }
            return InteractionResult.SUCCESS;
        }

        if (level.getBlockState(pos).is(Blocks.WATER_CAULDRON) && state.hasProperty(LayeredCauldronBlock.LEVEL)) {
            int cauldronLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (cauldronLevel >= 1) {
                if (!level.isClientSide()) {
                    LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                    stack.remove(SCDataComponents.IGNITED.get());
                    stack.remove(SCDataComponents.IGNITE_TIME.get());
                    stack.set(KHDataComponents.EXTINGUISHED.get(), true);
                } else {
                    float pitch = 1.8f + player.getRandom().nextFloat() * (3.4f - 1.8f);
                    player.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 0.5f, pitch);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        ItemStack offHandStack;
        if (Boolean.TRUE.equals(itemStack.get(KHDataComponents.EXTINGUISHED.get()))) return super.use(level, user, hand);
        if (itemStack == user.getMainHandItem()) offHandStack = user.getOffhandItem();
        else offHandStack = user.getMainHandItem();
        if (offHandStack.is(Items.WATER_BUCKET) && Boolean.TRUE.equals(itemStack.get(SCDataComponents.IGNITED.get()))) {
            if (!level.isClientSide()) {
                itemStack.remove(SCDataComponents.IGNITED.get());
                itemStack.remove(SCDataComponents.IGNITE_TIME.get());
                itemStack.set(KHDataComponents.EXTINGUISHED.get(), true);
                if (!user.isCreative()) {
                    if (itemStack == user.getMainHandItem()) {
                        user.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.BUCKET));
                    } else {
                        user.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BUCKET));
                    }
                }
            } else {
                float pitch = 1.8f + user.getRandom().nextFloat() * (3.4f - 1.8f);
                user.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 0.5f, pitch);
            }
        } else if (offHandStack.is(Items.FLINT_AND_STEEL)) {
            if (!level.isClientSide()) {
                if (itemStack.getCount() > 1) {
                    ItemStack ignitedArrow = itemStack.split(1);
                    ignitedArrow.set(SCDataComponents.IGNITED.get(), true);
                    ignitedArrow.set(SCDataComponents.IGNITE_TIME.get(), level.getGameTime());

                    if (!user.getInventory().add(ignitedArrow)) {
                        user.drop(ignitedArrow, false);
                    }
                } else {
                    itemStack.set(SCDataComponents.IGNITED.get(), true);
                    itemStack.set(SCDataComponents.IGNITE_TIME.get(), level.getGameTime());
                }

                if (user instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
                    offHandStack.hurtAndBreak(1, serverPlayer, serverPlayer.getEquipmentSlotForItem(itemStack));
                }
            } else {
                float pitch = 0.5f + user.getRandom().nextFloat() * 1.5f;
                user.playSound(SoundEvents.FIRECHARGE_USE, 0.5f, pitch);
            }
        }
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        AbstractArrow arrow = super.createArrow(level, ammo, shooter, weapon);
        if (Boolean.TRUE.equals(ammo.get(SCDataComponents.IGNITED.get()))) {
            arrow.setRemainingFireTicks(60);
        }
        return arrow;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ClothArrowTooltip.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}