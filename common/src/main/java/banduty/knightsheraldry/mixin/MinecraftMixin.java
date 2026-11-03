package banduty.knightsheraldry.mixin;

import banduty.knightsheraldry.combat.OffhandAttack;
import banduty.knightsheraldry.platform.ClientServices;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Vanilla dual wielding: with dual-wieldable weapons in both hands, entity hits and air swings alternate
 * between the main hand and the offhand. Block breaking always uses the main hand.
 */
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow @Nullable public LocalPlayer player;
    @Shadow @Nullable public HitResult hitResult;

    @Unique private boolean knightsheraldry$nextSwingOffhand;
    @Unique private boolean knightsheraldry$swingOffhand;

    @Unique
    private boolean knightsheraldry$alternating() {
        return this.player != null && this.hitResult != null
                && this.hitResult.getType() != HitResult.Type.BLOCK
                && OffhandAttack.canDualWield(this.player);
    }

    @Inject(method = "startAttack", at = @At("HEAD"))
    private void knightsheraldry$pickAttackHand(CallbackInfoReturnable<Boolean> cir) {
        this.knightsheraldry$swingOffhand = this.knightsheraldry$nextSwingOffhand && knightsheraldry$alternating();
    }

    @WrapOperation(method = "startAttack", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;attack(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)V"))
    private void knightsheraldry$offhandAttack(MultiPlayerGameMode gameMode, Player player, Entity target, Operation<Void> original) {
        if (!this.knightsheraldry$swingOffhand) {
            original.call(gameMode, player, target);
            return;
        }
        ((MultiPlayerGameModeAccessor) gameMode).knightsheraldry$ensureHasSentCarriedItem();
        ClientServices.getClientHelper().sendOffhandAttack(target.getId(), player.isShiftKeyDown());
        player.resetAttackStrengthTicker();
    }

    @WrapOperation(method = "startAttack", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;swing(Lnet/minecraft/world/InteractionHand;)V"))
    private void knightsheraldry$offhandSwing(LocalPlayer player, InteractionHand hand, Operation<Void> original) {
        if (knightsheraldry$alternating()) {
            original.call(player, this.knightsheraldry$swingOffhand ? InteractionHand.OFF_HAND : hand);
            this.knightsheraldry$nextSwingOffhand = !this.knightsheraldry$swingOffhand;
        } else {
            original.call(player, hand);
            this.knightsheraldry$nextSwingOffhand = false;
        }
        this.knightsheraldry$swingOffhand = false;
    }
}
