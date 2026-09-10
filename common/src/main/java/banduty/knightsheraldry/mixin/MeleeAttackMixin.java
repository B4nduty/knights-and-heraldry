package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.items.KHItems;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.MeleeAttack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MeleeAttack.class)
public abstract class MeleeAttackMixin {
    @Inject(method = "isHoldingUsableProjectileWeapon", at = @At("HEAD"), cancellable = true, remap = false)
    private static void knightsheraldry$treatFirearmAsProjectileWeapon(Mob mob, CallbackInfoReturnable<Boolean> cir) {
        if (mob.isHolding(KHItems.ARQUEBUS.get()) || mob.isHolding(KHItems.HANDGONNE.get())) {
            cir.setReturnValue(true);
        }
    }
}