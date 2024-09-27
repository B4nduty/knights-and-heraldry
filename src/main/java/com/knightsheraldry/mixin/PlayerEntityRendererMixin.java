package com.knightsheraldry.mixin;

import com.knightsheraldry.items.custom.armor.KHArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.model.TrinketsArmModel;
import com.knightsheraldry.model.UnderArmourArmModel;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Unique
    private BipedEntityModel<LivingEntity> chestplateModel;
    @Unique
    private BipedEntityModel<LivingEntity> trinketArmModel;

    @Inject(method = "getArmPose", at = @At("RETURN"), cancellable = true)
    private static void knightsheraldry$getArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> callbackInfoReturnable) {
        if (player.getStackInHand(hand).getNbt() != null && player.getStackInHand(hand).getNbt().getBoolean("Charged")) {
            callbackInfoReturnable.setReturnValue(BipedEntityModel.ArmPose.BOW_AND_ARROW);
        }
    }

    @Inject(method = "renderArm", at = @At("HEAD"))
    private void knightsheraldry$renderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        BipedEntityModel<LivingEntity> model;
        MinecraftClient client = MinecraftClient.getInstance();

        ItemStack stack = player.getInventory().getArmorStack(2);
        if (stack.getItem() instanceof KHArmorItem khArmorItem && khArmorItem.getSlotType() == ArmorItem.Type.CHESTPLATE.getEquipmentSlot()) {
            model = getLivingEntityBipedEntityModel(stack);
            if (client.currentScreen != null) TrinketRenderer.followBodyRotations(player, model);
            else model.setAngles(player, 0, 0, 0, 0, 0);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                    RenderLayer.getArmorCutoutNoCull(khArmorItem.getPath()));
            if (khArmorItem.isDyeable()) {
                int color = khArmorItem.getColor(stack);

                float r = (color >> 16 & 255) / 255.0F;
                float g = (color >> 8 & 255) / 255.0F;
                float b = (color & 255) / 255.0F;

                Identifier textureOverlayPath = getIdentifier(khArmorItem);

                // Base armor render (tinted layer) - Render the armor with color tint
                model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);

                // Overlay render (untinted layer) - Render the overlay (no tint)
                ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, textureOverlayPath);
            } else model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
        }

        if (TrinketsApi.getTrinketComponent(player).isPresent()) {
            for (Pair<SlotReference, ItemStack> equipped : TrinketsApi.getTrinketComponent(player).get().getEquipped(trinketStack -> trinketStack.getItem() instanceof KHTrinketsItem)) {
                ItemStack trinket = equipped.getRight();
                if (trinket.getItem() instanceof KHTrinketsItem khTrinketsItem) {
                    model = getTrinketChestplateModel();
                    if (client.currentScreen != null) TrinketRenderer.followBodyRotations(player, model);
                    else model.setAngles(player, 0, 0, 0, 0, 0);
                    VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                            RenderLayer.getArmorCutoutNoCull(khTrinketsItem.getPath()));
                    if (khTrinketsItem.isDyeable()) {
                        int color = khTrinketsItem.getColor(trinket);

                        float r = (color >> 16 & 255) / 255.0F;
                        float g = (color >> 8 & 255) / 255.0F;
                        float b = (color & 255) / 255.0F;

                        Identifier textureOverlayPath = getIdentifier(khTrinketsItem);

                        // Base armor render (tinted layer) - Render the armor with color tint
                        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);

                        // Overlay render (untinted layer) - Render the overlay (no tint)
                        ArmorRenderer.renderPart(matrices, vertexConsumers, light, trinket, model, textureOverlayPath);
                    } else model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
                }

            }
        }
    }

    @Unique
    private static @NotNull Identifier getIdentifier(Item item) {
        Identifier originalIdentifier = null;
        if (item instanceof KHArmorItem khArmorItem) originalIdentifier = khArmorItem.getPath();
        if (item instanceof KHTrinketsItem khTrinketsItem) originalIdentifier = khTrinketsItem.getPath();

        String textureOverlayString = null;
        if (originalIdentifier != null) {
            textureOverlayString = originalIdentifier.getPath();
        }

        if (textureOverlayString != null && textureOverlayString.endsWith(".png")) {
            textureOverlayString = textureOverlayString.substring(0, textureOverlayString.length() - 4);
        }

        textureOverlayString += "_overlay.png";

        return new Identifier(originalIdentifier.getNamespace(), textureOverlayString);
    }

    @Unique
    private @Nullable BipedEntityModel<LivingEntity> getLivingEntityBipedEntityModel(ItemStack stack) {
        BipedEntityModel<LivingEntity> model = null;
        if (stack.getItem() instanceof KHArmorItem khArmorItem) {
            if (khArmorItem.getSlotType() == ArmorItem.Type.CHESTPLATE.getEquipmentSlot()) model = this.getUnderArmourChestplateModel();
        }
        return model;
    }

    @Unique
    private BipedEntityModel<LivingEntity> getUnderArmourChestplateModel() {
        if (this.chestplateModel == null) {
            this.chestplateModel = new UnderArmourArmModel(UnderArmourArmModel.getTexturedModelData().createModel());
        }

        return this.chestplateModel;
    }

    @Unique
    private BipedEntityModel<LivingEntity> getTrinketChestplateModel() {
        if (this.trinketArmModel == null) {
            this.trinketArmModel = new TrinketsArmModel(TrinketsArmModel.getTexturedModelData().createModel());
        }

        return this.trinketArmModel;
    }
}