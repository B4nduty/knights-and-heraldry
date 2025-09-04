package com.knightsheraldry.client.entity.armor;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import com.knightsheraldry.model.HorseBardingModel;
import com.knightsheraldry.model.ModEntityModelLayers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class HorseBardingFeatureRenderer extends FeatureRenderer<HorseEntity, HorseEntityModel<HorseEntity>> {
    private static final Identifier HORSE_ARMOR_TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/horse/armor/horse_barding.png");
    private static final Identifier HORSE_ARMOR_TEXTURE_OVERLAY = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/horse/armor/horse_barding_overlay.png");
    private static final Identifier HORSE_ARMOR_TEXTURE_PLUME = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/horse/armor/horse_barding_plume.png");
    private final HorseBardingModel<HorseEntity> armorModel;

    public HorseBardingFeatureRenderer(FeatureRendererContext<HorseEntity, HorseEntityModel<HorseEntity>> context, EntityModelLoader modelLoader) {
        super(context);
        this.armorModel = new HorseBardingModel<>(modelLoader.getModelPart(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, HorseEntity horse, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack armorStack = horse.getArmorType();
        if (!armorStack.isEmpty() && armorStack.getItem() instanceof HorseBardingArmorItem horseBardingArmorItem) {
            this.getContextModel().copyStateTo(this.armorModel);

            this.armorModel.animateModel(horse, limbAngle, limbDistance, tickDelta);
            this.armorModel.setAngles(horse, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

            // Base armor texture
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(HORSE_ARMOR_TEXTURE));
            this.armorModel.render(matrices, vertexConsumer, light, LivingEntityRenderer.getOverlay(horse, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);

            // Overlay texture (dyed color)
            int colorInt = horseBardingArmorItem.getColor(armorStack);
            float[] colorFloat = new float[]{
                    (colorInt >> 16 & 255) / 255.0F,
                    (colorInt >> 8 & 255) / 255.0F,
                    (colorInt & 255) / 255.0F
            };
            VertexConsumer vertexConsumerOverlay = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(HORSE_ARMOR_TEXTURE_OVERLAY));
            this.armorModel.render(matrices, vertexConsumerOverlay, light, LivingEntityRenderer.getOverlay(horse, 0.0F), colorFloat[0], colorFloat[1], colorFloat[2], 1.0F);

            // Plume texture (optional, dyed color)
            if (armorStack.getNbt() != null && armorStack.getNbt().contains("plume")) {
                colorInt = armorStack.getNbt().getInt("plume");
            }
            colorFloat = new float[]{
                    (colorInt >> 16 & 255) / 255.0F,
                    (colorInt >> 8 & 255) / 255.0F,
                    (colorInt & 255) / 255.0F
            };
            VertexConsumer vertexConsumerPlume = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(HORSE_ARMOR_TEXTURE_PLUME));
            this.armorModel.plume.render(matrices, vertexConsumerPlume, light, LivingEntityRenderer.getOverlay(horse, 0.0F), colorFloat[0], colorFloat[1], colorFloat[2], 1.0F);
        }
    }
}