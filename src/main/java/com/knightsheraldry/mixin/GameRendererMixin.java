package com.knightsheraldry.mixin;

import com.knightsheraldry.items.custom.item.KHWeapons;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final MinecraftClient client;

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 3.0))
    public double knightsheraldry$updateTargetedEntity3(double constant) {
        return client.player != null && client.player.getMainHandStack().getItem() instanceof KHWeapons khWeapons ?
                khWeapons.getMaxDistance() : constant;
    }

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 6.0))
    public double knightsheraldry$updateTargetedEntity6(double constant) {
        return client.player != null && client.player.getMainHandStack().getItem() instanceof KHWeapons khWeapons ?
                khWeapons.getMaxDistance() : constant;
    }

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 9.0))
    public double knightsheraldry$updateTargetedEntity9(double constant) {
        return client.player != null && client.player.getMainHandStack().getItem() instanceof KHWeapons khWeapons ?
                MathHelper.square(khWeapons.getMaxDistance()) : constant;
    }
}