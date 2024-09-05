package com.knightsheraldry.entity.custom;

import com.knightsheraldry.effect.ModEffects;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class WarDartEntity extends PersistentProjectileEntity {
    private ItemStack wardartStack;

    public WarDartEntity(EntityType<? extends PersistentProjectileEntity> entityEntityType, World world) {
        super(entityEntityType, world);
        this.wardartStack = new ItemStack(ModItems.WARDART);
    }

    public WarDartEntity(LivingEntity livingEntity, World world, ItemStack stack) {
        super(ModEntities.WARDART_PROJECTILE, livingEntity, world);
        this.wardartStack = new ItemStack(ModItems.WARDART);
        this.wardartStack = stack.copy();
    }

    @Override
    protected ItemStack asItemStack() {
        return this.wardartStack.copy();
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (this.isOwner(player) || this.getOwner() == null) {
            super.onPlayerCollision(player);
        }
    }

    public ItemStack getStack() {
        return this.wardartStack.copy();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.PIN, 100, 0));
            livingEntity.damage(this.getDamageSources().genericKill(), 0.1f);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if (!this.getWorld().isClient) {
            if (type == HitResult.Type.BLOCK) {
                this.setVelocity(Vec3d.ZERO);
                BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();
                this.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
                this.setNoClip(true);
                BlockHitResult blockHitResult = (BlockHitResult)hitResult;
                this.onBlockHit(blockHitResult);
                BlockPos blockPos = blockHitResult.getBlockPos();
                this.getWorld().emitGameEvent(GameEvent.PROJECTILE_LAND, blockPos, GameEvent.Emitter.of(this, this.getWorld().getBlockState(blockPos)));
            }

            if (type == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult)hitResult).getEntity();
                this.setVelocity(Vec3d.ZERO);
                Vec3d pos = hitResult.getPos();
                this.getWorld().emitGameEvent(GameEvent.PROJECTILE_LAND, hitResult.getPos(), GameEvent.Emitter.of(this, null));
                this.setPosition(pos.getX(), pos.getY(), pos.getZ());
                this.setNoClip(true);
                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity instanceof PlayerEntity player && player.isCreative()) return;
                    this.onEntityHit((EntityHitResult)hitResult);
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isNoClip()) {
            this.setVelocity(Vec3d.ZERO);
        }
    }
}