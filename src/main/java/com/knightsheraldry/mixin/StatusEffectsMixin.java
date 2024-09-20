package com.knightsheraldry.mixin;

import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StatusEffects.class)
public abstract class StatusEffectsMixin {
    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = -4.0))
    private static double removeWeakness(double constant) {
        return 0.0d;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = 3.0))
    private static double removeStrength(double constant) {
        return 0.0d;
    }


}
