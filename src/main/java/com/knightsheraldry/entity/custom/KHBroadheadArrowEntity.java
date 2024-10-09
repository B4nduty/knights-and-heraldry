package com.knightsheraldry.entity.custom;

import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHBroadheadArrowEntity extends KHArrowEntity {
    private final ItemStack broadheadArrowStack = new ItemStack(ModItems.BROADHEAD_ARROW);

    public KHBroadheadArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world);
    }

    public KHBroadheadArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.KH_ARROW, shooter, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.broadheadArrowStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            this.applyDamage(target, getShooter());
        }
        super.onEntityHit(entityHitResult);
    }
}