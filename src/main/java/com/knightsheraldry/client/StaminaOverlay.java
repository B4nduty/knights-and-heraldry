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
    private static final int TOTAL_STAMINA = 200;
    private static final int EMPTY_STAMINA_WIDTH = 9;
    private static final int EMPTY_STAMINA_HEIGHT = 9;
    private static final int STAMINA_BAR_WIDTH = 9;
    private static final int STAMINA_BAR_HEIGHT = 9;
    private static final int STAMINA_UNIT_SIZE = 8;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        int stamina = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getInt("stamina_int");
        boolean staminaBlocked = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getBoolean("stamina_blocked");
        boolean ableStamina = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getBoolean("able_stamina");

        if (ableStamina && !player.isSpectator()) {
            int x = getStaminaBarXPosition();
            int y = getStaminaBarYPosition(player);
            renderStaminaBar(drawContext, x, y, stamina, staminaBlocked);
        }
    }

    private int getStaminaBarXPosition() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            return client.getWindow().getScaledWidth() / 2;
        }
        return 0;
    }

    private int getStaminaBarYPosition(ClientPlayerEntity player) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int height = client.getWindow().getScaledHeight();
            return player.isSubmergedInWater() ? height - 59 : height - 49;
        }
        return 0;
    }

    private void renderStaminaBar(DrawContext drawContext, int x, int y, int stamina, boolean staminaBlocked) {
        for (int i = 0; i < 10; i++) {
            renderEmptyStamina(drawContext, x + 82 - (i * STAMINA_UNIT_SIZE), y);
        }

        for (int i = 0; i < TOTAL_STAMINA; i++) {
            if (stamina > i) {
                if (staminaBlocked) {
                    renderBlockedStamina(drawContext, x + 82 - (i / 20 * STAMINA_UNIT_SIZE), y);
                } else {
                    renderFilledStamina(drawContext, x + 82 - (i / 20 * STAMINA_UNIT_SIZE), y);
                }
            } else {
                break;
            }
        }
    }

    private void renderEmptyStamina(DrawContext drawContext, int x, int y) {
        RenderSystem.disableDepthTest();
        RenderSystem.setShaderTexture(0, STAMINA_EMPTY);
        drawContext.drawTexture(STAMINA_EMPTY, x, y, 0, 0, EMPTY_STAMINA_WIDTH, EMPTY_STAMINA_HEIGHT, EMPTY_STAMINA_WIDTH, EMPTY_STAMINA_HEIGHT);
        RenderSystem.enableDepthTest();
    }

    private void renderFilledStamina(DrawContext drawContext, int x, int y) {
        RenderSystem.disableDepthTest();
        RenderSystem.setShaderTexture(0, STAMINA);
        drawContext.drawTexture(STAMINA, x, y, 0, 0, STAMINA_BAR_WIDTH, STAMINA_BAR_HEIGHT, STAMINA_BAR_WIDTH, STAMINA_BAR_HEIGHT);
        RenderSystem.enableDepthTest();
    }

    private void renderBlockedStamina(DrawContext drawContext, int x, int y) {
        RenderSystem.setShaderTexture(0, STAMINA_BLOCKED);
        drawContext.drawTexture(STAMINA_BLOCKED, x, y, 0, 0, STAMINA_BAR_WIDTH, STAMINA_BAR_HEIGHT, STAMINA_BAR_WIDTH, STAMINA_BAR_HEIGHT);
        RenderSystem.enableDepthTest();
    }
}