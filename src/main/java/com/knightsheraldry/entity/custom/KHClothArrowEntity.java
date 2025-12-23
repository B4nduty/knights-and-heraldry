package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import com.knightsheraldry.data.ArrowBehavior;
import com.knightsheraldry.data.ArrowBehaviorManager;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.List;
import java.util.Random;

public class KHClothArrowEntity extends SCArrowEntity {
    private int groundTicks = 0;
    private boolean blockCollisioned = false;

    public KHClothArrowEntity(LivingEntity shooter, Level level) {
        super(ModEntities.CLOTH_ARROW.get(), shooter, level);
    }

    public KHClothArrowEntity(EntityType<KHClothArrowEntity> khClothArrowEntityEntityType, Level level) {
        super(khClothArrowEntityEntityType, level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.CLOTH_ARROW.get());
    }

    private ArrowBehavior getBehavior() {
        ArrowBehavior behavior = ArrowBehaviorManager.INSTANCE.getBehavior(BuiltInRegistries.ITEM.getKey(ModItems.CLOTH_ARROW.get()));
        if (behavior == null) {
            behavior = new ArrowBehavior();
            behavior.targetItemId = BuiltInRegistries.ITEM.getKey(ModItems.CLOTH_ARROW.get());
            behavior.requireOnFire = true;
            behavior.groundBurnTicks = 100;
            behavior.aoeRadius = 5.0D;
            ArrowBehavior.EffectEntry nausea = new ArrowBehavior.EffectEntry();
            nausea.id = new ResourceLocation("minecraft", "nausea");
            nausea.duration = 100;
            nausea.amplifier = 0;
            ArrowBehavior.EffectEntry poison = new ArrowBehavior.EffectEntry();
            poison.id = new ResourceLocation("minecraft", "poison");
            poison.duration = 100;
            poison.amplifier = 0;
            behavior.effects.add(nausea);
            behavior.effects.add(poison);
            behavior.smoke.enabled = true;
            behavior.smoke.particle = new ResourceLocation("minecraft", "campfire_cosy_smoke");
            behavior.smoke.count = 80;
            behavior.smoke.maxSpeed = 0.1D;
            behavior.igniteBlocks.radiusX = 2;
            behavior.igniteBlocks.radiusY = 2;
            behavior.igniteBlocks.radiusZ = 2;
            behavior.igniteBlocks.chance = 0.1f;
        }
        return behavior;
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            scHitEntity(target, new ItemStack(ModItems.CLOTH_ARROW.get()), getBaseDamage());
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        Level level = this.level();
        ArrowBehavior behavior = getBehavior();

        blockCollisioned = true;
        if ((!behavior.requireOnFire || this.isOnFire()) && level instanceof ServerLevel serverLevel) {
            if (behavior.smoke.enabled) spawnSmokeParticles(serverLevel, blockHitResult, behavior);
            applySmokeEffectsToNearbyEntities(level, behavior);
            igniteNearbyBlocks(serverLevel, blockHitResult.getBlockPos(), behavior);
        }

        super.onHitBlock(blockHitResult);
    }

    @Override
    public void tick() {
        super.tick();

        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        ArrowBehavior behavior = getBehavior();

        if ((!behavior.requireOnFire || this.isOnFire()) && this.blockCollisioned) {
            if (groundTicks >= behavior.groundBurnTicks) {
                this.setSharedFlagOnFire(false);
                this.setRemainingFireTicks(0);
                this.setSecondsOnFire(0);
            } else groundTicks++;
            applySmokeEffectsToNearbyEntities(serverLevel, behavior);
        }

    }

    private void spawnSmokeParticles(ServerLevel serverLevel, BlockHitResult blockHitResult, ArrowBehavior behavior) {
        BlockPos center = blockHitResult.getBlockPos();

        int particleCount = behavior.smoke.count;
        double maxSpeed = behavior.smoke.maxSpeed;
        ParticleOptions particle = ParticleTypes.CAMPFIRE_COSY_SMOKE;

        for (int i = 0; i < particleCount; i++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            double speed = random.nextDouble() * maxSpeed;

            double dx = Math.cos(angle);
            double dz = Math.sin(angle);

            serverLevel.sendParticles(
                    particle,
                    center.getX() + 0.5,
                    center.getY() + 0.5,
                    center.getZ() + 0.5,
                    1,
                    dx, 0, dz,
                    speed
            );
        }
    }

    private void applySmokeEffectsToNearbyEntities(Level level, ArrowBehavior behavior) {
        List<LivingEntity> entitiesInRange = level.getEntitiesOfClass(
                LivingEntity.class,
                this.getBoundingBox().inflate(behavior.aoeRadius),
                LivingEntity::isAlive
        );

        for (LivingEntity entity : entitiesInRange) {
            for (ArrowBehavior.EffectEntry e : behavior.effects) {
                MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(e.id);
                if (effect != null) entity.addEffect(new MobEffectInstance(effect, e.duration, e.amplifier));
            }
        }
    }

    private void igniteNearbyBlocks(ServerLevel serverLevel, BlockPos hitPos, ArrowBehavior behavior) {
        Random random = new Random();

        for (BlockPos pos : BlockPos.withinManhattan(hitPos, behavior.igniteBlocks.radiusX, behavior.igniteBlocks.radiusY, behavior.igniteBlocks.radiusZ)) {
            if (!serverLevel.getBlockState(pos).isAir()) continue;

            boolean canPlaceFire = false;
            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.relative(dir);
                BlockState neighborState = serverLevel.getBlockState(neighbor);

                if (neighborState.isSolidRender(serverLevel, neighbor) || neighborState.ignitedByLava()) {
                    canPlaceFire = true;
                    break;
                }
            }

            if (canPlaceFire && random.nextFloat() < behavior.igniteBlocks.chance) {
                serverLevel.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
            }
        }
    }
}