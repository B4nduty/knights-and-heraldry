package com.knightsheraldry.event;

import com.knightsheraldry.items.item.khrangeweapon.Arquebus;
import com.knightsheraldry.items.item.khrangeweapon.Handgonne;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class UseItemHandler implements UseItemCallback {
    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity playerEntity, World world, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);

        if ((itemStack.getItem() instanceof Handgonne || itemStack.getItem() instanceof Arquebus) && isRainingOrUnderWater(world, playerEntity)) {
            return TypedActionResult.fail(itemStack);
        }

        return TypedActionResult.pass(itemStack);
    }

    private boolean isRainingOrUnderWater(World world, PlayerEntity player) {
        if (player.isSubmergedInWater()) return true;

        if (!world.isRaining()) {
            return false;
        }

        Biome biome = world.getBiome(player.getBlockPos()).value();

        return biome.doesNotSnow(player.getBlockPos()) || biome.getPrecipitation(player.getBlockPos()) == Biome.Precipitation.NONE;
    }
}
