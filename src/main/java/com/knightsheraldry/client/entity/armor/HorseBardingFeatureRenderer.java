package com.knightsheraldry.client.entity.armor;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import com.knightsheraldry.model.HorseBardingModel;
import com.knightsheraldry.model.ModEntityModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.ItemStack;

public class HorseBardingFeatureRenderer extends RenderLayer<Horse, HorseModel<Horse>> {
    private static final ResourceLocation HORSE_ARMOR_TEXTURE_PLUME = new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/horse/armor/horse_barding_plume.png");
    private final HorseBardingModel<Horse> armorModel;

    public HorseBardingFeatureRenderer(RenderLayerParent<Horse, HorseModel<Horse>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.armorModel = new HorseBardingModel<>(entityModelSet.bakeLayer(ModEntityModelLayers.HORSE_BARDING_MODEL_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, Horse horse, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack armorStack = horse.getArmor();
        if (!armorStack.isEmpty() && armorStack.getItem() instanceof HorseBardingArmorItem horseBardingArmorItem) {
            this.getParentModel().copyPropertiesTo(this.armorModel);

            this.armorModel.prepareMobModel(horse, limbAngle, limbDistance, tickDelta);
            this.armorModel.setupAnim(horse, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

            // Base armor texture
            ResourceLocation texture = new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/horse/armor/" + BuiltInRegistries.ITEM.getKey(armorStack.getItem()).getPath() + ".png");
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(texture));
            this.armorModel.renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(horse, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);

            // Overlay texture (dyed color)
            int colorInt = horseBardingArmorItem.getColor(armorStack);
            float[] colorFloat = new float[]{
                    (colorInt >> 16 & 255) / 255.0F,
                    (colorInt >> 8 & 255) / 255.0F,
                    (colorInt & 255) / 255.0F
            };
            ResourceLocation textureOverlay = new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/horse/armor/" + BuiltInRegistries.ITEM.getKey(armorStack.getItem()).getPath() + "_overlay.png");
            VertexConsumer vertexConsumerOverlay = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(textureOverlay));
            this.armorModel.renderToBuffer(poseStack, vertexConsumerOverlay, light, LivingEntityRenderer.getOverlayCoords(horse, 0.0F), colorFloat[0], colorFloat[1], colorFloat[2], 1.0F);

            // Plume texture (optional, dyed color)
            if (armorStack.getTag() != null && armorStack.getTag().contains("plume")) {
                colorInt = armorStack.getTag().getInt("plume");
            }
            colorFloat = new float[]{
                    (colorInt >> 16 & 255) / 255.0F,
                    (colorInt >> 8 & 255) / 255.0F,
                    (colorInt & 255) / 255.0F
            };
            VertexConsumer vertexConsumerPlume = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(HORSE_ARMOR_TEXTURE_PLUME));
            this.armorModel.plume.render(poseStack, vertexConsumerPlume, light, LivingEntityRenderer.getOverlayCoords(horse, 0.0F), colorFloat[0], colorFloat[1], colorFloat[2], 1.0F);
        }
    }
}