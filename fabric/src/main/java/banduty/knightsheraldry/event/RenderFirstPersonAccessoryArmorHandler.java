package banduty.knightsheraldry.event;

import banduty.knightsheraldry.items.item.DyeableItems;
import banduty.knightsheraldry.model.AccessoryArmModel;
import banduty.stoneycore.entity.custom.AbstractSiegeEntity;
import banduty.stoneycore.event.custom.RenderFirstPersonAccesoryArmorEvents;
import banduty.stoneycore.items.custom.armor.SCAccessoryItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RenderFirstPersonAccessoryArmorHandler implements RenderFirstPersonAccesoryArmorEvents {
    @Override
    public void onRenderInFirstPerson(LocalPlayer localPlayer, ItemStack stack, PoseStack poseStack, MultiBufferSource multiBufferSource,
                                      int light, HumanoidArm arm) {
        if (!(stack.getItem() instanceof SCAccessoryItem scAccessoryItem)) return;
        if (scAccessoryItem.getModels(stack).firstPerson().isEmpty()) return;

        AccessoryArmModel model = (AccessoryArmModel) scAccessoryItem.getModels(stack).firstPerson().get();
        int color = DyeableItems.getColor(stack);
        ResourceLocation texturePath = scAccessoryItem.getTexturePath(stack);

        VertexConsumer baseConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(texturePath));
        renderArm(localPlayer, model, poseStack, baseConsumer, light, scAccessoryItem.getRenderSettings(stack).overlay() ? color : -1, arm);

        if (scAccessoryItem.getRenderSettings(stack).overlay()) {
            VertexConsumer overlayConsumer = multiBufferSource.getBuffer(
                    RenderType.armorCutoutNoCull(getOverlayResourceLocation(stack)));
            renderArm(localPlayer, model, poseStack, overlayConsumer, light, -1, arm);
        }
    }

    private void renderArm(LocalPlayer localPlayer, AccessoryArmModel model, PoseStack poseStack, VertexConsumer consumer,
                           int light, int color, HumanoidArm arm) {
        PlayerModel<?> playerModel = ((PlayerModel<?>) ((LivingEntityRenderer<?, ?>)
                Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(localPlayer)).getModel());

        float armOffset = 0.125f; // This is 2px in model units
        if (localPlayer.getVehicle() instanceof AbstractSiegeEntity) armOffset -= 0.0625f;

        poseStack.pushPose();
        if (arm == HumanoidArm.RIGHT) {
            poseStack.translate(armOffset, 0.015F, 0.0F);
            model.armorRightArm.copyFrom(playerModel.rightArm);
            model.armorRightArm.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
        } else {
            poseStack.translate(-armOffset, 0.015F, 0.0F);
            model.armorLeftArm.copyFrom(playerModel.leftArm);
            model.armorLeftArm.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
        }
        poseStack.popPose();
    }

    private @NotNull ResourceLocation getOverlayResourceLocation(ItemStack stack) {
        if (!(stack.getItem() instanceof SCAccessoryItem scAccessoryItem)) return ResourceLocation.fromNamespaceAndPath("", "");
        ResourceLocation originalResourceLocation = scAccessoryItem.getTexturePath(stack);
        if (originalResourceLocation == null) {
            return ResourceLocation.fromNamespaceAndPath("", "");
        }

        String texturePath = originalResourceLocation.getPath();
        if (texturePath.endsWith(".png")) {
            texturePath = texturePath.substring(0, texturePath.length() - 4);
        }

        return ResourceLocation.fromNamespaceAndPath(originalResourceLocation.getNamespace(), texturePath + "_overlay.png");
    }
}