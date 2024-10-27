package com.knightsheraldry.mixin;

import com.knightsheraldry.items.custom.item.KHWeapons;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Unique
    private final MinecraftClient client = MinecraftClient.getInstance();

    @Inject(method = "updateTargetedEntity", at = @At("HEAD"), cancellable = true)
    private void knightsheraldry$updateTargetedEntity(float tickDelta, CallbackInfo ci) {
        Entity entity = this.client.getCameraEntity();
        if (entity != null) {
            if (this.client.world != null) {
                this.client.getProfiler().push("pick");
                this.client.targetedEntity = null;
                double reachDistance = getReachDistance(entity);

                this.client.crosshairTarget = entity.raycast(reachDistance, tickDelta, false);
                Vec3d cameraPos = entity.getCameraPosVec(tickDelta);
                double reachDistanceSquared = reachDistance * reachDistance;

                if (this.client.crosshairTarget != null) {
                    reachDistanceSquared = this.client.crosshairTarget.getPos().squaredDistanceTo(cameraPos);
                }

                Vec3d rotationVec = entity.getRotationVec(1.0F);
                Vec3d reachVec = cameraPos.add(rotationVec.x * reachDistance, rotationVec.y * reachDistance, rotationVec.z * reachDistance);
                Box box = entity.getBoundingBox().stretch(rotationVec.multiply(reachDistance)).expand(1.0, 1.0, 1.0);
                EntityHitResult entityHitResult = ProjectileUtil.raycast(entity, cameraPos, reachVec, box, (target) -> !target.isSpectator() && target.canHit(), reachDistanceSquared);

                if (entityHitResult != null) {
                    Entity hitEntity = entityHitResult.getEntity();
                    Vec3d hitPos = entityHitResult.getPos();
                    double hitDistance = cameraPos.squaredDistanceTo(hitPos);
                    if (hitDistance < reachDistanceSquared || this.client.crosshairTarget == null) {
                        this.client.crosshairTarget = entityHitResult;
                        if (hitEntity instanceof LivingEntity || hitEntity instanceof ItemFrameEntity) {
                            this.client.targetedEntity = hitEntity;
                        }
                    }
                }

                this.client.getProfiler().pop();
                ci.cancel();
            }
        }
    }

    @Unique
    private double getReachDistance(Entity entity) {
        double reachDistance = 0;
        if (this.client.interactionManager != null) {
            reachDistance = this.client.interactionManager.getReachDistance();
        }

        ItemStack mainHandStack = entity instanceof ClientPlayerEntity ? ((ClientPlayerEntity) entity).getMainHandStack() : ItemStack.EMPTY;
        if (mainHandStack.getItem() instanceof KHWeapons) {
            reachDistance *= 4;
        }
        return reachDistance;
    }
}