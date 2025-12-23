package com.knightsheraldry.entity.custom;

import banduty.stoneycore.util.SCDamageCalculator;
import banduty.stoneycore.util.definitionsloader.WeaponDefinitionsLoader;
import com.knightsheraldry.effect.ModEffects;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class WarDartEntity extends AbstractArrow {
    private ItemStack wardartStack;

    public WarDartEntity(EntityType<? extends AbstractArrow> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.wardartStack = new ItemStack(ModItems.WARDART.get());
    }

    public WarDartEntity(LivingEntity livingEntity, Level level, ItemStack stack) {
        super(ModEntities.WARDART_PROJECTILE.get(), livingEntity, level);
        this.wardartStack = new ItemStack(ModItems.WARDART.get());
        this.wardartStack = stack.copy();
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return this.wardartStack.copy();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    protected boolean tryPickup(Player player) {
        return super.tryPickup(player) || this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }

    @Override
    public void playerTouch(Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) {
            super.playerTouch(player);
        }
    }

    public ItemStack getStack() {
        return this.wardartStack.copy();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(ModEffects.PIN, 100, 0));
            livingEntity.hurt(this.damageSources().genericKill(),
                    WeaponDefinitionsLoader.getData(ModItems.WARDART.get()).melee().damage()
                            .getOrDefault(SCDamageCalculator.DamageType.PIERCING.name(), 0f));
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if (!this.level().isClientSide()) {
            if (type == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult)hitResult;
                this.onHitBlock(blockHitResult);
                BlockPos blockPos = blockHitResult.getBlockPos();
                this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockPos, GameEvent.Context.of(this, this.level().getBlockState(blockPos)));
            }

            if (type == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult)hitResult).getEntity();
                this.level().gameEvent(GameEvent.PROJECTILE_LAND, hitResult.getLocation(), GameEvent.Context.of(this, null));
                this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
                this.setPos(this.position().add(0, -1, 0));
                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity instanceof Player player && player.isCreative()) return;
                    this.onHitEntity((EntityHitResult)hitResult);
                }
            }
        }
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }
}