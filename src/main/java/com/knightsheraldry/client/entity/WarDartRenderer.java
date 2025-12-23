package com.knightsheraldry.client.entity;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.WarDartEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class WarDartRenderer extends EntityRenderer<WarDartEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(KnightsHeraldry.MOD_ID, "textures/item/wardart_3d.png");
    private final ItemRenderer itemRenderer;
    private final float scale;

    public WarDartRenderer(EntityRendererProvider.Context ctx, float scale) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
        this.scale = scale;
    }

    public WarDartRenderer(EntityRendererProvider.Context context) {
        this(context, 1.0F);
    }

    public void render(WarDartEntity warDartEntity, float yaw, float tickDelta, PoseStack poseStack, MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(tickDelta, warDartEntity.yRotO, warDartEntity.getYRot()) - 115.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(tickDelta, warDartEntity.xRotO, warDartEntity.getXRot()) - 90.0F));
        poseStack.translate(0, -1, 0);
        this.itemRenderer.renderStatic(warDartEntity.getStack(), ItemDisplayContext.FIRST_PERSON_LEFT_HAND, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, warDartEntity.level(), warDartEntity.getId());
        poseStack.popPose();
        super.render(warDartEntity, yaw, tickDelta, poseStack, multiBufferSource, light);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull WarDartEntity entity) {
        return TEXTURE;
    }
}