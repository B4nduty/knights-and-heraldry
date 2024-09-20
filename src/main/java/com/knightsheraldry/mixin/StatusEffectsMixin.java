package com.knightsheraldry.mixin;

import com.knightsheraldry.items.custom.item.KHWeapons;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StatusEffects.class)
public abstract class StatusEffectsMixin {
    @Unique
    private static boolean hasKHWeapon(LivingEntity entity) {
        if (entity instanceof ServerPlayerEntity player) {
            ItemStack mainHandItem = player.getMainHandStack();
            if (mainHandItem.getItem() instanceof KHWeapons khWeapons) {

            }
        }
        return false;
    }



    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = -4.0))
    private static double onStaticInit(double constant) {
        return constant;
    }


}
