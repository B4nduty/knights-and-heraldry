package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.attachment.KHLeggingsAttachment;
import banduty.knightsheraldry.model.AttachmentLeggingsModel;
import banduty.stoneycore.client.render.ArmorAttachmentRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class KHLeggingsAttachmentRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity entity, ItemStack itemStack, HumanoidModel<LivingEntity> contextModel) {
        HumanoidModel<LivingEntity> model = new AttachmentLeggingsModel(AttachmentLeggingsModel.getTexturedModelData().bakeRoot());
        if (!(itemStack.getItem() instanceof KHLeggingsAttachment khLeggingsAttachment)) return;
        contextModel.copyPropertiesTo(model);
        VertexConsumer baseConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                        BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + ".png")));
        int color = DyedItemColor.getOrDefault(itemStack, khLeggingsAttachment.getDefaultColor());
        model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);
    }
}
