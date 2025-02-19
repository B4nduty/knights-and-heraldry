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
public class TrinketsArmModel extends BipedEntityModel<LivingEntity> {
	public final ModelPart armorRightArm;

	public TrinketsArmModel(ModelPart root) {
		super(root);
		this.armorRightArm = root.getChild("armorRightArm");
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.armorRightArm);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData armorRightArm = modelPartData.addChild("armorRightArm", ModelPartBuilder.create().uv(96, 16).cuboid(-5.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.35F))
				.uv(112, 15).cuboid(-5.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.45F))
				.uv(60, 108).cuboid(-5.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.55F))
				.uv(85, 58).cuboid(-5.5F, 3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new Dilation(-0.01F)), ModelTransform.pivot(-4.0F, 2.0F, 0.0F));

		armorRightArm.addChild("right_plate_rim_r1", ModelPartBuilder.create().uv(112, 18).cuboid(-1.0F, -5.0F, -3.5F, 0.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		armorRightArm.addChild("right_besagew_r1", ModelPartBuilder.create().uv(116, 45).cuboid(-1.0F, 0.0F, -4.3F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		armorRightArm.addChild("right_couter_r1", ModelPartBuilder.create().uv(112, 25).cuboid(-4.5F, 0.1F, 1.6F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
				.uv(48, 65).cuboid(-4.5F, 0.1F, 3.6F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorRightArm.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}