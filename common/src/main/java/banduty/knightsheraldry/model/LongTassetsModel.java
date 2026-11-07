package banduty.knightsheraldry.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class LongTassetsModel extends HumanoidModel<LivingEntity> {
	public final ModelPart leftTasset;
	public final ModelPart rightTasset;

	public LongTassetsModel(ModelPart root) {
		super(root);
		this.leftTasset = root.getChild("leftTasset");
		this.rightTasset = root.getChild("rightTasset");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = modelData.getRoot();

		PartDefinition leftTasset = partdefinition.addOrReplaceChild("leftTasset", CubeListBuilder.create()
				.texOffs(16, 74).mirror().addBox(-1.5F, -0.3348F, -3.1024F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.ZERO);

		leftTasset.addOrReplaceChild("left_poleyn_r1", CubeListBuilder.create()
				.texOffs(16, 84).mirror().addBox(2.7F, 2.45F, 1.15F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(16, 84).mirror().addBox(-2.3F, 4.45F, 1.15F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.1652F, -0.2024F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rightTasset = partdefinition.addOrReplaceChild("rightTasset", CubeListBuilder.create()
				.texOffs(16, 74).addBox(-2.5F, -0.3348F, -3.1024F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.ZERO);

		rightTasset.addOrReplaceChild("right_poleyn_r1", CubeListBuilder.create()
				.texOffs(16, 84).addBox(-2.7F, 2.45F, 1.15F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(16, 84).addBox(-2.7F, 4.45F, 1.15F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1652F, -0.2024F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.rightTasset.copyFrom(this.rightLeg);
		this.rightTasset.render(poseStack, buffer, packedLight, packedOverlay, color);

		this.leftTasset.copyFrom(this.leftLeg);
		this.leftTasset.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}