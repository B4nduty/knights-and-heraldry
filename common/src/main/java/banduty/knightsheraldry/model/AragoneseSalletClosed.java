package banduty.knightsheraldry.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class AragoneseSalletClosed extends HumanoidModel<LivingEntity> {
	private final ModelPart headh;

	public AragoneseSalletClosed(ModelPart root) {
		super(root);
		this.headh = root.getChild("headh");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition headh = partdefinition.addOrReplaceChild("headh", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition sallet = headh.addOrReplaceChild("sallet", CubeListBuilder.create().texOffs(86, 84).addBox(-5.0F, 4.3F, -1.0F, 10.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(80, 0).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, -7.0F, -3.0F));

		PartDefinition visor = headh.addOrReplaceChild("visor", CubeListBuilder.create().texOffs(83, 16).addBox(-4.0F, -1.75F, -1.9F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.65F)), PartPose.offset(0.0F, -6.0F, -2.0F));

		PartDefinition armet_2_r1 = visor.addOrReplaceChild("armet_2_r1", CubeListBuilder.create().texOffs(80, 50).addBox(-4.5F, -1.9514F, -2.6152F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, -0.5F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r1 = visor.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(80, 42).addBox(-4.5F, -0.7929F, -5.9533F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, -0.5F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.headh.copyFrom(this.head);
		this.headh.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}