package banduty.knightsheraldry.client.item.armor;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.armor.attachment.KHCloak;
import banduty.knightsheraldry.model.CloakHoodModel;
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

public class KHCloakAttachmentRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, LivingEntity livingEntity,
                       ItemStack itemStack, HumanoidModel<LivingEntity> humanoidModel,
                       float v, float v1, float v2, float v3, float v4, float v5) {
        HumanoidModel<LivingEntity> model = new CloakHoodModel(CloakHoodModel.getTexturedModelData().bakeRoot());
        if (!(itemStack.getItem() instanceof KHCloak khCloak)) return;
        humanoidModel.copyPropertiesTo(model);
        model.setupAnim(livingEntity, v, v1, v3, v4, v5);
        VertexConsumer baseConsumer = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                        BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + ".png")));
        int color = DyedItemColor.getOrDefault(itemStack, khCloak.getDefaultColor());
        model.renderToBuffer(poseStack, baseConsumer, i, OverlayTexture.NO_OVERLAY, color);

        if (khCloak.hasOverlay()) {
            VertexConsumer baseConsumer2 = multiBufferSource.getBuffer(RenderType.armorCutoutNoCull(
                    ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/" +
                            BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getPath() + "_overlay.png")));
            model.renderToBuffer(poseStack, baseConsumer2, i, OverlayTexture.NO_OVERLAY, -1);
        }
    }
}
