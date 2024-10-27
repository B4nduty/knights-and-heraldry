package com.knightsheraldry.mixin;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.custom.armor.KHUnderArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.model.TrinketsArmModel;
import com.knightsheraldry.model.TrinketsChestplateModel;
import com.knightsheraldry.model.UnderArmourArmModel;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @Final
    @Shadow
    private MinecraftClient client;

    @Inject(method = "renderArm", at = @At("TAIL"))
    private void knightsheraldry$renderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Arm arm, CallbackInfo ci) {
        matrices.push();
        float f = arm == Arm.RIGHT ? 1.0F : -1.0F;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92.0F));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(f * -41.0F));
        matrices.translate(f * 0.3F, -1.1F, 0.45F);
        modelLoader(matrices, vertexConsumers, light, arm);
        matrices.pop();
    }

    @Inject(method = "renderArmHoldingItem", at = @At("TAIL"))
    private void knightsheraldry$renderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float equipProgress, float swingProgress, Arm arm, CallbackInfo ci) {
        modelLoader(matrices, vertexConsumers, light, arm);
    }

    @Unique
    private void modelLoader(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Arm arm) {
        ClientPlayerEntity player = this.client.player;
        if (player == null) return;
        ItemStack stack = player.getInventory().getArmorStack(2);
        if (stack.getItem() instanceof KHUnderArmorItem khArmorItem && khArmorItem.getSlotType() == ArmorItem.Type.CHESTPLATE.getEquipmentSlot()) {
            UnderArmourArmModel model = new UnderArmourArmModel(UnderArmourArmModel.getTexturedModelData().createModel());
            VertexConsumer baseConsumer = vertexConsumers.getBuffer(
                    RenderLayer.getArmorCutoutNoCull(khArmorItem.getPath()));
            float r = 1;
            float g = 1;
            float b = 1;
            if (khArmorItem.isDyeable()) {
                int color = khArmorItem.getColor(stack);
                r = (color >> 16 & 255) / 255.0F;
                g = (color >> 8 & 255) / 255.0F;
                b = (color & 255) / 255.0F;
            }

            Identifier textureOverlayPath = getOverlayIdentifier(khArmorItem);

            // Base armor render (tinted layer) - Render the armor with color tint
            if (arm == Arm.RIGHT) model.armorRightArm.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
            if (arm == Arm.LEFT) model.armorLeftArm.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);

            // Overlay render (untinted layer) - Render the overlay (no tint)
            if (!textureOverlayPath.equals(new Identifier(""))) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, textureOverlayPath);
        }

        if (TrinketsApi.getTrinketComponent(player).isPresent()) {
            for (Pair<SlotReference, ItemStack> equipped : TrinketsApi.getTrinketComponent(player).get().getEquipped(trinketStack -> trinketStack.getItem() instanceof KHTrinketsItem)) {
                ItemStack trinket = equipped.getRight();
                if (trinket.getItem() instanceof KHTrinketsItem khTrinketsItem && khTrinketsItem.type == KHTrinketsItem.Type.CHESTPLATE) {
                    TrinketsArmModel model = new TrinketsArmModel(TrinketsArmModel.getTexturedModelData().createModel());
                    float r = 1, g = 1, b = 1;
                    if (khTrinketsItem.isDyeable()) {
                        int color = khTrinketsItem.getColor(stack);
                        r = (color >> 16 & 255) / 255.0F; g = (color >> 8 & 255) / 255.0F; b = (color & 255) / 255.0F;
                    }
                    VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(khTrinketsItem.getPath()));
                    if (arm == Arm.RIGHT) model.armorRightArm.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
                    if (arm == Arm.LEFT) model.armorLeftArm.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
                    if (khTrinketsItem.isDyeable() && khTrinketsItem.hasOverlay()) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, getOverlayIdentifier(khTrinketsItem));
                    if (stack.getOrCreateNbt().getBoolean("aventail")) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel()), getAventailIdentifier(khTrinketsItem));
                    if (stack.getOrCreateNbt().getBoolean("rimmed")) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/rim_guards.png"));
                    if (stack.getOrCreateNbt().getBoolean("besagews")) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/besagews.png"));
                }
            }
        }
    }

    @Unique
    private @NotNull Identifier getOverlayIdentifier(Item item) {
        Identifier originalIdentifier = null;
        if (item instanceof KHUnderArmorItem khArmorItem) originalIdentifier = khArmorItem.getPath();
        if (item instanceof KHTrinketsItem khTrinketsItem) originalIdentifier = khTrinketsItem.getPath();

        String textureOverlayString = null;
        if (originalIdentifier != null) {
            textureOverlayString = originalIdentifier.getPath();
        }

        if (textureOverlayString != null && textureOverlayString.endsWith(".png")) {
            textureOverlayString = textureOverlayString.substring(0, textureOverlayString.length() - 4);
        }

        if (item instanceof KHTrinketsItem khTrinketsItem && khTrinketsItem.isDyeable()) textureOverlayString += "_overlay.png";
        else return new Identifier("");

        return new Identifier(originalIdentifier.getNamespace(), textureOverlayString);
    }

    @Unique
    private @NotNull Identifier getAventailIdentifier(KHTrinketsItem khTrinketsItem) {
        Identifier originalIdentifier = khTrinketsItem.getPath();

        String textureOverlayString = null;
        if (originalIdentifier != null) {
            textureOverlayString = originalIdentifier.getPath();
        }

        if (textureOverlayString != null && textureOverlayString.endsWith(".png")) {
            textureOverlayString = textureOverlayString.substring(0, textureOverlayString.length() - 4);
        }

        textureOverlayString += "_aventail.png";

        return new Identifier(originalIdentifier.getNamespace(), textureOverlayString);
    }
}