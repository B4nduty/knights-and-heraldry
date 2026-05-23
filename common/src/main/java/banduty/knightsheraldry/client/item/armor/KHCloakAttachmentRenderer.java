package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.attachment.KHCloak;
import banduty.knightsheraldry.model.CloakHoodModel;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class KHCloakAttachmentRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity entity, ItemStack itemStack, HumanoidModel<LivingEntity> contextModel) {
        HumanoidModel<LivingEntity> model = new CloakHoodModel(CloakHoodModel.getTexturedModelData().bakeRoot());
        if (!(itemStack.getItem() instanceof KHCloak khCloak)) return;
        contextModel.copyPropertiesTo(model);
        model.setupAnim(entity, entity.walkAnimation.position(), entity.walkAnimation.speed(),
                (float) entity.tickCount + Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true),
                entity.getYHeadRot() - entity.yBodyRot,
                entity.getXRot());
        VertexConsumer baseConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                        BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + ".png")));
        int color = DyedItemColor.getOrDefault(itemStack, khCloak.getDefaultColor());
        model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);

        if (khCloak.hasOverlay()) {
            VertexConsumer baseConsumer2 = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                    ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                            BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + "_overlay.png")));
            model.renderToBuffer(poseStack, baseConsumer2, packedLight, OverlayTexture.NO_OVERLAY, -1);
        }
    }
}
