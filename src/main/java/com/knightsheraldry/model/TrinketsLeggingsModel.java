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
public class TrinketsLeggingsModel extends BipedEntityModel<LivingEntity> {
    private final ModelPart armorRightLeg;
    private final ModelPart armorLeftLeg;
    public TrinketsLeggingsModel(ModelPart root) {
        super(root);
        this.setVisible(false);
        this.armorRightLeg = root.getChild("armorRightLeg");
        this.armorLeftLeg = root.getChild("armorLeftLeg");
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.armorRightLeg, this.armorLeftLeg);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData armorRightLeg = modelPartData.addChild("armorRightLeg", ModelPartBuilder.create().uv(0, 74).mirrored().cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.36F)).mirrored(false), ModelTransform.pivot(-2.0F, 12.0F, -1.0F));

        armorRightLeg.addChild("right_poleyn_r1", ModelPartBuilder.create().uv(16, 82).cuboid(1.5F, 4.45F, 1.15F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData armorLeftLeg = modelPartData.addChild("armorLeftLeg", ModelPartBuilder.create().uv(0, 74).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.36F)), ModelTransform.pivot(2.0F, 12.0F, -1.0F));

        armorLeftLeg.addChild("left_poleyn_r1", ModelPartBuilder.create().uv(16, 82).mirrored().cuboid(-6.5F, 4.45F, 1.15F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.0F, 0.0F, 1.0F, -0.7854F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.armorRightLeg.copyTransform(this.rightLeg);
        this.armorRightLeg.render(matrices, vertices, light, overlay, red, green, blue, alpha);

        this.armorLeftLeg.copyTransform(this.leftLeg);
        this.armorLeftLeg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}