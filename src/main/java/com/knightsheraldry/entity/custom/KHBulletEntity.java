package com.knightsheraldry.entity.custom;

import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHBulletEntity extends PersistentProjectileEntity {
    private final ItemStack bulletStack = new ItemStack(ModItems.BLACK_POWDER);
    private KHDamageCalculator.DamageType damageType;
    private float damage;

    public KHBulletEntity(EntityType<? extends PersistentProjectileEntity> entityEntityType, World world) {
        super(entityEntityType, world);
    }

    public KHBulletEntity(LivingEntity shooter, World world) {
        super(ModEntities.KH_BULLET, shooter, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.bulletStack;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            damage = KHDamageCalculator.getKHDamage(target, this.damage, this.damageType);
            KHDamageCalculator.applyDamage(target, (PlayerEntity) getOwner(), bulletStack, damage);
            if (this.isOnFire()) target.setOnFireFor(5);
        }
        super.onEntityHit(entityHitResult);
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.discard();
    }

    public void setDamageAmount(float damage) {
        this.damage = damage;
    }

    public void setDamageType(KHDamageCalculator.DamageType damageType) {
        this.damageType = damageType;
    }
}