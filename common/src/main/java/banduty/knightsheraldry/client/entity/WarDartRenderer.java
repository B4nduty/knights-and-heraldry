package banduty.knightsheraldry.client.entity;

import banduty.knightsheraldry.KnightsHeraldry;
import banduty.knightsheraldry.entity.custom.WarDartEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class WarDartRenderer extends EntityRenderer<WarDartEntity> {
    private final WarDartModel model;
    public static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(KnightsHeraldry.MOD_ID,
                    "textures/entity/wardart/wardart.png");

    public WarDartRenderer(EntityRendererProvider.Context ctx, float scale) {
        super(ctx);
        this.model = new WarDartModel(ctx.bakeLayer(WarDartModel.LAYER_LOCATION));
    }

    public WarDartRenderer(EntityRendererProvider.Context context) {
        this(context, 1.0F);
    }

    public void render(WarDartEntity warDartEntity, float yaw, float tickDelta, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(tickDelta, warDartEntity.yRotO, warDartEntity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(tickDelta, warDartEntity.xRotO, warDartEntity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.model.renderType(this.getTextureLocation(warDartEntity)), false, warDartEntity.isFoil());
        this.model.renderToBuffer(poseStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(warDartEntity, yaw, tickDelta, poseStack, multiBufferSource, light);
    }

    public ResourceLocation getTextureLocation(WarDartEntity entity) {
        return TEXTURE;
    }
}