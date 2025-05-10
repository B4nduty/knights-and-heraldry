package com.knightsheraldry.event;

import banduty.stoneycore.event.custom.RenderFirstPersonTrinketsArmorEvents;
import banduty.stoneycore.items.armor.SCTrinketsItem;
import banduty.stoneycore.util.DyeUtil;
import com.knightsheraldry.model.TrinketsArmModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class RenderFirstPersonTrinketsArmorHandler implements RenderFirstPersonTrinketsArmorEvents {
    private static final float ALPHA = 1.0F;

    @Override
    public void onRenderInFirstPerson(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                      int light, Arm arm) {
        if (!(stack.getItem() instanceof SCTrinketsItem scTrinketsItem)) return;
        if (scTrinketsItem.getFirstPersonModel() == null) return;

        TrinketsArmModel model = (TrinketsArmModel) scTrinketsItem.getFirstPersonModel();
        float[] color = DyeUtil.getFloatDyeColor(stack);
        Identifier texturePath = scTrinketsItem.getTexturePath();

        VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(texturePath));
        renderArm(model, matrices, baseConsumer, light, scTrinketsItem.hasOverlay() ? color : new float[]{1, 1, 1}, arm);

        if (scTrinketsItem.hasOverlay()) {
            VertexConsumer overlayConsumer = vertexConsumers.getBuffer(
                    RenderLayer.getArmorCutoutNoCull(getOverlayIdentifier(scTrinketsItem)));
            renderArm(model, matrices, overlayConsumer, light, new float[]{1, 1, 1}, arm);
        }
    }

    private void renderArm(TrinketsArmModel model, MatrixStack matrices, VertexConsumer consumer,
                           int light, float[] color, Arm arm) {
        if (arm == Arm.RIGHT) {
            model.armorRightArm.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV,
                    color[0], color[1], color[2], ALPHA);
        } else {
            model.armorLeftArm.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV,
                    color[0], color[1], color[2], ALPHA);
        }
    }

    private @NotNull Identifier getOverlayIdentifier(SCTrinketsItem scTrinketsItem) {
        Identifier originalIdentifier = scTrinketsItem.getTexturePath();
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