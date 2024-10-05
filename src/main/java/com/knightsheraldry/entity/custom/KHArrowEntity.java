package com.knightsheraldry.entity.custom;

import com.knightsheraldry.util.KHDamageCalculator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
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

    public void setDamageType(KHDamageCalculator.DamageType damageType) {
        this.damageType = damageType;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    public void applyDamage(LivingEntity target) {
        float damageDealt = new KHDamageCalculator().getKHDamage(target, damage, damageType);
        target.damage(this.getDamageSources().genericKill(), damageDealt);
    }
}