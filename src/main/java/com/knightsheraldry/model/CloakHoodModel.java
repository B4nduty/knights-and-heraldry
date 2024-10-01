package com.knightsheraldry.model;

import net.minecraft.client.model.*;
import net.minecraft.client.network.AbstractClientPlayerEntity;
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
		ModelPartData armorHead = modelPartData.addChild("armorHead", ModelPartBuilder.create().uv(56, 0).cuboid(-4.5F, -9.5F, -8.1F, 9.0F, 10.0F, 6.0F, new Dilation(0.6F))
				.uv(40, 16).cuboid(-4.5F, -9.5F, -0.9F, 9.0F, 10.0F, 6.0F, new Dilation(0.6F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		armorHead.addChild("hood_tail_r1", ModelPartBuilder.create().uv(86, 0).cuboid(-4.5F, -23.95F, 19.33F, 9.0F, 9.0F, 3.0F, new Dilation(0.59F)), ModelTransform.of(0.0F, 24.2F, 1.0F, 0.6109F, 0.0F, 0.0F));

		modelPartData.addChild("armorBody", ModelPartBuilder.create().uv(34, 36).cuboid(-8.0F, -0.75F, -3.0F, 16.0F, 22.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		modelPartData.addChild("armorLeftArm", ModelPartBuilder.create().uv(104, 36).cuboid(-1.0F, -2.75F, -3.0F, 6.0F, 22.0F, 6.0F, new Dilation(0.1F)), ModelTransform.pivot(4.0F, 2.0F, 0.0F));

		modelPartData.addChild("armorRightArm", ModelPartBuilder.create().uv(79, 36).cuboid(-5.0F, -2.75F, -3.0F, 6.0F, 22.0F, 6.0F, new Dilation(0.1F)), ModelTransform.pivot(-4.0F, 2.0F, 0.0F));
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
		float k = 0.1636246F;
		float l = -0.0710998125F;
		float m = 0.0F;
		float n = 0.0F;
		if (livingEntity.isSprinting() || livingEntity.isInSneakingPose()) {
			k = 0.61086515F;
			l = -0.1658996062F;
			m = 2.875F;
			n = 0.01908953812F;
		} else if (livingEntity.isOnGround() && livingEntity.getVelocity().horizontalLengthSquared() > 0 || !livingEntity.isOnGround()) {
			k = 0.26179935F;
			l = -0.1636246F;
			m = 2.125F;
			n = 0.00818122875F;
		}

		this.armorLeftArm.pivotY = m;
		if (livingEntity instanceof AbstractClientPlayerEntity) {
			this.cloakPitch += (k - this.cloakPitch) * 0.1F;
			this.cloakYaw += (n - this.cloakYaw) * 0.1F;
			this.cloakRoll += (l - this.cloakRoll) * 0.1F;
			this.armorLeftArm.pitch = this.cloakPitch;
			this.armorLeftArm.yaw = this.cloakYaw;
			this.armorLeftArm.roll = this.cloakRoll;
		} else {
			this.armorLeftArm.pitch = k;
			this.armorLeftArm.roll = l;
			this.armorLeftArm.yaw = n;
		}

		this.armorRightArm.yaw = -this.armorLeftArm.yaw;
		this.armorRightArm.pivotY = this.armorLeftArm.pivotY;
		this.armorRightArm.pitch = this.armorLeftArm.pitch;
		this.armorRightArm.roll = -this.armorLeftArm.roll;

		this.armorBody.pitch = this.armorLeftArm.pitch;
		this.armorBody.pivotY = this.armorLeftArm.pivotY;
	}
}