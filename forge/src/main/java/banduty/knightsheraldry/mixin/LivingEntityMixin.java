package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.animal.FlyingAnimal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract void calculateEntityAnimation(boolean flutter);

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    public void knightsheraldry$travel(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (!livingEntity.hasEffect(ModEffects.PIN.get())) return;

        ci.cancel();

        if (!livingEntity.onGround()) {
            double vy = livingEntity.getDeltaMovement().y;
            vy -= 0.08D;
            vy *= 1.225D;

            double maxDown = -4.9D;
            if (vy < maxDown) vy = maxDown;

            livingEntity.setDeltaMovement(0, vy, 0);
        } else {
            livingEntity.setDeltaMovement(0, 0, 0);
        }

        livingEntity.move(MoverType.SELF, livingEntity.getDeltaMovement());
        this.calculateEntityAnimation(livingEntity instanceof FlyingAnimal);
    }
}