package com.knightsheraldry.event;

import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import banduty.stoneycore.items.armor.SCTrinketsItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.model.TrinketsChestplateModel;
import com.knightsheraldry.model.TrinketsHelmetModel;
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
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RenderOverlayAndAdditionsHandler implements RenderOverlayAndAdditionsEvents {
    private static final float[] WHITE_COLOR = {1.0F, 1.0F, 1.0F};
    private static final float ALPHA = 1.0F;

    private static final Identifier PLUME_TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/plume.png");
    private static final Identifier RIM_GUARDS_TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/rim_guards.png");
    private static final Identifier BESAGEWS_TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/besagews.png");
    private static final Identifier SURCOAT_OVERLAY_TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/surcoat_overlay.png");

    @Override
    public void onRenderOverlayAndAdditionsEvents(LivingEntity entity, ItemStack stack,
                                                  MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                                  int light, BipedEntityModel<LivingEntity> model) {
        if (!(stack.getItem() instanceof SCTrinketsItem scTrinketsItem)) return;

        renderAventailIfNeeded(entity, stack, matrices, vertexConsumers, light, scTrinketsItem);
        renderPlumeIfNeeded(entity, stack, matrices, vertexConsumers, light);

        renderPartIfNeeded(stack, matrices, vertexConsumers, light, model, "kh_rimmed", RIM_GUARDS_TEXTURE);
        renderPartIfNeeded(stack, matrices, vertexConsumers, light, model, "kh_besagews", BESAGEWS_TEXTURE);

        if (isSurcoat(stack)) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, SURCOAT_OVERLAY_TEXTURE);
        }

        if (scTrinketsItem.hasOverlay()) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model,
                    getIdentifierWithSuffix("_overlay", scTrinketsItem));
        }
    }

    private void renderAventailIfNeeded(LivingEntity entity, ItemStack stack, MatrixStack matrices,
                                        VertexConsumerProvider vertexConsumers, int light, SCTrinketsItem item) {
        if (!stack.getOrCreateNbt().getBoolean("kh_aventail")) return;

        BipedEntityModel<LivingEntity> aventailModel = new TrinketsChestplateModel(
                TrinketsChestplateModel.getTexturedModelData().createModel());
        VertexConsumer consumer = vertexConsumers.getBuffer(
                RenderLayer.getArmorCutoutNoCull(getIdentifierWithSuffix("_aventail", item)));

        renderModel(entity, aventailModel, matrices, consumer, light, WHITE_COLOR);
    }

    private void renderPlumeIfNeeded(LivingEntity entity, ItemStack stack, MatrixStack matrices,
                                     VertexConsumerProvider vertexConsumers, int light) {
        if (stack.getNbt() == null || !stack.getNbt().contains("kh_plume")) return;

        BipedEntityModel<LivingEntity> plumeModel = new TrinketsHelmetModel(
                TrinketsHelmetModel.getTexturedModelData().createModel());
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(PLUME_TEXTURE));

        float[] color = getColorFromNbt(stack.getNbt().getInt("kh_plume"));
        renderModel(entity, plumeModel, matrices, consumer, light, color);
    }

    private void renderModel(LivingEntity entity, BipedEntityModel<LivingEntity> model,
                             MatrixStack matrices, VertexConsumer consumer, int light, float[] color) {
        TrinketRenderer.followBodyRotations(entity, model);
        model.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV,
                color[0], color[1], color[2], ALPHA);
    }

    private void renderPartIfNeeded(ItemStack stack, MatrixStack matrices,
                                    VertexConsumerProvider vertexConsumers, int light,
                                    BipedEntityModel<LivingEntity> model, String key, Identifier texture) {
        if (stack.getOrCreateNbt().getBoolean(key)) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, texture);
        }
    }

    private boolean isSurcoat(ItemStack stack) {
        return stack.getItem() == ModItems.SURCOAT || stack.getItem() == ModItems.SURCOAT_SLEEVELESS;
    }

    private float[] getColorFromNbt(int colorInt) {
        return new float[]{
                (colorInt >> 16 & 255) / 255.0F,
                (colorInt >> 8 & 255) / 255.0F,
                (colorInt & 255) / 255.0F
        };
    }

    private Identifier getIdentifierWithSuffix(String suffix, SCTrinketsItem item) {
        String path = item.getTexturePath().getPath().replace(".png", "") + suffix + ".png";
        return new Identifier(item.getTexturePath().getNamespace(), path);
    }
}