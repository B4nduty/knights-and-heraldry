package com.knightsheraldry.items.item.khammo;

import banduty.stoneycore.items.item.SCArrow;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;

public class ClothArrow extends SCArrow {
    private static final int IGNITE_DURATION_TICKS = 20 * 60;

    public ClothArrow(Settings settings, BiFunction<PlayerEntity, World, Entity> arrowEntityFactory) {
        super(settings, arrowEntityFactory);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!world.isClient && stack.getNbt() != null && stack.getNbt().getBoolean("ignited")) {
            long igniteTime = stack.getNbt().getLong("igniteTime");
            long currentTime = world.getTime();

            if (currentTime - igniteTime >= IGNITE_DURATION_TICKS) {
                stack.getOrCreateNbt().putBoolean("ignited", false);
                stack.getOrCreateNbt().putBoolean("extinguished", true);

                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                        SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, entity.getSoundCategory(), 0.5f,
                        1.8f + world.getRandom().nextFloat() * (3.4f - 1.8f)
                );
            }
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        BlockState state = world.getBlockState(pos);
        ItemStack stack = context.getStack();

        if (player == null) return ActionResult.PASS;

        if (world.getBlockState(pos).isOf(Blocks.FIRE)) {
            if (!world.isClient) {
                stack.getOrCreateNbt().putBoolean("ignited", true);
                stack.getOrCreateNbt().putLong("igniteTime", world.getTime());
            } else {
                float pitch = 0.8f + player.getRandom().nextFloat() * (1.2f - 0.8f);
                player.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 0.5f, pitch);
            }
            return ActionResult.SUCCESS;
        }

        if (world.getBlockState(pos).isOf(Blocks.WATER_CAULDRON) && state.contains(LeveledCauldronBlock.LEVEL)) {
            int level = state.get(LeveledCauldronBlock.LEVEL);
            if (level > 1) {
                if (!world.isClient) {
                    LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
                    stack.getOrCreateNbt().putBoolean("ignited", false);
                    stack.getOrCreateNbt().putBoolean("extinguished", true);
                } else {
                    float pitch = 1.8f + player.getRandom().nextFloat() * (3.4f - 1.8f);
                    player.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 0.5f, pitch);
                }
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ItemStack offHandStack;
        if (itemStack.getNbt() != null && itemStack.getNbt().getBoolean("extinguished")) return super.use(world, user, hand);
        if (itemStack == user.getMainHandStack()) offHandStack = user.getOffHandStack();
        else offHandStack = user.getMainHandStack();
        if (offHandStack.isOf(Items.WATER_BUCKET) && itemStack.getNbt() != null && itemStack.getNbt().getBoolean("ignited")) {
            if (!world.isClient) {
                itemStack.getOrCreateNbt().putBoolean("ignited", false);
                itemStack.getOrCreateNbt().putBoolean("extinguished", true);
                if (!user.isCreative()) {
                    if (itemStack == user.getMainHandStack()) {
                        user.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.BUCKET));
                    } else {
                        user.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.BUCKET));
                    }
                }
            } else {
                float pitch = 1.8f + user.getRandom().nextFloat() * (3.4f - 1.8f);
                user.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 0.5f, pitch);
            }
        } else if (offHandStack.isOf(Items.FLINT_AND_STEEL)) {
            if (!world.isClient) {
                itemStack.getOrCreateNbt().putBoolean("ignited", true);
                itemStack.getOrCreateNbt().putLong("igniteTime", world.getTime());
                if (user instanceof ServerPlayerEntity serverPlayerEntity && !serverPlayerEntity.isCreative())
                    offHandStack.damage(1, user.getRandom(), serverPlayerEntity);
            } else {
                float pitch = 0.8f + user.getRandom().nextFloat() * (1.2f - 0.8f);
                user.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 0.5f, pitch);
            }
        }
        return TypedActionResult.success(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (world != null && stack.getNbt() != null && stack.getNbt().getBoolean("ignited")) {
            long igniteTime = stack.getNbt().getLong("igniteTime");
            long remainingTicks = IGNITE_DURATION_TICKS - (world.getTime() - igniteTime);
            if (remainingTicks < 0) remainingTicks = 0;

            long seconds = remainingTicks / 20;
            long hours = seconds / 3600;
            seconds %= 3600;
            long minutes = seconds / 60;
            seconds %= 60;

            tooltip.add(Text.literal(String.format("Ignited - Time left: %02d:%02d:%02d", hours, minutes, seconds)));
        }
    }
}
