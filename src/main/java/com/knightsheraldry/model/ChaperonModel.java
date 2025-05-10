package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class ChaperonModel extends BipedEntityModel<LivingEntity> {
    private final ModelPart armorHead;
    public ChaperonModel(ModelPart root) {
        super(root);
        this.setVisible(false);
        this.armorHead = root.getChild("armorHead");
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.armorHead);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData armorHead = modelPartData.addChild("armorHead", ModelPartBuilder.create().uv(88, 31).cuboid(-4.5F, -8.5F, -4.5F, 9.0F, 2.0F, 9.0F, new Dilation(0.2F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        armorHead.addChild("cube_r1", ModelPartBuilder.create().uv(116, 42).cuboid(3.65F, -2.05F, -3.0F, 2.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        armorHead.addChild("cube_r2", ModelPartBuilder.create().uv(120, 42).cuboid(4.0F, -7.75F, -3.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 0.0F, 0.0F, -0.1309F, 0.0F, -0.1309F));

        armorHead.addChild("cube_r3", ModelPartBuilder.create().uv(102, 0).cuboid(-7.0F, -8.0F, -4.0F, 5.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        armorHead.addChild("cube_r4", ModelPartBuilder.create().uv(84, 18).cuboid(-5.5F, -1.0F, -5.5F, 11.0F, 2.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
        return TexturedModelData.of(modelData, 128, 64);
    }

    @Override
    public void setAngles(LivingEntity livingEntity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.armorHead.copyTransform(this.head);
        this.armorHead.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}