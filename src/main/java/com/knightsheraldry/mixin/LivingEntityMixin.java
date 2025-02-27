package com.knightsheraldry.mixin;

import banduty.stoneycore.util.playerdata.IEntityDataSaver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Unique
    private int stuckSwallowTailArrowTimer;

    @Unique
    private static final int SWALLOWTAIL_ARROW_COOLDOWN = 20 * 30;
    @Unique
    private static final int SWALLOWTAIL_ARROW_DECREMENT = 1;

    @Inject(method = "tick", at = @At("HEAD"))
    public void knightsheraldry$tick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity instanceof PlayerEntity player) {
            handleSwallowtailArrowTimer(player);
        }
    }

    @Unique
    private void handleSwallowtailArrowTimer(PlayerEntity player) {
        NbtCompound nbt = ((IEntityDataSaver) player).stoneycore$getPersistentData();
        int swallowtailArrowCount = nbt.getInt("swallowtail_arrow_count");

        if (swallowtailArrowCount >= 0) {
            int stuckArrows = player.getStuckArrowCount();
            if (stuckArrows > 0) {
                if (this.stuckSwallowTailArrowTimer <= 0) {
                    this.stuckSwallowTailArrowTimer = SWALLOWTAIL_ARROW_COOLDOWN - (20 * stuckArrows);
                }

                if (--this.stuckSwallowTailArrowTimer <= 0) {
                    nbt.putInt("swallowtail_arrow_count", swallowtailArrowCount - SWALLOWTAIL_ARROW_DECREMENT);
                }
            }
        }
    }
}