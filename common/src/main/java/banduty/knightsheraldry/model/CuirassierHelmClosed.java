package banduty.knightsheraldry.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class CuirassierHelmClosed extends HumanoidModel<LivingEntity> {
	private final ModelPart armorHead;

	public CuirassierHelmClosed(ModelPart root) {
		super(root);
		this.armorHead = root.getChild("armorHead");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition armorHead = partdefinition.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(83, 16).addBox(-4.0F, -7.75F, -3.9F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.65F))
		.texOffs(80, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.6F))
		.texOffs(0, 4).addBox(-4.0F, -6.0F, -7.4142F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(104, 35).addBox(-1.0F, -10.0F, -2.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(104, 33).addBox(-1.0F, -8.0F, 4.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition barbute_2_r1 = armorHead.addOrReplaceChild("barbute_2_r1", CubeListBuilder.create().texOffs(114, 67).addBox(-1.61F, -8.0F, -6.0F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition barbute_1_r1 = armorHead.addOrReplaceChild("barbute_1_r1", CubeListBuilder.create().texOffs(100, 67).addBox(-3.39F, -8.0F, -6.0F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition grid = armorHead.addOrReplaceChild("grid", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_grid_2_r1 = grid.addOrReplaceChild("right_grid_2_r1", CubeListBuilder.create().texOffs(112, 54).addBox(0.61F, -5.0F, -6.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(118, 54).addBox(-1.39F, -5.0F, -6.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.5F, -1.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition left_grid_2_r1 = grid.addOrReplaceChild("left_grid_2_r1", CubeListBuilder.create().texOffs(112, 54).addBox(-1.61F, -5.0F, -6.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(118, 54).addBox(0.39F, -5.0F, -6.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.5F, -1.0F, 0.0F, -0.2618F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.armorHead.copyFrom(this.head);
		this.armorHead.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}