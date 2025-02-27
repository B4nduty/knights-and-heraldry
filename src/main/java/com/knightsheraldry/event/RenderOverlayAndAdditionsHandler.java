package com.knightsheraldry.event;

import banduty.stoneycore.event.custom.RenderOverlayAndAdditionsEvents;
import banduty.stoneycore.items.armor.SCTrinketsItem;
import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.items.ModItems;
import com.knightsheraldry.model.TrinketsChestplateModel;
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
    @Override
    public void onRenderOverlayAndAdditionsEvents(LivingEntity livingEntity, ItemStack itemStack,
                                                  MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
                                                  int i, BipedEntityModel<LivingEntity> bipedEntityModel) {
        if (!(itemStack.getItem() instanceof SCTrinketsItem scTrinketsItem)) return;

        if (itemStack.getOrCreateNbt().getBoolean("kh_aventail")) {
            BipedEntityModel<LivingEntity> aventailModel = new TrinketsChestplateModel(TrinketsChestplateModel.getTexturedModelData().createModel());
            VertexConsumer baseConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getArmorCutoutNoCull(getIdentifierWithSuffix("_aventail", scTrinketsItem)));

            TrinketRenderer.followBodyRotations(livingEntity, aventailModel);
            aventailModel.render(matrixStack, baseConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        renderPartIfNeeded(itemStack, matrixStack, vertexConsumerProvider, i, bipedEntityModel, "kh_rimmed", new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/rim_guards.png"));
        renderPartIfNeeded(itemStack, matrixStack, vertexConsumerProvider, i, bipedEntityModel, "kh_besagews", new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/besagews.png"));

        if (itemStack.getItem() == ModItems.SURCOAT || itemStack.getItem() == ModItems.SURCOAT_SLEEVELESS) {
            ArmorRenderer.renderPart(matrixStack, vertexConsumerProvider, i, itemStack, bipedEntityModel, new Identifier(KnightsHeraldry.MOD_ID, "textures/entity/trinket/surcoat_overlay.png"));
        }

        if (scTrinketsItem.isDyeableWithOverlay()) {
            ArmorRenderer.renderPart(matrixStack, vertexConsumerProvider, i, itemStack, bipedEntityModel, getIdentifierWithSuffix("_overlay", scTrinketsItem));
        }
    }

    private void renderPartIfNeeded(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, BipedEntityModel<LivingEntity> model, String key, Identifier identifier) {
        if (stack.getOrCreateNbt().getBoolean(key)) {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, identifier);
        }
    }

    private Identifier getIdentifierWithSuffix(String suffix, SCTrinketsItem scTrinketsItem) {
        String texturePath = scTrinketsItem.getTexturePath().getPath().replace(".png", "") + suffix + ".png";
        return new Identifier(scTrinketsItem.getTexturePath().getNamespace(), texturePath);
    }
}
