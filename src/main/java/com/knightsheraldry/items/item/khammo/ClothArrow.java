package com.knightsheraldry.items.item.khammo;

import com.knightsheraldry.items.item.KHExtendedArrowItem;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;

public class ClothArrow extends KHExtendedArrowItem {
    private static final int IGNITE_DURATION_TICKS = 20 * 60;

    public ClothArrow(Item.Properties properties, BiFunction<LivingEntity, Level, AbstractArrow> arrowEntityFactory) {
        super(properties, arrowEntityFactory);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide() && stack.getTag() != null && stack.getTag().getBoolean("ignited")) {
            long igniteTime = stack.getTag().getLong("igniteTime");
            long currentTime = level.getGameTime();

            if (currentTime - igniteTime >= IGNITE_DURATION_TICKS) {
                stack.getOrCreateTag().remove("ignited");
                stack.getOrCreateTag().remove("igniteTime");
                stack.getOrCreateTag().putBoolean("extinguished", true);

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
                    ignitedArrow.getOrCreateTag().putBoolean("ignited", true);
                    ignitedArrow.getOrCreateTag().putLong("igniteTime", level.getGameTime());

                    if (!player.getInventory().add(ignitedArrow)) {
                        player.drop(ignitedArrow, false);
                    }
                } else {
                    stack.getOrCreateTag().putBoolean("ignited", true);
                    stack.getOrCreateTag().putLong("igniteTime", level.getGameTime());
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
                    stack.getOrCreateTag().remove("ignited");
                    stack.getOrCreateTag().remove("igniteTime");
                    stack.getOrCreateTag().putBoolean("extinguished", true);
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
        if (itemStack.getTag() != null && itemStack.getTag().getBoolean("extinguished")) return super.use(level, user, hand);
        if (itemStack == user.getMainHandItem()) offHandStack = user.getOffhandItem();
        else offHandStack = user.getMainHandItem();
        if (offHandStack.is(Items.WATER_BUCKET) && itemStack.getTag() != null && itemStack.getTag().getBoolean("ignited")) {
            if (!level.isClientSide()) {
                itemStack.getOrCreateTag().remove("ignited");
                itemStack.getOrCreateTag().remove("igniteTime");
                itemStack.getOrCreateTag().putBoolean("extinguished", true);
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
                    ignitedArrow.getOrCreateTag().putBoolean("ignited", true);
                    ignitedArrow.getOrCreateTag().putLong("igniteTime", level.getGameTime());

                    if (!user.getInventory().add(ignitedArrow)) {
                        user.drop(ignitedArrow, false);
                    }
                } else {
                    itemStack.getOrCreateTag().putBoolean("ignited", true);
                    itemStack.getOrCreateTag().putLong("igniteTime", level.getGameTime());
                }

                if (user instanceof ServerPlayer serverPlayer && !serverPlayer.isCreative()) {
                    offHandStack.hurtAndBreak(1, serverPlayer, p -> p.broadcastBreakEvent(hand));
                }
            } else {
                float pitch = 0.5f + user.getRandom().nextFloat() * 1.5f;
                user.playSound(SoundEvents.FIRECHARGE_USE, 0.5f, pitch);
            }
        }
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public @NotNull AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity shooter) {
        AbstractArrow arrow = super.createArrow(level, stack, shooter);
        if (stack.getTag() != null && stack.getTag().getBoolean("ignited")) {
            arrow.setRemainingFireTicks(60);
        }
        return arrow;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (level != null && itemStack.getTag() != null && itemStack.getTag().getBoolean("ignited")) {
            long igniteTime = itemStack.getTag().getLong("igniteTime");
            long remainingTicks = IGNITE_DURATION_TICKS - (level.getGameTime() - igniteTime);
            if (remainingTicks < 0) remainingTicks = 0;

            long seconds = remainingTicks / 20;
            long hours = seconds / 3600;
            seconds %= 3600;
            long minutes = seconds / 60;
            seconds %= 60;

            list.add(Component.literal(String.format("Ignited - Time left: %02d:%02d:%02d", hours, minutes, seconds)));
        }
    }
}