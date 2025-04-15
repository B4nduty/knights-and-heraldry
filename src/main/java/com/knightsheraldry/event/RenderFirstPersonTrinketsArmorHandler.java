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
    public void onRenderInFirstPerson(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Arm arm) {
        if (!(stack.getItem() instanceof SCTrinketsItem scTrinketsItem && scTrinketsItem.getFirstPersonModel() != null)) return;

        TrinketsArmModel model = (TrinketsArmModel) scTrinketsItem.getFirstPersonModel();

        float[] color = new float[3];
        color[0] = 1;
        color[1] = 1;
        color[2] = 1;
        if (scTrinketsItem.isDyeable()) {
            color = DyeUtil.getDyeColor(stack);
        }
        VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(scTrinketsItem.getTexturePath()));
        if (arm == Arm.RIGHT) {
            model.armorRightArm.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, color[0], color[1], color[2], 1.0F);
            if (scTrinketsItem.isDyeableWithOverlay()) {
                VertexConsumer dyeableConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(getOverlayIdentifier(scTrinketsItem)));
                model.armorRightArm.render(matrices, dyeableConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1.0F);
            }
        }

        if (arm == Arm.LEFT) {
            model.armorLeftArm.render(matrices, baseConsumer, light, OverlayTexture.DEFAULT_UV, color[0], color[1], color[2], 1.0F);
            if (scTrinketsItem.isDyeableWithOverlay()) {
                VertexConsumer dyeableConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(getOverlayIdentifier(scTrinketsItem)));
                model.armorLeftArm.render(matrices, dyeableConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1.0F);
            }
        }
    }

    private @NotNull Identifier getOverlayIdentifier(SCTrinketsItem scTrinketsItem) {
        Identifier originalIdentifier = scTrinketsItem.getTexturePath();

        String textureOverlayString = null;
        if (originalIdentifier != null) {
            textureOverlayString = originalIdentifier.getPath();
        }

        if (textureOverlayString != null && textureOverlayString.endsWith(".png")) {
            textureOverlayString = textureOverlayString.substring(0, textureOverlayString.length() - 4);
        }

        if (scTrinketsItem.isDyeableWithOverlay()) textureOverlayString += "_overlay.png";
        else return new Identifier("");

        return new Identifier(originalIdentifier.getNamespace(), textureOverlayString);
    }
}
