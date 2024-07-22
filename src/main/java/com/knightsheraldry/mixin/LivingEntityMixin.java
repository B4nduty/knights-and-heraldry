package com.knightsheraldry.mixin;

import com.knightsheraldry.networking.ModMessages;
import com.knightsheraldry.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void onJump(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object) this;
        if (entity instanceof PlayerEntity playerEntity
                && ((IEntityDataSaver) playerEntity).knightsheraldry$getPersistentData().getBoolean("stamina_blocked")) {
            ci.cancel();
        }
    }

    @Inject(method = "setSprinting", at = @At("HEAD"), cancellable = true)
    private void onSprinting(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object) this;
        if (entity instanceof PlayerEntity playerEntity
                && ((IEntityDataSaver) playerEntity).knightsheraldry$getPersistentData().getBoolean("stamina_blocked")) {
            ci.cancel();
        }
    }

    @Inject(method = "setCurrentHand", at = @At("HEAD"), cancellable = true)
    private void onSetCurrentHand(Hand hand, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object) this;
        if (entity instanceof PlayerEntity playerEntity
                && ((IEntityDataSaver) playerEntity).knightsheraldry$getPersistentData().getBoolean("stamina_blocked")) {
            ci.cancel();
        }
    }

    @Inject(method = "onAttacking", at = @At("HEAD"))
    private void onAttacking(Entity target, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object) this;
        if (entity instanceof PlayerEntity playerEntity
                && !playerEntity.isSpectator()) {
            boolean staminaBlocked = ((IEntityDataSaver) playerEntity).knightsheraldry$getPersistentData().getBoolean("stamina_blocked");
            int stamina = ((IEntityDataSaver) playerEntity).knightsheraldry$getPersistentData().getInt("stamina_int");

            int staminaCost = 10;
            if (stamina >= staminaCost && !staminaBlocked) {
                ClientPlayNetworking.send(ModMessages.ATTACK_ID, PacketByteBufs.create());
                playerEntity.sendMessage(Text.literal("Stamina: " + stamina));
            }
        }
    }
}
