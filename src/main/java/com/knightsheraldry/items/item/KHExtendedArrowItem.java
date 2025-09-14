package com.knightsheraldry.items.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.function.BiFunction;

public class KHExtendedArrowItem extends ArrowItem {
    private final BiFunction<LivingEntity, World, PersistentProjectileEntity> arrowEntityFactory;

    public KHExtendedArrowItem(Item.Settings settings,
                               BiFunction<LivingEntity, World, PersistentProjectileEntity> arrowEntityFactory) {
        super(settings);
        this.arrowEntityFactory = arrowEntityFactory;
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return arrowEntityFactory.apply(shooter, world);
    }
}