package banduty.knightsheraldry.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class PauldronsModel extends HumanoidModel<LivingEntity> {
	public final ModelPart armorRightArm;
	public final ModelPart armorLeftArm;

	public PauldronsModel(ModelPart root) {
		super(root);
		this.setAllVisible(false);
		this.armorRightArm = root.getChild("armorRightArm");
		this.armorLeftArm = root.getChild("armorLeftArm");
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.armorRightArm, this.armorLeftArm);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
		PartDefinition partdefinition = modelData.getRoot();

		PartDefinition armorRightArm = partdefinition.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(96, 0).addBox(-4.0F, -2.25F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F))
				.texOffs(112, 10).addBox(-3.0F, -2.26F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-4.0F, 2.0F, 0.0F));

		armorRightArm.addOrReplaceChild("right_plate_rim_r1", CubeListBuilder.create().texOffs(114, 18).addBox(-1.0F, -5.0F, -3.5F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -0.5F, 0.0F, 0.0F, -0.2618F));

		armorRightArm.addOrReplaceChild("right_besagew_r1", CubeListBuilder.create().texOffs(108, 20).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.1F, -5.0F, 0.0F, 0.2618F, 0.0F));

		armorRightArm.addOrReplaceChild("right_pauldron_r1", CubeListBuilder.create().texOffs(112, 0).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offsetAndRotation(1.0F, -0.25F, -2.75F, 0.0F, 0.5236F, 0.0F));

		armorRightArm.addOrReplaceChild("right_pauldron_r2", CubeListBuilder.create().texOffs(80, 0).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offsetAndRotation(1.0F, -0.25F, 1.75F, 0.0F, -0.3491F, 0.0F));

		armorRightArm.addOrReplaceChild("right_pauldron_r3", CubeListBuilder.create().texOffs(96, 10).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.44F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition armorLeftArm = partdefinition.addOrReplaceChild("armorLeftArm", CubeListBuilder.create().texOffs(96, 0).mirror().addBox(0.0F, -2.25F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false)
				.texOffs(112, 10).mirror().addBox(-1.0F, -2.26F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offset(4.0F, 2.0F, 0.0F));

		armorLeftArm.addOrReplaceChild("left_plate_rim_r1", CubeListBuilder.create().texOffs(114, 18).addBox(1.0F, -5.0F, -3.5F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.2618F));

		armorLeftArm.addOrReplaceChild("left_besagew_r1", CubeListBuilder.create().texOffs(118, 20).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.1F, -5.0F, 0.0F, -0.3491F, 0.0F));

		armorLeftArm.addOrReplaceChild("left_pauldron_r1", CubeListBuilder.create().texOffs(112, 0).mirror().addBox(0.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -0.25F, -2.75F, 0.0F, -0.5236F, 0.0F));

		armorLeftArm.addOrReplaceChild("left_pauldron_r2", CubeListBuilder.create().texOffs(80, 0).mirror().addBox(0.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -0.25F, 1.75F, 0.0F, 0.3491F, 0.0F));

		armorLeftArm.addOrReplaceChild("left_pauldron_r3", CubeListBuilder.create().texOffs(96, 10).mirror().addBox(0.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.44F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.armorRightArm.copyFrom(this.rightArm);
		armorRightArm.render(poseStack, buffer, packedLight, packedOverlay, color);

		this.armorLeftArm.copyFrom(this.leftArm);
		armorLeftArm.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}