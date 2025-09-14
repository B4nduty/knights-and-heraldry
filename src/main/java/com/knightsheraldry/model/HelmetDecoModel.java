package com.knightsheraldry.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class HelmetDecoModel extends BipedEntityModel<LivingEntity> {
	private final ModelPart armorHead;
	public HelmetDecoModel(ModelPart root) {
        super(root);
        this.setVisible(false);
		this.armorHead = root.getChild("armorHead");
	}
	public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
		ModelPartData modelPartData = modelData.getRoot();
        ModelPartData armorHead = modelPartData.addChild("armorHead", ModelPartBuilder.create().uv(0, 43).cuboid(0.0F, -22.5F, -11.0F, 0.0F, 22.0F, 22.0F, new Dilation(0.0F))
                .uv(45, 88).cuboid(-11.0F, -22.5F, 0.0F, 22.0F, 22.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		armorHead.addChild("cube_r1", ModelPartBuilder.create().uv(81, 110).cuboid(-1.5F, 1.5F, 0.0F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 5.5F, 0.5672F, 0.0F, 0.0F));

		armorHead.addChild("crown_r1", ModelPartBuilder.create().uv(48, 110).cuboid(-5.5F, -10.0F, -6.05F, 11.0F, 5.0F, 11.0F, new Dilation(0.05F))
		.uv(0, 110).cuboid(-6.0F, -7.0F, -6.85F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        armorHead.addChild("plume_r1", ModelPartBuilder.create().uv(0, 88).cuboid(-11.0F, -46.0F, 0.0F, 22.0F, 22.0F, 0.0F, new Dilation(0.0F))
                .uv(45, 43).cuboid(0.0F, -46.0F, -11.0F, 0.0F, 22.0F, 22.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 23.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData fluff = armorHead.addChild("fluff", ModelPartBuilder.create(), ModelTransform.of(0.0F, -11.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        fluff.addChild("plume_fluffy_r1", ModelPartBuilder.create().uv(0, -3).cuboid(-0.3F, -13.0F, -11.0F, 0.0F, 22.0F, 22.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6109F, -0.2182F, 0.0F));

        fluff.addChild("plume_fluffy_r2", ModelPartBuilder.create().uv(0, -3).cuboid(0.3F, -13.0F, -11.0F, 0.0F, 22.0F, 22.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.2182F, 0.0F));

        fluff.addChild("plume_fluffy_r3", ModelPartBuilder.create().uv(45, -3).cuboid(-0.3F, -46.0F, -11.0F, 0.0F, 22.0F, 22.0F, new Dilation(0.0F))
                .uv(0, 42).cuboid(-11.0F, -46.0F, -0.3F, 22.0F, 22.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 33.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.armorHead.copyTransform(this.head);
        this.armorHead.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}