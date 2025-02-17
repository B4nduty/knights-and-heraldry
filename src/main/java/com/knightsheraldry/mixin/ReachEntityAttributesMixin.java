package com.knightsheraldry.mixin;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.knightsheraldry.items.custom.item.KHWeapon;
import com.knightsheraldry.util.weaponutil.KHWeaponUtil;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ReachEntityAttributes.class)
public class ReachEntityAttributesMixin  {
    @Inject(method = "getReachDistance", at = @At("HEAD"), cancellable = true)
    private static void knightsheraldry$getReachDistance(LivingEntity entity, double baseReachDistance, CallbackInfoReturnable<Double> cir) {
        if (entity.getMainHandStack().getItem() instanceof KHWeapon khWeapon) {
            var reachDistance = KHWeaponUtil.getMaxDistance(khWeapon);
            cir.setReturnValue(reachDistance);
        }
    }
}
