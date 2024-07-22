package com.knightsheraldry.client;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.util.ModTags;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class DistanceCrosshairOverlay implements HudRenderCallback {
    private static final Identifier TOO_FAR_CLOSE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/too_far_close.png");
    private static final Identifier SLASHING_EFFECTIVE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/slashing_effective.png");
    private static final Identifier SLASHING_CRITICAL = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/slashing_critical.png");
    private static final Identifier SLASHING_MAXIMUM = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/slashing_maximum.png");
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null || player.getWorld() == null) {
            return;
        }

        Vec3d playerPos = player.getPos();

        double closestDistance = Double.MAX_VALUE;

        double distance = 0;
        if (MinecraftClient.getInstance().targetedEntity != null) distance = playerPos.distanceTo(MinecraftClient.getInstance().targetedEntity.getPos());
        if (distance < closestDistance) {
            closestDistance = distance;
        }

        if (!player.isSpectator() && (player.getMainHandStack().isIn(ModTags.Items.WARSWORD) ||
                player.getOffHandStack().isIn(ModTags.Items.WARSWORD))) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null) {
                int width = client.getWindow().getScaledWidth();
                int height = client.getWindow().getScaledHeight();

                x = width / 2;
                y = height;
            }

            if (closestDistance <= 1.0d) {
                RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
                drawContext.drawTexture(TOO_FAR_CLOSE, x, y/2, 0, 0, 1, 1, 1, 1);
            } else if (closestDistance <= 2.0d) {
                RenderSystem.setShaderTexture(0, SLASHING_MAXIMUM);
                drawContext.drawTexture(SLASHING_MAXIMUM, x - 5, y/2 - 5, 0, 0, 9, 9, 9, 9);
            }  else if (closestDistance <= 2.5d) {
                RenderSystem.setShaderTexture(0, SLASHING_CRITICAL);
                drawContext.drawTexture(SLASHING_CRITICAL, x - 5, y/2 - 5, 0, 0, 9, 9, 9, 9);
            }  else if (closestDistance <= 3.5d) {
                RenderSystem.setShaderTexture(0, SLASHING_MAXIMUM);
                drawContext.drawTexture(SLASHING_MAXIMUM, x - 5, y/2 - 5, 0, 0, 9, 9, 9, 9);
            } else if (closestDistance <= 4.0d) {
                RenderSystem.setShaderTexture(0, SLASHING_EFFECTIVE);
                drawContext.drawTexture(SLASHING_EFFECTIVE, x - 4, y/2 - 4, 0, 0, 7, 7, 7, 7);
            } else {
                RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
                drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y/2 - 1, 0, 0, 1, 1, 1, 1);
            }

            RenderSystem.disableDepthTest();

            RenderSystem.enableDepthTest();
        }
    }
}
