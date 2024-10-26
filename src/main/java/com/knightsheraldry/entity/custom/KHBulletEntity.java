package com.knightsheraldry.entity.custom;

import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHBulletEntity extends KHArrowEntity {
    private final ItemStack bulletStack;

    public KHBulletEntity(LivingEntity shooter, World world) {
        super(ModEntities.KH_ARROW, shooter, world);
        this.bulletStack = new ItemStack(ModItems.BLACK_POWDER);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.bulletStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            applyDamage(target, (LivingEntity) getOwner());
        }
        super.onEntityHit(entityHitResult);
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.discard();
    }
}