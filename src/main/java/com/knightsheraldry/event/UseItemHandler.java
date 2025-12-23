package com.knightsheraldry.event;

import com.knightsheraldry.items.item.khrangeweapon.Arquebus;
import com.knightsheraldry.items.item.khrangeweapon.Handgonne;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public class UseItemHandler implements UseItemCallback {
    @Override
    public InteractionResultHolder<ItemStack> interact(Player playerEntity, Level level, InteractionHand hand) {
        ItemStack itemStack = playerEntity.getItemInHand(hand);

        if ((itemStack.getItem() instanceof Handgonne || itemStack.getItem() instanceof Arquebus) && isRainingOrUnderWater(level, playerEntity)) {
            return InteractionResultHolder.fail(itemStack);
        }

        return InteractionResultHolder.pass(itemStack);
    }

    private boolean isRainingOrUnderWater(Level level, Player player) {
        if (player.isUnderWater()) return true;

        if (!level.isRaining()) {
            return false;
        }

        Biome biome = level.getBiome(player.getOnPos()).value();

        return biome.warmEnoughToRain(player.getOnPos()) || biome.getPrecipitationAt(player.getOnPos()) == Biome.Precipitation.NONE;
    }
}
