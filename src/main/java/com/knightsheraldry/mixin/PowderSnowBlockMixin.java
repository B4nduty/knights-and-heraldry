package com.knightsheraldry.mixin;

import com.knightsheraldry.items.ModItems;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @ModifyReturnValue(method = "canEntityWalkOnPowderSnow", at = @At("RETURN"))
    private static boolean preventSinkingWithCustomItem(boolean original, Entity entity) {
        return original || (entity instanceof Player player &&
                player.getInventory().hasAnyMatching(stack -> stack.is(ModItems.GAMBESON_BOOTS.get())));
    }
}