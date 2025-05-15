package com.knightsheraldry.event;

import banduty.stoneycore.event.custom.RenderFirstPersonAccesoryArmorEvents;
import banduty.stoneycore.items.armor.SCAccessoryItem;
import banduty.stoneycore.util.DyeUtil;
import com.knightsheraldry.model.AccessoryArmModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class RenderFirstPersonAccessoryArmorHandler implements RenderFirstPersonAccesoryArmorEvents {
    @Override
    public void onRenderInFirstPerson(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                      int light, Arm arm) {
        if (!(stack.getItem() instanceof SCAccessoryItem scAccessoryItem)) return;
        if (scAccessoryItem.getFirstPersonModel(stack) == null) return;

        AccessoryArmModel model = (AccessoryArmModel) scAccessoryItem.getFirstPersonModel(stack);
        float[] color = DyeUtil.getFloatDyeColor(stack);
        Identifier texturePath = scAccessoryItem.getTexturePath(stack);

        VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(texturePath));
        renderArm(model, matrices, baseConsumer, light, scAccessoryItem.hasOverlay() ? color : new float[]{1, 1, 1}, arm);

        if (scAccessoryItem.hasOverlay()) {
            VertexConsumer overlayConsumer = vertexConsumers.getBuffer(
                    RenderLayer.getArmorCutoutNoCull(getOverlayIdentifier(stack)));
            renderArm(model, matrices, overlayConsumer, light, new float[]{1, 1, 1}, arm);
        }
    }

    private void renderArm(AccessoryArmModel model, MatrixStack matrices, VertexConsumer consumer,
                           int light, float[] color, Arm arm) {
        if (arm == Arm.RIGHT) {
            model.armorRightArm.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV,
                    color[0], color[1], color[2], 1.0F);
        } else {
            model.armorLeftArm.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV,
                    color[0], color[1], color[2], 1.0F);
        }
    }

    private @NotNull Identifier getOverlayIdentifier(ItemStack stack) {
        if (!(stack.getItem() instanceof SCAccessoryItem scAccessoryItem)) return new Identifier("");
        Identifier originalIdentifier = scAccessoryItem.getTexturePath(stack);
        if (originalIdentifier == null) {
            return new Identifier("");
        }

        String texturePath = originalIdentifier.getPath();
        if (texturePath.endsWith(".png")) {
            texturePath = texturePath.substring(0, texturePath.length() - 4);
        }

        return new Identifier(originalIdentifier.getNamespace(), texturePath + "_overlay.png");
    }
}