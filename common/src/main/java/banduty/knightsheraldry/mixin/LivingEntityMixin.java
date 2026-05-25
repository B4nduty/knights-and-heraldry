package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.effect.KHEffects;
import banduty.stoneycore.util.data.entitydata.IEntityDataSaver;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract void calculateEntityAnimation(boolean flutter);

    @Unique
    private int stuckSwallowTailArrowTimer;

    @Unique
    private static final int SWALLOWTAIL_ARROW_COOLDOWN = 20 * 30;

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$travel(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (!livingEntity.hasEffect(KHEffects.PIN)) return;
        ci.cancel();
        if (!livingEntity.onGround()) {
            double vy = livingEntity.getDeltaMovement().y;
            vy -= 0.08D; vy *= 1.225D;

            double maxDown = -4.9D;
            if (vy < maxDown) vy = maxDown;

            livingEntity.setDeltaMovement(0, vy, 0);
            livingEntity.move(MoverType.SELF, livingEntity.getDeltaMovement());
            this.calculateEntityAnimation(livingEntity instanceof FlyingAnimal);
            return;
        }
        livingEntity.setDeltaMovement(0, 0, 0);
        livingEntity.move(MoverType.SELF, livingEntity.getDeltaMovement());
        this.calculateEntityAnimation(livingEntity instanceof FlyingAnimal);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void knightsheraldry$tick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity instanceof Player player) {
            handleSwallowtailArrowTimer(player);
        }
    }

    @Unique
    private void handleSwallowtailArrowTimer(Player player) {
        if (!((IEntityDataSaver) player).stoneycore$getPersistentData().contains("swallowtailArrowCount")) {
            return;
        }

        int swallowtailArrowCount = ((IEntityDataSaver) player).stoneycore$getPersistentData().getInt("swallowtailArrowCount");

        if (swallowtailArrowCount > 0) {
            int stuckArrows = player.getArrowCount();
            if (stuckArrows > 0) {
                if (this.stuckSwallowTailArrowTimer <= 0) {
                    this.stuckSwallowTailArrowTimer = SWALLOWTAIL_ARROW_COOLDOWN - (20 * stuckArrows);
                }

                if (--this.stuckSwallowTailArrowTimer <= 0) {
                    int nextCount = swallowtailArrowCount - 1;
                    if (nextCount <= 0) {
                        // Completely purge the NBT key when it reaches zero so data serialization saves clean empty states
                        ((IEntityDataSaver) player).stoneycore$getPersistentData().remove("swallowtailArrowCount");
                    } else {
                        ((IEntityDataSaver) player).stoneycore$getPersistentData().putInt("swallowtailArrowCount", nextCount);
                    }
                }
            }
        } else if (swallowtailArrowCount == 0) {
            ((IEntityDataSaver) player).stoneycore$getPersistentData().remove("swallowtailArrowCount");
        }
    }
}