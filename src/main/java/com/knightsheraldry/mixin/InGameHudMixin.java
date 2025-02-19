package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.armor.KHTrinketsItem;
import com.knightsheraldry.items.armor.KHUnderArmorItem;
import com.knightsheraldry.items.item.KHWeapon;
import com.knightsheraldry.util.KHDamageCalculator;
import com.knightsheraldry.util.SharedParameters;
import com.knightsheraldry.util.itemdata.KHTags;
import com.knightsheraldry.util.playerdata.IEntityDataSaver;
import com.knightsheraldry.util.weaponutil.KHWeaponUtil;
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
    private void knightsheraldry$renderCrosshair(DrawContext context, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.getWorld() != null && player.getMainHandStack().getItem() instanceof KHWeapon) {
            Vec3d playerPos = player.getPos();
            double closestDistance = Double.MAX_VALUE;

            double distance;
            if (MinecraftClient.getInstance().targetedEntity == null) distance = 9999;
            else distance = playerPos.distanceTo(MinecraftClient.getInstance().targetedEntity.getPos());
            if (distance < closestDistance) {
                closestDistance = distance;
            }

            ItemStack mainHandStack = player.getMainHandStack();
            if (mainHandStack.getItem() instanceof KHWeapon khWeapon) {
                boolean bludgeoning = player.getMainHandStack().getOrCreateNbt().getBoolean("kh_bludgeoning");
                boolean bludgeoningToPiercing = khWeapon.getAttackDamageValues()[0] == 0
                        && khWeapon.getAttackDamageValues()[1] > 0 && khWeapon.getAttackDamageValues()[2] > 0;
                boolean piercing = isPiercing((PlayerAttackProperties) player, khWeapon);

                if (bludgeoning || khWeapon.getOnlyDamageType() == KHDamageCalculator.DamageType.BLUDGEONING) {
                    renderBludgeoningOverlay(khWeapon, context, closestDistance);
                } else if (piercing || bludgeoningToPiercing || khWeapon.getOnlyDamageType() == KHDamageCalculator.DamageType.PIERCING) {
                    renderPiercingOverlay(khWeapon,context, closestDistance);
                } else {
                    renderSlashingOverlay(khWeapon,context, closestDistance);
                }
            }
            ci.cancel();
        }



    }

    @Unique
    private static boolean isPiercing(PlayerAttackProperties player, KHWeapon weapon) {
        int comboCount = player.getComboCount();
        boolean piercing = false;

        if (weapon.getAnimation() > 0) {
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
        return piercing;
    }

    @Unique
    private void renderBludgeoningOverlay(KHWeapon khWeapon, DrawContext drawContext, double distance) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight();
        Integer[] indices = {0, 1, 2, 3, 4};

        Identifier[] textures = new Identifier[5];
        textures[indices[0]] = TOO_FAR_CLOSE;
        textures[indices[1]] = BLUDGEONING_EFFECTIVE;
        textures[indices[2]] = BLUDGEONING_CRITICAL;
        textures[indices[3]] = BLUDGEONING_EFFECTIVE;
        textures[indices[4]] = BLUDGEONING_MAXIMUM;

        for (int i = 0; i < 4; i++) {
            if (distance <= KHWeaponUtil.getRadius(khWeapon, i) + 0.25F) {
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
    private void renderPiercingOverlay(KHWeapon khWeapon, DrawContext drawContext, double distance) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight();
        Integer[] indices = {0, 1, 2, 3, 4};

        Identifier[] textures = new Identifier[5];
        textures[indices[0]] = TOO_FAR_CLOSE;
        textures[indices[1]] = PIERCING_EFFECTIVE;
        textures[indices[2]] = PIERCING_CRITICAL;
        textures[indices[3]] = PIERCING_EFFECTIVE;
        textures[indices[4]] = PIERCING_MAXIMUM;

        for (int i = 0; i < 4; i++) {
            if (distance <= KHWeaponUtil.getRadius(khWeapon, i) + 0.25F) {
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
    private void renderSlashingOverlay(KHWeapon khWeapon, DrawContext drawContext, double distance) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight();
        Integer[] indices = {0, 1, 2, 3, 4};

        Identifier[] textures = new Identifier[5];
        textures[indices[0]] = TOO_FAR_CLOSE;
        textures[indices[1]] = SLASHING_EFFECTIVE;
        textures[indices[2]] = SLASHING_CRITICAL;
        textures[indices[3]] = SLASHING_EFFECTIVE;
        textures[indices[4]] = SLASHING_MAXIMUM;

        boolean textureFound = false;
        for (int i = 0; i < 4; i++) {
            if (distance <= KHWeaponUtil.getRadius(khWeapon, i) + 0.25F) {
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
    private void knightsheraldry$renderStaminaBar(DrawContext context, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        int stamina = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getInt("stamina_int");
        boolean staminaBlocked = ((IEntityDataSaver) player).knightsheraldry$getPersistentData().getBoolean("stamina_blocked");

        if (!ableStamina(player) && player.isSpectator()) return;
        int x = getStaminaBarXPosition();
        int y = getStaminaBarYPosition(player);
        renderStaminaBar(context, x, y, stamina, staminaBlocked);
    }

    @Unique
    private boolean ableStamina(PlayerEntity player) {
        boolean hasKHWeapon = player.getMainHandStack().getItem() instanceof KHWeapon;
        boolean hasRequiredEquipment = false;
        for (ItemStack armorStack : player.getArmorItems()) {
            if (armorStack.getItem() instanceof KHUnderArmorItem) {
                hasRequiredEquipment = true;
                break;
            }
        }
        return hasKHWeapon || hasRequiredEquipment;
    }

    @Unique
    private int getStaminaBarXPosition() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return 0;
        return client.getWindow().getScaledWidth() / 2;
    }

    @Unique
    private int getStaminaBarYPosition(ClientPlayerEntity player) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return 0;
        int height = client.getWindow().getScaledHeight();
        return player.isSubmergedInWater() ? height - 59 : height - 49;
    }

    @Unique
    private void renderStaminaBar(DrawContext drawContext, int x, int y, int stamina, boolean staminaBlocked) {
        for (int i = 0; i < 10; i++) {
            renderEmptyStamina(drawContext, x + 82 - (i * STAMINA_UNIT_SIZE), y - KnightsHeraldry.getConfig().getStaminaBarYOffset());
        }

        for (int i = 0; i < SharedParameters.TOTAL_STAMINA; i++) {
            if (stamina < i) break;
            int x1 = x + 82 - (i / (SharedParameters.TOTAL_STAMINA / 10) * STAMINA_UNIT_SIZE);
            if (staminaBlocked) renderBlockedStamina(drawContext, x1, y - KnightsHeraldry.getConfig().getStaminaBarYOffset());
            else renderFilledStamina(drawContext, x1, y - KnightsHeraldry.getConfig().getStaminaBarYOffset());
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
    @Unique
    private static final Identifier LOW_STAMINA = new Identifier(KnightsHeraldry.MOD_ID, "textures/overlay/low_stamina.png");

    @Inject(method = "render", at = @At("HEAD"))
    private void knightsheraldry$renderBackgroundOverlays(DrawContext context, float tickDelta, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.isCreative()) return;
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();
        TrinketsApi.getTrinketComponent(player).ifPresent(trinketComponent -> {
            trinketComponent.getAllEquipped().forEach(pair -> {
                ItemStack trinketStack = pair.getRight();
                if (trinketStack.getItem() instanceof KHTrinketsItem && trinketStack.isIn(KHTags.VISORED_HELMET.getTag()) && KnightsHeraldry.getConfig().getVisoredHelmet()) {
                    RenderSystem.setShaderTexture(0, VISOR_HELMET);
                    context.drawTexture(VISOR_HELMET, 0, 0, 0, 0, width, height, width, height);
                }
            });
        });

        IEntityDataSaver dataSaver = (IEntityDataSaver) player;
        var persistentData = dataSaver.knightsheraldry$getPersistentData();

        int stamina = persistentData.getInt("stamina_int");

        long firstLevel = Math.absExact((int) (SharedParameters.TOTAL_STAMINA * 0.3f));
        if (stamina <= firstLevel && KnightsHeraldry.getConfig().getLowStaminaIndicator()) {
            float opacity = Math.max(0.0f, Math.min(1.0f, (float) (firstLevel - stamina) / (firstLevel)));

            float red = 1.0F;
            float green = (float) stamina / firstLevel;
            if (persistentData.getBoolean("stamina_blocked")) green = 0;
            float blue = 0.0F;

            RenderSystem.setShaderTexture(0, LOW_STAMINA);
            RenderSystem.setShaderColor(red, green, blue, opacity);
            context.drawTexture(LOW_STAMINA, 0, 0, 0, 0, width, height, width, height);
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }
}
