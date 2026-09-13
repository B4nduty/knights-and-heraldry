package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.ai.FirearmAttack;
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
        if (!FirearmAttack.isHoldingFirearm(mob)) return;

        if (FirearmAttack.isInMeleeRange(mob) || FirearmAttack.isMeleeCommitted(mob)) return;

        cir.setReturnValue(true);
    }
}