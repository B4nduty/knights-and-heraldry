package com.knightsheraldry.items.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.BiFunction;

public class KHExtendedArrowItem extends ArrowItem {
    private final BiFunction<LivingEntity, Level, AbstractArrow> arrowEntityFactory;

    public KHExtendedArrowItem(Item.Properties properties,
                               BiFunction<LivingEntity, Level, AbstractArrow> arrowEntityFactory) {
        super(properties);
        this.arrowEntityFactory = arrowEntityFactory;
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity shooter) {
        return arrowEntityFactory.apply(shooter, level);
    }
}