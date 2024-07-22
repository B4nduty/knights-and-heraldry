package com.knightsheraldry.mixin;

import com.knightsheraldry.items.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void renderCrosshair(DrawContext context, CallbackInfo ci) {
        List<Item> itemsList = new ArrayList<>();
        itemsList.add(ModItems.WARSWORD);
        itemsList.add(ModItems.WARSWORD_CLAYMORE);
        itemsList.add(ModItems.WARSWORD_FLAMBERGE);
        itemsList.add(ModItems.WARSWORD_ZWEIHANDER);
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && (itemsList.contains((player.getMainHandStack().getItem())) ||
                itemsList.contains((player.getOffHandStack().getItem())))) {
            ci.cancel();
        }
    }
}
