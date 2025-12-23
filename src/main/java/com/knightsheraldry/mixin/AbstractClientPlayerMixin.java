package com.knightsheraldry.mixin;

import com.knightsheraldry.items.ModItems;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    public AbstractClientPlayerMixin(Level level, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(level, pos, yaw, gameProfile);
    }

    @Inject(method = "getFieldOfViewModifier", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void knightsheraldry$getFovMultiplierMixin(CallbackInfoReturnable<Float> cir, float f) {
        ItemStack itemStack = this.getUseItem();
        if (this.isUsingItem() && itemStack.is(ModItems.LONGBOW.get())) {
            int i = this.getUseItemRemainingTicks();
            float g = (float)i / 20.0f;
            g = g > 1.0f ? 1.0f : g * g;
            f *= 1.0f - g * 0.15f;
            cir.setReturnValue(Mth.lerp(Minecraft.getInstance().options.fovEffectScale().get().floatValue(), 1.0f, f));
        }
    }
}
