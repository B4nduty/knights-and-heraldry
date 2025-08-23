package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.ItemStack;

public class HorseBardingModel<T extends AbstractHorseEntity> extends HorseEntityModel<T> {
	private final ModelPart armorHead;
	public final ModelPart plume;
	private final ModelPart mouth;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart neck;
	private final ModelPart mane;
	private final ModelPart armorBody;
	private final ModelPart saddle;
	private final ModelPart headpiece;

	public HorseBardingModel(ModelPart root) {
		super(root);

		this.armorHead = root.getChild("armorHead");
		this.plume = root.getChild("plume");
		this.mouth = this.armorHead.getChild("mouth");
		this.left_ear = this.armorHead.getChild("left_ear");
		this.right_ear = this.armorHead.getChild("right_ear");
		this.neck = this.armorHead.getChild("neck");
		this.mane = this.armorHead.getChild("mane");
		this.headpiece = this.armorHead.getChild("headpiece");
		this.armorBody = root.getChild("armorBody");
		this.saddle = this.armorBody.getChild("saddle");
	}

	public Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.armorHead, this.mouth, this.left_ear, this.right_ear, this.neck, this.mane, this.headpiece);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.armorBody, this.saddle);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = HorseEntityModel.getModelData(Dilation.NONE);
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData armorHead = modelPartData.addChild("armorHead", ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 2.0F, -9.0F));

		modelPartData.addChild("plume", ModelPartBuilder.create().uv(96, 80).cuboid(0.0F, -19.0F, 3.0F, 0.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		armorHead.addChild("mouth", ModelPartBuilder.create().uv(0, 25).cuboid(-2.0F, -11.0F, -7.2F, 4.0F, 5.0F, 5.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		armorHead.addChild("left_ear", ModelPartBuilder.create().uv(19, 16).cuboid(0.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -0.01F));

		armorHead.addChild("right_ear", ModelPartBuilder.create().uv(19, 16).cuboid(-2.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -0.01F));

		armorHead.addChild("neck", ModelPartBuilder.create().uv(0, 33).cuboid(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		armorHead.addChild("mane", ModelPartBuilder.create().uv(56, 36).cuboid(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 0.0F, -0.01F));

		armorHead.addChild("headpiece", ModelPartBuilder.create().uv(30, 68).cuboid(-0.5F, -14.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(30, 64).cuboid(-1.5F, -12.0F, -1.5F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData armorBody = modelPartData.addChild("armorBody", ModelPartBuilder.create().uv(53, 1).cuboid(-5.5F, -6.0F, -21.5F, 11.0F, 11.0F, 23.0F, new Dilation(0.05F))
				.uv(50, 41).cuboid(-5.5F, 1.5F, -21.5F, 11.0F, 8.0F, 23.0F, new Dilation(0.0F))
				.uv(28, 103).cuboid(-6.5F, -10.5F, -15.0F, 13.0F, 10.0F, 2.0F, new Dilation(0.0F))
				.uv(58, 103).cuboid(-6.5F, -8.5F, -5.0F, 13.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.5F, 9.0F));

		armorBody.addChild("body_r1", ModelPartBuilder.create().uv(58, 75).cuboid(-5.0F, -3.6F, -2.2F, 10.0F, 7.0F, 4.0F, new Dilation(0.06F)), ModelTransform.of(0.0F, -1.9F, 1.2F, 0.5672F, 0.0F, 0.0F));

		armorBody.addChild("body_r2", ModelPartBuilder.create().uv(30, 75).cuboid(-5.0F, -3.6F, -1.8F, 10.0F, 7.0F, 4.0F, new Dilation(0.06F)), ModelTransform.of(0.0F, 0.5F, -21.2F, -0.5672F, 0.0F, 0.0F));

		armorBody.addChild("body_r3", ModelPartBuilder.create().uv(69, 92).cuboid(-1.8F, -3.6F, -11.0F, 4.0F, 7.0F, 22.0F, new Dilation(0.04F)), ModelTransform.of(-5.2F, 0.5F, -10.0F, 0.0F, 0.0F, 0.5672F));

		armorBody.addChild("body_r4", ModelPartBuilder.create().uv(0, 64).cuboid(-2.1F, -3.6F, -11.0F, 4.0F, 7.0F, 22.0F, new Dilation(0.04F)), ModelTransform.of(5.1F, 0.5F, -10.0F, 0.0F, 0.0F, -0.5672F));

		armorBody.addChild("saddle", ModelPartBuilder.create().uv(30, 2).cuboid(-6.0F, -6.3F, -11.0F, 12.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -2.5F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorHead.copyTransform(this.head);
		armorHead.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		this.armorBody.copyTransform(this.body);
		this.armorBody.pivotY += 8f;
		this.armorBody.pivotZ += 3.75f;
		armorBody.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.saddle.visible = entity.isSaddled();
		ItemStack armorStack = ItemStack.EMPTY;
		if (entity instanceof HorseEntity horseEntity) armorStack = horseEntity.getArmorType();
        this.plume.visible = armorStack.getNbt() != null && armorStack.getNbt().contains("kh_plume");
	}

	@Override
	public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
		super.animateModel(entity, limbAngle, limbDistance, tickDelta);
	}
}