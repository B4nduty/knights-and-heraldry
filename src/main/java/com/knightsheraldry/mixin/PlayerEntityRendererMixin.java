package com.knightsheraldry.mixin;

import com.knightsheraldry.util.itemdata.KHTags;
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
        if (player.getMainHandStack().getNbt() != null && (player.getMainHandStack().getNbt().getBoolean("Charged")
                || player.getMainHandStack().getNbt().getBoolean("Shoot"))) {
            callbackInfoReturnable.setReturnValue(BipedEntityModel.ArmPose.BOW_AND_ARROW);
        }

        if (player.isUsingItem() && player.getActiveItem() == player.getMainHandStack() && player.getMainHandStack().isIn(KHTags.WEAPONS_SHIELD.getTag())) {
            callbackInfoReturnable.setReturnValue(BipedEntityModel.ArmPose.BLOCK);
        }
    }
}