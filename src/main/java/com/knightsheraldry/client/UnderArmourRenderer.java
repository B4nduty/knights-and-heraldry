package com.knightsheraldry.client;

import com.knightsheraldry.items.custom.armor.KHUnderArmorItem;
import com.knightsheraldry.model.UnderArmourBootsModel;
import com.knightsheraldry.model.UnderArmourChestplateModel;
import com.knightsheraldry.model.UnderArmourHelmetModel;
import com.knightsheraldry.model.UnderArmourLeggingsModel;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class UnderArmourRenderer implements ArmorRenderer {
    private BipedEntityModel<LivingEntity> helmetModel;
    private BipedEntityModel<LivingEntity> chestplateModel;
    private BipedEntityModel<LivingEntity> leggingsModel;
    private BipedEntityModel<LivingEntity> bootsModel;

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
        BipedEntityModel<LivingEntity> model = getLivingEntityBipedEntityModel(stack);
        if (model != null) {
            TrinketRenderer.followBodyRotations(entity, model);
            if (stack.getItem() instanceof KHUnderArmorItem khArmorItem) {
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
        }
    }

    private static @NotNull Identifier getIdentifier(KHUnderArmorItem khArmorItem) {
        Identifier originalIdentifier = khArmorItem.getPath();

        String textureOverlayString = originalIdentifier.getPath();

        if (textureOverlayString.endsWith(".png")) {
            textureOverlayString = textureOverlayString.substring(0, textureOverlayString.length() - 4);
        }

        textureOverlayString += "_overlay.png";

        return new Identifier(originalIdentifier.getNamespace(), textureOverlayString);
    }

    private @Nullable BipedEntityModel<LivingEntity> getLivingEntityBipedEntityModel(ItemStack stack) {
        BipedEntityModel<LivingEntity> model = null;
        if (stack.getItem() instanceof KHUnderArmorItem khArmorItem) {
            if (khArmorItem.getSlotType() == ArmorItem.Type.HELMET.getEquipmentSlot()) model = this.getUnderArmourHelmetModel();
            if (khArmorItem.getSlotType() == ArmorItem.Type.CHESTPLATE.getEquipmentSlot()) model = this.getUnderArmourChestplateModel();
            if (khArmorItem.getSlotType() == ArmorItem.Type.LEGGINGS.getEquipmentSlot()) model = this.getUnderArmourLeggingsModel();
            if (khArmorItem.getSlotType() == ArmorItem.Type.BOOTS.getEquipmentSlot()) model = this.getUnderArmourBootsModel();
        }
        return model;
    }

    private BipedEntityModel<LivingEntity> getUnderArmourHelmetModel() {
        if (this.helmetModel == null) {
            this.helmetModel = new UnderArmourHelmetModel(UnderArmourHelmetModel.getTexturedModelData().createModel());
        }

        return this.helmetModel;
    }

    private BipedEntityModel<LivingEntity> getUnderArmourChestplateModel() {
        if (this.chestplateModel == null) {
            this.chestplateModel = new UnderArmourChestplateModel(UnderArmourChestplateModel.getTexturedModelData().createModel());
        }

        return this.chestplateModel;
    }

    private BipedEntityModel<LivingEntity> getUnderArmourLeggingsModel() {
        if (this.leggingsModel == null) {
            this.leggingsModel = new UnderArmourLeggingsModel(UnderArmourLeggingsModel.getTexturedModelData().createModel());
        }

        return this.leggingsModel;
    }

    private BipedEntityModel<LivingEntity> getUnderArmourBootsModel() {
        if (this.bootsModel == null) {
            this.bootsModel = new UnderArmourBootsModel(UnderArmourBootsModel.getTexturedModelData().createModel());
        }

        return this.bootsModel;
    }
}
