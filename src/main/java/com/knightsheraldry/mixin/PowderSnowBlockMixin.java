package com.knightsheraldry.mixin;

import com.knightsheraldry.items.ModItems;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @ModifyReturnValue(method = "canWalkOnPowderSnow", at = @At("RETURN"))
    private static boolean preventSinkingWithCustomItem(boolean original, Entity entity) {
        return original || (entity instanceof PlayerEntity player &&
                player.getInventory().containsAny(stack -> stack.isOf(ModItems.GAMBESON_BOOTS.get())));
    }
}
