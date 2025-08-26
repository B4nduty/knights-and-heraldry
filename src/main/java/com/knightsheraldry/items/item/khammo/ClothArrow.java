package com.knightsheraldry.items.item.khammo;

import banduty.stoneycore.items.item.SCArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        if (itemStack == user.getMainHandStack()) offHandStack = user.getOffHandStack();
        else offHandStack = user.getMainHandStack();
        if (offHandStack.isOf(Items.FLINT_AND_STEEL)) {
            itemStack.getOrCreateNbt().putBoolean("ignited", true);
        }
        return super.use(world, user, hand);
    }
}
