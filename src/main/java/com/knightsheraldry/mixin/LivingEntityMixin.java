package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.item.Lance;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.ModTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float modifyDamageAmount(float amount, DamageSource source) {
        if (source.getAttacker() instanceof PlayerEntity playerEntity) {
            if (amount > 1 && playerEntity.hasStatusEffect(StatusEffects.STRENGTH)) {
                int amplifier = playerEntity.getStatusEffect(StatusEffects.STRENGTH).getAmplifier();
                amount += (float) (3 * (amplifier + 1));
            }

            if (playerEntity.hasStatusEffect(StatusEffects.WEAKNESS)) {
                int amplifier = playerEntity.getStatusEffect(StatusEffects.WEAKNESS).getAmplifier();
                amount -= (float) (4 * (amplifier + 1));
            }

            if (amount <= 0) amount = 0;
        }

        return amount;
    }


    @Inject(method = "applyDamage", at = @At("TAIL"))
    private void sendDamage(DamageSource source, float amount, CallbackInfo ci) {
        if (source.getAttacker() instanceof PlayerEntity playerEntity
                && playerEntity.getMainHandStack().isIn(ModTags.Items.KH_WEAPONS)) {
            if (!playerEntity.hasStatusEffect(StatusEffects.WEAKNESS)) {
                if (amount <= 0) amount = 0;
                else if (!(playerEntity.getMainHandStack().getItem() instanceof Lance)) amount = amount + 1;
            }
            int amountInt = (int) (amount * 10);
            playerEntity.sendMessage(Text.literal("Damage: " + (((float) amountInt) / 10)), true);
        }
    }

    @Unique
    private boolean blockShield = true;

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;blockedByShield(Lnet/minecraft/entity/damage/DamageSource;)Z"))
    private boolean redirectBlockedByShield(LivingEntity instance, DamageSource source) {
        return blockShield && instance.blockedByShield(source);
    }
}