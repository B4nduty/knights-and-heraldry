package com.knightsheraldry.client;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.KHWeaponsTemplate;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class DistanceCrosshairOverlay implements HudRenderCallback {
    private static final Identifier TOO_FAR_CLOSE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/too_far_close.png");
    private static final Identifier SLASHING_EFFECTIVE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/slashing_effective.png");
    private static final Identifier SLASHING_CRITICAL = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/slashing_critical.png");
    private static final Identifier SLASHING_MAXIMUM = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/slashing_maximum.png");
    private static final Identifier BLUDGEONING_EFFECTIVE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/bludgeoning_effective.png");
    private static final Identifier BLUDGEONING_CRITICAL = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/bludgeoning_critical.png");
    private static final Identifier BLUDGEONING_MAXIMUM = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/bludgeoning_maximum.png");
    private static final Identifier PIERCING_EFFECTIVE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/piercing_effective.png");
    private static final Identifier PIERCING_CRITICAL = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/piercing_critical.png");
    private static final Identifier PIERCING_MAXIMUM = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/piercing_maximum.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if (player == null || player.getWorld() == null) {
            return;
        }

        Vec3d playerPos = player.getPos();
        double closestDistance = Double.MAX_VALUE;

        double distance;
        if (client.targetedEntity != null) {
            distance = playerPos.distanceTo(client.targetedEntity.getPos());
            if (distance < closestDistance) {
                closestDistance = distance;
            }
        }

        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack offHandStack = player.getOffHandStack();
        KHWeaponsTemplate weapon = null;

        if (mainHandStack.getItem() instanceof KHWeaponsTemplate) {
            weapon = (KHWeaponsTemplate) mainHandStack.getItem();
        } else if (offHandStack.getItem() instanceof KHWeaponsTemplate) {
            weapon = (KHWeaponsTemplate) offHandStack.getItem();
        }

        if (weapon != null) {
            int damageType = weapon.getDamageType(weapon.getDefaultStack());
            float[] damageValues = weapon.getDefaultAttackDamageValues();
            double[] radiusValues = weapon.getDefaultRadiusValues();

            if (damageType == 0) { // Bludgeoning
                renderBludgeoningOverlay(drawContext, closestDistance, radiusValues, damageValues);
            } else if (damageType == 1) { // Piercing
                renderPiercingOverlay(drawContext, closestDistance, radiusValues, damageValues);
            } else { // Slashing
                renderSlashingOverlay(drawContext, closestDistance, radiusValues, damageValues);
            }
        }

        RenderSystem.enableDepthTest();
    }

    private void renderBludgeoningOverlay(DrawContext drawContext, double distance, double[] radiusValues, float[] damageValues) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight();
        if (distance <= radiusValues[0]) {
            RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
            drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y / 2 - 1, 0, 0, 1, 1, 1, 1);
        } else if (distance <= radiusValues[1]) {
            RenderSystem.setShaderTexture(0, BLUDGEONING_MAXIMUM);
            drawContext.drawTexture(BLUDGEONING_MAXIMUM, x - 5, y / 2 - 5, 0, 0, 9, 9, 9, 9);
        } else if (distance <= radiusValues[2]) {
            RenderSystem.setShaderTexture(0, BLUDGEONING_CRITICAL);
            drawContext.drawTexture(BLUDGEONING_CRITICAL, x - 5, y / 2 - 5, 0, 0, 9, 9, 9, 9);
        } else if (distance <= radiusValues[3]) {
            RenderSystem.setShaderTexture(0, BLUDGEONING_MAXIMUM);
            drawContext.drawTexture(BLUDGEONING_MAXIMUM, x - 5, y / 2 - 5, 0, 0, 9, 9, 9, 9);
        } else if (distance <= radiusValues[4]) {
            RenderSystem.setShaderTexture(0, BLUDGEONING_EFFECTIVE);
            drawContext.drawTexture(BLUDGEONING_EFFECTIVE, x - 4, y / 2 - 4, 0, 0, 7, 7, 7, 7);
        } else {
            RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
            drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y / 2 - 1, 0, 0, 1, 1, 1, 1);
        }
    }

    private void renderPiercingOverlay(DrawContext drawContext, double distance, double[] radiusValues, float[] damageValues) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight();
        if (distance <= radiusValues[0]) {
            RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
            drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y / 2 - 1, 0, 0, 1, 1, 1, 1);
        } else if (distance <= radiusValues[1]) {
            RenderSystem.setShaderTexture(0, PIERCING_MAXIMUM);
            drawContext.drawTexture(PIERCING_MAXIMUM, x - 6, y / 2 - 6, 0, 0, 11, 11, 11, 11);
        } else if (distance <= radiusValues[2]) {
            RenderSystem.setShaderTexture(0, PIERCING_CRITICAL);
            drawContext.drawTexture(PIERCING_CRITICAL, x - 6, y / 2 - 6, 0, 0, 11, 11, 11, 11);
        } else if (distance <= radiusValues[3]) {
            RenderSystem.setShaderTexture(0, PIERCING_MAXIMUM);
            drawContext.drawTexture(PIERCING_MAXIMUM, x - 6, y / 2 - 6, 0, 0, 11, 11, 11, 11);
        } else if (distance <= radiusValues[4]) {
            RenderSystem.setShaderTexture(0, PIERCING_EFFECTIVE);
            drawContext.drawTexture(PIERCING_EFFECTIVE, x - 4, y / 2 - 4, 0, 0, 7, 7, 7, 7);
        } else {
            RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
            drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y / 2 - 1, 0, 0, 1, 1, 1, 1);
        }
    }

    private void renderSlashingOverlay(DrawContext drawContext, double distance, double[] radiusValues, float[] damageValues) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight();
        if (distance <= radiusValues[0]) {
            RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
            drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y / 2 - 1, 0, 0, 1, 1, 1, 1);
        } else if (distance <= radiusValues[1]) {
            RenderSystem.setShaderTexture(0, SLASHING_MAXIMUM);
            drawContext.drawTexture(SLASHING_MAXIMUM, x - 5, y / 2 - 5, 0, 0, 9, 9, 9, 9);
        } else if (distance <= radiusValues[2]) {
            RenderSystem.setShaderTexture(0, SLASHING_CRITICAL);
            drawContext.drawTexture(SLASHING_CRITICAL, x - 5, y / 2 - 5, 0, 0, 9, 9, 9, 9);
        } else if (distance <= radiusValues[3]) {
            RenderSystem.setShaderTexture(0, SLASHING_MAXIMUM);
            drawContext.drawTexture(SLASHING_MAXIMUM, x - 5, y / 2 - 5, 0, 0, 9, 9, 9, 9);
        } else if (distance <= radiusValues[4]) {
            RenderSystem.setShaderTexture(0, SLASHING_EFFECTIVE);
            drawContext.drawTexture(SLASHING_EFFECTIVE, x - 4, y / 2 - 4, 0, 0, 7, 7, 7, 7);
        } else {
            RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
            drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y / 2 - 1, 0, 0, 1, 1, 1, 1);
        }
    }
}
