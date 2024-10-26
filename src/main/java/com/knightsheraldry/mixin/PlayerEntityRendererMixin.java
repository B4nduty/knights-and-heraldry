package com.knightsheraldry.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(method = "getArmPose", at = @At("RETURN"), cancellable = true)
    private static void knightsheraldry$getArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> callbackInfoReturnable) {
        if (player.getStackInHand(hand).getNbt() != null && (player.getStackInHand(hand).getNbt().getBoolean("Charged")
                || player.getStackInHand(hand).getNbt().getBoolean("Shoot"))) {
            callbackInfoReturnable.setReturnValue(BipedEntityModel.ArmPose.BOW_AND_ARROW);
        }
    }
}