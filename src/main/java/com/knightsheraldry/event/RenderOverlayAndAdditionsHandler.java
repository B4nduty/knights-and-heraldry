package com.knightsheraldry.event;

import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import banduty.stoneycore.items.armor.SCAccessoryItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.model.HelmetDecoModel;
import com.knightsheraldry.util.itemdata.HelmetDeco;
import io.wispforest.accessories.api.client.AccessoryRenderer;
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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RenderOverlayAndAdditionsHandler implements RenderOverlayAndAdditionsEvents {
    private static final float[] WHITE_COLOR = {1.0F, 1.0F, 1.0F};
    private static final float ALPHA = 1.0F;

    private static final Identifier RIM_GUARDS_TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/accessories/rim_guards.png");
    private static final Identifier BESAGEWS_TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/accessories/besagews.png");
    private static final Identifier SURCOAT_OVERLAY_TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/accessories/surcoat_overlay.png");

    @Override
    public void onRenderOverlayAndAdditionsEvents(LivingEntity entity, ItemStack stack,
                                                  MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                                  int light, BipedEntityModel<LivingEntity> model) {
        if (!(stack.getItem() instanceof SCAccessoryItem scAccessoryItem)) return;

        renderHelmetDecoIfNeeded(entity, stack, matrices, vertexConsumers, light);

        renderPartIfNeeded(stack, matrices, vertexConsumers, light, model, "rimmed", RIM_GUARDS_TEXTURE);
        renderPartIfNeeded(stack, matrices, vertexConsumers, light, model, "besagews", BESAGEWS_TEXTURE);

        if (isSurcoat(stack)) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, SURCOAT_OVERLAY_TEXTURE);
        }

        if (scAccessoryItem.getRenderSettings(stack).overlay()) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model,
                    getIdentifierWithSuffix("_overlay", stack));
        }
    }

    private void renderHelmetDecoIfNeeded(LivingEntity entity, ItemStack stack, MatrixStack matrices,
                                          VertexConsumerProvider vertexConsumers, int light) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) return;

        String basePath = "textures/entity/accessories/deco/";

        for (HelmetDeco deco : HelmetDeco.HELMET_DECO.values()) {
            String key = deco.getNbtKey();

            if (deco.color() == 2) {
                if (nbt.contains(key) && nbt.getCompound(key).contains("color1")) {
                    float[] color = getColorFromNbt(nbt.getCompound(key).getInt("color1"));
                    Identifier texture = new Identifier(KnightsHeraldry.MOD_ID, basePath + key + "_base.png");
                    renderHelmetDeco(entity, matrices, vertexConsumers, light, texture, color);
                }
                if (nbt.contains(key) && nbt.getCompound(key).contains("color2")) {
                    float[] color = getColorFromNbt(nbt.getCompound(key).getInt("color2"));
                    Identifier texture = new Identifier(KnightsHeraldry.MOD_ID, basePath + key + "_stripe.png");
                    renderHelmetDeco(entity, matrices, vertexConsumers, light, texture, color);
                }
                continue;
            }

            if (!nbt.contains(key)) continue;
            float[] color = deco.color() == 1 ? getColorFromNbt(nbt.getInt(key)) : WHITE_COLOR;
            Identifier texture = new Identifier(KnightsHeraldry.MOD_ID, basePath + key + ".png");
            renderHelmetDeco(entity, matrices, vertexConsumers, light, texture, color);
        }
    }

    private void renderHelmetDeco(LivingEntity entity, MatrixStack matrices,
                                  VertexConsumerProvider vertexConsumers, int light,
                                  Identifier texture, float[] color) {
        BipedEntityModel<LivingEntity> model =
                new HelmetDecoModel(HelmetDecoModel.getTexturedModelData().createModel());
        VertexConsumer consumer =
                vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(texture));

        renderModel(entity, model, matrices, consumer, light, color);
    }


    private void renderModel(LivingEntity entity, BipedEntityModel<LivingEntity> model,
                             MatrixStack matrices, VertexConsumer consumer, int light, float[] color) {
        AccessoryRenderer.followBodyRotations(entity, model);
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
        return stack.getItem() == ModItems.SURCOAT.get() || stack.getItem() == ModItems.SURCOAT_SLEEVELESS.get();
    }

    private float[] getColorFromNbt(int colorInt) {
        return new float[]{
                (colorInt >> 16 & 255) / 255.0F,
                (colorInt >> 8 & 255) / 255.0F,
                (colorInt & 0xFF) / 255.0F
        };
    }

    private Identifier getIdentifierWithSuffix(String suffix, ItemStack stack) {
        if (!(stack.getItem() instanceof SCAccessoryItem item)) return new Identifier("");
        String path = item.getTexturePath(stack).getPath().replace(".png", "") + suffix + ".png";
        return new Identifier(item.getTexturePath(stack).getNamespace(), path);
    }
}