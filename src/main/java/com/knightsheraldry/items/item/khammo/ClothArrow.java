package com.knightsheraldry.items.item.khammo;

import banduty.stoneycore.items.item.SCArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.function.BiFunction;

public class ClothArrow extends SCArrow {
    public ClothArrow(Settings settings, BiFunction<PlayerEntity, World, Entity> arrowEntityFactory) {
        super(settings, arrowEntityFactory);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) return super.use(world, user, hand);
        ItemStack itemStack = user.getStackInHand(hand);
        ItemStack offHandStack;
        if (itemStack.getNbt() != null && itemStack.getNbt().getBoolean("extinguished")) return super.use(world, user, hand);
        if (itemStack == user.getMainHandStack()) offHandStack = user.getOffHandStack();
        else offHandStack = user.getMainHandStack();
        if (offHandStack.isOf(Items.WATER_BUCKET) && itemStack.getNbt() != null && itemStack.getNbt().getBoolean("ignited")) {
            itemStack.getOrCreateNbt().putBoolean("ignited", false);
            itemStack.getOrCreateNbt().putBoolean("extinguished", true);
            if (!user.isCreative()) {
                if (itemStack == user.getMainHandStack()) {
                    user.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.BUCKET));
                } else {
                    user.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.BUCKET));
                }
            }
        } else if (offHandStack.isOf(Items.FLINT_AND_STEEL)) {
            itemStack.getOrCreateNbt().putBoolean("ignited", true);
            if (user instanceof ServerPlayerEntity serverPlayerEntity && !serverPlayerEntity.isCreative())
                offHandStack.damage(1, user.getRandom(), serverPlayerEntity);
        }
        return TypedActionResult.success(itemStack);
    }
}
