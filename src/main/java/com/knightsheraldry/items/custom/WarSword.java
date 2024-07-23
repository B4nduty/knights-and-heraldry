package com.knightsheraldry.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class WarSword extends KHWeaponsTemplate {
    public WarSword(float attackSpeed, Settings settings) {
        super(attackSpeed, settings);
    }

    @Override
    public float[] getDefaultAttackDamageValues() {
        return new float[] {
                // Respect the order of higher and lower values, Crosshair Overlay not compatible with that right now
                1.0F, 8.0F, 12.0F, 8.0F, 4.0F, //Slashing
                1.0F, 6.0F, 9.0F, 6.0F, 3.0F, //Piercing
                1.0F, 5.0F, 7.25F, 5.0F, 2.5F //Bludgeoning
        };
    }

    @Override
    public double[] getDefaultRadiusValues() {
        return new double[] {
                // Values cannot be higher than it's next value
                1.0d, //1st Distance
                2.0d, //2nd Distance
                2.5d, //3rd Distance
                3.5d, //4th Distance
                4.0d //5th Distance
        };
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }
}