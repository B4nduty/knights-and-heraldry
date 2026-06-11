package banduty.knightsheraldry.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class AragoneseSalletOpened extends HumanoidModel<LivingEntity> {
	private final ModelPart armorHead;

	public AragoneseSalletOpened(ModelPart root) {
		super(root);
		this.armorHead = root.getChild("armorHead");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition armorHead = partdefinition.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F))
				.texOffs(80, 0).addBox(-1.0F, -9.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition sallet = armorHead.addOrReplaceChild("sallet", CubeListBuilder.create().texOffs(88, 25).addBox(-5.0F, -3.15F, -5.25F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition visor = sallet.addOrReplaceChild("visor", CubeListBuilder.create().texOffs(83, 16).addBox(-4.0F, -1.75F, -1.9F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offsetAndRotation(0.0F, -6.0F, -2.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition armet_2_r1 = visor.addOrReplaceChild("armet_2_r1", CubeListBuilder.create().texOffs(80, 50).addBox(-4.5F, -1.8514F, -2.6152F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.25F, -0.5F, -0.2182F, 0.0F, 0.0F));

		PartDefinition eyeslit_3_r1 = visor.addOrReplaceChild("eyeslit_3_r1", CubeListBuilder.create().texOffs(108, 37).addBox(-9.5F, -6.815F, -2.85F, 9.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(5.0F, 6.75F, 1.4F, 0.4363F, 0.0F, 0.0F));

		PartDefinition eyeslit_3_r2 = visor.addOrReplaceChild("eyeslit_3_r2", CubeListBuilder.create().texOffs(106, 48).addBox(-9.5F, -6.55F, -2.25F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 6.0F, 1.5F, 0.6545F, 0.0F, 0.0F));

		PartDefinition rotation = visor.addOrReplaceChild("rotation", CubeListBuilder.create(), PartPose.offset(0.0F, 4.9412F, -0.5129F));

		PartDefinition sallet_5_r1 = rotation.addOrReplaceChild("sallet_5_r1", CubeListBuilder.create().texOffs(103, 16).addBox(-5.981F, -0.2479F, -0.0315F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.9F, -5.8F, 0.0F, 0.5672F, 0.0F));

		PartDefinition sallet_4_r1 = rotation.addOrReplaceChild("sallet_4_r1", CubeListBuilder.create().texOffs(103, 16).mirror().addBox(-0.02F, -0.2479F, -0.0315F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.9F, -5.8F, 0.0F, -0.5672F, 0.0F));

		PartDefinition bevor = visor.addOrReplaceChild("bevor", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.6443F, -0.7673F, 0.4363F, 0.0F, 0.0F));

		PartDefinition sallet_4_r2 = bevor.addOrReplaceChild("sallet_4_r2", CubeListBuilder.create().texOffs(88, 41).addBox(-5.13F, -1.6944F, 0.1856F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9598F, -5.9F, 0.0F, 0.6109F, 0.0F));

		PartDefinition sallet_3_r1 = bevor.addOrReplaceChild("sallet_3_r1", CubeListBuilder.create().texOffs(88, 41).mirror().addBox(0.13F, -1.6944F, 0.1856F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.9598F, -5.9F, 0.0F, -0.6109F, 0.0F));

		PartDefinition bone = visor.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(90, 52).addBox(-0.5F, -1.25F, -0.05F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.643F, -2.5677F, -0.9163F, 0.0F, 0.0F));

		PartDefinition rotation2 = sallet.addOrReplaceChild("rotation2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, 4.6564F, -0.3491F, 0.0F, 0.0F));

		PartDefinition sallet_4_r3 = rotation2.addOrReplaceChild("sallet_4_r3", CubeListBuilder.create().texOffs(70, 25).addBox(-2.7418F, -2.8397F, -2.7418F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.75F, -1.4064F, 0.0F, -0.7854F, 0.0F));

		PartDefinition rotation3 = sallet.addOrReplaceChild("rotation3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -8.1758F, 4.5046F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r1 = rotation3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(112, 1).addBox(-0.0863F, 0.0237F, -1.8015F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4102F, 0.0F));

		PartDefinition cube_r2 = rotation3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(112, 1).mirror().addBox(-4.9137F, 0.0237F, -1.8015F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4102F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.armorHead.copyFrom(this.head);
		this.armorHead.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}