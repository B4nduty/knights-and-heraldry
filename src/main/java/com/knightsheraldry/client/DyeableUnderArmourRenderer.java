package com.knightsheraldry.client;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.model.UnderArmourBootsModel;
import com.knightsheraldry.model.UnderArmourChestplateModel;
import com.knightsheraldry.model.UnderArmourHelmetModel;
import com.knightsheraldry.model.UnderArmourLeggingsModel;
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
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DyeableUnderArmourRenderer implements ArmorRenderer {
    private BipedEntityModel<LivingEntity> helmetModel;
    private BipedEntityModel<LivingEntity> chestplateModel;
    private BipedEntityModel<LivingEntity> leggingsModel;
    private BipedEntityModel<LivingEntity> bootsModel;

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
        BipedEntityModel<LivingEntity> model = null;
        if (stack.isOf(ModItems.QUILTED_COIF)) model = this.getUnderArmourHelmetModel();
        else if (stack.isOf(ModItems.GAMBESON)) model = this.getUnderArmourChestplateModel();
        else if (stack.isOf(ModItems.GAMBESON_BREECHES)) model = this.getUnderArmourLeggingsModel();
        else if (stack.isOf(ModItems.GAMBESON_BOOTS)) model = this.getUnderArmourBootsModel();
        int color = ((DyeableItem) stack.getItem()).getColor(stack);
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        if (model != null) {
            contextModel.copyBipedStateTo(model);

            // Base armor render (tinted layer) - Render the armor with color tint
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/gambeson.png")));
            model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);

            // Overlay render (untinted layer) - Render the overlay (no tint)
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, new Identifier(KnightsHeraldry.MOD_ID, "textures/models/armor/gambeson_overlay.png"));
        }
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
