package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.attachment.KHChaperon;
import banduty.knightsheraldry.model.ChaperonModel;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class KHChaperonAttachmentRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, LivingEntity livingEntity, ItemStack itemStack, HumanoidModel<LivingEntity> humanoidModel, float v, float v1, float v2, float v3, float v4, float v5) {
        HumanoidModel<LivingEntity> model = new ChaperonModel(ChaperonModel.getTexturedModelData().bakeRoot());
        if (!(itemStack.getItem() instanceof KHChaperon khChaperon)) return;
        humanoidModel.copyPropertiesTo(model);
        VertexConsumer baseConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/chaperon.png")));
        int color = DyedItemColor.getOrDefault(itemStack, khChaperon.getDefaultColor());
        model.renderToBuffer(poseStack, baseConsumer, i, OverlayTexture.NO_OVERLAY, color);

        if (khChaperon.hasOverlay()) {
            VertexConsumer baseConsumer2 = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(
                    ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/chaperon_overlay.png")));
            model.renderToBuffer(poseStack, baseConsumer2, i, OverlayTexture.NO_OVERLAY, -1);
        }
    }
}
