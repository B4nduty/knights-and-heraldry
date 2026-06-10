package banduty.knightsheraldry.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class BlackSalletClosed extends HumanoidModel<LivingEntity> {
	private final ModelPart armorHead;

	public BlackSalletClosed(ModelPart root) {
		super(root);
		this.armorHead = root.getChild("armorHead");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition armorHead = partdefinition.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition sallet = armorHead.addOrReplaceChild("sallet", CubeListBuilder.create().texOffs(88, 25).addBox(-5.0F, -3.15F, -4.8F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition visor = sallet.addOrReplaceChild("visor", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -2.0F));

		PartDefinition rotation = visor.addOrReplaceChild("rotation", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.5108F, -2.8501F, -0.48F, 0.0F, 0.0F));

		PartDefinition sallet_3_r1 = rotation.addOrReplaceChild("sallet_3_r1", CubeListBuilder.create().texOffs(75, 17).addBox(-5.0F, -3.0F, 0.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.1F, 0.0F, 0.48F, 0.0F));

		PartDefinition sallet_2_r1 = rotation.addOrReplaceChild("sallet_2_r1", CubeListBuilder.create().texOffs(91, 17).addBox(0.0F, -3.0F, 0.0F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.1F, 0.0F, -0.48F, 0.0F));

		PartDefinition sallet_4_r1 = rotation.addOrReplaceChild("sallet_4_r1", CubeListBuilder.create().texOffs(108, 19).addBox(-5.01F, -1.2479F, -0.0315F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.05F)), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.0F, 0.48F, 0.0F));

		PartDefinition sallet_3_r2 = rotation.addOrReplaceChild("sallet_3_r2", CubeListBuilder.create().texOffs(108, 19).mirror().addBox(0.01F, -1.2479F, -0.0315F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.05F)).mirror(false), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, 0.0F, -0.48F, 0.0F));

		PartDefinition teeth = visor.addOrReplaceChild("teeth", CubeListBuilder.create(), PartPose.offset(0.0F, 3.2548F, -5.8515F));

		PartDefinition sallet_4_r2 = teeth.addOrReplaceChild("sallet_4_r2", CubeListBuilder.create().texOffs(84, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.1F, 0.0F, 0.48F, 0.0F));

		PartDefinition sallet_3_r3 = teeth.addOrReplaceChild("sallet_3_r3", CubeListBuilder.create().texOffs(84, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.1F, 0.0F, -0.48F, 0.0F));

		PartDefinition rotation2 = sallet.addOrReplaceChild("rotation2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, 4.6564F, 0.1745F, 0.0F, 0.0F));

		PartDefinition sallet_4_r3 = rotation2.addOrReplaceChild("sallet_4_r3", CubeListBuilder.create().texOffs(70, 25).addBox(-2.2418F, -2.8397F, -2.2418F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.75F, -1.4064F, 0.0F, -0.7854F, 0.0F));

		PartDefinition rotation3 = sallet.addOrReplaceChild("rotation3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -8.29F, 4.9394F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r1 = rotation3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(112, 1).addBox(-0.0863F, 0.0237F, -1.9015F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.4F, -0.1F, 0.0F, 0.7156F, 0.0F));

		PartDefinition cube_r2 = rotation3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(112, 1).mirror().addBox(-4.9137F, 0.0237F, -1.9015F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.4F, -0.1F, 0.0F, -0.7156F, 0.0F));

		PartDefinition bevor = sallet.addOrReplaceChild("bevor", CubeListBuilder.create().texOffs(90, 37).addBox(-4.5F, 1.0F, -4.65F, 9.0F, 2.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -2.918F, -0.3632F, 0.1309F, 0.0F, 0.0F));

		PartDefinition sallet_4_r4 = bevor.addOrReplaceChild("sallet_4_r4", CubeListBuilder.create().texOffs(90, 42).addBox(-4.0F, -1.0F, 0.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.2F, -5.9F, 0.0F, 0.3054F, 0.0F));

		PartDefinition sallet_3_r4 = bevor.addOrReplaceChild("sallet_3_r4", CubeListBuilder.create().texOffs(102, 42).addBox(0.0F, -1.0F, 0.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.2F, -5.9F, 0.0F, -0.3054F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.armorHead.copyFrom(this.head);
		this.armorHead.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}