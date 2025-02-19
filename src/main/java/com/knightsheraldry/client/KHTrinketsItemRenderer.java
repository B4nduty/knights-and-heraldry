package com.knightsheraldry.client;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.items.armor.KHTrinketsItem;
import com.knightsheraldry.model.*;
import com.knightsheraldry.util.DyeUtil;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class KHTrinketsItemRenderer implements TrinketRenderer {
    public KHTrinketsItemRenderer() {}
    
    @Environment(EnvType.CLIENT)
    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!(stack.getItem() instanceof KHTrinketsItem khTrinketsItem)) return;
        BipedEntityModel<LivingEntity> model = khTrinketsItem.getModel();
        TrinketRenderer.followBodyRotations(entity, model);

        if (model instanceof CloakHoodModel cloakHoodModel) {
            cloakHoodModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        }

        VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(khTrinketsItem.getTexturePath()));
        float[] color = DyeUtil.getDyeColor(stack);

        model.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, color[0], color[1], color[2], 1.0F);
        renderOverlayAndAdditions(entity, stack, matrices, vertexConsumers, light, model, khTrinketsItem);
    }

    @Environment(EnvType.CLIENT)
    private void renderOverlayAndAdditions(LivingEntity entity, ItemStack stack, MatrixStack matrices,
                                           VertexConsumerProvider vertexConsumers, int light,
                                           BipedEntityModel<LivingEntity> model, KHTrinketsItem khTrinketsItem) {
        if (stack.getOrCreateNbt().getBoolean("kh_aventail")) {
            BipedEntityModel<LivingEntity> aventailModel = new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel());
            VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(getIdentifierWithSuffix("_aventail", khTrinketsItem)));

            TrinketRenderer.followBodyRotations(entity, aventailModel);
            aventailModel.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        renderPartIfNeeded(stack, matrices, vertexConsumers, light, model, "kh_rimmed", new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/rim_guards.png"));
        renderPartIfNeeded(stack, matrices, vertexConsumers, light, model, "kh_besagews", new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/besagews.png"));

        if (stack.getItem() == ModItems.SURCOAT || stack.getItem() == ModItems.SURCOAT_SLEEVELESS) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/surcoat_overlay.png"));
        }

        if (khTrinketsItem.isDyeable()) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, getIdentifierWithSuffix("_overlay", khTrinketsItem));
        }
    }
    
    private void renderPartIfNeeded(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, BipedEntityModel<LivingEntity> model, String key, Identifier identifier) {
        if (stack.getOrCreateNbt().getBoolean(key)) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, identifier);
        }
    }

    private Identifier getIdentifierWithSuffix(String suffix, KHTrinketsItem khTrinketsItem) {
        String texturePath = khTrinketsItem.getTexturePath().getPath().replace(".png", "") + suffix + ".png";
        return new Identifier(khTrinketsItem.getTexturePath().getNamespace(), texturePath);
    }
}
