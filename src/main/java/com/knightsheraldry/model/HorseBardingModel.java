package com.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.knightsheraldry.items.armor.horse.HorseBardingArmorItem;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class HorseBardingModel<T extends AbstractHorseEntity> extends AnimalModel<T> {
	private final ModelPart head;
	public final ModelPart plume;
	private final ModelPart mouth;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart neck;
	private final ModelPart mane;
	private final ModelPart body;
	private final ModelPart saddle;
	private final ModelPart headpiece;

	public HorseBardingModel(ModelPart root) {
		this.head = root.getChild("head");
		this.mouth = root.getChild("mouth");
		this.left_ear = root.getChild("left_ear");
		this.right_ear = root.getChild("right_ear");
		this.neck = root.getChild("neck");
		this.mane = root.getChild("mane");
		this.body = root.getChild("body");
		this.saddle = this.body.getChild("saddle");
		this.headpiece = root.getChild("headpiece");
		this.plume = root.getChild("plume");
	}

	public Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.mouth, this.left_ear, this.right_ear, this.neck, this.mane, this.body, this.saddle);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, -11.0F, -2.0F, 6.0F, 5.0F, 7.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 2.0F, -9.0F));

		modelPartData.addChild("mouth", ModelPartBuilder.create().uv(0, 25).cuboid(-2.0F, -11.0F, -7.2F, 4.0F, 5.0F, 5.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 2.0F, -9.0F));

		modelPartData.addChild("left_ear", ModelPartBuilder.create().uv(19, 16).cuboid(0.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -9.01F));

		modelPartData.addChild("right_ear", ModelPartBuilder.create().uv(19, 16).cuboid(-2.55F, -13.0F, 4.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -9.01F));

		modelPartData.addChild("neck", ModelPartBuilder.create().uv(0, 33).cuboid(-2.05F, -6.0F, -2.0F, 4.0F, 12.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, -9.0F));

		modelPartData.addChild("mane", ModelPartBuilder.create().uv(56, 36).cuboid(-1.0F, -11.0F, 5.01F, 2.0F, 16.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 2.0F, -9.01F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(53, 1).cuboid(-5.5F, -6.0F, -21.5F, 11.0F, 11.0F, 23.0F, new Dilation(0.05F))
				.uv(50, 41).cuboid(-5.5F, 1.5F, -21.5F, 11.0F, 8.0F, 23.0F, new Dilation(0.0F))
				.uv(28, 103).cuboid(-6.5F, -10.5F, -15.0F, 13.0F, 10.0F, 2.0F, new Dilation(0.0F))
				.uv(58, 103).cuboid(-6.5F, -8.5F, -5.0F, 13.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.5F, 9.0F));

		body.addChild("body_r1", ModelPartBuilder.create().uv(58, 75).cuboid(-5.0F, -3.6F, -2.2F, 10.0F, 7.0F, 4.0F, new Dilation(0.06F)), ModelTransform.of(0.0F, -1.9F, 1.2F, 0.5672F, 0.0F, 0.0F));

		body.addChild("body_r2", ModelPartBuilder.create().uv(30, 75).cuboid(-5.0F, -3.6F, -1.8F, 10.0F, 7.0F, 4.0F, new Dilation(0.06F)), ModelTransform.of(0.0F, 0.5F, -21.2F, -0.5672F, 0.0F, 0.0F));

		body.addChild("body_r3", ModelPartBuilder.create().uv(69, 92).cuboid(-1.8F, -3.6F, -11.0F, 4.0F, 7.0F, 22.0F, new Dilation(0.04F)), ModelTransform.of(-5.2F, 0.5F, -10.0F, 0.0F, 0.0F, 0.5672F));

		body.addChild("body_r4", ModelPartBuilder.create().uv(0, 64).cuboid(-2.1F, -3.6F, -11.0F, 4.0F, 7.0F, 22.0F, new Dilation(0.04F)), ModelTransform.of(5.1F, 0.5F, -10.0F, 0.0F, 0.0F, -0.5672F));

		body.addChild("saddle", ModelPartBuilder.create().uv(30, 2).cuboid(-6.0F, -6.3F, -11.0F, 12.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -2.5F));

		modelPartData.addChild("headpiece", ModelPartBuilder.create().uv(30, 68).cuboid(-0.5F, -14.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(30, 64).cuboid(-1.5F, -12.0F, -1.5F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, -9.0F));

		modelPartData.addChild("plume", ModelPartBuilder.create().uv(96, 80).cuboid(0.0F, -19.0F, 3.0F, 0.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, -9.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		mouth.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		left_ear.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		right_ear.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		neck.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		mane.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		headpiece.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		boolean bl = entity.isSaddled();
		ItemStack armorStack = ItemStack.EMPTY;
		if (entity instanceof HorseEntity horseEntity) armorStack = horseEntity.getArmorType();

		this.plume.visible = armorStack.getItem() instanceof HorseBardingArmorItem;

		this.saddle.visible = bl;
	}

	@Override
	public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
		super.animateModel(entity, limbAngle, limbDistance, tickDelta);
		float i = MathHelper.lerpAngleDegrees(tickDelta, entity.prevBodyYaw, entity.bodyYaw);
		float j = MathHelper.lerpAngleDegrees(tickDelta, entity.prevHeadYaw, entity.headYaw);
		float k = MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch());
		float l = j - i;
		float m = k * ((float)Math.PI / 180F);
		if (l > 20.0F) {
			l = 20.0F;
		}

		if (l < -20.0F) {
			l = -20.0F;
		}

		if (limbDistance > 0.2F) {
			m += MathHelper.cos(limbAngle * 0.8F) * 0.15F * limbDistance;
		}

		float n = entity.getEatingGrassAnimationProgress(tickDelta);
		float o = entity.getAngryAnimationProgress(tickDelta);
		float p = 1.0F - o;
		float q = entity.getEatingAnimationProgress(tickDelta);
		float r = (float)entity.age + tickDelta;
		this.head.pivotY = 4.0F;
		this.head.pivotZ = -12.0F;
		this.body.pitch = 0.0F;
		this.head.pitch = ((float)Math.PI / 6F) + m;
		this.head.yaw = l * ((float)Math.PI / 180F);
		float v = (1.0F - Math.max(o, n)) * (((float)Math.PI / 6F) + m + q * MathHelper.sin(r) * 0.05F);
		this.head.pitch = o * (0.2617994F + m) + n * (2.1816616F + MathHelper.sin(r) * 0.05F) + v;
		this.head.yaw = o * l * ((float)Math.PI / 180F) + (1.0F - Math.max(o, n)) * this.head.yaw;
		this.head.pivotY = o * -4.0F + n * 11.0F + (1.0F - Math.max(o, n)) * this.head.pivotY;
		this.head.pivotZ = o * -4.0F + n * -12.0F + (1.0F - Math.max(o, n)) * this.head.pivotZ;

		this.headpiece.pivotY = this.head.pivotY;
		this.headpiece.pivotZ = this.head.pivotZ;
		this.headpiece.pitch = this.head.pitch;
		this.headpiece.yaw = this.head.yaw;

		this.mouth.pivotY = this.head.pivotY;
		this.mouth.pivotZ = this.head.pivotZ;
		this.mouth.pitch = this.head.pitch;
		this.mouth.yaw = this.head.yaw;

		this.mane.pivotY = this.head.pivotY;
		this.mane.pivotZ = this.head.pivotZ;
		this.mane.pitch = this.head.pitch;
		this.mane.yaw = this.head.yaw;

		this.neck.pivotY = this.head.pivotY;
		this.neck.pivotZ = this.head.pivotZ;
		this.neck.pitch = this.head.pitch;
		this.neck.yaw = this.head.yaw;

		this.left_ear.pivotY = this.head.pivotY;
		this.left_ear.pivotZ = this.head.pivotZ;
		this.left_ear.pitch = this.head.pitch;
		this.left_ear.yaw = this.head.yaw;

		this.right_ear.pivotY = this.head.pivotY;
		this.right_ear.pivotZ = this.head.pivotZ;
		this.right_ear.pitch = this.head.pitch;
		this.right_ear.yaw = this.head.yaw;

		this.plume.pivotY = this.head.pivotY;
		this.plume.pivotZ = this.head.pivotZ;
		this.plume.pitch = this.head.pitch;
		this.plume.yaw = this.head.yaw;

		this.body.pitch = o * (-(float)Math.PI / 4F) + p * this.body.pitch;
	}
}