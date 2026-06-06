package banduty.knightsheraldry.entity.custom;

import banduty.knightsheraldry.effect.KHEffects;
import banduty.knightsheraldry.entity.KHEntities;
import banduty.knightsheraldry.items.KHItems;
import banduty.stoneycore.combat.damagetype.SCDamageType;
import banduty.stoneycore.util.weaponutil.SCWeaponUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
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

public class WarDartEntity extends AbstractArrow {
    
    private static final EntityDataAccessor<Boolean> ID_FOIL =
            SynchedEntityData.defineId(WarDartEntity.class, EntityDataSerializers.BOOLEAN);

    public WarDartEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public WarDartEntity(LivingEntity livingEntity, Level level, ItemStack stack) {
        super(KHEntities.WARDART_PROJECTILE.get(), livingEntity, level, stack, stack);
        this.entityData.set(ID_FOIL, stack.hasFoil());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(ID_FOIL, false);
    }

    @Override
    protected ItemStack getPickupItem() {
        return this.getPickupItemStackOrigin().copy();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(KHItems.WARDART.get());
    }

    @Override
    protected boolean tryPickup(Player player) {
        return super.tryPickup(player)
                || this.ownedBy(player)
                && player.getInventory().add(this.getPickupItem());
    }

    @Override
    public void playerTouch(Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) {
            super.playerTouch(player);
        }
    }

    public ItemStack getStack() {
        return this.getPickupItem();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(KHEffects.PIN, 100, 0));

            livingEntity.hurt(
                    this.damageSources().genericKill(),
                    (float) SCWeaponUtil.getMaxDamage(
                            SCDamageType.PIERCING,
                            this.getPickupItem().getItem()
                    )
            );

            this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();

        if (!this.level().isClientSide()) {

            if (type == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;

                this.onHitBlock(blockHitResult);

                BlockPos blockPos = blockHitResult.getBlockPos();

                this.level().gameEvent(
                        GameEvent.PROJECTILE_LAND,
                        blockPos,
                        GameEvent.Context.of(this, this.level().getBlockState(blockPos))
                );
            }

            if (type == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult) hitResult).getEntity();

                this.level().gameEvent(
                        GameEvent.PROJECTILE_LAND,
                        hitResult.getLocation(),
                        GameEvent.Context.of(this, null)
                );

                this.setDeltaMovement(
                        this.getDeltaMovement().multiply(-0.01, -0.1, -0.01)
                );

                this.setPos(this.position().add(0, -1, 0));

                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity instanceof Player player && player.isCreative()) {
                        return;
                    }

                    this.onHitEntity((EntityHitResult) hitResult);
                }
            }
        }
    }

    public boolean isFoil() {
        return this.entityData.get(ID_FOIL);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }
}