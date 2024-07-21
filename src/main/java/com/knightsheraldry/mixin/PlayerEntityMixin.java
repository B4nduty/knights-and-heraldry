package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.StaminaData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IEntityDataSaver {
    @Unique
    private NbtCompound persistentData;

    @Override
    public NbtCompound bsroleplay$getPersistentData() {
        if(this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfo ci) {
        if(persistentData != null) {
            nbt.put("bsroleplay.data", persistentData);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("bsroleplay.data", 10)) {
            persistentData = nbt.getCompound("bsroleplay.data");
        }
    }

    @Inject(method = "damageShield", at = @At("HEAD"))
    private void knightsHeraldry$onDamageShield(float amount, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        int stamina = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getInt("stamina_int");
        int staminaCost = 20;

        if (KnightsHeraldry.CONFIG.common.getBlocking) {
            if (stamina >= staminaCost) {
                StaminaData.removeStamina((IEntityDataSaver) player, staminaCost);
                player.sendMessage(Text.literal("Shield block performed! Stamina remaining: " + (stamina - staminaCost)), true);
            } else {
                player.sendMessage(Text.literal("Not enough stamina to block!"), true);
            }
        }
    }
}