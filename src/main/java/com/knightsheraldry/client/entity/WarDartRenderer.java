package com.knightsheraldry.client.entity;

import com.knightsheraldry.KnightsHeraldry;
import com.knightsheraldry.entity.custom.WarDartEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class WarDartRenderer extends EntityRenderer<WarDartEntity> {
    public static final Identifier TEXTURE = new Identifier(KnightsHeraldry.MOD_ID, "textures/item/wardart_3d.png");
    private final ItemRenderer itemRenderer;
    private final float scale;

    public WarDartRenderer(EntityRendererFactory.Context ctx, float scale) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
        this.scale = scale;
    }

    public WarDartRenderer(EntityRendererFactory.Context context) {
        this(context, 1.0F);
    }

    public void render(WarDartEntity warDartEntity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, warDartEntity.prevYaw, warDartEntity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, warDartEntity.prevPitch, warDartEntity.getPitch()) + 90.0F));
        this.itemRenderer.renderItem(warDartEntity.getStack(), ModelTransformationMode.FIXED, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, warDartEntity.getWorld(), warDartEntity.getId());
        matrices.pop();
        super.render(warDartEntity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    public Identifier getTexture(WarDartEntity entity) {
        return TEXTURE;
    }
}