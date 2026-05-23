package banduty.knightsheraldry.client.item.deco;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.items.item.TwoLayerDyeableDeco;
import banduty.knightsheraldry.model.HelmetDecoModel;
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

public class TwoLayerDyeableDecoRenderer implements ArmorAttachmentRenderer {
    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity entity, ItemStack decoStack, HumanoidModel<LivingEntity> contextModel) {
        HumanoidModel<LivingEntity> model = new HelmetDecoModel(HelmetDecoModel.getTexturedModelData().bakeRoot());
        if (!(decoStack.getItem() instanceof TwoLayerDyeableDeco)) return;
        contextModel.copyPropertiesTo(model);
        model.setupAnim(entity, entity.walkAnimation.position(), entity.walkAnimation.speed(),
                (float) entity.tickCount + Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true),
                entity.getYHeadRot() - entity.yBodyRot,
                entity.getXRot());

        VertexConsumer baseConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/deco/" +
                        BuiltInRegistries.ITEM.getKey(decoStack.getItem()).getPath() + "_base.png")));
        int colorBase = TwoLayerDyeableDeco.getColor1(decoStack);
        model.renderToBuffer(poseStack, baseConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorBase);

        VertexConsumer stripeConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(
                ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID, "textures/entity/attachment/deco/" +
                        BuiltInRegistries.ITEM.getKey(decoStack.getItem()).getPath() + "_stripe.png")));
        int colorStripe = TwoLayerDyeableDeco.getColor2(decoStack);
        model.renderToBuffer(poseStack, stripeConsumer, packedLight, OverlayTexture.NO_OVERLAY, colorStripe);
    }
}
