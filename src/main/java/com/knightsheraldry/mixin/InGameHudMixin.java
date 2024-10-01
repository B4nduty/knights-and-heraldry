package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.items.custom.item.KHWeapons;
import com.knightsheraldry.util.IEntityDataSaver;
import com.knightsheraldry.util.ModTags;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.emi.trinkets.api.TrinketsApi;
import net.bettercombat.logic.PlayerAttackProperties;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Unique
    private static final Identifier TOO_FAR_CLOSE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/too_far_close.png");
    @Unique
    private static final Identifier SLASHING_EFFECTIVE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/slashing_effective.png");
    @Unique
    private static final Identifier SLASHING_CRITICAL = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/slashing_critical.png");
    @Unique
    private static final Identifier SLASHING_MAXIMUM = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/slashing_maximum.png");
    @Unique
    private static final Identifier BLUDGEONING_EFFECTIVE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/bludgeoning_effective.png");
    @Unique
    private static final Identifier BLUDGEONING_CRITICAL = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/bludgeoning_critical.png");
    @Unique
    private static final Identifier BLUDGEONING_MAXIMUM = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/bludgeoning_maximum.png");
    @Unique
    private static final Identifier PIERCING_EFFECTIVE = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/piercing_effective.png");
    @Unique
    private static final Identifier PIERCING_CRITICAL = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/piercing_critical.png");
    @Unique
    private static final Identifier PIERCING_MAXIMUM = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/piercing_maximum.png");

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void renderCrosshair(DrawContext context, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.getWorld() != null && (player.getMainHandStack().getItem() instanceof KHWeapons ||
                player.getOffHandStack().getItem() instanceof KHWeapons)) {
            Vec3d playerPos = player.getPos();
            double closestDistance = Double.MAX_VALUE;

            double distance;
            if (MinecraftClient.getInstance().targetedEntity == null) distance = 9999;
            else distance = playerPos.distanceTo(MinecraftClient.getInstance().targetedEntity.getPos());
            if (distance < closestDistance) {
                closestDistance = distance;
            }

            ItemStack mainHandStack = player.getMainHandStack();
            ItemStack offHandStack = player.getOffHandStack();
            KHWeapons weapon = null;

            if (mainHandStack.getItem() instanceof KHWeapons) {
                weapon = (KHWeapons) mainHandStack.getItem();
            } else if (offHandStack.getItem() instanceof KHWeapons) {
                weapon = (KHWeapons) offHandStack.getItem();
            }

            if (weapon != null) {
                boolean bludgeoning = player.getMainHandStack().getOrCreateNbt().getInt("CustomModelData") == 1 ||
                        player.getOffHandStack().getOrCreateNbt().getInt("CustomModelData") == 1;
                if (weapon.getDefaultStack().isIn(ModTags.Items.KH_WEAPONS_BLUDGEONING_TO_PIERCING)) bludgeoning = !bludgeoning;
                int comboCount = ((PlayerAttackProperties) player).getComboCount();
                boolean piercing = false;

                if (weapon.getDefaultStack().isIn(ModTags.Items.KH_WEAPONS_PIERCING)) {
                    int[] piercingAnimations = weapon.getPiercingAnimation();
                    int animationLength = weapon.getAnimation();
                    for (int piercingAnimation : piercingAnimations) {
                        if (comboCount % animationLength == piercingAnimation - 1) {
                            piercing = true;
                            break;
                        }
                    }

                    if (piercingAnimations.length == animationLength) piercing = true;
                }
                float[] damageValues = weapon.getDefaultAttackDamageValues();
                double[] radiusValues = weapon.getDefaultRadiusValues();

                if (bludgeoning || weapon.getDefaultStack().isIn(ModTags.Items.KH_WEAPONS_ONLY_BLUDGEONING)) { // Bludgeoning
                    renderBludgeoningOverlay(context, closestDistance, radiusValues, damageValues);
                } else if (piercing && weapon.getDefaultStack().isIn(ModTags.Items.KH_WEAPONS_PIERCING)
                        || weapon.getDefaultStack().isIn(ModTags.Items.KH_WEAPONS_BLUDGEONING_TO_PIERCING)
                        || weapon.getDefaultStack().isIn(ModTags.Items.KH_WEAPONS_ONLY_PIERCING)) { // Piercing
                    renderPiercingOverlay(context, closestDistance, radiusValues, damageValues);
                } else { // Slashing (default case)
                    renderSlashingOverlay(context, closestDistance, radiusValues, damageValues);
                }
            }
            ci.cancel();
        }



    }

    @Unique
    private void renderBludgeoningOverlay(DrawContext drawContext, double distance, double[] radiusValues, float[] damageValues) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight();
        Integer[] indices = {10, 11, 12, 13, 14};
        Arrays.sort(indices, (i1, i2) -> Float.compare(damageValues[i2], damageValues[i1]));

        Identifier[] textures = new Identifier[radiusValues.length];
        textures[indices[0] - 10] = BLUDGEONING_CRITICAL;
        textures[indices[1] - 10] = BLUDGEONING_EFFECTIVE;
        textures[indices[2] - 10] = BLUDGEONING_EFFECTIVE;
        textures[indices[3] - 10] = BLUDGEONING_MAXIMUM;
        textures[indices[4] - 10] = TOO_FAR_CLOSE;

        for (int i = 0; i < radiusValues.length; i++) {
            if (distance <= radiusValues[i] + 0.25F) {
                Identifier texture = textures[i];
                int width, height, xT, yT;
                if (texture == BLUDGEONING_CRITICAL) {
                    width = height = 9;
                    xT = yT = 5;
                } else if (texture == BLUDGEONING_EFFECTIVE) {
                    width = height = 9;
                    xT = yT = 5;
                } else if (texture == BLUDGEONING_MAXIMUM) {
                    width = height = 7;
                    xT = yT = 4;
                } else if (texture == TOO_FAR_CLOSE) {
                    width = height = 1;
                    xT = yT = 1;
                } else {
                    width = height = 9;
                    xT = yT = 5;
                }
                RenderSystem.setShaderTexture(0, texture);
                drawContext.drawTexture(texture, x - xT, y / 2 - yT, 0, 0, width, height, width, height);
                return;
            }
        }
        RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
        drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y / 2 - 1, 0, 0, 1, 1, 1, 1);
    }

    @Unique
    private void renderPiercingOverlay(DrawContext drawContext, double distance, double[] radiusValues, float[] damageValues) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight();
        Integer[] indices = {5, 6, 7, 8, 9};
        Arrays.sort(indices, (i1, i2) -> Float.compare(damageValues[i2], damageValues[i1]));

        Identifier[] textures = new Identifier[radiusValues.length];
        textures[indices[0] - 5] = PIERCING_CRITICAL;
        textures[indices[1] - 5] = PIERCING_EFFECTIVE;
        textures[indices[2] - 5] = PIERCING_EFFECTIVE;
        textures[indices[3] - 5] = PIERCING_MAXIMUM;
        textures[indices[4] - 5] = TOO_FAR_CLOSE;

        for (int i = 0; i < radiusValues.length; i++) {
            if (distance <= radiusValues[i] + 0.25F) {
                Identifier texture = textures[i];
                int width, height, xT, yT;
                if (texture == PIERCING_CRITICAL) {
                    width = height = 11;
                    xT = yT = 6;
                } else if (texture == PIERCING_EFFECTIVE) {
                    width = height = 11;
                    xT = yT = 6;
                } else if (texture == PIERCING_MAXIMUM) {
                    width = height = 7;
                    xT = yT = 4;
                } else if (texture == TOO_FAR_CLOSE) {
                    width = height = 1;
                    xT = yT = 1;
                } else {
                    width = height = 11;
                    xT = yT = 6;
                }
                RenderSystem.setShaderTexture(0, texture);
                drawContext.drawTexture(texture, x - xT, y / 2 - yT, 0, 0, width, height, width, height);
                return;
            }
        }
        RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
        drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y / 2 - 1, 0, 0, 1, 1, 1, 1);
    }

    @Unique
    private void renderSlashingOverlay(DrawContext drawContext, double distance, double[] radiusValues, float[] damageValues) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight();
        Integer[] indices = {0, 1, 2, 3, 4};
        Arrays.sort(indices, (i1, i2) -> Float.compare(damageValues[i2], damageValues[i1]));

        Identifier[] textures = new Identifier[radiusValues.length];
        textures[indices[0]] = SLASHING_CRITICAL;
        textures[indices[1]] = SLASHING_EFFECTIVE;
        textures[indices[2]] = SLASHING_EFFECTIVE;
        textures[indices[3]] = SLASHING_MAXIMUM;
        textures[indices[4]] = TOO_FAR_CLOSE;

        boolean textureFound = false;
        for (int i = 0; i < radiusValues.length; i++) {
            if (distance <= radiusValues[i] + 0.25F) {
                Identifier texture = textures[i];
                int width, height, xT, yT;
                if (texture == SLASHING_CRITICAL) {
                    width = height = 9;
                    xT = yT = 5;
                } else if (texture == SLASHING_EFFECTIVE) {
                    width = height = 9;
                    xT = yT = 5;
                } else if (texture == SLASHING_MAXIMUM) {
                    width = height = 7;
                    xT = yT = 4;
                } else if (texture == TOO_FAR_CLOSE) {
                    width = height = 1;
                    xT = yT = 1;
                } else {
                    width = height = 9;
                    xT = yT = 5;
                }
                RenderSystem.setShaderTexture(0, texture);
                drawContext.drawTexture(texture, x - xT, y / 2 - yT, 0, 0, width, height, width, height);
                textureFound = true;
                break;
            }
        }
        if (!textureFound) {
            RenderSystem.setShaderTexture(0, TOO_FAR_CLOSE);
            drawContext.drawTexture(TOO_FAR_CLOSE, x - 1, y / 2 - 1, 0, 0, 1, 1, 1, 1);
        }
    }


    @Unique
    private static final Identifier STAMINA = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/stamina_bar.png");
    @Unique
    private static final Identifier STAMINA_EMPTY = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/stamina_bar_empty.png");
    @Unique
    private static final Identifier STAMINA_BLOCKED = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/stamina_bar_blocked.png");
    @Unique
    private static final int TOTAL_STAMINA = 200;
    @Unique
    private static final int EMPTY_STAMINA_WIDTH = 9;
    @Unique
    private static final int EMPTY_STAMINA_HEIGHT = 9;
    @Unique
    private static final int STAMINA_BAR_WIDTH = 9;
    @Unique
    private static final int STAMINA_BAR_HEIGHT = 9;
    @Unique
    private static final int STAMINA_UNIT_SIZE = 8;

    @Inject(method = "renderStatusBars", at = @At("HEAD"))
    private void renderStaminaBar(DrawContext context, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        int stamina = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getInt("stamina_int");
        boolean staminaBlocked = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getBoolean("stamina_blocked");

        if (ableStamina(player) && !player.isSpectator()) {
            int x = getStaminaBarXPosition();
            int y = getStaminaBarYPosition(player);
            renderStaminaBar(context, x, y, stamina, staminaBlocked);
        }
    }

    @Unique
    private boolean ableStamina(PlayerEntity player) {
        boolean hasKHWeapon = player.getMainHandStack().getItem() instanceof KHWeapons ||
                player.getOffHandStack().isIn(ModTags.Items.KH_WEAPONS);
        boolean hasRequiredEquipment = false;
        for (ItemStack armorStack : player.getArmorItems()) {
            if (armorStack.isIn(ModTags.Items.KH_ARMORS)) {
                hasRequiredEquipment = true;
                break;
            }
        }
        return hasKHWeapon || hasRequiredEquipment;
    }

    @Unique
    private int getStaminaBarXPosition() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            return client.getWindow().getScaledWidth() / 2;
        }
        return 0;
    }

    @Unique
    private int getStaminaBarYPosition(ClientPlayerEntity player) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int height = client.getWindow().getScaledHeight();
            return player.isSubmergedInWater() ? height - 59 : height - 49;
        }
        return 0;
    }

    @Unique
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

    @Unique
    private void renderEmptyStamina(DrawContext drawContext, int x, int y) {
        drawContext.drawTexture(STAMINA_EMPTY, x, y, 0, 0, EMPTY_STAMINA_WIDTH, EMPTY_STAMINA_HEIGHT, EMPTY_STAMINA_WIDTH, EMPTY_STAMINA_HEIGHT);
    }

    @Unique
    private void renderFilledStamina(DrawContext drawContext, int x, int y) {
        drawContext.drawTexture(STAMINA, x, y, 0, 0, STAMINA_BAR_WIDTH, STAMINA_BAR_HEIGHT, STAMINA_BAR_WIDTH, STAMINA_BAR_HEIGHT);
    }

    @Unique
    private void renderBlockedStamina(DrawContext drawContext, int x, int y) {
        drawContext.drawTexture(STAMINA_BLOCKED, x, y, 0, 0, STAMINA_BAR_WIDTH, STAMINA_BAR_HEIGHT, STAMINA_BAR_WIDTH, STAMINA_BAR_HEIGHT);
    }

    @Unique
    private static final Identifier VISOR_HELMET = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/visor_helmet.png");

    @Inject(method = "render", at = @At("HEAD"))
    private void renderBackgroundOverlays(DrawContext context, float tickDelta, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        TrinketsApi.getTrinketComponent(player).ifPresent(trinketComponent -> {
            trinketComponent.getAllEquipped().forEach(pair -> {
                ItemStack trinketStack = pair.getRight();
                if (trinketStack.getItem() instanceof KHTrinketsItem && trinketStack.isIn(ModTags.Items.VISORED_HELMET) && KnightsHeraldry.config().getVisoredHelmet()) {
                    int width = context.getScaledWindowWidth();
                    int height = context.getScaledWindowHeight();

                    RenderSystem.setShaderTexture(0, VISOR_HELMET);
                    context.drawTexture(VISOR_HELMET, 0, 0, 0, 0, width, height, width, height);
                }
            });
        });
    }
}
