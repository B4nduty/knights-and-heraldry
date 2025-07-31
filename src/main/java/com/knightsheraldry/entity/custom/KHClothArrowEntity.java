package com.knightsheraldry.entity.custom;

import banduty.stoneycore.entity.custom.SCArrowEntity;
import com.knightsheraldry.entity.ModEntities;
import com.knightsheraldry.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.List;

public class KHClothArrowEntity extends SCArrowEntity {

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
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof LivingEntity target) {
            scHitEntity(target, new ItemStack(ModItems.CLOTH_ARROW.get()), getDamageAmount());
        }
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = this.getWorld();

        if (this.isOnFire() && world instanceof ServerWorld serverWorld) {
            spawnSmokeParticles(serverWorld, blockHitResult);
            applyWitherEffectToNearbyEntities(world);
        }

        super.onBlockHit(blockHitResult);
    }

    private void spawnSmokeParticles(ServerWorld serverWorld, BlockHitResult blockHitResult) {
        for (int i = 0; i < 20; i++) {
            double xOffset = (random.nextDouble() - 0.5) * 0.5;
            double yOffset = random.nextDouble() * 0.5;
            double zOffset = (random.nextDouble() - 0.5) * 0.5;

            serverWorld.spawnParticles(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    blockHitResult.getPos().x,
                    blockHitResult.getPos().y + 0.5,
                    blockHitResult.getPos().z,
                    1, xOffset, yOffset, zOffset, 0.01
            );
        }
    }

    private void applyWitherEffectToNearbyEntities(World world) {
        List<LivingEntity> entitiesInRange = world.getEntitiesByClass(
                LivingEntity.class,
                this.getBoundingBox().expand(5.0),
                entity -> entity.isAlive() && entity != this.getOwner()
        );

        for (LivingEntity entity : entitiesInRange) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1));
        }
    }
}