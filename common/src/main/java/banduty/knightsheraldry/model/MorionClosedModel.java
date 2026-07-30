package banduty.knightsheraldry.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class MorionClosedModel extends HumanoidModel<LivingEntity> {
	private final ModelPart armorHead;

	public MorionClosedModel(ModelPart root) {
        super(root);
        this.armorHead = root.getChild("armorHead");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition armorHead = partdefinition.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F))
		.texOffs(68, 6).addBox(-0.5F, -9.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(86, 16).addBox(-0.5F, -10.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = armorHead.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(74, 21).addBox(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -10.75F, 0.0F, 0.3578F, 0.0F, 0.0F));

		PartDefinition cube_r2 = armorHead.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(86, 21).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -10.75F, 0.0F, -0.3578F, 0.0F, 0.0F));

		PartDefinition cube_r3 = armorHead.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(104, 0).addBox(-0.5F, -0.35F, -3.7F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 7.5F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r4 = armorHead.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(104, 0).addBox(-0.5F, -0.35F, -0.3F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -7.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition bevor = armorHead.addOrReplaceChild("bevor", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.5201F, -6.2481F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r5 = bevor.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(120, 11).addBox(0.5F, -4.5F, -0.1F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(118, 9).addBox(0.5F, -4.5F, -0.2F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(116, 0).mirror().addBox(0.0F, -5.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r6 = bevor.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(120, 11).addBox(-4.5F, -4.5F, -0.1F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(118, 9).addBox(-4.5F, -4.5F, -0.2F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(116, 0).addBox(-5.0F, -5.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r7 = bevor.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(116, 0).mirror().addBox(0.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, -0.25F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r8 = bevor.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(116, 0).addBox(-5.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -0.25F, 0.0F, 0.3491F, 0.0F));

		PartDefinition bevor2 = armorHead.addOrReplaceChild("bevor2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.4783F, -6.1072F, 0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r9 = bevor2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(116, 6).mirror().addBox(0.0F, -2.0F, 0.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r10 = bevor2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(116, 6).addBox(-5.0F, -2.0F, 0.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition rim_right = armorHead.addOrReplaceChild("rim_right", CubeListBuilder.create().texOffs(86, 16).addBox(-9.0F, -0.5F, -6.0F, 9.0F, 1.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r11 = rim_right.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(106, 32).addBox(0.1737F, -0.5F, -1.0152F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 0.0F, 5.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r12 = rim_right.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(106, 29).addBox(0.1737F, -0.5F, -0.9848F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 0.0F, -5.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition rim_left = armorHead.addOrReplaceChild("rim_left", CubeListBuilder.create().texOffs(86, 16).mirror().addBox(0.0F, -0.5F, -6.0F, 9.0F, 1.0F, 12.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r13 = rim_left.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(106, 32).mirror().addBox(-9.1737F, -0.5F, -1.0152F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.0F, 0.0F, 5.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r14 = rim_left.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(106, 29).mirror().addBox(-9.1737F, -0.5F, -0.9848F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.0F, 0.0F, -5.0F, 0.0F, -0.1745F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.armorHead.copyFrom(this.head);
		this.armorHead.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}