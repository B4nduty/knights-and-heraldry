package com.knightsheraldry.client;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.util.IEntityDataSaver;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;

public class StaminaOverlay implements HudRenderCallback {
    private static final Identifier STAMINA = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/stamina_bar.png");
    private static final Identifier STAMINA_EMPTY = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/stamina_bar_empty.png");
    private static final Identifier STAMINA_BLOCKED = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/stamina_bar_blocked.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        int stamina = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getInt("stamina_int");
        boolean staminaBlocked = ((IEntityDataSaver) player).bsroleplay$getPersistentData().getBoolean("stamina_blocked");
        if (!player.isSpectator()) {
            int totalStamina = 200;
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null) {
                int width = client.getWindow().getScaledWidth();
                int height = client.getWindow().getScaledHeight();

                x = width / 2;
                y = height;
            }

            for(int i = 0; i < 10; i++) {
                RenderSystem.disableDepthTest();
                RenderSystem.setShaderTexture(0, STAMINA_EMPTY);
                drawContext.drawTexture(STAMINA_EMPTY,x + 82 - (i * 8),y - 49,0,0,9,9,
                        9,9);

                RenderSystem.enableDepthTest();
            }

            for(int i = 0; i < totalStamina; i++) {
                if (stamina > i && staminaBlocked) {
                    RenderSystem.disableDepthTest();
                    RenderSystem.setShaderTexture(0, STAMINA_BLOCKED);
                    drawContext.drawTexture(STAMINA_BLOCKED,x + 82 - (i/20 * 8),y - 49,0,0,9,9,
                            9,9);

                    RenderSystem.enableDepthTest();
                } else if(stamina > i) {
                    RenderSystem.disableDepthTest();
                    RenderSystem.setShaderTexture(0, STAMINA);
                    drawContext.drawTexture(STAMINA,x + 82 - (i/20 * 8),y - 49,0,0,9,9,
                            9,9);

                    RenderSystem.enableDepthTest();
                } else {
                    break;
                }
            }
        }
    }
}