package com.knightsheraldry.event;

import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.model.HelmetDecoModel;
import com.knightsheraldry.util.itemdata.HelmetDeco;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class RenderOverlayAndAdditionsHandler implements RenderOverlayAndAdditionsEvents {

    private static final ResourceLocation RIM_GUARDS_TEXTURE = new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/rim_guards.png");
    private static final ResourceLocation BESAGEWS_TEXTURE = new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/besagews.png");
    private static final ResourceLocation SURCOAT_OVERLAY_TEXTURE = new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/surcoat_overlay.png");
    private static final ResourceLocation CIVILIAN_BELT_TEXTURE = new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/entity/accessories/civilian_belt.png");

    @Override
    public void onRenderOverlayAndAdditionsEvents(LivingEntity entity, ItemStack stack,
                                                  PoseStack poseStack, MultiBufferSource multiBufferSource,
                                                  int light, HumanoidModel<LivingEntity> model) {
        if (!(stack.getItem() instanceof SCAccessoryItem scAccessoryItem)) return;

        renderHelmetDecoIfNeeded(entity, stack, poseStack, multiBufferSource, light);

        renderPartIfNeeded(stack, poseStack, multiBufferSource, light, model, "rimmed", RIM_GUARDS_TEXTURE);
        renderPartIfNeeded(stack, poseStack, multiBufferSource, light, model, "besagews", BESAGEWS_TEXTURE);

        if (isSurcoat(stack)) {
            ArmorRenderer.renderPart(poseStack, multiBufferSource, light, stack, model, SURCOAT_OVERLAY_TEXTURE);
        }

        if (stack.getItem() == ModItems.CIVILIAN_SURCOAT.get() || stack.getItem() == ModItems.GIORNEA.get()) {
            ArmorRenderer.renderPart(poseStack, multiBufferSource, light, stack, model, CIVILIAN_BELT_TEXTURE);
        }

        if (scAccessoryItem.getRenderSettings(stack).overlay()) {
            ArmorRenderer.renderPart(poseStack, multiBufferSource, light, stack, model, getResourceLocationWithSuffix(stack));
        }
    }

    private void renderHelmetDecoIfNeeded(LivingEntity entity, ItemStack stack, PoseStack poseStack,
                                          MultiBufferSource multiBufferSource, int light) {
        CompoundTag nbt = stack.getTag();
        if (nbt == null) return;

        String basePath = "textures/entity/accessories/deco/";

        for (HelmetDeco deco : HelmetDeco.HELMET_DECO.values()) {
            String key = deco.getNbtKey();

            if (deco.color() == 2) {
                if (nbt.contains(key) && nbt.getCompound(key).contains("color1")) {
                    float[] color = getColorFromNbt(nbt.getCompound(key).getInt("color1"));
                    ResourceLocation texture = new ResourceLocation(KnightsHeraldry.MOD_ID, basePath + key + "_base.png");
                    renderHelmetDeco(entity, poseStack, multiBufferSource, light, texture, color);
                }
                if (nbt.contains(key) && nbt.getCompound(key).contains("color2")) {
                    float[] color = getColorFromNbt(nbt.getCompound(key).getInt("color2"));
                    ResourceLocation texture = new ResourceLocation(KnightsHeraldry.MOD_ID, basePath + key + "_stripe.png");
                    renderHelmetDeco(entity, poseStack, multiBufferSource, light, texture, color);
                }
                continue;
            }

            if (!nbt.contains(key)) continue;
            float[] color = deco.color() == 1 ? getColorFromNbt(nbt.getInt(key)) : new float[]{1.0F, 1.0F, 1.0F};
            ResourceLocation texture = new ResourceLocation(KnightsHeraldry.MOD_ID, basePath + key + ".png");
            renderHelmetDeco(entity, poseStack, multiBufferSource, light, texture, color);
        }
    }

    private void renderHelmetDeco(LivingEntity entity, PoseStack poseStack,
                                  MultiBufferSource multiBufferSource, int light,
                                  ResourceLocation texture, float[] color) {
        HumanoidModel<LivingEntity> model =
                new HelmetDecoModel(HelmetDecoModel.getTexturedModelData().bakeRoot());
        VertexConsumer consumer =
                multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(texture));

        renderModel(entity, model, poseStack, consumer, light, color);
    }


    private void renderModel(LivingEntity entity, HumanoidModel<LivingEntity> model,
                             PoseStack poseStack, VertexConsumer consumer, int light, float[] color) {
        AccessoryRenderer.followBodyRotations(entity, model);
        model.renderToBuffer(poseStack, consumer, light, OverlayTexture.NO_OVERLAY,
                color[0], color[1], color[2], 1.0F);
    }

    private void renderPartIfNeeded(ItemStack stack, PoseStack poseStack,
                                    MultiBufferSource multiBufferSource, int light,
                                    HumanoidModel<LivingEntity> model, String key, ResourceLocation texture) {
        if (!stack.getOrCreateTag().getBoolean(key)) {
            return;
        }

        if (!texture.equals(RIM_GUARDS_TEXTURE) && !texture.equals(BESAGEWS_TEXTURE)) {
            ArmorRenderer.renderPart(poseStack, multiBufferSource, light, stack, model, texture);
            return;
        }

        String stackName = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();
        ResourceLocation finalTexture = getVariantTexture(texture, stackName);

        ArmorRenderer.renderPart(poseStack, multiBufferSource, light, stack, model, finalTexture);
    }

    private ResourceLocation getVariantTexture(ResourceLocation baseTexture, String stackName) {
        String variantPrefix = getVariantPrefix(stackName);
        if (variantPrefix == null) {
            return baseTexture;
        }

        String path = baseTexture.getPath();

        int lastSlashIndex = path.lastIndexOf('/');
        if (lastSlashIndex == -1) {
            String newPath = variantPrefix + path;
            return new ResourceLocation(baseTexture.getNamespace(), newPath);
        }

        String directory = path.substring(0, lastSlashIndex + 1);
        String filename = path.substring(lastSlashIndex + 1);
        String newPath = directory + variantPrefix + filename;

        return new ResourceLocation(baseTexture.getNamespace(), newPath);
    }

    private String getVariantPrefix(String stackName) {
        if (stackName.contains("dark_")) {
            return "dark_";
        } else if (stackName.contains("golden_")) {
            return "golden_";
        }
        return null;
    }

    private boolean isSurcoat(ItemStack stack) {
        return stack.getItem() == ModItems.SURCOAT.get() || stack.getItem() == ModItems.SURCOAT_SLEEVELESS.get();
    }

    private float[] getColorFromNbt(int colorInt) {
        return new float[]{
                (colorInt >> 16 & 255) / 255.0F,
                (colorInt >> 8 & 255) / 255.0F,
                (colorInt & 0xFF) / 255.0F
        };
    }

    private ResourceLocation getResourceLocationWithSuffix(ItemStack stack) {
        if (!(stack.getItem() instanceof SCAccessoryItem item)) return new ResourceLocation("");
        String path = item.getTexturePath(stack).getPath().replace(".png", "") + "_overlay" + ".png";
        return new ResourceLocation(item.getTexturePath(stack).getNamespace(), path);
    }
}