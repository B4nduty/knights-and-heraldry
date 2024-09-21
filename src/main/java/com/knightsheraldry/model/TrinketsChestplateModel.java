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
public class TrinketsChestplateModel extends BipedEntityModel<LivingEntity> {
	public final ModelPart head;
	public final ModelPart hat;
	public final ModelPart body;
	public final ModelPart rightArm;
	public final ModelPart leftArm;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;
	private final ModelPart armorBody;
	private final ModelPart armorRightArm;
	private final ModelPart armorLeftArm;

	public TrinketsChestplateModel(ModelPart root) {
		super(root);
		this.setVisible(false);
		this.head = root.getChild("head");
		this.hat = root.getChild("hat");
		this.body = root.getChild("body");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
		this.armorBody = root.getChild("armorBody");
		this.armorRightArm = root.getChild("armorRightArm");
		this.armorLeftArm = root.getChild("armorLeftArm");
	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.hat,
				this.armorBody, this.armorRightArm, this.armorLeftArm);
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData armorBody = modelPartData.addChild("armorBody", ModelPartBuilder.create().uv(80, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 10.0F, 4.0F, new Dilation(0.4F))
				.uv(93, 112).cuboid(-5.0F, 0.0F, -3.3F, 10.0F, 4.0F, 6.0F, new Dilation(0.65F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		armorBody.addChild("breastplate_2_r1", ModelPartBuilder.create().uv(72, 24).cuboid(-4.5F, 2.8F, -4.9F, 9.0F, 5.0F, 3.0F, new Dilation(-0.01F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		armorBody.addChild("breastplate_1_r1", ModelPartBuilder.create().uv(72, 16).cuboid(-4.5F, 0.0F, -2.65F, 9.0F, 5.0F, 3.0F, new Dilation(-0.01F))
				.uv(0, 65).cuboid(-4.5F, 9.0F, -0.1F, 9.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		armorBody.addChild("backfauld_r1", ModelPartBuilder.create().uv(26, 65).cuboid(-4.5F, 9.0F, -3.85F, 9.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		armorBody.addChild("leftTasset", ModelPartBuilder.create().uv(16, 74).mirrored().cuboid(-2.5F, 0.0F, 0.1F, 4.0F, 4.0F, 4.0F, new Dilation(0.1F)).mirrored(false), ModelTransform.pivot(3.0F, 11.5F, -3.5F));

		armorBody.addChild("rightTasset", ModelPartBuilder.create().uv(16, 74).cuboid(-1.5F, 0.0F, 0.1F, 4.0F, 4.0F, 4.0F, new Dilation(0.1F)), ModelTransform.pivot(-3.0F, 11.5F, -3.5F));

		armorBody.addChild("surcoatFront", ModelPartBuilder.create(), ModelTransform.of(0.0F, 8.5F, -2.5F, -0.4363F, 0.0F, 0.0F));

		armorBody.addChild("surcoatBack", ModelPartBuilder.create(), ModelTransform.of(0.0F, 8.5F, 2.5F, 0.2618F, 0.0F, 0.0F));

		ModelPartData surcoat = armorBody.addChild("surcoat", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		surcoat.addChild("back_surcoat_r1", ModelPartBuilder.create().uv(40, 97).cuboid(-4.5F, 0.0F, -0.45F, 9.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		surcoat.addChild("front_surcoat_r1", ModelPartBuilder.create().uv(40, 97).cuboid(-4.5F, 0.0F, -2.65F, 9.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		surcoat.addChild("belt", ModelPartBuilder.create().uv(0, 123).cuboid(-5.0F, 8.75F, -3.25F, 10.0F, 1.0F, 4.0F, new Dilation(0.0F))
				.uv(28, 123).cuboid(-5.0F, 8.75F, -0.75F, 10.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData top_back_surcoat = surcoat.addChild("top_back_surcoat", ModelPartBuilder.create().uv(40, 105).cuboid(-4.5F, -0.2F, 0.0F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.5F, 3.1F));

		top_back_surcoat.addChild("bottom_back_surcoat", ModelPartBuilder.create().uv(40, 110).cuboid(-4.5F, 0.0F, 0.0F, 9.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.8F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData top_front_surcoat = surcoat.addChild("top_front_surcoat", ModelPartBuilder.create().uv(40, 105).cuboid(-4.5F, -0.1F, 0.0F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.2F, -3.9F, 0.2182F, 0.0F, 0.0F));

		top_front_surcoat.addChild("bottom_front_surcoat", ModelPartBuilder.create().uv(40, 110).cuboid(-4.5F, 0.0F, 0.0F, 9.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.8913F, 0.0004F, -0.5236F, 0.0F, 0.0F));

		ModelPartData armorRightArm = modelPartData.addChild("armorRightArm", ModelPartBuilder.create().uv(96, 16).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.35F))
				.uv(112, 15).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.45F))
				.uv(60, 108).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.55F))
				.uv(85, 58).cuboid(-4.5F, 3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new Dilation(-0.01F)), ModelTransform.pivot(-3.0F, 2.0F, 0.0F));

		armorRightArm.addChild("right_plate_rim_r1", ModelPartBuilder.create().uv(112, 18).cuboid(-1.0F, -5.0F, -3.5F, 0.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		armorRightArm.addChild("right_besagew_r1", ModelPartBuilder.create().uv(116, 45).cuboid(-1.0F, 0.0F, -4.3F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		armorRightArm.addChild("right_couter_r1", ModelPartBuilder.create().uv(112, 25).cuboid(-4.5F, 0.1F, 1.6F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
				.uv(48, 65).cuboid(-4.5F, 0.1F, 3.6F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData armorLeftArm = modelPartData.addChild("armorLeftArm", ModelPartBuilder.create().uv(96, 16).mirrored().cuboid(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.35F)).mirrored(false)
				.uv(112, 15).mirrored().cuboid(0.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.45F)).mirrored(false)
				.uv(60, 108).mirrored().cuboid(0.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.55F)).mirrored(false)
				.uv(85, 58).mirrored().cuboid(-0.5F, 3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new Dilation(-0.01F)).mirrored(false), ModelTransform.pivot(3.0F, 2.0F, 0.0F));

		armorLeftArm.addChild("left_plate_rim_r1", ModelPartBuilder.create().uv(112, 18).cuboid(1.0F, -5.0F, -3.5F, 0.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		armorLeftArm.addChild("left_besagew_r1", ModelPartBuilder.create().uv(116, 45).cuboid(-2.0F, 0.0F, -4.3F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		armorLeftArm.addChild("left_couter_r1", ModelPartBuilder.create().uv(112, 25).cuboid(4.5F, 0.1F, 1.6F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
				.uv(48, 65).mirrored().cuboid(-0.5F, 0.1F, 3.6F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorBody.copyTransform(this.body);
		this.armorBody.render(matrices, vertices, light, OverlayTexture.DEFAULT_UV);

		this.armorRightArm.copyTransform(this.rightArm);
		this.armorRightArm.render(matrices, vertices, light, OverlayTexture.DEFAULT_UV);

		this.armorLeftArm.copyTransform(this.leftArm);
		this.armorLeftArm.render(matrices, vertices, light, OverlayTexture.DEFAULT_UV);
	}
}