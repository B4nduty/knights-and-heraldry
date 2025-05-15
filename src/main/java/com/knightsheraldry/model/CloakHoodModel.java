package com.knightsheraldry.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class CloakHoodModel extends BipedEntityModel<LivingEntity> {
	public final ModelPart armorHead;
	public final ModelPart armorBody;
	public final ModelPart armorRightArm;
	public final ModelPart armorLeftArm;
	public float cloakPitch;
	public float cloakYaw;
	public float cloakRoll;
	public CloakHoodModel(ModelPart root) {
        super(root);
        this.armorHead = root.getChild("armorHead");
		this.armorBody = root.getChild("armorBody");
		this.armorRightArm = root.getChild("armorRightArm");
		this.armorLeftArm = root.getChild("armorLeftArm");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData armorHead = modelPartData.addChild("armorHead", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData hood_head = armorHead.addChild("hood_head", ModelPartBuilder.create().uv(40, 0).cuboid(-4.5F, -8.5F, -6.0F, 9.0F, 9.0F, 4.0F, new Dilation(0.2F))
				.uv(40, 13).cuboid(-4.5F, -8.5F, -1.6F, 9.0F, 9.0F, 6.0F, new Dilation(0.2F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		hood_head.addChild("hood_tail_r1", ModelPartBuilder.create().uv(70, 16).cuboid(-1.0F, -22.15F, 9.13F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		hood_head.addChild("hood_tail_r2", ModelPartBuilder.create().uv(66, 8).cuboid(-2.5F, -26.15F, 10.33F, 5.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		hood_head.addChild("hood_tail_r3", ModelPartBuilder.create().uv(66, 0).cuboid(-3.5F, -23.95F, 19.33F, 7.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

		ModelPartData jester = hood_head.addChild("jester", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		jester.addChild("cube_r1", ModelPartBuilder.create().uv(119, 0).mirrored().cuboid(-9.25F, -9.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.25F, 0.0F, 0.5F, 0.0F, 0.0F, 0.1309F));

		jester.addChild("cube_r2", ModelPartBuilder.create().uv(119, 0).cuboid(7.25F, -9.0F, -1.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, 0.0F, 0.5F, 0.0F, 0.0F, -0.1309F));

		jester.addChild("hood_r1", ModelPartBuilder.create().uv(110, 20).cuboid(-2.5F, -9.1F, 7.55F, 5.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		jester.addChild("hood_r2", ModelPartBuilder.create().uv(80, 20).cuboid(-4.5F, -8.5F, 2.4F, 9.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		jester.addChild("ear_r1", ModelPartBuilder.create().uv(120, 0).cuboid(-4.5F, -11.0F, -2.0F, 1.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, 0.0F, 0.5F, 0.0F, 0.0F, -0.1745F));

		jester.addChild("ear_r2", ModelPartBuilder.create().uv(120, 0).cuboid(3.5F, -11.0F, -2.0F, 1.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, 0.0F, 0.5F, 0.0F, 0.0F, 0.1745F));

		ModelPartData bell = jester.addChild("bell", ModelPartBuilder.create(), ModelTransform.of(0.0F, -8.5F, 8.0F, 0.3491F, 0.0F, 0.0F));

		bell.addChild("hood_r3", ModelPartBuilder.create().uv(113, 0).cuboid(-1.5F, -1.5F, 0.05F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData legacy = hood_head.addChild("legacy", ModelPartBuilder.create().uv(0, 48).cuboid(-4.5F, -9.5F, -0.9F, 9.0F, 10.0F, 6.0F, new Dilation(0.6F))
				.uv(0, 32).cuboid(-4.5F, -9.5F, -8.1F, 9.0F, 10.0F, 6.0F, new Dilation(0.6F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		legacy.addChild("hood_tail_r4", ModelPartBuilder.create().uv(0, 20).cuboid(-4.5F, -23.95F, 19.33F, 9.0F, 9.0F, 3.0F, new Dilation(0.59F)), ModelTransform.of(0.0F, 24.2F, 1.0F, 0.6109F, 0.0F, 0.0F));

		ModelPartData armorBody = modelPartData.addChild("armorBody", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		armorBody.addChild("cloak", ModelPartBuilder.create().uv(34, 36).cuboid(-8.0F, -0.75F, -3.0F, 16.0F, 22.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData armorRightArm = modelPartData.addChild("armorRightArm", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 2.0F, 0.0F));

		armorRightArm.addChild("cloak_arm_right", ModelPartBuilder.create().uv(79, 36).cuboid(-5.0F, -2.75F, -3.0F, 6.0F, 22.0F, 6.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData armorLeftArm = modelPartData.addChild("armorLeftArm", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 2.0F, 0.0F));

		armorLeftArm.addChild("cloak_arm_left", ModelPartBuilder.create().uv(104, 36).cuboid(-1.0F, -2.75F, -3.0F, 6.0F, 22.0F, 6.0F, new Dilation(0.1F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorHead.copyTransform(this.head);
		this.armorHead.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);

		armorBody.xScale = body.xScale;
		armorBody.yScale = body.yScale;
		armorBody.zScale = body.zScale;
		armorBody.pivotX = body.pivotX;
		armorBody.pivotY = body.pivotY;
		armorBody.pivotZ = body.pivotZ;
		this.armorBody.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);

		armorRightArm.xScale = rightArm.xScale;
		armorRightArm.yScale = rightArm.yScale;
		armorRightArm.zScale = rightArm.zScale;
		armorRightArm.pivotX = rightArm.pivotX;
		armorRightArm.pivotY = rightArm.pivotY;
		armorRightArm.pivotZ = rightArm.pivotZ;
		this.armorRightArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);

		armorLeftArm.xScale = leftArm.xScale;
		armorLeftArm.yScale = leftArm.yScale;
		armorLeftArm.zScale = leftArm.zScale;
		armorLeftArm.pivotX = leftArm.pivotX;
		armorLeftArm.pivotY = leftArm.pivotY;
		armorLeftArm.pivotZ = leftArm.pivotZ;
		this.armorLeftArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(LivingEntity livingEntity, float f, float g, float h, float i, float j) {
		float k = 0.0836246F;
		float l = -0.0710998125F;
		float m = 0.0F;
		float n = 0.0F;
		if (livingEntity.isSprinting() || livingEntity.isInSneakingPose()) {
			k = 0.1636246F;
			l = -0.1658996062F;
			m = 3.125F;
			n = 0.01908953812F;
		} else if (!livingEntity.isOnGround() || livingEntity.getVelocity().horizontalLengthSquared() > 0) {
			k = 0.1236246F;
			l = -0.1636246F;
			m = 2.125F;
			n = 0.00818122875F;
		}

		this.armorLeftArm.pivotY = m * 2;
		this.cloakPitch += (k * 2 - this.cloakPitch);
		this.cloakYaw += (n * 2 - this.cloakYaw) * 0.5F;
		this.cloakRoll += (l * 2 - this.cloakRoll) * 0.5F;
		this.armorLeftArm.pitch = this.cloakPitch;
		this.armorLeftArm.yaw = this.cloakYaw;
		this.armorLeftArm.roll = this.cloakRoll;

		this.armorRightArm.yaw = -this.armorLeftArm.yaw;
		this.armorRightArm.pivotY = this.armorLeftArm.pivotY;
		this.armorRightArm.pitch = this.armorLeftArm.pitch;
		this.armorRightArm.roll = -this.armorLeftArm.roll;

		this.armorBody.pitch = this.armorLeftArm.pitch;
		this.armorBody.pivotY = this.armorLeftArm.pivotY;
	}
}