package com.knightsheraldry.mixin;

import com.knightsheraldry.items.custom.armor.KHArmorItem;
import com.knightsheraldry.items.custom.armor.KHTrinketsItem;
import com.knightsheraldry.model.TrinketsArmModel;
import com.knightsheraldry.model.UnderArmourArmModel;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
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
        BipedEntityModel<LivingEntity> model = null;
        MinecraftClient client = MinecraftClient.getInstance();

        ItemStack stack = player.getInventory().getArmorStack(2);
        if (stack.getItem() instanceof KHArmorItem khArmorItem && khArmorItem.getSlotType() == ArmorItem.Type.CHESTPLATE.getEquipmentSlot()) {
            model = getUnderArmourArmModel();
            if (model instanceof UnderArmourArmModel underArmourArmModel && client.currentScreen == null) {
                underArmourArmModel.armorRightArm.copyTransform(arm);
            }
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
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
            model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);

            // Overlay render (untinted layer) - Render the overlay (no tint)
            if (!textureOverlayPath.equals(new Identifier(""))) ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, textureOverlayPath);
        }

        if (TrinketsApi.getTrinketComponent(player).isPresent()) {
            for (Pair<SlotReference, ItemStack> equipped : TrinketsApi.getTrinketComponent(player).get().getEquipped(trinketStack -> trinketStack.getItem() instanceof KHTrinketsItem)) {
                ItemStack trinket = equipped.getRight();
                if (trinket.getItem() instanceof KHTrinketsItem khTrinketsItem) {
                    model = getTrinketArmModel();
                    if (model instanceof TrinketsArmModel trinketsArmModel && client.currentScreen == null) {
                        trinketsArmModel.armorRightArm.copyTransform(arm);
                    }
                    VertexConsumer vertexConsumer = vertexConsumers.getBuffer(
                            RenderLayer.getArmorCutoutNoCull(khTrinketsItem.getPath()));
                    float r = 1;
                    float g = 1;
                    float b = 1;
                    if (khTrinketsItem.isDyeable()) {
                        int color = khTrinketsItem.getColor(trinket);
                        r = (color >> 16 & 255) / 255.0F;
                        g = (color >> 8 & 255) / 255.0F;
                        b = (color & 255) / 255.0F;
                    }

                    Identifier textureOverlayPath = getOverlayIdentifier(khTrinketsItem);

                    // Base armor render (tinted layer) - Render the armor with color tint
                    model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);

                    // Overlay render (untinted layer) - Render the overlay (no tint)
                    if (!textureOverlayPath.equals(new Identifier(""))) ArmorRenderer.renderPart(matrices, vertexConsumers, light, trinket, model, textureOverlayPath);
                }
            }
        }
    }

    @Unique
    private @NotNull Identifier getOverlayIdentifier(Item item) {
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

        if (item instanceof KHTrinketsItem khTrinketsItem && khTrinketsItem.isDyeable()) textureOverlayString += "_overlay.png";
        else return new Identifier("");

        return new Identifier(originalIdentifier.getNamespace(), textureOverlayString);
    }

    @Unique
    private BipedEntityModel<LivingEntity> getUnderArmourArmModel() {
        if (this.chestplateModel == null) {
            this.chestplateModel = new UnderArmourArmModel(UnderArmourArmModel.getTexturedModelData().createModel());
        }

        return this.chestplateModel;
    }

    @Unique
    private BipedEntityModel<LivingEntity> getTrinketArmModel() {
        if (this.trinketArmModel == null) {
            this.trinketArmModel = new TrinketsArmModel(TrinketsArmModel.getTexturedModelData().createModel());
        }

        return this.trinketArmModel;
    }
}