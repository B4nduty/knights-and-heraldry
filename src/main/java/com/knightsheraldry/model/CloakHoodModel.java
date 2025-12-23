package com.knightsheraldry.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class CloakHoodModel extends HumanoidModel<LivingEntity> {
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
	public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition armorHead = modelPartData.addOrReplaceChild("armorHead", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hood_head = armorHead.addOrReplaceChild("hood_head", CubeListBuilder.create().texOffs(40, 0).addBox(-4.5F, -8.5F, -6.0F, 9.0F, 9.0F, 4.0F, new CubeDeformation(0.2F))
				.texOffs(40, 13).addBox(-4.5F, -8.5F, -1.6F, 9.0F, 9.0F, 6.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		hood_head.addOrReplaceChild("hood_tail_r1", CubeListBuilder.create().texOffs(70, 16).addBox(-1.0F, -22.15F, 9.13F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		hood_head.addOrReplaceChild("hood_tail_r2", CubeListBuilder.create().texOffs(66, 8).addBox(-2.5F, -26.15F, 10.33F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		hood_head.addOrReplaceChild("hood_tail_r3", CubeListBuilder.create().texOffs(66, 0).addBox(-3.5F, -23.95F, 19.33F, 7.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition jester = hood_head.addOrReplaceChild("jester", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		jester.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(119, 0).mirror().addBox(-9.25F, -9.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.25F, 0.0F, 0.5F, 0.0F, 0.0F, 0.1309F));

		jester.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(119, 0).addBox(7.25F, -9.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.5F, 0.0F, 0.0F, -0.1309F));

		jester.addOrReplaceChild("hood_r1", CubeListBuilder.create().texOffs(110, 20).addBox(-2.5F, -9.1F, 7.55F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		jester.addOrReplaceChild("hood_r2", CubeListBuilder.create().texOffs(80, 20).addBox(-4.5F, -8.5F, 2.4F, 9.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		jester.addOrReplaceChild("ear_r1", CubeListBuilder.create().texOffs(120, 0).addBox(-4.5F, -11.0F, -2.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.5F, 0.0F, 0.0F, -0.1745F));

		jester.addOrReplaceChild("ear_r2", CubeListBuilder.create().texOffs(120, 0).addBox(3.5F, -11.0F, -2.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.5F, 0.0F, 0.0F, 0.1745F));

		PartDefinition bell = jester.addOrReplaceChild("bell", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -8.5F, 8.0F, 0.3491F, 0.0F, 0.0F));

		bell.addOrReplaceChild("hood_r3", CubeListBuilder.create().texOffs(113, 0).addBox(-1.5F, -1.5F, 0.05F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition legacy = hood_head.addOrReplaceChild("legacy", CubeListBuilder.create().texOffs(0, 48).addBox(-4.5F, -9.5F, -0.9F, 9.0F, 10.0F, 6.0F, new CubeDeformation(0.6F))
				.texOffs(0, 32).addBox(-4.5F, -9.5F, -8.1F, 9.0F, 10.0F, 6.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		legacy.addOrReplaceChild("hood_tail_r4", CubeListBuilder.create().texOffs(0, 20).addBox(-4.5F, -23.95F, 19.33F, 9.0F, 9.0F, 3.0F, new CubeDeformation(0.59F)), PartPose.offsetAndRotation(0.0F, 24.2F, 1.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition armorBody = modelPartData.addOrReplaceChild("armorBody", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		armorBody.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(34, 36).addBox(-8.0F, -0.75F, -3.0F, 16.0F, 22.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorRightArm = modelPartData.addOrReplaceChild("armorRightArm", CubeListBuilder.create(), PartPose.offset(-4.0F, 2.0F, 0.0F));

		armorRightArm.addOrReplaceChild("cloak_arm_right", CubeListBuilder.create().texOffs(79, 36).addBox(-5.0F, -2.75F, -3.0F, 6.0F, 22.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorLeftArm = modelPartData.addOrReplaceChild("armorLeftArm", CubeListBuilder.create(), PartPose.offset(4.0F, 2.0F, 0.0F));

		armorLeftArm.addOrReplaceChild("cloak_arm_left", CubeListBuilder.create().texOffs(104, 36).addBox(-1.0F, -2.75F, -3.0F, 6.0F, 22.0F, 6.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 128, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.armorHead.copyFrom(this.head);
		this.armorHead.render(poseStack, vertexConsumer, light, overlay, red, green, blue, alpha);

		armorBody.xScale = body.xScale;
		armorBody.yScale = body.yScale;
		armorBody.zScale = body.zScale;
		armorBody.x = body.x;
		armorBody.y = body.y;
		armorBody.z = body.z;
		this.armorBody.render(poseStack, vertexConsumer, light, overlay, red, green, blue, alpha);

		armorRightArm.xScale = rightArm.xScale;
		armorRightArm.yScale = rightArm.yScale;
		armorRightArm.zScale = rightArm.zScale;
		armorRightArm.x = rightArm.x;
		armorRightArm.y = rightArm.y;
		armorRightArm.z = rightArm.z;
		this.armorRightArm.render(poseStack, vertexConsumer, light, overlay, red, green, blue, alpha);

		armorLeftArm.xScale = leftArm.xScale;
		armorLeftArm.yScale = leftArm.yScale;
		armorLeftArm.zScale = leftArm.zScale;
		armorLeftArm.x = leftArm.x;
		armorLeftArm.y = leftArm.y;
		armorLeftArm.z = leftArm.z;
		this.armorLeftArm.render(poseStack, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

    @Override
	public void setupAnim(LivingEntity livingEntity, float f, float g, float h, float i, float j) {
		float k = 0.0836246F;
		float l = -0.0710998125F;
		float m = 0.0F;
		float n = 0.0F;
		if (livingEntity.isSprinting() || livingEntity.isCrouching()) {
			k = 0.1636246F;
			l = -0.1658996062F;
			m = 3.125F;
			n = 0.01908953812F;
		} else if (!livingEntity.onGround() || livingEntity.getDeltaMovement().horizontalDistanceSqr() > 0) {
			k = 0.1236246F;
			l = -0.1636246F;
			m = 2.125F;
			n = 0.00818122875F;
		}

		this.armorLeftArm.y = m * 2;
		this.cloakPitch += (k * 2 - this.cloakPitch);
		this.cloakYaw += (n * 2 - this.cloakYaw) * 0.5F;
		this.cloakRoll += (l * 2 - this.cloakRoll) * 0.5F;
		this.armorLeftArm.xRot = this.cloakPitch;
		this.armorLeftArm.yRot = this.cloakYaw;
		this.armorLeftArm.zRot = this.cloakRoll;

		this.armorRightArm.yRot = -this.armorLeftArm.yRot;
		this.armorRightArm.y = this.armorLeftArm.y;
		this.armorRightArm.xRot = this.armorLeftArm.xRot;
		this.armorRightArm.zRot = -this.armorLeftArm.zRot;

		this.armorBody.xRot = this.armorLeftArm.xRot;
		this.armorBody.y = this.armorLeftArm.y;
	}
}