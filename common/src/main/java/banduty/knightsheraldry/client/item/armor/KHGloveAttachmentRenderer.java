package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.attachment.KHChestplateAttachment;
import banduty.knightsheraldry.items.armor.attachment.KHGlove;
import banduty.knightsheraldry.model.GlovesModel;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
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

public class KHGloveAttachmentRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, LivingEntity livingEntity, ItemStack itemStack, HumanoidModel<LivingEntity> humanoidModel, float v, float v1, float v2, float v3, float v4, float v5) {
        HumanoidModel<LivingEntity> model = new GlovesModel(GlovesModel.getTexturedModelData().bakeRoot());
        if (!(itemStack.getItem() instanceof KHGlove khGlove)) return;
        humanoidModel.copyPropertiesTo(model);
        VertexConsumer baseConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                        BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + ".png")));
        int color = DyedItemColor.getOrDefault(itemStack, khGlove.getDefaultColor());
        model.renderToBuffer(poseStack, baseConsumer, i, OverlayTexture.NO_OVERLAY, color);

        if (khGlove.hasOverlay()) {
            ResourceLocation textureOverlayPath = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                    BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + "_overlay.png");
            VertexConsumer overlayConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(textureOverlayPath));
            model.renderToBuffer(poseStack, overlayConsumer, i, OverlayTexture.NO_OVERLAY, -1);
        }
    }

    @Override
    public void onRenderInFirstPerson(LocalPlayer player, ItemStack itemStack, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, HumanoidArm arm) {
        GlovesModel model = new GlovesModel(GlovesModel.getTexturedModelData().bakeRoot());
        if (!(itemStack.getItem() instanceof KHChestplateAttachment khChestplate)) return;
        int color = DyedItemColor.getOrDefault(itemStack, khChestplate.getDefaultColor());
        ResourceLocation texturePath = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + ".png");

        VertexConsumer baseConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(texturePath));
        renderArm(player, model, poseStack, baseConsumer, light, color, arm);

        if (khChestplate.hasOverlay()) {
            ResourceLocation textureOverlayPath = ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                    BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + "_overlay.png");
            VertexConsumer overlayConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(textureOverlayPath));
            renderArm(player, model, poseStack, overlayConsumer, light, -1, arm);
        }
    }

    private void renderArm(LocalPlayer localPlayer, GlovesModel model, PoseStack poseStack, VertexConsumer consumer,
                           int light, int color, HumanoidArm arm) {
        PlayerModel<?> playerModel = ((PlayerModel<?>) ((LivingEntityRenderer<?, ?>)
                Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(localPlayer)).getModel());

        poseStack.pushPose();
        if (arm == HumanoidArm.RIGHT) {
            model.armorRightArm.copyFrom(playerModel.rightArm);
            model.armorRightArm.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
        } else {
            model.armorLeftArm.copyFrom(playerModel.leftArm);
            model.armorLeftArm.render(poseStack, consumer, light, OverlayTexture.NO_OVERLAY, color);
        }
        poseStack.popPose();
    }
}
