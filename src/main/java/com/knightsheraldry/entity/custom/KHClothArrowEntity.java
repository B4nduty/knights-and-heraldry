package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class KHClothArrowEntity extends SCArrowEntity {
    private int groundTicks = 0;
    private boolean blockCollisioned = false;

    public KHClothArrowEntity(LivingEntity shooter, World world) {
        super(ModEntities.CLOTH_ARROW.get(), shooter, world);
    }

    public KHClothArrowEntity(EntityType<KHClothArrowEntity> khClothArrowEntityEntityType, World world) {
        super(khClothArrowEntityEntityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.CLOTH_ARROW.get());
    }

    @Override
    protected void onSCEntityHit(EntityHitResult entityHitResult) {
        super.onSCEntityHit(entityHitResult);
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            scHitEntity(target, new ItemStack(ModItems.CLOTH_ARROW.get()), getDamageAmount());
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = this.getWorld();

        blockCollisioned = true;
        if (this.isOnFire() && world instanceof ServerWorld serverWorld) {
            spawnSmokeParticles(serverWorld, blockHitResult);
            applySmokeEffectsToNearbyEntities(world);
            igniteNearbyBlocks(serverWorld, blockHitResult.getBlockPos());
        }

        super.onBlockHit(blockHitResult);
    }

    @Override
    public void tick() {
        super.tick();

        if (!(this.getWorld() instanceof ServerWorld serverWorld)) return;

        if (this.isOnFire() && this.blockCollisioned) {
            if (groundTicks >= 100) {
                this.setOnFire(false);
                this.setFireTicks(0);
                this.setOnFireFor(0);
            } else groundTicks++;
            applySmokeEffectsToNearbyEntities(serverWorld);
        }

    }

    private void spawnSmokeParticles(ServerWorld serverWorld, BlockHitResult blockHitResult) {
        BlockPos center = blockHitResult.getBlockPos();

        int particleCount = 80;
        double maxSpeed = 0.1;

        for (int i = 0; i < particleCount; i++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            double speed = random.nextDouble() * maxSpeed;

            double dx = Math.cos(angle);
            double dz = Math.sin(angle);

            serverWorld.spawnParticles(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    center.getX() + 0.5,
                    center.getY() + 0.5,
                    center.getZ() + 0.5,
                    1,
                    dx, 0, dz,
                    speed
            );
        }
    }

    private void applySmokeEffectsToNearbyEntities(World world) {
        List<LivingEntity> entitiesInRange = world.getEntitiesByClass(
                LivingEntity.class,
                this.getBoundingBox().expand(5.0),
                LivingEntity::isAlive
        );

        for (LivingEntity entity : entitiesInRange) {
            // Apply Nausea + Poison while in smoke
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0)); // 5s nausea
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0)); // 5s poison
        }
    }

    private void igniteNearbyBlocks(ServerWorld world, BlockPos hitPos) {
        Random random = new Random();

        for (BlockPos pos : BlockPos.iterateOutwards(hitPos, 2, 2, 2)) {
            if (!world.getBlockState(pos).isAir()) continue;

            boolean canPlaceFire = false;
            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.offset(dir);
                BlockState neighborState = world.getBlockState(neighbor);

                if (neighborState.isOpaqueFullCube(world, neighbor) || neighborState.isBurnable()) {
                    canPlaceFire = true;
                    break;
                }
            }

            if (canPlaceFire && random.nextFloat() < 0.1f) {
                world.setBlockState(pos, Blocks.FIRE.getDefaultState());
            }
        }
    }
}