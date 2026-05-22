package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.accessory.KHChestplateAccessory;
import banduty.knightsheraldry.model.AccessoryArmModel;
import banduty.knightsheraldry.model.AccessoryChestplateModel;
import banduty.stoneycore.client.render.AccessoryRenderer;
import banduty.stoneycore.entity.custom.AbstractSiegeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class KHChestplateAccessoryRenderer implements AccessoryRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity entity, ItemStack accessoryStack, HumanoidModel<LivingEntity> contextModel) {
        HumanoidModel<LivingEntity> model = new AccessoryChestplateModel(AccessoryChestplateModel.getTexturedModelData().bakeRoot());
        if (!(accessoryStack.getItem() instanceof KHChestplateAccessory khChestplate)) return;
        contextModel.copyPropertiesTo(model);
        model.setupAnim(entity, entity.walkAnimation.position(), entity.walkAnimation.speed(),
                (float) entity.tickCount + Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true),
                entity.getYHeadRot() - entity.yBodyRot,
                entity.getXRot());
        VertexConsumer baseConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" +
                        BuiltInRegistries.ITEM.getKey(accessoryStack.getItem()).getPath() + ".png")));
        int color = DyedItemColor.getOrDefault(accessoryStack, khChestplate.getDefaultColor());
        model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY, khChestplate.hasOverlay() ? color : -1);

        if (khChestplate.hasOverlay()) {
            ResourceLocation textureOverlayPath = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" +
                    BuiltInRegistries.ITEM.getKey(accessoryStack.getItem()).getPath() + ".png");
            VertexConsumer overlayConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(textureOverlayPath));
            model.renderToBuffer(poseStack, overlayConsumer, packedLight, OverlayTexture.NO_OVERLAY, -1);
        }
    }

    @Override
    public void onRenderInFirstPerson(LocalPlayer player, ItemStack itemStack, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, HumanoidArm arm) {
        AccessoryArmModel model = new AccessoryArmModel(AccessoryArmModel.getTexturedModelData().bakeRoot());
        if (!(itemStack.getItem() instanceof KHChestplateAccessory khChestplate)) return;
        int color = DyedItemColor.getOrDefault(itemStack, khChestplate.getDefaultColor());
        ResourceLocation texturePath = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" +
                BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + ".png");

        VertexConsumer baseConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(texturePath));
        renderArm(player, model, poseStack, baseConsumer, light, khChestplate.hasOverlay() ? color : -1, arm);

        if (khChestplate.hasOverlay()) {
            ResourceLocation textureOverlayPath = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/accessories/" +
                    BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + ".png");
            VertexConsumer overlayConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(textureOverlayPath));
            renderArm(player, model, poseStack, overlayConsumer, light, -1, arm);
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
}
