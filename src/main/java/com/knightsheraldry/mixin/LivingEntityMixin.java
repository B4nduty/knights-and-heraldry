package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.ModTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow @Nullable public abstract DamageSource getRecentDamageSource();

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


    @Inject(method = "disablesShield", at = @At("HEAD"), cancellable = true)
    public void disablesShield(CallbackInfoReturnable<Boolean> cir) {
        ItemStack mainStack = ((LivingEntity) (Object) this).getMainHandStack();
        boolean isWeaponOrInTag = mainStack.getItem() instanceof AxeItem
                || mainStack.isIn(ModTags.Items.KH_WEAPONS_DISABLE_SHIELD);

        if (KnightsHeraldry.config().getVanillaWeaponsDamage0()) {
            cir.setReturnValue(mainStack.isIn(ModTags.Items.KH_WEAPONS_DISABLE_SHIELD));
        } else {
            cir.setReturnValue(isWeaponOrInTag);
        }
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void injectDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof PlayerEntity attacker) {
            if (attacker.getMainHandStack().isIn(ModTags.Items.KH_WEAPONS_BYPASS_BLOCK)) {
                this.blockShield = false;
            }
        }
    }


    @Inject(method = "damage", at = @At("TAIL"))
    private void sendDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof PlayerEntity playerAttacker
                && playerAttacker.getMainHandStack().isIn(ModTags.Items.KH_WEAPONS)) {
            playerAttacker.sendMessage(Text.literal("Damage: " + amount), true);
        }
    }

    @Unique
    private boolean blockShield = true;

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;blockedByShield(Lnet/minecraft/entity/damage/DamageSource;)Z"))
    private boolean redirectBlockedByShield(LivingEntity instance, DamageSource source) {
        return blockShield && instance.blockedByShield(source);
    }
}