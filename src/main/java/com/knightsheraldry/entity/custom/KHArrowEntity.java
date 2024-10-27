package com.knightsheraldry.entity.custom;

import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KHArrowEntity extends PersistentProjectileEntity {
    private KHDamageCalculator.DamageType damageType;
    private float damage;

    public KHArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
        super(type, world);
    }

    public KHArrowEntity(EntityType<KHArrowEntity> khArrow, LivingEntity shooter, World world) {
        super(khArrow, shooter, world);
    }

    public void setDamageAmount(float damage) {
        this.damage = damage;
    }

    public float getDamageAmount() {
        return this.damage;
    }

    public void setDamageType(KHDamageCalculator.DamageType damageType) {
        this.damageType = damageType;
    }

    public KHDamageCalculator.DamageType getDamageType() {
        return this.damageType;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        this.remove(RemovalReason.DISCARDED);
    }

    public void hitKHEntity(LivingEntity target, ItemStack stack, float damage) {
        damage = KHDamageCalculator.getKHDamage(target, damage, getDamageType());
        KHDamageCalculator.applyDamage(target, (PlayerEntity) getOwner(), stack, damage);
        if (this.isOnFire()) target.setOnFireFor(5);
    }
}