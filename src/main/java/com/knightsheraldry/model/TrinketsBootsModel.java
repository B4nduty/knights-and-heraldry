package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class TrinketsBootsModel extends BipedEntityModel<LivingEntity> {
	public final ModelPart head;
	public final ModelPart hat;
	public final ModelPart body;
	public final ModelPart rightArm;
	public final ModelPart leftArm;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;
	private final ModelPart armorRightBoot;
	private final ModelPart armorLeftBoot;
	public TrinketsBootsModel(ModelPart root) {
		super(root);
		this.setVisible(false);
		this.head = root.getChild("head");
		this.hat = root.getChild("hat");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
		this.armorRightBoot = root.getChild("armorRightBoot");
		this.armorLeftBoot = root.getChild("armorLeftBoot");
	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.hat,
				this.armorRightBoot, this.armorLeftBoot);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData armorRightBoot = modelPartData.addChild("armorRightBoot", ModelPartBuilder.create().uv(12, 89).cuboid(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.45F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		armorRightBoot.addChild("right_sabaton_r1", ModelPartBuilder.create().uv(28, 93).cuboid(-3.25F, 11.25F, -3.25F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData armorLeftBoot = modelPartData.addChild("armorLeftBoot", ModelPartBuilder.create().uv(12, 89).mirrored().cuboid(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.45F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		armorLeftBoot.addChild("left_sabaton_r1", ModelPartBuilder.create().uv(28, 93).cuboid(-3.25F, 11.25F, -3.25F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorRightBoot.copyTransform(this.rightLeg);
		this.armorRightBoot.render(matrices, vertices, light, OverlayTexture.DEFAULT_UV);

		this.armorLeftBoot.copyTransform(this.leftLeg);
		this.armorLeftBoot.render(matrices, vertices, light, OverlayTexture.DEFAULT_UV);
	}
}