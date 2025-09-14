package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import com.knightsheraldry.data.ArrowBehavior;
import com.knightsheraldry.data.ArrowBehaviorManager;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
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

    private ArrowBehavior getBehavior() {
        ArrowBehavior behavior = ArrowBehaviorManager.INSTANCE.getBehavior(Registries.ITEM.getId(ModItems.CLOTH_ARROW.get()));
        if (behavior == null) {
            behavior = new ArrowBehavior();
            behavior.targetItemId = Registries.ITEM.getId(ModItems.CLOTH_ARROW.get());
            behavior.requireOnFire = true;
            behavior.groundBurnTicks = 100;
            behavior.aoeRadius = 5.0D;
            ArrowBehavior.EffectEntry nausea = new ArrowBehavior.EffectEntry();
            nausea.id = new Identifier("minecraft", "nausea");
            nausea.duration = 100;
            nausea.amplifier = 0;
            ArrowBehavior.EffectEntry poison = new ArrowBehavior.EffectEntry();
            poison.id = new Identifier("minecraft", "poison");
            poison.duration = 100;
            poison.amplifier = 0;
            behavior.effects.add(nausea);
            behavior.effects.add(poison);
            behavior.smoke.enabled = true;
            behavior.smoke.particle = new Identifier("minecraft", "campfire_cosy_smoke");
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
            scHitEntity(target, new ItemStack(ModItems.CLOTH_ARROW.get()), getDamage());
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = this.getWorld();
        ArrowBehavior behavior = getBehavior();

        blockCollisioned = true;
        if ((!behavior.requireOnFire || this.isOnFire()) && world instanceof ServerWorld serverWorld) {
            if (behavior.smoke.enabled) spawnSmokeParticles(serverWorld, blockHitResult, behavior);
            applySmokeEffectsToNearbyEntities(world, behavior);
            igniteNearbyBlocks(serverWorld, blockHitResult.getBlockPos(), behavior);
        }

        super.onBlockHit(blockHitResult);
    }

    @Override
    public void tick() {
        super.tick();

        if (!(this.getWorld() instanceof ServerWorld serverWorld)) return;

        ArrowBehavior behavior = getBehavior();

        if ((!behavior.requireOnFire || this.isOnFire()) && this.blockCollisioned) {
            if (groundTicks >= behavior.groundBurnTicks) {
                this.setOnFire(false);
                this.setFireTicks(0);
                this.setOnFireFor(0);
            } else groundTicks++;
            applySmokeEffectsToNearbyEntities(serverWorld, behavior);
        }

    }

    private void spawnSmokeParticles(ServerWorld serverWorld, BlockHitResult blockHitResult, ArrowBehavior behavior) {
        BlockPos center = blockHitResult.getBlockPos();

        int particleCount = behavior.smoke.count;
        double maxSpeed = behavior.smoke.maxSpeed;
        ParticleEffect particle = ParticleTypes.CAMPFIRE_COSY_SMOKE;
        if (behavior.smoke.particle != null && Registries.PARTICLE_TYPE.containsId(behavior.smoke.particle)) {
            // Fallback to campfire smoke because resolving arbitrary particle types into ParticleEffect is non-trivial here
            particle = ParticleTypes.CAMPFIRE_COSY_SMOKE;
        }

        for (int i = 0; i < particleCount; i++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            double speed = random.nextDouble() * maxSpeed;

            double dx = Math.cos(angle);
            double dz = Math.sin(angle);

            serverWorld.spawnParticles(
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

    private void applySmokeEffectsToNearbyEntities(World world, ArrowBehavior behavior) {
        List<LivingEntity> entitiesInRange = world.getEntitiesByClass(
                LivingEntity.class,
                this.getBoundingBox().expand(behavior.aoeRadius),
                LivingEntity::isAlive
        );

        for (LivingEntity entity : entitiesInRange) {
            for (ArrowBehavior.EffectEntry e : behavior.effects) {
                StatusEffect effect = Registries.STATUS_EFFECT.get(e.id);
                if (effect != null) entity.addStatusEffect(new StatusEffectInstance(effect, e.duration, e.amplifier));
            }
        }
    }

    private void igniteNearbyBlocks(ServerWorld world, BlockPos hitPos, ArrowBehavior behavior) {
        Random random = new Random();

        for (BlockPos pos : BlockPos.iterateOutwards(hitPos, behavior.igniteBlocks.radiusX, behavior.igniteBlocks.radiusY, behavior.igniteBlocks.radiusZ)) {
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

            if (canPlaceFire && random.nextFloat() < behavior.igniteBlocks.chance) {
                world.setBlockState(pos, Blocks.FIRE.getDefaultState());
            }
        }
    }
}